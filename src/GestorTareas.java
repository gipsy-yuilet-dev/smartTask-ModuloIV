import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase gestora que encapsula toda la lógica de negocio para el manejo de tareas.
 * Implementa el patrón de diseño Singleton y proporciona métodos para realizar
 * operaciones CRUD sobre las tareas del sistema.
 *
 * <p>Esta clase se encarga de:</p>
 * <ul>
 *   <li>Gestionar el ciclo de vida de las tareas</li>
 *   <li>Mantener un registro único de IDs</li>
 *   <li>Proporcionar métodos de búsqueda y filtrado</li>
 *   <li>Calcular estadísticas del sistema</li>
 * </ul>
 *
 * @author Yulieta (Melek) Eyzaguirre
 * @version 2.0
 * @since 2026-02-12
 */
public class GestorTareas {

    // ==================== CONSTANTES ====================

    /**
     * Prioridad baja para tareas.
     */
    public static final int PRIORIDAD_BAJA = 1;

    /**
     * Prioridad media para tareas.
     */
    public static final int PRIORIDAD_MEDIA = 2;

    /**
     * Prioridad alta para tareas.
     */
    public static final int PRIORIDAD_ALTA = 3;

    // ==================== ATRIBUTOS ====================

    /**
     * Lista que almacena todas las tareas del sistema.
     */
    private final List<Tarea> listaTareas;

    /**
     * Contador para generar IDs únicos automáticamente.
     */
    private int contadorId;

    // ==================== CONSTRUCTORES ====================

    /**
     * Constructor que inicializa el gestor de tareas.
     * Crea una lista vacía y establece el contador de IDs en 1.
     */
    public GestorTareas() {
        this.listaTareas = new ArrayList<>();
        this.contadorId = 1;
    }

    // ==================== MÉTODOS PÚBLICOS - OPERACIONES CRUD ====================

    /**
     * Agrega una nueva tarea al sistema.
     * El ID se asigna automáticamente de forma incremental.
     *
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad (1: Baja, 2: Media, 3: Alta)
     * @return La tarea creada
     * @throws IllegalArgumentException si el nombre es nulo/vacío o la prioridad es inválida
     */
    public Tarea agregarTarea(String nombre, int prioridad) {
        // Validación de entrada
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea no puede estar vacío");
        }

        if (prioridad < PRIORIDAD_BAJA || prioridad > PRIORIDAD_ALTA) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3");
        }

        // Crear nueva tarea con ID autoincremental
        Tarea nuevaTarea = new Tarea(contadorId++, nombre.trim(), prioridad);
        listaTareas.add(nuevaTarea);

        return nuevaTarea;
    }

    /**
     * Marca una tarea como completada según su ID.
     *
     * @param id Identificador único de la tarea
     * @return true si la tarea fue marcada exitosamente, false si no se encontró
     */
    public boolean marcarComoCompletada(int id) {
        Tarea tarea = buscarTareaPorId(id);

        if (tarea != null) {
            tarea.marcarCompletada();
            return true;
        }

        return false;
    }

    /**
     * Elimina una tarea del sistema según su ID.
     *
     * @param id Identificador único de la tarea a eliminar
     * @return true si la tarea fue eliminada exitosamente, false si no se encontró
     */
    public boolean eliminarTarea(int id) {
        Tarea tarea = buscarTareaPorId(id);

        if (tarea != null) {
            listaTareas.remove(tarea);
            return true;
        }

        return false;
    }

    // ==================== MÉTODOS DE CONSULTA ====================

    /**
     * Obtiene una copia de la lista completa de tareas.
     * Se devuelve una copia para mantener el encapsulamiento.
     *
     * @return Lista inmutable con todas las tareas
     */
    public List<Tarea> listarTareas() {
        return new ArrayList<>(listaTareas);
    }

    /**
     * Busca una tarea específica por su ID.
     *
     * @param id Identificador único de la tarea
     * @return La tarea encontrada o null si no existe
     */
    public Tarea buscarTareaPorId(int id) {
        for (Tarea tarea : listaTareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    /**
     * Filtra y obtiene todas las tareas con una prioridad específica.
     *
     * @param prioridad Nivel de prioridad a filtrar (1: Baja, 2: Media, 3: Alta)
     * @return Lista con las tareas que coinciden con la prioridad
     */
    public List<Tarea> filtrarPorPrioridad(int prioridad) {
        return listaTareas.stream()
            .filter(tarea -> tarea.getPrioridad() == prioridad)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las tareas completadas.
     *
     * @return Lista con las tareas completadas
     */
    public List<Tarea> obtenerTareasCompletadas() {
        return listaTareas.stream()
            .filter(Tarea::isCompletado)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las tareas pendientes.
     *
     * @return Lista con las tareas pendientes
     */
    public List<Tarea> obtenerTareasPendientes() {
        return listaTareas.stream()
            .filter(tarea -> !tarea.isCompletado())
            .collect(Collectors.toList());
    }

    // ==================== MÉTODOS DE ESTADÍSTICAS ====================

    /**
     * Obtiene el total de tareas en el sistema.
     *
     * @return Número total de tareas
     */
    public int obtenerTotalTareas() {
        return listaTareas.size();
    }

    /**
     * Obtiene el número de tareas completadas.
     *
     * @return Cantidad de tareas completadas
     */
    public int contarTareasCompletadas() {
        return (int) listaTareas.stream()
            .filter(Tarea::isCompletado)
            .count();
    }

    /**
     * Obtiene el número de tareas pendientes.
     *
     * @return Cantidad de tareas pendientes
     */
    public int contarTareasPendientes() {
        return (int) listaTareas.stream()
            .filter(tarea -> !tarea.isCompletado())
            .count();
    }

    /**
     * Cuenta las tareas según su nivel de prioridad.
     *
     * @param prioridad Nivel de prioridad a contar
     * @return Cantidad de tareas con la prioridad especificada
     */
    public int contarPorPrioridad(int prioridad) {
        return (int) listaTareas.stream()
            .filter(tarea -> tarea.getPrioridad() == prioridad)
            .count();
    }

    /**
     * Calcula el porcentaje de tareas completadas.
     *
     * @return Porcentaje de completitud (0.0 a 100.0)
     */
    public double calcularPorcentajeCompletadas() {
        if (listaTareas.isEmpty()) {
            return 0.0;
        }

        return (contarTareasCompletadas() * 100.0) / listaTareas.size();
    }

    /**
     * Calcula el porcentaje de tareas pendientes.
     *
     * @return Porcentaje de tareas pendientes (0.0 a 100.0)
     */
    public double calcularPorcentajePendientes() {
        if (listaTareas.isEmpty()) {
            return 0.0;
        }

        return (contarTareasPendientes() * 100.0) / listaTareas.size();
    }

    // ==================== MÉTODOS DE UTILIDAD ====================

    /**
     * Verifica si la lista de tareas está vacía.
     *
     * @return true si no hay tareas, false en caso contrario
     */
    public boolean estaVacia() {
        return listaTareas.isEmpty();
    }

    /**
     * Limpia todas las tareas del sistema.
     * Reinicia el contador de IDs a 1.
     */
    public void limpiarTodas() {
        listaTareas.clear();
        contadorId = 1;
    }

    /**
     * Convierte un número de prioridad en su texto descriptivo.
     *
     * @param prioridad Valor numérico de la prioridad
     * @return Texto descriptivo de la prioridad
     */
    public static String obtenerTextoPrioridad(int prioridad) {
        switch (prioridad) {
            case PRIORIDAD_BAJA:
                return "Baja";
            case PRIORIDAD_MEDIA:
                return "Media";
            case PRIORIDAD_ALTA:
                return "Alta";
            default:
                return "No definida";
        }
    }

    /**
     * Obtiene el símbolo emoji según el nivel de prioridad.
     *
     * @param prioridad Valor numérico de la prioridad
     * @return Emoji representativo de la prioridad
     */
    public static String obtenerEmojPrioridad(int prioridad) {
        switch (prioridad) {
            case PRIORIDAD_BAJA:
                return "🟢";
            case PRIORIDAD_MEDIA:
                return "🟡";
            case PRIORIDAD_ALTA:
                return "🔴";
            default:
                return "⚪";
        }
    }
}
