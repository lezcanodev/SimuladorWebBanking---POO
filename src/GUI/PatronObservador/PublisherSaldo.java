package GUI.PatronObservador;

/**
 * Esta clase es la encargada de notificar a todos los suscriptores que
 * el saldo ha cambiado en este caso los suscriptores son GUIVentanaPrincipal.
 * 
 * Esta clase implementa el patron de diseño Observador para la notificacion
 * del cambio de saldo, y tambien utiliza el patron Singleton por que solo
 * es necesario una instancia una vez el simulador este abierto.
 * 
 * @author Grupo 14
 */
public class PublisherSaldo {
    private SuscriptorSaldo[] suscriptores;
    private int iSuscriptores;
    private static PublisherSaldo singleton = null;
    
    /**
     * Constructor de la clase PublisherSaldo
     */
    private PublisherSaldo(){
        this.suscriptores = new SuscriptorSaldo[50];
        this.iSuscriptores = 0;
    } // Fin del constructor
    
    /**
     * Se encarga de crear una instancia si es que todavia no existe y 
     * luego la devuelve
     * @return PublisherSaldo
     */
    public static PublisherSaldo getInstancia(){
        if(singleton == null){
            singleton = new PublisherSaldo();
        }
        return singleton;
    }
    
    /**
     * Añade un suscriptor
     * @param suscriptor  suscriptor que sera añadido
     */
    public void addSuscriptor(SuscriptorSaldo suscriptor){
        suscriptores[iSuscriptores++] = suscriptor;
    }
    
    /**
     * Notifica a todos los suscriptores que el saldo ha cambiado
     */
    public void notificarCambioSaldo(){
        for (SuscriptorSaldo suscriptor : suscriptores ) {
            if(suscriptor != null){
                suscriptor.actualizarSaldo();
            }
        }
    }
    
}
