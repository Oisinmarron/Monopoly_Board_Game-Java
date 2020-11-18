package monopolyTheGame;

import java.awt.Color;

public class Utilities extends PurchasableProperty {
	private int multiplier = 0;
	
	public Utilities(int location, String propName, int mortgage, int purchaseCost) {
		super(location, propName, mortgage, purchaseCost);
		propType = 2;
	}

	//Rent calculator
	public int getRent (int numOwned, Player player) {
		Dice die = new Dice();
		//numOwned refers to the number of utilities the owner has
		int dice = die.rollDice(player);
		switch (numOwned){
			case 1:
				multiplier = 4;
	        	break;
	        case 2:
	        	multiplier = 10;
	        	break;
	        default:
	        	System.out.println("No rent due");
	        	multiplier = 0;
	        	break;
	      }
		return multiplier*dice;
	}

	@Override
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIfPlayerOwnsNeighborhood() {
		// TODO Auto-generated method stub
		
	}

}
