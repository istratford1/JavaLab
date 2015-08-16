package resources;

public class Card {

	private Suit suit;
	private Rank rank;
	
	
	public Rank getRank() {
		return this.rank;
	}

	
	public boolean isAce(){
		return (rank.name().equals("ACE"));
				
	}
	
	
	public String toString(){
		return rank.name().toLowerCase() + " of " + suit.name().toLowerCase();
		
	}
	
	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}
	
    public int rankToValue(Boolean aceHigh){
		
		int cardVal = 0;
		switch (this.rank){
		case ACE :
			cardVal = (aceHigh) ? 11 : 1;
			break;
		
		case JACK : case QUEEN : case KING :
			cardVal = 10;
			break;
				
			
		default : 
			cardVal = rank.ordinal() + 1;
			
		}
		
		return cardVal;
		
		
	}
	
	
	
}
