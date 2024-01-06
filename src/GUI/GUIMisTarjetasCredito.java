package GUI;

import App.Session;
import Model.Cliente;
import Model.TarjetaCredito;
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
 * Clase que renderiza las tarjetas de credito del cliente
 * @author Grupo 14
 */
public class GUIMisTarjetasCredito extends GUIJPanelBasic{
    private final Session session;
    private final SimuladorWebBanking simulador;
    private final GUIVentanaPrincipal guiVentanaPrincipal;
    
    /**
     * Constrcutor de la clase GUIMisTarjetasCredito
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     * @param guiVentanaPrincipal referencia a la instancia de ventana principal
     */
    public GUIMisTarjetasCredito(Session session, SimuladorWebBanking simulador,GUIVentanaPrincipal guiVentanaPrincipal) {
        super("Mis tarjetas de credito");
        this.session = session;
        this.simulador = simulador;   
        this.guiVentanaPrincipal = guiVentanaPrincipal;
        cargarContenido();
    } // Fin constructor

    /**
     * Crea el contenido de GUIMisTarjetasCredito y lo retorna
     * @return mis tarjetas de credito
     */
    @Override
    public JPanel contenido() {
        JPanel contenido = new JPanel();
        Cliente c = session.getCliente();
        contenido.setLayout(new BorderLayout());
        contenido.setBorder(new EmptyBorder(5,5,5,5));
                
        // Creamos un jpanel para las tarjetas de credito del cliente
        JPanel contenidoTarjetasCredito = new JPanel();
        contenidoTarjetasCredito.setLayout(new GridBagLayout());
        GridBagConstraints contenidoTarjetasCreditoConstrainsts = new GridBagConstraints();
        
        contenidoTarjetasCreditoConstrainsts.insets = new Insets(5, 5,5,5);
        
        if(c.getMisTarjetasCredito() == null){
            JLabel noTieneTarjetasLabel = new JLabel(
            "<html><h3>No tienes ninguna tarjeta de credito</h3></html>"
            );
            contenido.add(noTieneTarjetasLabel);
            return contenido;
        }
        
        int gridx = 0;
        int gridy = 0;
        int maxGridx = 3;
        for (TarjetaCredito miTarjeta : c.getMisTarjetasCredito()) {
            contenidoTarjetasCreditoConstrainsts.gridx = gridx;
            contenidoTarjetasCreditoConstrainsts.gridy = gridy;
            contenidoTarjetasCredito.add(tarjetaCreditoJPanel(miTarjeta), contenidoTarjetasCreditoConstrainsts );
            gridx++;
            if(gridx%maxGridx == 0){
                gridx = 0;
                gridy++;
            }
        }
        
        
        // Añadimos la cuentas del cliente al contenido
        contenido.add(contenidoTarjetasCredito, BorderLayout.CENTER);
        
        return contenido;
    }
    
    
    /**
     * Crea un JPanel con las informacion de la tarjeta de credito
     * @param tarjeta tarjeta de credito del cliente
     * @return JPanel con informacion de cuenta
     */
    public JPanel tarjetaCreditoJPanel(TarjetaCredito tarjeta){
        JPanel tarjetaJP = new JPanel();
        tarjetaJP.setLayout(new GridBagLayout());
        GridBagConstraints cuentaJpConstraints = new GridBagConstraints();
        
        tarjetaJP.setBackground(Color.WHITE);
        cuentaJpConstraints.insets = new Insets(8,15,8,15);
        cuentaJpConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        
        // Añadimos el label nro tarjeta
        JLabel labelNroTarjeta = new JLabel("Nro. tarjeta: "+tarjeta.getNroTarjeta());
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 0;
        tarjetaJP.add(labelNroTarjeta, cuentaJpConstraints);
        
        // Añadimos el label fecha de vencimiento
        JLabel labelFechaVencimiento = new JLabel("Fecha de vencimiento: "+tarjeta.getFechaVencimiento());
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 1;
        tarjetaJP.add(labelFechaVencimiento, cuentaJpConstraints);
        
        // Añadimos el label saldo disponible
        JLabel labelSaldoDisponible = new JLabel("Saldo disponible: "+tarjeta.getSaldoDisponible());
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 2;
        tarjetaJP.add(labelSaldoDisponible, cuentaJpConstraints);
        
        // Añadimos el label deuda total
        JLabel labelDeudaTotal = new JLabel("Deuda total: "+tarjeta.getDeudaTotal());
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 3;
        tarjetaJP.add(labelDeudaTotal, cuentaJpConstraints);
        
        // Añadimos el  boton ver saldo
        JButton buttonVerSaldo = new JButton("Pagar");
        buttonVerSaldo.addActionListener((ActionEvent) -> {
           if(tarjeta.getDeudaTotal() == 0){
               JOptionPane.showMessageDialog(
                       this,
                       "No hay nada que pagar para esta tarjeta de credito",
                        "Tarjeta de credito",
                       JOptionPane.INFORMATION_MESSAGE
               );
               return;
           }
           guiVentanaPrincipal.actualizarContenidoCentral(new GUIPagoTarjetaCredito(
                    session, 
                    simulador, 
                    tarjeta,
                    guiVentanaPrincipal
           ));
        });
        cuentaJpConstraints.gridx = 0;
        cuentaJpConstraints.gridy = 4;
        cuentaJpConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        tarjetaJP.add(buttonVerSaldo, cuentaJpConstraints);
        
        return tarjetaJP;
    }
   
    
}
