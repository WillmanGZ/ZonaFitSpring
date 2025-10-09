package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    // Para interactuar con la consola
    private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);
    String nl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("Iniciando la aplicación");
        // Levantar la fabrica de spring
        SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("Aplicación finalizada");
    }

    // Metodo para hacer la aplicacion de consola, se ejecutará inmediatamente se monte la fabrica de spring
    @Override
    public void run(String... args) throws Exception {
        zonaFitApp();
    }

    private void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion);
            } catch (Exception e) {
                logger.info(nl + "Error al ejecutar opciones: " + e.getMessage() + nl);
            }
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info(nl + """
                \n*** Aplicación Zona Fit (GYM) ***
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elije una opción:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                // Listar Clientes
                logger.info(nl + "--- Listado de Clientes ---" + nl);
                var clientes = clienteServicio.listarClientes();
                clientes.forEach(cliente -> logger.info(nl + cliente.toString() + nl));
            }
            case 2 -> {
                // Buscar cliente por id
                logger.info(nl + "--- Buscar cliente por Id ---" + nl);
                logger.info("Introduce el id del cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = clienteServicio.buscarClientePorId(idCliente);

                if (cliente != null) {
                    logger.info("Cliente encontrado: " + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado" + nl);
                }
            }
            case 3 -> {
                // Agregar cliente
                logger.info(nl + "--- Agregar Cliente ---" + nl);
                logger.info("Nombre: ");
                var nombre = consola.nextLine();
                logger.info("Apellido: ");
                var apellido = consola.nextLine();
                logger.info("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setMembresia(membresia);
                clienteServicio.guardarCliente(cliente);
                logger.info("Cliente agregado: " + cliente + nl);
            }
            case 4 -> {
                // Modificar cliente
                logger.info(nl + "--- Modificar Cliente  ---" + nl);
                logger.info("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

                if (cliente != null) {
                    logger.info("Nombre: ");
                    var nombre = consola.nextLine();
                    logger.info("Apellido: ");
                    var apellido = consola.nextLine();
                    logger.info("Membresia: ");
                    var membresia = Integer.parseInt(consola.nextLine());

                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setMembresia(membresia);
                    clienteServicio.guardarCliente(cliente);
                    logger.info("Cliente Modificado" + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado" + nl);
                }
            }
            case 5 -> {
                // Eliminar cliente
                logger.info(nl + "--- Eliminar cliente por Id ---" + nl);
                logger.info("Introduce el id del cliente a eliminar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = clienteServicio.buscarClientePorId(idCliente);

                if (cliente != null) {
                    clienteServicio.eliminarCliente(cliente);
                    logger.info("Cliente eliminado: " + cliente + nl);
                } else {
                    logger.info("Cliente no encontrado" + nl);
                }
            }
            case 6 -> {
                // Salir
                logger.info(nl + "Hasta pronto!" + nl);
                salir = true;
            }
            default -> {
                logger.info(nl + "Opción no reconocida" + nl);
            }
        }
        return salir;
    }
}
