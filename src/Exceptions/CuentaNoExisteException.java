package Exceptions;

/**
 * Clase que representa la excepcion de que una cuenta
 * no existe
 * @author Grupo 14
 */
public class CuentaNoExisteException extends Exception {
    
    /**
     * Constructor de la clase CuentaNoExisteException
     */
    public CuentaNoExisteException(){
        super("La cuenta no existe");
    } // Fin cosntructor
    
}
