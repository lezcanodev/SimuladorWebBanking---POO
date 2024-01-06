package GUI.Componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase que genera un JTexfield con un JLabel
 * @author Grupo 14
 */
public class TextFieldConLabel extends JPanel{
    private GridBagConstraints gridBagConstraint;
    private JTextField jTextField;
    
    /**
     * Constructor de la clase TextFieldConLabel
     * @param label contenido de JLabel
     */
    public TextFieldConLabel(String label){
        setLayout(new GridBagLayout());
        this.gridBagConstraint = new GridBagConstraints();
        
        gridBagConstraint.anchor = GridBagConstraints.LINE_START;
        
        // JLabel
        JLabel jLabel = new JLabel(label);
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 0;
        add(jLabel, gridBagConstraint);
        
        // JTextField
        this.jTextField = new JTextField(10);
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 1;
        add(jTextField, gridBagConstraint);
    } // Fin constructor
    
    /**
     * Devuelve el valor del JTextField
     * @return valor
     */
    public String getValueJTextField(){
        return jTextField.getText();
    }
    
    /**
     * Estable un valor para el JTextField
     * @param value nuevo valor
     */
    public void setValueJTextField(String value){
        jTextField.setText(value);
    }
    
}
