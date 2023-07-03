package fciencias.edatos.twentyQuestions;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa en especifico un nodo para el arbol del
 * juego de las 20 preguntas
 * 
 * @author Reyes Ramos Luz María 318211073
 * @version 2.0 Diciembre 2021
 * @since Estructuras de datos 2022-1
 */
public class QuestionNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Contiene una pregunta o una respuesta "entidad" */
    private String data;

    /** Nodo derecho del arbol, representa el camino si se reponde con un "ci :D" */
    private QuestionNode yesNode;

    /**
     * Nodo izquierdo, representa el camino que se tomará si se responde con un "No
     * :("
     */
    private QuestionNode noNode;

    /** Fecha en que se crea una pregunta ó personaje */
    private LocalDateTime date;

    /** Formato para mostrar la fecha */
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /** Representacion de la fecha en cadena */
    String dateS;

    /**
     * Constructor del nodo que representa una hoja.
     * Para el juego de TwentyQuestions, representa una respuesta
     * 
     * @param element Ente a almacenar: respuesta de la pregunta
     */
    public QuestionNode(String element) {
        this(element, null, null);
        date = date.now();
        dateS = date.format(format);

    }

    /**
     * Constructor para una rama dado un dato, es decir con subárbol izquierdo y
     * derecho.
     * Para TwentyQuestionsGame representa un nodo que almacena una pregunta.
     * 
     * @param data La pregunta a almacenar.
     * @param si   Nodo derecho: Camino a seguir si la respuesta del usuario es un
     *             si.
     * @param no   Nodo izquierdo: Camino a seguir si la respuesta del usuario es
     *             no.
     */
    public QuestionNode(String data, QuestionNode si, QuestionNode no) {
        this.data = data;
        yesNode = si;
        noNode = no;
        date = date.now();
        dateS = date.format(format);
    }

    /** Constructor vacio */
    public QuestionNode(){
    }

    /** 
     * Obtiene el elemento que almacena el nodo, en este caso : una fruta o una pregunta 
     * @return elemento que almacena el nodo.
    */
    public String getData() {
        return data;
    }

    /**
     * Modifica el elemento que almacena el nodo
     * @param data Nuevo elemento.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Obtiene el nodo derecho del arbol: el nodo si la respuesta en un 'SI'
     * @return Nodo derecho.
     */
    public QuestionNode getYesNode() {
        return yesNode;
    }

    /**
     * Obtiene el nodo izquierdo del arbol; el nodo si la respuesta es 'NO'
     * @return Nodo izquierdo.
     */
    public QuestionNode getNoNode() {
        return noNode;
    }

    /**
     * Modifica el nodo derecho del arbol; Nodo si.
     * @param yes Nuevo nodo derecho.
     */
    public void setYesNode(QuestionNode yes) {
        yesNode = yes;
    }

    /**
     * Modifica el nodo izquiero del arbol; Nodo no .
     * @param no Nuevo nodo izquierdo.
     */
    public void setNoNode(QuestionNode no) {
        noNode = no;
    }

    @Override
    public String toString() {
        return data;
    }

    /**
     * Obtiene la fecha como tipo LocalDateTime.
     * @return fecha tipo LocalDateTime
     */
    public LocalDateTime getDateT() {
        return date;
    }

    /**
     * Obtiene la representación en String de la fecha.
     * @return la fecha en String
     */
    public String getDate() {
        return dateS;
    }


}