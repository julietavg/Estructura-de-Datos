import reference.*;  

/**
 * Clase que implementa las cartas
 * @author Julieta Vargas Gutiérrez 318341945
 * @author
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */


  public class Card {
      
    //Atributos
	private String nombreCarta;
	private String signoCarta;

	private int par;

	/** Posibles pares de una carta */ //Dado a que una carta tiene 2 pares
	int[] pairs;

	/**Representación de la carta */
	private String cardDraw;
       	
        /***********************************************
        **           CONSTRUCTORES                    **
        ************************************************/
    public Card(){
    }

    /**
	* Crea una nueva varta
	* @param nombreCarta el nombre de la carta.
	* @param signoCarta el signo de la carta.
	* @param par si son pares.
	*/
	
	public Card(String nombreCarta, String signoCarta, int par){
		this.nombreCarta = nombreCarta;
		this.signoCarta = signoCarta;
		this.par = par;
	}

         /***********************************************
        **         MÉTODOS DE ACCESO                  **
        ************************************************/

       /**
	* Regresa el nombre de la carta
	* @return el nombre de la carta
	*/
        
	public String getNombreCarta() {
		return nombreCarta;
	}
        
        /**
	* Modifca el nombre de la carta
	* @param nombreCarta el nuevo nombre de la carta
	*/
            
	public void setNombreCarta(String nombreCarta) {
		this.nombreCarta = nombreCarta;
	}
        
        /**
	* Regresa el signo de la carta
	* @return el signo de la carta
	*/
        
	public String getSignoCarta() {
		return signoCarta;
	}
        
         /**
	* Modifca el signo de la carta
	* @param signoCarta el nuevo nombre de la carta
	*/
        
	public void setSignoCarta(String signoCarta) {
		this.signoCarta = signoCarta;
	}
        
        /**
	* Regresa si la carta es par
	* @return es par
	*/
        
	public int getPar() {
		return par;
	}
        
        /**
	* Modifca si la carta es par
	* @param par si la carta es par
	*/

	public void setPar(int par) {
		this.par = par;
	}


	public String getDrawCard(){
		return cardDraw;
	}

	public void setDraw(String draw){
		this.cardDraw = draw;
	}
	public int[] getPairs(){
		return pairs;
	}
	public void setPairs(int[] pairs){
		this.pairs = pairs;
	}

	@Override 
	public String toString(){
		return  cardDraw + "\t["+getNombreCarta() + "  "+ getSignoCarta() + "]\n";
	}
}
