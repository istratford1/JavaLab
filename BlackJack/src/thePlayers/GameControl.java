package thePlayers;

import game.TextGUI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.IsInstanceOf;

import resources.Card;
import resources.Deck;

public class GameControl {

	
	private ArrayList<Player> playerArr = new ArrayList<Player>();
	private int playerNdx = 0;
	
	
	public GameControl(){
	
		
		
	}
	
	public void addPlayer(Player p){
		
		playerArr.add(p);
		
	}
	
	
	public Player getDealer(){
		return playerArr.get(0);
		
	}
	
	public ArrayList<Player> getPlayers(){
		ArrayList<Player> justPlayers = new ArrayList<Player>(); 
		for(Player p : playerArr){
			if(! (p instanceof DealerPlayer)){
				justPlayers.add(p);
				
			}
			
		}
		return justPlayers;
	}
	
	
	public Player nextPlayer(){
		
		incrementPlayer();
		return  currentPlayer();
		
		
		
	}
	
	private Player currentPlayer() {
	
		return  playerArr.get(playerNdx);
	}
	

	
	private void incrementPlayer(){
		int numPlayers = playerArr.size();
		playerNdx++;
		if(playerNdx >= numPlayers){
			playerNdx = 0;
			
		}
		
		
	}
	
      public void setupPlayers(TextGUI textGUI, int maxPlayers){
		
		int humanPlayers = textGUI.getNumPlayers(0,maxPlayers);
		int computerPlayers = maxPlayers - humanPlayers;
		
		this.addPlayer(new DealerPlayer("Dealer"));
		
		for(int p = 0; p < humanPlayers; p++){
			this.addPlayer(new HumanPlayer(textGUI.getPlayerName()));	
			
		}
	
		
		for(int c = 0; c < computerPlayers; c++ ){
			this.addPlayer(new ComputerPlayer("Computer player " + (c+1)));
			
		}
		
	}
	

	
	public boolean allPlayersFinished(){
		
		boolean allFinished = true;
		for(Player p: playerArr){
			if(!p.isFinished() ){
					allFinished = false;
					break;
				}
			
			
		}
		
		return allFinished;
		
	}

	
	public void pressAnyKey(TextGUI textGUI){
		
		textGUI.getAnyKey();
				
	}
	
	/** calculation that a player should twist based on the probablity of the dealer winning
	 * @param playerScore
	 * @param dealerCard (ace high)
	 * @return true if the player should twist based on the dealer card
	 */
	private boolean willTwist(int playerScore, int dealerCard){
	
	    boolean twist = false;
		switch (dealerCard){
		
		case 2 : case 3 : 
			// 0 % chance of bust dealer 
			twist = (playerScore >= 5 && playerScore <= 12 ) ? true : false;
			break;
		case 4: case 5: case 6:
			// 2.3 % chance of bust dealer
			twist = (playerScore >= 5 && playerScore <= 11 ) ? true : false;
			break;
		
		case 7: case 8: case 9: case 10: case 11:
			// 2.9 % chance of bust dealer
			twist = (playerScore >= 5 && playerScore <= 16 ) ? true : false;
			break;
		
		
		}
		
		return twist;
		
		
		
	}
	
	private void pause(int seconds){
		
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private int getAvePlayerScore(){
		int allPlayerScores = 0;
		int numActivePlayers = 0;
		
		for(Player p: playerArr){
			if( !(p instanceof DealerPlayer)){
				if(p.isFinished() || !p.isBust()) {
					allPlayerScores =+ p.getBestHandVal();
					numActivePlayers++;
				}
			}
			
		}
		return (int) allPlayerScores / numActivePlayers;
	}
	

	/**take player turn
	 * if cards are less than two, autoamtically add cards
	 * else, add cards if it is in the players best interests to do so
	 * 
	 * 
	 * @param textGUI
	 * @param deck
	 */
	public void takeTurn(TextGUI textGUI, Deck deck) {
		// TODO Auto-generated method stub
		boolean twist = false;
		Player n;
		
		if(currentPlayer().isFinished()){
			n = nextPlayer();
		} else{
			n = currentPlayer(); 
	    } 	
		
		textGUI.showPlayerStatus(n);
	
	
		if(n.cardCount() < 2){

			twist = true;
		} else{
			if(n instanceof HumanPlayer){
				twist = textGUI.getTwist();
			
			}else if(n instanceof DealerPlayer){
				
				pause(1);
				
				if(allPlayersFinished()){
					((DealerPlayer) n).setHideFirstCard(false);	
					twist = willTwist(n,getAvePlayerScore());
				}
			
			}else if(n instanceof ComputerPlayer){
				pause(1);
				
				twist = willTwist(n.getBestHandVal(), ((DealerPlayer) getDealer()).firstCardVal()); 
			
				
			}
		}
		
		if(twist){
			pause(1);
			
			Card c = deck.dealCard();
			n.addToHand(c);
			textGUI.addToLine(c.toString());
			
			
		} else {
		   n.setFinished(true);
		   
		}
		
		if(n.isBust()){
			n.setFinished(true);
		}
		
		
		textGUI.showPlayerStatus(n);
		
		
	}
	

	

	/**
	 * @param Player p 
	 * get the best total and determine if it advisable to accept another card
	 * the handToBeat gives a reference score that should be beaten (e.g. another player score, dealer)
	 * if there is no hand to be beaten, get a default value (will differ depending on player)
	 * 
	 * @return booelan - twist = true(accept card)  or twist = false (do no accept card) 
	 */
	public 	boolean willTwist(Player p, int handToBeat) {
				
		boolean twist = false;
		int myCurrentHand = p.getBestHandVal();
		handToBeat = (handToBeat==0) ? p.defaultHandToBeat() : handToBeat;
		
		if (myCurrentHand < handToBeat && ! p.isBust()){
			twist =  true;
			
		} else{
			
			twist =  false;
			
		}
		
		return twist;
	
	
	

	}


	public void showAllPlayerStatus(TextGUI textGUI) {
		// TODO Auto-generated method stub
	   for(Player p: playerArr){
		   if(p.cardCount()>=2){
		   textGUI.showPlayerStatus(p);
		   }
	   }
	  
	 
		
	}

	public void showResult(TextGUI textGUI) {
		// TODO Auto-generated method stub
			
			int beatTheDealer = 0;
			Player theDealer = getDealer();
			for(Player p : playerArr){
				if(!p.isBust() && (p.getBestHandVal() > theDealer.getBestHandVal() ||theDealer.isBust() )){
					textGUI.showWinner(p);
					beatTheDealer ++;					
				}else{
					textGUI.showLoser(p);
					
				}
				
				
			}
				
			
			if (beatTheDealer > 0){
				textGUI.showMsg(beatTheDealer + " people beat the dealer's score of " + theDealer.getBestHandVal());
				
			} else{
				textGUI.showMsg("Dealer wins all !");
			}	
				
				
			
			
		}



	



	
}

