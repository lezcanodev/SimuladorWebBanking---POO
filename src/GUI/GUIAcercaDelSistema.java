package GUI;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Clase que renderiza una ventna acerca del sistema
 * @author Grupo 14
 */
public class GUIAcercaDelSistema extends JFrame{        
    private GridBagConstraints layoutConstraints;
    private JPanel contenido; 
    private JPanel jpGrupo;
    
    /**
     * Constructor de la clase GUIAcercaDelSistema
     */
    public GUIAcercaDelSistema(){
        super();
        setTitle("Acerca del Sistema");
        setBounds(50,50,540,440);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Contendra todo el contenido de la ventana cerca del sistema
        this.contenido = new JPanel();
        contenido.setLayout(new GridBagLayout());
        
        // Contendra solo el contenido acerca del grupo
        this.jpGrupo = new JPanel();
        
        this.layoutConstraints = new GridBagConstraints();
        layoutConstraints.weightx = 1.0;
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        
        // Añadimos el boton ver documentacion al contenido
        JPanel jpButton = new JPanel();
        jpButton.setLayout(new BorderLayout());
        jpButton.setBorder(new EmptyBorder(15,5,5,30));
        JButton verDoc = new JButton("Ver Documentación");
        verDoc.addActionListener((ActionEvent) -> {
            actionButtonVerDoc();
        });
        jpButton.add(verDoc, BorderLayout.EAST);
        layoutConstraints.gridy = 0;
        contenido.add(jpButton, layoutConstraints);
        
        // Creamos un jpanel que contendra "Lenguajes de programacion II"
        // y lo añadimos a contenido
        JPanel jpFacultad = new JPanel();
        jpFacultad.setLayout(new BorderLayout());
        jpFacultad.setBorder(new EmptyBorder(50,30,5,5));
        JLabel facultad = new JLabel(
        "<html><div style=\"color: blue; font-size:12px; \"><p style=\"margin-bottom:8px; \">Universidad</p><p>Lenguajes de Programación II</p></div></html>"
        );
        jpFacultad.add(facultad, BorderLayout.WEST);
        layoutConstraints.gridy=1;
        contenido.add(jpFacultad, layoutConstraints);
        
        // Creamos un jpanel que contendra "Examen final - 2023"
        // y lo añadimos a contenido
        JPanel jpExamen = new JPanel();
        jpExamen.setLayout(new BorderLayout());
        jpExamen.setBorder(new EmptyBorder(40,70,5,5));
        JLabel examen = new JLabel(
        "<html><div style=\"color: red; font-size:30px; \">Examen Final - 2023</div></html>"
        );
        jpExamen.add(examen, BorderLayout.WEST);
        layoutConstraints.gridy=2;
        contenido.add(jpExamen, layoutConstraints);
        
        // Añadimos la lista de integrantes al contenido pero sin cargar
        // los integrantes
        cargarContenidoGrupo(false);
        
        add(contenido,BorderLayout.NORTH);
        setVisible(true);
    }
    
    /**
     * Carga el contenido acerca del grupo
     * @param mostrarIntegrantes booleano que define si mostrar los integrantes del grupo
     */
    public final void cargarContenidoGrupo(Boolean mostrarIntegrantes){
        jpGrupo.setLayout(new BorderLayout());
        jpGrupo.setBorder(new EmptyBorder(0,70,5,5));
        JLabel grupo = new JLabel("<html><p>Grupo N° 14</p><p>Lista de integrantes</p><br/></html>");    
        jpGrupo.add(grupo, BorderLayout.NORTH);
        if(mostrarIntegrantes){
            JLabel integrantes = new JLabel("- Grupo 14");
            jpGrupo.add(integrantes, BorderLayout.CENTER);
        }
        layoutConstraints.gridy=3;
        contenido.add(jpGrupo, layoutConstraints);
    }
    
    /**
     * Evento que debe realizarse al presionar click el boton "Ver documentacion"
     */
    public void actionButtonVerDoc(){
        GUIAcercaDelSistema parent = this;

        /**
         * Hilo que se encarga de la actividad de mostrar la
         * documentacion
         */
        Thread hiloVerDocumentacion = new Thread(new Runnable() {
            @Override
            public void run() {
                File javadocDir = new File("javadoc");
                try {
                    Desktop.getDesktop().browse(new File(javadocDir, "index.html").toURI());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "No se pudo abrir el javadoc",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        /**
         * Hilo que se encarga de la actividad de mostrar los
         * integrantes y actualizar la ventana
         */
        Thread hiloCargarGrupo = new Thread(new Runnable() {
            @Override
            public void run() {
                jpGrupo.removeAll();
                cargarContenidoGrupo(true);
                contenido.revalidate();
                contenido.repaint();
            }
        });        
        
        hiloVerDocumentacion.start();
        hiloCargarGrupo.start();
    }
    
}
