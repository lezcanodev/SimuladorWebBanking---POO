package Model;

/**
 * Clase que se encarga de representar una cuenta
 * @author Grupo 14
 */
public class Cuenta {
    private Integer idCuenta;
    private Integer nroCuenta;
    private String tipoCuenta;
    private Double saldo;

    /**
     * Constructor de la clase cuenta
     * @param idCuenta id de la cuenta
     * @param nroCuenta numero de la cuenta
     * @param tipoCuenta tipo de cuenta
     * @param saldo saldo en la cuenta
     */
    public Cuenta(
        Integer idCuenta, Integer nroCuenta, String tipoCuenta,
        Double saldo
    ){
        
        assert idCuenta != null && idCuenta >= 0 : "El id de cuenta debe ser mayor o igual a cero" ;
        assert tipoCuenta != null : "Tipo de cuenta no debe ser null";
        assert nroCuenta != null && nroCuenta >= 1000 : "Nro. de cuenta debe tener al menos 4 digitos" ;
        assert saldo != null && saldo >= 0.0 : "Saldo debe ser mayor a cero";
        
        this.idCuenta = idCuenta;
        this.nroCuenta = nroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    } // Fin del constructor
    
    /**
     * Disminuye el saldo disponible de la cuenta
     * @param monto monto a disminuir
     */
    public void disminuirSaldo(Double monto){
        this.saldo -= monto;
    }
    
    /**
     * Aumenta el saldo disponible de la cuenta
     * @param monto monto a aumentar
     */
    public void aumentarSaldo(Double monto){
        this.saldo += monto;
    }
    
    /**
     * Devuelve el saldo de la cuenta
     * @return saldo
     */
    public Double getSaldo(){
        return saldo;
    }
    
    
    /**
     * Devuelve el numero de cuenta
     * @return nroCuenta
     */
    public Integer getNroCuenta(){
        return nroCuenta;
    }
    
    /**
     * Devuelve el tipo de cuenta
     * @return tipo cuenta
     */
    public String getTipo(){
        return tipoCuenta;
    }
    
    /**
     * Representacion en String de la cuenta
     * @return String que representa la instancia
     */
    @Override
    public String toString(){
        return "CUENTA NRO.: "+nroCuenta+"\n"+
               "TIPO: "+tipoCuenta;
    }
    
}
