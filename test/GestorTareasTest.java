import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Suite de pruebas unitarias para la clase GestorTareas.
 * Implementa pruebas exhaustivas con enfoque de QA profesional.
 * 
 * <p>Estrategia de testing:</p>
 * <ul>
 *   <li>Happy Path: Casos de uso normales</li>
 *   <li>Edge Cases: Casos límite y frontera</li>
 *   <li>Error Cases: Validación de errores y excepciones</li>
 *   <li>State Testing: Verificación de estados del sistema</li>
 *   <li>Boundary Testing: Pruebas en los límites de valores</li>
 * </ul>
 * 
 * @author Yulieta (Melek) Eyzaguirre
 * @version 1.0
 * @since 2026-02-23
 */
@DisplayName("Suite de Pruebas: GestorTareas")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GestorTareasTest {
    
    private GestorTareas gestor;
    
    /**
     * Configuración ejecutada antes de cada test.
     * Crea una instancia fresca de GestorTareas.
     */
    @BeforeEach
    @DisplayName("Setup: Inicializar GestorTareas")
    void setUp() {
        gestor = new GestorTareas();
    }
    
    /**
     * Limpieza ejecutada después de cada test.
     */
    @AfterEach
    @DisplayName("Teardown: Limpiar recursos")
    void tearDown() {
        gestor = null;
    }
    
    // ==================== PRUEBAS DE INICIALIZACIÓN ====================
    
    @Test
    @Order(1)
    @DisplayName("Test 001: GestorTareas se inicializa correctamente")
    void testInicializacionGestor() {
        assertNotNull(gestor, "El gestor no debe ser null");
        assertTrue(gestor.estaVacia(), "El gestor debe iniciar vacío");
        assertEquals(0, gestor.obtenerTotalTareas(), "Debe tener 0 tareas al iniciar");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 002: Constantes de prioridad están definidas correctamente")
    void testConstantesPrioridad() {
        assertEquals(1, GestorTareas.PRIORIDAD_BAJA, "PRIORIDAD_BAJA debe ser 1");
        assertEquals(2, GestorTareas.PRIORIDAD_MEDIA, "PRIORIDAD_MEDIA debe ser 2");
        assertEquals(3, GestorTareas.PRIORIDAD_ALTA, "PRIORIDAD_ALTA debe ser 3");
    }
    
    // ==================== PRUEBAS DE agregarTarea() ====================
    
    @Test
    @Order(3)
    @DisplayName("Test 003: Agregar tarea válida con prioridad baja")
    void testAgregarTareaValidaPrioridadBaja() {
        Tarea tarea = gestor.agregarTarea("Leer documentación", GestorTareas.PRIORIDAD_BAJA);
        
        assertNotNull(tarea, "La tarea creada no debe ser null");
        assertEquals(1, tarea.getId(), "El primer ID debe ser 1");
        assertEquals("Leer documentación", tarea.getNombre(), "El nombre debe coincidir");
        assertEquals(1, tarea.getPrioridad(), "La prioridad debe ser BAJA (1)");
        assertFalse(tarea.isCompletado(), "La tarea nueva debe estar pendiente");
        assertEquals(1, gestor.obtenerTotalTareas(), "Debe haber 1 tarea en el gestor");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test 004: Agregar tarea válida con prioridad media")
    void testAgregarTareaValidaPrioridadMedia() {
        Tarea tarea = gestor.agregarTarea("Revisar código", GestorTareas.PRIORIDAD_MEDIA);
        
        assertEquals(2, tarea.getPrioridad(), "La prioridad debe ser MEDIA (2)");
        assertEquals(1, gestor.obtenerTotalTareas(), "Debe haber 1 tarea");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 005: Agregar tarea válida con prioridad alta")
    void testAgregarTareaValidaPrioridadAlta() {
        Tarea tarea = gestor.agregarTarea("Entregar proyecto", GestorTareas.PRIORIDAD_ALTA);
        
        assertEquals(3, tarea.getPrioridad(), "La prioridad debe ser ALTA (3)");
        assertEquals(1, gestor.obtenerTotalTareas(), "Debe haber 1 tarea");
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 006: Agregar múltiples tareas - IDs autoincrementales")
    void testAgregarMultiplesTareasIdsAutoincrementales() {
        Tarea tarea1 = gestor.agregarTarea("Tarea 1", 1);
        Tarea tarea2 = gestor.agregarTarea("Tarea 2", 2);
        Tarea tarea3 = gestor.agregarTarea("Tarea 3", 3);
        
        assertEquals(1, tarea1.getId(), "Primera tarea debe tener ID 1");
        assertEquals(2, tarea2.getId(), "Segunda tarea debe tener ID 2");
        assertEquals(3, tarea3.getId(), "Tercera tarea debe tener ID 3");
        assertEquals(3, gestor.obtenerTotalTareas(), "Debe haber 3 tareas");
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 007: Agregar tarea con nombre que tiene espacios al inicio/final")
    void testAgregarTareaNombreConEspacios() {
        Tarea tarea = gestor.agregarTarea("  Tarea con espacios  ", 2);
        
        assertEquals("Tarea con espacios", tarea.getNombre(), 
                    "El nombre debe tener espacios eliminados (trim)");
    }
    
    // ==================== PRUEBAS DE ERRORES EN agregarTarea() ====================
    
    @Test
    @Order(8)
    @DisplayName("Test 008: Error al agregar tarea con nombre null")
    void testAgregarTareaNombreNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea(null, 2);
        });
        
        assertTrue(exception.getMessage().contains("no puede estar vacío"),
                  "El mensaje de error debe ser descriptivo");
        assertEquals(0, gestor.obtenerTotalTareas(), 
                    "No debe agregarse ninguna tarea");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 009: Error al agregar tarea con nombre vacío")
    void testAgregarTareaNombreVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea("", 2);
        });
        
        assertTrue(exception.getMessage().contains("no puede estar vacío"));
        assertEquals(0, gestor.obtenerTotalTareas());
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 010: Error al agregar tarea con nombre solo espacios")
    void testAgregarTareaNombreSoloEspacios() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea("   ", 2);
        });
        
        assertTrue(exception.getMessage().contains("no puede estar vacío"));
    }
    
    @Test
    @Order(11)
    @DisplayName("Test 011: Error al agregar tarea con prioridad menor a 1")
    void testAgregarTareaPrioridadMenorA1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea("Tarea inválida", 0);
        });
        
        assertTrue(exception.getMessage().contains("entre 1 y 3"),
                  "Debe indicar el rango válido de prioridad");
    }
    
    @Test
    @Order(12)
    @DisplayName("Test 012: Error al agregar tarea con prioridad mayor a 3")
    void testAgregarTareaPrioridadMayorA3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea("Tarea inválida", 4);
        });
        
        assertTrue(exception.getMessage().contains("entre 1 y 3"));
    }
    
    @Test
    @Order(13)
    @DisplayName("Test 013: Error al agregar tarea con prioridad negativa")
    void testAgregarTareaPrioridadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTarea("Tarea", -1);
        });
    }
    
    // ==================== PRUEBAS DE listarTareas() ====================
    
    @Test
    @Order(14)
    @DisplayName("Test 014: Listar tareas cuando está vacío")
    void testListarTareasVacio() {
        List<Tarea> tareas = gestor.listarTareas();
        
        assertNotNull(tareas, "La lista no debe ser null");
        assertTrue(tareas.isEmpty(), "La lista debe estar vacía");
        assertEquals(0, tareas.size(), "El tamaño debe ser 0");
    }
    
    @Test
    @Order(15)
    @DisplayName("Test 015: Listar tareas con una tarea")
    void testListarTareasConUnaTarea() {
        gestor.agregarTarea("Tarea única", 2);
        List<Tarea> tareas = gestor.listarTareas();
        
        assertEquals(1, tareas.size(), "Debe haber 1 tarea");
        assertEquals("Tarea única", tareas.get(0).getNombre());
    }
    
    @Test
    @Order(16)
    @DisplayName("Test 016: Listar tareas con múltiples tareas")
    void testListarTareasConMultiplesTareas() {
        gestor.agregarTarea("Tarea 1", 1);
        gestor.agregarTarea("Tarea 2", 2);
        gestor.agregarTarea("Tarea 3", 3);
        
        List<Tarea> tareas = gestor.listarTareas();
        
        assertEquals(3, tareas.size(), "Debe haber 3 tareas");
        assertEquals("Tarea 1", tareas.get(0).getNombre());
        assertEquals("Tarea 2", tareas.get(1).getNombre());
        assertEquals("Tarea 3", tareas.get(2).getNombre());
    }
    
    @Test
    @Order(17)
    @DisplayName("Test 017: Listar tareas retorna copia defensiva")
    void testListarTareasRetornaCopiaDefensiva() {
        gestor.agregarTarea("Tarea original", 2);
        
        List<Tarea> tareas1 = gestor.listarTareas();
        List<Tarea> tareas2 = gestor.listarTareas();
        
        assertNotSame(tareas1, tareas2, 
                     "Cada llamada debe retornar una nueva instancia de lista");
        
        // Intentar modificar la lista retornada no debe afectar al gestor
        tareas1.clear();
        
        assertEquals(1, gestor.obtenerTotalTareas(), 
                    "El gestor debe mantener su estado interno");
    }
    
    // ==================== PRUEBAS DE marcarComoCompletada() ====================
    
    @Test
    @Order(18)
    @DisplayName("Test 018: Marcar tarea como completada - caso exitoso")
    void testMarcarComoCompletadaExitoso() {
        Tarea tarea = gestor.agregarTarea("Tarea pendiente", 2);
        int id = tarea.getId();
        
        boolean resultado = gestor.marcarComoCompletada(id);
        
        assertTrue(resultado, "Debe retornar true si se marcó exitosamente");
        assertTrue(tarea.isCompletado(), "La tarea debe estar completada");
    }
    
    @Test
    @Order(19)
    @DisplayName("Test 019: Marcar tarea como completada - ID inexistente")
    void testMarcarComoCompletadaIdInexistente() {
        gestor.agregarTarea("Tarea 1", 1);
        
        boolean resultado = gestor.marcarComoCompletada(999);
        
        assertFalse(resultado, "Debe retornar false si el ID no existe");
    }
    
    @Test
    @Order(20)
    @DisplayName("Test 020: Marcar como completada cuando está vacío")
    void testMarcarComoCompletadaListaVacia() {
        boolean resultado = gestor.marcarComoCompletada(1);
        
        assertFalse(resultado, "Debe retornar false si no hay tareas");
    }
    
    @Test
    @Order(21)
    @DisplayName("Test 021: Marcar múltiples tareas como completadas")
    void testMarcarMultiplesTareasCompletadas() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        Tarea t2 = gestor.agregarTarea("Tarea 2", 2);
        Tarea t3 = gestor.agregarTarea("Tarea 3", 3);
        
        gestor.marcarComoCompletada(t1.getId());
        gestor.marcarComoCompletada(t3.getId());
        
        assertTrue(t1.isCompletado(), "Tarea 1 debe estar completada");
        assertFalse(t2.isCompletado(), "Tarea 2 debe estar pendiente");
        assertTrue(t3.isCompletado(), "Tarea 3 debe estar completada");
    }
    
    @Test
    @Order(22)
    @DisplayName("Test 022: Marcar tarea ya completada (idempotencia)")
    void testMarcarComoCompletadaIdempotencia() {
        Tarea tarea = gestor.agregarTarea("Tarea", 2);
        
        boolean resultado1 = gestor.marcarComoCompletada(tarea.getId());
        boolean resultado2 = gestor.marcarComoCompletada(tarea.getId());
        
        assertTrue(resultado1, "Primera llamada debe ser exitosa");
        assertTrue(resultado2, "Segunda llamada también debe ser exitosa");
        assertTrue(tarea.isCompletado(), "La tarea debe permanecer completada");
    }
    
    // ==================== PRUEBAS DE eliminarTarea() ====================
    
    @Test
    @Order(23)
    @DisplayName("Test 023: Eliminar tarea - caso exitoso")
    void testEliminarTareaExitoso() {
        Tarea tarea = gestor.agregarTarea("Tarea a eliminar", 2);
        int id = tarea.getId();
        
        boolean resultado = gestor.eliminarTarea(id);
        
        assertTrue(resultado, "Debe retornar true si se eliminó");
        assertEquals(0, gestor.obtenerTotalTareas(), "No debe haber tareas");
        assertTrue(gestor.estaVacia(), "El gestor debe estar vacío");
    }
    
    @Test
    @Order(24)
    @DisplayName("Test 024: Eliminar tarea - ID inexistente")
    void testEliminarTareaIdInexistente() {
        gestor.agregarTarea("Tarea 1", 1);
        
        boolean resultado = gestor.eliminarTarea(999);
        
        assertFalse(resultado, "Debe retornar false si el ID no existe");
        assertEquals(1, gestor.obtenerTotalTareas(), "La tarea debe permanecer");
    }
    
    @Test
    @Order(25)
    @DisplayName("Test 025: Eliminar de lista vacía")
    void testEliminarTareaListaVacia() {
        boolean resultado = gestor.eliminarTarea(1);
        
        assertFalse(resultado, "Debe retornar false en lista vacía");
    }
    
    @Test
    @Order(26)
    @DisplayName("Test 026: Eliminar múltiples tareas")
    void testEliminarMultiplesTareas() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        Tarea t2 = gestor.agregarTarea("Tarea 2", 2);
        Tarea t3 = gestor.agregarTarea("Tarea 3", 3);
        
        gestor.eliminarTarea(t2.getId());
        
        assertEquals(2, gestor.obtenerTotalTareas(), "Deben quedar 2 tareas");
        assertNotNull(gestor.buscarTareaPorId(t1.getId()), "T1 debe existir");
        assertNull(gestor.buscarTareaPorId(t2.getId()), "T2 no debe existir");
        assertNotNull(gestor.buscarTareaPorId(t3.getId()), "T3 debe existir");
    }
    
    @Test
    @Order(27)
    @DisplayName("Test 027: Eliminar tarea completada")
    void testEliminarTareaCompletada() {
        Tarea tarea = gestor.agregarTarea("Tarea completada", 2);
        gestor.marcarComoCompletada(tarea.getId());
        
        boolean resultado = gestor.eliminarTarea(tarea.getId());
        
        assertTrue(resultado, "Debe poder eliminar tarea completada");
        assertEquals(0, gestor.obtenerTotalTareas());
    }
    
    // ==================== PRUEBAS DE buscarTareaPorId() ====================
    
    @Test
    @Order(28)
    @DisplayName("Test 028: Buscar tarea por ID - caso exitoso")
    void testBuscarTareaPorIdExitoso() {
        Tarea tareaOriginal = gestor.agregarTarea("Tarea buscada", 2);
        int id = tareaOriginal.getId();
        
        Tarea tareaEncontrada = gestor.buscarTareaPorId(id);
        
        assertNotNull(tareaEncontrada, "Debe encontrar la tarea");
        assertSame(tareaOriginal, tareaEncontrada, "Debe ser la misma instancia");
        assertEquals("Tarea buscada", tareaEncontrada.getNombre());
    }
    
    @Test
    @Order(29)
    @DisplayName("Test 029: Buscar tarea por ID - ID inexistente")
    void testBuscarTareaPorIdInexistente() {
        gestor.agregarTarea("Tarea 1", 1);
        
        Tarea tarea = gestor.buscarTareaPorId(999);
        
        assertNull(tarea, "Debe retornar null si no existe");
    }
    
    @Test
    @Order(30)
    @DisplayName("Test 030: Buscar en lista vacía")
    void testBuscarTareaPorIdListaVacia() {
        Tarea tarea = gestor.buscarTareaPorId(1);
        
        assertNull(tarea, "Debe retornar null en lista vacía");
    }
    
    // ==================== PRUEBAS DE filtrarPorPrioridad() ====================
    
    @Test
    @Order(31)
    @DisplayName("Test 031: Filtrar por prioridad BAJA")
    void testFiltrarPorPrioridadBaja() {
        gestor.agregarTarea("Tarea baja 1", 1);
        gestor.agregarTarea("Tarea media", 2);
        gestor.agregarTarea("Tarea baja 2", 1);
        gestor.agregarTarea("Tarea alta", 3);
        
        List<Tarea> tareasBajas = gestor.filtrarPorPrioridad(1);
        
        assertEquals(2, tareasBajas.size(), "Debe haber 2 tareas de prioridad baja");
        assertTrue(tareasBajas.stream().allMatch(t -> t.getPrioridad() == 1),
                  "Todas deben tener prioridad 1");
    }
    
    @Test
    @Order(32)
    @DisplayName("Test 032: Filtrar por prioridad MEDIA")
    void testFiltrarPorPrioridadMedia() {
        gestor.agregarTarea("Tarea baja", 1);
        gestor.agregarTarea("Tarea media 1", 2);
        gestor.agregarTarea("Tarea media 2", 2);
        
        List<Tarea> tareasMedias = gestor.filtrarPorPrioridad(2);
        
        assertEquals(2, tareasMedias.size());
    }
    
    @Test
    @Order(33)
    @DisplayName("Test 033: Filtrar por prioridad sin resultados")
    void testFiltrarPorPrioridadSinResultados() {
        gestor.agregarTarea("Tarea baja", 1);
        gestor.agregarTarea("Tarea media", 2);
        
        List<Tarea> tareasAltas = gestor.filtrarPorPrioridad(3);
        
        assertTrue(tareasAltas.isEmpty(), "No debe haber tareas de prioridad alta");
    }
    
    @Test
    @Order(34)
    @DisplayName("Test 034: Filtrar en lista vacía")
    void testFiltrarPorPrioridadListaVacia() {
        List<Tarea> tareas = gestor.filtrarPorPrioridad(2);
        
        assertTrue(tareas.isEmpty());
    }
    
    // ==================== PRUEBAS DE ESTADÍSTICAS ====================
    
    @Test
    @Order(35)
    @DisplayName("Test 035: Contar tareas completadas")
    void testContarTareasCompletadas() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        Tarea t2 = gestor.agregarTarea("Tarea 2", 2);
        Tarea t3 = gestor.agregarTarea("Tarea 3", 3);
        
        gestor.marcarComoCompletada(t1.getId());
        gestor.marcarComoCompletada(t3.getId());
        
        assertEquals(2, gestor.contarTareasCompletadas(), "Debe haber 2 completadas");
    }
    
    @Test
    @Order(36)
    @DisplayName("Test 036: Contar tareas pendientes")
    void testContarTareasPendientes() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        gestor.agregarTarea("Tarea 2", 2);
        gestor.agregarTarea("Tarea 3", 3);
        
        gestor.marcarComoCompletada(t1.getId());
        
        assertEquals(2, gestor.contarTareasPendientes(), "Debe haber 2 pendientes");
    }
    
    @Test
    @Order(37)
    @DisplayName("Test 037: Calcular porcentaje de completadas")
    void testCalcularPorcentajeCompletadas() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        Tarea t2 = gestor.agregarTarea("Tarea 2", 2);
        gestor.agregarTarea("Tarea 3", 3);
        gestor.agregarTarea("Tarea 4", 1);
        
        gestor.marcarComoCompletada(t1.getId());
        gestor.marcarComoCompletada(t2.getId());
        
        double porcentaje = gestor.calcularPorcentajeCompletadas();
        
        assertEquals(50.0, porcentaje, 0.01, "Debe ser 50% (2 de 4)");
    }
    
    @Test
    @Order(38)
    @DisplayName("Test 038: Porcentaje de completadas con lista vacía")
    void testCalcularPorcentajeCompletadasListaVacia() {
        double porcentaje = gestor.calcularPorcentajeCompletadas();
        
        assertEquals(0.0, porcentaje, "Debe ser 0% en lista vacía");
    }
    
    @Test
    @Order(39)
    @DisplayName("Test 039: Contar por prioridad")
    void testContarPorPrioridad() {
        gestor.agregarTarea("Baja 1", 1);
        gestor.agregarTarea("Baja 2", 1);
        gestor.agregarTarea("Media 1", 2);
        gestor.agregarTarea("Alta 1", 3);
        gestor.agregarTarea("Alta 2", 3);
        gestor.agregarTarea("Alta 3", 3);
        
        assertEquals(2, gestor.contarPorPrioridad(1), "Debe haber 2 de prioridad baja");
        assertEquals(1, gestor.contarPorPrioridad(2), "Debe haber 1 de prioridad media");
        assertEquals(3, gestor.contarPorPrioridad(3), "Debe haber 3 de prioridad alta");
    }
    
    // ==================== PRUEBAS DE ESTADO ====================
    
    @Test
    @Order(40)
    @DisplayName("Test 040: Verificar que estaVacia() funciona correctamente")
    void testEstaVacia() {
        assertTrue(gestor.estaVacia(), "Debe estar vacío inicialmente");
        
        gestor.agregarTarea("Tarea", 2);
        assertFalse(gestor.estaVacia(), "No debe estar vacío después de agregar");
        
        gestor.limpiarTodas();
        assertTrue(gestor.estaVacia(), "Debe estar vacío después de limpiar");
    }
    
    @Test
    @Order(41)
    @DisplayName("Test 041: Limpiar todas las tareas")
    void testLimpiarTodas() {
        gestor.agregarTarea("Tarea 1", 1);
        gestor.agregarTarea("Tarea 2", 2);
        gestor.agregarTarea("Tarea 3", 3);
        
        gestor.limpiarTodas();
        
        assertTrue(gestor.estaVacia(), "Debe estar vacío");
        assertEquals(0, gestor.obtenerTotalTareas(), "Total debe ser 0");
    }
    
    @Test
    @Order(42)
    @DisplayName("Test 042: IDs se reinician después de limpiar")
    void testIdsSeReinicianDespuesLimpiar() {
        gestor.agregarTarea("Tarea 1", 1);
        gestor.agregarTarea("Tarea 2", 2);
        gestor.limpiarTodas();
        
        Tarea nuevaTarea = gestor.agregarTarea("Nueva tarea", 1);
        
        assertEquals(1, nuevaTarea.getId(), "El ID debe reiniciarse a 1");
    }
    
    // ==================== PRUEBAS DE INTEGRACIÓN ====================
    
    @Test
    @Order(43)
    @DisplayName("Test 043: Flujo completo - Agregar, Listar, Marcar, Eliminar")
    void testFlujoCompletoAgregarListarMarcarEliminar() {
        // Agregar
        Tarea t1 = gestor.agregarTarea("Estudiar Java", 2);
        Tarea t2 = gestor.agregarTarea("Hacer ejercicios", 1);
        
        // Listar
        List<Tarea> tareas = gestor.listarTareas();
        assertEquals(2, tareas.size());
        
        // Marcar como completada
        gestor.marcarComoCompletada(t1.getId());
        assertTrue(t1.isCompletado());
        
        // Eliminar
        gestor.eliminarTarea(t2.getId());
        assertEquals(1, gestor.obtenerTotalTareas());
    }
    
    @Test
    @Order(44)
    @DisplayName("Test 044: Escenario de uso real - Gestión de múltiples tareas")
    void testEscenarioUsoReal() {
        // Agregar tareas de diferentes prioridades
        gestor.agregarTarea("Comprar víveres", 1);
        Tarea urgente = gestor.agregarTarea("Entregar informe", 3);
        gestor.agregarTarea("Revisar correos", 2);
        gestor.agregarTarea("Llamar al dentista", 1);
        Tarea importante = gestor.agregarTarea("Reunión con cliente", 3);
        
        // Verificar estado inicial
        assertEquals(5, gestor.obtenerTotalTareas());
        assertEquals(0, gestor.contarTareasCompletadas());
        
        // Completar tareas urgentes
        gestor.marcarComoCompletada(urgente.getId());
        gestor.marcarComoCompletada(importante.getId());
        
        // Verificar estadísticas
        assertEquals(2, gestor.contarTareasCompletadas());
        assertEquals(3, gestor.contarTareasPendientes());
        assertEquals(40.0, gestor.calcularPorcentajeCompletadas(), 0.01);
        
        // Filtrar por prioridad alta
        List<Tarea> tareasAltas = gestor.filtrarPorPrioridad(3);
        assertEquals(2, tareasAltas.size());
        
        // Todas las tareas de prioridad alta deben estar completadas
        assertTrue(tareasAltas.stream().allMatch(Tarea::isCompletado));
    }
    
    @Test
    @Order(45)
    @DisplayName("Test 045: Cobertura - Obtener tareas completadas")
    void testObtenerTareasCompletadas() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        gestor.agregarTarea("Tarea 2", 2);
        Tarea t3 = gestor.agregarTarea("Tarea 3", 3);
        
        gestor.marcarComoCompletada(t1.getId());
        gestor.marcarComoCompletada(t3.getId());
        
        List<Tarea> completadas = gestor.obtenerTareasCompletadas();
        
        assertEquals(2, completadas.size());
        assertTrue(completadas.stream().allMatch(Tarea::isCompletado));
    }
    
    @Test
    @Order(46)
    @DisplayName("Test 046: Cobertura - Obtener tareas pendientes")
    void testObtenerTareasPendientes() {
        Tarea t1 = gestor.agregarTarea("Tarea 1", 1);
        gestor.agregarTarea("Tarea 2", 2);
        Tarea t3 = gestor.agregarTarea("Tarea 3", 3);
        
        gestor.marcarComoCompletada(t1.getId());
        
        List<Tarea> pendientes = gestor.obtenerTareasPendientes();
        
        assertEquals(2, pendientes.size());
        assertTrue(pendientes.stream().noneMatch(Tarea::isCompletado));
    }
    
    @Test
    @Order(47)
    @DisplayName("Test 047: Métodos de texto de prioridad")
    void testObtenerTextoPrioridad() {
        assertEquals("Baja", GestorTareas.obtenerTextoPrioridad(1));
        assertEquals("Media", GestorTareas.obtenerTextoPrioridad(2));
        assertEquals("Alta", GestorTareas.obtenerTextoPrioridad(3));
        assertEquals("No definida", GestorTareas.obtenerTextoPrioridad(999));
    }
    
    @Test
    @Order(48)
    @DisplayName("Test 048: Métodos de emoji de prioridad")
    void testObtenerEmojiPrioridad() {
        assertEquals("🟢", GestorTareas.obtenerEmojPrioridad(1));
        assertEquals("🟡", GestorTareas.obtenerEmojPrioridad(2));
        assertEquals("🔴", GestorTareas.obtenerEmojPrioridad(3));
        assertEquals("⚪", GestorTareas.obtenerEmojPrioridad(0));
    }
}
