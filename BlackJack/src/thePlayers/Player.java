package thePlayers;

import java.util.ArrayList;
import java.util.Observable;

import resources.Card;



/**
 * @author ian
 * 
 * Base Player model class that models a blackjack player
 * A player has a hand of 0 to n cards
 * is able to add cards to the hand
 * is able to give cards back 
 * can give the total for the cards in his hand
 * can list the cards in his hand
 * is bust if cards total goes above 21
 *
 */
public abstract class Player  {

	private static final int BLACKJACK = 21;


	
	// player has a name
	private String name;
	private int handVal;
	private boolean isFinished;
	// player has a hand of cards
	
	protected ArrayList<Card> hand = new ArrayList<Card>();
	
	protected abstract int defaultHandToBeat();
	
	


	/**
	 * constructor with name 
	 */
	public Player(String name){
		this.name = name;
	
	}
	
	public Player(){
		
	}
	
	public int cardCount(){
		return hand.size();
		
		
	}
	

		
	/** checks if the hand is above the BLACKJACK score.
	 * uses getBestHandVal to determine score 
	 * 
	 * @return true is hand is above BLACKJACK constant
	 */
	public boolean isBust(){
		
		boolean isBust = (getBestHandVal() > BLACKJACK);
	
		return isBust;
		
	}
		
	
	
	
	/** adds a card object to the arraylist hand.
	 *  also adds the value of that card to the class handVal variable 
	 * @param c of type Card
	 */
	public void addToHand(Card c){
		
		hand.add(c);
		addToHandVal(c);
		
	}
	
	
	
	/** getter for hand arraylist
	 * @return arraylist of Card
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	
	
	
	/** add each card to the hand total.
	 * in this case, all aces are set to 'high' (value of 11)
	 * 
	 * @param c of type card
	 */
	private void addToHandVal(Card c){
		
		handVal += c.rankToValue(true); // ace high
		
		
	}
		
		
	

	
	
	/**return a string that represents each card in the hand and the best score that they represent 
	 * 
	 * @return string that represents hand
	 */
	public String handToString(){
		StringBuilder str = new StringBuilder();
		for(Card c: hand){
		  str.append("|" + c.toString() + "|");
		}
		str.append("Score " + getBestHandVal());
		return str.toString();
		
	}
	
	/**
	 * find the number of aces currently in the hand by examining each card object
	 * 
	 * @return int - the number of aces in the hand
	 */
	private int getNumAces(){
		int numAces=0;
		for(Card c: hand){
		   if (c.isAce()){
			   numAces++;
			   }
		}
		
		return numAces;
	}
	
	/**
	 * gets the best possible hand when hand contains aces
	 * all aces are set to high by default. This could lead to being bust
	 * this method sets each ace in the hand to low (ie takes 10 from the score) until the score is closest to BLACKJACK
	 *  when it is closest to blackjack, the for while loop will exit and return the best score
	 * 
	 * @return int besthandval - the best possible score for the current hand
	 */
	public int getBestHandVal(){
		int bestHandVal = this.handVal;
		int numAces = getNumAces();
		while(numAces > 0 && bestHandVal > BLACKJACK){
			bestHandVal -=10;
			numAces --;
		
		}
		return bestHandVal;
			
		
		
	}

	
	

	public String getName() {
		return name;
	}




	public boolean isFinished() {
		return isFinished;
	}




	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}



	
	
	
	
	
}
