
package Model;

import simuladorwebbanking.SimuladorWebBanking;

/**
 * Clase que se encarga de autentificar al cliente
 * @author Grupo 14
 */
public class Autentificar {
    private SimuladorWebBanking simuladorWebBanking;

    /**
     * Constructor de la clase Autentificar
     * @param simuladorWebBanking simulador que contiene los datos
     */
    public Autentificar(SimuladorWebBanking simuladorWebBanking) {
        
        assert simuladorWebBanking != null: "El simulador no puede ser null";
        
        this.simuladorWebBanking = simuladorWebBanking;
    }// Fin constructor
    
    /**
     * Verifica si el cliente existe y lo retorna
     * @param rucOci ruc o ci del cliente a autentificar
     * @param pinCuenta pin de cuenta del cliente a autentificar
     * @return cliente si existe o null si no existe
     */
    public Cliente autentificar(String rucOci, Integer pinCuenta){
        Cliente[] clientes = simuladorWebBanking.getClientes();
        
        for (Cliente cliente : clientes) {
            if(cliente.getRucOci().equals(rucOci) && 
               cliente.getPinCuenta().equals(pinCuenta)){
                return cliente;
            }
        }
                
        return null;
    }
}
