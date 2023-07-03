# Proyecto 01: Old Maid
## Autor:  Reyes Ramos Luz María 318211073
## Autor: Vargas Gutiérrez Julieta 318341945

## Descripción

Implementación del juego de cartas llamado Old Maid (con listas)
el cual consiste en ir descartando pares de carta, el jugador que 
se quede con la última carta es el perdedor 

## Incovenientes
Durante la implementación se presentaron varios problemas, por la facilidad de tenerlo ya hecho, 
se decidió  utilizar listas doblemente ligadas, aunque era inecesario. Al implemetar métodos en la lista como
obtener un índice dado un elemento, los indices obtenidos no coincídian con los resultados esperados, a pesar de hacer múltiples pruebas con listas simples, las cuales eran éxitosas, al momento de utilizar dicho metodo para OldMaidGame en una lista de jugadores, fue un completo desastre con respecto a las posiciones de la lista, al final se solucionó implementando ese método con complejidad O(n).

Además, debido a que no se tiene la facilidad de acceso que posee un arreglo, fue complicado iterar con respecto a la lista de jugadores, ya que las posiciones y tamaño no son fijos, esto da como resultado posibles errores en las referencias a un elemento, en concreto para este proyecto a los atributos actual, prePlayer, nextPlayer en la clase OldMaidGame.



Por otro lado, al mismo tiempo se tuvieron múltiples inconvenientes técnicos, a uno de los intefrantes del equipo le fue complicada la ejecución del programa debido a la codificación de cáracteres que utiliza Windows.



## Compilación y ejecución 
Para compilar la practica se debe usar ant build y ant jar
Para ejecutar ant run

