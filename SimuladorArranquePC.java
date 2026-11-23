
public class SimuladorArranquePC {

    // 1. DECLARACION DEL ARREGLO DE ETAPAS

    // String[] permite crear un arreglo que almacena
    // diferentes cadenas de texto (String).
    //
    // Cada posicion representa una etapa del encendido.
    //
    // Ejemplo:
    // etapas[0] = "BOTON DE ENCENDIDO"
    // etapas[1] = "FUENTE DE PODER (PSU)"

    static String[] etapas = {

            "BOTON DE ENCENDIDO",
            "FUENTE DE PODER (PSU)",
            "POWER GOOD",
            "CPU RESET",
            "BIOS / UEFI",
            "POST - Autodiagnostico",
            "MEMORIA RAM / DRAM",
            "CHIPSET Y BUSES",
            "TARJETA GRAFICA (VGA / GPU)",
            "DETECCION DE DISCO SSD / HDD",
            "BOOTLOADER",
            "CARGA DEL KERNEL",
            "INICIALIZACION DE DRIVERS",
            "INICIO DE SERVICIOS",
            "PANTALLA DE INICIO DE SESION"

    };

    // 2. VARIABLE QUE ALMACENA EL COMPONENTE CON FALLA

    // static indica que esta variable pertenece a la clase.
    //
    // String almacena texto.
    //
    // Esta variable permite seleccionar que componente
    // presentara un error durante la segunda simulacion.
    //
    // Para cambiar el error, modificar el texto.

    static String componenteConFalla = "MEMORIA RAM / DRAM";

    // 3. METODO PRINCIPAL MAIN

    // main es el punto de entrada del programa.
    //
    // La maquina virtual de Java (JVM) comienza
    // la ejecucion del programa desde este metodo.

    public static void main(String[] args) {

        // Mostramos el encabezado del programa.

        System.out.println("   SIMULADOR DE ARRANQUE DE PC");

        // PRIMERA CORRIDA: ARRANQUE EXITOSO

        // Se informa al usuario que comenzara la primera
        // simulacion, donde todos los componentes funcionan.

        System.out.println("\n PRIMERA CORRIDA");
        System.out.println("ESCENARIO: EQUIPO FUNCIONAL");

        // Llamamos al metodo ejecutarArranque.
        //
        // false significa que NO se provocara una falla.
        //
        // El programa recorrera todas las etapas
        // hasta llegar a la pantalla de inicio de sesion.

        ejecutarArranque(false);

        // SEGUNDA CORRIDA: ARRANQUE CON ERROR

        // Se informa que comenzara una nueva simulacion.
        // En esta ocasion se provocara una falla.

        System.out.println("\n\nSEGUNDA CORRIDA ");
        System.out.println("ESCENARIO: EQUIPO CON UNA FALLA");

        // true significa que se debe simular un error.
        //
        // El programa comparara cada etapa con la variable
        // componenteConFalla.
        //
        // Cuando encuentre coincidencia, detendra el proceso.

        ejecutarArranque(true);

        // Mensaje que indica el final de las simulaciones.

        System.out.println("FIN DE LA SIMULACION");

    }

    // 4. METODO QUE EJECUTA EL CICLO DE ENCENDIDO

    // public: permite acceder al metodo desde otras clases.
    //
    // static: permite utilizarlo sin crear un objeto.
    //
    // void: indica que el metodo no devuelve ningun valor.
    //
    // boolean provocarFalla:
    // recibe true o false para determinar si habra un error.

    public static void ejecutarArranque(boolean provocarFalla) {

        // Mensaje inicial del proceso.

        System.out.println("\nIniciando secuencia de encendido...\n");

        // CICLO FOR: RECORRER LAS ETAPAS DEL ARREGLO

        // int i = 0:
        // Creamos una variable llamada i que comienza en cero.
        //
        // i < etapas.length:
        // El ciclo continuara mientras i sea menor
        // que la cantidad de elementos del arreglo.
        //
        // i++:
        // Incrementa el valor de i en una unidad.
        //
        // El ciclo permite recorrer todas las etapas
        // sin escribir una instruccion para cada posicion.

        for (int i = 0; i < etapas.length; i++) {

            // Mostramos el numero y nombre de la etapa.
            //
            // i + 1 se utiliza porque los arreglos comienzan
            // en la posicion cero, pero queremos mostrar
            // las etapas comenzando desde el numero uno.

            System.out.println("ETAPA " + (i + 1) + ": " + etapas[i]);

            // 5. CONDICIONAL PARA DETECTAR UNA FALLA

            // if evalua si se cumplen las condiciones.
            //
            // provocarFalla:
            // Comprueba si se solicito simular un error.
            //
            // && significa AND (Y).
            // Ambas condiciones deben ser verdaderas.
            //
            // etapas[i].equals(componenteConFalla):
            // Compara el nombre de la etapa actual con
            // el componente que tiene configurada la falla.

            if (provocarFalla &&
                    etapas[i].equals(componenteConFalla)) {

                // Si las dos condiciones son verdaderas,
                // significa que encontramos el componente
                // que presenta el problema.

                System.out.println("[ERROR] FALLO DETECTADO");

                // Llamamos al metodo que explica el error.
                //
                // Le enviamos el nombre de la etapa afectada
                // para que pueda identificar el problema.

                mostrarError(etapas[i]);

                // Indicamos que el proceso se detendra.

                System.out.println("\nEl proceso de arranque se ha detenido.");
                System.out.println("No es posible continuar con el inicio.");

                // return termina inmediatamente el metodo.
                //
                // Esto evita que el ciclo siga ejecutandose.
                // Tampoco se llega a la pantalla de inicio.

                return;

            }

            // 6. SI NO HAY ERROR, CONTINUA EL ARRANQUE

            // Si el programa no encontro una falla,
            // muestra que el componente funciona correctamente.

            System.out.println("[OK] Componente inicializado correctamente.");

            // Llamamos a mostrarDetalle para explicar
            // que sucede internamente en cada etapa.
            //
            // etapas[i] contiene el nombre del componente
            // que estamos procesando actualmente.

            mostrarDetalle(etapas[i]);

            // Imprimimos una linea para separar las etapas.

            System.out.println("----------------------------------");

        }

        // 7. MENSAJE FINAL DE ARRANQUE EXITOSO

        // Estas instrucciones solamente se ejecutan
        // si el ciclo termina sin encontrar una falla.
        //
        // Si se ejecuta return dentro del if anterior,
        // nunca se llega a esta seccion.

        System.out.println("ARRANQUE COMPLETADO");
        System.out.println("Sistema operativo iniciado.");
        System.out.println("Bienvenido: Usuario Administrador");
        System.out.println("Pantalla de inicio de sesion disponible.");
        System.out.println("Ingresa tus credenciales para continuar.");
    }

    // 8. METODO PARA EXPLICAR CADA ETAPA

    // Este metodo recibe el nombre de una etapa
    // y muestra una explicacion de su funcionamiento.
    //
    // Utilizamos switch para evaluar diferentes opciones.
    //
    // Cada case representa un posible valor de la variable.

    public static void mostrarDetalle(String etapa) {

        switch (etapa) {

            // Cuando la etapa es el boton de encendido.

            case "BOTON DE ENCENDIDO":

                System.out.println("Se envia la senal de encendido.");

                break;

            // La fuente de poder distribuye energia.

            case "FUENTE DE PODER (PSU)":

                System.out.println("La fuente distribuye energia.");

                break;

            // Power Good indica que las condiciones
            // de alimentacion son adecuadas.

            case "POWER GOOD":

                System.out.println("Voltajes estables y senal de energia correcta.");

                break;

            // El procesador comienza su ejecucion
            // despues de la secuencia de reinicio.

            case "CPU RESET":

                System.out.println("El procesador comienza su ejecucion.");

                break;

            // El firmware BIOS o UEFI inicializa
            // y configura el hardware.

            case "BIOS / UEFI":

                System.out.println("Se ejecuta el firmware de la tarjeta madre.");

                break;

            // POST significa Power-On Self-Test.
            // Es una comprobacion inicial del hardware.

            case "POST - Autodiagnostico":

                System.out.println("Se comprueba el hardware esencial.");

                break;

            // La memoria DRAM se inicializa
            // para permitir que el sistema utilice RAM.

            case "MEMORIA RAM / DRAM":

                System.out.println("Se inicializa la memoria principal.");

                break;

            // El chipset y los buses permiten
            // la comunicacion entre los componentes.

            case "CHIPSET Y BUSES":

                System.out.println("Se inicializan las comunicaciones internas.");

                break;

            // Se inicializa el adaptador grafico
            // para proporcionar salida de video.

            case "TARJETA GRAFICA (VGA / GPU)":

                System.out.println("Se inicializa el sistema de video.");

                break;

            // El firmware busca un dispositivo
            // desde el cual pueda iniciar el sistema.

            case "DETECCION DE DISCO SSD / HDD":

                System.out.println("Se busca el dispositivo de arranque.");

                break;

            // El Bootloader es el gestor de arranque.
            // Su funcion es iniciar la carga del sistema operativo.

            case "BOOTLOADER":

                System.out.println("Se localiza el gestor de arranque.");

                break;

            // El Kernel es el nucleo del sistema operativo.
            // Administra recursos de hardware y software.

            case "CARGA DEL KERNEL":

                System.out.println("Se carga el nucleo del sistema operativo.");

                break;

            // Los drivers permiten que el sistema operativo
            // interactue con los dispositivos de hardware.

            case "INICIALIZACION DE DRIVERS":

                System.out.println("Se cargan los controladores.");

                break;

            // Se inician los servicios necesarios
            // para el funcionamiento del sistema operativo.

            case "INICIO DE SERVICIOS":

                System.out.println("Se inician los servicios del sistema.");

                break;

            // Finalmente se prepara la pantalla
            // donde el usuario podra autenticarse.

            case "PANTALLA DE INICIO DE SESION":

                System.out.println("Se prepara la autenticacion del usuario.");

                break;

        }

    }

    // 9. METODO PARA MOSTRAR LOS ERRORES DEL SISTEMA

    // Este metodo recibe el nombre del componente
    // que presenta el problema.
    //
    // Mediante switch identifica el error y muestra
    // las posibles causas que lo originaron.

    public static void mostrarError(String etapa) {

        switch (etapa) {

            case "MEMORIA RAM / DRAM" -> {
                System.out.println("ERROR: Memoria RAM no detectada.");

                System.out.println("Posibles causas:");
                System.out.println("- Modulo RAM mal colocado.");
                System.out.println("- Memoria RAM danada.");
                System.out.println("- Slot de memoria defectuoso.");
                System.out.println("- Problemas de compatibilidad.");
            }

            case "FUENTE DE PODER (PSU)" -> {
                System.out.println("ERROR: No hay suministro electrico.");

                System.out.println("Posibles causas:");
                System.out.println("- Fuente de poder danada.");
                System.out.println("- Cable de alimentacion desconectado.");
            }

            case "POST - Autodiagnostico" -> {
                System.out.println("ERROR: Fallo durante el POST.");

                System.out.println("Posibles causas:");
                System.out.println("- Procesador defectuoso.");
                System.out.println("- Memoria RAM defectuosa.");
                System.out.println("- Problemas en la tarjeta madre.");
            }

            case "TARJETA GRAFICA (VGA / GPU)" -> {
                System.out.println("ERROR: No se obtiene salida de video.");

                System.out.println("Posibles causas:");
                System.out.println("- GPU defectuosa.");
                System.out.println("- Alimentacion de GPU desconectada.");
                System.out.println("- Problema de video integrado.");
            }

            case "DETECCION DE DISCO SSD / HDD" -> {
                System.out.println("ERROR: No se encuentra el disco.");

                System.out.println("Posibles causas:");
                System.out.println("- Disco desconectado.");
                System.out.println("- SSD/HDD danado.");
                System.out.println("- Cable SATA defectuoso.");
            }

            case "BOOTLOADER" -> {
                System.out.println("ERROR: Gestor de arranque danado.");

                System.out.println("Posibles causas:");
                System.out.println("- Archivos de arranque corruptos.");
                System.out.println("- Particion EFI incorrecta.");
                System.out.println("- Configuracion de arranque incorrecta.");
            }

            case "CARGA DEL KERNEL" -> {
                System.out.println("ERROR: No se puede cargar el kernel.");

                System.out.println("Posibles causas:");
                System.out.println("- Archivos del sistema danados.");
                System.out.println("- Kernel incompatible.");
            }

            case "INICIALIZACION DE DRIVERS" -> {
                System.out.println("ERROR: Fallo de controlador.");

                System.out.println("Posibles causas:");
                System.out.println("- Driver incompatible.");
                System.out.println("- Controlador danado.");
            }

            case "INICIO DE SERVICIOS" -> {
                System.out.println("ERROR: Fallo al iniciar un servicio.");

                System.out.println("Posibles causas:");
                System.out.println("- Servicio esencial detenido.");
                System.out.println("- Dependencias incorrectas.");
            }

        }
        // ERROR EN MEMORIA RAM
        // ERROR EN FUENTE DE PODER
        // ERROR DURANTE EL POST
        // ERROR EN TARJETA GRAFICA
        // ERROR EN DISCO DURO O SSD
        // ERROR EN BOOTLOADER
        // ERROR EN KERNEL
        // ERROR EN DRIVERS
        // ERROR EN SERVICIOS

    }

}
