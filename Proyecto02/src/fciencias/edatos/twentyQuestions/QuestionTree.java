package fciencias.edatos.twentyQuestions;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Clase que implementa un arbol si/no para el juego
 * de adivinar a un personaje.
 * 
 * Con el uso de arboles binarios se le hacen al usuario una serie de preguntas
 * con si/no como respuesta, si la respuesta no está disponible en el árbol,
 * este se actualizará con el personaje que estaba pensando el usuario y una
 * pregunta
 * relacionada al mismo.
 * 
 * @author Reyes Ramos Luz María 318211073
 * @version 2.0 Diciembre 2021
 * @since Estructuras de datos 2022-1
 */
public class QuestionTree implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    /** Raíz del arból */
    private QuestionNode root;

    /** Lista con la frutas : hojas del árbol */
    private LinkedList fruitsList = new LinkedList();

    /** Almacena las preguntas */
    private LinkedList questionsList = new LinkedList();

    /**
     * Construye un arbol con sólo una hoja (una respuesta).
     * En este caso, el personaje es un personaje onmipresente
     */ // Por default si no encuentra el txt ni el archivo serializado.
    public QuestionTree() {
        root = new QuestionNode("Sandia :D"); // No sé que poner JAJAJAJ
    }

    /**
     * Construye el arbol a partir de un archivo de texto con las
     * frutas y preguntas.
     * 
     * @param input Scanner que lee las entradas del archivo de texto
     */
    public void buildTree(Scanner input) {
        while (input.hasNextLine()) {
            root = readTree(input);
        }
    }

    // Auxiliar rescursivo de buildTree
    private QuestionNode readTree(Scanner sc) {
        String type = sc.nextLine();
        String data = sc.nextLine();

        QuestionNode raiz = new QuestionNode(data);
        if (type.contains("Q:")) {
            raiz.setYesNode(readTree(sc));
            raiz.setNoNode(readTree(sc));
        }

        return raiz; // regresa la raiz

    }

    /**
     * Escribe la informacion de los nodos y las hojas del árbol en un archivo
     * de texto: pregutas y frutas --> Actualiza el banco de preguntas en el txt.
     * 
     * @param output Objeto PrintStream
     */
    public void writeTree(PrintStream output) {
        if (output == null) {
            throw new IllegalArgumentException();
        }
        writeTree(root, output);
    }

    // Auxiliar recursivo que escribe la información del árbol a un txt
    private void writeTree(QuestionNode root, PrintStream salida) {
        if (isAnswer(root)) {
            salida.println("A:");
            salida.println(root.getData().toUpperCase());
        } else {
            salida.println("Q:");
            salida.println(root.getData().toUpperCase());
            writeTree(root.getYesNode(), salida);
            writeTree(root.getNoNode(), salida);
        }
    }

    // Determina si un nodo es pregunta o respuesta
    private boolean isAnswer(QuestionNode nodo) {
        return (nodo.getYesNode() == null || nodo.getNoNode() == null);
    }

    // Hacer las preguntas al usuario hasta que adivine, despues exapndir el arbol
    // bah

    /**
     * Hace las preguntas al usuario, si es la fruta que estaba pensando el usuario
     * termina,
     * si no es así se actualiza el banco de preguntas con la nueva fruta que no
     * adivino y
     * una pregunta que la indentifique. En caso de que adivinar a un personaje
     * sobrepase el limite de las
     * 20 preguntas el metodo termina.
     * 
     * @param sc   Scanner para pedir entradas al usuario.
     * @param cont Almacena la el numero de preguntas que lleva adivinar a un
     *             personaje.
     */
    public void asking(Scanner sc, int cont) {
        root = asking(root, sc, cont);
    }

    // Metodo recursivo auxiliar para asking
    private QuestionNode asking(QuestionNode actual, Scanner sc, int cont) {
        if (isAnswer(actual)) {
            if (cont <= 0) {
                System.out.println("\n\tSe alcanzó el limite de 20 preguntas, fin del juego!!");
                return new QuestionNode();
            }
            if (si("¿Acaso la fruta en la que estas pensando es " + actual.getData() + "?", sc)) {
                System.out.println("\n\t\tA D I V I N E! :D");

            } else { // Aprender

                System.out.println("\n\t¿En qué fruta estabas pensando?");
                String characName = sc.nextLine();
                if (characName.equals("\n") || characName.equals("") || characName.equals(" ")
                        || spaceString(characName)) {
                    while (characName.equals("\n") || characName.equals("") || characName.equals(" ")
                            || spaceString(characName)) {
                        System.out.println("\n\t¿En qué fruta estabas pensando?, escribe un nombre valido");
                        characName = sc.nextLine();
                    }
                }
                QuestionNode respuesta = new QuestionNode(characName);
                System.out.println(
                        "\n\tProvea una SI/NO pregunta para que pueda distinguir a su fruta---> u.u \n\n\t--No introduzca '?'");
                String userQuestion = sc.nextLine();
                if (userQuestion.equals("\n") || userQuestion.equals("") || userQuestion.equals(" ")
                        || spaceString(userQuestion)) {
                    while (userQuestion.equals("\n") || userQuestion.equals("") || userQuestion.equals(" ")
                            || spaceString(userQuestion)) {
                        System.out.println(
                                "\n\tProvea una valida SI/NO pregunta para que pueda distinguir a su fruta ---> u.u  \n\n\t--No introduzca '?'");
                        userQuestion = sc.nextLine();
                    }
                }
                if (si("Con esta pregunta puedo identificar a " + respuesta + "?", sc)) {
                    actual = new QuestionNode(userQuestion + "?", respuesta, actual);
                } else {
                    actual = new QuestionNode(userQuestion + "?", actual, respuesta); // Mejor dos signos que ninguno
                }
            }
        } else {

            if (si(actual.getData(), sc)) {
                // cont++;
                actual.setYesNode(asking(actual.getYesNode(), sc, cont - 1));
            } else {
                // cont++;
                actual.setNoNode(asking(actual.getNoNode(), sc, cont - 1));
            }

        }
        return actual;
    }

    // Si es una respuesta afirmativa

    /**
     * Confima la respuesta del usuario.
     * 
     * @param mensaje Mensaje, pregunta que se quiere verficar.
     * @param sc      Scanner para leer las entradas del usuario.
     * @return true si la respuesta es afirmativa, false en otro caso.
     */
    public boolean si(String mensaje, Scanner sc) {
        String m = mensaje + " (S/N)?";
        System.out.println(m);
        String input = sc.nextLine().trim().toUpperCase();
        while (!input.equals("S") && !input.equals("N")) {
            System.out.println("\n\t Typea una entrada valida; S = si ó N = No :D"
                    + m + "\n");
            input = sc.nextLine().trim().toUpperCase();

        }
        return input.equals("S");
    }

    // intento de recorrido:
    /**
     * Recorrido inorden del árbol.
     */
    public void inorden() {
        // izquierda, raiz , derecha
        inorden(root);
    }

    // Auiliar de metodo inorden
    private void inorden(QuestionNode current) {

        if (current == null)
            return;

        inorden(current.getNoNode());
        System.out.println(current + current.getDate());
        inorden(current.getYesNode());
    }

    // Añadir hojas a una lista

    /**
     * Añade los elementos del árbol a sus respectivas listas:
     * Si es una hoja se añade a la lista de frutas, en otro caso es
     * una preguntas, entonces se añade a la lista de preguntas.
     */
    public void addList() {
        addToList(root);
    }

    // Auxiliar para agregar las hojas a la lista
    private void addToList(QuestionNode current) {
        if (current == null) {
            return;
        }
        if (isAnswer(current)) {
            fruitsList.add(current);
        } else {
            questionsList.add(current);
            addToList(current.getYesNode());
            addToList(current.getNoNode());
        }
    }

    /**
     * Obtiene la lista de hojas (frutas) del árbol.
     * 
     * @return lista de frutas.
     */
    public LinkedList getFruiList() {
        return fruitsList;
    }

    /**
     * Obtiene la lista de preguntas del árbol.
     * 
     * @return lista de preguntas.
     */
    public LinkedList getQuestionsList() {
        return questionsList;
    }

    // Verifica si la cadena que pasa el usuario solo consiste en espacios.
    private boolean spaceString(String cadena) {

        for (int n = 0; n < cadena.length(); n++)
            if (cadena.charAt(n) != ' ')
                return false;

        return true;
    }

}
