import reference.*;  
/**
 * Clase que implementa el jugador
 * @author Julieta Vargas Gutiérrez 318341945
 * @author
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */

public class Player {
        Deck deck;      
   
     //Atributos   
	String name;
 
        /***********************************************
        **           CONSTRUCTORES                    **
        ************************************************/
      
	/**
	* Crea una nuevo jugador apartir del mazo
	*/
	public Player(){
	      this.deck = new Deck();
	}
        
	/**
	* Crea una nuevo jugador
	* @param name nombre del jugador.
	*/
        
	public Player(String name){
		this.name = name + " ٩(●̮̮̃•̃)۶";
		this.deck = new Deck();
	}
        
        
        /***********************************************
        **         MÉTODOS DE ACCESO                  **
        ************************************************/
        
        /**
	* Regresa el mazo
	* @return deck.
	*/
        
	public Deck getDeck() {
		return deck;
	}
        
        /**
	* Modifica el mazo 
	* @param deck el nuevo nombre de la mascota.
	*/

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
        
         /**
	* Regresa el nombre del jugador
	* @return name.
	*/

	public String getName() {
		return name;
	}
        
        /**
	* Modifica el nombre del jugador
	* @param name.
	*/

	public void setName(String name) {
		this.name = name;
	}
}
