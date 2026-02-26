import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del sistema SmartTask.
 * Proporciona un menú interactivo por consola para gestionar tareas.
 * Implementa estructuras de control: if, switch, while.
 * 
 * <p>Esta clase actúa como capa de presentación (UI) y delega toda
 * la lógica de negocio a la clase {@link GestorTareas}.</p>
 *
 * @author Yulieta (Melek) Eyzaguirre
 * @version 2.0
 * @since 2026-02-12
 */
public class Main {
    
    // ==================== ATRIBUTOS ====================

    /**
     * Gestor de tareas que contiene toda la lógica del negocio.
     */
    private static GestorTareas gestorTareas;

    /**
     * Scanner para leer entrada del usuario.
     */
    private static Scanner scanner;

    // ==================== MÉTODO PRINCIPAL ====================

    /**
     * Método principal que inicia la aplicación.
     * Inicializa el gestor y el scanner, luego ejecuta el menú principal.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Inicializar componentes
        gestorTareas = new GestorTareas();
        scanner = new Scanner(System.in);
        boolean continuar = true;
        
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║   BIENVENIDO A SMARTTASK SYSTEM     ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.println();
        
        // Estructura de control WHILE - Bucle principal del programa
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            // Estructura de control SWITCH - Selección de operaciones
            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    listarTareas();
                    break;
                case 3:
                    marcarTareaCompletada();
                    break;
                case 4:
                    eliminarTarea();
                    break;
                case 5:
                    buscarTareaPorId();
                    break;
                case 6:
                    filtrarTareasPorPrioridad();
                    break;
                case 7:
                    mostrarEstadisticas();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\n┌─────────────────────────────────────┐");
                    System.out.println("│ Gracias por usar SmartTask System  │");
                    System.out.println("│        ¡Hasta pronto!              │");
                    System.out.println("└─────────────────────────────────────┘");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Por favor, intente nuevamente.");
            }
            
            // Pausa para que el usuario pueda ver los resultados
            if (continuar) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menú principal con todas las opciones disponibles.
     */
    private static void mostrarMenu() {
        System.out.println("\n╔═════════════════════════════════════╗");
        System.out.println("║          MENÚ PRINCIPAL             ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║ 1. ➕ Agregar Tarea                ║");
        System.out.println("║ 2. 📋 Listar Todas las Tareas      ║");
        System.out.println("║ 3. ✅ Marcar Tarea como Completada ║");
        System.out.println("║ 4. 🗑️  Eliminar Tarea              ║");
        System.out.println("║ 5. 🔍 Buscar Tarea por ID          ║");
        System.out.println("║ 6. 🎯 Filtrar por Prioridad        ║");
        System.out.println("║ 7. 📊 Ver Estadísticas             ║");
        System.out.println("║ 0. 🚪 Salir                        ║");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Lee y valida la opción ingresada por el usuario.
     * 
     * @return El número de opción seleccionado
     */
    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return -1; // Retorna -1 si no es un número válido
        }
    }
    
    /**
     * Agrega una nueva tarea a la lista.
     * Solicita al usuario el nombre y prioridad de la tarea.
     * Delega la creación al {@link GestorTareas}.
     */
    private static void agregarTarea() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       AGREGAR NUEVA TAREA           │");
        System.out.println("└─────────────────────────────────────┘");
        
        System.out.print("Ingrese el nombre de la tarea: ");
        String nombre = scanner.nextLine();
        
        // Estructura IF - Validación de entrada vacía
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("\n❌ Error: El nombre no puede estar vacío.");
            return;
        }
        
        System.out.println("\nSeleccione la prioridad:");
        System.out.println("1. 🟢 Baja");
        System.out.println("2. 🟡 Media");
        System.out.println("3. 🔴 Alta");
        System.out.print("Opción: ");
        
        int prioridad = leerOpcion();
        
        // Estructura IF-ELSE - Validación de prioridad
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("\n❌ Prioridad inválida. Se asignará prioridad baja por defecto.");
            prioridad = 1;
        }
        
        try {
            // Delegar la creación al gestor
            Tarea nuevaTarea = gestorTareas.agregarTarea(nombre, prioridad);
            System.out.println("\n✅ Tarea agregada exitosamente con ID: " + nuevaTarea.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error al agregar tarea: " + e.getMessage());
        }
    }
    
    /**
     * Lista todas las tareas almacenadas en el sistema.
     * Muestra información detallada de cada tarea.
     * Delega la obtención de tareas al {@link GestorTareas}.
     */
    private static void listarTareas() {
        System.out.println("\n┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                    LISTA DE TAREAS                              │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
        
        // Estructura IF-ELSE - Verificar si hay tareas
        if (gestorTareas.estaVacia()) {
            System.out.println("\n📭 No hay tareas registradas en el sistema.");
            return;
        }
        
        List<Tarea> tareas = gestorTareas.listarTareas();
        System.out.println("\nTotal de tareas: " + tareas.size());
        System.out.println("─────────────────────────────────────────────────────────────────");
        
        // Estructura WHILE - Recorrer la lista de tareas
        int index = 0;
        while (index < tareas.size()) {
            Tarea tarea = tareas.get(index);
            System.out.println((index + 1) + ". " + tarea.toString());
            index++; // Operador de incremento
        }
    }
    
    /**
     * Marca una tarea como completada según su ID.
     * Solicita al usuario el ID de la tarea a completar.
     * Delega la operación al {@link GestorTareas}.
     */
    private static void marcarTareaCompletada() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│    MARCAR TAREA COMO COMPLETADA     │");
        System.out.println("└─────────────────────────────────────┘");
        
        // Estructura IF - Verificar si hay tareas
        if (gestorTareas.estaVacia()) {
            System.out.println("\n📭 No hay tareas para marcar.");
            return;
        }
        
        listarTareas();
        
        System.out.print("\nIngrese el ID de la tarea a completar: ");
        int id = leerOpcion();
        
        Tarea tareaEncontrada = gestorTareas.buscarTareaPorId(id);

        // Estructura IF-ELSE - Verificar si se encontró la tarea
        if (tareaEncontrada != null) {
            // Estructura IF-ELSE anidada - Verificar si ya está completada
            if (tareaEncontrada.isCompletado()) {
                System.out.println("\n⚠️ La tarea ya está marcada como completada.");
            } else {
                gestorTareas.marcarComoCompletada(id);
                System.out.println("\n✅ Tarea ID " + id + " marcada como completada.");
            }
        } else {
            System.out.println("\n❌ No se encontró ninguna tarea con el ID: " + id);
        }
    }
    
    /**
     * Elimina una tarea de la lista según su ID.
     * Delega la operación al {@link GestorTareas}.
     */
    private static void eliminarTarea() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         ELIMINAR TAREA              │");
        System.out.println("└─────────────────────────────────────┘");
        
        // Estructura IF - Verificar si hay tareas
        if (gestorTareas.estaVacia()) {
            System.out.println("\n📭 No hay tareas para eliminar.");
            return;
        }
        
        listarTareas();
        
        System.out.print("\nIngrese el ID de la tarea a eliminar: ");
        int id = leerOpcion();
        
        // Estructura IF-ELSE - Verificar si se eliminó la tarea
        if (gestorTareas.eliminarTarea(id)) {
            System.out.println("\n✅ Tarea ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("\n❌ No se encontró ninguna tarea con el ID: " + id);
        }
    }
    
    /**
     * Busca una tarea por su ID y la muestra por consola.
     * Delega la búsqueda al {@link GestorTareas}.
     */
    private static void buscarTareaPorId() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       BUSCAR TAREA POR ID           │");
        System.out.println("└─────────────────────────────────────┘");
        
        System.out.print("\nIngrese el ID de la tarea: ");
        int id = leerOpcion();
        
        Tarea tareaEncontrada = gestorTareas.buscarTareaPorId(id);

        // Estructura IF-ELSE - Mostrar resultado de búsqueda
        if (tareaEncontrada != null) {
            System.out.println("\n✅ Tarea encontrada:");
            System.out.println(tareaEncontrada.toString());
        } else {
            System.out.println("\n❌ No se encontró ninguna tarea con el ID: " + id);
        }
    }
    
    /**
     * Filtra y muestra las tareas según el nivel de prioridad.
     * Delega el filtrado al {@link GestorTareas}.
     */
    private static void filtrarTareasPorPrioridad() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│    FILTRAR POR PRIORIDAD            │");
        System.out.println("└─────────────────────────────────────┘");
        
        System.out.println("\nSeleccione la prioridad:");
        System.out.println("1. 🟢 Baja");
        System.out.println("2. 🟡 Media");
        System.out.println("3. 🔴 Alta");
        System.out.print("Opción: ");
        
        int prioridad = leerOpcion();
        
        // Estructura IF - Validar prioridad
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("\n❌ Prioridad inválida.");
            return;
        }
        
        List<Tarea> tareasFiltradas = gestorTareas.filtrarPorPrioridad(prioridad);

        System.out.println("\n📋 Tareas con prioridad " + GestorTareas.obtenerTextoPrioridad(prioridad) + ":");
        System.out.println("─────────────────────────────────────────────────────────────────");
        
        // Estructura IF-ELSE - Mostrar resultado
        if (tareasFiltradas.isEmpty()) {
            System.out.println("No se encontraron tareas con esta prioridad.");
        } else {
            int index = 0;
            // Estructura WHILE - Mostrar tareas filtradas
            while (index < tareasFiltradas.size()) {
                System.out.println(tareasFiltradas.get(index).toString());
                index++;
            }
            System.out.println("\nTotal encontradas: " + tareasFiltradas.size());
        }
    }
    
    /**
     * Muestra estadísticas generales sobre las tareas.
     * Calcula y muestra diversos indicadores.
     * Delega los cálculos al {@link GestorTareas}.
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        ESTADÍSTICAS                 │");
        System.out.println("└─────────────────────────────────────┘");
        
        int total = gestorTareas.obtenerTotalTareas();

        // Estructura IF - Verificar si hay tareas
        if (total == 0) {
            System.out.println("\n📭 No hay tareas registradas.");
            return;
        }
        
        int completadas = gestorTareas.contarTareasCompletadas();
        int pendientes = gestorTareas.contarTareasPendientes();
        int prioridadBaja = gestorTareas.contarPorPrioridad(GestorTareas.PRIORIDAD_BAJA);
        int prioridadMedia = gestorTareas.contarPorPrioridad(GestorTareas.PRIORIDAD_MEDIA);
        int prioridadAlta = gestorTareas.contarPorPrioridad(GestorTareas.PRIORIDAD_ALTA);

        // Uso de operadores aritméticos para calcular porcentajes
        double porcentajeCompletadas = gestorTareas.calcularPorcentajeCompletadas();
        double porcentajePendientes = gestorTareas.calcularPorcentajePendientes();

        System.out.println("\n📊 Resumen General:");
        System.out.println("   Total de tareas: " + total);
        System.out.println("   ✅ Completadas: " + completadas + " (" + String.format("%.1f", porcentajeCompletadas) + "%%)");
        System.out.println("   ⏳ Pendientes: " + pendientes + " (" + String.format("%.1f", porcentajePendientes) + "%%)");
        
        System.out.println("\n🎯 Por Prioridad:");
        System.out.println("   🟢 Baja: " + prioridadBaja);
        System.out.println("   🟡 Media: " + prioridadMedia);
        System.out.println("   🔴 Alta: " + prioridadAlta);
    }
}
