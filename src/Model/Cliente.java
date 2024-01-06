
package Model;

/**
 * Clase que se encarga de representar un cliente
 * @author Grupo 14
 */
public class Cliente {
    private Integer idCliente;
    private String rucOci;
    private String nombre;
    private String apellido;
    private Integer pinCuenta;
    private Integer pinTransaccion;

    
    private Cuenta[] cuentas;
    private TarjetaCredito[] tarjetasCredito;
    private CuentaDeTercero[] cuentaDeTerceros;
    
    /**
     * Constructor de la clase Cliente
     * @param idCliente id del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param pinCuenta pin de cuenta del cliente
     * @param pinTransaccion  pin de transaccion del cliente
     * @param rucOci ruc o ci del cliente
     * @param cuentas cuentas asiganadas al cliente
     * @see Cuenta
     */
    public Cliente(
        Integer idCliente, String nombre, String apellido,
        Integer pinCuenta, Integer pinTransaccion,
        String rucOci, Cuenta[] cuentas
    ){
        assert idCliente != null && idCliente >= 0 : "El id de cliente debe ser mayor o igual a cero" ;
        assert nombre != null : "Nombre no debe ser null";
        assert apellido != null : "Apellido no debe ser null";
        assert pinCuenta != null && pinCuenta >= 1000 : "Pin de cuenta debe tener al menos 4 digitos" ;
        assert pinTransaccion != null && pinTransaccion >= 1000 : "Pin de transaccion debe tener al menos 4 digitos" ;
        assert rucOci != null : "Ruc o CI no debe ser null" ;
        assert cuentas != null && cuentas.length >= 1: "El cliente debe tener al menos 1 cuenta" ;
        
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pinCuenta = pinCuenta;
        this.pinTransaccion = pinTransaccion;
        this.rucOci = rucOci;
        this.cuentas = cuentas;
    } // Fin del constructor
    
    /**
     * Retorna el saldo total que tiene el cliente
     * en todas sus cuentas
     * @return saldo total
     */
    public Double saldoTotal(){
        Double saldo = 0d;
        for (Cuenta cuenta : cuentas) {
            saldo += cuenta.getSaldo();
        }
        return saldo;
    }
    
    
    /**
     * Estable las tarjetas de credito que tendra el cliente
     * @param tarjetas tarjetas de credito
     */
    public void setTarjetasCredito(TarjetaCredito[] tarjetas){
        this.tarjetasCredito = tarjetas;
    }
    
    /**
     * Establece las cuentas de tercero que tendra el cliente
     * @param cuentas  cuentas de terceros
     */
    public void setCuentaDeTerceros(CuentaDeTercero[] cuentas){
        this.cuentaDeTerceros = cuentas;
    }
    
    /**
     * Devuelve las cuentas del cliente
     * @return arreglo de cuentas
     */
    public Cuenta[] getMisCuentas(){
        return cuentas;
    }
    
    /**
     * Devuelve las tarjetas de credito del cliente
     * @return arreglos tarjetas de credito
     */
    public TarjetaCredito[] getMisTarjetasCredito(){
        return tarjetasCredito;
    }
    
    /**
     * Devuelve las cuentas de terceros registradas por el cliente
     * @return arreglos de cuentas registradas
     */
    public CuentaDeTercero[] getMisCuentasRegistradas(){
        return cuentaDeTerceros;
    }
    
    
    /**
     * Devuelve el ruc o ci del cliente
     * @return  cadena de texto que representa ruc o ci
     */
    public String getRucOci(){
        return rucOci;
    }
    
    /**
     * Devuelve el pin de cuenta del cliente
     * @return Integer que representa el pin de cuenta
     */
    public Integer getPinCuenta(){
        return pinCuenta;
    }
    
    /**
     * Devuelve el nombre y apellido del cliente
     * @return nombre + " " + apellido 
     */
    public String getNombreApellido(){
        return nombre+" "+apellido;
    }
    
    /**
     * Devuelve el pin de transaccion del cliente
     * @return Integer que representa el pin de transaccion
     */
    public Integer getPinTransaccion(){
        return pinTransaccion;
    }
    
}
