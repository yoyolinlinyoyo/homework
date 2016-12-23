import java.util.*;
public class Player extends Person {
 private String name;
 private int chips;
 private int bet=0;
 private ArrayList<Card> oneRoundCard=new ArrayList();
 
 public Player(String name,int chips){
 this.name=name;
 this.chips=chips;
 }
 public String get_name(){
	 return name;
 }
 public int make_bet(){
	 if(bet>chips){
		 System.out.print("You don't have enough money to use");
		 return bet;
	 }
	 else
		 bet=1;
	 return bet;
 }
 
 
 public boolean hit_me(Table table){
	 if(getTotalValue()<17){
		 return true;
	 }
	 else
		 return false;
 }
 
 public int get_current_chips(){
	 return chips;
 }
 public void increase_chips(int change){
	 chips+=change;
 }
 public void say_hello(){
	 System.out.println("Hello, I am " + name + "."); 
	 System.out.println("I have"+chips+"chips");
 }
}