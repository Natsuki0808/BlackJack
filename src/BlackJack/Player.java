package BlackJack;

public class Player extends Human{
	
	private int chip;

	@Override
	void drawTramp() {
		// TODO 自動生成されたメソッド・スタブ
		
		Tramp hand = new Tramp();
		
		hand.dealCard();
		
		this.hands.add(hand);
		
	}

	public int getChip() {
		return chip;
	}

	public void setChip(int chip) {
		this.chip = chip;
	}
	
	public void addChip(int bet) {
		this.chip = chip + bet;
	}

}
