package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import thePlayers.Player;


public class TextGUI implements ITextInput, ITextOutput {

	
    private Scanner scn;
	private boolean quit;
	
	
	public TextGUI() {
		// initialise the scanner
		resetScanner();
	
		
	}

	private void resetScanner(){
		scn = new Scanner(System.in);
		
	}
	
		
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	
	
	@Override
	public void showStartState() {
	
		System.out.println("******* Welcom to Blackjack *********");
		System.out.println("\n");
	    System.out.println("**************************");
		
		
	}

	
	public void getAnyKey(){
		resetScanner();
		System.out.println("Press any key");
		scn.nextLine();
		
		
	}
	


	@Override
	public void showEndState() {
		// TODO Auto-generated method stub
		System.out.println("******* Thank you for playing *********");
	
	}
	
	
	
	@Override
    public int getNumPlayers(int minPlayers, int maxPlayers){
		int selection = 0;
	   	resetScanner();
		System.out.println("Please enter the numer of human players (0-4)");
		do{
		selection = scn.nextInt();
		} while(selection < minPlayers || selection > maxPlayers) ;
		
		return selection;  
			  
	      
		}
	    
				
	
	
	
    @Override
	public String getPlayerName(){
		String selection;
		resetScanner();
		System.out.println("Please enter Player name");
		selection = scn.nextLine();
        return selection;  
			
		
	}



	@Override
	public boolean getTwist() {
		// TODO Auto-generated method stub
		resetScanner();
		
		   /// set initial values
	    String returnStr = "";
	    boolean response = false;
	    
	    System.out.println("******* Another card ??*********");
	    do {
	
	    	returnStr =  scn.next(); // read scanner input into string
	    	
	    	if (returnStr.equalsIgnoreCase("y")){
	    		response = true;
	    		break;
	    			    		
	    	} else {
	    		response = false;
	    		break;
		   	}
	    	
	    	
		  } while ( !returnStr.equalsIgnoreCase("y") || !returnStr.equalsIgnoreCase("n"));
	    return response;
		
		
	}

	public void showPlayerStatus(Player p){
		StringBuilder str = new StringBuilder();
		if(p.isBust()){
			
			str.append(" Player " + p.getName()  +  "is BUST with a score of " + p.getBestHandVal());
			
		}else if (p.isFinished()){
			
			str.append(" Player " + p.getName() + " has finished with a score of " + p.getBestHandVal());
		}
			
			
		System.out.println(str.toString());
	}
	
	@Override
	public void showWinner(Player p) {
		// TODO Auto-generated method stub
		System.out.println(p.getName() + " is a WINNER ! "  + " with " + p.getBestHandVal());
	}

	@Override
	public void showLoser(Player p) {
		// TODO Auto-generated method stub
		System.out.println(p.getName() +  " has lost "  + " with " + p.getBestHandVal());
		
	}

	@Override
	public void showMsg(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

	public void addToLine(String string) {
		// TODO Auto-generated method stub
		System.out.print(string);
	}
	
	
	

	
}
