package App;

import Model.Cliente;

/**
 * Clase encargada de mantener la referencia al cliente que inicio session
 * @author Grupo 14
 */
public class Session {
    private Cliente cliente;
    
    /**
     * Constructor de la clase Session
     * @param cliente cliente que inicio session
     */
    public Session(Cliente cliente) {
        this.cliente = cliente;
    } // Fin constructor
    
    
    /**
     * Devuelve el cliente que inicio session
     * @return instacia de Cliente
     */
    public Cliente getCliente(){
        return cliente; 
    }
}
