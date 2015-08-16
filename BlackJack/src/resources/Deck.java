package resources;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ian
 *
 */
/**
 * @author ian
 *
 */
public class Deck {

	private ArrayList<Card> cards = new ArrayList<Card>();
	

	/**
	 * @author ian
	 * 
	 * create a new deck of cards
	 * 
	 */
	public Deck(){
		generateDeck();
		
	}
	
	public void generateDeck(){
		
		// loop around suit
		for(Suit s : Suit.values()){
			// loop around each card in suit
			for(Rank r : Rank.values()){
				cards.add(new Card(s,r));	
				
			}

		}
		
	}
	
	
	public void shuffleDeck(){
		
		Collections.shuffle(cards);
		
	}
	
	
	/**
	 * removes a card from the deck
	 * returns a card object
	 * 
	 * @return Card c - the card that is to dealt
	 */
	public Card dealCard(){
		int topCardNdx = cards.size()-1;
		Card c = cards.get(topCardNdx);
		cards.remove(topCardNdx);	
		
		return c;
		
	}
	
	
	/** returns the card to the bottom of the pack
	 * @param c
	 */
	public void returnCard(Card c){
		
		
		
	}
	
	public int getCardsLeft(){
		return cards.size();
		
	}
	

}
	

