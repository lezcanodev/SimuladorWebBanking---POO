
package Model;

/**
 * Clase encaragada de representar un Servicio
 * @author Grupo 14
 */
public class Servicio {
    /**
     * Id del servicio
     */
    protected Integer idServicio;
    /**
     * Codigo del servicio
     */
    protected String codServicio;
    /**
     * Nombre de la empresa del servicio
     */
    protected String empresa;
    
    // para simular un id auto incremental
    private static int ID_SERVICIO = 1;
    
    /**
     * Constructor de la clase Servicio
     * @param codServicio codigo del servicio
     * @param empresa nombre de la empresa del servicio
     */
    public Servicio(String empresa , String codServicio) {
        assert empresa != null : "La empresa no debe ser null";
        assert codServicio != null : "El codigo de servicio no debe ser null";
        
        this.idServicio = ID_SERVICIO++;
        this.codServicio = codServicio;
        this.empresa = empresa;
    }
    
    /**
     * Devuelve el id del servicio
     * @return Integer que representa el id del servicio
     */
    public Integer getIdServicio() {
        return idServicio;
    }

    /**
     * Devuelve el codigo del servicio
     * @return String que repsenta el codigo del servicio
     */
    public String getCodServicio() {
        return codServicio;
    }

    /**
     * Devuelve el nombre de la empresa
     * @return String que representa el nombre del empresa
     */
    public String getEmpresa() {
        return empresa;
    }
    
    
    /**
     * Representacion del Servicio como String
     * @return String que representa el servicio
     */
    @Override
    public String toString(){
        return getEmpresa()+" - "+getCodServicio();
    }
}
