import reference.*;

/**
 * Clase que implementa el mazo
 * @author Julieta Vargas Gutiérrez 318341945
 * @author Reyes Ramos Luz Marìa  318211073
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */


public class Deck {
        
     /** Listas doblemente ligadas para cartas */ //	
	DoubleLinkedList<Card> decks;

     /**Constuctor a partir de listas doblemente ligadas*/     
	public Deck(){
		this.decks = new DoubleLinkedList<>();
	}

	public Deck(DoubleLinkedList<Card> cards){
		this.decks = cards;
	}

        
        /**
	* Permite añadir carta
        * @param card la carta a agregar
	*/
        
	public void addToDeck (Card card){
		this.decks.add(0,card);
	}
        
         /**
	* Permite remover una carta
        * @param i indice de la carta que quieres remover
	*/
   
       public void removeFromDeck (int i){
		this.decks.remove(i);
	}
       
       /**
	* Regresa el tamaño del mazo
	* @return el tamaño del mazo
	*/   
	public int getSize (){
		return this.decks.size();
	}

       /**
	* Regresa apartir de listas doblemente ligadas un mazo con cartas
	* @return decks
	*/       
        
	public DoubleLinkedList<Card> getDecks() {
		return decks;
	}
        
        /**
	* Modifca el mazo apartir de una carta
        * @param decks
	*/

	public void setDecks(DoubleLinkedList<Card> decks) {
		this.decks = decks;
	}

	@Override
	public String toString(){
		return getDecks().toString();
	}


 
}
