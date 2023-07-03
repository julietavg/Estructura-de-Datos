import java.util.Arrays;



/**
* Práctica 1 del curso de Estructuras de Datos.
* @author Emmanuel Cruz Hernández.
* @author Reyes Ramos Luz María    318211073
* @author Vargas Gutiérrez Julieta 318341945
* @version 2.0 Septiembre 2021.
* @since Laboratorio de Estructuras de Datos 2022-1.
*/
public class Practica01{

	/**
	* Hace la mezcla de dos arreglos ordenados desde la primera posición hasta
	* una posición límite
	* @param array1 el primer arreglo a mezlar
	* @param n el límite de mezcla del primer arreglo
	* @param array2 el segundo arreglo a mezclar
	* @param m el límite de mezcla del segundo arreglo.
	* @return un arreglo ordenado de longitud m+n con la mezcla definida.
	*/
	public static int[] mergeSortedArray(int[] array1, int n, int[] array2, int m){
		if(n > array1.length || m > array2.length)
			throw new RuntimeException("Límites no válidos");

		int[] result = new int[n + m];
		int pointer;
		for(pointer = 0; pointer < n; pointer++)
			result[pointer] = array1[pointer];
		for(int i = 0 ; i < m ; i++, pointer++)
			result[pointer] = array2[i];

		// Ordenamiento del arreglo result
		for(int j = 0; j < result.length - 1; j++){
			for(int k = j+1; k < result.length; k++){
				if(result[k] < result[j]){
					int aux = result[k];
					result[k] = result[j];
					result[j] = aux;
				}
			}
		}

		return result;
	}


	/**
	* M E R G E . S O R T E Y   - V E R S I ÓN N - 2.0
	* Hace la mezcla de dos arreglos ordenados desde la primera posición hasta
	* una posición límite
	* @param array1 el primer arreglo a mezlar
	* @param n el límite de mezcla del primer arreglo
	* @param array2 el segundo arreglo a mezclar
	* @param m el límite de mezcla del segundo arreglo.
	* @return un arreglo ordenado de longitud m+n con la mezcla definida.
	*/
      public static int[] mergeSortedArray2(int[] a, int n, int[] b, int m){

         if(n > a.length || m > b.length){
             throw new IllegalArgumentException("Límites no válidos.");
         }

         int [] result = new int[n + m];
         int i = 0, j = 0, k = 0; //iteradores para los array, con k el arreglo resultante

         while(j < n && k < m){
           if(a[j] < b[k]){
                 result[i] = a[j];
                 j++;
             }else{
                result[i] = b[k];
                 k++;
             }
             i++;
         }

         while(j < n){
             result[i] = a[j];
             j++;
             i++;
         }

         while(k < m){
             result[i] = b[k];
             k++;
             i++;
         }

         return result;
     }

    /**
    * Verifica si un tablero contiene los números desde 0 hasta n-1 en cada fila y cada columna.
    * @param board el tablero de nxn que contiene elementos dentro del rango [0, n-1].
    * @return true si el tablero contiene los números desde 0 hasta n-1 en cada fila y columna,
	* false en otro caso.
    */
    public static boolean isValidBoard(int[][] board){
    	int length = board.length;
		for (int i = 0; i < length ; i++) {
			for (int j = 0; j < length ; j++ ) {
				boolean verificador = false;
				// Verifica sobre las filas
				for(int k = 0 ; k < length; k++){
					if(board[i][k] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
				verificador = false;
				// Verifica sobre las columnas
				for(int k = 0 ; k < length; k++){
					if(board[k][i] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
			}
		}
		return true;
	}
	/**
	* B O A R D - V E R S I ÓN N - 2.0
	* Verifica si un tablero contiene los números desde 0 hasta n-1 en cada fila y cada columna.
    * @param board el tablero de nxn que contiene elementos dentro del rango [0, n-1].
    * @return true si el tablero contiene los números desde 0 hasta n-1 en cada fila y columna,
	* false en otro caso.
	 */
	public static boolean isValidBoard2(int[][]board){
		int size= board.length;
		int[][] cont = new int[size][size];
		for(int n=0; n<size; n++){
			for(int k=0; k<size; k++){
				//filas
				if(cont[n][board[n][k]]==2 ){
					return false;

				}

				cont[n][board[n][k]]++;

				if(cont[board[k][n]][n] == 2){
					return false;
				}

				cont[board[k][n]][n]++;



			}
		}

		return true;

	}


	/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArray(int[] num, int position){
		for(int i = 0; i < position ; i++){
			int aux = num[0];
			for(int j = 0; j < num.length -1 ; j++){
				num[j] = num[j+1];
			}
			num[num.length-1] = aux;
		}
	}
	/**
	 * ---- V E R S I Ó N  2.0 -------
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArray2(int[] num, int position){

		int size = num.length;
		int[] arrAux = new int[num.length];
		for(int n =0; n<size; n++)  //izquierda -derecha
			arrAux[n] = num[(n+position)%size];

		for (int m =0 ; m<size; m++)
			num[m]= arrAux[m];

	}
	public static void main(String[] args) {


	    	String directorio1 = "Tests/ArrayTests/";
	     	String directorio2 = "Tests/BoardTests/";


   //readArray

	 int[] arrayA1 = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
	 int[] arrayA2 = ArrayReader.readArray(directorio1 + "ArrayA2.txt");
     //COPIAS
	 int[] arrayA1cop = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
	 int[] arrayA2cop = ArrayReader.readArray(directorio1 + "ArrayA2.txt");


 		int[] arrayB1 = ArrayReader.readArray(directorio1 + "ArrayB1.txt");
 		int[] arrayB2 = ArrayReader.readArray(directorio1 + "ArrayB2.txt");
 			//COPIAS
 		int[] arrayB1cop = ArrayReader.readArray(directorio1 + "ArrayB1.txt");


 		int[] arrayC1 = ArrayReader.readArray(directorio1 + "ArrayC1.txt");
 		int[] arrayC2 = ArrayReader.readArray(directorio1 + "ArrayC2.txt");

 			//COPIAS
 		int[] arrayC1cop = ArrayReader.readArray(directorio1 + "ArrayC1.txt");


 		int[] arrayD1 = ArrayReader.readArray(directorio1 + "ArrayD1.txt");
 		int[] arrayD2 = ArrayReader.readArray(directorio1 + "ArrayD2.txt");
 			//COPIAS
 		int[] arrayD1cop = ArrayReader.readArray(directorio1 + "ArrayD1.txt");


 		int[] arrayE1 = ArrayReader.readArray(directorio1 + "ArrayE1.txt");
 		int[] arrayE2 = ArrayReader.readArray(directorio1 + "ArrayE2.txt");
 			//COPIAS
 		int[] arrayE1cop = ArrayReader.readArray(directorio1 + "ArrayE1.txt");

 		int[] arrayF1 = ArrayReader.readArray(directorio1 + "ArrayF1.txt");
 		int[] arrayF2 = ArrayReader.readArray(directorio1 + "ArrayF2.txt");
 			//COPIAS
 		int[] arrayF1cop = ArrayReader.readArray(directorio1 + "ArrayF1.txt");


		// EJEMPLOS DE ACTIVIDAD 1
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		  long inicio = System.currentTimeMillis();
			int[] resultA = mergeSortedArray(arrayA1, 500, arrayA2, 700);
			long fin = System.currentTimeMillis();
			//System.out.println("Resultado A: "+Arrays.toString(resultA));
			System.out.println("El algoritmo 1; A1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			int[] resultB = mergeSortedArray(arrayB1, 2000, arrayB2, 3500);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado B: "+Arrays.toString(resultB));
			System.out.println("El algoritmo 1; B1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			int[] resultC = mergeSortedArray(arrayC1, 4000, arrayC2, 4000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 1; C1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			int[] resultD = mergeSortedArray(arrayD1, 7000, arrayD2, 8000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 1; D1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			int[] resultE = mergeSortedArray(arrayE1, 15000, arrayE2, 19000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 1; E1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			int[] resultF = mergeSortedArray(arrayF1, 30000, arrayF2, 25000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 1; F1 tarda: "+(fin-inicio)+" milisegundos");

			 //EJEMPLOS DE ACTIVIDAD MEJORADO 1
			System.out.println("\nEJEMPLOS DE ACTIVIDAD 1.2 \n");

			inicio = System.currentTimeMillis();
			resultA = mergeSortedArray2(arrayA1, 500, arrayA2, 700);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado A: "+Arrays.toString(resultA));
			System.out.println("El algoritmo 2; A1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			resultB = mergeSortedArray2(arrayB1, 2000, arrayB2, 3500);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado B: "+Arrays.toString(resultB));
			System.out.println("El algoritmo 2; B1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			resultC = mergeSortedArray2(arrayC1, 4000, arrayC2, 4000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 2; C1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			resultD = mergeSortedArray2(arrayD1, 7000, arrayD2, 8000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 2; D1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			resultE = mergeSortedArray2(arrayE1, 15000, arrayE2, 19000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 2; E1 tarda: "+(fin-inicio)+" milisegundos");

			inicio = System.currentTimeMillis();
			resultF = mergeSortedArray2(arrayF1, 30000, arrayF2, 25000);
			fin = System.currentTimeMillis();
			//System.out.println("Resultado C: "+Arrays.toString(resultC));
			System.out.println("El algoritmo 2; F1 tarda: "+(fin-inicio)+" milisegundos");








    		// EJEMPLOS DE ACTIVIDAD 2
     /*
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 2\n");
		int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
		long inicioA = System.currentTimeMillis();
		boolean boardResultA = isValidBoard(boardA);
		long finA = System.currentTimeMillis();
		System.out.println("El tablero A es válido: "+boardResultA);
		System.out.println("Board1A tarda: "+ (finA - inicioA) + " milisegundos.");
		int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
		long inicioB = System.currentTimeMillis();
		boolean boardResultB = isValidBoard(boardB);
		long finB = System.currentTimeMillis();
		System.out.println("El tablero B es válido: "+boardResultB);
		System.out.println("Board1B tarda: "+ (finB - inicioB) + " milisegundos.");
		int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
		long inicioC = System.currentTimeMillis();
		boolean boardResultC = isValidBoard(boardC);
		long finC = System.currentTimeMillis();
		System.out.println("El tablero C es válido: "+boardResultC);
		System.out.println("Board1C tarda: "+ (finC - inicioC) + " milisegundos.");
		int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");
		long inicioD = System.currentTimeMillis();
		boolean boardResultD = isValidBoard(boardD);
		long finD = System.currentTimeMillis();
		System.out.println("El tablero D es válido: "+boardResultD);
		System.out.println("Board1D tarda: "+ (finD - inicioD) + " milisegundos.");
		int[][] boardE = ArrayReader.readMatrix(directorio2 + "BoardE.txt");
		long inicioE = System.currentTimeMillis();
		boolean boardResultE = isValidBoard(boardE);
		long finE = System.currentTimeMillis();
		System.out.println("El tablero E es válido: "+boardResultE);
		System.out.println("Board1E tarda: "+ (finE - inicioE) + " milisegundos.");
		int[][] boardF = ArrayReader.readMatrix(directorio2 + "BoardF.txt");
		long inicioF = System.currentTimeMillis();
		boolean boardResultF = isValidBoard(boardF);
		long finF = System.currentTimeMillis();
		System.out.println("El tablero F es válido: "+boardResultF);
		System.out.println("Board1F tarda: "+ (finF - inicioF) + " milisegundos.");
		//EJEMPLOS DE ACTIVDAD 2: -- A L G O R T M O -2.0--

		// EJEMPLOS DE ACTIVIDAD 2

		System.out.println("\nEJEMPLOS DE ACTIVIDAD 2: *A L G O R I T M O - 2*\n");

		long inicioA2 = System.currentTimeMillis();
		boolean boardResultA2 = isValidBoard2(boardA);
		long finA2 = System.currentTimeMillis();
		System.out.println("El tablero A es válido: "+boardResultA2);
		System.out.println("Board1A tarda: "+ (finA2 - inicioA2) + " milisegundos.");

		long inicioB2 = System.currentTimeMillis();
		boolean boardResultB2 = isValidBoard2(boardB);
		long finB2 = System.currentTimeMillis();
		System.out.println("El tablero B es válido: "+boardResultB2);
		System.out.println("Board1B tarda: "+ (finB2 - inicioB2) + " milisegundos.");

		long inicioC2 = System.currentTimeMillis();
		boolean boardResultC2 = isValidBoard2(boardC);
		long finC2 = System.currentTimeMillis();
		System.out.println("El tablero C es válido: "+boardResultC2);
		System.out.println("Board1C tarda: "+ (finC2 - inicioC2) + " milisegundos.");
		long inicioD2 = System.currentTimeMillis();
		boolean boardResultD2 = isValidBoard2(boardD);
		long finD2 = System.currentTimeMillis();
		System.out.println("El tablero D es válido: "+boardResultD2);
		System.out.println("Board1D tarda: "+ (finD2 - inicioD2) + " milisegundos.");
		long inicioE2 = System.currentTimeMillis();
		boolean boardResultE2 = isValidBoard2(boardE);
		long finE2 = System.currentTimeMillis();
		System.out.println("El tablero E es válido: "+boardResultE2);
		System.out.println("Board1E tarda: "+ (finE2 - inicioE2) + " milisegundos.");
		long inicioF2 = System.currentTimeMillis();
		boolean boardResultF2 = isValidBoard2(boardF);
		long finF2 = System.currentTimeMillis();
		System.out.println("El tablero F es válido: "+boardResultF2); */
	//	System.out.println("Board1F tarda: "+ (finF2 - inicioF2) + " milisegundos.");
 //

		// EJEMPLOS DE ACTIVIDAD 3: ALGORITMO 1



		System.out.println("\nEJEMPLOS DE ACTIVIDAD 3\n");

		long inicioArrayA11 = System.currentTimeMillis();
		rotateArray(arrayA1, 500);
		long finArrayA11 = System.currentTimeMillis();

		long inicioArrayB11 = System.currentTimeMillis();
		rotateArray(arrayB1, 1000);
		long finArrayB11 = System.currentTimeMillis();

		long inicioArrayC11 = System.currentTimeMillis();
		rotateArray(arrayC1, 2000);
		long finArrayC11 = System.currentTimeMillis();

		long inicioArrayD11 = System.currentTimeMillis();
		rotateArray(arrayD1, 3000);
		long finArrayD11 = System.currentTimeMillis();

		long inicioArrayE11 = System.currentTimeMillis();
		rotateArray(arrayE1, 10000);
		long finArrayE11 = System.currentTimeMillis();

		long inicioArrayF11 = System.currentTimeMillis();
		rotateArray(arrayF1, 20000);
		long finArrayF11 = System.currentTimeMillis();

		System.out.println("Arreglo A1 rotado 500 veces: ");
		//System.out.println("Arreglo A1 rotado 500 veces: " + Arrays.toString(arrayA1));
		System.out.println("Algoritmo 1; A1 tarda: "+ (finArrayA11 - inicioArrayA11) + " milisegundos.");

		//System.out.println("Arreglo B1 rotado 1000 veces: " + Arrays.toString(arrayB1));
		System.out.println("Arreglo B1 rotado 1000 veces: ");
		System.out.println("Algotimo 1; B1  tarda: "+ (finArrayB11 - inicioArrayB11) + " milisegundos.");

		//System.out.println("Arreglo C1 rotado 2000 veces: " + Arrays.toString(arrayC1));
		System.out.println("Arreglo C1 rotado 2000 veces: " );
		System.out.println("Algotimo 1; C1  tarda: "+ (finArrayC11 - inicioArrayC11) + " milisegundos.");


		//System.out.println("Arreglo D1 rotado 3000 veces: " + Arrays.toString(arrayD1));
		System.out.println("Arreglo D1 rotado 3000 veces: ");
		System.out.println("Algoritmo 1; D1 tarda: "+ (finArrayD11 - inicioArrayD11) + " milisegundos.");

		System.out.println("Arreglo E1 rotado 10000 veces: ");
		//System.out.println("Arreglo E1 rotado 10000 veces: " + Arrays.toString(arrayE1));
		System.out.println("Algotimo 1; E1  tarda: "+ (finArrayE11 - inicioArrayE11) + " milisegundos.");

		System.out.println("Arreglo F1 rotado 20000 veces: ");
		//System.out.println("Arreglo F1 rotado 20000 veces: " + Arrays.toString(arrayF1));
		System.out.println("Algotimo 1; F1  tarda: "+ (finArrayF11 - inicioArrayF11) + " milisegundos.");


		//--------------------------------------------------------------------------------

		System.out.println("\nEJEMPLOS DE ACTIVIDAD 3: ALGORITMO 2 (;._.)\n");

		long inicioArrayA12 = System.currentTimeMillis();
		rotateArray2(arrayA1cop, 500);
		long finArrayA12 = System.currentTimeMillis();

		long inicioArrayB12 = System.currentTimeMillis();
		rotateArray2(arrayB1cop, 1000);
		long finArrayB12 = System.currentTimeMillis();

		long inicioArrayC12 = System.currentTimeMillis();
		rotateArray2(arrayC1cop, 2000);
		long finArrayC12 = System.currentTimeMillis();

		long inicioArrayD12 = System.currentTimeMillis();
		rotateArray2(arrayD1cop, 3000);
		long finArrayD12 = System.currentTimeMillis();

		long inicioArrayE12 = System.currentTimeMillis();
		rotateArray2(arrayE1cop, 10000);
		long finArrayE12 = System.currentTimeMillis();

		long inicioArrayF12 = System.currentTimeMillis();
		rotateArray2(arrayF1cop, 20000);
		long finArrayF12 = System.currentTimeMillis();

		System.out.println("\tArreglo A1 rotado 500 veces: ");
		//System.out.println("Arreglo A1 rotado 500 veces: " + Arrays.toString(arrayA1cop));
		System.out.println("\t\tAlgoritmo 2; A1 tarda: "+ (finArrayA12 - inicioArrayA12) + " milisegundos.");

		//System.out.println("Arreglo B1 rotado 1000 veces: " + Arrays.toString(arrayB1cop));
		System.out.println("\tArreglo B1 rotado 1000 veces: " );
		System.out.println("\t\tlgoritmo 2; B1 tarda: "+ (finArrayB12 - inicioArrayB12) + " milisegundos.");

		//System.out.println("Arreglo C1 rotado 2000 veces: " + Arrays.toString(arrayC1cop));
		System.out.println("\tArreglo C1 rotado 2000 veces: " );
		System.out.println("\t\tAlgoritmo 2; C1 tarda: "+ (finArrayC12 - inicioArrayC12) + " milisegundos.");

		//System.out.println("Arreglo D1 rotado 3000 veces: " + Arrays.toString(arrayD1cop));
		System.out.println("\tArreglo D1 rotado 3000 veces: " );
		System.out.println("\t\tlgoritmo 2; D1 tarda: "+ (finArrayD12 - inicioArrayD12) + " milisegundos.");

		//System.out.println("Arreglo E1 rotado 10000 veces: " + Arrays.toString(arrayE1cop));
		System.out.println("\tArreglo E1 rotado 10000 veces");
		System.out.println("\t\tAlgotimo 2; E1  tarda: "+ (finArrayE12 - inicioArrayE12) + " milisegundos.");

		//System.out.println("Arreglo F1 rotado 20000 veces: " + Arrays.toString(arrayF1cop));
		System.out.println("\t\tArreglo F1 rotado 20000 veces: " );
		System.out.println("\t\tAlgotimo 2; F1  tarda: "+ (finArrayF12 - inicioArrayF12) + " milisegundos.");


	}
}
