import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/*
 * A game of Blackjack
 */
public class Blackjack {
	private DeckOfCards deck;
	private int nextCard;
	private LinkedList<LinkedList<Card>> playerCards;
	private int numbPlayers;
	
	/*
	 * Create a game of Blackjack with a certain number of players
	 */
	public Blackjack(int numPlayers){
		numbPlayers = numPlayers;
		
		deck = new DeckOfCards();
		deck.shuffle();
		
		nextCard = 0;
		playerCards = new LinkedList<LinkedList<Card>>();
		for(int i = 0; i < numbPlayers; i++){
			LinkedList<Card> player = new LinkedList<Card>();
			player.add(deck.getCard(nextCard));
			nextCard++;
			player.add(deck.getCard(nextCard));
			nextCard++;
			playerCards.add(player);
		}
	}
	
	/*
	 * Starts the Blackjack game and lets the players play
	 */
	public int[][] play(){
		Scanner in = new Scanner(System.in);
		
		LinkedList<LinkedList<Card>> finalCards = new LinkedList<LinkedList<Card>>();
		int[][] finalScores = new int[numbPlayers][2];
		
		ListIterator<LinkedList<Card>> it = playerCards.listIterator();
		for(int i = 0; i < numbPlayers; i++){
			System.out.println("Player " + (i + 1) + "'s turn:");
			LinkedList<Card> player = it.next();
			
			BlackjackOption choice = BlackjackOption.HIT;
			do {
				ListIterator<Card> itCard = player.listIterator();
				System.out.print("You have ");
				Card card;
				for(int j = 0; j < player.size() - 1; j++){
					card = itCard.next();
					System.out.print("a " + card.getRank() + " of " + card.getSuit() + "S, ");
				}
				card = itCard.next();
				System.out.println("and a " + card.getRank() + " of " + card.getSuit() + "S.");
				
				int score = score(player);
				System.out.println("Your score is " + score + ".");
				if(score > 21){
					System.out.println("You went over 21. You lose.\n");
					break;
				}
				else if(score == 21){
					System.out.println("You got 21. Congrats.\n");
					break;
				}
				
				System.out.println("What would you like to do?");
				System.out.print("Select 1 to Hit, 2 to Stand: ");
				int option = in.nextInt();
				System.out.print("\n");
				
				if(option == 1){
					choice = BlackjackOption.HIT;
					
					nextCard++;
					if(nextCard == deck.getNumbCards()){
						deck.shuffle();
						nextCard = 0;
					}
					
					player.add(deck.getCard(nextCard));
				}
				else {
					choice = BlackjackOption.STAND;
					
					finalCards.add(player);
				}
			}while(choice != BlackjackOption.STAND);
			
			finalScores[i][0] = score(player);
			finalScores[i][1] = player.size();
		}
		
		in.close();
		
		playerCards = finalCards;
		return finalScores;
	}

	/*
	 * Returns the score of a hand of cards
	 */
	public int score(LinkedList<Card> cards){
		int total = 0;
		boolean ace = false;
		
		ListIterator<Card> it = cards.listIterator();
		while(it.hasNext()){
			Card card = it.next();
			Rank rank = card.getRank();
			
			if(rank == Rank.TWO){
				total += 2;
			}
			else if(rank == Rank.THREE){
				total += 3;
			}
			else if(rank == Rank.FOUR){
				total += 4;
			}
			else if(rank == Rank.FIVE){
				total += 5;
			}
			else if(rank == Rank.SIX){
				total += 6;
			}
			else if(rank == Rank.SEVEN){
				total += 7;
			}
			else if(rank == Rank.EIGHT){
				total += 8;
			}
			else if(rank == Rank.NINE){
				total += 9;
			}
			else if(rank == Rank.TEN || rank == Rank.JACK || rank == Rank.QUEEN || rank == Rank.KING){
				total += 10;
			}
			else {
				total += 11;
				ace = true;
			}
		}
		
		if(ace && total > 21){
			it = cards.listIterator();
			while(it.hasNext()){
				Card card = it.next();
				Rank rank = card.getRank();
				
				if(rank == Rank.ACE){
					total -= 10;
					if(total <= 21){
						break;
					}
				}
			}
		}
		
		return total;
	}
	
	/*
	 * Determines who won the game of Blackjack
	 */
	public void results(int[][] scores){
		LinkedList<Integer> winners = new LinkedList<Integer>();
		
		boolean twentyOne = false;
		int maxScore = 0;
		for(int i = 0; i < numbPlayers; i++){
			if(scores[i][0] <= 21){
				if(maxScore < scores[i][0]){
					maxScore = scores[i][0];
					winners.clear();
					winners.add(i);
					if(maxScore == 21 && scores[i][1] == 2){
						twentyOne = true;
					}
				}
				else if(maxScore == scores[i][0]){
					if(maxScore == 21){
						if(twentyOne){
							if(scores[i][1] == 2){
								winners.add(i);
							}
						}
						else {
							if(scores[i][1] == 2){
								winners.clear();
								winners.add(i);
								twentyOne = true;
							}
							else {
								winners.add(i);
							}
						}
					}
					else {
						winners.add(i);
					}
				}
			}
		}
		
		System.out.println("Winners: ");
		if(winners.isEmpty()){
			System.out.println("No One Won");
		}
		else {
			ListIterator<Integer> itWinners = winners.listIterator();
			while(itWinners.hasNext()){
				System.out.println("Player " + (itWinners.next() + 1));
			}
		}
	}
	
	/*
	 * Resets the Blackjack game and deals new cards to the players
	 */
	public void reset(){
		deck.shuffle();
		
		nextCard = 0;
		playerCards = new LinkedList<LinkedList<Card>>();
		for(int i = 0; i < numbPlayers; i++){
			LinkedList<Card> player = new LinkedList<Card>();
			player.add(deck.getCard(nextCard));
			nextCard++;
			player.add(deck.getCard(nextCard));
			nextCard++;
			playerCards.add(player);
		}
	}
}
