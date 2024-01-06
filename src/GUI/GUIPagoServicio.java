package GUI;

import App.Session;
import Controller.PagoServicioController;
import Exceptions.ClienteNoTienePagosServicioException;
import Exceptions.CuentaMontoInsuficienteException;
import Exceptions.PINTransaccionIncorrectoExcepcion;
import Exceptions.PagoServicioMontoInsuficienteException;
import Exceptions.PagoServicioMontoSuperiorException;
import Exceptions.PagoServicioNoExisteException;
import Exceptions.TarjetaCreditoMontoInsuficienteException;
import GUI.Componentes.ComboBoxConLabel;
import GUI.Componentes.Form;
import GUI.Componentes.RadioGroupConLabel;
import GUI.Componentes.ShowOptionDialogGuardarMessage;
import GUI.Componentes.TextFieldConLabel;
import GUI.PatronObservador.PublisherSaldo;
import Model.Cuenta;
import Model.PagoServicio;
import Model.Servicio;
import Model.TarjetaCredito;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que renderiza el formulario para realizar el pago de un servicio
 * @author Grupo 14
 */
public class GUIPagoServicio extends GUIJPanelBasic{
    private final Session session;
    private final SimuladorWebBanking simulador;
    
    /**
     * Constrcutor de la clase GUIPagoServicio
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     */
    public GUIPagoServicio(Session session, SimuladorWebBanking simulador) {
        super("Pagar servicio");
        this.session = session;
        this.simulador = simulador;   
        cargarContenido();
    } // Fin constructor

    /**
     * Añade inicialmente el formulario pago servicio paso uno
     * @return formulario pago servicio paso uno
     */
    @Override
    public JPanel contenido() {
        JPanel contenido = new JPanel();
        // Añadimos el formulario
        contenido.add(formulatioPasoUno());
        return contenido;
    }
    
    /**
     * Creamos el formulario pago servicio paso uno
     * @return formulario pago servicio paso uno
     */
    public JPanel formulatioPasoUno(){
        Form formPagoServicioPasoUno = new Form();
        
        // Añadimos al formulario las lista de servicios
        ComboBoxConLabel comboBoxServicios = new ComboBoxConLabel("Servicios");
        for (Servicio servicio : simulador.getServicios()) {
            if(servicio == null) continue;
            comboBoxServicios.addItem(
                    servicio.toString(),
                    servicio
            );
        }
        formPagoServicioPasoUno.addForm(comboBoxServicios);
        
        // Añadimos al formulario el campo identificador cliente
        TextFieldConLabel identificadorCliente = new TextFieldConLabel("Identificador del cliente");
        formPagoServicioPasoUno.addForm(identificadorCliente);
       
        
        // Añadimos al formulario el campo identificador cliente
        RadioGroupConLabel metodoPagoGroup = new RadioGroupConLabel("Metodo de pago");
        JRadioButton metodoCuenta=new JRadioButton("Cuenta");    
        JRadioButton metodoTarjeta=new JRadioButton("Tarjeta credito");
        metodoCuenta.setSelected(true);
        metodoPagoGroup.addRadio(metodoCuenta);
        metodoPagoGroup.addRadio(metodoTarjeta);
        formPagoServicioPasoUno.addForm(metodoPagoGroup);
        
        // Añadimos al formulario el boton siguiente
        JButton buttonSiguiente = new JButton("Siguiente");
        buttonSiguiente.addActionListener((ActionEvent e) -> {
            PagoServicioController pgs = new PagoServicioController(session, simulador);
            try {
                Servicio s = (Servicio)comboBoxServicios.getSelectedItem().getValue();

                PagoServicio pg = pgs.validarDatosServicio(
                        s.getCodServicio(),
                        identificadorCliente.getValueJTextField()
                );
   
                formularioPasoDos(pg, metodoCuenta.isSelected());
              
            } catch (IllegalArgumentException | PagoServicioNoExisteException | ClienteNoTienePagosServicioException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        formPagoServicioPasoUno.addForm(buttonSiguiente);
        
        return formPagoServicioPasoUno;
    }
     
    /**
     * Cargamos el formulario paso dos dependiendo del tipo de pago
     * @param pagoServicio servicio a pagar
     * @param pagoConCuenta booleano que indica si el pago sera con cuenta
     */
    private void formularioPasoDos(PagoServicio pagoServicio, Boolean pagoConCuenta){
        if(pagoConCuenta){
            formularioPasoDosConCuenta(pagoServicio);
        }else{
            if(session.getCliente().getMisTarjetasCredito() == null){
                JOptionPane.showMessageDialog(
                    this,
                    "No tienes ninguna tarjeta de credito",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(pagoServicio.soportaTarjetaCredito()){
                formularioPasoDosConTarjeta(pagoServicio);
                return;
            }
            JOptionPane.showMessageDialog(
                    this,
                    pagoServicio.getEmpresa()+" no soporta pagos con tarjeta de credito",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Creamos el formulario pago servicio paso dos con cuenta y
     * actualizamos el contenido 
     * @param pagoServicio servicio a pagar
     */
    public void formularioPasoDosConCuenta(PagoServicio pagoServicio){
        Form formPagoServicioPasoDos = new Form();
        
        JLabel empresa = new JLabel("Servicio: "+pagoServicio.getEmpresa());
        formPagoServicioPasoDos.addForm(empresa);
        
        JLabel detalleIdentificador = new JLabel(pagoServicio.getDetalleIdentificador());
        formPagoServicioPasoDos.addForm(detalleIdentificador);
        
        TextFieldConLabel montoPagar = new TextFieldConLabel("Monto a pagar");
        formPagoServicioPasoDos.addForm(montoPagar);
        
        if(pagoServicio.getMonto() != null){
            montoPagar.setValueJTextField(pagoServicio.getMonto().toString());
        }
        
        ComboBoxConLabel comboBoxCuentas = new ComboBoxConLabel("Cuentas");
        for (Cuenta miCuenta : session.getCliente().getMisCuentas()) {
            comboBoxCuentas.addItem(
                    ("Cuenta nro. "+miCuenta.getNroCuenta()),
                    miCuenta
            );
        }
        formPagoServicioPasoDos.addForm(comboBoxCuentas);
        
        JButton buttonRealizarPago = new JButton("Realizar pago");
        buttonRealizarPago.addActionListener((ActionEvent e) -> {
            // Obtenemos la cuenta seleccionada
            Cuenta cuenta = (Cuenta)comboBoxCuentas.getSelectedItem().getValue();
            PagoServicioController pgs = new PagoServicioController(session, simulador);
            
            // Generamos el detalle para mostrar al cliente
            String detalle = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Detalle</div></html>\n";
            detalle += pagoServicio.getDetalle()+"\n";
            detalle += "METODO: Cuenta\n";
            detalle +=  cuenta+"\n";
            detalle += "MONTO: "+montoPagar.getValueJTextField()+"\n";
            detalle += "NOMBRE: "+session.getCliente().getNombreApellido();
            
            detalle += "\n\n Ingrese el PIN de transaccion";
            String pinTransaccion = JOptionPane.showInputDialog(
                this,
                detalle,
                "Confirmar pago",
                JOptionPane.QUESTION_MESSAGE);
            
            // si es null significa que el usuario cancelo el pago
            if(pinTransaccion == null) return;
            
            try {
                String comprobante = pgs.validarPagoServicio(
                        cuenta,
                        pinTransaccion,
                        pagoServicio,
                        montoPagar.getValueJTextField());
                
                String cmp = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Comprobante</div></html>\n";
                cmp += comprobante;
                
                new ShowOptionDialogGuardarMessage(
                    cmp,
                    "Comprobante",
                           "comprobante-"+pagoServicio.getEmpresa()+".txt"
                );
                
                PublisherSaldo.getInstancia().notificarCambioSaldo();
                
                // Una vez finalizado el pago Redireccionamos al 
                // usuario nuevamente al paso uno
                actualizarContenido(formulatioPasoUno());
                
            } catch (PINTransaccionIncorrectoExcepcion | CuentaMontoInsuficienteException | IllegalArgumentException |
                     PagoServicioMontoInsuficienteException | PagoServicioMontoSuperiorException ex) {
                JOptionPane.showMessageDialog(
                        this, 
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);  
            }
            
        }); 
        formPagoServicioPasoDos.addForm(buttonRealizarPago);
        
        actualizarContenido(formPagoServicioPasoDos);
    }
    
    
    /**
     * Creamos el formulario pago servicio paso dos con tarjeta de credito y
     * actualizamos el contenido 
     * @param pagoServicio servicio a pagar
     */
    public void formularioPasoDosConTarjeta(PagoServicio pagoServicio){
        Form formPagoServicioPasoDos = new Form();
        
        JLabel empresa = new JLabel("Servicio: "+pagoServicio.getEmpresa());
        formPagoServicioPasoDos.addForm(empresa);
        
        JLabel detalleIdentificador = new JLabel(pagoServicio.getDetalleIdentificador());
        formPagoServicioPasoDos.addForm(detalleIdentificador);
        
        TextFieldConLabel montoPagar = new TextFieldConLabel("Monto a pagar");
        formPagoServicioPasoDos.addForm(montoPagar);
        
        if(pagoServicio.getMonto() != null){
            montoPagar.setValueJTextField(pagoServicio.getMonto().toString());
        }
        
        ComboBoxConLabel comboBoxCuentas = new ComboBoxConLabel("Tarjetas de credito");
        for (TarjetaCredito tarjeta : session.getCliente().getMisTarjetasCredito()) {
            comboBoxCuentas.addItem(
                    ("Tarjeta nro: "+ tarjeta.getNroTarjeta()),
                    tarjeta
            );
        }
        formPagoServicioPasoDos.addForm(comboBoxCuentas);
        
        JButton buttonRealizarPago = new JButton("Realizar pago");
        buttonRealizarPago.addActionListener((ActionEvent e) -> {
            TarjetaCredito tarjeta = (TarjetaCredito)comboBoxCuentas.getSelectedItem().getValue();
            PagoServicioController pgs = new PagoServicioController(session, simulador);
            String detalle = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Detalle</div></html>\n";
            detalle += pagoServicio.getDetalle()+"\n";
            detalle += "METODO: Tarjeta de credito\n";
            detalle +=  tarjeta+"\n";
            detalle += "MONTO: "+montoPagar.getValueJTextField()+"\n";
            detalle += "NOMBRE: "+session.getCliente().getNombreApellido();
            
            detalle += "\n\n Ingrese el PIN de transaccion";
            String pinTransaccion = JOptionPane.showInputDialog(
                this,
                detalle,
                "Confirmar pago",
                JOptionPane.QUESTION_MESSAGE);
            
            if(pinTransaccion == null) return;
            
            try {
                String comprobante = pgs.validarPagoServicio(
                        tarjeta,
                        pinTransaccion,
                        pagoServicio,
                        montoPagar.getValueJTextField());
                
                String cmp = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Comprobante</div></html>\n";
                cmp += comprobante;
                
                new ShowOptionDialogGuardarMessage(
                    cmp,
                    "Comprobante",
                           "comprobante-"+pagoServicio.getEmpresa()+".txt"
                );
                
                PublisherSaldo.getInstancia().notificarCambioSaldo();

                // Una vez finalizado el pago Redireccionamos al 
                // usuario nuevamente al paso uno
                actualizarContenido(formulatioPasoUno());
                
            } catch (PINTransaccionIncorrectoExcepcion | TarjetaCreditoMontoInsuficienteException | IllegalArgumentException |
                     PagoServicioMontoInsuficienteException | PagoServicioMontoSuperiorException ex) {
                JOptionPane.showMessageDialog(
                        this, 
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);  
            }
            
        }); 
        formPagoServicioPasoDos.addForm(buttonRealizarPago);
        
        actualizarContenido(formPagoServicioPasoDos);
    }

}
