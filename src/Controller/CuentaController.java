package Controller;

import App.Session;
import Model.Cuenta;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que se encarga de validar los datos sobre la clase cuenta
 * @author Grupo 14
 */
public class CuentaController {
    private Session session;
    private SimuladorWebBanking simuladorWebBanking;

    /**
     * Constructor de la clase CuentaController
     * @param session session del cliente
     * @param simuladorWebBanking simulador que contiene los datos
     */
    public CuentaController(Session session, SimuladorWebBanking simuladorWebBanking) {
        this.session = session;
        this.simuladorWebBanking = simuladorWebBanking;
    }// Fin constructor

    /**
     * Verifica si la cuenta pasada es valida y la devuelve 
     * @param cuenta cuenta que sera validada
     * @return Cuenta
     * @throws IllegalArgumentException lanzado si la cuenta no es valida
     */
    public Cuenta validarCuenta(Cuenta cuenta) throws IllegalArgumentException{
        
        if(cuenta == null){
            throw new IllegalArgumentException("La cuenta no es valida");
        }
        
        return cuenta;
    }
}
