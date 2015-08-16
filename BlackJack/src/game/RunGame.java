package game;

import java.io.StringReader;


import thePlayers.ComputerPlayer;
import thePlayers.DealerPlayer;
import thePlayers.HumanPlayer;
import thePlayers.Player;
import thePlayers.GameControl;
import resources.Deck;

public class RunGame {

	static final int MAXPLAYERS = 3;
	
	
	public static void main(String[] args){
		
		// set up variables
		TextGUI textGUI = new TextGUI();
		GameControl gameControl = new GameControl();
		Deck deck = new Deck();
		
		// start game
		
		textGUI.showStartState();
		gameControl.setupPlayers(textGUI, MAXPLAYERS);
		deck.shuffleDeck();
		
		
		// play game, all players play first, try to get blackjack
		
		while(!(gameControl.allPlayersFinished())){
			gameControl.takeTurn(textGUI, deck);
		
								
		}
		

		// show who has beaten the dealer (can be 0 or more players)
		gameControl.showResult(textGUI);
		
		
		textGUI.showEndState();
	
	
	
	
	
	



		
	}
}
