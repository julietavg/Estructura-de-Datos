package fciencias.edatos.practica04;

import java.util.Arrays;
import java.util.Random;

/**
 * Implementación de algunos algoritmos de ordenamiento.
 * @author Julieta Vargas Gutiérrez 318341945
 * @auhor Reyes Ramos Luz María 318211073
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */
public class Sorter{

	/**
	 * Cambia de posición dos elementos entre sí de un arreglo de enteros.
	 * @param arr el arreglo del cual cambiar la posición de los elementos.
	 * @param i el índice del primer elemento a cambiar.
	 * @param j el índice del segundo elemento a cambiar.
	 */
	private static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


  //-----------------------------QuickSort-------------------------------------
	/**
	 * Ordena un arreglo de forma ascendente con merge sort.
	 * @param arr el arreglo a ordenar.
	 */
	public static void quicksort(int[] arr){
		quicksort(arr, 0, arr.length-1);
	}
	/**
	 * Auxiliar de mergeSort para dividir y mezclar.
	 * @param arr el arreglo con los elementos a dividir y mezclar.
	 * @param lo el índice de inicio a modificación.
	 * @param hi el índice del último elemento a modificación.
	 */
	 private static void quicksort(int[] arr, int lo, int hi){
            if(hi <= lo) //Cuando el fragmento esta ordenado lo a hi
                return;
            int j = partition(arr,lo,hi);
            quicksort(arr, lo , j-1);
            quicksort(arr, j+1,hi);

        }

	/**
	 * Mezcla dos arreglos, ordenando de menor a mayor.
	 * @param arr el arreglo con los elementos a modificar.
	 * @param lo el inicio de la primera mitad
	 * @param hi el índice del último elemento.
         * @return j
	 */

          public static int partition(int[]arr, int lo, int hi){
            int i = lo;
            int j = hi + 1;
            int piv = arr[lo];

            while(true){
               while(arr[++i]< piv) if(i == hi){
                    break;
                }
               while(piv < arr[--j])if (j == lo){
                    break;
                }
                if (i >= j){
                    break;

                }
             swap(arr, i , j);
            }

            swap (arr,lo,j);
             return j;
            }




    //----------------------------- MergeSort -------------------------------------
         /**
	 * Ordena un arreglo de forma ascendente con merge sort.
	 * @param arr el arreglo a ordenar.
	 */
	public static void mergeSort(int[] arr){
		mergeSort(arr, 0, arr.length-1);
	}

	/**
	 * Auxiliar de mergeSort para dividir y mezclar.
	 * @param arr el arreglo con los elementos a dividir y mezclar.
	 * @param lo el índice de inicio a modificación.
	 * @param hi el índice del último elemento a modificación.
	 */
	private static void mergeSort(int[] arr, int lo, int hi){
		// Cuando ya esta ordenado el fragmento de lo hasta hi
		if(hi <= lo)
			return;

		// La mitad del corte del arreglo
		int mid = lo + (hi-lo) / 2;

		mergeSort(arr, lo, mid);
		mergeSort(arr, mid+1, hi);

		merge(arr, lo, mid, hi);
	}

	/**
	 * Mezcla dos arreglos, ordenando de menor a mayor.
	 * @param arr el arreglo con los elementos a modificar.
	 * @param lo el inicio de la primera mitad.
	 * @param mid el índice de la mitad del subarreglo.
	 * @param hi el índice del último elemento.
	 */
	private static void merge(int[] arr, int lo, int mid, int hi){
		int i = lo;
		int j = mid+1;
		int[] aux = Arrays.copyOf(arr, arr.length);

		for(int k = lo ; k <= hi; k++){
			// Si ya nos acabamos los elementos de la primera mitad
			if(i > mid)
				arr[k] = aux[j++];
			else if(j > hi) // Si ya nos acabamos la segunda mitad
				arr[k] = aux[i++];
			else if(aux[j] < aux[i]) // El menor está en la primera mitad
				arr[k] = aux[j++];
			else // El manor está en la segunda mitad
				arr[k] = aux[i++];
		}
	}


      //-----------------------------mergeSortMejorado-------------------------------------

        public static void mergeSortMejorado(int[] arr)
    {
        if(arr == null)
        {
            return;
        }

        if(arr.length > 1)
        {
            int mid = arr.length / 2;

            // Corta la parte izquierda
            int[] izquierda = new int[mid];
            for(int i = 0; i < mid; i++)
            {
                izquierda[i] = arr[i];
            }

            // Corta la parte derecha
            int[] derecha = new int[arr.length - mid];
            for(int i = mid; i < arr.length; i++)
            {
                derecha[i - mid] = arr[i];
            }
            mergeSort(izquierda);
            mergeSort(derecha);

            int i = 0;
            int j = 0;
            int k = 0;

            // Junta la parte derecha e izquierda
            while(i < izquierda.length && j < derecha.length)
            {
                if(izquierda[i] < derecha[j])
                {
                    arr[k] = izquierda[i];
                    i++;
                }
                else
                {
                    arr[k] = derecha[j];
                    j++;
                }
                k++;
            }
           // Agrupa los elementos restantes

            while(i < izquierda.length)
            {
                arr[k] = izquierda[i];
                i++;
                k++;
            }
            while(j < derecha.length)
            {
                arr[k] = derecha[j];
                j++;
                k++;
            }
        }
    }



	/**
	 * Crea un nuevo arreglo con números pseudoaleatorios.
	 * @param n el tamaño del arreglo a crear.
	 * @param max el mayor elemento a generar en el arreglo.
	 * @return un arreglo de tamaño n con números pseudoaleatorios de 0 a 19.
	 */
	public static int[] generate(int n, int max){
		int[] res = new int[n];
		Random rn = new Random();
		for(int i = 0 ; i < n; i++)
			res[i] = rn.nextInt(max);
		return res;
	}


  //-----------------------------  MAIN      -------------------------------------

       public static void main(String[] args) {

          int[] arr1 = generate(10000, 15);
          int[] arr2 = Arrays.copyOf(arr1, arr1.length);
		      int[] arr3 = Arrays.copyOf(arr1, arr1.length);

		long inicio = System.currentTimeMillis();
		long fin = System.currentTimeMillis();


       System.out.println("No ordenado: " + Arrays.toString(arr1));
                //Tiempo de MergeSort

		inicio = System.currentTimeMillis();
		mergeSort(arr1);
		fin = System.currentTimeMillis();
		System.out.println("Ordenado con MergeSort: " + Arrays.toString(arr1));
		System.out.println("Ordenado con mergeSort tardó: " + (fin - inicio) + " milisegundos");


                //Tiempo Mejorado de MergeSort

    //System.out.println("No ordenado: " + Arrays.toString(arr2));
		inicio = System.currentTimeMillis();
		mergeSortMejorado(arr2);
		fin = System.currentTimeMillis();
		System.out.println("Ordenado con MergeSort: " + Arrays.toString(arr2));
		System.out.println("Ordenado con mergeSort tardó: " + (fin - inicio) + " milisegundos");




		//Tiempo de QuickSort
		//System.out.println("No ordenado: " + Arrays.toString(arr3));
		inicio = System.currentTimeMillis();
		quicksort(arr3);
		fin = System.currentTimeMillis();
		System.out.println("Ordenado con QuickSort: " + Arrays.toString(arr3));
		System.out.println("Ordenado con quicksort tardó: " + (fin - inicio) + " milisegundos");
	}

}
