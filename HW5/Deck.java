import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> usedCard = new ArrayList<Card>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Card> openCard=new ArrayList<Card>(); 
	
	public int nUsed;

	// TODO: Please implement the constructor
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		// 1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		// Hint: Use new Card(x,y) and 3 for loops to add card into deck
		// Sample code start
		// Card card=new Card(1,1); ->means new card as clubs ace
		// cards.add(card);
		// Sample code end
		for (int i = 1; i <= nDeck; i++)
			for (Card.Suit f : Card.Suit.values())
				for (int n = 1; n <= 13; n++) {
					Card card = new Card(f, n);
					cards.add(card);
				}
		shuffle();

	}

	// TODO: Please implement the method to print all cards on screen
	public void printDeck() {
		// Hint: print all items in ArrayList<Card> cards,
		// please implement and reuse printCard method in Card class
		for (Card m : cards)
			m.printCard();

	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}

	public void shuffle() {
		Random x = new Random();
		int pick1, pick2;
		for (int i = 0; i < 52; i++) {// 洗牌52次
			pick1 = x.nextInt(51);
			pick2 = x.nextInt(50);
			Card scratch = cards.get(pick1);// 暫存抽出的牌
			cards.remove(pick1);// 隨機抽出一張牌
			cards.add(pick2, scratch);// 從剩下的51張牌中找一個位置將抽出的牌放進去
		}
		usedCard.clear();
		nUsed = 0;
		openCard.clear();
	}


	public Card getOneCard(boolean isOpened)     
	{
	int n =usedCard.size();
	
	if (n <= 51) {
		usedCard.add(cards.get(n));
		if(isOpened) openCard.add(cards.get(n));
		nUsed++;
		if (nUsed == 52) 
			shuffle();
		}
	
	return cards.get(n);
	}
	public ArrayList<Card> getOpenedCard(){
		return openCard;
	}
}