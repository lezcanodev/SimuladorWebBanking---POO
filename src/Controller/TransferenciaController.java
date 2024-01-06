package Controller;

import App.Session;
import Exceptions.CuentaMontoInsuficienteException;
import Exceptions.PINTransaccionIncorrectoExcepcion;
import Helper.Validador;
import Model.Cliente;
import Model.Cuenta;
import Model.CuentaDeTercero;
import Model.Transferencia;
import Model.TransferenciaACuentasPropias;
import Model.TransferenciaATerceros;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase encargada de validar los datos ingresado en la ventana
 * GUITransferencia
 * @author Grupo 14
 */
public class TransferenciaController {
    private Session session;
    private SimuladorWebBanking simulador;
    
    /**
     * Constructor de la clase TransferenciaController
     * @param session session del cliente
     * @param simulador simulador de la aplicacion
     */
    public TransferenciaController(Session session, SimuladorWebBanking simulador) {
        this.session = session;
        this.simulador = simulador;
    } // Fin de constructor
    
    
    /**
     * Valida los datos para realizar una transferencia a cuentas propias y 
     * luego devuelve una instancia de la clase TransferenciaACuentasPropias
     * @param origen cuenta origen de la transferencia
     * @param destino cuenta destino del cliente de la transferencia
     * @param monto monto a transferir
     * @param concepto concepto de la transferencia
     * @return TransferenciaACuentasPropias
     * @throws CuentaMontoInsuficienteException lanzado si la cuenta no tiene el monto para transferir
     * @throws IllegalArgumentException lanzado si un argumento es invalido
     */
    public TransferenciaACuentasPropias validarDatosTransferencia(Cuenta origen, Cuenta destino, String monto, String concepto)
    throws CuentaMontoInsuficienteException, IllegalArgumentException{
        if(origen.getNroCuenta() == destino.getNroCuenta()){
            throw new IllegalArgumentException("La cuenta origen y destino deben ser diferentes");
        }
        
        if(Validador.estaVacio(concepto)){
            throw new IllegalArgumentException("El concepto es invalido");
        }
        
        if(!Validador.esDouble(monto) || Double.parseDouble(monto) <= 0){
            throw new IllegalArgumentException("El monto es invalido");
        }

        
        if(origen.getSaldo() < Double.parseDouble(monto)){
            throw new CuentaMontoInsuficienteException();
        }
        
        TransferenciaACuentasPropias ts = new TransferenciaACuentasPropias(
            origen, 
            concepto,
            Double.parseDouble(monto),
            destino
        );
        
        return ts;
    }
    
    
    /**
     * Valida los datos para realizar una transferencia a terceros y 
     * luego devuelve una instancia de la clase TransferenciaATerceros
     * @param origen cuenta origen de la transferencia
     * @param destino cuenta destino de un tercero de la transferencia
     * @param monto monto a transferir
     * @param concepto concepto de la transferencia
     * @return TransferenciaATerceros
     * @throws CuentaMontoInsuficienteException lanzado si la cuenta origen no tiene el monto para transferir
     * @throws IllegalArgumentException lanzado si un argumento es invalido
     */
    public TransferenciaATerceros validarDatosTransferencia(Cuenta origen, CuentaDeTercero destino, String monto, String concepto) 
    throws CuentaMontoInsuficienteException, IllegalArgumentException{        
        if(Validador.estaVacio(concepto)){
            throw new IllegalArgumentException("El concepto es invalido");
        }
        
        if(!Validador.esDouble(monto) || Double.parseDouble(monto) <= 0){
            throw new IllegalArgumentException("El monto es invalido");
        }
        
        if(origen.getSaldo() < Double.parseDouble(monto)){
            throw new CuentaMontoInsuficienteException();
        }
        
        Cuenta dest = null;
        
        for (Cliente cliente : simulador.getClientes()) {
            for (Cuenta cuenta : cliente.getMisCuentas()) {
                if(cuenta.getNroCuenta().compareTo(destino.getNroCuenta())==0   ){
                    dest = cuenta;
                    break;
                }
            }
            if(dest != null) break;
        }
        
        destino.setCuentaAsociada(dest);
        
        TransferenciaATerceros ts = new TransferenciaATerceros(
            origen, 
            concepto,
            Double.parseDouble(monto),
            destino
        );
        
        return ts;
    }
    
    /**
     * Valida el pin de transaccion y realiza la transferencia
     * @param transferencia transferencia a realizar
     * @param pinTransaccion pin de transaccion del cliente
     * @return String que representa el comprobante
     * @throws PINTransaccionIncorrectoExcepcion lanzado si el pin de transaccion es incorrecto
     * @throws IllegalArgumentException lanzado si el campo pin transaccion no es valido
     */
    public String validarTransferencia(Transferencia transferencia, String pinTransaccion) 
            throws PINTransaccionIncorrectoExcepcion, IllegalArgumentException{
        
        if(!Validador.esInteger(pinTransaccion)){
            throw new IllegalArgumentException("El PIN de transaccion es invalido");
        }
        
        if(Integer.parseInt(pinTransaccion) != session.getCliente().getPinTransaccion()){
            throw new PINTransaccionIncorrectoExcepcion();
        }
        
        String comprobante = transferencia.realizarTransferencia(Integer.parseInt(pinTransaccion));
        comprobante += "\nNOMBRE: "+session.getCliente().getNombreApellido();
        return comprobante;
    }
    
}
