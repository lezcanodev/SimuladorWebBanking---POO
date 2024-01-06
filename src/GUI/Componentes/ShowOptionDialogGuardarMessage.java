package GUI.Componentes;

import javax.swing.JOptionPane;

/**
 * Clase encargada de mostrar un cuadro de dialogo
 * con la opciones "ok" y "guardar" por si el usuario
 * quiere guardar la informacion del mensaje del cuadro
 * de dialogo
 * @author Grupo 14
 */
public class ShowOptionDialogGuardarMessage {
    private String mensaje;
    private String titulo;
    
    private final int OPTION_GUARDAR = 1;
    
    /***
     * Constructor de la clase ShowOptionDialogGuardarMessage
     * @param mensaje mensaje a mostrar en el cuadro de dialogo
     * @param titutlo titulo del cuadro de dialogo
     * @param defaultFileName nombre por defecto que se utilizara al guardar
     */
    public ShowOptionDialogGuardarMessage(String mensaje, String titutlo, String defaultFileName) {
        this.mensaje = mensaje;
        
        Object[] opciones = {"Salir", "Guardar"};
        int optionSeleccionada = JOptionPane.showOptionDialog(
                null,
                mensaje,
                titutlo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]);
        
        
        if(optionSeleccionada == OPTION_GUARDAR){
            new FileGuardarComo(
                    defaultFileName,
                    mensaje
            );
        }
        
    } // Fin constructor
    
    
    
}
