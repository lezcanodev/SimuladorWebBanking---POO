package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que renderiza una ventana basica que solo
 * constiene el titulo y el contenido
 * @author Grupo 14
 */
public abstract class GUIBasic extends JFrame{
    /**
     * Reglas de diseño del JPanel
     */
    private final GridBagConstraints gridBagLayoutConstraint; 
    
    /**
     * Constructor de GUIBasic
     * @param titulo titulo de la ventana
     */
    public GUIBasic(String titulo){
        super();
        setLayout(new GridBagLayout());
        setTitle(titulo);
        
        this.gridBagLayoutConstraint = new GridBagConstraints();
        
        titulo = "<html><h1>"+titulo+"</h1></html>";
        JLabel labelTitulo = new JLabel(titulo);
        this.gridBagLayoutConstraint.gridx = 0;
        this.gridBagLayoutConstraint.gridy = 0;
        add(labelTitulo, this.gridBagLayoutConstraint);
    }
    
    /**
     * Funcion que define el contenido de la ventana
     * @return JPanel que representa el contenido
     */
    public abstract JPanel contenido();
    
    /**
     * Posiciona el contenido y inicializar la ventana
     */
    protected final void iniciar(){
        gridBagLayoutConstraint.gridx = 0;
        gridBagLayoutConstraint.gridy = 1;
        add(contenido(), gridBagLayoutConstraint);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
