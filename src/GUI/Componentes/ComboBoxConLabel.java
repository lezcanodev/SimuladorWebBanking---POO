package GUI.Componentes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que genera un ComboBox con un JLabel
 * @author Grupo 14
 */
public class ComboBoxConLabel extends JPanel{
    private JComboBox comboBox;
    private GridBagConstraints gridBagConstraint;
    
    /**
     * Clase interna que representa un item del comboBox
     */
    public class JComboBoxItem{
        private String key;
        private Object value;
        
        /**
         * Constructor de la clase interna JComboBoxItem
         * @param key valor que se vera en el comboBox
         * @param value valor interno que tendra el item
         */
        public JComboBoxItem(String key, Object value){
            this.key = key;
            this.value = value;
        }
        
        /**
         * Devuelve el valor del item
         * @return valor del item
         */
        public Object getValue(){
            return value;
        }
        
        /**
         * Representacion de JComboBoxItem
         * @return String representando JComboBoxItem
         */
        @Override
        public String toString(){
            return key;
        }
        
    }
    
    
    /**
     * Constructor de la clase ComboBoxConLabel
     * @param label contenido de JLabel
     */
    public ComboBoxConLabel(String label){
        setLayout(new GridBagLayout());
        this.gridBagConstraint = new GridBagConstraints();
        this.comboBox = new JComboBox();
        

        gridBagConstraint.anchor = GridBagConstraints.LINE_START;
        
        // JLabel
        JLabel jLabel = new JLabel(label);
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 0;
        add(jLabel, gridBagConstraint);
        
        // ComboBox
        gridBagConstraint.gridx = 0;
        gridBagConstraint.gridy = 1;
        add(this.comboBox, gridBagConstraint);
        
    } // Fin constructor
    
    /**
     * Añade un unevo item al comboBox
     * @param key key del item nuevo
     * @param value  valor del item nuevo
     */
    public void addItem(String key, Object value){
        comboBox.addItem(new JComboBoxItem(key, value));
    }
    
    /**
     * Retorna el item seleccionado en el combobox
     * @return item selecionado
     */
    public JComboBoxItem getSelectedItem(){
        return (JComboBoxItem)comboBox.getSelectedItem();
    }
    
}



