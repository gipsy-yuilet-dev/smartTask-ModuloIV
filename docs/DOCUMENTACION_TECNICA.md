# 📚 Documentación Técnica - SmartTask System

## 📋 Índice
1. [Introducción](#introducción)
2. [Arquitectura del Sistema](#arquitectura-del-sistema)
3. [Clases y Componentes](#clases-y-componentes)
4. [Principios de POO Aplicados](#principios-de-poo-aplicados)
5. [Buenas Prácticas Implementadas](#buenas-prácticas-implementadas)
6. [Guía de Uso](#guía-de-uso)
7. [Javadoc](#javadoc)

---

## 🎯 Introducción

**SmartTask** es una aplicación de gestión de tareas desarrollada en Java que implementa los principios de Programación Orientada a Objetos (POO). El sistema permite a los usuarios crear, listar, completar, eliminar y filtrar tareas de manera eficiente.

### Características Principales
- ✅ Gestión completa de tareas (CRUD)
- 🎯 Sistema de prioridades (Baja, Media, Alta)
- 📊 Estadísticas detalladas
- 🔍 Búsqueda y filtrado de tareas
- 🛡️ Validación de datos robusta
- 📝 Documentación Javadoc completa

---

## 🏗️ Arquitectura del Sistema

El sistema sigue el patrón de arquitectura **Modelo-Vista-Controlador (MVC)** simplificado:

```
┌─────────────────────────────────────────┐
│            Main.java (Vista)            │
│  - Interfaz de usuario                  │
│  - Menú interactivo                     │
│  - Entrada/Salida por consola          │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│      GestorTareas.java (Controlador)    │
│  - Lógica de negocio                    │
│  - Operaciones CRUD                     │
│  - Gestión de colecciones              │
│  - Cálculos y estadísticas             │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│         Tarea.java (Modelo)             │
│  - Encapsulamiento de datos            │
│  - Atributos privados                  │
│  - Getters/Setters validados           │
│  - Métodos de negocio                  │
└─────────────────────────────────────────┘
```

---

## 📦 Clases y Componentes

### 1. **Clase Tarea** (Modelo)

**Responsabilidad**: Representa una tarea individual del sistema.

#### Atributos Privados (Encapsulados)
```java
private int id;              // Identificador único
private String nombre;       // Descripción de la tarea
private int prioridad;       // 1=Baja, 2=Media, 3=Alta
private boolean completado;  // Estado de la tarea
```

#### Constructores
- `Tarea()` - Constructor por defecto
- `Tarea(int id, String nombre, int prioridad, boolean completado)` - Constructor completo
- `Tarea(int id, String nombre, int prioridad)` - Constructor simplificado

#### Métodos Principales
| Método | Descripción |
|--------|-------------|
| `getId()` | Obtiene el ID de la tarea |
| `setNombre(String)` | Establece el nombre (con validación) |
| `setPrioridad(int)` | Establece la prioridad (con validación 1-3) |
| `marcarCompletada()` | Marca la tarea como completada |
| `marcarPendiente()` | Marca la tarea como pendiente |
| `obtenerTextoPrioridad()` | Convierte prioridad numérica a texto |
| `toString()` | Representación en texto de la tarea |

#### Validaciones Implementadas
- ✅ El nombre no puede ser nulo ni vacío
- ✅ La prioridad debe estar entre 1 y 3
- ✅ Lanza `IllegalArgumentException` en caso de datos inválidos

---

### 2. **Clase GestorTareas** (Controlador/Lógica de Negocio)

**Responsabilidad**: Gestiona todas las operaciones sobre las tareas.

#### Atributos
```java
private final List<Tarea> listaTareas;  // Colección de tareas
private int contadorId;                  // Generador de IDs únicos
```

#### Constantes
```java
public static final int PRIORIDAD_BAJA = 1;
public static final int PRIORIDAD_MEDIA = 2;
public static final int PRIORIDAD_ALTA = 3;
```

#### Operaciones CRUD

**Create (Crear)**
```java
public Tarea agregarTarea(String nombre, int prioridad)
```
- Valida los datos de entrada
- Genera ID automáticamente
- Retorna la tarea creada

**Read (Leer)**
```java
public List<Tarea> listarTareas()
public Tarea buscarTareaPorId(int id)
public List<Tarea> filtrarPorPrioridad(int prioridad)
public List<Tarea> obtenerTareasCompletadas()
public List<Tarea> obtenerTareasPendientes()
```

**Update (Actualizar)**
```java
public boolean marcarComoCompletada(int id)
```

**Delete (Eliminar)**
```java
public boolean eliminarTarea(int id)
```

#### Métodos de Estadísticas
| Método | Retorno | Descripción |
|--------|---------|-------------|
| `obtenerTotalTareas()` | int | Total de tareas |
| `contarTareasCompletadas()` | int | Tareas completadas |
| `contarTareasPendientes()` | int | Tareas pendientes |
| `contarPorPrioridad(int)` | int | Tareas por prioridad |
| `calcularPorcentajeCompletadas()` | double | % de completitud |
| `calcularPorcentajePendientes()` | double | % pendientes |

#### Métodos de Utilidad
```java
public boolean estaVacia()
public void limpiarTodas()
public static String obtenerTextoPrioridad(int prioridad)
public static String obtenerEmojPrioridad(int prioridad)
```

---

### 3. **Clase Main** (Vista/Interfaz de Usuario)

**Responsabilidad**: Gestiona la interacción con el usuario.

#### Atributos
```java
private static GestorTareas gestorTareas;
private static Scanner scanner;
```

#### Menú Principal
```
1. ➕ Agregar Tarea
2. 📋 Listar Todas las Tareas
3. ✅ Marcar Tarea como Completada
4. 🗑️ Eliminar Tarea
5. 🔍 Buscar Tarea por ID
6. 🎯 Filtrar por Prioridad
7. 📊 Ver Estadísticas
0. 🚪 Salir
```

#### Métodos Privados
- `mostrarMenu()` - Muestra el menú de opciones
- `leerOpcion()` - Lee y valida entrada del usuario
- `agregarTarea()` - Interfaz para agregar tarea
- `listarTareas()` - Interfaz para listar tareas
- `marcarTareaCompletada()` - Interfaz para marcar completada
- `eliminarTarea()` - Interfaz para eliminar
- `buscarTareaPorId()` - Interfaz para buscar
- `filtrarTareasPorPrioridad()` - Interfaz para filtrar
- `mostrarEstadisticas()` - Interfaz para estadísticas

---

## 🎓 Principios de POO Aplicados

### 1. **Encapsulamiento** ✅

**Implementación en `Tarea.java`**:
```java
// ❌ ANTES (Sin encapsulamiento)
public int id;
public String nombre;

// ✅ AHORA (Con encapsulamiento)
private int id;
private String nombre;

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre no puede estar vacío");
    }
    this.nombre = nombre.trim();
}
```

**Beneficios**:
- 🔒 Los datos están protegidos de acceso directo
- ✅ Validación centralizada en los setters
- 🛡️ Control total sobre cómo se modifican los datos

### 2. **Abstracción** ✅

**Separación de Responsabilidades**:
- `Tarea`: Solo se preocupa de sus propios datos
- `GestorTareas`: Gestiona la colección y lógica de negocio
- `Main`: Solo maneja la presentación

### 3. **Modularidad** ✅

**Cada clase tiene una única responsabilidad**:
```java
// Main - Solo UI
private static void agregarTarea() {
    // ... recopila datos del usuario ...
    gestorTareas.agregarTarea(nombre, prioridad);
}

// GestorTareas - Solo lógica
public Tarea agregarTarea(String nombre, int prioridad) {
    // ... valida y crea la tarea ...
}
```

### 4. **Cohesión Alta** ✅

Cada clase agrupa métodos relacionados:
- `Tarea`: Métodos sobre UNA tarea
- `GestorTareas`: Métodos sobre COLECCIÓN de tareas
- `Main`: Métodos de INTERFAZ de usuario

### 5. **Acoplamiento Bajo** ✅

`Main` no conoce la implementación interna de `GestorTareas`:
```java
// Main no sabe que internamente usa ArrayList
List<Tarea> tareas = gestorTareas.listarTareas();
```

---

## ✨ Buenas Prácticas Implementadas

### 1. **Documentación Javadoc Completa** 📝

Cada clase, método y atributo está documentado:

```java
/**
 * Agrega una nueva tarea al sistema.
 * El ID se asigna automáticamente de forma incremental.
 * 
 * @param nombre Descripción de la tarea
 * @param prioridad Nivel de prioridad (1: Baja, 2: Media, 3: Alta)
 * @return La tarea creada
 * @throws IllegalArgumentException si el nombre es nulo/vacío o la prioridad es inválida
 */
public Tarea agregarTarea(String nombre, int prioridad) {
    // ...
}
```

### 2. **Validación de Datos** ✅

```java
// Validación en setters
public void setPrioridad(int prioridad) {
    if (prioridad < 1 || prioridad > 3) {
        throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3");
    }
    this.prioridad = prioridad;
}
```

### 3. **Nombres Descriptivos** 📛

```java
// ❌ MAL
public void mt() { }

// ✅ BIEN
public void marcarTareaCompletada() { }
```

### 4. **Constantes para Valores Mágicos** 🔢

```java
// ❌ MAL
if (prioridad == 1) { }

// ✅ BIEN
public static final int PRIORIDAD_BAJA = 1;
if (prioridad == PRIORIDAD_BAJA) { }
```

### 5. **Manejo de Excepciones** ⚠️

```java
try {
    Tarea nuevaTarea = gestorTareas.agregarTarea(nombre, prioridad);
    System.out.println("✅ Tarea agregada exitosamente");
} catch (IllegalArgumentException e) {
    System.out.println("❌ Error: " + e.getMessage());
}
```

### 6. **Inmutabilidad Defensiva** 🛡️

```java
// Retorna una copia para evitar modificación externa
public List<Tarea> listarTareas() {
    return new ArrayList<>(listaTareas);
}
```

### 7. **Uso de `final` para Colecciones** 🔒

```java
private final List<Tarea> listaTareas;  // La referencia no puede cambiar
```

### 8. **Organización del Código** 📂

```java
public class Tarea {
    // ==================== ATRIBUTOS ====================
    private int id;
    
    // ==================== CONSTRUCTORES ====================
    public Tarea() { }
    
    // ==================== GETTERS Y SETTERS ====================
    public int getId() { }
    
    // ==================== MÉTODOS ADICIONALES ====================
    public void marcarCompletada() { }
}
```

### 9. **DRY (Don't Repeat Yourself)** 🔄

```java
// Método reutilizable
public static String obtenerTextoPrioridad(int prioridad) {
    switch (prioridad) {
        case PRIORIDAD_BAJA: return "Baja";
        case PRIORIDAD_MEDIA: return "Media";
        case PRIORIDAD_ALTA: return "Alta";
        default: return "No definida";
    }
}
```

### 10. **Código No Spaghetti** 🍝

**Características del código limpio implementado**:
- ✅ Métodos cortos y específicos
- ✅ Una responsabilidad por método
- ✅ Indentación consistente
- ✅ Sin código duplicado
- ✅ Flujo lógico claro

---

## 📖 Guía de Uso

### Compilación

```bash
# Compilar el proyecto
cd SmartTask/src
javac -d ../bin *.java
```

### Ejecución

```bash
# Ejecutar la aplicación
cd ../bin
java Main
```

### Uso del Sistema

#### 1. Agregar una Tarea
```
Opción: 1
Ingrese el nombre de la tarea: Estudiar Java
Seleccione la prioridad: 3 (Alta)
✅ Tarea agregada exitosamente con ID: 1
```

#### 2. Listar Tareas
```
Opción: 2
[ ] ID: 1 | Estudiar Java | Prioridad: Alta | Estado: Pendiente
```

#### 3. Marcar como Completada
```
Opción: 3
Ingrese el ID: 1
✅ Tarea ID 1 marcada como completada
```

#### 4. Ver Estadísticas
```
Opción: 7
📊 Resumen General:
   Total de tareas: 1
   ✅ Completadas: 1 (100.0%)
   ⏳ Pendientes: 0 (0.0%)
```

---

## 📄 Javadoc

### Generar Documentación HTML

```bash
# Generar Javadoc
cd SmartTask/src
javadoc -d ../docs -encoding UTF-8 -charset UTF-8 *.java
```

### Ver Documentación

```bash
# Abrir en navegador
start ../docs/index.html
```

---

## 📊 Diagrama de Clases UML

```
┌─────────────────────────────────────┐
│           <<class>>                 │
│            Tarea                    │
├─────────────────────────────────────┤
│ - id: int                           │
│ - nombre: String                    │
│ - prioridad: int                    │
│ - completado: boolean               │
├─────────────────────────────────────┤
│ + Tarea()                           │
│ + Tarea(int, String, int, boolean)  │
│ + getId(): int                      │
│ + setNombre(String): void           │
│ + setPrioridad(int): void           │
│ + marcarCompletada(): void          │
│ + toString(): String                │
└─────────────────────────────────────┘
                 △
                 │ usa
                 │
┌─────────────────────────────────────┐
│           <<class>>                 │
│         GestorTareas                │
├─────────────────────────────────────┤
│ - listaTareas: List<Tarea>          │
│ - contadorId: int                   │
│ + PRIORIDAD_BAJA: int               │
│ + PRIORIDAD_MEDIA: int              │
│ + PRIORIDAD_ALTA: int               │
├─────────────────────────────────────┤
│ + agregarTarea(String, int): Tarea  │
│ + listarTareas(): List<Tarea>       │
│ + marcarComoCompletada(int): boolean│
│ + eliminarTarea(int): boolean       │
│ + buscarTareaPorId(int): Tarea      │
│ + obtenerTotalTareas(): int         │
└─────────────────────────────────────┘
                 △
                 │ usa
                 │
┌─────────────────────────────────────┐
│           <<class>>                 │
│             Main                    │
├─────────────────────────────────────┤
│ - gestorTareas: GestorTareas        │
│ - scanner: Scanner                  │
├─────────────────────────────────────┤
│ + main(String[]): void              │
│ - mostrarMenu(): void               │
│ - agregarTarea(): void              │
│ - listarTareas(): void              │
│ - marcarTareaCompletada(): void     │
└─────────────────────────────────────┘
```

---

## 🎯 Conclusión

El proyecto **SmartTask** demuestra la aplicación correcta de:

1. ✅ **Encapsulamiento**: Atributos privados con getters/setters validados
2. ✅ **Separación de Responsabilidades**: Modelo-Vista-Controlador
3. ✅ **Buenas Prácticas**: Javadoc, validación, constantes, código limpio
4. ✅ **Código Mantenible**: No spaghetti, DRY, nombres descriptivos
5. ✅ **Robustez**: Manejo de excepciones y validaciones

---

**Autor**: Yulieta (Melek) Eyzaguirre  
**Versión**: 2.0  
**Fecha**: 2026-02-12  
**Curso**: Talento Digital - Módulo IV - POO en Java
