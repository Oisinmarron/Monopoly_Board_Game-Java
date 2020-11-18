package monopolyTheGame;

import java.awt.Color;

public class Railroads extends PurchasableProperty{
	public int rent;
	
	public Railroads(int location, String propName, int mortgage, int purchaseCost) {
		super(location, propName, mortgage, purchaseCost);
		propType = 2;
	}
	
	
	//Rent calculator
	public int getRent (int numOwned) {
		//numOwned refers to the number of railroads the owner has
		switch (numOwned){
		case 1:
			rent = 25;
        	break;
        case 2:
        	rent = 50;
        	break;
        case 3:
        	rent = 100;
        	break;
        case 4:
        	rent = 200;
        	break;
        default:
        	System.out.println("No rent due");
        	break;
		}
	      return rent;
	}
	
	public int getRent() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setIfPlayerOwnsNeighborhood() {
		// TODO Auto-generated method stub
		
	}


}
