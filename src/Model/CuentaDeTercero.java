package Model;

/**
 * Clase que se encarga de representar una cuenta de tercero
 * @author Grupo 14
 */
public class CuentaDeTercero {
    private Integer idCuentaTercero;
    private Integer nroCuenta;
    private String nombre;
    private String apellido;
    private String ruc;
    private Cuenta cuentaAsociada;
    
    /**
     * Constructor de la clase cuenta de tercero
     * @param idCuentaTercero id de la cuenta
     * @param nroCuenta numero de cuenta de la cuenta de tercero
     * @param nombre nombre de la cuenta de tercero
     * @param apellido apellido de la cuenta de tercero
     * @param ruc  ruc de la cuenta de tercero
     */
    public CuentaDeTercero(
        Integer idCuentaTercero, Integer nroCuenta, String nombre, 
        String apellido, String ruc
    ){
        
        assert idCuentaTercero != null && idCuentaTercero >= 0 : "El id de cuenta de tercero debe ser mayor o igual a cero" ;
        assert nombre != null : "Nombre no debe ser null";
        assert apellido != null : "Apellido no debe ser null";
        assert ruc != null : "RUC no debe ser null";
        
        this.idCuentaTercero = idCuentaTercero;
        this.nroCuenta = nroCuenta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ruc = ruc;
    } // Fin del constructor
    
    
    /**
     * Devuelve el numero de cuenta de la cuenta registrada por
     * el cliente
     * @return Integer que representa el numero de cuenta
     */
    public Integer getNroCuenta(){
        return nroCuenta;
    }
    
    /**
     * Devuelve el ruc  la cuenta registrada por
     * el cliente
     * @return Strinc que representa el RUC
     */
    public String getRuc(){
        return ruc;
    }
    
    
    /**
     * Devuelve el nombre y apellido de la cuenta registrada por
     * el cliente
     * @return String que contiene el nombre y apellido
     */
    public String getNombreApellido(){
        return nombre+" "+apellido;
    }
        
    /**
     * Devuelve la cuenta asociada a la cuenta de tercero
     * @return cuenta asociada
     */
    public Cuenta getCuentaAsociada(){
        return cuentaAsociada;
    }
    
    /**
     * Establece la cuenta asociada a la cuenta de tercero
     * @param cuenta cuenta asociada
     */
    public void setCuentaAsociada(Cuenta cuenta){
        this.cuentaAsociada = cuenta;
    }
    
    /**
     * Representacion en String de la cuenta
     * @return String que representa la instancia
     */
    @Override
    public String toString(){
        return "CUENTA NRO.: "+getNroCuenta()+"\n"+
               "RUC: "+getRuc()+"\n"+
               "NOMBRE: "+getNombreApellido();
    }
    
}
