package GUI.Componentes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase encargada de guardar un archivo en la ubicacion que el
 * usuario eliga
 * @author Grupo 14
 */
public class FileGuardarComo {
    private JFileChooser fileChooser; //componente para que el usuario eliga una direccion para el archivo
    private String defaultFileName;
    private String contenido;

    /**
     * Constructor de la clase FileGuardarComo
     * @param defaultFileName nombre por defecto por el que se guarda el archivo
     * @param contenido contenido de lo que se guardara
     */
    public FileGuardarComo( String defaultFileName, String contenido) {
        this.fileChooser = new JFileChooser();
        this.defaultFileName = defaultFileName;
        this.contenido = contenido;
        
        // Establece el nombre por defecto con el cual se guardara el archivo
        fileChooser.setSelectedFile(new File(defaultFileName));
        
        int guardar = 0;
        int option = 0;
        File file;
        // EL bucle se ejecutara mientras el  archivo exista
        // y el usuario no quiera reemplazarlo o termina si
        // el usuario cancelaa el guardado
        do{
            // Abre la venta de directorios para que el usuario eliga uno
            guardar = fileChooser.showSaveDialog(null);

            // Obtiene un archivo con la direccion que eligio el usuario
            file = fileChooser.getSelectedFile();
            
            // Preguntamos si quiere reemplazar o cancelar
            if(guardar == JFileChooser.APPROVE_OPTION && file.exists()){
                option = JOptionPane.showConfirmDialog(
                            null, 
                            "El archivo ya existe, quieres reemplazarlo?",
                            "Error",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.ERROR_MESSAGE);
            }else break;
            
        }while(option == JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION);
        
        if(option != JOptionPane.CANCEL_OPTION  && guardar == JFileChooser.APPROVE_OPTION){            
            try {
                    FileWriter fw = new FileWriter(file);
                    fw.write(contenido);
                    fw.close();
                    
                    JOptionPane.showMessageDialog(
                            null,
                            "Se ha guardado el archivo",
                            "Archivo",
                            JOptionPane.INFORMATION_MESSAGE);
            }catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(
                            null,
                            "Se ha cancelado el guardado del archivo",
                            "Archivo",
                            JOptionPane.INFORMATION_MESSAGE); 
        }
        
        
    } // Fin constructor
    
    
    
}
