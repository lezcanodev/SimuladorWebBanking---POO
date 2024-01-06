package GUI;

import App.Session;
import Controller.TransferenciaController;
import Exceptions.CuentaMontoInsuficienteException;
import Exceptions.PINTransaccionIncorrectoExcepcion;
import GUI.Componentes.ComboBoxConLabel;
import GUI.Componentes.Form;
import GUI.Componentes.ShowOptionDialogGuardarMessage;
import GUI.Componentes.TextFieldConLabel;
import GUI.PatronObservador.PublisherSaldo;
import Model.Cuenta;
import Model.CuentaDeTercero;
import Model.Transferencia;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que renderiza el formulario para realizar una transferencia
 * entre cuentas
 * @author Grupo 14
 */
public class GUITransferencia extends GUIJPanelBasic{
    private final Session session;
    private final SimuladorWebBanking simulador;
    
    /**
     * Constrcutor de la clase GUITransferencia
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     */
    public GUITransferencia(Session session, SimuladorWebBanking simulador) {
        super("Transferencia");
        this.session = session;
        this.simulador = simulador;   
        cargarContenido();
    } // Fin constructor

    /**
     * Crea el formulario para realizar una transferencia 
     * @return formulario para transferencia
     */
    @Override
    public JPanel contenido() {
        Form formPagoServicioPasoUno = new Form();
        
        // Añadimos al formulario el campo concepto
        TextFieldConLabel concepto = new TextFieldConLabel("Concepto");
        formPagoServicioPasoUno.addForm(concepto);
        
        // Añadimos al formulario la lista de mis cuentas
        ComboBoxConLabel cuentasOrigen = new ComboBoxConLabel("Cuenta Origen (Mis cuentas)");
        for (Cuenta miCuenta : session.getCliente().getMisCuentas()) {
            cuentasOrigen.addItem(
                    "Cruenta Nro. "+miCuenta.getNroCuenta(),
                    miCuenta
            );
        }
        formPagoServicioPasoUno.addForm(cuentasOrigen);
        
        // Añadimos al formulario la lista de mis cuentas y cuentas de tercero
        ComboBoxConLabel cuentasDestino = new ComboBoxConLabel("Cuenta Destino (Mis cuentas y cuentas de tercero)");
        for (Cuenta miCuenta : session.getCliente().getMisCuentas()) {
            cuentasDestino.addItem(
                    "Cruenta Nro. "+miCuenta.getNroCuenta(),
                    miCuenta
            );
        }
        if(session.getCliente().getMisCuentasRegistradas() != null){
            for (CuentaDeTercero cuentaTercero : session.getCliente().getMisCuentasRegistradas()) {
                cuentasDestino.addItem(
                        "Cruenta Nro. "+cuentaTercero.getNroCuenta()+" "+cuentaTercero.getNombreApellido(),
                        cuentaTercero
                );
            }
        }
        formPagoServicioPasoUno.addForm(cuentasDestino);
        
        // Añadimos al formulario el campo monto a transferir
        TextFieldConLabel montoATransferir = new TextFieldConLabel("Monto");
        formPagoServicioPasoUno.addForm(montoATransferir);
        
        // Agregamos al formulario al boton transferir
        JButton buttonTransferir = new JButton("Transferir");
        buttonTransferir.addActionListener((ActionEvent e) -> {
            TransferenciaController tsc = new TransferenciaController(session, simulador);
            Cuenta origen = (Cuenta)cuentasOrigen.getSelectedItem().getValue();
            Object destino = cuentasDestino.getSelectedItem().getValue();
            
            
            Transferencia transferencia = null;
            try{
                if(destino instanceof Cuenta){
                    transferencia =  tsc.validarDatosTransferencia(
                            origen,
                            (Cuenta)destino,
                            montoATransferir.getValueJTextField(),
                            concepto.getValueJTextField()                        
                    );
                }else{
                    transferencia =  tsc.validarDatosTransferencia(
                            origen,
                            (CuentaDeTercero)destino,
                            montoATransferir.getValueJTextField(),
                            concepto.getValueJTextField()                        
                    ); 
                }
                
                String detalle = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Detalle</div></html>\n";
                detalle += transferencia.getDetalle();
                detalle += "\n\n Ingrese el PIN de transaccion";
                
                String pinTransaccion = JOptionPane.showInputDialog(
                this,
                detalle,
                "Confirmar pago",
                JOptionPane.QUESTION_MESSAGE
                );
                
                if(pinTransaccion == null) return;
                
                String comprobante = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Comprobante</div></html>\n";
                comprobante += tsc.validarTransferencia(
                    transferencia,
                    pinTransaccion
                );
               
                new ShowOptionDialogGuardarMessage(
                    comprobante,
                    "Comprobante",
                           "transferencia-"+transferencia.getConcepto()+".txt"
                );
                
                PublisherSaldo.getInstancia().notificarCambioSaldo();
                
            }catch(IllegalArgumentException | CuentaMontoInsuficienteException | PINTransaccionIncorrectoExcepcion ex){
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
   
            
            
        });
        formPagoServicioPasoUno.addForm(buttonTransferir);
        
        return formPagoServicioPasoUno;
    }
}
