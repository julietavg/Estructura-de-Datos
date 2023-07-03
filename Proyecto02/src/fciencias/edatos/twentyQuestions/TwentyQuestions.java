package fciencias.edatos.twentyQuestions;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase que implementa el juego de las 20 preguntas.
 * 
 * @author Reyes Ramos Luz María 318211073
 * @version 2.0 Diciembre 2021
 * @since Estructuras de datos 2022-1
 */
public class TwentyQuestions {

    /** Arbol del juego */
    QuestionTree questions = new QuestionTree();

    /** Banco de preguntas */
    public static final String fileName = "Banco/preguntas.txt";

    /** Scanner para leer las entradas del usuario */
    Scanner consola = new Scanner(System.in);

    /**
     * Carga el estado del arbol en su ultima ejecución, si no halla el archivo;
     * construye el arbol a partir del banco de pregutas txt actualizado.
     * En otro caso, crea el arbol con el constructor por default.
     * 
     * @param file Nombre del archivo serializado.
     */
    public void leerArbol(String file) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        // Deserializa el arbol , creal el arbol persistente
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            questions = (QuestionTree) ois.readObject();

        } catch (FileNotFoundException fnfe) {
            // Crea el arbol de 0 a partir del txt
            try {
                questions.buildTree(new Scanner(new File(fileName)));
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println(ioe);
            ioe.printStackTrace();
        } finally {
            // Cierre
            try {
                if (fis != null)
                    fis.close();
                if (ois != null)
                    ois.close();
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Guarda el estado del arbol en un archivo serializado.
     * 
     * @param fileName Nombre del archivo.
     */
    public void saveTree(String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(questions);

        } catch (FileNotFoundException fnfe) {
            System.out.println("\n\t1 " + fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("\n\t2 " + ioe.getMessage());
            ioe.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (oos != null)
                    oos.close();

            } catch (Exception e) {
                System.out.println("\n\t3 " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    /**
     * Obtiene el arbol banco de preguntas del juego.
     * 
     * @return arbol del juego.
     */
    public QuestionTree getGameTree() {
        return questions;
    }

    /**
     * Juego de las 20 preguntas
     */
    public void playGame() {

        Scanner console = new Scanner(System.in);
        leerArbol("tree.ser");
        do {
            System.out.println("\n\tPiensa en una fruta para que adivine... (owo)\n");
            getGameTree().asking(console, 19);
        } while (getGameTree().si("\n\n\tJugar de nuevo?;D", console));
        // Escribir el arbol al archivo
        try {
            getGameTree().writeTree(new PrintStream(new File(fileName)));
        } catch (Exception e) {
            System.out.println(e);
        }

        saveTree("tree.ser");
        getGameTree().addList();

    }

}
