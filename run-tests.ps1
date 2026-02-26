# Script para ejecutar tests de SmartTask
# Autor: Yulieta (Melek) Eyzaguirre
# Fecha: Febrero 2026

Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "   🧪 SMARTTASK - EJECUTOR DE PRUEBAS UNITARIAS 🧪     " -ForegroundColor Cyan
Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

# Función para mostrar mensajes con colores
function Write-Step {
    param([string]$Message, [string]$Color = "Yellow")
    Write-Host "▶️  $Message" -ForegroundColor $Color
}

function Write-Success {
    param([string]$Message)
    Write-Host "✅ $Message" -ForegroundColor Green
}

function Write-Error-Custom {
    param([string]$Message)
    Write-Host "❌ $Message" -ForegroundColor Red
}

# Verificar que estamos en la carpeta correcta
$currentPath = Get-Location
Write-Step "Directorio actual: $currentPath" "Cyan"

# Verificar estructura de directorios
Write-Step "Verificando estructura del proyecto..." "Yellow"

$requiredDirs = @("src", "test", "lib")
$missingDirs = @()

foreach ($dir in $requiredDirs) {
    if (-not (Test-Path $dir)) {
        $missingDirs += $dir
        Write-Error-Custom "Falta directorio: $dir"
    } else {
        Write-Success "Encontrado: $dir/"
    }
}

if ($missingDirs.Count -gt 0) {
    Write-Error-Custom "Faltan directorios requeridos. Abortando."
    exit 1
}

# Verificar JUnit JAR
Write-Step "Verificando JUnit5..." "Yellow"
$junitJar = "lib/junit-platform-console-standalone-1.10.1.jar"

if (-not (Test-Path $junitJar)) {
    Write-Error-Custom "No se encuentra JUnit5 en: $junitJar"
    Write-Host ""
    Write-Host "Descarga JUnit5 desde:" -ForegroundColor Yellow
    Write-Host "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/junit-platform-console-standalone-1.10.1.jar" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "O ejecuta:" -ForegroundColor Yellow
    Write-Host 'Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/junit-platform-console-standalone-1.10.1.jar" -OutFile "lib/junit-platform-console-standalone-1.10.1.jar"' -ForegroundColor Cyan
    exit 1
} else {
    Write-Success "JUnit5 encontrado"
}

Write-Host ""
Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "   📦 COMPILACIÓN                                       " -ForegroundColor Cyan
Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

# Limpiar directorio bin
Write-Step "Limpiando directorio bin..." "Yellow"
if (Test-Path bin) {
    Remove-Item -Path bin\* -Recurse -Force -ErrorAction SilentlyContinue
    Write-Success "Directorio bin limpiado"
} else {
    New-Item -Path bin -ItemType Directory -Force | Out-Null
    Write-Success "Directorio bin creado"
}

# Compilar código fuente
Write-Step "Compilando código fuente (src/)..." "Yellow"
javac -encoding UTF-8 -d bin src\*.java 2>&1 | Out-String | ForEach-Object {
    if ($_ -match "error") {
        Write-Host $_ -ForegroundColor Red
    } elseif ($_ -match "warning") {
        Write-Host $_ -ForegroundColor Yellow
    }
}

if ($LASTEXITCODE -ne 0) {
    Write-Error-Custom "Error al compilar código fuente"
    exit 1
}
Write-Success "Código fuente compilado exitosamente"

# Compilar tests
Write-Step "Compilando tests (test/)..." "Yellow"
javac -encoding UTF-8 -d bin -cp "lib\*;bin" test\*.java 2>&1 | Out-String | ForEach-Object {
    if ($_ -match "error") {
        Write-Host $_ -ForegroundColor Red
    } elseif ($_ -match "warning") {
        Write-Host $_ -ForegroundColor Yellow
    }
}

if ($LASTEXITCODE -ne 0) {
    Write-Error-Custom "Error al compilar tests"
    exit 1
}
Write-Success "Tests compilados exitosamente"

# Contar archivos compilados
$classCount = (Get-ChildItem -Path bin -Filter *.class -Recurse).Count
Write-Host ""
Write-Step "Archivos .class generados: $classCount" "Cyan"

Write-Host ""
Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "   🧪 EJECUCIÓN DE PRUEBAS                              " -ForegroundColor Cyan
Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

# Ejecutar tests
Write-Step "Ejecutando pruebas unitarias..." "Green"
Write-Host ""

$testOutput = java -jar $junitJar `
    --class-path bin `
    --scan-class-path `
    --disable-banner 2>&1

# Mostrar output
$testOutput | ForEach-Object {
    $line = $_
    if ($line -match "successful|SUCCESSFUL") {
        Write-Host $line -ForegroundColor Green
    } elseif ($line -match "failed|FAILED|Failures") {
        Write-Host $line -ForegroundColor Red
    } elseif ($line -match "Test run") {
        Write-Host $line -ForegroundColor Cyan
    } else {
        Write-Host $line
    }
}

Write-Host ""
Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan

# Verificar resultado
if ($LASTEXITCODE -eq 0) {
    Write-Host "   ✅ TODAS LAS PRUEBAS PASARON EXITOSAMENTE ✅        " -ForegroundColor Green
} else {
    Write-Host "   ❌ ALGUNAS PRUEBAS FALLARON ❌                      " -ForegroundColor Red
}

Write-Host "═══════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

# Resumen
Write-Host "📊 RESUMEN:" -ForegroundColor Cyan
Write-Host "   - Clases compiladas: $classCount" -ForegroundColor White
Write-Host "   - Tests ejecutados: Ver arriba" -ForegroundColor White
Write-Host "   - Código fuente: src/" -ForegroundColor White
Write-Host "   - Tests: test/" -ForegroundColor White
Write-Host ""

# Mensaje final
if ($LASTEXITCODE -eq 0) {
    Write-Host "🎉 ¡Excelente trabajo! Tu código pasa todas las pruebas. 🎉" -ForegroundColor Green
} else {
    Write-Host "⚠️  Revisa los errores arriba y corrige los tests fallidos." -ForegroundColor Yellow
}

Write-Host ""
Read-Host "Presiona ENTER para salir"

exit $LASTEXITCODE
