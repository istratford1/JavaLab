package thePlayers;

import resources.Card;

public class DealerPlayer extends Player{

	
	private boolean hideFirstCard = true;
	
	public DealerPlayer(String name) {
		super(name);

		
	}


	
	@Override
	protected int defaultHandToBeat() {
		// TODO Auto-generated method stub
		
		
		return 18;
				
				
	}
	
	
	
	int firstCardVal(){
		return this.hand.get(0).rankToValue(true);
		
	}

	/**return a string that represents each card in the hand and the best score that they represent 
	 * 
	 * @return string that represents hand
	 */
	

	public String handToString(){
		StringBuilder str = new StringBuilder();
		
		if(hideFirstCard && this.cardCount() == 2){
			// only show the first card
			str.append(this.hand.get(0).toString());
			str.append(" | ??????");	
		} else {

			for(Card c: this.hand){
				str.append(c.toString() + " | ");
			}
			str.append("Score " + getBestHandVal());

		}

		
		return str.toString();
		
	}



	public boolean isHideFirstCard() {
		return hideFirstCard;
	}



	public void setHideFirstCard(boolean hideFirstCard) {
		this.hideFirstCard = hideFirstCard;
	}

	
}
