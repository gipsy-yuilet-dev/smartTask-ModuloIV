import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de pruebas unitarias para la clase Tarea.
 * Prueba todos los métodos, getters, setters, constructores y validaciones.
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 */
@DisplayName("Suite de Pruebas: Tarea")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TareaTest {
    
    private Tarea tarea;
    
    @BeforeEach
    @DisplayName("Setup: Inicializar Tarea")
    void setUp() {
        tarea = new Tarea();
    }
    
    @AfterEach
    void tearDown() {
        tarea = null;
    }
    
    // ==================== PRUEBAS DE CONSTRUCTORES ====================
    
    @Test
    @Order(1)
    @DisplayName("Test 001: Constructor por defecto")
    void testConstructorPorDefecto() {
        Tarea t = new Tarea();
        
        assertEquals(0, t.getId(), "ID por defecto debe ser 0");
        assertEquals("", t.getNombre(), "Nombre por defecto debe ser vacío");
        assertEquals(1, t.getPrioridad(), "Prioridad por defecto debe ser 1 (Baja)");
        assertFalse(t.isCompletado(), "Nueva tarea debe estar pendiente");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 002: Constructor con 3 parámetros")
    void testConstructorConTresParametros() {
        Tarea t = new Tarea(5, "Estudiar JUnit", 2);
        
        assertEquals(5, t.getId());
        assertEquals("Estudiar JUnit", t.getNombre());
        assertEquals(2, t.getPrioridad());
        assertFalse(t.isCompletado(), "Debe iniciar como pendiente");
    }
    
    @Test
    @Order(3)
    @DisplayName("Test 003: Constructor con 4 parámetros")
    void testConstructorConCuatroParametros() {
        Tarea t = new Tarea(10, "Tarea completada", 3, true);
        
        assertEquals(10, t.getId());
        assertEquals("Tarea completada", t.getNombre());
        assertEquals(3, t.getPrioridad());
        assertTrue(t.isCompletado());
    }
    
    // ==================== PRUEBAS DE GETTERS Y SETTERS ====================
    
    @Test
    @Order(4)
    @DisplayName("Test 004: getId y setId")
    void testGetSetId() {
        tarea.setId(42);
        assertEquals(42, tarea.getId());
        
        tarea.setId(100);
        assertEquals(100, tarea.getId());
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 005: getNombre y setNombre - caso válido")
    void testGetSetNombreValido() {
        tarea.setNombre("Mi tarea importante");
        assertEquals("Mi tarea importante", tarea.getNombre());
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 006: setNombre elimina espacios extras (trim)")
    void testSetNombreTrim() {
        tarea.setNombre("  Tarea con espacios  ");
        assertEquals("Tarea con espacios", tarea.getNombre());
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 007: setNombre lanza excepción con nombre null")
    void testSetNombreNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tarea.setNombre(null);
        });
        
        assertTrue(exception.getMessage().contains("no puede estar vacío"));
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 008: setNombre lanza excepción con nombre vacío")
    void testSetNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            tarea.setNombre("");
        });
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 009: setNombre lanza excepción con solo espacios")
    void testSetNombreSoloEspacios() {
        assertThrows(IllegalArgumentException.class, () -> {
            tarea.setNombre("   ");
        });
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 010: getPrioridad y setPrioridad - valores válidos")
    void testGetSetPrioridadValidos() {
        tarea.setPrioridad(1);
        assertEquals(1, tarea.getPrioridad());
        
        tarea.setPrioridad(2);
        assertEquals(2, tarea.getPrioridad());
        
        tarea.setPrioridad(3);
        assertEquals(3, tarea.getPrioridad());
    }
    
    @Test
    @Order(11)
    @DisplayName("Test 011: setPrioridad lanza excepción con valor < 1")
    void testSetPrioridadMenorA1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tarea.setPrioridad(0);
        });
        
        assertTrue(exception.getMessage().contains("entre 1 y 3"));
    }
    
    @Test
    @Order(12)
    @DisplayName("Test 012: setPrioridad lanza excepción con valor > 3")
    void testSetPrioridadMayorA3() {
        assertThrows(IllegalArgumentException.class, () -> {
            tarea.setPrioridad(4);
        });
    }
    
    @Test
    @Order(13)
    @DisplayName("Test 013: setPrioridad lanza excepción con valor negativo")
    void testSetPrioridadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            tarea.setPrioridad(-1);
        });
    }
    
    @Test
    @Order(14)
    @DisplayName("Test 014: isCompletado y setCompletado")
    void testGetSetCompletado() {
        assertFalse(tarea.isCompletado(), "Inicial debe ser false");
        
        tarea.setCompletado(true);
        assertTrue(tarea.isCompletado());
        
        tarea.setCompletado(false);
        assertFalse(tarea.isCompletado());
    }
    
    // ==================== PRUEBAS DE MÉTODOS ADICIONALES ====================
    
    @Test
    @Order(15)
    @DisplayName("Test 015: marcarCompletada()")
    void testMarcarCompletada() {
        assertFalse(tarea.isCompletado(), "Debe iniciar como pendiente");
        
        tarea.marcarCompletada();
        
        assertTrue(tarea.isCompletado(), "Debe estar completada");
    }
    
    @Test
    @Order(16)
    @DisplayName("Test 016: marcarPendiente()")
    void testMarcarPendiente() {
        tarea.setCompletado(true);
        assertTrue(tarea.isCompletado());
        
        tarea.marcarPendiente();
        
        assertFalse(tarea.isCompletado(), "Debe estar pendiente");
    }
    
    @Test
    @Order(17)
    @DisplayName("Test 017: marcarCompletada() es idempotente")
    void testMarcarCompletadaIdempotente() {
        tarea.marcarCompletada();
        tarea.marcarCompletada();
        
        assertTrue(tarea.isCompletado());
    }
    
    @Test
    @Order(18)
    @DisplayName("Test 018: marcarPendiente() es idempotente")
    void testMarcarPendienteIdempotente() {
        tarea.marcarPendiente();
        tarea.marcarPendiente();
        
        assertFalse(tarea.isCompletado());
    }
    
    @Test
    @Order(19)
    @DisplayName("Test 019: obtenerTextoPrioridad() - prioridad 1 (Baja)")
    void testObtenerTextoPrioridadBaja() {
        tarea.setPrioridad(1);
        assertEquals("Baja", tarea.obtenerTextoPrioridad());
    }
    
    @Test
    @Order(20)
    @DisplayName("Test 020: obtenerTextoPrioridad() - prioridad 2 (Media)")
    void testObtenerTextoPrioridadMedia() {
        tarea.setPrioridad(2);
        assertEquals("Media", tarea.obtenerTextoPrioridad());
    }
    
    @Test
    @Order(21)
    @DisplayName("Test 021: obtenerTextoPrioridad() - prioridad 3 (Alta)")
    void testObtenerTextoPrioridadAlta() {
        tarea.setPrioridad(3);
        assertEquals("Alta", tarea.obtenerTextoPrioridad());
    }
    
    @Test
    @Order(22)
    @DisplayName("Test 022: obtenerSimboloEstado() - tarea pendiente")
    void testObtenerSimboloEstadoPendiente() {
        tarea.setCompletado(false);
        assertEquals("[ ]", tarea.obtenerSimboloEstado());
    }
    
    @Test
    @Order(23)
    @DisplayName("Test 023: obtenerSimboloEstado() - tarea completada")
    void testObtenerSimboloEstadoCompletada() {
        tarea.setCompletado(true);
        assertEquals("[X]", tarea.obtenerSimboloEstado());
    }
    
    @Test
    @Order(24)
    @DisplayName("Test 024: esPrioridadValida() - valores válidos")
    void testEsPrioridadValidaValores() {
        tarea.setPrioridad(1);
        assertTrue(tarea.esPrioridadValida());
        
        tarea.setPrioridad(2);
        assertTrue(tarea.esPrioridadValida());
        
        tarea.setPrioridad(3);
        assertTrue(tarea.esPrioridadValida());
    }
    
    // ==================== PRUEBAS DE toString() ====================
    
    @Test
    @Order(25)
    @DisplayName("Test 025: toString() - tarea pendiente")
    void testToStringTareaPendiente() {
        Tarea t = new Tarea(1, "Estudiar", 2, false);
        String resultado = t.toString();
        
        assertNotNull(resultado);
        assertTrue(resultado.contains("[ ]"), "Debe contener símbolo pendiente");
        assertTrue(resultado.contains("ID: 1"), "Debe contener el ID");
        assertTrue(resultado.contains("Estudiar"), "Debe contener el nombre");
        assertTrue(resultado.contains("Media"), "Debe contener texto de prioridad");
        assertTrue(resultado.contains("Pendiente"), "Debe contener estado");
    }
    
    @Test
    @Order(26)
    @DisplayName("Test 026: toString() - tarea completada")
    void testToStringTareaCompletada() {
        Tarea t = new Tarea(2, "Proyecto", 3, true);
        String resultado = t.toString();
        
        assertTrue(resultado.contains("[X]"), "Debe contener símbolo completado");
        assertTrue(resultado.contains("Alta"), "Debe contener prioridad alta");
        assertTrue(resultado.contains("Completada"), "Debe contener estado completada");
    }
    
    // ==================== PRUEBAS DE ESTADO Y TRANSICIONES ====================
    
    @Test
    @Order(27)
    @DisplayName("Test 027: Transición pendiente -> completada -> pendiente")
    void testTransicionEstados() {
        // Estado inicial: pendiente
        assertFalse(tarea.isCompletado());
        
        // Transición a completada
        tarea.marcarCompletada();
        assertTrue(tarea.isCompletado());
        
        // Transición a pendiente
        tarea.marcarPendiente();
        assertFalse(tarea.isCompletado());
    }
    
    @Test
    @Order(28)
    @DisplayName("Test 028: Modificar propiedades no afecta el estado de completitud")
    void testModificarPropiedadesNoAfectaEstado() {
        tarea.marcarCompletada();
        assertTrue(tarea.isCompletado());
        
        tarea.setNombre("Nuevo nombre");
        tarea.setPrioridad(3);
        
        assertTrue(tarea.isCompletado(), "El estado completado debe permanecer");
    }
    
    // ==================== PRUEBAS DE LÍMITES Y CASOS ESPECIALES ====================
    
    @Test
    @Order(29)
    @DisplayName("Test 029: Nombre con caracteres especiales")
    void testNombreConCaracteresEspeciales() {
        tarea.setNombre("Tarea #1 - Revisar código (URGENTE) @2026");
        assertEquals("Tarea #1 - Revisar código (URGENTE) @2026", tarea.getNombre());
    }
    
    @Test
    @Order(30)
    @DisplayName("Test 030: Nombre muy largo")
    void testNombreMuyLargo() {
        String nombreLargo = "E".repeat(500);
        tarea.setNombre(nombreLargo);
        assertEquals(nombreLargo, tarea.getNombre());
    }
    
    @Test
    @Order(31)
    @DisplayName("Test 031: ID con valores extremos")
    void testIdValoresExtremos() {
        tarea.setId(0);
        assertEquals(0, tarea.getId());
        
        tarea.setId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, tarea.getId());
        
        tarea.setId(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, tarea.getId());
    }
    
    @Test
    @Order(32)
    @DisplayName("Test 032: Valores de prioridad en los límites")
    void testPrioridadEnLimites() {
        tarea.setPrioridad(1);  // Límite inferior
        assertTrue(tarea.esPrioridadValida());
        
        tarea.setPrioridad(3);  // Límite superior
        assertTrue(tarea.esPrioridadValida());
    }
    
    // ==================== PRUEBAS DE INTEGRACIÓN ====================
    
    @Test
    @Order(33)
    @DisplayName("Test 033: Crear tarea completa y verificar todos los atributos")
    void testCrearTareaCompletaVerificarTodo() {
        Tarea t = new Tarea(99, "Tarea de prueba integral", 2, false);
        
        // Verificar valores iniciales
        assertEquals(99, t.getId());
        assertEquals("Tarea de prueba integral", t.getNombre());
        assertEquals(2, t.getPrioridad());
        assertFalse(t.isCompletado());
        
        // Verificar métodos derivados
        assertEquals("Media", t.obtenerTextoPrioridad());
        assertEquals("[ ]", t.obtenerSimboloEstado());
        assertTrue(t.esPrioridadValida());
        
        // Modificar estado
        t.marcarCompletada();
        assertTrue(t.isCompletado());
        assertEquals("[X]", t.obtenerSimboloEstado());
        
        // Verificar toString
        String str = t.toString();
        assertTrue(str.contains("99"));
        assertTrue(str.contains("Tarea de prueba integral"));
        assertTrue(str.contains("Media"));
        assertTrue(str.contains("Completada"));
    }
    
    @Test
    @Order(34)
    @DisplayName("Test 034: Dos tareas con mismos valores son diferentes instancias")
    void testDosTareasInstanciasDiferentes() {
        Tarea t1 = new Tarea(1, "Tarea", 2);
        Tarea t2 = new Tarea(1, "Tarea", 2);
        
        assertNotSame(t1, t2, "Deben ser instancias diferentes");
        // Nota: No se sobreescribe equals(), por lo que no serán iguales
        assertNotEquals(t1, t2);
    }
    
    @Test
    @Order(35)
    @DisplayName("Test 035: Clonar tarea mediante constructor")
    void testClonarTareaMedianteConstructor() {
        Tarea original = new Tarea(5, "Tarea original", 3, true);
        Tarea copia = new Tarea(
            original.getId(),
            original.getNombre(),
            original.getPrioridad(),
            original.isCompletado()
        );
        
        assertEquals(original.getId(), copia.getId());
        assertEquals(original.getNombre(), copia.getNombre());
        assertEquals(original.getPrioridad(), copia.getPrioridad());
        assertEquals(original.isCompletado(), copia.isCompletado());
        
        // Modificar copia no afecta original
        copia.setNombre("Modificada");
        assertNotEquals(original.getNombre(), copia.getNombre());
    }
}
