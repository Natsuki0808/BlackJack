package BlackJack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	static boolean[][] Tramps = new boolean[4][13];
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {;
		
		
		boolean hitFlag = true;
		boolean gameFlag = true;
		
		int bet = 0;
		
	
		System.out.println("ようこそ！\nここはブラックジャックのテーブルです。\nまずはこのゲームで使うチップをお配りします。\n");
		
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		player.setChip(100);
		
		
		labelA:
		while(gameFlag) {
			
			
			initializeTramps();
			player.clearHands();
			dealer.clearHands();
			hitFlag = true;
			bet = 0;
			
			displayChip(player);
			
			bet = betChip(player.getChip());
			
			
			
			System.out.println("ハンドをお配りします。\n");
			
			player.setHands();
			dealer.setHands();
			
			displayHands(player,dealer,hitFlag);
			
			while(hitFlag) {
			
				System.out.println("Hitしますか？Standしますか？(h/s)");
				
				String play = scanner.next();
				
				if(play.equals("h")) {
					System.out.println("カードを一枚ドローします。");
					
					player.drawTramp();
					
					displayHands(player,dealer,hitFlag);
					
					if(player.checkHands() == false) {
						
						System.out.println("プレイヤーのハンドの合計が21を超えたためバーストしました。\nあなたの負けです");
						
						player.addChip(0 - bet);
						
						displayChip(player);
						
						gameFlag = checkEnd(player.getChip());
						
						continue labelA;
						
					}
					
				}else if(play.equals("s")) {
					
					hitFlag = false;
					
				}
			}
			
			displayHands(player,dealer,hitFlag);
			
			dealer.drawTramp();
			
			displayHands(player,dealer,hitFlag);
			
			if(dealer.checkHands() == false) {
				
				System.out.println("ディーラーのハンドの合計が21を超えたためバーストしました。\nあなたの勝ちです");
				
				player.addChip(bet);
				
				displayChip(player);
				
				gameFlag = checkEnd(player.getChip());
				
				continue;
				
			}
			
			System.out.println("プレイヤーのハンドの合計は"+ player.sumHands() +"です。\nディーラーのハンドの合計は"+ dealer.sumHands() +"です。");
			
			if(player.sumHands() > dealer.sumHands()) {
				
				System.out.println("あなたの勝ちです");
				
				player.addChip(bet);
				
				displayChip(player);
				
				gameFlag = checkEnd(player.getChip());
				
			}else if(player.sumHands() < dealer.sumHands()) {
				
				System.out.println("あなたの負けです");
				
				player.addChip(0 - bet);
				
				displayChip(player);
				
				gameFlag = checkEnd(player.getChip());
				
			}else {
				
				System.out.println("プレイヤーのハンドの枚数は"+ player.hands.size() +"です。\nディーラーのハンドの枚数は"+ dealer.hands.size() +"です。");
				
				if(player.hands.size() > dealer.hands.size()) {
					
					System.out.println("あなたの勝ちです");
					
					player.addChip(bet);
					
					displayChip(player);
					
					gameFlag = checkEnd(player.getChip());
					
				}else if(player.hands.size() < dealer.hands.size()) {
					
					System.out.println("あなたの負けです");
					
					player.addChip(0 - bet);
					
					displayChip(player);
					
					gameFlag = checkEnd(player.getChip());
					
				}else {
					
					System.out.println("引き分けです。");
					
					displayChip(player);
					
					gameFlag = checkEnd(player.getChip());
					
				}
				
			}
		}
	
	}
	
	public static void initializeTramps() {
		
		for(int i = 0; i < Tramps.length; i++) {
			for(int j = 0; j < Tramps[i].length; j++) {
				
				Tramps[i][j] = true;
				
			}
			
		}
	}
	
	public static String handsChangeString(ArrayList<Tramp> hands) {
		
		String handsString = "";
		
		
		for(int i = 0; i < hands.size(); i++) {
			
			handsString = handsString + cardChangeString(hands.get(i));
			
			if( i != (hands.size() - 1)) {
				handsString = handsString + ",";
			}
			
		}
		
		
		return handsString;
		
	}
	
	public static String cardChangeString(Tramp card) {
		
		String suit ="";
		
		switch (card.getSuit()) {
			case Tramp.SPADE:
				
				suit = "♠";
			
				break;
				
			case Tramp.HEART:
			
				suit = "♥";
				
				break;
				
			case Tramp.DIAMOND:
	
				suit = "♦";
				
				break;
	
			case Tramp.CLUB:
				
				suit = "♣";
	
				break;

		
		}
		
		String number = "" + (card.getNumber() + 1);
		
		String stringCard = suit + number;
		
		return stringCard;
		
	}
	
	public static void displayHands(Player player, Dealer dealer, boolean flag) {
		
		System.out.println("------------------------------------------------------\n");
		System.out.println("プレイヤー：" + handsChangeString(player.hands));
		
		if(flag) {
			System.out.println("ディーラー：" + cardChangeString(dealer.hands.get(0)) + ",?\n");
			
		}else {
			System.out.println("ディーラー：" + handsChangeString(dealer.hands) +"\n");
		}
		
		System.out.println("------------------------------------------------------");
		
	}
	
	public static int betChip(int chip) {
		
		int bet = 0;
		
		
		do {
		System.out.println("いくら賭けますか？");
		
		bet = scanner.nextInt(); 
		
		}while(chip < bet);
		
		return bet;
		
	}
	
	public static void displayChip(Player player) {
		System.out.println("------------------------------------------------------");
		System.out.println("現在のチップ：" + player.getChip());
		System.out.println("------------------------------------------------------");
		
		
	}
	
	public static boolean checkEnd(int chip) {
		
		if(chip > 0) {
		
			System.out.println("ゲームを終了しますか？(y/n)");
			
			String game = scanner.next();
			
			if(game.equals("y")) {
				
				return false;
				
			}else if(game.equals("n")){
				
				return true;
				
			}else {
				
				return true;
				
			}
			
		}else {
			
			System.out.println("チップがなくなりました。\nこれ以上の遊戯はできません。");
			
			return false;
			
		}
		
	}
}
