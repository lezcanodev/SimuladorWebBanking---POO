package GUI;

import App.Session;
import Controller.PagoTarjetaCreditoController;
import Exceptions.CuentaMontoInsuficienteException;
import Exceptions.PINTransaccionIncorrectoExcepcion;
import GUI.Componentes.ComboBoxConLabel;
import GUI.Componentes.Form;
import GUI.Componentes.ShowOptionDialogGuardarMessage;
import GUI.Componentes.TextFieldConLabel;
import GUI.PatronObservador.PublisherSaldo;
import Model.Cuenta;
import Model.PagoTarjetaCredito;
import Model.TarjetaCredito;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que renderiza el formulario de pago de tarejta de credito
 * @author Grupo 14
 */
public class GUIPagoTarjetaCredito extends GUIJPanelBasic{
    private final Session session;
    private final SimuladorWebBanking simulador;
    private final TarjetaCredito tarjeta;
    private final GUIVentanaPrincipal guiVentanaPrincipal;
    
    /**
     * Constrcutor de la clase GUIPagoTarjetaCredito
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     * @param tarjeta tarjeta de creditoa a pagar
     * @param guiVentanaPrincipal referencia a la instancia de ventana principal
     */
    public GUIPagoTarjetaCredito(Session session, SimuladorWebBanking simulador,TarjetaCredito tarjeta,GUIVentanaPrincipal guiVentanaPrincipal) {
        super("Pago de tarjeta de credito");
        this.session = session;
        this.simulador = simulador;   
        this.tarjeta = tarjeta;
        this.guiVentanaPrincipal = guiVentanaPrincipal;
        cargarContenido();
    } // Fin constructor

    /**
     * Añade inicialmente el formulario pago servicio paso uno
     * @return formulario pago servicio paso uno
     */
    @Override
    public JPanel contenido() {
        JPanel contenido = new JPanel();
        Form formPagoTarjetaCredito = new Form();
        
        // Agregamos el label numero de tarjeta de credito
        JLabel labelNroTarjeta = new JLabel("Nro. tarjeta: "+tarjeta.getNroTarjeta() );
        formPagoTarjetaCredito.addForm(labelNroTarjeta);
        
        // Agregamos el label numero de tarjeta de credito
        JLabel labelFechaVencimiento = new JLabel("Fecha de vencimiento: "+tarjeta.getFechaVencimiento() );
        formPagoTarjetaCredito.addForm(labelFechaVencimiento);
        
        // Agregamos el label pago minimo
        JLabel labelPagoMinimo = new JLabel("Pago minimo: "+tarjeta.calcularPagoMinimo() );
        formPagoTarjetaCredito.addForm(labelPagoMinimo);
        
        // Agregamos el label deuda total
        JLabel labelDeudaTotal = new JLabel("Deuda total: "+tarjeta.getDeudaTotal());
        formPagoTarjetaCredito.addForm(labelDeudaTotal);
        
        // Agregamos el label de comision
        JLabel labelComision = new JLabel("Comision: "+tarjeta.getComision());
        formPagoTarjetaCredito.addForm(labelComision);
        
        // Agreamos el campo Monto a pagar        
        TextFieldConLabel montoPagar = new TextFieldConLabel("Monto a pagar");
        montoPagar.setValueJTextField(tarjeta.calcularPagoMinimo()+"");
        formPagoTarjetaCredito.addForm(montoPagar);
        
        // Agregamos el comobox de cuentas del cliente
        ComboBoxConLabel comboBoxCuentas = new ComboBoxConLabel("Cuentas");
        for (Cuenta miCuenta : session.getCliente().getMisCuentas()) {
            comboBoxCuentas.addItem(
                    ("Cuenta nro. "+miCuenta.getNroCuenta()),
                    miCuenta
            );
        }
        formPagoTarjetaCredito.addForm(comboBoxCuentas);
        
        //Agregamos el boton realizar pago
        JButton buttonRealizarPago = new JButton("Realizar pago");
        buttonRealizarPago.addActionListener((ActionEvent e) -> {            
            Cuenta cuenta = (Cuenta)comboBoxCuentas.getSelectedItem().getValue();
            
            PagoTarjetaCreditoController ptc = new PagoTarjetaCreditoController(session, simulador);
            
            try {
                PagoTarjetaCredito pago = ptc.validarDatosPagoTarjetaCredito(
                        cuenta,
                        tarjeta,
                        montoPagar.getValueJTextField()
                );
                
                String detalle = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Detalle</div></html>\n";
                detalle += pago.getDetalle();
                detalle += "\nNOMBRE: "+session.getCliente().getNombreApellido();
                detalle += "\n\n Ingrese el PIN de transaccion";
                
                String pinTransaccion = JOptionPane.showInputDialog(
                    this,
                    detalle,
                    "Confirmar pago",
                    JOptionPane.QUESTION_MESSAGE
                );
            
                if(pinTransaccion == null) return; 
                String comprobante = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Comprobante</div></html>\n";
                comprobante += ptc.validarPagoTarjetaCredito(pago, pinTransaccion);
                comprobante +="\nNOMBRE: "+session.getCliente().getNombreApellido();
                
                new ShowOptionDialogGuardarMessage(
                    comprobante,
                    "Comprobante",
                           "comprobante-"+tarjeta.getNroTarjeta()+".txt"
                );
                
                PublisherSaldo.getInstancia().notificarCambioSaldo();
                guiVentanaPrincipal.cargarGUIMistarjetasCredito();
                
            } catch (PINTransaccionIncorrectoExcepcion | IllegalArgumentException | CuentaMontoInsuficienteException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            
        });
        formPagoTarjetaCredito.addForm(buttonRealizarPago);
        
        
        // Añadimos el formulario
        contenido.add(formPagoTarjetaCredito);
        return contenido;
    }
}
