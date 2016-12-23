import java.util.*;

public class Table {
	static final int MAXPLAYER = 4;
	private Deck all;
	private Player[] pl;
	private Dealer D;
	private int[] pos_betArray;
	private Card twice;

	public Table(int nDeck) {
		all = new Deck(nDeck);
		pl = new Player[MAXPLAYER];
	}

	public void set_player(int pos, Player p) {
		pl[pos] = p;
	}

	public Player[] get_player() {
		return pl;
	}

	public void set_dealer(Dealer d) {
		D = d;
	}

	public Card get_face_up_card_of_dealer() {
		return twice;
	}

	private void ask_each_player_about_bets() {
		pos_betArray = new int[pl.length];
		for (int i = 0; i < pl.length; i++) {
			pl[i].say_hello();
			pos_betArray[i] = pl[i].make_bet();
		}

	}

	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < pl.length; i++) {
			pl[i].setOneRoundCard(all.getOneCard(true));
			pl[i].setOneRoundCard(all.getOneCard(true));
		}
		D.setOneRoundCard(all.getOneCard(false));
		D.setOneRoundCard(all.getOneCard(true));
		twice = D.getOneRoundCard().get(1);
		System.out.println("Dealer's face up card is ");
		twice.printCard();

	}
	private void ask_each_player_about_hits(){
		for (Player n : pl)
		{
			System.out.println(n.get_name()+"'s Cards now:");
			n.printAllCard();
			while(n.hit_me(this)){
				n.setOneRoundCard(all.getOneCard(true));
				System.out.println("Hit! "+n.get_name()+"'s Cards now:");
				n.printAllCard();
			}
			System.out.println("Pass hit!");
			System.out.println(n.get_name()+"'s hit is over!");
		}
	}
	private void ask_dealer_about_hits(){
		while(D.hit_me(this)){
			D.setOneRoundCard(all.getOneCard(true));
		}
		System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips(){
		System.out.println("Dealer's card value is "+D.getTotalValue()+" ,Cards:");
		D.printAllCard();
		int dealercard = D.getTotalValue();
		for(int i=0;i<pl.length;i++){
			int playercard = pl[i].getTotalValue();
			int playerbet = pos_betArray[i];
			System.out.println(pl[i].get_name()+"'s Cards: ");
			pl[i].printAllCard();
			System.out.print(pl[i].get_name()+" card value is "+pl[i].getTotalValue());
			if(playercard<dealercard&&dealercard<=21){//player lose
				pl[i].increase_chips(-playerbet);
				System.out.println(", Loss "+playerbet+"Chips, the Chips now is: "+ pl[i].get_current_chips());
			}
			else if(playercard>21&&dealercard>21){//draw
				System.out.println(", chips have no change! The Chips now is: "+pl[i].get_current_chips());
			}
			else if(playercard>dealercard&&playercard<=21){//player win
				pl[i].increase_chips(+playerbet);
				System.out.println(", Loss "+playerbet+"Chips, the Chips now is: "+ pl[i].get_current_chips());
			}
			else if(playercard==dealercard){//draw
				System.out.println(", chips have no change! The Chips now is: "+pl[i].get_current_chips());
			}
			else if(playercard>21){//player lose
				pl[i].increase_chips(-playerbet);
				System.out.println(", Loss "+playerbet+"Chips, the Chips now is: "+ pl[i].get_current_chips());
			}
			else if(dealercard>21){//player win
				pl[i].increase_chips(+playerbet);
				System.out.println(", Loss "+playerbet+"Chips, the Chips now is: "+ pl[i].get_current_chips());
			}
		}
	}
	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();//run the method
		distribute_cards_to_dealer_and_players();		
		ask_each_player_about_hits();					
		ask_dealer_about_hits();								
		calculate_chips();										
	}

}
