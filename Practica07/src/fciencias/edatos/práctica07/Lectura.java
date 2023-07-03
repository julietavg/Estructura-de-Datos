package fciencias.edatos.practica07;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Implementación de la lectura de un archivo txt para convertirla en una tabla
 * hash
 * @author Julieta Vargas Gutiérrez 318341845
 * @author Reyes Ramos Luz María 318211073
 * @version Enero 2021.
 * @since Estructuras de Datos 2022-1.
 */

public final class Lectura{
    //Constructor por omisión 
    public Lectura() throws IOException{
	LecturaTabla("tabla-periodica.txt");
	}
    
    Map<String, Elemento> mapTP;
     /**
     * Metodo para llenar un Mapa a partir de un archivo .txt
     * @param ruta - Cadena con la ruta especificada del archivo
     */
    public void LecturaTabla(String ruta){
            mapTP = new AbstractHashMap<>(5227);
            BufferedReader br = null;
            try {
            FileReader file = new FileReader(ruta);
            br = new BufferedReader(file);
            String comma;
             //Lee el archivo coma por coma
            while ((comma=br.readLine()) != null){
                
                  /** atributos del elemento  */
                String nombre = comma.split(",")[0];
                String simbolo = comma.split(",")[1];
                Double peso = Double.parseDouble(comma.split(",")[2]);
                Elemento elemento = new Elemento(nombre, simbolo, peso);
                
                 //inserta los atributos en un hashmap si no son vacios
                if (!nombre.equals("") && !simbolo.equals("") && !peso.equals("")) {
                    mapTP.put(elemento.getSimbolo(), elemento);
                }
            }
              } catch (IOException | NumberFormatException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
                ;
            }
       
       }
    }
	
            
        }

