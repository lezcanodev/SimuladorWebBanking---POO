package Model;

import java.time.LocalDate;

/**
 * Clase que representa el pago de una tarjeta de credito
 * @author Grupo 14
 */
public class PagoTarjetaCredito {
    private Integer idPago;
    private TarjetaCredito tarjeta;
    private Cuenta cuenta;
    private Double montoPagado;
    private LocalDate fecha;
    
    // Para simular un id auto incremental
    private static Integer ID_PAGO = 1;
    
    /**
     * Constructor de la clase PagoTarjetaCredito
     * @param tarjeta tarjeta de credito a pagar
     * @param cuenta cuenta que pagara la tarjeta
     * @param montoPagado  monto pagado por la tarjeta
     */
    public PagoTarjetaCredito(TarjetaCredito tarjeta, Cuenta cuenta, Double montoPagado) {
        this.idPago = ID_PAGO++;
        this.tarjeta = tarjeta;
        this.cuenta = cuenta;
        this.montoPagado = montoPagado;
    } // Fin constructor
    
    /**
     * Realiza el pago de una tarjeta de credito y devuelve el
     * comprobante
     * @param pinTransaccion pin de transaccion del cliente
     * @return String que representa el comprobante
     */
    public String realizarPago(Integer pinTransaccion){
        cuenta.disminuirSaldo(montoPagado);
        fecha = LocalDate.now();
               
        String comprobante = "";
        comprobante += "ID PAGO: "+idPago+"\n";
        comprobante += tarjeta+"\n";
        comprobante += "MONTO PAGADO: "+montoPagado+"\n";
        comprobante += "FECHA DE PAGO: "+fecha+"\n";
        comprobante += "METODO PAGO: cuenta \n";
        comprobante += cuenta+"\n"; 
        comprobante += "PIN TRANSACCION: "+pinTransaccion; 
        
        tarjeta.pagarDeuda(montoPagado);
        return comprobante;
    }
    
    
    /**
     * Devuelve el comprobante del pago de tarjeta de credito
     * @return String que representa el comprobante
     */
    public String getDetalle(){
        String detalle = "";
        detalle += "ID PAGO: "+idPago+"\n";
        detalle += tarjeta+"\n";
        detalle += "MONTO A PAGAR: "+montoPagado+"\n";
        detalle += "METODO PAGO: cuenta \n";
        detalle += cuenta;
        return detalle;
    }
    
}
