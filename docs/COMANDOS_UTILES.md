# 🛠️ SmartTask - Comandos Útiles

## 📂 Navegación

```powershell
# Ir al proyecto
cd "C:\Users\Julieta\OneDrive\Documentos\CursoJavaTalentoDigital\javaProyectosTalentoDigital\tareas\smartTask-ModuloIV\SmartTask"

# Ver estructura
tree /F
```

---

## 🔨 Compilación

### Compilar todo el proyecto
```powershell
cd src
javac -d ..\bin *.java
```

### Compilar un archivo específico
```powershell
javac -d ..\bin Tarea.java
javac -d ..\bin GestorTareas.java
javac -d ..\bin Main.java
```

### Compilar con encoding específico
```powershell
javac -encoding UTF-8 -d ..\bin *.java
```

---

## ▶️ Ejecución

### Ejecutar la aplicación
```powershell
cd bin
java Main
```

### Ejecutar desde raíz
```powershell
java -cp bin Main
```

---

## 📚 Javadoc

### Generar Javadoc completo
```powershell
javadoc -d docs -encoding UTF-8 -charset UTF-8 -author -version -sourcepath src src\*.java
```

### Generar solo para una clase
```powershell
javadoc -d docs -encoding UTF-8 src\Tarea.java
```

### Abrir Javadoc en navegador
```powershell
start docs\index.html
```

---

## 🧹 Limpieza

### Limpiar archivos .class
```powershell
cd bin
del /Q *.class
```

### Limpiar Javadoc
```powershell
Remove-Item -Recurse -Force docs\*
```

### Limpieza completa
```powershell
# Limpiar binarios
Remove-Item -Force bin\*.class

# Limpiar docs
Remove-Item -Recurse -Force docs\*
```

---

## 🔍 Inspección

### Ver archivos compilados
```powershell
cd bin
dir *.class
```

### Ver tamaño de archivos
```powershell
Get-ChildItem src\*.java | Select-Object Name, Length | Format-Table
```

### Contar líneas de código
```powershell
# Total de líneas en src
(Get-Content src\*.java | Measure-Object -Line).Lines

# Por archivo
Get-ChildItem src\*.java | ForEach-Object { 
    Write-Host "$($_.Name): $((Get-Content $_.FullName | Measure-Object -Line).Lines) líneas"
}
```

---

## 📦 Empaquetado

### Crear JAR ejecutable
```powershell
# 1. Crear manifest
echo "Main-Class: Main" > manifest.txt

# 2. Crear JAR
cd bin
jar cvfm SmartTask.jar ..\manifest.txt *.class

# 3. Ejecutar JAR
java -jar SmartTask.jar
```

---

## 🔄 Recompilar todo

### Script completo de recompilación
```powershell
# Limpiar
Remove-Item -Force bin\*.class -ErrorAction SilentlyContinue

# Compilar
cd src
javac -d ..\bin *.java

# Verificar
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Compilación exitosa" -ForegroundColor Green
    cd ..\bin
    Get-ChildItem *.class | ForEach-Object { Write-Host "  ✓ $($_.Name)" -ForegroundColor Green }
} else {
    Write-Host "❌ Error en compilación" -ForegroundColor Red
}
```

---

## 🧪 Verificación

### Verificar que todo compila
```powershell
cd src
javac -d ..\bin *.java 2>&1 | Select-String "error"
```

### Verificar estructura de clases
```powershell
cd bin
javap -public Tarea
javap -public GestorTareas
javap -public Main
```

---

## 📊 Análisis de Código

### Ver métodos públicos de una clase
```powershell
javap -public bin\GestorTareas.class
```

### Ver toda la información de una clase
```powershell
javap -verbose bin\Tarea.class
```

### Ver solo signatures de métodos
```powershell
javap -s bin\Main.class
```

---

## 🔖 Git (Opcional)

### Inicializar repositorio
```powershell
git init
git add .
git commit -m "Implementación completa de SmartTask v2.0 con POO"
```

### Crear .gitignore
```powershell
@"
# Compiled class files
*.class
bin/

# Package Files
*.jar
*.war
*.ear

# IDE
.idea/
*.iml
.vscode/

# OS
.DS_Store
Thumbs.db
"@ | Out-File -Encoding UTF-8 .gitignore
```

---

## 📝 Edición Rápida

### Abrir archivos en Notepad++
```powershell
notepad++ src\Tarea.java
notepad++ src\GestorTareas.java
notepad++ src\Main.java
```

### Abrir en VS Code
```powershell
code src\Tarea.java
code src\GestorTareas.java
code src\Main.java
```

---

## 🎯 Shortcuts Útiles

### Todo en uno: Limpiar, Compilar, Ejecutar
```powershell
# Guardar como: compilar-ejecutar.ps1
Remove-Item -Force bin\*.class -ErrorAction SilentlyContinue
cd src
javac -d ..\bin *.java
if ($LASTEXITCODE -eq 0) {
    cd ..\bin
    java Main
} else {
    Write-Host "❌ Error en compilación" -ForegroundColor Red
}
```

### Generar todo: Compilar + Javadoc
```powershell
# Compilar
cd src
javac -d ..\bin *.java

# Javadoc
javadoc -d ..\docs -encoding UTF-8 -charset UTF-8 -author -version *.java

Write-Host "✅ Compilación y Javadoc completados" -ForegroundColor Green
```

---

## 📖 Ver Documentación

### Abrir todos los archivos de documentación
```powershell
start RESUMEN_MEJORAS.md
start DOCUMENTACION_TECNICA.md
start GUIA_RAPIDA.md
start docs\index.html
```

---

## 🎓 Para tu Presentación/Entrega

### Crear un ZIP del proyecto
```powershell
# Comprimir todo (excepto bin y docs)
Compress-Archive -Path src\*, *.md -DestinationPath SmartTask_v2.0.zip
```

### Generar reporte completo
```powershell
@"
PROYECTO: SmartTask v2.0
AUTOR: Yulieta (Melek) Eyzaguirre
FECHA: $(Get-Date -Format "dd/MM/yyyy")

ARCHIVOS FUENTE:
$((Get-ChildItem src\*.java).Name -join "`n")

LÍNEAS DE CÓDIGO:
$(Get-ChildItem src\*.java | ForEach-Object { "$($_.Name): $((Get-Content $_.FullName | Measure-Object -Line).Lines) líneas" } | Out-String)

DOCUMENTACIÓN:
$((Get-ChildItem *.md).Name -join "`n")

JAVADOC:
$((Get-ChildItem docs\*.html).Count) archivos HTML generados
"@ | Out-File reporte_proyecto.txt
```

---

## 🚀 Comandos Favoritos (Copia y Pega)

```powershell
# 1. Compilar rápido
cd src; javac -d ..\bin *.java; cd ..

# 2. Ejecutar rápido
cd bin; java Main; cd ..

# 3. Compilar y ejecutar
cd src; javac -d ..\bin *.java; cd ..\bin; java Main; cd ..

# 4. Ver todo
tree /F

# 5. Limpiar y compilar
Remove-Item -Force bin\*.class; cd src; javac -d ..\bin *.java; cd ..
```

---

## 💡 Tips

1. **Siempre compila desde la carpeta `src`**
2. **Ejecuta desde la carpeta `bin`**
3. **Usa UTF-8 si tienes problemas con caracteres especiales**
4. **Genera Javadoc después de cada cambio importante**
5. **Haz backup antes de modificar archivos importantes**

---

## 📞 Ayuda Rápida

### Si no compila:
```powershell
# Ver errores detallados
javac -d ..\bin -verbose *.java
```

### Si no ejecuta:
```powershell
# Verificar que Main.class existe
Test-Path bin\Main.class

# Ver el classpath
java -cp bin -version
```

### Si Javadoc falla:
```powershell
# Usar locale inglés
javadoc -locale en_US -d docs -sourcepath src src\*.java
```

---

**Archivo creado**: $(Get-Date -Format "dd/MM/yyyy HH:mm")  
**Para**: Yulieta (Melek) Eyzaguirre  
**Proyecto**: SmartTask v2.0
