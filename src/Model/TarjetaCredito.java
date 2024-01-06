package Model;

import java.time.LocalDate;

/**
 * Clase que se encarga de representar una tarjeta de credito
 * @author Grupo 14
 */
public class TarjetaCredito {
    private Integer idTajeta;
    private String nroTarjeta;
    private String codigoTarjeta;
    private LocalDate fechaVencimiento;
    private Double saldoDisponible;
    private Double deudaTotal;
    private Double comision;
    
    /**
     * Constructor de la clase tarjeta credito
     * @param idTajeta id de la tarjeta de credito
     * @param nroTarjeta numero de la tarjeta de credito
     * @param codigoTarjeta codigo de seguridad de la tarjeta
     * @param fechaVencimiento fecha de vencimiento de la tarjeta
     * @param saldoDisponible saldo disponible de la tarjeta
     * @param deudaTotal  deuda a pagar por la tarjeta
     */
    public TarjetaCredito(
        Integer idTajeta, String nroTarjeta, String codigoTarjeta,
        LocalDate fechaVencimiento, Double saldoDisponible, Double deudaTotal
    ) {
        
        assert idTajeta != null && idTajeta >= 0 : "El id de la tarjeta debe ser mayor o igual a cero" ;
        assert nroTarjeta != null : "Numero de tarjeta no debe ser null";
        assert codigoTarjeta != null : "Codigo de tarjeta no debe ser null";
        assert fechaVencimiento != null : "Fecha de vencimiento de la tarjeta no debe ser null" ;
        assert saldoDisponible != null && saldoDisponible >= 0.0 : "El saldo disponible de la tarjeta debe ser mayor o igual a cero" ;
        assert deudaTotal != null && deudaTotal >= 0.0 : "La deuda total debe ser mayor o igual a cero" ;
        
        
        this.idTajeta = idTajeta;
        this.nroTarjeta = nroTarjeta;
        this.codigoTarjeta = codigoTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.saldoDisponible = saldoDisponible;
        this.deudaTotal = deudaTotal;
    } // Fin del constructor
    
    /**
     * Gasta un monto de la tarjeta de credito por lo tanto
     * el saldo disponible disminuye y la deuda aumenta
     * @param monto monto a gastar
     */
    public void gastarMonto(Double monto){
        this.saldoDisponible -= monto;
        this.deudaTotal += monto;
    }
    
    /**
     * Paga la deuda de la tarjeta de forma parcial o en su
     * totalidad
     * @param monto monto a gastar
     */
    public void pagarDeuda(Double monto){
        saldoDisponible += monto;
        comision = 0.0;
        
        if(monto < this.deudaTotal){
            comision = deudaTotal*0.05;
        }
        
        deudaTotal = comision + deudaTotal - monto; 
    }
    
    /**
     * Calcula el pago minimo y lo devuelve
     * @return Double que representa el pago minimo
     */
    public Double calcularPagoMinimo(){
        return deudaTotal*0.3;
    }
    
    /**
     * Devuelve la comision del pago de tarjeta de credito si
     * es que lo hay
     * @return Double que representa la comision
     */
    public Double getComision(){
        return comision == null ? 0 : comision;
    }
    
    /**
     * Devuelve el saldo disponible de la tarjeta
     * @return Double que representa el saldo disponible
     */
    public Double getSaldoDisponible(){
        return saldoDisponible;
    }
    
    /**
     * Devuelve el numero de la tarjeta de credito
     * @return String que representa numero de la tarjeta de credito
     */
    public String getNroTarjeta(){
        return nroTarjeta;
    }
    
    /**
     * Devuelve la fecha de vencimiento de la tarjeta de credito
     * @return LocalDate que representa la fecha de vencimiento
     */
    public LocalDate getFechaVencimiento(){
        return fechaVencimiento;
    }
    
    /**
     * Devuelve la deuda total de la tarjeta de credito
     * @return Double que representa la deuda total
     */
    public Double getDeudaTotal(){
        return deudaTotal;
    }
    
    /**
     * Representacion en String de la clase TarjetaCredito
     * @return String que representa la instancia de TarjetaCredito
     */
    @Override
    public String toString(){
        return "NRO. DE LA TARJETA: "+nroTarjeta+"\n"+
               "FECHA DE VENCIMIENTO: "+fechaVencimiento+"\n"+
               "DEUDA TOTAL: "+getDeudaTotal()+"\n"+
               "PAGO MINIMO: "+calcularPagoMinimo()+"\n"+
               "COMISION: "+getComision();
    }
}
