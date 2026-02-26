import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de demostración para mostrar el uso de Polimorfismo, Herencia e Interfaces
 * en el sistema SmartTask.
 * 
 * <p>Esta clase NO es parte del flujo principal del programa, es únicamente
 * para demostración académica de los conceptos de POO.</p>
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 */
public class DemoPolimorfismo {
    
    /**
     * Método principal de demostración.
     * Este método NO se ejecuta en el flujo normal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  DEMOSTRACIÓN DE POLIMORFISMO Y HERENCIA - SMARTTASK     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // ========== DEMOSTRACIÓN 1: POLIMORFISMO CON LISTA DE TAREAS ==========
        System.out.println("═══ 1. POLIMORFISMO: Lista polimórfica de Tareas ═══\n");
        
        // Lista polimórfica: puede contener objetos de tipo Tarea y sus subclases
        List<Tarea> listaTareas = new ArrayList<>();
        
        // Agregar diferentes tipos de tareas a la misma lista
        listaTareas.add(new TareaNormal(1, "Revisar documentación", 1, "Estudio", 45));
        listaTareas.add(new TareaNormal(2, "Comprar víveres", 2, "Personal", 60));
        listaTareas.add(new TareaUrgente(3, "Entregar proyecto final", 3, 
                                        LocalDateTime.now().plusHours(5), 9, "Juan Pérez"));
        listaTareas.add(new TareaUrgente(4, "Resolver incidente crítico", 3,
                                        LocalDateTime.now().plusHours(2), 10, "María González"));
        
        // Recorrer la lista y mostrar información (toString polimórfico)
        System.out.println("📋 Listado de tareas (toString polimórfico):\n");
        for (Tarea tarea : listaTareas) {
            // Llamada polimórfica: cada tipo de tarea usa su propia versión de toString()
            System.out.println(tarea.toString());
            System.out.println();
        }
        
        // ========== DEMOSTRACIÓN 2: POLIMORFISMO CON INTERFAZ ACCIONABLE ==========
        System.out.println("\n═══ 2. POLIMORFISMO: Interfaz Accionable ═══\n");
        
        // Lista de objetos que implementan Accionable
        List<Accionable> tareasAccionables = new ArrayList<>();
        
        tareasAccionables.add(new TareaNormal(5, "Leer capítulo 5", 1, "Estudio", 30));
        tareasAccionables.add(new TareaUrgente(6, "Llamar al cliente", 3,
                                              LocalDateTime.now().plusHours(1), 8, "Carlos Ruiz"));
        
        System.out.println("🎯 Ejecutando tareas usando la interfaz Accionable:\n");
        for (Accionable tarea : tareasAccionables) {
            // Polimorfismo: cada tipo ejecuta su propia versión de los métodos
            System.out.println("Nivel de importancia: " + tarea.obtenerNivelImportancia());
            System.out.println("Descripción: " + tarea.obtenerDescripcionAccion());
            System.out.println("Validación: " + (tarea.validar() ? "✅ VÁLIDA" : "❌ INVÁLIDA"));
            tarea.ejecutar();
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println();
        }
        
        // ========== DEMOSTRACIÓN 3: HERENCIA Y CASTING ==========
        System.out.println("\n═══ 3. HERENCIA: Casting y verificación de tipos ═══\n");
        
        for (Tarea tarea : listaTareas) {
            System.out.println("Analizando tarea ID " + tarea.getId() + ": " + tarea.getNombre());
            
            // Uso de instanceof para verificar el tipo real del objeto
            if (tarea instanceof TareaUrgente) {
                // Downcasting a TareaUrgente para acceder a métodos específicos
                TareaUrgente tareaUrg = (TareaUrgente) tarea;
                System.out.println("  🚨 Tipo: URGENTE");
                System.out.println("  ⏰ Horas restantes: " + tareaUrg.horasRestantes());
                System.out.println("  ⚠️  Criticidad: " + tareaUrg.getNivelCriticidad() + "/10");
                System.out.println("  👤 Responsable: " + tareaUrg.getResponsable());
                
                if (tareaUrg.esVencida()) {
                    System.out.println("  ❗ ALERTA: TAREA VENCIDA");
                }
                
            } else if (tarea instanceof TareaNormal) {
                // Downcasting a TareaNormal
                TareaNormal tareaNorm = (TareaNormal) tarea;
                System.out.println("  📋 Tipo: NORMAL");
                System.out.println("  📁 Categoría: " + tareaNorm.getCategoria());
                System.out.println("  ⏱️  Tiempo estimado: " + tareaNorm.getTiempoEstimado() + " min");
            }
            
            System.out.println();
        }
        
        // ========== DEMOSTRACIÓN 4: SOBRESCRITURA DE MÉTODOS ==========
        System.out.println("\n═══ 4. SOBRESCRITURA: Comparación de comportamientos ═══\n");
        
        TareaNormal tareaSimple = new TareaNormal(7, "Organizar escritorio", 1, "Personal", 20);
        TareaUrgente tareaUrgente = new TareaUrgente(8, "Presentación ejecutiva", 3,
                                                     LocalDateTime.now().plusHours(3), 10, "Director");
        
        System.out.println("Comparando comportamiento de los métodos sobrescritos:\n");
        
        System.out.println("--- TareaNormal ---");
        System.out.println("toString(): " + tareaSimple.toString());
        System.out.println("obtenerNivelImportancia(): " + tareaSimple.obtenerNivelImportancia());
        System.out.println();
        
        System.out.println("--- TareaUrgente ---");
        System.out.println("toString(): " + tareaUrgente.toString());
        System.out.println("obtenerNivelImportancia(): " + tareaUrgente.obtenerNivelImportancia());
        System.out.println();
        
        // ========== DEMOSTRACIÓN 5: PRINCIPIO DE RESPONSABILIDAD ÚNICA ==========
        System.out.println("\n═══ 5. PRINCIPIO DE RESPONSABILIDAD ÚNICA (SRP) ═══\n");
        
        System.out.println("✅ Tarea: Se encarga SOLO de los datos y comportamiento de UNA tarea");
        System.out.println("✅ TareaNormal: Extiende Tarea agregando comportamiento ESPECÍFICO de tareas normales");
        System.out.println("✅ TareaUrgente: Extiende Tarea agregando comportamiento ESPECÍFICO de tareas urgentes");
        System.out.println("✅ Accionable: Define SOLO las acciones comunes de las tareas");
        System.out.println("✅ GestorTareas: Se encarga SOLO de la gestión de la colección de tareas");
        System.out.println("✅ Main: Se encarga SOLO de la interfaz de usuario y presentación");
        
        // ========== DEMOSTRACIÓN 6: BAJO ACOPLAMIENTO ==========
        System.out.println("\n═══ 6. BAJO ACOPLAMIENTO ═══\n");
        
        System.out.println("✅ Las clases se comunican a través de interfaces bien definidas");
        System.out.println("✅ TareaNormal y TareaUrgente no dependen entre sí");
        System.out.println("✅ El código cliente puede trabajar con Tarea sin conocer el tipo específico");
        System.out.println("✅ Se pueden agregar nuevos tipos de tareas sin modificar código existente");
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           FIN DE LA DEMOSTRACIÓN                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Método auxiliar que demuestra el uso de polimorfismo con parámetros.
     * Acepta cualquier objeto que implemente Accionable.
     * 
     * @param tarea Objeto que implementa la interfaz Accionable
     */
    public static void procesarTareaGenerica(Accionable tarea) {
        System.out.println("\n🔧 Procesando tarea genérica...");
        
        if (tarea.validar()) {
            System.out.println("✅ Tarea validada correctamente");
            tarea.ejecutar();
        } else {
            System.out.println("❌ Tarea inválida, no se puede ejecutar");
        }
    }
}
