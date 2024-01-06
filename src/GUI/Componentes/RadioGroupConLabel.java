package GUI.Componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Clase que genera un RadioGroup con un JLabel
 * @author Grupo 14
 */
public class RadioGroupConLabel extends JPanel{
    private ButtonGroup buttonGroup;
    private GridBagConstraints gridBagConstraint;
    private JTextField jTextField;
    private Integer gridy;
    
    /**
     * Constructor de la clase RadioGroupConLabel
     * @param label contenido de JLabel
     */
    public RadioGroupConLabel(String label){
        setLayout(new GridBagLayout());
        this.gridBagConstraint = new GridBagConstraints();
        this.buttonGroup = new ButtonGroup();
        this.gridy = 0;
        
        gridBagConstraint.anchor = GridBagConstraints.LINE_START;
        
        // JLabel
        JLabel jLabel = new JLabel(label);
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 0;
        add(jLabel, gridBagConstraint);
        
    } // Fin constructor
    
    /**
     * Agrega un JRadioButton
     * @param radio JRadioButton
     */
    public void addRadio(JRadioButton radio){
        gridBagConstraint.gridy = ++gridy;
        buttonGroup.add(radio);
        add(radio, gridBagConstraint);
    }
    
}
