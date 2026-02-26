/**
 * Clase que representa una Tarea en el sistema SmartTask.
 * Esta clase encapsula la información de una tarea individual con sus atributos
 * y métodos para gestionar su estado.
 * 
 * @author Yulieta (Melek)Eyzaguirre
 * @version 1.0
 * @since 2026-02-09
 */
public class Tarea {
    
    // ==================== ATRIBUTOS ====================
    
    /**
     * Identificador único de la tarea.
     * Modificador protected permite acceso a clases hijas.
     */
    protected int id;
    
    /**
     * Nombre o descripción de la tarea.
     * Modificador protected permite acceso a clases hijas.
     */
    protected String nombre;
    
    /**
     * Nivel de prioridad de la tarea.
     * Valores válidos: 1 (Baja), 2 (Media), 3 (Alta)
     * Modificador protected permite acceso a clases hijas.
     */
    protected int prioridad;
    
    /**
     * Estado de completitud de la tarea.
     * true si está completada, false si está pendiente.
     * Modificador protected permite acceso a clases hijas.
     */
    protected boolean completado;
    
    // ==================== CONSTRUCTORES ====================
    
    /**
     * Constructor por defecto.
     * Inicializa una tarea vacía con valores predeterminados.
     */
    public Tarea() {
        this.id = 0;
        this.nombre = "";
        this.prioridad = 1;
        this.completado = false;
    }
    
    /**
     * Constructor con parámetros.
     * Crea una tarea con los valores especificados.
     * 
     * @param id Identificador único de la tarea
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad (1: Baja, 2: Media, 3: Alta)
     * @param completado Estado de completitud de la tarea
     */
    public Tarea(int id, String nombre, int prioridad, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.completado = completado;
    }
    
    /**
     * Constructor simplificado para crear una tarea nueva.
     * La tarea se crea como no completada por defecto.
     * 
     * @param id Identificador único de la tarea
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad (1: Baja, 2: Media, 3: Alta)
     */
    public Tarea(int id, String nombre, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.completado = false;
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    /**
     * Obtiene el ID de la tarea.
     * 
     * @return El identificador único de la tarea
     */
    public int getId() {
        return id;
    }
    
    /**
     * Establece el ID de la tarea.
     * 
     * @param id El nuevo identificador de la tarea
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Obtiene el nombre de la tarea.
     * 
     * @return La descripción de la tarea
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre de la tarea.
     * Valida que el nombre no sea nulo ni vacío.
     *
     * @param nombre La nueva descripción de la tarea
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la tarea no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }
    
    /**
     * Obtiene la prioridad de la tarea.
     * 
     * @return El nivel de prioridad (1: Baja, 2: Media, 3: Alta)
     */
    public int getPrioridad() {
        return prioridad;
    }
    
    /**
     * Establece la prioridad de la tarea.
     * Valida que el valor esté en el rango permitido (1-3).
     *
     * @param prioridad El nuevo nivel de prioridad
     * @throws IllegalArgumentException si la prioridad no está en el rango 1-3
     */
    public void setPrioridad(int prioridad) {
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3");
        }
        this.prioridad = prioridad;
    }
    
    /**
     * Verifica si la tarea está completada.
     * 
     * @return true si la tarea está completada, false en caso contrario
     */
    public boolean isCompletado() {
        return completado;
    }
    
    /**
     * Establece el estado de completitud de la tarea.
     * 
     * @param completado El nuevo estado de la tarea
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
    
    // ==================== MÉTODOS ADICIONALES ====================
    
    /**
     * Marca la tarea como completada.
     * Cambia el estado de completado a true.
     */
    public void marcarCompletada() {
        this.completado = true;
    }
    
    /**
     * Marca la tarea como pendiente.
     * Cambia el estado de completado a false.
     */
    public void marcarPendiente() {
        this.completado = false;
    }
    
    /**
     * Obtiene el texto descriptivo de la prioridad.
     * Convierte el valor numérico en una descripción legible.
     * 
     * @return String con el nivel de prioridad ("Baja", "Media" o "Alta")
     */
    public String obtenerTextoPrioridad() {
        // Uso de estructura switch
        switch (this.prioridad) {
            case 1:
                return "Baja";
            case 2:
                return "Media";
            case 3:
                return "Alta";
            default:
                return "No definida";
        }
    }
    
    /**
     * Obtiene el símbolo que representa el estado de la tarea.
     * 
     * @return String con el símbolo de estado "[X]" si está completada o "[ ]" si está pendiente
     */
    public String obtenerSimboloEstado() {
        // Uso de operador ternario
        return this.completado ? "[X]" : "[ ]";
    }
    
    /**
     * Valida si la prioridad está en el rango correcto.
     * 
     * @return true si la prioridad es válida (1-3), false en caso contrario
     */
    public boolean esPrioridadValida() {
        // Uso de operadores lógicos y relacionales
        return this.prioridad >= 1 && this.prioridad <= 3;
    }
    
    /**
     * Convierte la información de la tarea en una cadena de texto legible.
     * 
     * @return String con la representación formateada de la tarea
     */
    @Override
    public String toString() {
        // Uso de operador +  para concatenación
        return obtenerSimboloEstado() + " ID: " + id + " | " + nombre + 
               " | Prioridad: " + obtenerTextoPrioridad() + 
               " | Estado: " + (completado ? "Completada" : "Pendiente");
    }
}
