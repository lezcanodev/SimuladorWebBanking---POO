package GUI;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Clase que crea una estructura basica para
 * un JPanel donde contiene un titulo y el contenido
 * @author Grupo 14
 */
public abstract class GUIJPanelBasic extends JPanel{
    /**
     * Contenido del panel
     */
    private final JPanel content;
    
    /**
     * Constructor de GUIJPanelBasic
     * @param titulo titulo de la ventana
     */
    public GUIJPanelBasic(String titulo){
        super();
        setLayout(new BorderLayout());
        this.content = new JPanel();
        
        titulo = "<html><center><h1>"+titulo+"</h1></center></html>";
        JLabel labelTitulo = new JLabel(titulo);
        add(labelTitulo, BorderLayout.NORTH);
        
    } // Fin constructor
    
    /**
     * Funcion que define el contenido de la ventana
     * @return JPanel que representa el contenido
     */
    public abstract JPanel contenido();
    
    /**
     * Posiciona y añade el contenido
     */
    protected final void cargarContenido(){
        JPanel c = contenido();
        content.setBorder(new EmptyBorder(10,30,10,10));
        content.add(c);
        add(content , BorderLayout.LINE_START); 
    }
    
    /**
     * Recibe el nuevo contenido a cargar
     * @param nuevoContenido  nuevo contenido
     */
    protected void actualizarContenido(JPanel nuevoContenido){
        content.removeAll();
        content.add(nuevoContenido);
        content.setBorder(new EmptyBorder(10,30,10,10));
        add(content , BorderLayout.LINE_START); 
        content.revalidate();
        content.repaint();
    }
}
