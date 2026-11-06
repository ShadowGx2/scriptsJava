
import java.util.ArrayDeque;             // Importa una cola para almacenar los datos transmitidos.
import java.util.Queue;                  // Permite trabajar con una estructura de tipo cola.
import java.nio.charset.StandardCharsets; // Permite convertir texto a bytes usando UTF-8.
import java.io.ByteArrayOutputStream;     // Permite reconstruir los bytes recibidos en un arreglo.

// Declaramos la clase que representa nuestro puerto de comunicación.
class PuertoComunicacion {

    // Creamos una cola que funcionará como búfer de transmisión.
    private Queue<Byte> buffer = new ArrayDeque<>();

    // Variable que indica si el puerto está abierto o cerrado.
    private boolean puertoAbierto = false;

    // Variable que almacena el nombre del puerto simulado.
    private String nombrePuerto;

    // Constructor: recibe el nombre del puerto al crear el objeto.
    public PuertoComunicacion(String nombrePuerto) {

        // Guardamos el nombre recibido en el atributo de la clase.
        this.nombrePuerto = nombrePuerto;
    }

    // Método encargado de abrir el puerto de comunicación.
    public void abrirPuerto() {

        // Comprobamos si el puerto ya se encuentra abierto.
        if (!puertoAbierto) {

            // Cambiamos el estado del puerto a abierto.
            puertoAbierto = true;

            // Mostramos un mensaje indicando que el puerto está abierto.
            System.out.println("Puerto " + nombrePuerto + " abierto correctamente.");

        } else {

            // Informamos que el puerto ya estaba abierto.
            System.out.println("El puerto ya se encuentra abierto.");
        }
    }

    // Método encargado de enviar información a través del puerto.
    public void enviarDatos(String mensaje) {

        // Verificamos que el puerto esté abierto antes de transmitir.
        if (!puertoAbierto) {

            // Mostramos un mensaje de error si el puerto está cerrado.
            System.out.println("ERROR: No se pueden enviar datos. Puerto cerrado.");

            // Terminamos la ejecución del método.
            return;
        }

        // Convertimos el mensaje de texto en un arreglo de bytes UTF-8.
        byte[] datos = mensaje.getBytes(StandardCharsets.UTF_8);

        // Mostramos el mensaje que será enviado.
        System.out.println("\n[EMISOR] Enviando mensaje: " + mensaje);

        // Recorremos cada byte del mensaje.
        for (byte dato : datos) {

            // Introducimos cada byte en el búfer del puerto.
            buffer.add(dato);

            // Mostramos el valor numérico del byte transmitido.
            System.out.println("[PUERTO] Byte transmitido: " + (dato & 0xFF));
        }

        // Indicamos que la transmisión terminó.
        System.out.println("[PUERTO] Transmisión finalizada.");
    }

    // Método encargado de recibir los datos almacenados en el búfer.
    public void recibirDatos() {

        // Verificamos que el puerto esté abierto.
        if (!puertoAbierto) {

            // Informamos que no es posible recibir información.
            System.out.println("ERROR: El puerto está cerrado.");

            // Finalizamos el método.
            return;
        }

        // Comprobamos si existen datos disponibles para recibir.
        if (buffer.isEmpty()) {

            // Informamos que no hay datos pendientes.
            System.out.println("[RECEPTOR] No hay datos disponibles.");

            // Terminamos la ejecución del método.
            return;
        }

        // Creamos un objeto para reconstruir el mensaje recibido.
        ByteArrayOutputStream datosRecibidos = new ByteArrayOutputStream();

        // Mostramos que comenzó el proceso de recepción.
        System.out.println("\n[RECEPTOR] Recibiendo datos...");

        // Extraemos los bytes de la cola mientras existan datos.
        while (!buffer.isEmpty()) {

            // Retiramos el primer byte del búfer.
            byte dato = buffer.poll();

            // Guardamos el byte recibido para reconstruir el mensaje.
            datosRecibidos.write(dato);

            // Mostramos el valor numérico del byte recibido.
            System.out.println("[RECEPTOR] Byte recibido: " + (dato & 0xFF));
        }

        // Convertimos todos los bytes recibidos nuevamente en texto.
        String mensaje = new String(
            datosRecibidos.toByteArray(),
            StandardCharsets.UTF_8
        );

        // Mostramos el mensaje reconstruido en el dispositivo receptor.
        System.out.println("\n[RECEPTOR] Mensaje recibido: " + mensaje);

        // Indicamos que la recepción terminó correctamente.
        System.out.println("[PUERTO] Recepción finalizada.");
    }

    // Método para consultar el estado actual del puerto.
    public void mostrarEstado() {

        // Comprobamos el valor de la variable que controla el puerto.
        if (puertoAbierto) {

            // Mostramos que el puerto está abierto.
            System.out.println("Estado del puerto: ABIERTO");

        } else {

            // Mostramos que el puerto está cerrado.
            System.out.println("Estado del puerto: CERRADO");
        }
    }

    // Método encargado de cerrar el puerto.
    public void cerrarPuerto() {

        // Comprobamos si el puerto está abierto.
        if (puertoAbierto) {

            // Cambiamos el estado del puerto a cerrado.
            puertoAbierto = false;

            // Mostramos el mensaje de cierre.
            System.out.println("\nPuerto " + nombrePuerto + " cerrado.");

        } else {

            // Informamos que el puerto ya estaba cerrado.
            System.out.println("El puerto ya estaba cerrado.");
        }
    }
}

// Clase principal del programa.
public class SimuladorPuerto {

    // Método principal: punto de inicio de la aplicación.
    public static void main(String[] args) {

        // Mostramos el título del programa.
        System.out.println("====================================");

        // Identificamos el simulador.
        System.out.println(" SIMULADOR DE PUERTO DE COMUNICACION");

        // Cerramos visualmente el encabezado.
        System.out.println("====================================");

        // Creamos un objeto que representa el puerto COM1.
        PuertoComunicacion puerto = new PuertoComunicacion("COM1");

        // Consultamos el estado inicial del puerto.
        puerto.mostrarEstado();

        // Abrimos el puerto para permitir la comunicación.
        puerto.abrirPuerto();

        // Creamos un mensaje que será transmitido.
        String mensaje = "Hola, este es un mensaje desde Java.";

        // Enviamos el mensaje desde el dispositivo emisor.
        puerto.enviarDatos(mensaje);

        // Recibimos los datos desde el dispositivo receptor.
        puerto.recibirDatos();

        // Consultamos nuevamente el estado del puerto.
        puerto.mostrarEstado();

        // Cerramos el puerto al terminar la comunicación.
        puerto.cerrarPuerto();

        // Mostramos que el programa terminó.
        System.out.println("\nSimulacion finalizada.");
    }
}
