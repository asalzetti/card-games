
/*
 * A playing card
 */
public class Card {
	private Suit suit;
	private Rank rank;
	
	/*
	 * Creates a card of a certain suit and rank
	 */
	public Card(Suit s, Rank r){
		suit = s;
		rank = r;
	}

	/*
	 * Creates a card of a certain suit and rank given their numerical values
	 */
	public Card(int s, int r){
		switch(s){
		case 0: suit = Suit.HEART; break;
		case 1: suit = Suit.DIAMOND; break;
		case 2: suit = Suit.CLUB; break;
		case 3: suit = Suit.SPADE; break;
		}
		
		switch(r){
		case 0: rank = Rank.ACE; break;
		case 1: rank = Rank.TWO; break;
		case 2: rank = Rank.THREE; break;
		case 3: rank = Rank.FOUR; break;
		case 4: rank = Rank.FIVE; break;
		case 5: rank = Rank.SIX; break;
		case 6: rank = Rank.SEVEN; break;
		case 7: rank = Rank.EIGHT; break;
		case 8: rank = Rank.NINE; break;
		case 9: rank = Rank.TEN; break;
		case 10: rank = Rank.JACK; break;
		case 11: rank = Rank.QUEEN; break;
		case 12: rank = Rank.KING; break;
		}
	}
	
	/*
	 * Returns the card's suit
	 */
	public Suit getSuit(){
		return suit;
	}
	
	/*
	 * Returns the card's rank
	 */
	public Rank getRank(){
		return rank;
	}
	
}
