package Exceptions;

/**
 * Clase que representa la excepcion de que ingreso un monto superior
 * para pagar el servicio
 * @author Grupo 14
 */
public class PagoServicioMontoSuperiorException extends Exception{
        
    /**
     * Constructor de la clase PagoServicioMontoSuperiorException
     */
    public PagoServicioMontoSuperiorException(){
        super("El Monto es supeior al solicitado");
    } // Fin cosntructor

}
