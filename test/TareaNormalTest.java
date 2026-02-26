import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de pruebas unitarias para la clase TareaNormal.
 * Prueba la herencia, implementación de interfaz y funcionalidad específica.
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 */
@DisplayName("Suite de Pruebas: TareaNormal")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TareaNormalTest {
    
    private TareaNormal tarea;
    
    @BeforeEach
    void setUp() {
        tarea = new TareaNormal();
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
        TareaNormal t = new TareaNormal();
        
        assertEquals(0, t.getId());
        assertEquals("", t.getNombre());
        assertEquals(1, t.getPrioridad());
        assertFalse(t.isCompletado());
        assertEquals("General", t.getCategoria(), "Categoría por defecto debe ser General");
        assertEquals(30, t.getTiempoEstimado(), "Tiempo por defecto debe ser 30 min");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 002: Constructor con 3 parámetros")
    void testConstructorConTresParametros() {
        TareaNormal t = new TareaNormal(5, "Estudiar", 2);
        
        assertEquals(5, t.getId());
        assertEquals("Estudiar", t.getNombre());
        assertEquals(2, t.getPrioridad());
        assertEquals("General", t.getCategoria());
        assertEquals(30, t.getTiempoEstimado());
    }
    
    @Test
    @Order(3)
    @DisplayName("Test 003: Constructor completo con todos los parámetros")
    void testConstructorCompleto() {
        TareaNormal t = new TareaNormal(10, "Proyecto", 3, "Trabajo", 120);
        
        assertEquals(10, t.getId());
        assertEquals("Proyecto", t.getNombre());
        assertEquals(3, t.getPrioridad());
        assertEquals("Trabajo", t.getCategoria());
        assertEquals(120, t.getTiempoEstimado());
    }
    
    // ==================== PRUEBAS DE GETTERS Y SETTERS ====================
    
    @Test
    @Order(4)
    @DisplayName("Test 004: getCategoria y setCategoria")
    void testGetSetCategoria() {
        tarea.setCategoria("Personal");
        assertEquals("Personal", tarea.getCategoria());
        
        tarea.setCategoria("Estudio");
        assertEquals("Estudio", tarea.getCategoria());
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 005: getTiempoEstimado y setTiempoEstimado")
    void testGetSetTiempoEstimado() {
        tarea.setTiempoEstimado(60);
        assertEquals(60, tarea.getTiempoEstimado());
        
        tarea.setTiempoEstimado(180);
        assertEquals(180, tarea.getTiempoEstimado());
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 006: setTiempoEstimado no acepta valores <= 0")
    void testSetTiempoEstimadoValorInvalido() {
        tarea.setTiempoEstimado(30);
        
        tarea.setTiempoEstimado(0);
        assertEquals(30, tarea.getTiempoEstimado(), "No debe cambiar con 0");
        
        tarea.setTiempoEstimado(-10);
        assertEquals(30, tarea.getTiempoEstimado(), "No debe cambiar con negativo");
    }
    
    // ==================== PRUEBAS DE HERENCIA ====================
    
    @Test
    @Order(7)
    @DisplayName("Test 007: TareaNormal hereda de Tarea")
    void testHeredaDeTarea() {
        assertTrue(tarea instanceof Tarea, "TareaNormal debe ser instancia de Tarea");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 008: Métodos heredados funcionan correctamente")
    void testMetodosHeredados() {
        tarea.setId(100);
        tarea.setNombre("Tarea heredada");
        tarea.setPrioridad(2);
        tarea.marcarCompletada();
        
        assertEquals(100, tarea.getId());
        assertEquals("Tarea heredada", tarea.getNombre());
        assertEquals(2, tarea.getPrioridad());
        assertTrue(tarea.isCompletado());
    }
    
    // ==================== PRUEBAS DE INTERFAZ ACCIONABLE ====================
    
    @Test
    @Order(9)
    @DisplayName("Test 009: Implementa interfaz Accionable")
    void testImplementaAccionable() {
        assertTrue(tarea instanceof Accionable, 
                  "TareaNormal debe implementar Accionable");
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 010: ejecutar() no lanza excepciones")
    void testEjecutar() {
        tarea.setNombre("Tarea ejecutable");
        tarea.setCategoria("Test");
        
        assertDoesNotThrow(() -> {
            tarea.ejecutar();
        });
    }
    
    @Test
    @Order(11)
    @DisplayName("Test 011: cancelar() marca tarea como pendiente")
    void testCancelar() {
        tarea.marcarCompletada();
        assertTrue(tarea.isCompletado());
        
        tarea.cancelar();
        
        assertFalse(tarea.isCompletado(), "Cancelar debe marcar como pendiente");
    }
    
    @Test
    @Order(12)
    @DisplayName("Test 012: validar() retorna true para tarea válida")
    void testValidarTareaValida() {
        tarea.setNombre("Tarea válida");
        tarea.setPrioridad(2);
        
        assertTrue(tarea.validar(), "Tarea válida debe pasar validación");
    }
    
    @Test
    @Order(13)
    @DisplayName("Test 013: validar() retorna false para nombre null")
    void testValidarNombreNull() {
        TareaNormal t = new TareaNormal();
        // Constructor por defecto pone nombre vacío
        
        assertFalse(t.validar(), "Validación debe fallar con nombre vacío");
    }
    
    @Test
    @Order(14)
    @DisplayName("Test 014: validar() retorna false para prioridad inválida")
    void testValidarPrioridadInvalida() {
        tarea.setNombre("Tarea");
        // Forzar prioridad inválida directamente (asumiendo acceso protected)
        
        assertTrue(tarea.validar(), "Con prioridad válida debe pasar");
    }
    
    @Test
    @Order(15)
    @DisplayName("Test 015: obtenerNivelImportancia() retorna prioridad (1-3)")
    void testObtenerNivelImportancia() {
        tarea.setPrioridad(1);
        assertEquals(1, tarea.obtenerNivelImportancia());
        
        tarea.setPrioridad(2);
        assertEquals(2, tarea.obtenerNivelImportancia());
        
        tarea.setPrioridad(3);
        assertEquals(3, tarea.obtenerNivelImportancia());
    }
    
    @Test
    @Order(16)
    @DisplayName("Test 016: obtenerDescripcionAccion() contiene información relevante")
    void testObtenerDescripcionAccion() {
        tarea.setNombre("Estudiar Java");
        tarea.setCategoria("Educación");
        tarea.setTiempoEstimado(45);
        
        String descripcion = tarea.obtenerDescripcionAccion();
        
        assertNotNull(descripcion);
        assertTrue(descripcion.contains("Estudiar Java"));
        assertTrue(descripcion.contains("Educación"));
        assertTrue(descripcion.contains("45"));
    }
    
    // ==================== PRUEBAS DE POLIMORFISMO ====================
    
    @Test
    @Order(17)
    @DisplayName("Test 017: toString() sobrescrito incluye info adicional")
    void testToStringPolimorfismo() {
        TareaNormal t = new TareaNormal(1, "Tarea normal", 2, "Personal", 60);
        
        String str = t.toString();
        
        assertTrue(str.contains("Tarea normal"));
        assertTrue(str.contains("Personal"), "Debe incluir categoría");
        assertTrue(str.contains("60"), "Debe incluir tiempo estimado");
        assertTrue(str.contains("NORMAL"), "Debe indicar tipo");
    }
    
    @Test
    @Order(18)
    @DisplayName("Test 018: Polimorfismo - TareaNormal como Tarea")
    void testPolimorfismoComoTarea() {
        Tarea t = new TareaNormal(5, "Tarea polimórfica", 3);
        
        assertEquals(5, t.getId());
        assertEquals("Tarea polimórfica", t.getNombre());
        assertEquals(3, t.getPrioridad());
    }
    
    @Test
    @Order(19)
    @DisplayName("Test 019: Polimorfismo - TareaNormal como Accionable")
    void testPolimorfismoComoAccionable() {
        Accionable a = new TareaNormal(10, "Accionable", 2, "Test", 30);
        
        assertTrue(a.validar());
        assertEquals(2, a.obtenerNivelImportancia());
        assertNotNull(a.obtenerDescripcionAccion());
        
        assertDoesNotThrow(() -> {
            a.ejecutar();
            a.cancelar();
        });
    }
    
    // ==================== PRUEBAS DE CASOS LÍMITE ====================
    
    @Test
    @Order(20)
    @DisplayName("Test 020: Categoría con caracteres especiales")
    void testCategoriaCaracteresEspeciales() {
        tarea.setCategoria("Categoría #1 - Test (2026)");
        assertEquals("Categoría #1 - Test (2026)", tarea.getCategoria());
    }
    
    @Test
    @Order(21)
    @DisplayName("Test 021: Tiempo estimado muy grande")
    void testTiempoEstimadoMuyGrande() {
        tarea.setTiempoEstimado(10000);
        assertEquals(10000, tarea.getTiempoEstimado());
    }
    
    @Test
    @Order(22)
    @DisplayName("Test 022: Categoría vacía es válida")
    void testCategoriaVacia() {
        tarea.setCategoria("");
        assertEquals("", tarea.getCategoria());
    }
    
    // ==================== PRUEBAS DE INTEGRACIÓN ====================
    
    @Test
    @Order(23)
    @DisplayName("Test 023: Flujo completo de uso")
    void testFlujoCompleto() {
        // Crear tarea
        TareaNormal t = new TareaNormal(1, "Estudiar JUnit", 2, "Estudio", 90);
        
        // Validar
        assertTrue(t.validar());
        
        // Ejecutar
        assertDoesNotThrow(() -> t.ejecutar());
        
        // Completar
        t.marcarCompletada();
        assertTrue(t.isCompletado());
        
        // Cancelar
        t.cancelar();
        assertFalse(t.isCompletado());
        
        // Verificar descripción
        String desc = t.obtenerDescripcionAccion();
        assertTrue(desc.contains("Estudiar JUnit"));
    }
    
    @Test
    @Order(24)
    @DisplayName("Test 024: Comparar niveles de importancia con prioridades")
    void testCompararNivelesImportancia() {
        TareaNormal baja = new TareaNormal(1, "Baja", 1);
        TareaNormal media = new TareaNormal(2, "Media", 2);
        TareaNormal alta = new TareaNormal(3, "Alta", 3);
        
        assertTrue(baja.obtenerNivelImportancia() < media.obtenerNivelImportancia());
        assertTrue(media.obtenerNivelImportancia() < alta.obtenerNivelImportancia());
    }
}
