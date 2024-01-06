package Exceptions;

/**
 * Clase que representa la excepcion de que una cuenta
 * no tiene cierto monto
 * @author Grupo 14
 */
public class CuentaMontoInsuficienteException extends Exception{
        
    /**
     * Constructor de la clase CuentaMontoInsuficienteException
     */
    public CuentaMontoInsuficienteException(){
        super("La cuenta no tiene el monto suficiente");
    } // Fin cosntructor
    
}
