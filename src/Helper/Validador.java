package Helper;

/**
 * Clase auxiliar que ayuda a verificar ciertos datos
 * @author Grupo 14
 */
public class Validador {
    
    /**
     * Funcion que verifica si s es un integer correcto
     * @param s cadena a verificar
     * @return true si es integer false en caso contrario
     */
    public static boolean esInteger(String s){
        if (estaVacio(s)) return false;

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Funcion que verifica si s es un double correcto
     * @param s cadena a verificar
     * @return true si es double false en caso contrario
     */
    public static boolean esDouble(String s){
        if (estaVacio(s)) return false;
        
        boolean es = true;
        try{
            Double.parseDouble(s);
        }catch(Exception ex){
            es = false;
        }finally{
            return es; 
        }
    }
    
    /**
     * Verifica si una cadena es nula o vacia
     * @param s cadena a validar
     * @return true si es vacio en caso contrario false
     */
    public static boolean estaVacio(String s){
        if(s == null) return true;
        return s.trim().length() <= 0;
    }
    
}
