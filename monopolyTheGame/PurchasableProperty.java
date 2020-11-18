package monopolyTheGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class PurchasableProperty extends Property{
	
	protected int ownerNum;
	protected int mortgage;
	protected int purchaseCost;
	protected String name;
	private Scanner input = new Scanner(System.in);
	protected boolean isMortgaged;
	
	//Inheritance of parent
	public PurchasableProperty(int location, String propName, int mortgage, int purchaseCost) {
		super (location);
		this.mortgage = mortgage;
		this.purchaseCost = purchaseCost;
		name = propName;
		ownerNum = -1;
		this.isMortgaged = false;
	}
	
	/*Return the rent given number of properties owned. IE: Orange will need all 3 to upgrade
	* using houses/hotels. Railroads can use this to return the rental price given number of RR's owned.
	*/
	public abstract int getRent();
	public abstract void setIfPlayerOwnsNeighborhood();
	
	//Methods for get/set property attributes	
	public int getOwner() {
		return ownerNum;
	}
	
	public void resetOwner() {
		ownerNum = -1;
	}
	
	public int getMortgage() {
		return mortgage;
	}
	
	public void mortgage(Player player) {
		this.isMortgaged = true;
		player.addCapital(getMortgage());
		System.out.println("You have mortgaged " + name + " for $" + mortgage);
	}
	
	public int getPurchaseCost() {
		return purchaseCost;
	}
	
	public String getPropName() {
		return name;
	}
	
	//Set owner
	public void setOwner(Player player, ArrayList <Player> allPlayers) {
		String response;
		int wrongScan = 0;
		
		//Message and instruction display
		System.out.println("\n\n_________________________________");
		System.out.println(name +
				"\nPurchase Cost: " + purchaseCost +
				"\nMortgage: " + mortgage + 
				"\nRent: " + getRent());
		System.out.println("No one owns this piece of land. Do you wish to purchase? Y/N");

		while(wrongScan == 0) {		
			
			response = input.next();
			
			if(response.equals("Y")) {
				
				if (player.getCapital() >= purchaseCost){		// Method int getCapital() in Player class
					ownerNum = player.getPlayerNum();	// Method getPlayerNum() in Player class returns player name
					System.out.println("You now own " + name);
					player.reduceCapital(purchaseCost);
					System.out.println("Your new balance is $" + player.getCapital());
				}
				else {
					System.out.println("Player does not have enough capital");
					System.out.println("You do not have enough money. \nBank will now open auction to other players\n");
					auction(allPlayers);
				}
				wrongScan = 1;
			}
			
			else if (response.equals("N")){
				System.out.println("Bank will now open auction to other players");
				auction(allPlayers);
				wrongScan = 1;
			}
			
			else{
				System.out.println("You did not enter Y/N, try again\n");
			}
		}
	}
			
		
	public void auction(ArrayList <Player> allPlayers) {
		int counter, highestBid = 0, enteredNum, wrongScan, highestBidder = 7;
		String response;

		
		for(counter = 0; counter < allPlayers.size(); counter++) {
			System.out.println(allPlayers.get(counter).getName() + " would you like to bid greater than "
					+ highestBid + "? ----- Y/N");
			
			wrongScan = 0;
			while(wrongScan == 0) {
	            response = input.next();
	            
				if(response.equals("Y")) {
					System.out.println("You have $" + allPlayers.get(counter).getCapital() + " in your account.");
					System.out.println("What would you like to bid?");
					
					enteredNum = input.nextInt();
					
					//Check bid validity
					while (enteredNum <= highestBid) {
						System.out.println("That bid is not greater than $" + highestBid + "\nBid again");
						enteredNum = input.nextInt();
					}
					//Check highest bid
					if(allPlayers.get(counter).getCapital() >= enteredNum) {
						highestBid = enteredNum;
						highestBidder = counter;
						System.out.println(allPlayers.get(counter).getName() + " you are now the highest bidder \n");
						wrongScan = 1;
					}
					else {
						System.out.println("Player does not have enough capital");
						wrongScan = 1;
					}
				}
				else if(response.equals("N")){
					wrongScan = 1;
				}
				else {
					System.out.println("You did not enter Y/N, try again");
				}
				
			}
			
			wrongScan = 0;
			
			if (counter == allPlayers.size() - 1) {
				while(wrongScan == 0) {
					
					System.out.println("Does anyone want to bid again? Y/N");
					response = input.next();
					
					if(response.equals("Y")) {
						counter = 0;
						wrongScan = 1;
					}
					else if(response.equals("N") && highestBidder != 7) {
						allPlayers.get(highestBidder).reduceCapital(highestBid); 
						System.out.println(allPlayers.get(counter).getName() + " you won the bid.\n");
						
						ownerNum = highestBidder;	// Method getPlayerNum() in Player class returns player name
						System.out.println("You now own " + name);
						allPlayers.get(ownerNum).reduceCapital(highestBid);
						System.out.println("Your new balance is $" + allPlayers.get(ownerNum).getCapital());
						wrongScan = 1;
					}
					else if((response.equals("N")) && (highestBidder == 7)){
						System.out.println("Property must be sold a to highest bidder.");
						counter = 0;
						wrongScan = 1;
					}
					else {
						System.out.println("You did not enter Y/N, try again\n");
					}
				}
			}
		}
	}
}


