import java.util.Iterator;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.net.*;

/**
 * Main : Double Linked List.
 * @author Reyes Ramos Luz María 318211073
 * @author Vargas Gutiérrez Julieta 318341945
 * @version 1.0 Octubre 2021
 * @since Estructuras de datos lab 2022-1
 */


public class Main {
 public static void main(String []args){

   DoubleLinkedList lista = new DoubleLinkedList();
  DoubleLinkedList<String> listaString = new DoubleLinkedList <String>();



          int opc;
          String cadenas;
          int posicion;
          boolean eleccion;
          boolean excep = true;
       Scanner opcion = new Scanner(System.in);
       Scanner cadena = new Scanner(System.in);
       Scanner numerica = new Scanner(System.in);
       Scanner bool = new Scanner(System.in);
       boolean salir = false;
       boolean repetir = true;



           while(!salir){


              //Opciones a elegir
               System.out.println("*************           PRACTICA 2: LISTAS             *************");
               System.out.println("[1] Agrega una cadena a la lista\n");
               System.out.println("[2] Elimina una cadena a la lista\n");
               System.out.println("[3] Limpia la lista\n");
               System.out.println("[4] Verifica si un elemento está la lista\n");
               System.out.println("[5] Obtener un elemento de la lista\n");
               System.out.println("[6] Verifica si la lista está vacia\n");
               System.out.println("[7] Obtener la longitud de la lista\n");
               System.out.println("[8] Obtener la reversa de la lista\n");
               System.out.println("[9] Corta la lista\n");
               System.out.println("[10] Mostrar lista\n");
               System.out.println("[11] Salir del menú\n");



               System.out.println("Escoge tu opción");
               opc = opcion.nextInt();

               //Casos que el usuario puede escoger
               switch (opc) {

                       case 1:
                         System.out.println("Eligiste la opción [1]");
                         System.out.println("**** Agrega cadenas a la lista ****" );
                         System.out.println(" \n¿QUE CADENA QUIERES AGREGAR?");
                         cadenas = cadena.nextLine();

                         do{
                             try{
                         System.out.println("¿Qué posición te gustaría");
                         posicion =numerica.nextInt();
                         listaString.add(posicion,cadenas);
                         System.out.println("La cadena añadida fue "+cadenas);
                     }catch(InputMismatchException e){
                       System.out.println("Necesito un numero" );
                     }catch(IndexOutOfBoundsException e){
                System.out.println( "Necesito un número dentro del rango");
                 }
              }while(!repetir);

                         break;


                       case 2:
                       System.out.println("Eligiste la opción [2]");
                       System.out.println("**** Elimina una cadena a la lista ****" );
                       System.out.println(" \n EN QUE POSICIÓN ESTA EL ELEMENTO QUE QUIERES ELIMINAR");

                   do{
                       try{
                         posicion =numerica.nextInt();
                         listaString.remove(posicion);
                         System.out.println("Tu cadena en  "+posicion+" se elimino");

                     }catch(InputMismatchException e){
                       System.out.println("Necesito un número");
										numerica.nextInt();
									}catch(IndexOutOfBoundsException e){
										System.out.println("Necesito un número dentro del rango");
					    	}
                    }while(!repetir);
                         break;


                       case 3:


                        System.out.println("Eligiste la opción [3]");
                        System.out.println("**** Limpiar la lista ****" );
                        System.out.println(" \n Estamos limpiando su lista");
                         listaString.clear();
                         System.out.println("Ya está limpia");


                         break;


                       case 4:
                       System.out.println("Eligiste la opción [4]");
                       System.out.println("*** *Verificar si un elemento está en la lista****" );
                       System.out.println(" \n ¿CUAL ES EL ELEMENTO QUE QUIERES VERIFICAR?");

                         cadenas = cadena.nextLine();
                         listaString.contains(cadenas);


                         if(listaString.contains(cadenas)==true){
                           System.out.println("Encontre el elemento");
                           }else{
                               System.out.println("No encontre el elemento");
                           }
                         break;


                       case 5:
                       System.out.println("Eligiste la opción [5]");
                       System.out.println("**** Obtener un elemento de la lista ****" );
                       System.out.println("\n EN QUE POSICIÓN ESTA EL ELEMENTO QUE QUIERES OBTENER");


                         posicion =numerica.nextInt();

                         break;


                       case 6:
                       System.out.println("Eligiste la opción [6]");
                       System.out.println("**** Vericar si la lista está vacía ****" );
                       System.out.println("Verificamos si la lista esta vacia  =  "+ listaString.isEmpty());
                         break;


                       case 7:
                       System.out.println("Eligiste la opción [7]");
                       System.out.println("**** Obtener la longitud de la lista ****" );
                         System.out.println("La longitud de la lista es = "+ listaString.size());

                         break;


                       case 8:
                       System.out.println(" Eligiste la opción [8]");
                       System.out.println("**** Obtener la reversa de la lista ****" );
                         System.out.println(" La reversa de tu lista es = ");
                         listaString.revert();
                         break;


                       case 9:

                       System.out.println(" Eligiste la opción [9]");
                          System.out.println("**** Cortar la lista ****" );


                            System.out.println("Cortare tu lista, ¿qué lado te gustaría?");
                             System.out.println("Si la parte izquierda escribe false ");
                               System.out.println("Si la parte derecha escribe true ");

                            eleccion = bool.nextBoolean();
                            if(eleccion == true){
                                    System.out.println("Se borro la parte derecha ");
                              }else{
                                       System.out.println("Se borro la parte izquierda ");
                              }
                            break;



                       case 10:

                       System.out.println(" Eligiste la opción [10]");
                       System.out.println("**** Mostrar la lista ****" );
                        System.out.println("Tu lista es = "+listaString.toString());
                         break;



                       case 11:
                         salir = true;
                         break;


                     default:
                         System.out.println("Debes escoger un número del 1 al 11");
             }

           }



}
}
