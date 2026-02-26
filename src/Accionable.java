/**
 * Interfaz que define las operaciones comunes para acciones sobre tareas.
 * Implementa el principio de abstracción y polimorfismo permitiendo que
 * diferentes tipos de tareas definan su propio comportamiento.
 * 
 * <p>Esta interfaz permite aplicar el principio de Inversión de Dependencias (DIP)
 * del SOLID, permitiendo que el código dependa de abstracciones y no de implementaciones
 * concretas.</p>
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 */
public interface Accionable {
    
    /**
     * Ejecuta la acción principal asociada a la tarea.
     * Cada tipo de tarea puede implementar esta acción de manera diferente.
     */
    void ejecutar();
    
    /**
     * Cancela la acción de la tarea.
     * Permite revertir o cancelar una tarea según su tipo.
     */
    void cancelar();
    
    /**
     * Valida si la tarea cumple con los requisitos necesarios
     * para ser ejecutada.
     * 
     * @return true si la tarea es válida y puede ejecutarse, false en caso contrario
     */
    boolean validar();
    
    /**
     * Obtiene el nivel de importancia de la tarea.
     * Permite diferenciar entre tareas normales y urgentes.
     * 
     * @return Valor numérico que representa la importancia (ej: 1-10)
     */
    int obtenerNivelImportancia();
    
    /**
     * Genera un resumen descriptivo de la acción que realiza la tarea.
     * 
     * @return String con la descripción de la acción
     */
    String obtenerDescripcionAccion();
}
