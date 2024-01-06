package GUI.Componentes;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 * Clase que se encarga de renderizar un formulario, es decir agrega un componente
 * uno debajo del otro
 * @author Grupo 14
 */
public class Form extends JPanel{
    private int gridx;
    private int gridy;
    private GridBagConstraints constraints;
    
    /**
     * Constructor de la clase Form
     */
    public Form(){
        setLayout(new GridBagLayout());
        this.constraints = new GridBagConstraints();
        this.constraints.anchor = GridBagConstraints.LINE_START;
        this.constraints.insets = new Insets(4, 0, 4, 0);
        this.gridx = 0;
        this.gridy = 0;
    } // Fin constructor
    
    /**
     * Recibe un campo de entrada y lo añade al formulario
     * @param input campo de entrada
     */
    public void addForm(Component input){
        constraints.gridx = 0;
        constraints.gridy = gridy;
        add(input, constraints);
        gridx = 0;
        gridy++;
    }
}
