package GUI;

import Exceptions.CuentaNoExisteException;
import GUI.Componentes.Form;
import GUI.Componentes.ShowOptionDialogGuardarMessage;
import GUI.Componentes.TextFieldConLabel;
import GUI.PatronObservador.PublisherSaldo;
import Model.Deposito;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import simuladorwebbanking.CajeroDepositoSimulador;

/**
 * Clase encargada de renderizar el GUI para simular un cajero que solo puede
 * realizar la operacion de de deposito
 * @author Grupo 14
 */
public class GUICajeroDepositoSimulador extends GUIBasic{    
    private CajeroDepositoSimulador cajero;
    private GUIVentanaPrincipal guiVentanPrincipal;
    /**
     * Constructor de la clase GUICajeroDepositoSimulador
     * @param cajero simulador de deposito
     * @param guiVentanPrincipal referencia a la instancia de ventana principal
     */
    public GUICajeroDepositoSimulador(CajeroDepositoSimulador cajero, GUIVentanaPrincipal guiVentanPrincipal) {
        super("Cajero");
        setBounds(50,50,400,370);
        setResizable(false);
        this.cajero = cajero;
        this.guiVentanPrincipal = guiVentanPrincipal;
        iniciar();
    } // Fin constructor
    
    /**
     * Creamos el formulario de deposito y lo retornamos
     * @return formulario de deposito
     */
    @Override
    public JPanel contenido() {
        JPanel contenido = new JPanel();
        contenido.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.insets = new Insets(10,10,10,10);
        
        JPanel formulario = new JPanel();
        formulario.setLayout(new GridBagLayout());
        
        // Creamos el formulario depositante
        JPanel depositante = new JPanel();
        depositante.setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Depositante");
        depositante.add(titulo, BorderLayout.NORTH);
        
        Form formularioDepositante = new Form();
        
        // Añadimos al formulario el campo ci del depositante
        TextFieldConLabel ciDepositante = new TextFieldConLabel("CI");
        formularioDepositante.addForm(ciDepositante);
        
        // Añadimos al formulario el campo nombre del depositante
        TextFieldConLabel nombreDepositante = new TextFieldConLabel("Nombre");
        formularioDepositante.addForm(nombreDepositante);
        
        // Añadimos al formulario el campo apellido del depositante
        TextFieldConLabel apellidoDepositante = new TextFieldConLabel("Apellido");
        formularioDepositante.addForm(apellidoDepositante);
        
        depositante.add(formularioDepositante,BorderLayout.CENTER);
        
        constraints.gridx=0;
        formulario.add(depositante, constraints);
        // fin formulario depositante
        
        
        // Creamos el formulario depositario
        JPanel depositario = new JPanel();
        depositario.setLayout(new BorderLayout());
        
        JLabel tituloDepositario = new JLabel("Depositario");
        depositario.add(tituloDepositario, BorderLayout.NORTH);
        
        Form formDepositario = new Form();
        
        // Añadimos al formulario el campo numero de cuenta del depositario
        TextFieldConLabel nroCuentaDepositario = new TextFieldConLabel("Numero de cuenta");
        formDepositario.addForm(nroCuentaDepositario);
        
        // Añadimos al formulario el campo ruc o ci del depositario
        TextFieldConLabel rucOciDepositario = new TextFieldConLabel("RUC o CI");
        formDepositario.addForm(rucOciDepositario);
        
        // Añadimos al formulario el campo monto a depositar 
        TextFieldConLabel montoADepositar = new TextFieldConLabel("Monto a depositar");
        formDepositario.addForm(montoADepositar);
        
        depositario.add(formDepositario,BorderLayout.CENTER);
        
        constraints.gridx=1;
        formulario.add(depositario, constraints);
        // fin formulario depositario

        constraints.gridy=0;
        contenido.add(formulario, constraints);
        
        JButton buttonRealizarDeposito = new JButton("Depositar");
        buttonRealizarDeposito.addActionListener((ActionEvent e) -> {
            try{
                Deposito deposito = cajero.validarDatosDeposito(
                        nroCuentaDepositario.getValueJTextField(),
                        rucOciDepositario.getValueJTextField(),
                        montoADepositar.getValueJTextField(),
                        ciDepositante.getValueJTextField(),
                        nombreDepositante.getValueJTextField(),
                        apellidoDepositante.getValueJTextField()
                );
                
                String detalle = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Detalle</div></html>\n";
                detalle += deposito.getDetalle();
                
                int confirmado = JOptionPane.showConfirmDialog(
                        this,
                        detalle,
                        "Detalle",
                        JOptionPane.YES_NO_OPTION
                );
                
                if(confirmado == JOptionPane.YES_OPTION){
                    deposito.realizarDeposito();
                    String comprobante = "<html><div style=\"font-weight:bold; font-size:16px; border-bottom: 1px dashed black;  \" >Comprobante</div></html>\n";
                    comprobante += deposito.getDetalle();
                    
                    new ShowOptionDialogGuardarMessage(
                        comprobante,
                        "Comprobante",
                               "deposito-cuenta"+nroCuentaDepositario.getValueJTextField()+".txt"
                    );
                
                    
                    PublisherSaldo.getInstancia().notificarCambioSaldo();
                }
                
            }catch(IllegalArgumentException | CuentaNoExisteException ex){
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            
            
            
        });
        constraints.gridy=1;
        contenido.add(buttonRealizarDeposito, constraints);
        
        return contenido;
    }
}
