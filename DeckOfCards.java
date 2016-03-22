import java.util.Arrays;
import java.util.Collections;

/*
 * A deck of playing cards
 */
public class DeckOfCards {
	private Card[] deck;
	private int numbCards;
	
	/*
	 * Creates a deck of 52 playing cards
	 */
	public DeckOfCards(){
		numbCards = 52;
		deck = new Card[numbCards];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 13; j++){
				deck[13*i + j] = new Card(i, j);
			}
		}
	}
	
	/*
	 * Shuffles the deck of cards
	 */
	public void shuffle(){
		for(int i = 0; i < 3; i++)
			Collections.shuffle(Arrays.asList(deck));
	}

	/*
	 * Prints out the deck of cards in order
	 */
	public void print(){
		for(int i = 0; i < numbCards; i++){
			Card c = deck[i];
			System.out.println(c.getSuit() + " : " + c.getRank());
		}
	}
	
	/*
	 * Returns the i-th card in the deck
	 */
	public Card getCard(int index){
		if(index >= 0 && index < 52){
			return deck[index];
		}
		return new Card(-1, -1);
	}

	/*
	 * Returns the number of cards in the deck
	 */
	public int getNumbCards(){
		return numbCards;
	}
	
}
