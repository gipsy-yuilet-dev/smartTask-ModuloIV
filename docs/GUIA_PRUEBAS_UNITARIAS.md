# 📚 GUÍA COMPLETA DE PRUEBAS UNITARIAS EN JAVA CON JUNIT5

## 🎯 **Índice**

1. [Introducción a las Pruebas Unitarias](#introducción)
2. [Configuración de JUnit5](#configuración)
3. [Anatomía de una Prueba Unitaria](#anatomía)
4. [Anotaciones de JUnit5](#anotaciones)
5. [Assertions (Aserciones)](#assertions)
6. [Buenas Prácticas](#buenas-prácticas)
7. [Patrones de Testing](#patrones)
8. [Cobertura de Código](#cobertura)
9. [Ejemplos Prácticos](#ejemplos)
10. [Troubleshooting](#troubleshooting)

---

## 📖 **1. Introducción a las Pruebas Unitarias** {#introducción}

### ¿Qué son las Pruebas Unitarias?

Las pruebas unitarias son **piezas de código que verifican el comportamiento de unidades individuales** de tu aplicación (métodos, clases, funciones). Son la base de la pirámide de testing.

### ¿Por qué son importantes?

✅ **Detección temprana de bugs**: Encuentran errores antes de producción  
✅ **Documentación viva**: Los tests muestran cómo usar el código  
✅ **Refactoring seguro**: Permite cambiar código con confianza  
✅ **Diseño mejorado**: Código testeable = código bien diseñado  
✅ **Regresión**: Evita que bugs antiguos vuelvan  

### Principios FIRST

- **F**ast (Rápidas): Deben ejecutarse en milisegundos
- **I**solated (Aisladas): Independientes entre sí
- **R**epeatable (Repetibles): Mismo resultado siempre
- **S**elf-Validating (Auto-validables): Pass o Fail, sin ambigüedad
- **T**imely (Oportunas): Escritas junto con el código

---

## ⚙️ **2. Configuración de JUnit5** {#configuración}

### Opción A: Proyecto Java Simple (VSCode)

#### Paso 1: Descargar JARs de JUnit5

```bash
# Crear carpeta lib si no existe
mkdir lib

# Descargar desde Maven Central (manualmente o con curl)
# junit-jupiter-api (API)
# junit-jupiter-engine (Motor de ejecución)
# junit-platform-console-standalone (Para ejecutar desde consola)
```

**URLs para descargar:**
- [JUnit Platform Console Standalone](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/junit-platform-console-standalone-1.10.1.jar)

#### Paso 2: Configurar `.vscode/settings.json`

```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": [
        "lib/**/*.jar"
    ]
}
```

#### Paso 3: Estructura de Directorios

```
proyecto/
├── src/
│   ├── Tarea.java
│   ├── GestorTareas.java
│   └── Main.java
├── test/
│   ├── TareaTest.java
│   └── GestorTareasTest.java
├── lib/
│   └── junit-platform-console-standalone-1.10.1.jar
├── bin/
└── .vscode/
    └── settings.json
```

### Opción B: Proyecto Maven

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Opción C: Proyecto Gradle

```gradle
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.1'
}

test {
    useJUnitPlatform()
}
```

---

## 🔬 **3. Anatomía de una Prueba Unitaria** {#anatomía}

### Estructura AAA (Arrange-Act-Assert)

```java
@Test
@DisplayName("Test: Sumar dos números positivos")
void testSumar() {
    // ARRANGE (Preparar): Configurar el escenario
    Calculadora calc = new Calculadora();
    int a = 5;
    int b = 3;
    
    // ACT (Actuar): Ejecutar la acción
    int resultado = calc.sumar(a, b);
    
    // ASSERT (Afirmar): Verificar el resultado
    assertEquals(8, resultado, "5 + 3 debe ser 8");
}
```

### Clase de Test Completa

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Suite de Pruebas: Calculadora")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculadoraTest {
    
    private Calculadora calc;
    
    // Se ejecuta una vez ANTES de todos los tests
    @BeforeAll
    static void setupAll() {
        System.out.println("Iniciando suite de pruebas");
    }
    
    // Se ejecuta ANTES de cada test
    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }
    
    // Se ejecuta DESPUÉS de cada test
    @AfterEach
    void tearDown() {
        calc = null;
    }
    
    // Se ejecuta una vez DESPUÉS de todos los tests
    @AfterAll
    static void tearDownAll() {
        System.out.println("Finalizando suite de pruebas");
    }
    
    @Test
    @Order(1)
    @DisplayName("Test: Sumar números positivos")
    void testSumar() {
        assertEquals(8, calc.sumar(5, 3));
    }
    
    @Test
    @Order(2)
    @DisplayName("Test: División por cero lanza excepción")
    void testDivisionPorCero() {
        assertThrows(ArithmeticException.class, () -> {
            calc.dividir(10, 0);
        });
    }
}
```

---

## 🏷️ **4. Anotaciones de JUnit5** {#anotaciones}

### Anotaciones Principales

| Anotación | Descripción | Uso |
|-----------|-------------|-----|
| `@Test` | Marca un método como prueba | Obligatorio para cada test |
| `@BeforeEach` | Ejecuta antes de cada test | Inicialización |
| `@AfterEach` | Ejecuta después de cada test | Limpieza |
| `@BeforeAll` | Ejecuta una vez antes de todos | Setup global (static) |
| `@AfterAll` | Ejecuta una vez después de todos | Teardown global (static) |
| `@DisplayName("...")` | Nombre descriptivo del test | Mejor legibilidad |
| `@Disabled` | Deshabilita un test | Tests temporales |
| `@RepeatedTest(n)` | Repite un test n veces | Pruebas de estabilidad |
| `@Tag("nombre")` | Etiqueta para filtrar tests | Organización |
| `@Order(n)` | Orden de ejecución | Requiere `@TestMethodOrder` |

### Anotaciones Avanzadas

```java
// Test parametrizado
@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 5, 8})
void testNumerosPrimos(int numero) {
    assertTrue(esPrimo(numero));
}

// Test con timeout
@Test
@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
void testRapido() {
    // Debe completarse en menos de 100ms
}

// Test anidados
@Nested
@DisplayName("Pruebas de Suma")
class SumaTests {
    @Test
    void testSumaPositivos() { ... }
    
    @Test
    void testSumaNegativos() { ... }
}
```

---

## ✔️ **5. Assertions (Aserciones)** {#assertions}

### Assertions Básicas

```java
// Igualdad
assertEquals(esperado, actual);
assertEquals(esperado, actual, "Mensaje de error");
assertEquals(10.5, resultado, 0.01); // Con delta para doubles

// No igualdad
assertNotEquals(3, resultado);

// Verdadero/Falso
assertTrue(condicion);
assertFalse(condicion);

// Null/Not Null
assertNull(objeto);
assertNotNull(objeto);

// Misma instancia
assertSame(obj1, obj2);
assertNotSame(obj1, obj2);
```

### Assertions Avanzadas

```java
// Arrays
assertArrayEquals(new int[]{1, 2, 3}, resultado);

// Lanzamiento de excepciones
Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    metodoQueLanzaExcepcion();
});
assertTrue(exception.getMessage().contains("texto esperado"));

// NO lanza excepciones
assertDoesNotThrow(() -> {
    metodoSeguro();
});

// Timeout
assertTimeout(Duration.ofSeconds(1), () -> {
    metodoQueDebeSerRapido();
});

// Múltiples assertions (todas se ejecutan)
assertAll("Verificar persona",
    () -> assertEquals("Juan", persona.getNombre()),
    () -> assertEquals(25, persona.getEdad()),
    () -> assertTrue(persona.isActivo())
);
```

### Assertions para Colecciones (Java 8+)

```java
List<String> lista = Arrays.asList("a", "b", "c");

// Tamaño
assertEquals(3, lista.size());

// Contiene elemento
assertTrue(lista.contains("b"));

// Verificar todos los elementos
assertTrue(lista.stream().allMatch(s -> s.length() == 1));

// Verificar al menos uno
assertTrue(lista.stream().anyMatch(s -> s.equals("a")));

// Ninguno cumple condición
assertTrue(lista.stream().noneMatch(s -> s.length() > 1));
```

---

## 📘 **6. Buenas Prácticas** {#buenas-prácticas}

### ✅ **DO (Hacer)**

1. **Un concepto por test**
```java
// ✅ BIEN
@Test
void testAgregarTareaIncrementaContador() {
    gestor.agregarTarea("Tarea", 2);
    assertEquals(1, gestor.obtenerTotal());
}

@Test
void testAgregarTareaGuardaNombreCorrectamente() {
    Tarea t = gestor.agregarTarea("Nueva", 2);
    assertEquals("Nueva", t.getNombre());
}

// ❌ MAL - Prueba múltiples cosas
@Test
void testAgregarTarea() {
    Tarea t = gestor.agregarTarea("Nueva", 2);
    assertEquals(1, gestor.obtenerTotal());
    assertEquals("Nueva", t.getNombre());
    assertTrue(t.esPrioridadValida());
}
```

2. **Nombres descriptivos**
```java
// ✅ BIEN
@Test
void testEliminarTareaInexistenteRetornaFalse() { }

// ❌ MAL
@Test
void test1() { }
```

3. **Arrange-Act-Assert claro**
```java
@Test
void testMarcarComoCompletada() {
    // Arrange
    Tarea tarea = new Tarea(1, "Test", 2);
    
    // Act
    tarea.marcarCompletada();
    
    // Assert
    assertTrue(tarea.isCompletado());
}
```

4. **Probar casos límite**
```java
// Valores en los límites
@Test
void testPrioridadEnLimiteInferior() {
    tarea.setPrioridad(1); // Mínimo válido
    assertTrue(tarea.esPrioridadValida());
}

@Test
void testPrioridadEnLimiteSuperior() {
    tarea.setPrioridad(3); // Máximo válido
    assertTrue(tarea.esPrioridadValida());
}

// Valores fuera de límites
@Test
void testPrioridadDebajoDeLimite() {
    assertThrows(IllegalArgumentException.class, () -> {
        tarea.setPrioridad(0);
    });
}
```

5. **Independencia entre tests**
```java
// ✅ BIEN - Cada test crea su propia instancia
@BeforeEach
void setUp() {
    gestor = new GestorTareas();
}

// ❌ MAL - Estado compartido
private static GestorTareas gestor = new GestorTareas();
```

### ❌ **DON'T (No Hacer)**

1. **No dependas del orden de ejecución**
2. **No uses datos hardcoded compartidos**
3. **No pruebes métodos privados directamente**
4. **No uses lógica compleja en los tests**
5. **No ignores tests fallidos**

---

## 🎨 **7. Patrones de Testing** {#patrones}

### Test de Happy Path (Caso Feliz)

```java
@Test
void testAgregarTareaExitoso() {
    Tarea t = gestor.agregarTarea("Mi tarea", 2);
    
    assertNotNull(t);
    assertEquals("Mi tarea", t.getNombre());
}
```

### Test de Error Path (Caso de Error)

```java
@Test
void testAgregarTareaNombreNullLanzaExcepcion() {
    assertThrows(IllegalArgumentException.class, () -> {
        gestor.agregarTarea(null, 2);
    });
}
```

### Test de Boundary (Límites)

```java
@Test
void testLimites() {
    // Límite inferior
    tarea.setPrioridad(1);
    assertEquals(1, tarea.getPrioridad());
    
    // Límite superior
    tarea.setPrioridad(3);
    assertEquals(3, tarea.getPrioridad());
}
```

### Test de Estado (State Testing)

```java
@Test
void testTransicionDeEstados() {
    // Estado inicial
    assertFalse(tarea.isCompletado());
    
    // Transición
    tarea.marcarCompletada();
    
    // Nuevo estado
    assertTrue(tarea.isCompletado());
}
```

### Test de Integración

```java
@Test
void testFlujoCompletoAgregarYEliminar() {
    // Agregar
    Tarea t = gestor.agregarTarea("Tarea", 2);
    assertEquals(1, gestor.obtenerTotal());
    
    // Eliminar
    gestor.eliminarTarea(t.getId());
    assertEquals(0, gestor.obtenerTotal());
    assertTrue(gestor.estaVacia());
}
```

---

## 📊 **8. Cobertura de Código** {#cobertura}

### ¿Qué es la Cobertura?

La cobertura mide qué porcentaje del código es ejecutado por los tests.

### Tipos de Cobertura

1. **Cobertura de Líneas**: % de líneas ejecutadas
2. **Cobertura de Ramas**: % de ramas if/switch ejecutadas
3. **Cobertura de Métodos**: % de métodos llamados
4. **Cobertura de Clases**: % de clases instanciadas

### Meta Objetivo

- ✅ **Mínimo 80%** de cobertura de líneas
- ✅ **Ideal 90%+** de cobertura
- ⚠️ **100% no siempre es realista ni necesario**

### Cómo Medir Cobertura

#### Con VSCode + Extension

1. Instalar extensión: "Coverage Gutters"
2. Usar herramienta: JaCoCo

#### Con JaCoCo (Maven)

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

```bash
mvn clean test
# Reporte en: target/site/jacoco/index.html
```

### Qué NO cubrir

- Getters/Setters simples (sin lógica)
- Constructores triviales
- Código generado automáticamente
- Clases de configuración pura

---

## 💡 **9. Ejemplos Prácticos** {#ejemplos}

### Ejemplo 1: Probar Clase con Validaciones

```java
public class Usuario {
    private String email;
    
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }
}

// Tests
@Test
void testSetEmailValido() {
    Usuario user = new Usuario();
    user.setEmail("test@example.com");
    assertEquals("test@example.com", user.getEmail());
}

@Test
void testSetEmailNullLanzaExcepcion() {
    Usuario user = new Usuario();
    assertThrows(IllegalArgumentException.class, () -> {
        user.setEmail(null);
    });
}

@Test
void testSetEmailSinArrobaLanzaExcepcion() {
    Usuario user = new Usuario();
    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
        user.setEmail("invalido");
    });
    assertTrue(ex.getMessage().contains("inválido"));
}
```

### Ejemplo 2: Probar Colecciones

```java
@Test
void testListarTareasRetornaCopiaDefensiva() {
    gestor.agregarTarea("Tarea 1", 1);
    
    List<Tarea> lista1 = gestor.listarTareas();
    List<Tarea> lista2 = gestor.listarTareas();
    
    assertNotSame(lista1, lista2, "Debe retornar nueva instancia");
    
    // Modificar lista no afecta al gestor
    lista1.clear();
    assertEquals(1, gestor.obtenerTotal());
}
```

### Ejemplo 3: Probar Excepciones Personalizadas

```java
@Test
void testExcepcionConMensajeEspecifico() {
    Exception ex = assertThrows(TareaNoEncontradaException.class, () -> {
        gestor.buscarTarea(999);
    });
    
    assertEquals("Tarea con ID 999 no encontrada", ex.getMessage());
}
```

---

## 🔧 **10. Troubleshooting** {#troubleshooting}

### Problema 1: Tests no se ejecutan

**Síntomas**: Los tests no aparecen o no se ejecutan

**Soluciones**:
1. Verificar que la clase tenga `@Test`
2. Verificar que sea `public void`
3. Verificar que JUnit esté en el classpath
4. Limpiar y recompilar: `Clean Java Language Server Workspace`

### Problema 2: ClassNotFoundException

**Síntomas**: Error al ejecutar tests

**Soluciones**:
```java
// Verificar imports correctos
import org.junit.jupiter.api.Test; // ✅ JUnit5
// NO: import org.junit.Test; // ❌ JUnit4
```

### Problema 3: Tests pasan localmente pero fallan en CI

**Causas comunes**:
- Dependencia del orden de ejecución
- Dependencia de zona horaria
- Uso de datos hardcoded
- Tests no determinísticos

**Solución**: Hacer tests independientes y repetibles

### Problema 4: Tests lentos

**Optimizaciones**:
- Evitar operaciones de I/O
- Usar mocks para dependencias externas
- No hacer Thread.sleep()
- Minimizar setup/teardown pesados

---

## 📝 **Resumen de Comandos**

### Compilar y Ejecutar Tests (VSCode)

```bash
# Compilar todo
javac -d bin -cp "lib/*" src/*.java test/*.java

# Ejecutar todos los tests
java -jar lib/junit-platform-console-standalone-1.10.1.jar \
  --class-path bin \
  --scan-class-path

# Ejecutar una clase específica
java -jar lib/junit-platform-console-standalone-1.10.1.jar \
  --class-path bin \
  --select-class GestorTareasTest
```

### Con Maven

```bash
mvn test                    # Ejecutar todos los tests
mvn test -Dtest=TareaTest  # Ejecutar un test específico
mvn clean test             # Limpiar y ejecutar
```

---

## 🎓 **Certificación de Conocimiento**

### Checklist de Competencias

- [ ] Sé qué es una prueba unitaria y por qué es importante
- [ ] Puedo configurar JUnit5 en mi proyecto
- [ ] Conozco la estructura AAA (Arrange-Act-Assert)
- [ ] Uso correctamente las anotaciones @Test, @BeforeEach, @AfterEach
- [ ] Sé usar assertions: assertEquals, assertTrue, assertThrows
- [ ] Escribo tests independientes y repetibles
- [ ] Pruebo casos felices, errores y límites
- [ ] Mis tests tienen nombres descriptivos
- [ ] Logro >80% de cobertura de código
- [ ] Entiendo la diferencia entre pruebas unitarias e integración

---

## 📚 **Recursos Adicionales**

- [Documentación Oficial JUnit5](https://junit.org/junit5/docs/current/user-guide/)
- [Javadoc JUnit5 API](https://junit.org/junit5/docs/current/api/)
- [Effective Unit Testing (Libro)](https://www.manning.com/books/effective-unit-testing)
- [Test Driven Development by Kent Beck](https://www.kentbeck.com/)

---

## ✨ **Conclusión**

Las pruebas unitarias son una **inversión**, no un costo. Te ahorran tiempo a largo plazo, mejoran la calidad del código y te dan confianza para hacer cambios.

> "Code without tests is broken by design." - Jacob Kaplan-Moss

**¡Empieza a testear hoy y construye software más confiable!** 🚀

---

**Última actualización**: Febrero 2026  
**Versión**: 1.0  
**Autor**: Yulieta (Melek) Eyzaguirre
