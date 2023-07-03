import java.util.Random;
import reference.*;


/**
 *
 * @author julie
 */
public class Main {
    
   static DoubleLinkedList<Card> allCards;
    static DoubleLinkedList<Player> players;
       
      /***********************************************
      **              MÉTODOS                       **
      ************************************************/
    
    
    /**
	*  Crea las cartas
	*/
    
    
    
    /**
    * Genera los jugadores
    */
                
    
   public static void generaPlayer(){
		players = new DoubleLinkedList<>();
		players.add(0,new Player("Player 1"));
		players.add(0,new Player("Player 2"));
		players.add(0,new Player("Player 3"));
		players.add(0,new Player("Player 4"));
        players.add(0,new Player("Player 5"));
		players.add(0,new Player("Player 6"));
		players.add(0,new Player("Player 7"));
		players.add(0,new Player("Player 8"));
	} 


   
    /**
    * Distribuye las cartas
    */
   /*private static void distribuyeCartas() {
		DoubleLinkedList<Card> 
                        tempCards = null; //Aquí pasa algo mal pero no sé que es):
                
		for(int i=0,k=0;i<allCards.size();i++,k++){
			if(k==4)k=0;
			Random r = new Random();
			int randint = (Math.abs(r.nextInt()) % (allCards.size()-i));
			players.get(k).getDeck().addToDeck(tempCards.get(randint));
			tempCards.remove(randint);
		}
	} */
   
    
  
    
    
    public static void main(String[] args) {
		OldMaid p = new OldMaid();
		//allCards = p.generateCards().getDecks();
		Deck d =  p.generateCards();
		System.out.println(d);
		
		System.out.println("\n\tREVUELTAS?\n"+p.shuffle(d));
                //generaPlayer();
                //distribuyeCartas();
		
                
        
        
        
       
        
       
       
       
        
       
    }
    
}
