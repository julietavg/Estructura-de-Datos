package fciencias.edatos.practica04;

/**
* Búsqueda binaria en un arreglo.
* @author Julieta Vargas Gutiérrez 318341945
* @author Reyes Ramos Luz María 318211073
* @version 1.0 Noviembre 2021.
* @since Estructuras de datos 2021-2.
*/
public class BinarySearch{

    /**
	 * Regresa la posición de un elemento en un arreglo.
	 * @param arr el arreglo donde buscar.
	 * @param e el elemento a buscar.
         * @return la posición del elemento buscado
         * -1 si el elemento no se encuentra
	 */

    public static int find(int[] arr, int e){
		return find(arr ,0, arr.length,e);
	}

	/**
	 * Verifica si un elemento está contenido en un arreglo.
	 * @param arr el arreglo donde buscar.
	 * @param e el elemento a buscar.
	 */


	public static int find(int[]arr, int lo, int hi, int e)
    {
        if (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == e) //Sí el elemento esta en medio de el mismo
                return mid;
            if (arr[mid] > e) // Si el elemento es más pequeño que la mitad entonces esta en la izquierda
                return find(arr, lo, mid - 1, e);
            return find(arr, mid + 1, hi, e); // Sino se encuentra a la derecha 
        }
        return -1; //No está presente
    }
        
/**
        public static void main(String[] args) {
		int[] arr = {1, 5, 7, 9, 12, 34, 57, 80, 134, 567, 894, 900, 930, 989, 1034, 1067, 1134, 2456};
		int elem = 80;
                int r = (find(arr,elem));
                if(r == -1)
                     System.out.println("El elemento no esta):");
                  else
                 System.out.println("El elemento está en la posición  " + r);
    }
                    
   */
        
   }
