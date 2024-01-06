package simuladorwebbanking;

import Exceptions.CuentaNoExisteException;
import Helper.Validador;
import Model.Cliente;
import Model.Cuenta;
import Model.Deposito;

/**
 * Clase encargada de validar los datos de un deposito
 * @author Grupo 14
 */
public class CajeroDepositoSimulador {
    private Integer idCajeroSimulador;
    private SimuladorWebBanking simulador;

    /**
     * Constructor de la clase CajeroDepositoSimulador
     * @param idCajeroSimulador id del cajero
     * @param simulador  simulador de la aplicacion
     */
    public CajeroDepositoSimulador(Integer idCajeroSimulador, SimuladorWebBanking simulador) {
       
        assert idCajeroSimulador != null && idCajeroSimulador >= 0: "El id del cajero debe ser >= 0";
        assert simulador != null: "El simulador no debe ser null";
        
        this.idCajeroSimulador = idCajeroSimulador;
        this.simulador = simulador;
    } // Fin constructor

    
    /**
     * Valida que los datos de un deposito sea valido y luego retorna una instancia de deposito
     * @param nroCuenta numero de cuenta a depositar
     * @param rucOci ruc o ci del depositario
     * @param monto monto a depositar
     * @param ciDepositante ci del depositante
     * @param nombre nombre del depositante
     * @param apellido apellido del depositante
     * @return deposito
     * @throws IllegalArgumentException Es lanzado si un argumento es invalido
     * @throws CuentaNoExisteException Es lanzado si la cuenta no existe
     */
    public Deposito validarDatosDeposito(String nroCuenta, String rucOci, String monto, String ciDepositante, 
            String nombre, String apellido)
    throws IllegalArgumentException, CuentaNoExisteException{
        
        if(!Validador.esInteger(nroCuenta)){
            throw new IllegalArgumentException("Numero de cuenta invalido");
        }
        
        if(Validador.estaVacio(rucOci)){
            throw new IllegalArgumentException("RUC o CI del depositario invalido");
        }
        
        if(Validador.estaVacio(ciDepositante)){
            throw new IllegalArgumentException("Nombre del depositante es invalido");
        }
        
        if(Validador.estaVacio(apellido)){
            throw new IllegalArgumentException("Apellido del depositante es invalido");
        }
        
        if(Validador.estaVacio(nombre)){
            throw new IllegalArgumentException("CI del depositante es invalido");
        }
        
        if(!Validador.esDouble(monto)){
            throw new IllegalArgumentException("El monto es invalido");
        }
        
        Double m = Double.parseDouble(monto);
        
        if(m <= 0){
            throw new IllegalArgumentException("El monto es invalido");
        }
        
        
        Cuenta depositario = null;
        
        for (Cliente cliente : simulador.getClientes()) {
            for (Cuenta cuenta : cliente.getMisCuentas()) {
                if(cuenta.getNroCuenta().compareTo(Integer.parseInt(nroCuenta) ) == 0){
                    depositario = cuenta;
                }
            }
        }
        
        if(depositario == null){
            throw new CuentaNoExisteException();
        }
        
        Deposito deposito = new Deposito(
                idCajeroSimulador, 
                ciDepositante,
                nombre, 
                apellido,
                m,
                depositario
        );
        
        return deposito;
    }
    
   
    
}
