package Controller;

import App.Session;
import Exceptions.CuentaMontoInsuficienteException;
import Exceptions.PagoServicioMontoInsuficienteException;
import Exceptions.PagoServicioMontoSuperiorException;
import Exceptions.PagoServicioNoExisteException;
import Exceptions.TarjetaCreditoMontoInsuficienteException;
import Exceptions.ClienteNoTienePagosServicioException;
import Exceptions.PINTransaccionIncorrectoExcepcion;
import Helper.Validador;
import Model.Cuenta;
import Model.PagoServicio;
import Model.TarjetaCredito;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que se encarga de validar los datos de la
 * ventana GUIPagoServicio
 * @author Grupo 14
 */
public class PagoServicioController {
    private Session session;
    private SimuladorWebBanking simulador;

    /**
     * Constructor de clas clase PagoServicioController
     * @param session session del cliente
     * @param simulador simulador de la apliacion
     */
    public PagoServicioController(Session session, SimuladorWebBanking simulador) {
        this.session = session;
        this.simulador = simulador;
    } // Fin constructor
    
    
    /**
     * Busca y devuelve un pago de servicio que tenga PENDIENTE el cliente,
     * si el servicio no existe o no hay ningun pago se lanza una excepcion
     * @param codServicio codigo del servicio
     * @param identificadorCliente identificador del cliente en el servicio
     * @return PagoServicio
     * @throws PagoServicioNoExisteException lanzado si no existe el pago de servicio
     * @throws IllegalArgumentException  lanzado si uno de los parametros esta mal
     * @throws Exceptions.ClienteNoTienePagosServicioException lanzado si el cliente no tiene nada que pagar
     */
    public PagoServicio validarDatosServicio(String codServicio, String identificadorCliente)
    throws PagoServicioNoExisteException, IllegalArgumentException, ClienteNoTienePagosServicioException{
        PagoServicio[] servicios = simulador.getPagoServicios();
        
        if(Validador.estaVacio(codServicio)){
            throw new IllegalArgumentException("Codigo de servicio invalido");
        }
        
        if(Validador.estaVacio(identificadorCliente)){
            throw new IllegalArgumentException("Identificador de cliente invalido");
        }
        
        boolean existeServicio = false;

        // Buscamo si existe un pago de servicio pendiente para el cliente
        for (PagoServicio servicio : servicios) {
         
            if(servicio.getCodServicio().equals(codServicio) &&
               servicio.equalsIdentificador(identificadorCliente) &&
               servicio.getEstado().compareTo(PagoServicio.PENDIENTE)==0 ){

                return servicio;
            }
            if(servicio.getCodServicio().equals(codServicio) &&
               servicio.equalsIdentificador(identificadorCliente)){
                existeServicio = true;
            }
        }
        
        /*
        Si existe el servicio y llego hasta aqui significa que el cliente
        no tiene nada que pagar
        */
        if(existeServicio){
            throw new ClienteNoTienePagosServicioException();
        }
        
        throw new PagoServicioNoExisteException();
    }
    
    
    /**
     * Valida que los datos de realizar pago con cuenta sean correctos y luego
     * realiza el pago
     * @param cuenta cuenta con la que se pagara
     * @param pinTransaccion pin de transaccion del cliente
     * @param pagoServicio servicio a pagar
     * @param monto monto ingresado para pagar el servicio
     * @return String que representa el comprobante
     * @throws PINTransaccionIncorrectoExcepcion lanzado si el pin de transaccion es incorrecto
     * @throws CuentaMontoInsuficienteException Lanzado si la cuenta no tiene el monto suficiente
     * @throws PagoServicioMontoSuperiorException Lanzado si ingreso un monto superior para realizar el pago 
     * @throws IllegalArgumentException Lanzado si una entranda tiene un formato invalido
     * @throws PagoServicioMontoInsuficienteException Lanzado si no ingreso el monto suficiente para realizar el pago 
     */
    public String validarPagoServicio(Cuenta cuenta, String pinTransaccion, PagoServicio pagoServicio, String monto)
    throws PINTransaccionIncorrectoExcepcion, CuentaMontoInsuficienteException, PagoServicioMontoSuperiorException, IllegalArgumentException, PagoServicioMontoInsuficienteException{
        
        if(pagoServicio.getEstado().compareTo(PagoServicio.PAGADO)==0){
            throw new IllegalArgumentException("EL servicio ya fue pagado");
        }
        
        if(!Validador.esInteger(pinTransaccion)){
            throw new IllegalArgumentException("El PIN de transaccion es invalido");
        }
        
        if(Integer.parseInt(pinTransaccion) != session.getCliente().getPinTransaccion()){
            throw new PINTransaccionIncorrectoExcepcion();
        }
        
        if(!Validador.esDouble(monto)){
            throw new IllegalArgumentException("Monto invalido");
        }
        
        Double m = Double.parseDouble(monto);
        
        if(cuenta.getSaldo() < m){
            throw new CuentaMontoInsuficienteException();
        }

        if(pagoServicio.getMonto() != null){
            if(pagoServicio.getMonto() > m) throw new PagoServicioMontoInsuficienteException();
            if(pagoServicio.getMonto() < m) throw new PagoServicioMontoSuperiorException();
        }
        
        String comprobante = pagoServicio.realizarPago(cuenta, Integer.parseInt(pinTransaccion), m);
        comprobante += "\nNOMBRE: "+session.getCliente().getNombreApellido();
        return comprobante;
    }
    
    
    /**
     * Valida que los datos de realizar pago con tarjeta de credito sean correctos y luego
     * realiza el pago
     * @param tarjeta tarjeta de credito con la que se pagara
     * @param pinTransaccion pin de transaccion del cliente
     * @param pagoServicio servicio a pagar
     * @param monto monto ingresado para pagar el servicio
     * @return String que representa el comprobante
     * @throws PINTransaccionIncorrectoExcepcion lanzado si el pin de transaccion es incorrecto
     * @throws TarjetaCreditoMontoInsuficienteException Lanzado si la tarjeta de credito no tiene el monto suficiente
     * @throws PagoServicioMontoSuperiorException Lanzado si ingreso un monto superior para realizar el pago 
     * @throws IllegalArgumentException Lanzado si una entranda tiene un formato invalido
     * @throws PagoServicioMontoInsuficienteException Lanzado si no ingreso el monto suficiente para realizar el pago 
     */
    public String validarPagoServicio(TarjetaCredito tarjeta, String pinTransaccion, PagoServicio pagoServicio, String monto)
    throws PINTransaccionIncorrectoExcepcion, TarjetaCreditoMontoInsuficienteException, PagoServicioMontoSuperiorException, IllegalArgumentException, PagoServicioMontoInsuficienteException{
        
        if(!Validador.esInteger(pinTransaccion)){
            throw new IllegalArgumentException("El PIN de transaccion es invalido");
        }
        
        if(Integer.parseInt(pinTransaccion) != session.getCliente().getPinTransaccion()){
            throw new PINTransaccionIncorrectoExcepcion();
        }
        
        if(!Validador.esDouble(monto)){
            throw new IllegalArgumentException("Monto invalido");
        }
        
        Double m = Double.parseDouble(monto);
        
        if(tarjeta.getSaldoDisponible() < m){
            throw new TarjetaCreditoMontoInsuficienteException();
        }

        if(pagoServicio.getMonto() != null){
            if(pagoServicio.getMonto() > m) throw new PagoServicioMontoInsuficienteException();
            if(pagoServicio.getMonto() < m) throw new PagoServicioMontoSuperiorException();
        }
        
        String comprobante = pagoServicio.realizarPago(tarjeta, Integer.parseInt(pinTransaccion), m);
        comprobante += "\nNOMBRE: "+session.getCliente().getNombreApellido();
        return comprobante;
    }
    
}
