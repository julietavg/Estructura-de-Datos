package fciencias.edatos.twentyQuestions;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Clase que implementa una lista simplemente ligada.
 * 
 * @author Reyes Ramos Luz María 318211073
 * @author
 * @version 2.0 Diciembre 11, 2021
 * @since EDD-2022-1
 */
public class LinkedList implements Serializable {

    /** Cabeza de la lista */
    private Node head;

    /** Guarda la longitud de la lista */
    private int size;

    private class Node {

        /** Contiene el elemento de la lista */
        private QuestionNode element;

        /** Referencia al nodo siguiente */
        private Node next;

        /**
         * Constructor de un nodo
         * 
         * @param element El elemento a almacenar
         */
        public Node(QuestionNode element) {
            this.element = element;
        }

        /**
         * Obtiene el elemento almacenado en el nodo.
         * 
         * @return Elemento almacenado.
         */
        public QuestionNode getElement() {
            return element;
        }

        /**
         * Obtiene la referencia siguiente a un nodo.
         * 
         * @return el nodo siguiente.
         */
        public Node getNext() {
            return next;
        }

        /**
         * Modifica el elemento que contiene un nodo.
         * 
         * @param element Nuevo elemento.
         */
        public void setElement(QuestionNode element) {
            this.element = element;
        }

        /**
         * Modifica la referencia siguiente de un nodo.
         * 
         * @param next Nodo siguiente
         */
        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();

        }
    }

    /**
     * Añade un elemento a la lista.
     * 
     * @param element El elemento a agregar.
     */
    public void add(QuestionNode element) {
        Node newElement = new Node(element);
        newElement.setNext(head);
        head = newElement;
        size++;
    }

    @Override
    public String toString() {
        Node iterator = head;
        String list = "";
        while (iterator != null) {

            list += iterator.getElement() + "\n\n";
            iterator = iterator.getNext();
        }
        return list;
    }

    /**
     * Determina si la lista está vacía.
     * 
     * @return true si la lista es vacía , false en otro caso.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Obtiene el elemento de una lista dado un indíce.
     * 
     * @param i indice del elemento a buscar.
     * @return El elemento almacenado en el índice dado.
     */
    public QuestionNode get(int i) {
        // Si la lista es vacia
        try {
            if (isEmpty()) {
                System.out.println("\n\tLa lista está vacía");
                return null;
            }
            Node iterador = head;

            for (int n = 0; n < size; n++) {
                if (n == i) {
                    return iterador.getElement();
                }
                iterador = iterador.getNext();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * Obtiene la longitud de la lista.
     * 
     * @return longitud de la lista.
     */
    public int size() {
        return size;
    }

    /**
     * Vacía la lista.
     */
    public void clean() {
        head = null;
        size = 0;
    }

    /**
     * Determina si un elemento está contenido en la lista.
     * 
     * @param element El elemento a buscar.
     * @return true si esta en la lista, false en otro caso.
     */
    public boolean contains(QuestionNode element) {
        if (isEmpty()) {
            return false;
        }
        Node iterator = head;
        for (int n = 0; n < size(); n++) {
            if (element.equals(iterator.getElement()))
                return true;
            iterator = iterator.getNext();
        }
        return false;
    }

    public QuestionNode remove(int i) {
        Node eliminado = null;
        try {
            // List vacia
            if (isEmpty()) {
                System.out.println("\n\tLa lista está vacía");
                return null;
            }
            // Si i es 0
            if (i == 0) {
                eliminado = head;
                head = head.getNext();
                size--;
                return eliminado.getElement();
            }
            // Algun otro indice
            Node iterador = head;
            for (int n = 0; n < size(); n++) {
                if (n == i) {
                    eliminado = iterador;
                    iterador.setNext(iterador.getNext().getNext());
                    size--;
                    return eliminado.getElement();
                }
                iterador = iterador.getNext();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return eliminado.getElement();
    }

    // Intentando mergeSort

    /*
     * 1) Si la cabeza es NULL o sólo hay un elemento en la lista ligada, entonces
     * return
     * 2) En otro caso se divide a la lista ligada en dos mitades : 'a' y 'b'
     * 3) Ordenar las mitades 'a' y 'b': mergeSort(a) , mergeSort(b)
     * 4) Mezclar a y b, y actualizar la cabeza.
     */

    // Mezcla las dos mitades ordenadas a y b

    private Node sortedMergeAlpha(Node a, Node b) {
        Node result = null;
        // casos base
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        // Elegir a o b y recursion
        if (a.getElement().getData().compareTo(b.getElement().getData()) <= 0) {
            result = a;
            result.setNext(sortedMergeAlpha(a.getNext(), b));
        } else {
            result = b;
            result.setNext(sortedMergeAlpha(a, b.getNext()));
        }
        return result;
    }

    // Sort por fecha

    private Node sortedMergeDate(Node a, Node b) {
        Node result = null;
        // casos base
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        // Elegir a o b y recursion
        if (a.getElement().getDateT().compareTo(b.getElement().getDateT()) <= 0) {
            result = a;
            result.setNext(sortedMergeDate(a.getNext(), b));
        } else {
            result = b;
            result.setNext(sortedMergeDate(a, b.getNext()));
        }
        return result;
    }

    // obtener la mitad de la lista
    private Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /**
     * Ordena los elementos de una lista usando mergeSort
     * 
     * @param h nodo con referencias a las lista a ordenar: cabeza de la lista
     * 
     * @return Nodo con referencias a los demás ordenados: lista ordenada.
     */
    public Node mergeSortAlpha(Node h) {
        if (h == null || h.getNext() == null) {
            return h;
        }
        Node middle = getMiddle(h);
        Node nextoMiddle = middle.getNext();
        middle.setNext(null);
        Node left = mergeSortAlpha(h);
        Node right = mergeSortAlpha(nextoMiddle);
        Node sortedList = sortedMergeAlpha(left, right);
        return sortedList;
    }

    public Node mergeSortDate(Node h) {
        if (h == null || h.getNext() == null) {
            return h;
        }
        Node middle = getMiddle(h);
        Node nextoMiddle = middle.getNext();
        middle.setNext(null);
        Node left = mergeSortDate(h);
        Node right = mergeSortDate(nextoMiddle);
        Node sortedList = sortedMergeDate(left, right);
        return sortedList;
    }

    public Node getHead() {
        return head;
    }

    // imprimir lista con fechas
    public String printListD() {
        Node iterator = head;
        String list = "";
        while (iterator != null) {

            list += iterator.getElement().getData() + "\t" + iterator.getElement().getDate() + "\n\n";
            iterator = iterator.getNext();
        }
        return list;
    }

    // Por si no funciona el merge con fechas
    /**
     * Padsa los elementos de una lista a un arreglo y los ordena por
     * fecha de creación.
     * @param list lista a ordenar
     * @return Arreglo ordenado con los elementos de la lista.
     */
    public QuestionNode[] sortArrayListDate(LinkedList list) {
        QuestionNode[] array = toArray(list);
        Arrays.sort(array, new Comparator<QuestionNode>() {
            @Override
            public int compare(QuestionNode n1, QuestionNode n2) {
                return n1.getDateT().compareTo(n2.getDateT());
            }
        });
        printArrayDate(array);
        return array;

    }

    /**
     * Padsa los elementos de una lista a un arreglo y los ordena alfabeticamente.
     * @param list lista a ordenar
     * @return Arreglo ordenado con los elementos de la lista.
     */
    public void sortAlpha(LinkedList list) {
        QuestionNode[] array = toArray(list);
        Arrays.sort(array, new Comparator<QuestionNode>() {
            @Override
            public int compare(QuestionNode n1, QuestionNode n2) {
                return n1.getData().compareTo(n2.getData());
            }
        });
        printArray(array);

    }

    
    private QuestionNode[] toArray(LinkedList list) {

        QuestionNode[] arrayList = new QuestionNode[list.size()];
        for (int n = 0; n < arrayList.length; n++) {
            QuestionNode element = get(n);
            arrayList[n] = element;
        }
        return arrayList;
    }

    private String printArray(QuestionNode[] array) {
        String elements = "";
        for (QuestionNode questionNode : array) {
            elements += questionNode + "\n\n";
        }
        System.out.println(elements);
        return elements;
    }

    private String printArrayDate(QuestionNode[] array) {
        String elements = "";
        for (QuestionNode questionNode : array) {
            elements += questionNode + "\t" + questionNode.getDate() + "\n\n";
        }
        System.out.println(elements);
        return elements;
    }

}
