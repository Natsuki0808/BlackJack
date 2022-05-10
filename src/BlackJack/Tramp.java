package BlackJack;

import java.util.Random;

public class Tramp {
	final static int SPADE = 0;
	final static int HEART = 1;
	final static int DIAMOND = 2;
	final static int CLUB = 3;
	
	private int suit;
	private int number;
	
	
	
	public void dealCard() {
		
		Random rand = new Random();
		
		
		int randomSuit;
		int randomNumber;
		
		boolean flag = true;
		
		while(flag) {
			randomSuit = rand.nextInt(4);
			randomNumber =  rand.nextInt(13);
			if(Game.Tramps[randomSuit][randomNumber]) {
				
				Game.Tramps[randomSuit][randomNumber] = false;
				
				this.setSuit(randomSuit);
				this.setNumber(randomNumber);
				
				flag = false;
			}
			
		}
		
	}
	
	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
