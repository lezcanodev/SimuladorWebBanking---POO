package Controller;

import App.Session;
import Exceptions.ClienteNoExisteException;
import Helper.Validador;
import Model.Autentificar;
import Model.Cliente;
import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que se encarga de validar los datos de la
 * ventana GUIAutenficiar
 * @author Grupo 14
 */
public class AutentificarController {
    private SimuladorWebBanking simuladorWebBanking;

    /**
     * Constructor de la clase Autentificar controller
     * @param simuladorWebBanking simulador que contiene los datos
     */
    public AutentificarController(SimuladorWebBanking simuladorWebBanking) {
        this.simuladorWebBanking = simuladorWebBanking;
    }// Fin constructor

    
    /**
     * Valida los datos y devuelve una instancia de Session
     * @param rucOci ruc o ci del cliente
     * @param pinCuenta pin de cuenta del cliente
     * @return session del cliente
     * @throws ClienteNoExisteException lanzado si el cliente no existe
     * @throws IllegalArgumentException lanzado si rucOci o pinCuenta es invalido 
     */
    public Session validarDatosAuth(String rucOci, String pinCuenta) throws ClienteNoExisteException, IllegalArgumentException{
        
        // Validamos el pin de cuenta
        if(!Validador.esInteger(pinCuenta)){
            throw new IllegalArgumentException("Pin de cuenta invalido");
        }
        
        // Validamos el ruc o ci
        if(Validador.estaVacio(rucOci)){
            throw new IllegalArgumentException("RUC o CI invalido");
        }
        
        // Modelo autentificar
        Autentificar autenficiar = new Autentificar(simuladorWebBanking);
        
        // Intentamos obtener el cliente
        Cliente cliente = autenficiar.autentificar(rucOci, Integer.parseInt(pinCuenta));
        
        // Verificamos si existe el cliente
        if(cliente == null){
             throw new ClienteNoExisteException();
        }
        
        // Retornamos una session con el cliente
        return new Session(cliente);
    }
}
