package GUI;

import App.Session;
import Controller.CuentaController;
import Model.Cliente;
import Model.Cuenta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que renderiza las cuentas del cliente
 * @author Grupo 14
 */
public class GUIMisCuentas extends GUIJPanelBasic{
    private final Session session;
    private final SimuladorWebBanking simulador;

    /**
     * Constrcutor de la clase GUIMisCuentas
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     */
    public GUIMisCuentas(Session session, SimuladorWebBanking simulador) {
        super("Mis cuentas");
        this.session = session;
        this.simulador = simulador;   
        cargarContenido();
    } // Fin constructor

    /**
     * Crea y devuelve un JPanel con las cuentas del cliente 
     * @return JPanel mis cuentas
     */
    @Override
    public JPanel contenido() {
        JPanel contenido = new JPanel();
        Cliente c = session.getCliente();
        contenido.setLayout(new BorderLayout());
        contenido.setBorder(new EmptyBorder(5,5,5,5));
        
        // Label del saldo actual del cliente
        JLabel labelSaldoActual = new JLabel(
          "<html><h4>Saldo actual: " + c.saldoTotal()+"</h4></html>"
        );
        contenido.add(labelSaldoActual, BorderLayout.NORTH);
        
        // Creamos un jpanel para cuentas del cliente
        JPanel contenidoCuentas = new JPanel();
        contenidoCuentas.setLayout(new GridBagLayout());
        GridBagConstraints contenidoCuentasConstrainsts = new GridBagConstraints();
        
        // margen entre las tarjetas
        contenidoCuentasConstrainsts.insets = new Insets(5, 5,5,5);
        
        //posicion inicial
        int gridx = 0;
        int gridy = 0;
        //maximo de columnas
        int maxGridx = 3; 
        
        CuentaController cc = new CuentaController(session,simulador);
        for (Cuenta miCuenta : c.getMisCuentas()) {
            try{
                cc.validarCuenta(miCuenta);
                contenidoCuentasConstrainsts.gridx = gridx;
                contenidoCuentasConstrainsts.gridy = gridy;
                contenidoCuentas.add(cuentaJPanel(miCuenta), contenidoCuentasConstrainsts );
                gridx++;
                if(gridx%maxGridx == 0){
                    gridx = 0;
                    gridy++;
                }
            }catch(IllegalArgumentException ex){
                //Solo ignoramos las cuentas que no sean validas
            }
        }
        
        
        // Añadimos la cuentas del cliente al contenido
        contenido.add(contenidoCuentas, BorderLayout.CENTER);
        
        return contenido;
    }
    
    
    /**
     * Crea un JPanel con las informacion de la cuenta
     * @param cuenta cuenta del cliente
     * @return JPanel con informacion de cuenta
     */
    public JPanel cuentaJPanel(Cuenta cuenta){
        JPanel cuentaJP = new JPanel();
        cuentaJP.setLayout(new GridBagLayout());
        GridBagConstraints cuentaJpConstraints = new GridBagConstraints();
        
        cuentaJP.setBackground(Color.WHITE);
        cuentaJpConstraints.insets = new Insets(8,50,8,50);
        cuentaJpConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        
        // Añadimos el label nro cuenta
        JLabel labelNroCuenta = new JLabel("Nro. Cuenta: "+cuenta.getNroCuenta());
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 0;
        cuentaJP.add(labelNroCuenta, cuentaJpConstraints);
        
        // Añadimos el label tipo cuenta
        JLabel labelTipo = new JLabel("Tipo: "+cuenta.getTipo());
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 1;
        cuentaJP.add(labelTipo, cuentaJpConstraints);
        
        // Añadimos el  boton ver saldo
        JButton buttonVerSaldo = new JButton("Ver saldo");
        buttonVerSaldo.addActionListener((ActionEvent) -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Saldo de la cuenta nro. "+cuenta.getNroCuenta()+" es "+cuenta.getSaldo(),
                    "Cuenta nro. "+cuenta.getNroCuenta(),
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 2;
        cuentaJpConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        cuentaJP.add(buttonVerSaldo, cuentaJpConstraints);
        
        return cuentaJP;
    }
   
    
}
