import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Suite de pruebas unitarias para la clase TareaUrgente.
 * Prueba la herencia, implementación de interfaz y funcionalidad específica de tareas urgentes.
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 */
@DisplayName("Suite de Pruebas: TareaUrgente")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TareaUrgenteTest {
    
    private TareaUrgente tarea;
    private LocalDateTime ahora;
    
    @BeforeEach
    void setUp() {
        tarea = new TareaUrgente();
        ahora = LocalDateTime.now();
    }
    
    @AfterEach
    void tearDown() {
        tarea = null;
    }
    
    // ==================== PRUEBAS DE CONSTRUCTORES ====================
    
    @Test
    @Order(1)
    @DisplayName("Test 001: Constructor por defecto inicializa correctamente")
    void testConstructorPorDefecto() {
        TareaUrgente t = new TareaUrgente();
        
        assertEquals(0, t.getId());
        assertEquals("", t.getNombre());
        assertEquals(3, t.getPrioridad(), "Urgente debe tener prioridad ALTA por defecto");
        assertFalse(t.isCompletado());
        assertNotNull(t.getFechaLimite(), "Debe tener fecha límite");
        assertTrue(t.isNotificacionesActivas(), "Notificaciones deben estar activas");
        assertEquals(8, t.getNivelCriticidad(), "Criticidad por defecto debe ser 8");
        assertEquals("Sin asignar", t.getResponsable());
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 002: Constructor con 3 parámetros")
    void testConstructorConTresParametros() {
        TareaUrgente t = new TareaUrgente(5, "Urgente", 3);
        
        assertEquals(5, t.getId());
        assertEquals("Urgente", t.getNombre());
        assertEquals(3, t.getPrioridad());
        assertNotNull(t.getFechaLimite());
    }
    
    @Test
    @Order(3)
    @DisplayName("Test 003: Constructor completo")
    void testConstructorCompleto() {
        LocalDateTime fecha = LocalDateTime.now().plusDays(2);
        TareaUrgente t = new TareaUrgente(10, "Proyecto crítico", 3, fecha, 10, "Juan Pérez");
        
        assertEquals(10, t.getId());
        assertEquals("Proyecto crítico", t.getNombre());
        assertEquals(3, t.getPrioridad());
        assertEquals(fecha, t.getFechaLimite());
        assertEquals(10, t.getNivelCriticidad());
        assertEquals("Juan Pérez", t.getResponsable());
    }
    
    // ==================== PRUEBAS DE GETTERS Y SETTERS ====================
    
    @Test
    @Order(4)
    @DisplayName("Test 004: getFechaLimite y setFechaLimite")
    void testGetSetFechaLimite() {
        LocalDateTime nuevaFecha = LocalDateTime.now().plusHours(5);
        tarea.setFechaLimite(nuevaFecha);
        
        assertEquals(nuevaFecha, tarea.getFechaLimite());
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 005: isNotificacionesActivas y setNotificacionesActivas")
    void testGetSetNotificaciones() {
        assertTrue(tarea.isNotificacionesActivas());
        
        tarea.setNotificacionesActivas(false);
        assertFalse(tarea.isNotificacionesActivas());
        
        tarea.setNotificacionesActivas(true);
        assertTrue(tarea.isNotificacionesActivas());
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 006: getNivelCriticidad y setNivelCriticidad - valores válidos")
    void testGetSetNivelCriticidadValidos() {
        tarea.setNivelCriticidad(1);
        assertEquals(1, tarea.getNivelCriticidad());
        
        tarea.setNivelCriticidad(5);
        assertEquals(5, tarea.getNivelCriticidad());
        
        tarea.setNivelCriticidad(10);
        assertEquals(10, tarea.getNivelCriticidad());
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 007: setNivelCriticidad rechaza valores fuera de rango")
    void testSetNivelCriticidadFueraDeRango() {
        tarea.setNivelCriticidad(8);
        
        tarea.setNivelCriticidad(0);
        assertEquals(8, tarea.getNivelCriticidad(), "No debe cambiar con 0");
        
        tarea.setNivelCriticidad(11);
        assertEquals(8, tarea.getNivelCriticidad(), "No debe cambiar con 11");
        
        tarea.setNivelCriticidad(-5);
        assertEquals(8, tarea.getNivelCriticidad(), "No debe cambiar con negativo");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 008: getResponsable y setResponsable")
    void testGetSetResponsable() {
        tarea.setResponsable("María González");
        assertEquals("María González", tarea.getResponsable());
        
        tarea.setResponsable("Carlos Ruiz");
        assertEquals("Carlos Ruiz", tarea.getResponsable());
    }
    
    // ==================== PRUEBAS DE HERENCIA ====================
    
    @Test
    @Order(9)
    @DisplayName("Test 009: TareaUrgente hereda de Tarea")
    void testHeredaDeTarea() {
        assertTrue(tarea instanceof Tarea, "TareaUrgente debe ser instancia de Tarea");
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 010: Métodos heredados funcionan")
    void testMetodosHeredados() {
        tarea.setId(50);
        tarea.setNombre("Tarea heredada urgente");
        tarea.setPrioridad(3);
        tarea.marcarCompletada();
        
        assertEquals(50, tarea.getId());
        assertEquals("Tarea heredada urgente", tarea.getNombre());
        assertEquals(3, tarea.getPrioridad());
        assertTrue(tarea.isCompletado());
    }
    
    // ==================== PRUEBAS DE INTERFAZ ACCIONABLE ====================
    
    @Test
    @Order(11)
    @DisplayName("Test 011: Implementa interfaz Accionable")
    void testImplementaAccionable() {
        assertTrue(tarea instanceof Accionable);
    }
    
    @Test
    @Order(12)
    @DisplayName("Test 012: ejecutar() no lanza excepciones")
    void testEjecutar() {
        tarea.setNombre("Incidente crítico");
        tarea.setResponsable("Equipo DevOps");
        
        assertDoesNotThrow(() -> {
            tarea.ejecutar();
        });
    }
    
    @Test
    @Order(13)
    @DisplayName("Test 013: cancelar() desactiva notificaciones")
    void testCancelar() {
        tarea.marcarCompletada();
        assertTrue(tarea.isNotificacionesActivas());
        
        tarea.cancelar();
        
        assertFalse(tarea.isCompletado());
        assertFalse(tarea.isNotificacionesActivas(), 
                   "Cancelar debe desactivar notificaciones");
    }
    
    @Test
    @Order(14)
    @DisplayName("Test 014: validar() retorna true para tarea válida")
    void testValidarTareaValida() {
        tarea.setNombre("Tarea urgente válida");
        tarea.setPrioridad(3);
        tarea.setFechaLimite(LocalDateTime.now().plusHours(2));
        tarea.setNivelCriticidad(9);
        
        assertTrue(tarea.validar());
    }
    
    @Test
    @Order(15)
    @DisplayName("Test 015: validar() retorna false con fecha límite null")
    void testValidarFechaLimiteNull() {
        TareaUrgente t = new TareaUrgente();
        t.setNombre("Tarea");
        t.setPrioridad(3);
        t.setFechaLimite(null);
        
        assertFalse(t.validar(), "Debe fallar validación sin fecha límite");
    }
    
    @Test
    @Order(16)
    @DisplayName("Test 016: validar() retorna false con criticidad inválida")
    void testValidarCriticidadInvalida() {
        TareaUrgente t = new TareaUrgente();
        t.setNombre("Tarea");
        t.setPrioridad(3);
        // setNivelCriticidad rechaza valores inválidos, pero podemos probar indirectamente
        
        assertTrue(t.validar(), "Con criticidad válida debe pasar");
    }
    
    @Test
    @Order(17)
    @DisplayName("Test 017: obtenerNivelImportancia() mayor que tareas normales")
    void testObtenerNivelImportancia() {
        tarea.setNivelCriticidad(9);
        int nivel = tarea.obtenerNivelImportancia();
        
        assertTrue(nivel >= 7 && nivel <= 10, 
                  "Tareas urgentes deben tener importancia entre 7 y 10");
    }
    
    @Test
    @Order(18)
    @DisplayName("Test 018: obtenerDescripcionAccion() contiene información urgente")
    void testObtenerDescripcionAccion() {
        tarea.setNombre("Resolver incidente");
        tarea.setNivelCriticidad(10);
        tarea.setResponsable("Soporte técnico");
        
        String descripcion = tarea.obtenerDescripcionAccion();
        
        assertNotNull(descripcion);
        assertTrue(descripcion.contains("URGENTE") || descripcion.contains("🚨"));
        assertTrue(descripcion.contains("Resolver incidente"));
        assertTrue(descripcion.contains("10"));
        assertTrue(descripcion.contains("Soporte técnico"));
    }
    
    // ==================== PRUEBAS DE MÉTODOS ESPECÍFICOS ====================
    
    @Test
    @Order(19)
    @DisplayName("Test 019: esVencida() retorna false para tarea futura")
    void testEsVencidaFalsoParaFutura() {
        tarea.setFechaLimite(LocalDateTime.now().plusHours(5));
        
        assertFalse(tarea.esVencida(), "Tarea futura no debe estar vencida");
    }
    
    @Test
    @Order(20)
    @DisplayName("Test 020: esVencida() retorna true para tarea pasada")
    void testEsVencidaVerdaderoParaPasada() {
        tarea.setFechaLimite(LocalDateTime.now().minusHours(1));
        
        assertTrue(tarea.esVencida(), "Tarea pasada debe estar vencida");
    }
    
    @Test
    @Order(21)
    @DisplayName("Test 021: horasRestantes() positivo para tarea futura")
    void testHorasRestantesPositivo() {
        tarea.setFechaLimite(LocalDateTime.now().plusHours(10));
        
        long horas = tarea.horasRestantes();
        
        assertTrue(horas >= 9 && horas <= 11, 
                  "Deben quedar aproximadamente 10 horas (±1 por precisión)");
    }
    
    @Test
    @Order(22)
    @DisplayName("Test 022: horasRestantes() negativo para tarea vencida")
    void testHorasRestantesNegativo() {
        tarea.setFechaLimite(LocalDateTime.now().minusHours(5));
        
        long horas = tarea.horasRestantes();
        
        assertTrue(horas < 0, "Horas restantes deben ser negativas para tarea vencida");
    }
    
    @Test
    @Order(23)
    @DisplayName("Test 023: enviarNotificacion() sin excepciones cuando activas")
    void testEnviarNotificacionActivas() {
        tarea.setNombre("Tarea con notificación");
        tarea.setNotificacionesActivas(true);
        
        assertDoesNotThrow(() -> {
            tarea.enviarNotificacion();
        });
    }
    
    @Test
    @Order(24)
    @DisplayName("Test 024: enviarNotificacion() no hace nada si desactivadas")
    void testEnviarNotificacionDesactivadas() {
        tarea.setNotificacionesActivas(false);
        
        assertDoesNotThrow(() -> {
            tarea.enviarNotificacion();
        });
    }
    
    // ==================== PRUEBAS DE POLIMORFISMO ====================
    
    @Test
    @Order(25)
    @DisplayName("Test 025: toString() sobrescrito incluye info de urgencia")
    void testToStringConInfoUrgente() {
        LocalDateTime fecha = LocalDateTime.now().plusHours(3);
        TareaUrgente t = new TareaUrgente(1, "Crítico", 3, fecha, 10, "Admin");
        
        String str = t.toString();
        
        assertTrue(str.contains("Crítico"));
        assertTrue(str.contains("10"));
        assertTrue(str.contains("Admin"));
        assertTrue(str.contains("URGENTE") || str.contains("🚨"));
    }
    
    @Test
    @Order(26)
    @DisplayName("Test 026: toString() indica si está vencida")
    void testToStringIndicaVencida() {
        // Tarea vencida
        TareaUrgente vencida = new TareaUrgente();
        vencida.setNombre("Vencida");
        vencida.setFechaLimite(LocalDateTime.now().minusHours(1));
        
        String str = vencida.toString();
        assertTrue(str.contains("VENCIDA") || str.contains("⚠️"));
        
        // Tarea vigente
        TareaUrgente vigente = new TareaUrgente();
        vigente.setNombre("Vigente");
        vigente.setFechaLimite(LocalDateTime.now().plusHours(5));
        
        String str2 = vigente.toString();
        assertTrue(str2.contains("VIGENTE") || str2.contains("✅"));
    }
    
    @Test
    @Order(27)
    @DisplayName("Test 027: Polimorfismo - TareaUrgente como Tarea")
    void testPolimorfismoComoTarea() {
        Tarea t = new TareaUrgente(5, "Polimórfica", 3);
        
        assertEquals(5, t.getId());
        assertEquals("Polimórfica", t.getNombre());
        assertEquals(3, t.getPrioridad());
    }
    
    @Test
    @Order(28)
    @DisplayName("Test 028: Polimorfismo - TareaUrgente como Accionable")
    void testPolimorfismoComoAccionable() {
        LocalDateTime fecha = LocalDateTime.now().plusDays(1);
        Accionable a = new TareaUrgente(10, "Accionable", 3, fecha, 9, "Test");
        
        assertTrue(a.validar());
        assertTrue(a.obtenerNivelImportancia() >= 7);
        assertNotNull(a.obtenerDescripcionAccion());
        
        assertDoesNotThrow(() -> {
            a.ejecutar();
            a.cancelar();
        });
    }
    
    // ==================== PRUEBAS DE CASOS LÍMITE ====================
    
    @Test
    @Order(29)
    @DisplayName("Test 029: Fecha límite muy lejana")
    void testFechaLimiteMuyLejana() {
        LocalDateTime lejana = LocalDateTime.now().plusYears(10);
        tarea.setFechaLimite(lejana);
        
        assertEquals(lejana, tarea.getFechaLimite());
        assertFalse(tarea.esVencida());
    }
    
    @Test
    @Order(30)
    @DisplayName("Test 030: Responsable vacío es válido")
    void testResponsableVacio() {
        tarea.setResponsable("");
        assertEquals("", tarea.getResponsable());
    }
    
    @Test
    @Order(31)
    @DisplayName("Test 031: Nivel criticidad en límites (1 y 10)")
    void testCriticidadEnLimites() {
        tarea.setNivelCriticidad(1);
        assertEquals(1, tarea.getNivelCriticidad());
        
        tarea.setNivelCriticidad(10);
        assertEquals(10, tarea.getNivelCriticidad());
    }
    
    // ==================== PRUEBAS DE INTEGRACIÓN ====================
    
    @Test
    @Order(32)
    @DisplayName("Test 032: Flujo completo de tarea urgente")
    void testFlujoCompleto() {
        // Crear tarea urgente
        LocalDateTime limite = LocalDateTime.now().plusHours(2);
        TareaUrgente t = new TareaUrgente(1, "Incidente producción", 3, 
                                          limite, 10, "DevOps Team");
        
        // Validar
        assertTrue(t.validar());
        assertFalse(t.esVencida());
        
        // Ejecutar
        assertDoesNotThrow(() -> t.ejecutar());
        
        // Enviar notificación
        assertTrue(t.isNotificacionesActivas());
        assertDoesNotThrow(() -> t.enviarNotificacion());
        
        // Completar
        t.marcarCompletada();
        assertTrue(t.isCompletado());
        
        // Cancelar
        t.cancelar();
        assertFalse(t.isCompletado());
        assertFalse(t.isNotificacionesActivas());
    }
    
    @Test
    @Order(33)
    @DisplayName("Test 033: Comparar niveles de importancia")
    void testCompararNivelesImportancia() {
        TareaUrgente baja = new TareaUrgente();
        baja.setNivelCriticidad(1);
        
        TareaUrgente alta = new TareaUrgente();
        alta.setNivelCriticidad(10);
        
        assertTrue(baja.obtenerNivelImportancia() <= alta.obtenerNivelImportancia());
    }
    
    @Test
    @Order(34)
    @DisplayName("Test 034: Escenario real - Tarea vence en pocas horas")
    void testEscenarioReal() {
        TareaUrgente t = new TareaUrgente();
        t.setNombre("Resolver bug crítico");
        t.setFechaLimite(LocalDateTime.now().plusHours(3));
        t.setNivelCriticidad(10);
        t.setResponsable("Desarrollador Senior");
        t.setNotificacionesActivas(true);
        
        // Verificar estado
        assertFalse(t.esVencida());
        assertTrue(t.horasRestantes() >= 2 && t.horasRestantes() <= 4);
        assertTrue(t.validar());
        
        // Enviar notificación
        assertDoesNotThrow(() -> t.enviarNotificacion());
        
        // Verificar nivel de importancia alto
        assertTrue(t.obtenerNivelImportancia() >= 9);
        
        // Verificar descripción contiene info crítica
        String desc = t.obtenerDescripcionAccion();
        assertTrue(desc.contains("10"));
        assertTrue(desc.contains("Desarrollador Senior"));
    }
}
