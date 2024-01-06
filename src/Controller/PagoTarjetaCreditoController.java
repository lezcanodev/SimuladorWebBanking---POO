package Controller;

import App.Session;
import Exceptions.CuentaMontoInsuficienteException;
import Exceptions.PINTransaccionIncorrectoExcepcion;
import Helper.Validador;
import Model.Cuenta;
import Model.PagoTarjetaCredito;
import Model.TarjetaCredito;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que se encarga de validar los datos de la
 * ventana GUIPagoTarjetaCredito
 * @author Grupo 14
 */
public class PagoTarjetaCreditoController {
    private Session session;
    private SimuladorWebBanking simulador;

    /**
     * Constructor de clas clase PagoTarjetaCreditoController
     * @param session session del cliente
     * @param simulador simulador de la apliacion
     */
    public PagoTarjetaCreditoController(Session session, SimuladorWebBanking simulador) {
        this.session = session;
        this.simulador = simulador;
    } // Fin constructor
    
    
    /**
     * Valida los datos para hacer el pago de la tarjeta de credito
     * y luego instancia un PagoTarjetaCredito y lo devuelve
     * @param cuenta cuenta con la que se pagara la tarjetas
     * @param tarjeta tarjeta de credito a pagar
     * @param monto monto a pagar
     * @return PagoTarjetaCredito
     * @throws IllegalArgumentException lanzado si un parametro de entrada es invalido
     * @throws CuentaMontoInsuficienteException lanzado si la cuenta no tiene el monto suficiente
     */
    public PagoTarjetaCredito validarDatosPagoTarjetaCredito(Cuenta cuenta, TarjetaCredito tarjeta, String monto)
    throws IllegalArgumentException, CuentaMontoInsuficienteException{        
        if(!Validador.esDouble(monto)){
            throw new IllegalArgumentException("Monto a pagar es invalido");
        }
        
        Double m = Double.parseDouble(monto);
        
        if(cuenta.getSaldo() < m){
            throw new CuentaMontoInsuficienteException();
        }
        
        if(tarjeta.getDeudaTotal() == 0){
            throw new IllegalArgumentException("La deuda ya fue pagada");
        }
        
        if(tarjeta.calcularPagoMinimo() > m){
            throw new IllegalArgumentException("El monto ingresado es menor al minimo");
        }
        
        if(tarjeta.getDeudaTotal() < m){
            throw new IllegalArgumentException("El monto ingresado es superior a la deuda");
        }
        
        PagoTarjetaCredito pago = new PagoTarjetaCredito(
                tarjeta,
                cuenta,
                m
        );
       return pago;
    }
    
    
    /**
     * Valida el pin de transaccion y realizs el pago de la tarjeta
     * @param pagoTarjeta pago de la tarjeta de credito
     * @param pinTransaccion pin de transaccion del cliente
     * @return String que representa el comprobante
     * @throws PINTransaccionIncorrectoExcepcion lanzado si el pin de transaccion es incorrecto
     * @throws IllegalArgumentException lanzado si el pin de transaccion es invalido
     */
    public String validarPagoTarjetaCredito(PagoTarjetaCredito pagoTarjeta, String pinTransaccion)
    throws PINTransaccionIncorrectoExcepcion, IllegalArgumentException{
        
        if(!Validador.esInteger(pinTransaccion)){
            throw new IllegalArgumentException("El PIN de transaccion es invalido");
        }
        
        if(Integer.parseInt(pinTransaccion) != session.getCliente().getPinTransaccion()){
            throw new PINTransaccionIncorrectoExcepcion();
        }
        
        String comprobante = pagoTarjeta.realizarPago(Integer.parseInt(pinTransaccion));
        
        return comprobante;
    }
    
    
}
