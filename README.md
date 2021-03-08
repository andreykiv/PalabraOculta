# Palabra Oculta

#### 1. Equipo Desarrollo 

| Developer | Rama | Rol |
| --- | :---:  | :---:  |
| Myroslav Andreykiv | Master / Dev1 | Developer | 
| Gerard Bonet | Dev 2 | Developer |
| Noelia Barrera | Dev 3 | Developer | 

#### 2. Descripción
```
El objetivo era implementar una aplicación que permita jugar al juego del ahorcado. 
Para su realización se deberán seguir las siguientes indicaciones.

• En un panel incluiremos los botones correspondientes a las letras. Se deberá
implementar un único procedimiento que permita habilitar y deshabilitar todos
los botones a la vez.

• Utilizamos otro componente las distintas imágenes que muestren la evolución del
ahorcamiento. Por lo tanto, el jugador solo dispone de 10 intentos fallidos para
adivinar la palabra.

• Además incluiremos un groupbox (pistas) que incluirá 5 botones de color rojo. El
numero de vidas a crear podrá ser modificado con lo que hemos de pasar este
como parámetro. También se presentara un botón pista.

• La palabra a adivinar se extraerá de forma aleatoria de un Listbox que se rellena
con 10 palabras que deseéis.

• Tras obtener las palabras se crearan componentes label “_” necesarios para
adivinar la palabra.

• Cada vez que se pulse sobre una letra esta se deshabilitara sino has acertado se
cargara la siguiente imagen. Si has acertado se cargara la imagen en la posición
que corresponda. Se comprobara si un jugador ha ganado o ha perdido mostrando
una imagen por pantalla. Tanto si gana como si pierde se le pedirá al jugador si
quiere volver a jugar.

• El menú Archivo del juego tendrá dos opciones Salir y nuevo juego. También
mostraremos los intentos fallidos que lleva el jugador en cada partida.

• Además se crearan dos formularios accesibles desde la barra de menú: Como jugar y
acerca de (donde aparecerá el nombre del alumno dentro de un menú ayuda).

Retos adicionales:
• Se debe crear un formulario donde se puede elegir el nivel del jugador.

• Las características de la primera parte son del jugador principiante. El intermedio
tendrá 8 intentos y el avanzado solo 6.

• Este formulario aparecerá siempre que seleccionemos nuevo juego

• Se deberá crear una nueva opción de menú llamada diccionario / palabra nueva
donde se podrán añadir palabras al diccionario.

Gestión de la pista:
• En cualquier momento del juego el usuario puede solicitar una pista pulsando el
botón pista. Si acepta la pista, se perderá un intento,
se eliminara uno de los botones presentes en el groupbox , se mostrará la pista
siempre que tenga mas de un intento.

• La pista a mostrar será la primera letra de cada palabra oculta que aun no haya
sido encontrada. Es posible que tras mostrar una pista el jugador gane la partida.

```

#### 3. Link a un demo con el proyecto desplegado: https://github.com/andreykiv/PalabraOculta


#### 4. Lista de instrumentos utilizados.

IDE: Eclipse IDE Version: 2020-12 (4.18.0)
JRE System Library: jdk.1.8.0_281  
Maven: version 3.6.3 (integrated within Eclipse)

