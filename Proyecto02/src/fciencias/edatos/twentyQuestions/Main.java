package fciencias.edatos.twentyQuestions;
import java.util.Scanner;

/**
 * Menu del juego Twenty Questions
 * 
 * @author Reyes Ramos Luz María 31821073
 * @version 2.0 Diciembre 2021
 * @since Estructuras de datos 2022-1
 */
public class Main {


    private Scanner consola = new Scanner(System.in);

    private TwentyQuestions game = new TwentyQuestions();

    
    public void menu() {
        int option;
        while (true) {
            try {
                System.out.println("\n\t\tBienvenido a Twenty-Quesions ---> F r u t a s\n" + "\n\tQue desea hacer?\n");
                System.out.println(
                        "\t[1] Jugar.\n" +
                                "\t[2] Ver listado de preguntas que contiene el juego en orden alfabetico. \n" +
                                "\t[3] Ver listado de frutas que contiene el juego en orden alfabetico.\n" +
                                "\t[4] Ver listado de preguntas en el orden que fueron agregadas.\n " +
                                "\t[5] Ver listado de frutas en el orden que fueron agregadas.\n" +
                                "\t[6] Salir. \n" +
                                "\t\t Ingrese su opción:");
                option = Integer.parseInt(consola.nextLine());
                switch (option) {
                    case 1:
                        game.playGame();
                        break;
                    case 2:
                        game.getGameTree().getQuestionsList().sortAlpha(game.getGameTree().getQuestionsList());
                        break;
                    case 3:
                        game.getGameTree().getFruiList().sortAlpha(game.getGameTree().getFruiList());
                        break;
                    case 4:
                        game.getGameTree().getQuestionsList().sortArrayListDate(game.getGameTree().getQuestionsList());
                        break;
                    case 5:
                        game.getGameTree().getFruiList().sortArrayListDate(game.getGameTree().getFruiList());
                        break;
                    case 6:

                        System.out.println("\n\tSaliendo...");
                        try {

                            Thread.sleep(1000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        return;
                    default:
                        System.out.println("\n\tUps! esa opcion aun no esta disponible crack ;)");
                        break;
                }

            } catch (Exception e) {
                System.out.println("\n\tTypee una una entrada valida, solo las opciones disponibles en este menu :(.");
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.menu();
    }

}
