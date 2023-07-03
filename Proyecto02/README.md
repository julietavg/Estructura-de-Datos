# Proyecto2
Implementacion de Twenty Questions Game Fruita o 20Q Fruits  consiste en que el usuario
piense en una fruta y el programa tratará de adivinarlo haciendo  a lo más 20 preguntas
cuyas respuestas solo son sí y no.

Rama sin GUI - M
Rama con GUI - MainB

## Autores:
* Reyes Ramos Luz María  318211073
* Vargas Gutiérrez Julieta 318341945

## Inconvenientes

Los principales inconvenientes presentados en esta implementacion fue el ordenamiento de las
lista usadas para alamcenar las preguntas y hojas del arbol, se tenía pensado utilizar ordenamientos
en las mismas para no hacer copias a arreglos en cada ejecución, pero se tuvieron inconvenientes con 
las referencias a los nodos y los resultados fueron lejos de ser exitosos.

Por otro lado, la serialización del banco de preguntas fue un poco problematica al principio
ya que se obtenian excepciones sobre "No serializable" o respecto a la clave UID, al final esto se resolvio
declarando como estaticos a los objetos que no implementan serializable como Scanner.

Por ultimo, la lectura de archivo presentó ciertos inconvenientes también, algunos saltos del linea
o elementos faltantes hacia que el programa terminará lanzando multiples excepciones y por ende no 
funcionando de manera esperada.

## Compilación  y ejecución
 * ant build
 * ant jar 
 * ant run - ejecutar programa
 
 Para la implementación de este proyecto, como se pueden observar en las diferentes ramas de este repositorio,
 se implementaron dos arboles binarios, donde cada integrante implemento el suyo, al final por decisión de ambas partes
 se utilizó el del banco de preguntas a partir del un txt.
 
 Por otra parte se mantuvieron múltiplas sesiones de meet para la organización de la estructura del programa.



