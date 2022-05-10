package BlackJack;

public class Dealer extends Human{

	@Override
	void drawTramp() {
		// TODO 自動生成されたメソッド・スタブ
		
		while(sumHands() < 17) {
			
			Tramp hand = new Tramp();
			
			hand.dealCard();
			
			this.hands.add(hand);
			
		}
		
	}

}
