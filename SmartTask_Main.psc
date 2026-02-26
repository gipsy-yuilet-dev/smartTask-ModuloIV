Algoritmo SmartTask_Main
	Definir opcion, totalTareas, indice Como Entero
	Definir continuar Como Lógico
	Definir tareas Como Cadena
	Definir estados Como Lógico
	Dimensionar tareas(50)
	Dimensionar estados(50)
	totalTareas <- 0
	continuar <- Verdadero
	Mientras continuar Hacer
		Escribir '=== SMART TASK ==='
		Escribir '1. Agregar Tarea'
		Escribir '2. Listar Tareas'
		Escribir '3. Eliminar Tarea'
		Escribir '4. Marcar como Completada'
		Escribir '0. Salir'
		Escribir 'Seleccione una opcion:'
		Leer opcion
		Según opcion Hacer
			1:
				// Pasamos los arreglos y el contador (totalTareas) para que el subproceso los vea
				AgregarTarea(tareas,estados,totalTareas)
			2:
				ListarTareas(tareas,estados,totalTareas)
			3:
				EliminarTarea(tareas,estados,totalTareas)
			4:
				MarcarCompletada(tareas,estados,totalTareas)
			0:
				continuar <- Falso
				Escribir 'Saliendo del sistema...'
			De Otro Modo:
				Escribir 'Opcion no valida.'
		FinSegún
		Escribir ''
		Escribir 'Presione una tecla para continuar...'
		Esperar Tecla // Salto de linea estético
		Limpiar Pantalla
	FinMientras
FinAlgoritmo

// IMPORTANTE: 'totalTareas' debe ser Por Referencia porque su valor cambia (aumenta)
Función AgregarTarea(tareas,estados,totalTareas Por Referencia)
	Si totalTareas=50 Entonces
		Escribir 'No es posible agregar mas tareas.'
	SiNo
		Escribir 'Ingrese la descripcion de la nueva tarea:'
		// PSeInt usa indices base 1, asi que totalTareas + 1 es correcto para la nueva posición
		Leer tareas[totalTareas+1]
		estados[totalTareas+1]<-Falso
		totalTareas <- totalTareas+1
		Escribir 'Tarea agregada correctamente.'
	FinSi
FinFunción

Función ListarTareas(tareas,estados,totalTareas)
	Definir textoEstado Como Cadena
	Definir indice Como Entero
	Si totalTareas=0 Entonces
		Escribir 'No hay tareas registradas.'
	SiNo
		Escribir '--- LISTA DE TAREAS ---'
		Para indice<-1 Hasta totalTareas Con Paso 1 Hacer
			Si estados[indice] Entonces
				textoEstado <- '[X] Completada'
			SiNo
				textoEstado <- '[ ] Pendiente'
			FinSi
			Escribir indice, '. ', tareas[indice], ' - ', textoEstado
		FinPara
	FinSi
FinFunción

// 'totalTareas' Por Referencia porque al borrar, el total disminuye
Función EliminarTarea(tareas,estados,totalTareas Por Referencia)
	Definir posicion, indice Como Entero
	Si totalTareas=0 Entonces
		Escribir 'No hay tareas para eliminar.'
	SiNo
		ListarTareas(tareas,estados,totalTareas)
		Escribir 'Ingrese el numero de la tarea a eliminar:'
		Leer posicion // Mostramos lista para que elija
		Si posicion<1 O posicion>totalTareas Entonces
			Escribir 'Numero de tarea invalido.'
		SiNo
			// Desplazamos los elementos para llenar el hueco
			Para indice<-posicion Hasta totalTareas-1 Con Paso 1 Hacer
				tareas[indice] <- tareas[indice+1]
				estados[indice] <- estados[indice+1]
			FinPara
			totalTareas <- totalTareas-1
			Escribir 'Tarea eliminada.'
		FinSi
	FinSi
FinFunción

// Aquí 'totalTareas' puede ser Por Valor, pero 'estados' se modifica (aunque los arreglos pasan por referencia por defecto)
Función MarcarCompletada(tareas,estados,totalTareas)
	Definir posicion Como Entero
	Si totalTareas=0 Entonces
		Escribir 'No hay tareas para actualizar.'
	SiNo
		ListarTareas(tareas,estados,totalTareas)
		Escribir 'Ingrese el numero de la tarea a marcar como completada:'
		Leer posicion
		Si posicion<1 O posicion>totalTareas Entonces
			Escribir 'Numero de tarea invalido.'
		SiNo
			estados[posicion] <- Verdadero
			Escribir 'Tarea marcada como completada.'
		FinSi
	FinSi
FinFunción
