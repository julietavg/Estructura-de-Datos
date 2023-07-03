
package fciencias.edatos.practica05;
import java.util.Scanner;
/**
* Implementación de un árbol binario de busqueda.
* @author Julieta Vargas Gutiérrez 318341945
* @author Reyes Ramos Luz María 318211073
* @version 3.0 Noviembre 2021 (Anterior 2.0 Julio 2021).
* @since Estructuras de Datos 2022-1.
*/
public class BinarySearchTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>{
	
	/**
	 * Nodo para un árbol binario de búsqueda.
	 */
	public class BinaryNode{

		/** Clave. */
		public K key;

		/** Elemento. */
		public T element;

		/** Padre del nodo. */
		public BinaryNode parent;

		/** Hijo izquierdo. */
		public BinaryNode left;

		/** Hijo derecho. */
		public BinaryNode right;

		/**
		 * Crea un nuevo nodo.
		 * @param key la clave.
		 * @param element el elemento a almacenar.
		 * @param parent el padre del nodo.
		 */
		public BinaryNode(K key, T element, BinaryNode parent){
			this.key = key;
			this.element = element;
			this.parent = parent;
		}
        //Auxiliar delete
        /** Metodo para intercambiar el elemento de un nodo.
		 *  @param e elemento por el que se cambiara.
		 */
		 public void setElement(T e){
			this.element = e;
		}

		public T getElement(){
			return element;

		}
                
		@Override
		public String toString(){
			return element.toString();
		}
	}
	

	/** Root */
	private BinaryNode root;

	@Override
     /**
     * Dada una clave k regresea el elemento asociado a la clave
     * @param key clave del elemento asociado
     */
	public T retrieve(K k){
		BinaryNode node = retrieve(root, k);
		if(node == null)
			return null;
		return node.element;
	}
     /**
     * Auxiliar del método retrieve 
     * @param actual nodo en el que se emipiza a buscar
     * @param k clave del nodo 
     */
	private BinaryNode retrieve(BinaryNode actual, K k){
		// No se encuentra el elemento
		if(actual == null)
			return null;

		// Si encontramos el elemento
		if(actual.key.compareTo(k) == 0)
			return actual;

		// Comparamos los elementos
		if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			return retrieve(actual.left, k);
		} else { // Verificar en la derecha
			return retrieve(actual.right, k);
		}

	    
	}

    /**
     * Método que dado un elemento t inserta el elemento t con clave k en el árbol
     * @param e elemento del nodo a insertar
     * @param k clave del elemento 
     */ 
        
	@Override
	public void insert(T e, K k){
	  // Si es vacío entonces insertamos al nuevo elemento como la raíz del árbol
          if(this.isEmpty()){
            this.root= new BinaryNode(k,e,null); 
            return; 
        }
         //Sino es vacio, un nodo iterador que comience en la raíz
        BinaryNode iterator= this.root;
	     // Invocar el método insert de tres parámetros
          insert(iterator,e,k);
	}
    /**
     * Auxiliar del metodo insert
     * @param actual el nodo al cual se esta buscando la posición a insertar 
     * @param e elemento del nodo a insertar
     * @param k clave del elemento 
     */
	
	private void insert(BinaryNode actual, T e, K k){
		// Comparamos las claves: la clave de actual con k. Con compareTo
		int clave = actual.key.compareTo(k);   
		// Si la clave es menor verificamos que el hijo izquierdo no sea null
		if(clave >= 0){ 
			if(actual.left == null){
				actual.left = new BinaryNode(k,e,actual);
				return;
			}
			// * Si es null insertamos el nuevo elemento como hijo izquierdo del actual. Si no recursión
			insert(actual.left,e,k);   
		}
		// Si la clave es mayor
		if(clave < 0){
			if(actual.right == null){
				actual.right = new BinaryNode(k,e,actual);
				return;
			} 
			// * Si es null insertamos el nuevo elemento como hijo derecho del actual. Si no recursión
			insert(actual.right,e,k);
		}
	}
        
    /**
    * Dada una clave k elimina al elemento asociado con el árbol 
    * @param k clave del elemento
    * @return k el elemento eliminado porBorrar
    * @return null en caso de no existir
    */
      @Override
	public T delete(K k){
		// retieve(root, k)
		BinaryNode porBorrar = retrieve(root, k);
		// Si ese resultado es null -> regresar null
		if (porBorrar == null)// Verificar sí esta en el árbol
            return null;
		// Crear una variable que almacene el elemento en retrieve
		T eretrieve = porBorrar.element;
		BinaryNode father  = porBorrar.parent;
		// Cuando tiene dos hijos
		if (porBorrar.right != null && porBorrar.left != null){
			BinaryNode max = findMax(porBorrar.left);
			delete(max.key);
			porBorrar.key = max.key;
			porBorrar.element = max.element;
			return eretrieve;
		}
		// Cuando no tiene hijos
		if(porBorrar.right == null && porBorrar.left == null){
			//Verificar si es hijo izquierdo o derecho.
			if(father.left == porBorrar)
				father.left = null;
			else
				father.right = null;
			return porBorrar.element;
		}
		// Cuando solo tiene un hijo
		boolean izquierdo = father.left == porBorrar;
		if (porBorrar.right != null) //Subir el derecho
		    porBorrar = porBorrar.right;
		else //Subir el izquierdo
	        porBorrar = porBorrar.left;
	    porBorrar.parent = father;

		if(izquierdo)
			father.left = porBorrar;
		else
			father.right = porBorrar;
		return eretrieve;
	}
	

	@Override
	public T findMin(){
		if(isEmpty())
			return null;
		
		BinaryNode iterator = root;
		// Mientras sí tenga hijo izquierdo -> Que actual se mueva al izquierdo
		while(iterator.left != null)
			iterator = iterator.left;
		
		// Ya encontramos al nodo con clave menor
		
		return iterator.getElement();
	
	}


	@Override
	public T findMax(){
		if(isEmpty())
			return null;
		BinaryNode iterator = root;
		while (iterator.right != null) 
			iterator = iterator.right;
		
		return iterator.getElement();
	}
	
	/*
    * Auxiliar que nos da el elemento con la clave asociada maxima
    *@param actual el nodo en el que estamos
    *@return maxElem el elemento con la clave maxima
    */
    public BinaryNode findMax(BinaryNode actual){
		if(actual == null)
			return null;

		BinaryNode maxElem = actual;
		while(maxElem.right != null)
			maxElem = maxElem.right;
		return maxElem;
	} 
        
    //Creo que este queda así, sino checalo je
    @Override
    public boolean isEmpty() {
     return this.root == null; //
      }
	@Override
	public void preorden(){
		// Primero verifica la raiz
		// Aplica preorden al izquierdo
		// Aplica preorden al derecho
		preorden(root);
	}

	private void preorden(BinaryNode current){
		//root, left, right
		if(current == null){
			return;
		}
		System.out.print(current+" ");
		preorden(current.left);
		preorden(current.right);

	}

	@Override
	public void inorden(){
		//izquierda, raiz , derecha
		inorden(root);
	}
	private void inorden(BinaryNode current){
		
		if(current == null)
			return;
		
		inorden(current.left);
		System.out.print(current+ " ");
		inorden(current.right);
	}

	@Override
	public void postorden(){
		//izquierda, derecha,raiz
		postorden(root);
	}

	private void postorden(BinaryNode current){
		if(current == null)
			return;
		
		postorden(current.left);
		postorden(current.right);
		System.out.print(current +" " );
	}

	//Se elgió representar un arbol binario con caracteres como elementos y claves con números enteros.

	

	private  int validaKey(Scanner sc){
		
		int key;
		while (true) {
			try {
				System.out.println("\n\n\tIngrese la clave del elemento (clave numeros enteros):");
				key  = Integer.parseInt(sc.nextLine());
				return key;
			} catch (Exception e) {
				System.out.println("\n\n\tIngresa una clave válida, para este caso"+
				"solo  son admisibles claves con números enteros!. \n\tIntenta de nuevo.");
			}
		}
		
    }

	private void recorrerTree(Scanner sc){
		
		while (true) {
			System.out.println("\n\n\tRecorrer el arból!!\n\tElige en que orden deseas recorrerlo:"+
			"\n\n\tA.- Preorden.\n\n\tB.- Inorden.\n\n\tC.- Postorden \n\n\tD.-Regresar a menu principal"+ "\n\tTypea tu opción:");
			char option = sc.next().toUpperCase().charAt(0);
			//sc.nextLine();
			switch (option) {
				case 'A': //preorden
					preorden();
					break;
				case 'B':
					inorden();
					break;
				case 'C':
					postorden();
					break;
			    case 'D':
				    return;
				default:
					System.out.println("\n\tOpción no disponible :(.\tIntenta con una opción valida xD");
					break;
			}
		}
	}

	

	



  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BinarySearchTree<Integer, Character> tree = new BinarySearchTree<>();
		int option;
		while (true) {
			try {
				
				while (true) {
					System.out.println(
					"\n\t\tBinary Search Tree\n"+  
					"\t[1] Retrive  \n "+
					"\t[2] Insert  \n "+
					"\t[3] Delete \n "+
					"\t[4] Find min: Encuentra el elemento con la clave de valor mínimo. \n "+
					"\t[5] Find max: Halla el elemento con la clave de valor máximo.\n "+
					"\t[6] Recorridos. \n "+
					"\t[7] Exit. :) \n ");
					System.out.println("\n\tPor favor ingrese su opción:");
					option = Integer.parseInt(sc.nextLine());
					switch (option) {
						case 1: //retrieve
						   
							System.out.println("\n\tBuscar/recuperar elemento ---->\t \t"+
							"Clave del elemento:");
							int key = tree.validaKey(sc);
							Character retrieved = tree.retrieve(key);
							if(retrieved == null ){
								System.out.println("\n\n\t---------Elemento no hallado, probablemente la clave no pertenezca al arból.");
								break;
							}
							System.out.println("\n\tLa clave ingresada fue:"+ key+ "\tElemento:"+ retrieved );
							break;
						case 2: 
							 
							System.out.println("\n\tInsertar elemento ---->\t \t"+
							"Ingrese el elemento a insertar (Este arbol en particular recibe carácteres :D):");
							char element = sc.nextLine().charAt(0);
							System.out.println("\n\tClave del elemento  ----->");
							int key1 = tree.validaKey(sc);
							tree.insert(element, key1);
							System.out.println("\n\n\t\tOperación concluida!!");
							break;
						case 3:
							System.out.println("\n\tEliminar elemento ---->\t \t"+
							"Clave del elemento a eliminar ---->");
							int removedKey = tree.validaKey(sc);
							Character removed  = tree.delete(removedKey);
							if(removed == null ){
								System.out.println("\n\n\t---------Elemento no hallado, probablemente la clave no pertenezca al arból.");
								break;
							}
							System.out.println("\n\n\tEl elemento eliminado fue:"+ removed);
							break;
						case 4:
						    Character min =  tree.findMin();
						    if(min == null ){
							System.out.println("\n\n\t--------Arból vacío");
							break;
                            }
							System.out.println("\n\\ntEl elemento con la clave mínima es:"+min);
							break;
						case 5:
						    Character max = tree.findMax();
							if(max == null){
								System.out.println("\n\n\t--------Arból vacío");
								break;
							}
							System.out.println("\n\\ntEl elemento con la clave máxima es:"+ max);
							break;
						case 6: //Recorridos
							 tree.recorrerTree(sc);
							 break;
						case 7:
							System.out.println("Saliendo...");
							return;
							
						default:
							System.out.println("\n\tOpción no disponble.");
							break;
					}
				}
			} catch (Exception e) {
				System.out.println("Typea una entrada valida!");
			}
		
			
		
				
		}
		}

	
}
