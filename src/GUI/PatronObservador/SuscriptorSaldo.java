package GUI.PatronObservador;

/**
 * Define los metodos que debe tener un suscriptor para poder
 * ser notificado de los cambios del saldo
 * @author Grupo 14
 */
public interface SuscriptorSaldo {
    /**
     * Definimos el metodo que es llamado cuando el saldo cambia
     */
    public void actualizarSaldo();
}
