package Exceptions;

/**
 * Clase que representa la excepcion de que una tarjeta de credito
 * no tiene cierto monto
 * @author Grupo 14
 */
public class TarjetaCreditoMontoInsuficienteException extends Exception{
        
    /**
     * Constructor de la clase TarjetaCreditoMontoInsuficienteException
     */
    public TarjetaCreditoMontoInsuficienteException(){
        super("La tarjeta de credito no tiene el monto suficiente");
    } // Fin cosntructor
    
}
