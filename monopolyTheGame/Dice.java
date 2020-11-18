package monopolyTheGame;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	private int max;
	private int min;
	private int dice1;
	private int dice2;
	
	public Dice() {
		max = 6;
		min = 1;
	}
	
	public int rollDice(Player player) {
		dice1 = ThreadLocalRandom.current().nextInt(min, max+1);
		dice2 = ThreadLocalRandom.current().nextInt(min, max+1);
		int totalVal = dice1 + dice2;
		// Test: Dice1 = Dice2;
		if(dice1 == dice2) {
			System.out.println("You rolled a pair of doubles.");
			player.doubleCount();
		}
		else {
			player.doubleDiceCount = 0;
		}
		System.out.println("Value rolled: "+ totalVal);
		return totalVal;
	}

	public int rollDice() {
		dice1 = ThreadLocalRandom.current().nextInt(min, max+1);
		dice2 = ThreadLocalRandom.current().nextInt(min, max+1);
		int totalVal = dice1 + dice2;
	
		System.out.println("Value rolled: "+ totalVal);
		return totalVal;
	}
}
