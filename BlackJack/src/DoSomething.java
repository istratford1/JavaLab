import resources.Deck;


public class DoSomething {

	public static void main(String[] args){
		
		Deck d = new Deck();
		d.shuffleDeck();
		for(int c = 0; c < 52; c++){
			System.out.println(	d.dealCard().toString());
						
		
		}
		
		System.out.println("Left in the pack: " + d.getCardsLeft() );
	}
}
