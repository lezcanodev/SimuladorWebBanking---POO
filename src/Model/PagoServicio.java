package Model;

import java.time.LocalDate;

/**
 * Clase abstracta que representa lo que debe tener un Pago de servicio
 * @author Grupo 14
 */
public abstract class PagoServicio {
    /**
     * Id del pago del servicio
     */
    protected Integer idPago;
    /**
     * Servicio que esta relacionado al pago
     */
    protected Servicio servicio;
    /**
     * Monto a pagar por el servicio
     */
    protected Double monto;
    /**
     * Fecha en la que se realizo el pago
     */
    protected LocalDate fecha;
    /**
     * Fecha de vencimiento del pago
     */
    protected LocalDate fechaVencimiento;
    /**
     * Estado del pago
     */
    protected Integer estado;
    /**
     * Constante que representa el estado cuando el pago
     * fue hecho
     */
    public final static Integer PAGADO = 1;
    /**
     * Constante que representa el estado cuando el pago
     * todavia no fue hecho
     */
    public final static Integer PENDIENTE = 0;

    /**
     * Constructor de la clase abstractaPagoServicio
     * @param idPago id del pago
     * @param servicio servicio del cual se realizara el pago
     * @param monto monto a pagar
     * @param fechaVencimiento fecha de vencimiento del pago
     * @param estado estado del pago (PENDIENTE, PAGADO)
     */
    public PagoServicio(
        Integer idPago, Servicio servicio, 
        Double monto, LocalDate fechaVencimiento, Integer estado
    ){
        
        assert idPago != null && idPago >= 0 : "El id de pago de servicio debe ser mayor o igual a cero" ;
        assert servicio != null : "El servicio no puede ser null";
        assert monto != null && monto >= 0 : "El monto del servicio debe ser mayor o igual a cero";
        assert fechaVencimiento != null: "Fecha de vencimiento no puede ser null" ;
        assert estado == PENDIENTE || estado == PAGADO: "El estado del pago de servicio debe ser PAGADO o PENDIENTE" ;
        
        this.idPago = idPago;
        this.servicio = servicio;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    } // Fin constructor
    
    
    /**
     * Define el metodo realizar pago con cuenta que debe implementar todos los servicios
     * @param cuenta cuenta con la que se realizo el pago
     * @param pinTransaccion pin de transaccion del cliente
     * @param monto monto pagado
     * @return String que representa el comprobante del pago
     */
    public abstract String realizarPago(Cuenta cuenta, Integer pinTransaccion, Double monto);
    
    /**
     * Define el metodo realizar pago con tarjeta de credito que implementan
     * los servicios que soportan este tipo de pago
     * @param tarjeta tarjeta con la que se realizo el pago
     * @param pinTransaccion pin de transaccion del cliente
     * @param monto monto pagado
     * @return String que representa el comprobante del pago
     * @throws UnsupportedOperationException lanzado si el servicio no soporta este tipo de pago
     */
    public abstract String realizarPago(TarjetaCredito tarjeta, Integer pinTransaccion, Double monto) throws UnsupportedOperationException;

    /**
     * Se encarga de verificar si el identificar pasado por parametro
     * coincide con el identificador del cliente
     * @param indentificadorCliente
     * @return true si coincide false en caso contrario
     */
    public abstract Boolean equalsIdentificador(Object indentificadorCliente);
    
    /**
     * Devuelve el detalle del pago sin realizarlo
     * @return detalle del pago
     */
    public abstract String getDetalle();
        
    /**
     * Devuelve un string que contiene el nombre del identificador y los
     * valores
     * @return String representancion del identificador
     */
    public abstract String getDetalleIdentificador();
    
    /**
     * Devuelve el codigo del servicio
     * @return String que representa el codigo
     */
    public String getCodServicio(){
        return servicio.getCodServicio();
    }
    
    /**
     * Devuelve el estado del pago 
     * @return integer que representa el estado del pago
     */
    public Integer getEstado(){
        return estado;
    }
    
    /**
     * Devuelve el monto a pagar por el servicio 
     * @return double representa monto a pagar
     */
    public Double getMonto(){
        return monto;
    }
       
    /**
     * Devuelve nombre de la empresa o servicio
     * @return string representa nombre empresa o servicio
     */
    public String getEmpresa(){
        return servicio.getEmpresa();
    }
    
    /**
     * Retorna true si el servicio soporta ser pagado
     * con tarjeta de credito en caso contrario false
     * @return boolean que representa si soporta pagar con tarjeta de credito
     */
    public boolean soportaTarjetaCredito(){
        return true;
    }
}
