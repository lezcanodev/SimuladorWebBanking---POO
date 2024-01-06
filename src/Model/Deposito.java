
package Model;

import java.time.LocalDate;

/**
 * Clase que representa un deposito
 * @author Grupo 14
 */
public class Deposito {
    private Integer idDeposito;
    private Integer idCajero;
    private String ciDepositante;
    private String nombreDepositante;
    private String apellidoDepositante;
    private Double monto;
    private LocalDate fecha;
    private Cuenta depositario;
    
    // para simular un id auto incremental
    private static Integer ID_DEPOSITO = 1;

    /**
     * Constructor de la clase Deposito
     * @param idCajero id del cajero donde se realizo el deposito
     * @param ciDepositante ci del depositante
     * @param nombreDepositante nombre del depositante
     * @param apellidoDepositante apellido del depositante
     * @param monto monto a depositar
     * @param depositario cuenta del depositario
     */
    public Deposito(Integer idCajero, String ciDepositante, String nombreDepositante, String apellidoDepositante, Double monto, Cuenta depositario) {
        this.idDeposito = ID_DEPOSITO++;
        this.idCajero = idCajero;
        this.ciDepositante = ciDepositante;
        this.nombreDepositante = nombreDepositante;
        this.apellidoDepositante = apellidoDepositante;
        this.monto = monto;
        this.depositario = depositario;
        this.fecha = LocalDate.now();
    } // Fin del constructor
    
    
    /**
     * Realiza el deposito del monto a la cuenta depositario
     */
    public void realizarDeposito(){
        depositario.aumentarSaldo(monto);
    }
    
    /**
     * Devuelve los detalles del deposito
     * @return detalle del deposito
     */
    public String getDetalle(){
        return 
                "ID DEPOSITO: "+idDeposito+"\n"+
                "ID CAJERO: "+idCajero+"\n"+
                "FECHA: "+fecha+"\n"+
                "DEPOSITARIO: "+"\n"+
                "       MONTO DEPOSITADO: "+monto+"\n"+
                "       NRO. DEPOSITARIO: "+depositario.getNroCuenta()+"\n"+
                "DEPOSITANTE: \n"+
                "       NOMBRE: "+nombreDepositante+"\n"+
                "       APELLIDO: "+apellidoDepositante+"\n"+
                "       CI: "+ciDepositante;
    }
    
}
