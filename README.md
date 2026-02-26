# SmartTask

SmartTask es una pequeña aplicación de consola en Java para gestionar tareas (to‑dos). Permite agregar, listar, marcar como completadas, eliminar y filtrar tareas por prioridad desde un menú interactivo en la terminal.

---

## Contenido del repositorio

- `src/` : código fuente Java (.java)
- `bin/` : clases compiladas (.class) (ya incluidas en el repo actual)
- `docs/` : documentación generada
- `test/` : pruebas unitarias
- `SmartTask.jar` : JAR ejecutable (si ya fue generado)

---

## Requisitos

- Java 11+ (JRE o JDK) instalado y accesible en la variable `PATH`.

---

## Compilar y ejecutar desde la línea de comando (Windows PowerShell)

1. Compilar todas las clases (desde la raíz del proyecto):

```powershell
javac -d .\out\classes .\src\*.java
```

2. Crear un manifest simple (si no existe):

```powershell
Set-Content -Path .\out\manifest.txt -Value "Main-Class: Main`n"
```

3. Empaquetar el JAR ejecutable:

```powershell
jar cfm .\SmartTask.jar .\out\manifest.txt -C .\out\classes .
```

4. Ejecutar la aplicación:

```powershell
java -jar .\SmartTask.jar
```

> Nota: también puedes ejecutar directamente las clases compiladas con `java -cp .\out\classes Main`.

---

## Uso (resumen)

La app muestra un menú en consola con opciones numéricas. Usa las teclas para seleccionar:
- 1: Agregar tarea
- 2: Listar tareas
- 3: Marcar como completada
- 4: Eliminar tarea
- 5: Buscar por ID
- 6: Filtrar por prioridad
- 7: Estadísticas
- 0: Salir

---

## Subir a GitHub

1. Inicializar repo (si aún no existe):

```powershell
git init
git add .
git commit -m "Initial commit"
```

2. Crear el repositorio remoto en GitHub (interfaz web) y luego conectar y empujar:

```powershell
git remote add origin https://github.com/<usuario>/<repo>.git
git branch -M main
git push -u origin main
```

> Si usas HTTPS, GitHub te pedirá un token personal (PAT). Puedes configurar autenticación con Git Credential Manager o usar SSH.

---

## Resumen en Español

SmartTask es una pequeña utilidad de consola para gestionar tareas sencillas desde la terminal. Es ideal para practicar Java y estructuras básicas (clases, listas, control de flujo).

---

## Summary (English)

SmartTask is a small Java console application to manage tasks (to‑dos). It provides a simple interactive menu to add, list, complete, delete and filter tasks by priority. It is useful as a learning project for basic Java features.

---

## Resumo (Português)

SmartTask é uma pequena aplicação de console em Java para gerir tarefas. Possui um menu simples para adicionar, listar, marcar como concluídas, eliminar e filtrar tarefas por prioridade. Ideal para aprender conceitos básicos de Java.

---

Si quieres, puedo también:
- Añadir un `build.gradle` o `pom.xml` para automatizar el build y el empaquetado.
- Ejecutar las pruebas unitarias y adjuntar el reporte.

Gracias, dime cómo prefieres proceder.
