package Model;

import java.time.LocalDate;

/**
 * Clase que representa el pago de servicio de Essap
 * @author Grupo 14
 */
public class PagoServicioEssap extends PagoServicio{
    Integer issan;
    
    /**
     * Constructor  de la clase PagoServicioEssap
     * @param idPago id del pago
     * @param servicio serivicio el cual sera pagado en este caso Essap
     * @param monto monto a pagar
     * @param fechaVencimiento fecha de vencimiento del pago
     * @param estado estado del pago (PENDIENTE, PAGADO)
     * @param issan identificador del cliente (issan)
     */
    public PagoServicioEssap(
        Integer idPago,Servicio servicio, Double monto, LocalDate fechaVencimiento, 
        Integer estado, Integer issan
    ){
        super(idPago, servicio, monto, fechaVencimiento, estado);
        
        assert issan != null: "El issan no debe ser null";
        assert servicio != null && servicio.getCodServicio().compareTo("654321") == 0: "El codigo de servicio no coincide con el pago de servicio";
        
        
        this.issan = issan;
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
    

    @Override
    public String realizarPago(TarjetaCredito tarjeta, Integer pinTransaccion, Double monto) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Pago con tarjeta de credito no soportado");
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
            return issan == Integer.parseInt((String)indentificadorCliente);
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
            "ISSAN: "+issan+"\n"+    
            "FECHA VENCIMIENTO: "+fechaVencimiento;
    }
    
    /**
     * Retorna true si el servicio soporta ser pagado
     * con tarjeta de credito en caso contrario false
     * @return boolean que representa si soporta pagar con tarjeta de credito
     */
    @Override
    public boolean soportaTarjetaCredito(){
        return false;
    }
    
    /**
     * Devuelve un string que contiene el nombre del identificador y los
     * valores
     * @return String representancion del identificador
     */
    @Override
    public String getDetalleIdentificador(){
        return "issan: "+issan;
    }
    
}
