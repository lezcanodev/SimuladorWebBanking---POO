package Exceptions;

/**
 * Clase que representa la excepcion de que el PIN de transaccion es incorrecto
 * @author Grupo 14
 */
public class PINTransaccionIncorrectoExcepcion extends Exception {
    
    /**
     * Constructor de la clase PINTransaccionIncorrectoExcepcion
     */
    public PINTransaccionIncorrectoExcepcion(){
        super("El PIN de transaccion es incorrecto");
    } // Fin cosntructor
    
}