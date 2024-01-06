package Model;

import java.time.LocalDate;

/**
 * Clase  que representa una transferencia donde el destino
 * es una cuenta de tercero
 * @author Grupo 14
 */
public class TransferenciaATerceros extends Transferencia{
    private CuentaDeTercero cuentaDestino;
    
    /**
     * Constructor de la clase TransferenciaATerceros
     * @param cuentaOrigen cuenta origen de la transferencia
     * @param concepto concepto de la transferencia
     * @param monto monto a transferir
     * @param cuentaDestino cuenta destino la cual es una cuenta de tercero
     */
    public TransferenciaATerceros(Cuenta cuentaOrigen, String concepto, Double monto, CuentaDeTercero cuentaDestino) {
        super(cuentaOrigen, concepto, monto);
        this.cuentaDestino = cuentaDestino;
    } // Fin de constructor

    /**
     * Implementa el metodo para realizar una transferencia a un tercero,
     * busca la cuenta del tercero y aumenta su sadlo disponible y disminuye
     * el saldo de la cuenta de origen
     * @param pinTransaccion pin de transaccion del cliente
     * @return String que representa el comprobante
     */
    @Override
    public String realizarTransferencia(Integer pinTransaccion) {
        this.fecha = LocalDate.now();
        cuentaOrigen.disminuirSaldo(monto);
        cuentaDestino.getCuentaAsociada().aumentarSaldo(monto);
        
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
     * Genera y devuelve detalles acerca de la transferencia a un tercero
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
