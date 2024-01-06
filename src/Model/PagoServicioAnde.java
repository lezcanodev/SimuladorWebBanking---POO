package Model;

import java.time.LocalDate;

/**
 * Clase que representa el pago de servicio de la Ande
 * @author Grupo 14
 */
public class PagoServicioAnde extends PagoServicio{
    Integer nis;
    
    
    /**
     * Constructor  de la clase PagoServicioAnde
     * @param idPago id del pago
     * @param servicio serivicio el cual sera pagado en este caso Ande
     * @param monto monto a pagar
     * @param fechaVencimiento fecha de vencimiento del pago
     * @param estado estado del pago (PENDIENTE, PAGADO)
     * @param nis identificador del cliente (nis)
     */
    public PagoServicioAnde(
        Integer idPago, Servicio servicio,  Double monto, LocalDate fechaVencimiento, 
        Integer estado, Integer nis
    ){
        super(idPago, servicio, monto, fechaVencimiento, estado);
        
        assert nis != null: "El nis no debe ser null";
        assert servicio != null && servicio.getCodServicio().compareTo("123456") == 0: "El codigo de servicio no coincide con el pago de servicio";
        
        this.nis = nis;
    } // Fin constructor
    
    /**
     * Implemente el pago con cuenta
     * @param cuenta cuenta con la que se realizo el pago
     * @param pinTransaccion pin de transaccion del cliente
     * @param monto monto pagado
     * @return String que representa el comprobante del pago
     */
    @Override
    public String realizarPago(Cuenta cuenta, Integer pinTransaccion, Double monto) {
        this.estado = PagoServicio.PAGADO;
        this.fecha = LocalDate.now();
        cuenta.disminuirSaldo(monto);
        
        String comprobante = "";
        comprobante += getDetalle()+"\n";
        comprobante += "FECHA DE PAGO: "+fecha+"\n";
        comprobante += "MONTO: "+monto+"\n";
        comprobante += "Metodo PAGO: Cuenta\n";
        comprobante += cuenta+"\n";
        comprobante += "PIN TRANSACCION: "+pinTransaccion;
        
        return comprobante;
    }
    
     /**
     * Implemente el pago con tarjeta de credito
     * @param tarjeta tarjeta de credito con la que se realizo el pago
     * @param pinTransaccion pin de transaccion del cliente
     * @param monto monto pagado
     * @return String que representa el comprobante del pago
     */
    @Override
    public String realizarPago(TarjetaCredito tarjeta, Integer pinTransaccion, Double monto) throws UnsupportedOperationException {
        this.estado = PagoServicio.PAGADO;
        this.fecha = LocalDate.now();
        tarjeta.gastarMonto(monto);
        
        String comprobante = "";
        comprobante += getDetalle()+"\n";
        comprobante += "FECHA DE PAGO: "+fecha+"\n";
        comprobante += "MONTO: "+monto+"\n";
        comprobante += "Metodo PAGO: tarjeta de credito\n";
        comprobante += tarjeta+"\n";
        comprobante += "PIN TRANSACCION: "+pinTransaccion;
        
        return comprobante;
    }
    
    /**
     * Se encarga de verificar si el identificar pasado por parametro
     * coincide con el identificador del cliente
     * @param indentificadorCliente
     * @return true si coincide false en caso contrario
     */
    @Override
    public Boolean equalsIdentificador(Object indentificadorCliente) {
        try{
            return nis == Integer.parseInt((String)indentificadorCliente);
        }catch(Exception ex){
            return false;
        }
    }
    
    /**
     * Devuelve el detalle del pago sin realizarlo
     * @return detalle del pago
     */
    @Override
    public String getDetalle() {
        return 
            "ID PAGO: "+idPago+"\n"+
            "CODIGO SERVICIO: "+getCodServicio()+"\n"+
            "SERVICIO: "+getEmpresa()+"\n"+
            "NIS: "+nis+"\n"+    
            "FECHA VENCIMIENTO: "+fechaVencimiento;
    }
    
    /**
     * Devuelve un string que contiene el nombre del identificador y los
     * valores
     * @return String representancion del identificador
     */
    @Override
    public String getDetalleIdentificador(){
        return "nis: "+nis;
    }
    
}
