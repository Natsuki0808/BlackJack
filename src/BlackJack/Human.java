package BlackJack;

import java.util.ArrayList;

 public abstract class Human {
	ArrayList<Tramp> hands = new ArrayList<Tramp>();
	
	
	void setHands() {
		
		
		for(int i = 0; i<2;i++) {
			
			Tramp hand = new Tramp();
			
			hand.dealCard();
			
			this.hands.add(hand);
			
		}
		
		
	}
	
	boolean checkHands() {
		boolean result = true;
		
		if(sumHands() > 21) {
			result = false;
		}
		
		return result;
	}
	
	int sumHands() {
		
		boolean aceFlag = false;
		int sum = 0;
		
		for(int i = 0; i < this.hands.size(); i++) {
			
			if(this.hands.get(i).getNumber() == 0) {
				
				aceFlag = true;
				
			}
			
			if(this.hands.get(i).getNumber() > 9) {
				
				sum = sum + 10;
				
			}else {
				
				sum = sum + this.hands.get(i).getNumber() + 1;
				
			}
			
			
		}
		
		if(aceFlag) {
			
			if(sum < 12) {
				sum += 10;
			}
			
		}
		
		return sum;
	}
	
	void clearHands() {
		
		hands.clear();
		
	}
	
	abstract void drawTramp() ;

}
