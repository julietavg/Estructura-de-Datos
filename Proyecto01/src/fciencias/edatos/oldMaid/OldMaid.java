import reference.*;
import java.util.Random;

import javax.smartcardio.CardException;
/**
 * Clase que representa la modalidad de juego "Old Maid".
 * @author Reyes Ramos Luz María. 318211073
 * @author 
 * @version 1.0 Noviembre 2021
 * @since EDD 2022-1
 */
public class OldMaid {

    /**Contiene el mazo de las 51 cartas */
    static DoubleLinkedList<Card> allCards;


    /**Contiene la cantidad total de jugadores */
    static DoubleLinkedList<Player> players;

    //creando en mazo de todas las cartas
    /**
     * Genera un deck con las 51 cartas de inicio del juego.
     * @return
     */
    public Deck generateCards(){
       
        allCards = new DoubleLinkedList<>();
        String[] cardName = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] cardSign = {"♠", "♦", "♥", "♣"};
        for(int i=0;i<13;i++){
            if(!cardName[i].equals("Queen")){
                allCards.add(0,new Card(cardName[i], cardSign[0], 1));
            }
            allCards.add(0,new Card(cardName[i], cardSign[1], 2));
            allCards.add(0,new Card(cardName[i], cardSign[2], 2));
            allCards.add(0,new Card(cardName[i], cardSign[3], 1));
        }
        Deck cards = new Deck(auxCreateCards(allCards));
        return cards ;
    }

    private DoubleLinkedList<Card>  auxCreateCards(DoubleLinkedList<Card> allCards ){
        String[] cards = {"🃞","🂾", "🃎", "🂮", "🃝", "🂽", "🃏", "🃛","🂻","🃋",
                          "🃛", "🃚", "🂺" ,"🃊" , "🂪", "🃙", "🂹","🃉", "🂩","🃘","🂸",
                          "🃈","🂨", "🃗", "🂷", "🃇","🂧", "🃖","🂶", "🃆", "🂦", "🃕" , "🂵" , "🃅",
                          "🂥", "🃔", "🂴", "🃄", "🂤", "🃓", "🂳","🃃", "🂣", "🃒", "🂲", "🃂", "🂢",
                          "🃑", "🂱", "🃁", "🂡" };
        for (int n = 0; n < cards.length; n++) 
            allCards.get(n).setDraw(cards[n]);
        
        return allCards;

    }

    /**
     * Genera una lista de jugadores: Total de participantes en el juego.
     * A lo maximo son 10 jugadores. Rango 1-10
     * @param n Número de jugadores que participarán en el juego.
     * @return Lista con n jugadores.
     */
    public DoubleLinkedList<Player> generatePlayers(int n){
        if(n<1 || n>10){
            System.out.println("Número de jugadores fuera de rango 🚩🚩🚩");
            return new DoubleLinkedList<>();
        }
        players = new DoubleLinkedList<>();
        for (int k = 0; k<n; k++)
            players.add(0, new Player("Player " + n+1 + " ٩(●̮̮̃•̃)۶") );
        
        return players;

    }
    //Revolver/ Barajear cartas
    public Deck shuffle(Deck allCards){
        Queue<Integer> index = auxShuffle();
        int i =0;
        DoubleLinkedList<Card> copy = allCards.getDecks();
        //System.out.println("\nSize queue:" + index.size()+ "\tCopy:"+ copy.size());
        //System.out.println("\n\tC O P Y - \n"+ copy);
        DoubleLinkedList<Card> shuffle = new DoubleLinkedList<>();
        for(int n = 0; n<copy.size(); n++){
            i= index.first();
            index.dequeue();
            //System.out.println("\nn="+n +"\tindex:"+ i);
            shuffle.add(n, copy.get(i));
        }



        return new Deck(shuffle);
    }

    //auxiliar para barajear cartas
    /*
     * Regresa una cola con numeros random sin repeticiones 
     * del 0 al 50, esto será de ayuda para utilizarse como 
     * índices.
     */
    private Queue<Integer> auxShuffle(){
        Queue <Integer> indices = new Queue<>();
        DoubleLinkedList<Integer> repeated = new DoubleLinkedList<>();
        
        Random rn = new Random();
        int index = rn.nextInt(51);
        for(int k = 0; k<51; k++){
            while (repeated.contains(index)) {
                index = rn.nextInt(51);
            }
            repeated.add(0, index);
            indices.enqueue(index);
        }
        //System.out.println(indices);
        return indices;

    }
}
