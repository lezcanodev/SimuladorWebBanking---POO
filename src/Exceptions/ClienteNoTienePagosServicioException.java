package Exceptions;

/**
 * Clase que representa una excepcion de cuando el cliente de un servicio
 * no tiene ninguna deuda a pagar
 * @author Grupo 14
 */
public class ClienteNoTienePagosServicioException extends Exception{
        
    /**
     * Constructor de la clase ClienteNoTienePagosServicioException
     */
    public ClienteNoTienePagosServicioException(){
        super("No tienes nada para pagar en este servicio");
    } // Fin cosntructor
    
}
