package simuladorwebbanking;

import GUI.GUIAutentificacion;
import Model.Cliente;
import Model.Cuenta;
import Model.CuentaDeTercero;
import Model.PagoServicio;
import Model.PagoServicioAnde;
import Model.PagoServicioEssap;
import Model.Servicio;
import Model.TarjetaCredito;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Clase encargada de crear todas las instanscias
 * necesarias para simular las operaciones e inicializar
 * la aplicacion
 * 
 * @author Grupo 14
 */
public final class SimuladorWebBanking {

    /**
     * Este metodo se encarga de instanciar el SimuladorWebBanking
     * e iniciar la aplicación
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimuladorWebBanking simulador = new SimuladorWebBanking();
        new GUIAutentificacion(simulador);
    }
    
    private final Cliente[] clientes; // Clientes para simular las operaciones
    private final PagoServicio[] pagoServicios; // pagos de servicios
    private final Servicio[] servicios; // servicios
    private  CajeroDepositoSimulador cajero; // cajero que solo soporta deposito
    
    /**
     * Constructor de la clase SimuladorWebBanking se encarga de inicializar
     * todos los datos necesarios para empezar la simulacion
     */
    public SimuladorWebBanking(){
        this.clientes = new Cliente[2];
        this.pagoServicios = new PagoServicio[5];
        this.servicios = new Servicio[2];
        iniciarCliente();
        iniciarPagosServicio();
        iniciarCajeroDepositoSimulador();
    } // Fin del constructor
    
    /**
     * Crea las instacias de clientes cada uno con sus cuentas
     * tarjetas de credito y cuenta de terceros para poder
     * simular las distintas operaciones
     */
    public void iniciarCliente(){        
        /* INICIAMOS CUENTAS PARA LOS CLIENTES  */        
        Object[][] tablaCuentas = {
        //    id  |  nroCuenta  |   tipo      | saldo
            {  1  ,     3421    , "Ahorro"    , 500000d },
            {  2  ,     9087    , "Corriente" , 850000d },
            {  3  ,     7771    , "Ahorro"    , 275000d },
            {  4  ,     8881    , "Ahorro"    , 275000d },
        };
        
        // Creamos las cuentas para el cliente Jorge
        Cuenta[] cuentasParaJorge = new Cuenta[2];
        for (int i = 0; i < 2; i++) {
            Integer idCuenta = Integer.valueOf(tablaCuentas[i][0]+"");
            Integer nroCuenta = Integer.valueOf(tablaCuentas[i][1]+"");
            String tipoCuenta = tablaCuentas[i][2] + "";
            Double saldo  = Double.valueOf(tablaCuentas[i][3]+"");
            
            cuentasParaJorge[i] = new Cuenta(
                    idCuenta, nroCuenta, tipoCuenta, saldo
            );
        }

        // Creamos las cuentas para el cliente Pedro
        Cuenta[] cuentasParaPedro = new Cuenta[2];
        for (int i = 2; i < tablaCuentas.length; i++) {
            Integer idCuenta = Integer.valueOf(tablaCuentas[i][0]+"");
            Integer nroCuenta = Integer.valueOf(tablaCuentas[i][1]+"");
            String tipoCuenta = tablaCuentas[i][2] + "";
            Double saldo  = Double.valueOf(tablaCuentas[i][3]+"");
            
            cuentasParaPedro[i-2] = new Cuenta(
                    idCuenta, nroCuenta, tipoCuenta, saldo
            );
        }
        /* FIN ASIGNAMOS CUENTAS A LOS CLIENTES  */
        
        
        /* INICIAMOS LOS CLIENTES */
        
        // Datos para simular el cliente
        Object[][] tablaClientes = {
        //    id  |   ruc o ci  |  nombre  |  apellido  | pin cuenta | pin transaccion  |     cuentas
            {  1  ,  "1234567"  ,  "Jorge" , "Vergara"  ,    1234    ,  5678            ,  cuentasParaJorge },
            {  2  ,  "7654321"  ,  "Pedro" , "Ramirez"  ,    4321    ,  8765            ,  cuentasParaPedro },
        };
        
        // Creamos los cliente
        for (int i = 0; i < tablaClientes.length; i++) {
            Integer idCliente = Integer.valueOf(tablaClientes[i][0]+"");
            String nombre = tablaClientes[i][2]+"";
            String apellido = tablaClientes[i][3]+"";
            Integer pinCuenta = Integer.valueOf(tablaClientes[i][4]+"");
            Integer pinTransaccion = Integer.valueOf(tablaClientes[i][5]+"");
            String rucOci = tablaClientes[i][1]+"";
            Cuenta[] cuentas = (Cuenta[])tablaClientes[i][6];
                    
            clientes[i] = new Cliente(
                    idCliente, nombre, apellido, pinCuenta,
                    pinTransaccion, rucOci, cuentas
            );
        }
        
        Cliente clienteJorge = clientes[0];
        Cliente clientePedro = clientes[1];
        /* FIN INICIAMOS LOS CLIENTES */
        
        
        /*  ASIGNAMOS TARJETAS DE CREDITO A LOS CLIENTES */
        Object[][] tablaTarjetasCredito = {
        //     id  |      nro tartjeta      | codigo tarheta |  fecha vencimiento  | saldoDisponible  | deuda total
            {  1   ,  "123456 78912345678"  ,     6532       ,     "01/12/2025"    ,     250000d      ,   800000d    },
            {  2   ,  "345678 12345678912"  ,     2587       ,     "01/01/2027"    ,     1500000d     ,   500000d    }
        };
       
        TarjetaCredito[] tarjetas = new TarjetaCredito[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (int i = 0; i < tablaTarjetasCredito.length; i++) {
            Integer idTajeta = Integer.valueOf(tablaTarjetasCredito[i][0]+"");
            String nroTarjeta = tablaTarjetasCredito[i][1]+"";
            String codigoTarjeta = tablaTarjetasCredito[i][2]+"";
            LocalDate fechaVencimiento = LocalDate.parse(tablaTarjetasCredito[i][3]+"", formatter);
            Double saldoDisponible = Double.valueOf(tablaTarjetasCredito[i][4]+"");
            Double deudaTotal = Double.valueOf(tablaTarjetasCredito[i][5]+"");
            
            tarjetas[i] = new TarjetaCredito(
                    idTajeta,
                    nroTarjeta,
                    codigoTarjeta,
                    fechaVencimiento,
                    saldoDisponible,
                    deudaTotal
            );
        }
        clienteJorge.setTarjetasCredito(tarjetas);
        /*  FIN ASIGNAMOS TARJETAS DE CREDITO A LOS CLIENTES */
        
        
        /* ASIGNAMOS CUENTAS DE TERCEROS A LOS CLIENTES */
        
        //Cuentas de terceros para el cliente jorge
        CuentaDeTercero[] cuentasDeTerceroParaJorge = {
            new CuentaDeTercero(
                  1,
                  clientePedro.getMisCuentas()[0].getNroCuenta(),
                  "Pedro",
                  "Ramirez",
                  clientePedro.getRucOci()
            )
        };
        clienteJorge.setCuentaDeTerceros(cuentasDeTerceroParaJorge);
        
        //Cuentas de terceros para el cliente pedro
        CuentaDeTercero[] cuentasDeTerceroParaPedro = {
            new CuentaDeTercero(
                  2,
                  clienteJorge.getMisCuentas()[0].getNroCuenta(),
                  "Jorge",
                  "Vergara",
                  clienteJorge.getRucOci()
            )
        };
        clientePedro.setCuentaDeTerceros(cuentasDeTerceroParaPedro);
        
    }
    

    /**
     * Crea las instacias de pago de servicios para poder
     * simular el pago de un servicio
     */
    public void iniciarPagosServicio(){
        /*CREAMOS LOS SERVICIOS*/
        String[][] tablaServicios = {
          //  Empresa   |   Codigo 
            {  "Ande"   ,   "123456" },
            {  "Essap"  ,   "654321" }
        };
        
        for (int i = 0; i < tablaServicios.length; i++) {
            String empresa = tablaServicios[i][0];
            String codServicio = tablaServicios[i][1];
            
            servicios[i] = new Servicio(
                    empresa,
                    codServicio
            );
        }
        /*FIN CREAMOS LOS SERVICIOS*/
        
        /*CREAMOS LOS PAGOS DE SERVICIO*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Servicio servicioAnde = servicios[0];
        Servicio servicioEssap = servicios[1];
        
        Object[][] tablaPagoServicio = {
         //   id |   monto   |   servicio    | identificador cliente | fecha de vencimiento
            { 1  ,  120000d  , servicioAnde  ,         123456        ,     "01/12/2023"    },
            { 2  ,  125000d  , servicioAnde  ,         123456        ,     "01/01/2024"    },
            { 3  ,  75000d   , servicioAnde  ,         654321        ,     "01/01/2024"    },
            { 4  ,  250000d  , servicioEssap ,         123456        ,     "16/02/2024"    },
            { 5  ,   null    , servicioEssap ,         654321        ,     "06/08/2024"    }
        };

        //Creamos pago servicios de ande
        for (int i = 0; i < 3; i++) {
            Integer id = Integer.valueOf(tablaPagoServicio[i][0]+"");
            Double monto = (tablaPagoServicio[i][1] == null) ? null :  Double.valueOf(tablaPagoServicio[i][1]+"");
            Servicio servicio = (Servicio)tablaPagoServicio[i][2];
            LocalDate fechaVencimiento =  LocalDate.parse(tablaPagoServicio[i][4]+"", formatter);
            Integer identificadorCliente =  Integer.valueOf(tablaPagoServicio[i][3]+"");
            
            pagoServicios[i] = new PagoServicioAnde(
               id, servicio, monto, fechaVencimiento, PagoServicio.PENDIENTE, identificadorCliente
            );
        } 
        
        //Creamos pago servicios de essap
        for (int i = 3; i < tablaPagoServicio.length; i++) {
            Integer id = Integer.valueOf(tablaPagoServicio[i][0]+"");
            Double monto = (tablaPagoServicio[i][1] == null) ? null :  Double.valueOf(tablaPagoServicio[i][1]+"");
            Servicio servicio = (Servicio)tablaPagoServicio[i][2];
            LocalDate fechaVencimiento =  LocalDate.parse(tablaPagoServicio[i][4]+"", formatter);
            Integer identificadorCliente =  Integer.valueOf(tablaPagoServicio[i][3]+"");
            
            pagoServicios[i] = new PagoServicioEssap(
               id, servicio, monto, fechaVencimiento, PagoServicio.PENDIENTE, identificadorCliente
            );
        } 
        /*FIN CREAMOS LOS PAGOS DE SERVICIO*/
        
    }
    
    /**
     * Inicializa un cajero para simular un cajero
     */
    public void iniciarCajeroDepositoSimulador(){
        this.cajero = new CajeroDepositoSimulador(
                1,
                this
        );
    }
    
    /**
     * Devuelve los cliente que genero el simulador
     * @return arreglo de clientes
     */
    public Cliente[] getClientes(){
        return clientes;
    }
    
    /**
     * Devuelve los servicios que genero el simulador
     * @return arreglo de servicios
     */
    public Servicio[] getServicios(){
        return servicios;
    }
    
    /**
     * Devuelve los pagos de servicio que genero el simulador
     * @return arreglo de pago de servicios
     */
    public PagoServicio[] getPagoServicios(){
        return pagoServicios;
    }
    
    /**
     * Devuelve el cajero que genero el simulador
     * @return cajero
     */
    public CajeroDepositoSimulador getCajeroSimulador(){
        return cajero;
    }
    
}
