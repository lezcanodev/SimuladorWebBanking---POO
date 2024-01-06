package Model;

import java.time.LocalDate;

/**
 * Clase  que representa una transferencia donde el origen
 * y destino son las cuentas del cliente
 * @author Grupo 14
 */
public class TransferenciaACuentasPropias extends Transferencia{
    private Cuenta cuentaDestino;
    
    /**
     * Constructor de la clase TransferenciaACuentasPropias
     * @param cuentaOrigen cuenta origen de la transferencia
     * @param concepto concepto de la transferencia
     * @param monto monto a transferir
     * @param cuentaDestino cuenta destino la cual es una cuenta del cliente
     */
    public TransferenciaACuentasPropias(Cuenta cuentaOrigen, String concepto, Double monto, Cuenta cuentaDestino) {
        super(cuentaOrigen, concepto, monto);
        this.cuentaDestino = cuentaDestino;
    } // Fin de constructor

    /**
     * Implementa el metodo para realizar una transferencia a cuentas propias
     * @param pinTransaccion pin de transaccion del cliente
     * @return String que representa el comprobante
     */
    @Override
    public String realizarTransferencia(Integer pinTransaccion) {
        this.fecha = LocalDate.now();
        cuentaOrigen.disminuirSaldo(monto);
        cuentaDestino.aumentarSaldo(monto);
        
        String comprobante = "";
        comprobante =   "ID PAGO: "+idTransferencia+"\n"+
                        "CONCEPTO: "+concepto+"\n"+
                        "MONTO TRANSFERIDO: "+monto+"\n"+ 
                        "FECHA: "+fecha+"\n"+ 
                        "CUENTA ORIGEN"+"\n"+
                        cuentaOrigen+"\n"+
                        "CUENTA DESTINO"+"\n"+
                        cuentaDestino+"\n"+
                        "PIN DE TRANSACCION: "+pinTransaccion;
        return comprobante;
    }

    /**
     * Genera y devuelve detalles acerca de la transferencia a cuentas propias
     * @return String que representa la transferencia
     */
    @Override
    public String getDetalle() {
        return "ID PAGO: "+idTransferencia+"\n"+
               "CONCEPTO: "+concepto+"\n"+
               "MONTO A TRANSFERIR: "+monto+"\n"+ 
               "CUENTA ORIGEN"+"\n"+
               cuentaOrigen+"\n"+
               "CUENTA DESTINO"+"\n"+
               cuentaDestino;
    }
    
}
