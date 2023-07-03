package fciencias.edatos.practica06;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Implementación de árbol AVL
 * 
 * @author Vargas Gutiérrez Julieta 318241945
 * @Reyes Ramos Luz María 318211073
 * @author Emmanuel Cruz Hernández.
 * @version 3.0 Noviembre 2021 (Anterior 2.0 Julio 2021).
 * @since Estructuras de Datos 2022-1.
 */

public class AVLTree<K extends Comparable, T> implements TDABinarySearchTree<K,T> {

	/**
	 * Nodo de un arbol AVL.
	 */
	public class AVLNode {

		/** Altura del nodo. */
		public int altura;

		/** Hijo izquierdo. */
		public AVLNode izquierdo;

		/** Hijo derecho. */
		public AVLNode derecho;

		/** Padre del nodo. */
		public AVLNode padre;

		/** Elemento almacenado en el nodo. */
		public T elemento;

		/** Clave del nodo. */
		public K clave;

		/** Balance factor */
		int bf;


		/**
		 * Obtiene el elemento almacenado en el nodo.
		 * @return elemento del nodo.
		 */
		public T getElement() {return elemento;}

		/**
		 * Crea un nuevo nodo AVL
		 * 
		 * @param element el elemento a almacenar.
		 * @param key     la clave del nodo.
		 * @param padre   el padre del nodo
		 */
		public AVLNode(T element, K key, AVLNode padre) {
			elemento = element;
			clave = key;
			this.padre = padre;
			altura = this.getAltura();
			bf =0;

		}

		/**
		 * Calcula la altura del nodo.
		 */

		public int getAltura() {
			// Si este nodo es hoja
			if (izquierdo == null && derecho == null) {
				return 0;
			} else if (izquierdo != null && derecho != null) { // Dos hijos
				int max = izquierdo.getAltura() > derecho.getAltura() ? izquierdo.getAltura() : derecho.getAltura();
				return 1 + max;
			} else { // Tiene solo un hijo
				boolean tieneIzquierdo = izquierdo != null;
				return 1 + (tieneIzquierdo ? izquierdo.getAltura() : derecho.getAltura());
			}
		}


		/**
		 * Actualiza la altura del nodo.
		 */
		public void actualizaAltura() {
			this.altura = this.getAltura();
		}

	}

	private AVLNode raiz;

	@Override
	public T retrieve(K k) {
		AVLNode node = retrieve(k,raiz);
		if(node == null)
			return null;
		return node.getElement();
	}

	/**
	 * Obtenia el nodo con una clave específica.
	 *
	 * @param k      la clave a buscar
	 * @param actual el nodo actual
	 * @return el nodo con clave k o null si no existe.
	 */
	private AVLNode retrieve(K k, AVLNode actual) {
		// Verificamos que actual es null
		if (actual == null)
			return null;

		int compare = k.compareTo(actual.clave);

		// Si existe el elemento
		if (compare == 0) {
			return actual;
		}

		if (compare < 0) { // Caso del hijo izquiero
			return retrieve(k, actual.izquierdo);
		} else { // Caso del hijo derecho
			return retrieve(k, actual.derecho);
		}
	}

	@Override
	public void insert(T e, K k) {
		if (raiz == null) { // Arbol vacío
			raiz = new AVLNode(e, k, null);
			return;
		}
		AVLNode v = insert(e, k, raiz);

		// Rebalancear a partir de v hasta raiz
		 rebalance(v);
	}

	/**
	 * Inserta un nodo de forma recursiva.
	 * 
	 * @param e      el elemento a insertar
	 * @param k      es la clave del nodo a insertar
	 * @param actual el nodo actual
	 * @return
	 */
	public AVLNode insert(T e, K k, AVLNode actual) {
		if (k.compareTo(actual.clave) < 0) { // Verificamos sobre el izquierdo
			if (actual.izquierdo == null) { // Insertamos en esa posición
				actual.izquierdo = new AVLNode(e, k, actual);
				//actual.actualizaAltura();
				return actual.izquierdo;
			} else { // Recursión sobre el izquierdo
				return insert(e, k, actual.izquierdo);
			}
		} else { // Verificamos sobre la derecha
			if (actual.derecho == null) { // Insertamos en esa posición
				actual.derecho = new AVLNode(e, k, actual);
				//actual.actualizaAltura();
				return actual.derecho;
			} else { // Recursión sobre el derecho
				return insert(e, k, actual.derecho);
			}
		}


	}

        /**
         *  Método booleano que nos dice dice que está balanceado
         * @param node
         * @return true si esta balanceado
         *         false en otro caso
         */
	private boolean isBalanced(AVLNode node) {

		if (node == null)
			return true;
		if(node.izquierdo == null && node.derecho == null){
			return  true;
		}

		int lh =0 , rh = 0;
		if(node.izquierdo == null){
			lh=-1;
			rh = node.derecho.getAltura();
		} else if(node.derecho == null){
			rh = -1;
			lh = node.izquierdo.getAltura();
		}else{
			lh = node.izquierdo.getAltura();
			rh = node.derecho.getAltura();
		}

		if (Math.abs(lh - rh) < 2 && isBalanced(node.izquierdo) && isBalanced(node.derecho))
			return true;
		return false;
	}

	@Override
	public T delete(K k) {
		AVLNode v = retrieve(k, raiz);

		// El elemento que queremos eliminar no está en el árbol
		if (v == null) {
			return null;
		}

		T eliminado = v.elemento;

		// Eliminar con auxiliar
		AVLNode w = delete(v);

		// Rebalancear
		rebalance(w);

		return eliminado;
	}

	
	/**
         *  Método que elimina un elemento
         * @param v elemento a eliminar
         */

	private AVLNode delete(AVLNode v) {
		if (v.izquierdo != null && v.derecho != null) { // Tiene dos hijos
			AVLNode menor = findMin(v.derecho);
			swap(menor, v);
			return delete(menor);
		} else if (v.izquierdo == null && v.derecho == null) { // No tiene hijos
			boolean esIzquierdo = v.padre.izquierdo == v;
			if (esIzquierdo) {
				v.padre.izquierdo = null;
			} else {
				v.padre.derecho = null;
			}
			return v.padre;
		} else { // Sólo tiene un hijo
			boolean hijoIzquierdo = v.izquierdo != null;
			if (hijoIzquierdo) {
				swap(v, v.izquierdo);
				return delete(v.izquierdo);
			} else {
				swap(v, v.derecho);
				return delete(v.derecho);
			}
		}
	}

	@Override
	public T findMin() {
             	return findMin(raiz).elemento;
	}
        
        /**
         *  Método para intercambiar de lugares dos nodos
         * @param v elemento a intercambiar
         * @param w elemento a intercambir
         */
	private void swap(AVLNode v, AVLNode w) {
		T value = v.elemento;
		K clave = v.clave;
		v.elemento = w.elemento;
		v.clave = w.clave;
		w.elemento = value;
		w.clave = clave;
	}

       /**
        *  Auxiliar para encontrar al elemento con la clave minima
        * @param actual elemento con la clave minima
        */
	private AVLNode findMin(AVLNode actual) {
		if (actual == null)
			return null;
		AVLNode iterador = actual;

		while (iterador.izquierdo != null) {
			iterador = actual.izquierdo;
		}

		return iterador;

	}

	
	@Override
	public T findMax(){
		return findMax(raiz).elemento;
	}
        
        /**
        * Auxiliar para encontrar al elemento con la clave maxima
        * @param actual elemento con la clave maxima
        */
        
	private AVLNode findMax(AVLNode actual){
		if(actual == null)
			return null;
		AVLNode iterador = actual;

		while(iterador.derecho != null){
			iterador =iterador.derecho;
		}

		return iterador;
	}


	@Override
	public void preorden() {
		preorden(raiz);
	}
    
        /**
         * Auxiliar que realiza un recorrido preorden
         * @param actual elemento
         */
        
	private void preorden(AVLNode actual) {
		if (actual == null)
			return;

		System.out.println(actual.elemento);
		preorden(actual.izquierdo);
		preorden(actual.derecho);
	}

	@Override
	public void inorden() {
		inorden(raiz);
	}
        
          /**
         * Auxiliar que realiza un recorrido inorden
         * @param actual elemento
         */

	private void inorden(AVLNode actual) {
		if (actual == null) {
			return;
		}
		preorden(actual.izquierdo);
		System.out.println(actual.elemento);
		preorden(actual.derecho);
	}

	@Override
	public void postorden() {
		postorden(raiz);
	}
        
         /**
         * Auxiliar que realiza un recorrido postorden
         * @param actual elemento
         */

	private void postorden(AVLNode actual) {
		if (actual == null)
			return;
		postorden(actual.izquierdo);
		postorden(actual.derecho);
		System.out.println(actual.elemento);
	}

         /**
         * Rotación hacia la izquierda
         * @param v el nodo a rotar
         */
	private void leftRotate(AVLNode v){
		AVLNode u = v.derecho;
		v.derecho = u.izquierdo;

		if(u.izquierdo != null){
			u.izquierdo.padre = v;

		}
		u.padre = v.padre;
		if(v.padre == null){
			this.raiz = u;
		}else if(v == v.padre.izquierdo){
			v.padre.izquierdo=u;
		}else{
			v.padre.derecho = u;
		}
		u.izquierdo = v;
		v.padre = u;

		//Updating balance factor
		v.bf = v.bf-1-Math.max(0, u.bf);
		u.bf = u.bf -1 + Math.min(0,v.bf);
	}
        /**
         * Rotación hacia la derecha
         * @param v el nodo a rotar
         */

	private void rightRotate(AVLNode v){
		AVLNode u = v.izquierdo;
		v.izquierdo = u.derecho;

		if(u.derecho != null){
			u.derecho.padre = v;
		}
		u.padre = v.padre;
		if(v.padre == null){
			this.raiz = u;
		}else if(v == v.padre.derecho){
			v.padre.derecho = u;
		}else{
			v.padre.izquierdo = u;
		}
		u.derecho = v;
		v.padre =u;
		v.bf = v.bf +1 - Math.min(0, u.bf);
		u.bf = u.bf +1 + Math.max(0,v.bf);
	}


      /**
       * Método que rebalancea
       * @param node nodo a rebalancear
       */
        
	private void rebalance(AVLNode node){
		if(node.bf < -1 || node.bf>1){ // |H(hi)  - H(hd)| <2
			rebalanceAux(node);
			return;
		}
		//	Por default bf es 0  - - - >  Actualizando (-) para izquierdo , (+) para subarbol derecho.
		if(node.padre != null){
			if(node == node.padre.izquierdo){ // si es hijo izquierdo
				node.padre.bf-=1;
			}
			if(node == node.padre.derecho){ //si es hijo derecho
				node.padre.bf+=1;
			}
			if(node.padre.bf != 0){
				rebalance(node.padre); //Si no es 0 --> Hay un desbalance ---> Recursión.
			}
		}
	}

	
        
        /**
         * Auxiliar para rebalancear un arbol
         * @param node nodo a rebalancear
         */
	private  void rebalanceAux(AVLNode node){
		if(node.bf > 0){
			if(node.derecho.bf<0){ 
				rightRotate(node.derecho);
				leftRotate(node);
			}else {
				leftRotate(node);
			}
		}else if(node.bf<0){
			if(node.izquierdo.bf>0){
				leftRotate(node.izquierdo);
				rightRotate(node);
			}else{
				rightRotate(node);
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return raiz == null;
	}


	/**
         * Método que nos ayuda a imprimir diagrama del arbol
         * @param node el nodo actual
         * @param indent para poder imprimir
         * @param last booleano del nodo anterior 
         */
        
	private void printHelper(AVLNode node, String indent, boolean last) {
		if (node != null) {
			System.out.print(indent);
			if (last) {
				System.out.print("R----");
				indent += "     ";
			} else {
				System.out.print("L----");
				indent += "|    ";
			}

			System.out.println(node.getElement() + "(H = " + node.getAltura() + ")");

			printHelper(node.izquierdo, indent, false);
			printHelper(node.derecho, indent, true);
		}
	}
        
        /**
         * Imprime el diagrama
         */
	public void diagramPrint() {
		printHelper(this.raiz, "", true);
	}
       
        /**
         * Método que nos ayuda a saber si una clave es valida (solo acepta números enteros)
         * @param sc clave del usuario
         */
        
	private int validaKey(Scanner sc){	
		int key;
		while (true) {
			try {
				System.out.println("\n\n\tIngrese el elemento (numeros enteros):");
				key  = Integer.parseInt(sc.nextLine());
				return key;
			} catch (Exception e) {
				System.out.println("\n\n\tIngresa un elemento valido, para este caso"+
				"solo  son números enteros!. \n\tIntenta de nuevo.");
			}
		}
		
    }
        /**
         * Auxiliar del método main para realizar los recorridos
         * @param sc reccorido que escoga el usuario
         */
        
	private void recorrerTree(Scanner sc) {
		while (true) {
			System.out.println("\n\n\t Recorridos del árbol \n\tElige en que orden deseas recorrerlo:" +
					"\n\n\tA.- Preorden.\n\n\tB.- Inorden.\n\n\tC.- Postorden \n\n\tD.-Regresar a menu principal"
					+ "\n\tTypea tu opción:");
			char option = sc.next().toUpperCase().charAt(0);
			sc.nextLine();
			switch (option) {
				case 'A':
					preorden();
					diagramPrint();
					break;
				case 'B':
					inorden();
					diagramPrint();
					break;
				case 'C':
					postorden();
					diagramPrint();
					break;
				case 'D':
					return;
				default:
					System.out.println("\n\tEscoge A, B, C o D");
					break;
			}
		}
	}


	public static void main(String[] args) {
		AVLTree<Integer, Integer> arbol = new AVLTree<>();

		Scanner sc = new Scanner(System.in);
		int option;
		while (true) {
			try {
				while (true) {
					System.out.println(
							"\n\t\t AVL TREE\n" +
									"\t[1] Retrieve  \n " +
									"\t[2] Insert  \n " +
									"\t[3] Delete \n " +
									"\t[4] Find min: Encuentra el elemento con la clave de valor mínimo. \n " +
									"\t[5] Find max: Halla el elemento con la clave de valor máximo.\n " +
									"\t[6] Comprueba si el árbol esta vacio \n " +
									"\t[7] Recorridos. \n " +
									"\t[8] Salir \n ");
					System.out.println("\n\tPor favor ingrese su opción:");
					option = Integer.parseInt(sc.nextLine());
					switch (option) {
						case 1:
                                                    if(!arbol.isEmpty()){
							int key = arbol.validaKey(sc);
							int retrieved = arbol.retrieve(key);
							if(retrieved <=0 || retrieved >0 ){
								System.out.println("\n\tLa clave ingresada fue: "+ key+ "\tElemento: "+ retrieved );
							break;
                                                        }
                                                        System.out.println("\n\n\t---------Elemento no hallado, probablemente la clave no pertenezca al arból.");
						      
                                                    }else{
                                                        System.out.println("El ábol esta vacio!");
                                                    }
                                                        break;

						case 2:
							System.out.println("\n\tInsertar elemento ---->\t \t");
							int key1 = arbol.validaKey(sc);
							arbol.insert(key1, key1);
							System.out.println("\n\n\t\tOperación concluida!!");
							
							arbol.diagramPrint();

						//	System.out.println("\n\t Balanceado?" + arbol.isBalanced(arbol.raiz));
							break;
                                                        
                                                         case 3:
                                                               if(arbol.isEmpty()){  
                                                                   System.out.println("No hay ningun elemento que puedas eliminar");
                                                               }else{  
                                                                  try {
                                                                  System.out.println( "Inserte clave del elemento que quieres eliminar" );
                                                                 int aux = sc.nextInt();
                                                                  sc.nextLine();
                                                                  arbol.delete(aux);
                                                                } catch (InputMismatchException ime) {
                                                                  System.out.println("\t Ingresa un número entero") ;
                                                                  sc.nextLine();
                                                                  continue;
                                                                } catch (Exception e) {
                                                                  System.out.print("\n\t No puedo eliminar este elemento ya que es la raiz):");
                                                                  sc.nextLine();
                                                                  continue;
                                                                }
                                                               }
                                                                break;
                                                    
						case 4:
							if (arbol.isEmpty()) {
				                              System.out.println("El árbol está vacio");
                                                              break;
                                                        }else{
                                                          System.out.println("Elemento con la clave minima es: \n" + arbol.findMin()   );
                                                    
                                                              } 
				                         break;
				                    
						case 5:
							if (arbol.isEmpty()) {
				                              System.out.println("El árbol está vacio");
                                                              break;
                                                        }else{
                                                          System.out.println("Elemento con la clave máxima es: \n" + arbol.findMax()   );
                                                    
                                                              } 
				                         break;
						case 6:
							System.out.println("\n\t Verifica si el árbol esta vacio \t \t");
							if (arbol.isEmpty()) {
								System.out.println(" \n\t El árbol esta vacio ");
							} else {
								System.out.println("\n\nEl árbol tiene al menos un elemento!");
							}
							break;
						case 7: // Recorridos

							if (arbol.isEmpty()) {
								System.out.println("\n\n No se puede recorrer el árbol porque está vacio\t\t");
							} else {
								arbol.recorrerTree(sc);
							}
							break;
						case 8:
							System.out.println("Saliendo...");
							return;

						default:
							System.out.println("\n\tOpción no disponible.");
							break;
					}
				}
			} catch (Exception e) {
				System.out.println("Typea una entrada valida!");

			}

		}
        }
}


