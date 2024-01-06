package Exceptions;

/**
 * Clase que representa la excepcion de que no ingreso el monto suficiente
 * para pagar el servicio
 * @author Grupo 14
 */
public class PagoServicioMontoInsuficienteException extends Exception{
        
    /**
     * Constructor de la clase PagoServicioMontoInsuficienteException
     */
    public PagoServicioMontoInsuficienteException(){
        super("Monto insuficiente para pagar el servicio");

    } // Fin cosntructor

       
    
}
