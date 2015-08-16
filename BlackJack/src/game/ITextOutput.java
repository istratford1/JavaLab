package game;

import thePlayers.Player;

public interface ITextOutput {

	
	public void showStartState();
		
		

	public void showWinner(Player p);
	
	public void showLoser(Player p);
	
	public void showMsg(String msg);
	
	public void showEndState();





	void showPlayerStatus(Player p);
	
	
	
	
}
