package Exceptions;

/**
 * Clase que representa la excepcion de que un cliente
 * no existe
 * @author Grupo 14
 */
public class ClienteNoExisteException extends Exception {
    
    /**
     * Constructor de la clase ClienteNoExisteException
     */
    public ClienteNoExisteException(){
        super("El cliente no existe");
    } // Fin cosntructor
}
