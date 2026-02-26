import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa una Tarea Urgente en el sistema SmartTask.
 * Extiende de {@link Tarea} e implementa la interfaz {@link Accionable}.
 * 
 * <p>Las tareas urgentes requieren atención inmediata y tienen características
 * especiales como fecha límite y notificaciones automáticas.</p>
 * 
 * <p>Esta clase demuestra el uso de <strong>Herencia</strong>, <strong>Polimorfismo</strong>
 * y <strong>Sobrescritura de métodos</strong> en el paradigma de Orientación a Objetos.</p>
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 * @see Tarea
 * @see Accionable
 */
public class TareaUrgente extends Tarea implements Accionable {
    
    // ==================== CONSTANTES ====================

    /**
     * Nivel mínimo de criticidad permitido.
     */
    public static final int CRITICIDAD_MINIMA = 1;

    /**
     * Nivel máximo de criticidad permitido.
     */
    public static final int CRITICIDAD_MAXIMA = 10;

    /**
     * Nivel de criticidad por defecto.
     */
    public static final int CRITICIDAD_DEFECTO = 8;

    // ==================== ATRIBUTOS ADICIONALES ====================
    
    /**
     * Fecha y hora límite para completar la tarea urgente.
     */
    private LocalDateTime fechaLimite;
    
    /**
     * Indica si se deben enviar notificaciones para esta tarea.
     */
    private boolean notificacionesActivas;
    
    /**
     * Nivel de criticidad adicional (escala 1-10).
     */
    private int nivelCriticidad;
    
    /**
     * Persona o departamento responsable de la tarea urgente.
     */
    private String responsable;
    
    // ==================== CONSTRUCTORES ====================
    
    /**
     * Constructor por defecto.
     * Llama al constructor padre e inicializa valores específicos de TareaUrgente.
     */
    public TareaUrgente() {
        super();
        this.prioridad = 3; // Las tareas urgentes tienen prioridad Alta por defecto
        this.fechaLimite = LocalDateTime.now().plusDays(1);
        this.notificacionesActivas = true;
        this.nivelCriticidad = CRITICIDAD_DEFECTO;
        this.responsable = "Sin asignar";
    }
    
    /**
     * Constructor con parámetros básicos.
     * 
     * @param id Identificador único de la tarea
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad (generalmente 3 para urgentes)
     */
    public TareaUrgente(int id, String nombre, int prioridad) {
        super(id, nombre, prioridad);
        this.fechaLimite = LocalDateTime.now().plusDays(1);
        this.notificacionesActivas = true;
        this.nivelCriticidad = CRITICIDAD_DEFECTO;
        this.responsable = "Sin asignar";
    }
    
    /**
     * Constructor completo con todos los parámetros.
     * 
     * @param id Identificador único de la tarea
     * @param nombre Descripción de la tarea
     * @param prioridad Nivel de prioridad
     * @param fechaLimite Fecha y hora límite
     * @param nivelCriticidad Nivel de criticidad (1-10)
     * @param responsable Persona responsable
     */
    public TareaUrgente(int id, String nombre, int prioridad, 
                       LocalDateTime fechaLimite, int nivelCriticidad, String responsable) {
        super(id, nombre, prioridad);
        this.fechaLimite = fechaLimite;
        this.notificacionesActivas = true;
        this.nivelCriticidad = nivelCriticidad;
        this.responsable = responsable;
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    /**
     * Obtiene la fecha límite de la tarea.
     * 
     * @return La fecha y hora límite
     */
    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }
    
    /**
     * Establece la fecha límite de la tarea.
     * 
     * @param fechaLimite La nueva fecha límite
     */
    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    /**
     * Verifica si las notificaciones están activas.
     * 
     * @return true si las notificaciones están activas
     */
    public boolean isNotificacionesActivas() {
        return notificacionesActivas;
    }
    
    /**
     * Activa o desactiva las notificaciones.
     * 
     * @param notificacionesActivas El nuevo estado de notificaciones
     */
    public void setNotificacionesActivas(boolean notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
    }
    
    /**
     * Obtiene el nivel de criticidad.
     * 
     * @return El nivel de criticidad (1-10)
     */
    public int getNivelCriticidad() {
        return nivelCriticidad;
    }
    
    /**
     * Establece el nivel de criticidad.
     * 
     * @param nivelCriticidad El nuevo nivel de criticidad (1-10)
     * @throws IllegalArgumentException si el nivel no está entre CRITICIDAD_MINIMA y CRITICIDAD_MAXIMA
     */
    public void setNivelCriticidad(int nivelCriticidad) {
        if (nivelCriticidad < CRITICIDAD_MINIMA || nivelCriticidad > CRITICIDAD_MAXIMA) {
            throw new IllegalArgumentException(
                "El nivel de criticidad debe estar entre " + CRITICIDAD_MINIMA + " y " + CRITICIDAD_MAXIMA
            );
        }
        this.nivelCriticidad = nivelCriticidad;
    }
    
    /**
     * Obtiene el responsable de la tarea.
     * 
     * @return El nombre del responsable
     */
    public String getResponsable() {
        return responsable;
    }
    
    /**
     * Establece el responsable de la tarea.
     * 
     * @param responsable El nuevo responsable
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
    // ==================== IMPLEMENTACIÓN DE ACCIONABLE ====================
    
    /**
     * Ejecuta la tarea urgente con notificaciones especiales.
     * Demuestra polimorfismo mediante implementación de interfaz.
     */
    @Override
    public void ejecutar() {
        System.out.println("🚨 EJECUTANDO TAREA URGENTE: " + this.nombre);
        System.out.println("   ⚠️  Nivel de Criticidad: " + this.nivelCriticidad + "/10");
        System.out.println("   👤 Responsable: " + this.responsable);
        System.out.println("   ⏰ Fecha Límite: " + formatearFecha());
        System.out.println("   🔔 Notificaciones: " + (this.notificacionesActivas ? "ACTIVAS" : "Desactivadas"));
        
        if (esVencida()) {
            System.out.println("   ❗ ALERTA: Esta tarea está VENCIDA");
        }
    }
    
    /**
     * Cancela la tarea urgente con registro en log.
     */
    @Override
    public void cancelar() {
        System.out.println("🚫 CANCELANDO TAREA URGENTE: " + this.nombre);
        System.out.println("   Registrando cancelación en el sistema...");
        this.marcarPendiente();
        this.notificacionesActivas = false;
    }
    
    /**
     * Valida si la tarea urgente cumple con todos los requisitos.
     * 
     * @return true si la tarea es válida y tiene todos los datos necesarios
     */
    @Override
    public boolean validar() {
        boolean validacionBasica = this.nombre != null && 
                                  !this.nombre.trim().isEmpty() && 
                                  this.esPrioridadValida();
        
        boolean validacionUrgente = this.fechaLimite != null && 
                                   this.nivelCriticidad >= CRITICIDAD_MINIMA &&
                                   this.nivelCriticidad <= CRITICIDAD_MAXIMA;

        return validacionBasica && validacionUrgente;
    }
    
    /**
     * Obtiene el nivel de importancia de la tarea urgente.
     * Las tareas urgentes tienen mayor importancia (escala 7-10).
     * 
     * @return Nivel de importancia de 7 a 10
     */
    @Override
    public int obtenerNivelImportancia() {
        // Las tareas urgentes tienen mayor importancia
        // Se mapea el nivel de criticidad (1-10) a importancia (7-10)
        return 6 + (this.nivelCriticidad / 3);
    }
    
    /**
     * Genera una descripción detallada de la acción urgente.
     * 
     * @return Descripción de la acción con indicadores de urgencia
     */
    @Override
    public String obtenerDescripcionAccion() {
        return "🚨 TAREA URGENTE - " + this.nombre + 
               " | Criticidad: " + this.nivelCriticidad + "/10" +
               " | Responsable: " + this.responsable +
               " | Vence: " + formatearFecha();
    }
    
    // ==================== MÉTODOS ADICIONALES ====================
    
    /**
     * Verifica si la tarea ha superado su fecha límite.
     * 
     * @return true si la tarea está vencida
     */
    public boolean esVencida() {
        return LocalDateTime.now().isAfter(fechaLimite);
    }
    
    /**
     * Calcula las horas restantes hasta la fecha límite.
     * 
     * @return Cantidad de horas restantes (puede ser negativo si está vencida)
     */
    public long horasRestantes() {
        return java.time.Duration.between(LocalDateTime.now(), fechaLimite).toHours();
    }
    
    /**
     * Formatea la fecha límite en un formato legible.
     * 
     * @return Fecha formateada como String
     */
    private String formatearFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaLimite.format(formatter);
    }
    
    /**
     * Envía una notificación de recordatorio.
     */
    public void enviarNotificacion() {
        if (notificacionesActivas) {
            System.out.println("🔔 NOTIFICACIÓN: Tarea urgente '" + this.nombre + "' requiere atención");
            System.out.println("   Tiempo restante: " + horasRestantes() + " horas");
        }
    }
    
    // ==================== MÉTODOS SOBRESCRITOS ====================
    
    /**
     * Representación en cadena de la tarea urgente.
     * Sobrescribe el método toString() de la clase padre.
     * Demuestra polimorfismo mediante sobrescritura (override).
     * 
     * @return Representación en String de la tarea urgente
     */
    @Override
    public String toString() {
        String estado = esVencida() ? "⚠️ VENCIDA" : "✅ VIGENTE";
        return super.toString() + 
               " | Criticidad: " + this.nivelCriticidad + "/10" +
               " | Responsable: " + this.responsable +
               " | Vence: " + formatearFecha() +
               " | Estado: " + estado +
               " | Tipo: URGENTE 🚨";
    }
}
