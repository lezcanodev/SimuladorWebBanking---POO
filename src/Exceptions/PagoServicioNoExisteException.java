package Exceptions;

/**
 * Clase que representa la excepcion de que un servicio
 * no existe
 * @author Grupo 14
 */
public class PagoServicioNoExisteException extends Exception{
        
    /**
     * Constructor de la clase ClienteNoExisteException
     */
    public PagoServicioNoExisteException(){
        super("El servicio no existe");
    } // Fin cosntructor
}
