# 🚀 SmartTask - Guía Rápida de Uso

## ⚡ Inicio Rápido

### 1. Compilar el Proyecto
```bash
cd SmartTask/src
javac -d ../bin *.java
```

### 2. Ejecutar la Aplicación
```bash
cd ../bin
java Main
```

---

## 📖 Cómo Usar la Aplicación

### Menú Principal
```
╔═════════════════════════════════════╗
║          MENÚ PRINCIPAL             ║
╠═════════════════════════════════════╣
║ 1. ➕ Agregar Tarea                ║
║ 2. 📋 Listar Todas las Tareas      ║
║ 3. ✅ Marcar Tarea como Completada ║
║ 4. 🗑️  Eliminar Tarea              ║
║ 5. 🔍 Buscar Tarea por ID          ║
║ 6. 🎯 Filtrar por Prioridad        ║
║ 7. 📊 Ver Estadísticas             ║
║ 0. 🚪 Salir                        ║
╚═════════════════════════════════════╝
```

---

## 🎯 Ejemplos de Uso

### ➕ Opción 1: Agregar Tarea

**Entrada:**
```
Opción: 1
Ingrese el nombre de la tarea: Estudiar Java POO
Seleccione la prioridad: 3
```

**Salida:**
```
✅ Tarea agregada exitosamente con ID: 1
```

### 📋 Opción 2: Listar Tareas

**Salida:**
```
┌─────────────────────────────────────────────────────────────────┐
│                    LISTA DE TAREAS                              │
└─────────────────────────────────────────────────────────────────┘

Total de tareas: 3
─────────────────────────────────────────────────────────────────
1. [ ] ID: 1 | Estudiar Java POO | Prioridad: Alta | Estado: Pendiente
2. [X] ID: 2 | Hacer ejercicios | Prioridad: Media | Estado: Completada
3. [ ] ID: 3 | Revisar código | Prioridad: Baja | Estado: Pendiente
```

### ✅ Opción 3: Marcar como Completada

**Entrada:**
```
Opción: 3
Ingrese el ID de la tarea a completar: 1
```

**Salida:**
```
✅ Tarea ID 1 marcada como completada.
```

### 🗑️ Opción 4: Eliminar Tarea

**Entrada:**
```
Opción: 4
Ingrese el ID de la tarea a eliminar: 2
```

**Salida:**
```
✅ Tarea ID 2 eliminada correctamente.
```

### 🔍 Opción 5: Buscar por ID

**Entrada:**
```
Opción: 5
Ingrese el ID de la tarea: 1
```

**Salida:**
```
✅ Tarea encontrada:
[X] ID: 1 | Estudiar Java POO | Prioridad: Alta | Estado: Completada
```

### 🎯 Opción 6: Filtrar por Prioridad

**Entrada:**
```
Opción: 6
Seleccione la prioridad:
1. 🟢 Baja
2. 🟡 Media
3. 🔴 Alta
Opción: 3
```

**Salida:**
```
📋 Tareas con prioridad Alta:
─────────────────────────────────────────────────────────────────
[X] ID: 1 | Estudiar Java POO | Prioridad: Alta | Estado: Completada
[ ] ID: 5 | Proyecto final | Prioridad: Alta | Estado: Pendiente

Total encontradas: 2
```

### 📊 Opción 7: Ver Estadísticas

**Salida:**
```
┌─────────────────────────────────────┐
│        ESTADÍSTICAS                 │
└─────────────────────────────────────┘

📊 Resumen General:
   Total de tareas: 5
   ✅ Completadas: 2 (40.0%)
   ⏳ Pendientes: 3 (60.0%)

🎯 Por Prioridad:
   🟢 Baja: 1
   🟡 Media: 2
   🔴 Alta: 2
```

---

## 🎓 Características del Sistema

### ✅ Gestión de Tareas
- Crear tareas con nombre y prioridad
- Listar todas las tareas
- Marcar tareas como completadas
- Eliminar tareas

### 🎯 Sistema de Prioridades
- **🟢 Baja (1)**: Tareas de baja importancia
- **🟡 Media (2)**: Tareas de importancia moderada
- **🔴 Alta (3)**: Tareas urgentes o importantes

### 📊 Funcionalidades Adicionales
- Buscar tareas por ID
- Filtrar tareas por nivel de prioridad
- Ver estadísticas completas del sistema
- Cálculo de porcentajes de completitud

### 🛡️ Validaciones
- ✅ Nombres de tareas no vacíos
- ✅ Prioridades en rango válido (1-3)
- ✅ Manejo de errores con mensajes claros
- ✅ Protección contra datos inválidos

---

## 🔧 Comandos Útiles

### Compilar
```bash
javac -d bin src\*.java
```

### Ejecutar
```bash
cd bin
java Main
```

### Generar Javadoc
```bash
javadoc -d docs -encoding UTF-8 -charset UTF-8 -author -version -sourcepath src src\*.java
```

### Ver Javadoc
```bash
start docs\index.html
```

### Limpiar Binarios
```bash
del /Q bin\*.class
```

---

## 📚 Documentación Adicional

1. **RESUMEN_MEJORAS.md** - Resumen completo de todas las mejoras implementadas
2. **DOCUMENTACION_TECNICA.md** - Documentación técnica detallada
3. **docs/index.html** - Javadoc HTML completo

---

## ❓ Preguntas Frecuentes

### ¿Cómo agrego una tarea?
Selecciona la opción 1 del menú, ingresa el nombre y la prioridad.

### ¿Puedo editar una tarea existente?
Actualmente puedes marcarla como completada (opción 3) pero no editarla. Esta funcionalidad puede agregarse en futuras versiones.

### ¿Las tareas se guardan al cerrar el programa?
No, actualmente las tareas solo se mantienen en memoria. Para persistencia, se puede implementar guardado en archivo o base de datos.

### ¿Qué pasa si ingreso datos inválidos?
El sistema valida todos los datos y muestra mensajes de error claros cuando algo no es válido.

### ¿Cómo veo el código fuente?
Todos los archivos .java están en la carpeta `src/`:
- `Tarea.java` - Modelo de datos
- `GestorTareas.java` - Lógica de negocio
- `Main.java` - Interfaz de usuario

---

## 🎯 Tips de Uso

1. **Usa prioridades consistentemente**: Define un criterio claro para asignar prioridades
2. **Revisa las estadísticas regularmente**: Te ayudarán a ver tu progreso
3. **Marca tareas completadas**: Mantén tu lista actualizada
4. **Elimina tareas obsoletas**: Mantén tu lista limpia y relevante

---

## 🚀 Mejoras Futuras Sugeridas

### Funcionalidades
- [ ] Editar tareas existentes
- [ ] Fechas de vencimiento
- [ ] Categorías o etiquetas
- [ ] Notas adicionales por tarea
- [ ] Subtareas

### Técnicas
- [ ] Persistencia en archivo
- [ ] Base de datos
- [ ] Interfaz gráfica
- [ ] Tests unitarios
- [ ] Exportar/Importar tareas

---

**¡Disfruta usando SmartTask! 🎉**

Para más información, consulta:
- `DOCUMENTACION_TECNICA.md` - Detalles técnicos
- `RESUMEN_MEJORAS.md` - Todas las mejoras implementadas
- `docs/index.html` - Javadoc completo
