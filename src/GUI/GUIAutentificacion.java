package GUI;

import App.Session;
import Controller.AutentificarController;
import Exceptions.ClienteNoExisteException;
import GUI.Componentes.TextFieldConLabel;
import GUI.Componentes.TextPasswordConLabel;
import GUI.PatronObservador.PublisherSaldo;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que renderiza la ventana Autentificacion
 * @author Grupo 14
 */
public class GUIAutentificacion extends GUIBasic{
    private final SimuladorWebBanking simuladorWebBanking;
    
    /**
     * Constructor de la clase GUIAutentiificacion
     * @param simulador instancia del simulador que contiene todos los datos
     */
    public GUIAutentificacion(SimuladorWebBanking simulador){
        super("Autentificarse");
        setBounds(400,400,300,300);
        setResizable(false);
        this.simuladorWebBanking = simulador;
        iniciar();
    } // Fin constructor

    /**
     * Contenido de la ventana GUIAutentificacion
     * @return JPanel del contenido de GUIAutentificacion
     */
    @Override
    public JPanel contenido() {
        AutentificarController autentificarController = new AutentificarController(simuladorWebBanking);
        JPanel contenido = new JPanel();
        contenido.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        
        gridBagConstraints.fill  = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(15, 0, 0,0);
        
        // Añadimos el campo rucOci
        TextFieldConLabel rucOci = new TextFieldConLabel("Ruc o ci");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        contenido.add(rucOci, gridBagConstraints);
        
        // Añadimos el campo pin de cuenta
        TextPasswordConLabel pinCuenta = new TextPasswordConLabel("Pin de cuenta");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        contenido.add(pinCuenta, gridBagConstraints);
        
        //Añadirmos el boton ingresar
        JButton buttonIngresar = new JButton("Ingresar");

        buttonIngresar.addActionListener((ActionEvent e) -> {
            try {
                Session session = autentificarController.validarDatosAuth(
                        rucOci.getValueJTextField(),
                        pinCuenta.getValueJTextField()
                );
                
                PublisherSaldo publisherSaldo = PublisherSaldo.getInstancia();
                GUIVentanaPrincipal guiVentanaPrincipal = new GUIVentanaPrincipal(session, simuladorWebBanking);
                publisherSaldo.addSuscriptor(guiVentanaPrincipal);
                
            }catch(ClienteNoExisteException ex) {
                JOptionPane.showMessageDialog(
                        this, 
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }catch(IllegalArgumentException ex){
                JOptionPane.showMessageDialog(
                        this, 
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);  
            }catch(Exception ex){
                JOptionPane.showMessageDialog(
                        this, 
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill  = GridBagConstraints.NONE;
        contenido.add(buttonIngresar, gridBagConstraints);
        
        return contenido;
    }
}
