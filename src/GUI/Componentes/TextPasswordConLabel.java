package GUI.Componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * Clase que genera un JPasswordField con un JLabel
 * @author Grupo 14
 */
public class TextPasswordConLabel extends JPanel{
    private GridBagConstraints gridBagConstraint;
    private JPasswordField jTextField;
    
    /**
     * Constructor de la clase TextPasswordConLabel
     * @param label contenido de JLabel
     */
    public TextPasswordConLabel(String label){
        setLayout(new GridBagLayout());
        this.gridBagConstraint = new GridBagConstraints();
        
        gridBagConstraint.anchor = GridBagConstraints.LINE_START;
        
        // JLabel
        JLabel jLabel = new JLabel(label);
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 0;
        add(jLabel, gridBagConstraint);
        
        // JPasswordField
        this.jTextField = new JPasswordField(10);
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 1;
        add(jTextField, gridBagConstraint);
    } // Fin constructor
    
    /**
     * Devuelve el valor del JPasswordField
     * @return valor
     */
    public String getValueJTextField(){
        String value = "";
        for (char c : jTextField.getPassword()) {
            value += c + "";
        }
        return value;
    }
    
    /**
     * Estable un valor para el JPasswordField
     * @param value nuevo valor
     */
    public void setValueJTextField(String value){
        jTextField.setText(value);
    }
    
}
