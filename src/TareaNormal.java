/**
 * Clase que representa una Tarea Normal en el sistema SmartTask.
 * Extiende de {@link Tarea} e implementa la interfaz {@link Accionable}.
 * 
 * <p>Las tareas normales tienen un proceso de ejecución estándar y no requieren
 * atención inmediata. Se procesan en el orden regular de prioridad.</p>
 * 
 * <p>Esta clase demuestra el uso de <strong>Herencia</strong> y <strong>Polimorfismo</strong>
 * en el paradigma de Orientación a Objetos.</p>
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 * @see Tarea
 * @see Accionable
 */
public class TareaNormal extends Tarea implements Accionable {
    
    // ==================== ATRIBUTOS ADICIONALES ====================
    
    /**
     * Categoría de la tarea normal (ej: "Personal", "Trabajo", "Estudio").
     */
    private String categoria;
    
    /**
     * Tiempo estimado de ejecución en minutos.
     */
    private int tiempoEstimado;
    
    // ==================== CONSTRUCTORES ====================
    
    /**
     * Constructor por defecto.
     * Llama al constructor padre e inicializa valores específicos de TareaNormal.
     */
    public TareaNormal() {
        super();
        this.categoria = "General";
        this.tiempoEstimado = 30; // 30 minutos por defecto
    }
    
    /**
     * Constructor con parámetros básicos.
     * 
     * @param id Identificador único de la tarea
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad (1: Baja, 2: Media, 3: Alta)
     */
    public TareaNormal(int id, String nombre, int prioridad) {
        super(id, nombre, prioridad);
        this.categoria = "General";
        this.tiempoEstimado = 30;
    }
    
    /**
     * Constructor completo con todos los parámetros.
     * 
     * @param id Identificador único de la tarea
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad (1: Baja, 2: Media, 3: Alta)
     * @param categoria Categoría de la tarea
     * @param tiempoEstimado Tiempo estimado en minutos
     */
    public TareaNormal(int id, String nombre, int prioridad, String categoria, int tiempoEstimado) {
        super(id, nombre, prioridad);
        this.categoria = categoria;
        this.tiempoEstimado = tiempoEstimado;
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    /**
     * Obtiene la categoría de la tarea.
     * 
     * @return La categoría de la tarea
     */
    public String getCategoria() {
        return categoria;
    }
    
    /**
     * Establece la categoría de la tarea.
     * 
     * @param categoria La nueva categoría
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Obtiene el tiempo estimado de ejecución.
     * 
     * @return El tiempo estimado en minutos
     */
    public int getTiempoEstimado() {
        return tiempoEstimado;
    }
    
    /**
     * Establece el tiempo estimado de ejecución.
     * 
     * @param tiempoEstimado El nuevo tiempo estimado en minutos
     * @throws IllegalArgumentException si el tiempo es menor o igual a 0
     */
    public void setTiempoEstimado(int tiempoEstimado) {
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException("El tiempo estimado debe ser mayor a 0");
        }
        this.tiempoEstimado = tiempoEstimado;
    }
    
    // ==================== IMPLEMENTACIÓN DE ACCIONABLE ====================
    
    /**
     * Ejecuta la tarea normal.
     * Este método demuestra polimorfismo en tiempo de ejecución.
     */
    @Override
    public void ejecutar() {
        System.out.println("📋 Ejecutando tarea normal: " + this.nombre);
        System.out.println("   Categoría: " + this.categoria);
        System.out.println("   Tiempo estimado: " + this.tiempoEstimado + " minutos");
        System.out.println("   Prioridad: " + obtenerTextoPrioridad());
    }
    
    /**
     * Cancela la tarea normal.
     * Permite revertir el estado de la tarea.
     */
    @Override
    public void cancelar() {
        System.out.println("❌ Cancelando tarea normal: " + this.nombre);
        this.marcarPendiente();
    }
    
    /**
     * Valida si la tarea normal es válida para ejecutarse.
     * 
     * @return true si la tarea tiene nombre válido y prioridad correcta
     */
    @Override
    public boolean validar() {
        return this.nombre != null && 
               !this.nombre.trim().isEmpty() && 
               this.esPrioridadValida();
    }
    
    /**
     * Obtiene el nivel de importancia de la tarea normal.
     * Las tareas normales tienen importancia basada en su prioridad (1-3).
     * 
     * @return Nivel de importancia de 1 a 3
     */
    @Override
    public int obtenerNivelImportancia() {
        return this.prioridad; // Nivel 1-3 para tareas normales
    }
    
    /**
     * Genera una descripción de la acción que realiza esta tarea.
     * 
     * @return Descripción de la acción
     */
    @Override
    public String obtenerDescripcionAccion() {
        return "Tarea Normal - " + this.nombre + 
               " [" + this.categoria + "] - " + 
               this.tiempoEstimado + " min";
    }
    
    // ==================== MÉTODOS SOBRESCRITOS ====================
    
    /**
     * Representación en cadena de la tarea normal.
     * Sobrescribe el método toString() de la clase padre para incluir información adicional.
     * Demuestra polimorfismo mediante sobrescritura (override).
     * 
     * @return Representación en String de la tarea normal
     */
    @Override
    public String toString() {
        return super.toString() + 
               " | Categoría: " + this.categoria + 
               " | Tiempo: " + this.tiempoEstimado + " min" +
               " | Tipo: NORMAL";
    }
}
