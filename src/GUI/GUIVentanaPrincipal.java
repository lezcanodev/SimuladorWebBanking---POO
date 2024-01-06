package GUI;

import App.Session;
import GUI.PatronObservador.PublisherSaldo;
import GUI.PatronObservador.SuscriptorSaldo;
import Model.Cliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que renderiza la ventana principal donde 
 * solo ingresaran los cliente autentificados
 * @author Grupo 14
 */
public class GUIVentanaPrincipal extends JFrame implements SuscriptorSaldo{
    private final Session session;
    private final SimuladorWebBanking simulador;
    private JPanel aside;
    JPanel contenidoPrincipal; // Mantiene el contenido central de la ventana
    
    /**
     * Consturctor de la ventana GUIVentanaPrincipal
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     */
    public GUIVentanaPrincipal(Session session, SimuladorWebBanking simulador){
        super();
        this.session = session;
        this.simulador = simulador;
        this.contenidoPrincipal = new JPanel();
        this.aside = new JPanel();
        this.contenidoPrincipal.setLayout(new BorderLayout());
        this.contenidoPrincipal.setBorder(new EmptyBorder(5,15,5,15));
        
        setTitle("Cliente: "+session.getCliente().getNombreApellido());
        setLayout(new BorderLayout());
        setBounds(100,100,800,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
       
        add(aside(), BorderLayout.WEST);
        cotenidoInicial();
    } // Fin constructor
    
    
    /**
     * Añade el contenido central de la ventana
     * GUIVentanaPrincipal
     */
    private void addCotenidoCentral(JPanel contenido){
        contenidoPrincipal.removeAll();
        contenidoPrincipal.add(contenido, BorderLayout.CENTER);
        add(contenidoPrincipal, BorderLayout.CENTER);
        contenidoPrincipal.revalidate();
        contenidoPrincipal.repaint();
    }
    
    /**
     * Crea el contenido central inicial y lo añade
     */
    private void cotenidoInicial(){
        JPanel contenidoInicial = new JPanel();
        Cliente c = session.getCliente();
        // Añadimos el mensaje de bienveninda
        JLabel labelNombreApellidoCliente = new JLabel(
        "<html><h1>Bienvenido "+c.getNombreApellido()+"</h1></html>"
        );
        contenidoInicial.add(labelNombreApellidoCliente);
        
        addCotenidoCentral(contenidoInicial);
    }

    
    /**
     * Crea el contenido del lado izquierdo de la ventana
     * GUIVentanaPrincipal y lo retorna
     * @return contenido
     */
    private JPanel aside(){
        aside.setLayout(new GridBagLayout());
        GridBagConstraints asideConstrains = new GridBagConstraints();
        Cliente c = session.getCliente();
        
        asideConstrains.weightx = 1;
        asideConstrains.fill = GridBagConstraints.HORIZONTAL;
        asideConstrains.anchor = GridBagConstraints.CENTER;
        
        aside.setBackground(Color.ORANGE);
        
        // Añadimos el nombre y apellido del cliente
        JLabel labelNombreApellidoCliente = new JLabel(
        "<html><h2>"+c.getNombreApellido()+"</h2></html>"
        );
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 0;
        asideConstrains.insets = new Insets(5,5,0,5);
        aside.add(labelNombreApellidoCliente, asideConstrains);
        
        // Añadimos el saldo total del cliente
        JLabel labelSaldoTotalCliente = new JLabel(
        "<html><bold>Saldo total:"+c.saldoTotal()+"</bold></html>"
        );
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 1;
        asideConstrains.insets = new Insets(0,5,40,5);
        aside.add(labelSaldoTotalCliente, asideConstrains);
        
         
        asideConstrains.insets = new Insets(5,5,5,5);
        
        // Añadimos el boton para ingresar a mis cuentas
        JButton misCuentas = new JButton("Mis cuentas");
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 2;
        misCuentas.addActionListener((ActionEvent e)-> {
            addCotenidoCentral((JPanel)(new GUIMisCuentas(session, simulador)));
        });
        aside.add(misCuentas, asideConstrains);
        
        //Añadimos el boton para ingresar a mis tarjetas de credito
        JButton tarjetasCredito = new JButton("Mis tarjetas de credito");
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 3;
        tarjetasCredito.addActionListener((ActionEvent e)-> {
            cargarGUIMistarjetasCredito();
        });
        aside.add(tarjetasCredito, asideConstrains);
        
        // Añadimos el boton para pagar un servicio
        JButton pagarServicio = new JButton("Pagar servicio");
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 4;
        pagarServicio.addActionListener((ActionEvent e)-> {
            addCotenidoCentral((JPanel)(new GUIPagoServicio(session, simulador)));
        });
        aside.add(pagarServicio, asideConstrains);
        
        // Añadimos el boton transferencia
        JButton transferencia = new JButton("Transferencia");
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 5;
        transferencia.addActionListener((ActionEvent e)-> {
            addCotenidoCentral((JPanel)(new GUITransferencia(session, simulador)));
        });
        aside.add(transferencia, asideConstrains);
        
        // Añadimos el boton acerca del sistema
        JButton depositar = new JButton("Realizar deposito");
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 6;
        depositar.addActionListener((ActionEvent e)-> {
            new GUICajeroDepositoSimulador(simulador.getCajeroSimulador(), this);    
        });
        aside.add(depositar, asideConstrains);
        
        // Añadimos el boton acerca del sistema
        JButton acercaDelSistema = new JButton("Acerca del sistema");
        asideConstrains.gridx = 0;
        asideConstrains.gridy = 7;
        acercaDelSistema.addActionListener((ActionEvent e)-> {
            new GUIAcercaDelSistema();    
        });
        aside.add(acercaDelSistema, asideConstrains);
        
        return aside;
    }
    
    /**
     * Renderiza GUIMistTarjetasCredito como contenido central
     */
    public void cargarGUIMistarjetasCredito(){
        addCotenidoCentral((JPanel)(new GUIMisTarjetasCredito(session, simulador, this)));
    }
    
    
    /**
     * Actualiza el contenido del panel izquierda de la app principalmente
     * lo actualiza cuando cambia el saldo
     */
    public void actualizarSaldo(){
        aside.removeAll();
        add(aside(), BorderLayout.WEST);
        aside.revalidate();
        aside.repaint();
    }
    
    /**
     * Actualiza el contenido central de la app con un nuevo
     * contenido
     */
    public void actualizarContenidoCentral(JPanel nuevoContenido){
        addCotenidoCentral(nuevoContenido);
    }
    
   
    
}
