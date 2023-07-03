package fciencias.edatos.practica07;
import java.io.IOException;
import java.util.Scanner;
/**
* Implementación de los atributos que contiene un elemento 
* @author Julieta Vargas Gutiérrez 318341845
* @author Reyes Ramos Luz María 318211073
* @version Enero 2021.
* @since Estructuras de Datos 2022-1.
*/

public class Calculo {
   
       /** Mandamos a llamar a Map */
        Map<String, Elemento> map; 
        
        /** Masa del compuesto */
        double masa = 0.0;
       
        /** Constructor por omisión */
        public Calculo(){
          this.map = new AbstractHashMap<>();
        }
        
    /**
     * Metodo que valida si el elemento que ingresa el usuario es valido
     * @param elemento que verificaremos si existe
     * @return false si no se encontro el elemento
     */
    private boolean validacion(String[] elemento){
            for (String elemento1 : elemento) {
                if (!elemento1.matches("[A-Z][a-z]*\\d*")) {
                    System.out.println("No es una entrada válida):");
				  //  System.out.println("Intenta de esta forma = H,Na,Ca, (H2,O)");
                    return false;
                }
            }
        return true;
    }
        
    /**
     * Metodo que calcula la masa de un elemento o compuesto 
     * @param compuesto o elemento a calcular la masa
     * @return masa
     */
 
    public double calculadora(String[] compuesto) {
        int n; // Por lo que multiplicaremos al elemento
        String elem; //Elemento 
        for (String compuesto1 : compuesto) {
            if (compuesto1.matches(",*\\d.*")) { // Si se encuentra una coma (es compuesto)
                n = Integer.parseInt(compuesto1.replaceAll("\\D+", ""));
                elem = compuesto1.replaceAll("\\d", "");
                if (this.map.get(elem) != null) {
                    this.masa = this.masa + this.map.get(elem).getMasa() * n; // calculo de la masa
                }
            } else {
                elem = compuesto1.replaceAll("\\d", ""); //Es elemento
                if (this.map.get(elem) != null) {
                    this.masa = this.masa + this.map.get(elem).getMasa();
                }
            }
        }
        return this.masa;
    }
        
    
    /** Menu
     * @param args */
       
	public static void main(String[] args){
            
        Calculo tabla = new Calculo();
             try{
            Lectura lector = new Lectura();
            tabla.map = lector.mapTP;
        } catch(IOException e){
            System.out.println("No pude leer el archivo):");
        }
                       
        Scanner sc = new Scanner(System.in);
      
        int option;
        while (true) {
            try {
                while (true) {
                    System.out.println(
                            "\n\t\t CALCULO DE MASA MOLECULAR \n"
                            + "\t Bienvenida/o \n "
                            + "\t[1] Ver la tabla completa  \n "
                            + "\t[2] Calcular la masa  \n "
                            + "\t[3] Salir \n " );
                    System.out.println("\n\tPor favor ingrese su opción:");
                    
                    
                    option = Integer.parseInt(sc.nextLine());
                    
                   
                    switch (option) {
                        case 1 -> {
                           Lectura a = new Lectura();
                         break;
                        }
                        case 2 -> {
                            System.out.println("\nIngresa el elemento/compuesto que deseas calcular: ");
                            System.out.println("\tLa primera letra tiene que ser mayuscula!");
                            String comp = sc.nextLine();
                            String[] resp = comp.split("\\,");
                            boolean valid = tabla.validacion(resp);
                            tabla.masa = 0.0;
                            if (valid & tabla.calculadora(resp) > 0) {
                                tabla.masa = 0.0;
                                System.out.println(" El peso total de tu fórmula es: " + tabla.calculadora(resp) + "\n");

                            } else if (valid & tabla.calculadora(resp) == 0) {
                                System.out.println("\n Parece que " + comp + " no existe ):");
                                System.out.println("\n Intenta de esta forma (H, P , Na , (H2,O))");
                            }
                        }

                        case 3 -> {
                            System.out.println("Saliendo...");
                            return;
                        }
                      
                            
                        default -> System.out.println("\n\tOpción no disponible.");
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Typea una entrada valida!");

            }

        }
    }
}
    
