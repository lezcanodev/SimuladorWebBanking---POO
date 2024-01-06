package Model;

import java.time.LocalDate;

/**
 * Clase abstracta que representa una transferencia
 * @author Grupo 14
 */
public abstract class Transferencia {
    /**
     * Id de la transaferencia
     */
    protected Integer idTransferencia;
    /**
     * Cuenta origen de la transferencia
     */
    protected Cuenta cuentaOrigen;
    /**
     * Concepto de la transferencia
     */
    protected String concepto;
    /**
     * Monto a transferir
     */
    protected Double monto;
    /**
     * Fecha en la que se realizo la transferencia
     */
    protected LocalDate fecha;
    
    // para simular un id auto incremental
    private static Integer ID_TRANSFERENCIA = 1;
    
    /**
     * Constructor de la clase abstracta Transferencia
     * @param cuentaOrigen cuenta origen de la transferencia
     * @param concepto concepto de la transferencia
     * @param monto monto a transferir
     */
    public Transferencia(Cuenta cuentaOrigen, String concepto, Double monto) {
        this.idTransferencia = ID_TRANSFERENCIA++;
        this.cuentaOrigen = cuentaOrigen;
        this.concepto = concepto;
        this.monto = monto;
    } // Fin constructor
    
    /**
     * Define el metodo para realizar una transferencia
     * @param pinTransaccion pin de transaccion del cliente
     * @return String que representa el comprobante
     */
    public abstract String realizarTransferencia(Integer pinTransaccion);
    
    
    /**
     * Genera y devuelve detalles acerca de la transferencia
     * @return String que representa la transferencia
     */
    public abstract String getDetalle();

    /**
     * Devuelve la cuenta origen de la transferencia
     * @return cuenta origen
     */
    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * Devuelve el concepto de la transferencia
     * @return  concepto
     */
    public String getConcepto() {
        return concepto;
    }
    
    /**
     * Devuelve el monto transferido
     * @return monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Devuelve la fecha que se realizo la transferencia
     * @return fecha de la transferencia
     */
    public LocalDate getFecha() {
        return fecha;
    }
    
    
    
}
