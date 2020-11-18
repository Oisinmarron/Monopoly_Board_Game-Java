package monopolyTheGame;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;

public class BuildingLand extends PurchasableProperty{
	
	ArrayList <Player> playerArray = new ArrayList<Player>();
	public int buildingNum;
	public int[] rent;
	public int houseCost;
	public Color neighborhoodColor;
	public static int totalHomesAvailable;
	public static int totalHotelsAvailable;
	public int playerOwnsNeighborhood;
	
	
	public BuildingLand(int location, String propName, int mortgage, int purchaseCost, int rOne, 
			int rTwo, int rThree, int rFour, int rHotel, int houseCost, int rEmpty, Color colour) {
		super(location, propName, mortgage, purchaseCost);
		
		this.rent = new int[] {rEmpty, rOne, rTwo, rThree, rFour, rHotel};
		this.houseCost = houseCost;
		neighborhoodColor = colour;
		buildingNum = -1;
		propType = 1;
		playerOwnsNeighborhood = 0;
	}

	public String getPropertyInfo() {
		return "Property Name - " + getPropName() +  "\nOwner - " + getOwner() + 
				"\nPurchase Cost - " + getPurchaseCost() + "\nMortgage Cost - " 
				+ getMortgage() + "\nCost Per House - " + getPricePerHouse() + 
				"\nCurrent Rent Cost - " + getRent();
	}
	
	public Color getNeighborhoodColor() {
		return neighborhoodColor;
	}
	
	public void setIfPlayerOwnsNeighborhood() {
		playerOwnsNeighborhood = 1;
	}
	
	public void resetOwner() {
		ownerNum = -1;
		playerOwnsNeighborhood = 0;
	}
	
	// Set owner
	// Overwritten to account for neighborhood colors
	public void setOwner(Player player, ArrayList <Player> allPlayers) {
		Scanner input = new Scanner(System.in);
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
						
					ownerOwnsNeighborhood(player); // Lets property know if owner owns the neighborhood;
				}
				else {
					System.out.println("Player does not have enough capital");
					System.out.println("You do not have enough money. \nBank will now open auction to other players\n");
					auction(allPlayers);
					ownerOwnsNeighborhood(allPlayers.get(ownerNum));
				}
				wrongScan = 1;
			}
				
			else if (response.equals("N")){
				System.out.println("Bank will now open auction to other players");
				auction(allPlayers);
				wrongScan = 1;
				ownerOwnsNeighborhood(allPlayers.get(ownerNum));
			}
				
			else{
				System.out.println("You did not enter Y/N, try again\n");
			}
		}
	}
		
	
	public int getLocation() {
		return location;
	}
	
	public int getPricePerHouse() {
		return houseCost;
	}
	
	
	public int getBuildingNum() {
		return buildingNum;
	}
	
	public int getOwner() {
		return ownerNum;
	}
	
	
	public void buyBuilding(Player player) {	
		if ((player.getCapital() >= houseCost) && (ownerNum == player.getPlayerNum()) && (buildingNum < 5)){
			buildingNum++;	
			player.reduceCapital(houseCost);
			if(buildingNum <= 4) {
				player.setNumOfHouses(player.getNumOfHouses()+1);
				System.out.println("You now have: " + buildingNum + "houses");
				totalHomesAvailable--;
			}
			else {
				player.setNumOfHotels(1);
				player.setNumOfHouses(0);
				System.out.println("You now have a hotel ");
				totalHomesAvailable = totalHomesAvailable + 4;
				totalHotelsAvailable--;
			}
			
		}
	}
	
	public void sellBuilding(Player player) {	
		if ((buildingNum >= 1) && (ownerNum == player.getPlayerNum())){	
			
			if(buildingNum < 5) {
				totalHomesAvailable++;	
			}
			else if (buildingNum == 5){
				totalHotelsAvailable++;
			}
			
			buildingNum--;
			player.addCapital(houseCost/2);
		}
	}
	
	public void ownerOwnsNeighborhood(Player player) { // Checks if owner bought out the whole neighborhood
		BuildingLand temp;
		int sameColorPropNum = 0;
		
		for (int count = 0; count < player.ownedProperties.size(); count++) {
			
			if(player.ownedProperties.get(count).getPropType() == 1) {	// Is from BuildingLand
				temp = ((BuildingLand) player.ownedProperties.get(count));
				if (temp.neighborhoodColor.equals(this.neighborhoodColor)) { // A player property has same color as current property
					sameColorPropNum++;
				}
			}
		}
		
		if((this.neighborhoodColor.equals(Color.WHITE)) || (this.neighborhoodColor.equals(Color.BLUE))) { // Two neighborhoods with two only properties
			if(sameColorPropNum == 1) {
				this.playerOwnsNeighborhood = 1;
			}
		}else {
			if(sameColorPropNum == 2) {
				this.playerOwnsNeighborhood = 1;
			}
		}
		// Let other properties in neighborhood know player owns whole neighborhood
		if(this.playerOwnsNeighborhood == 1) {
			for (int count = 0; count < player.ownedProperties.size(); count++) {
				
				if(player.ownedProperties.get(count).getPropType() == 1) {	// Is from BuildingLand
					temp = ((BuildingLand) player.ownedProperties.get(count));
					if (temp.neighborhoodColor.equals(this.neighborhoodColor)) { // A player property has same color as current property
						player.ownedProperties.get(count).setIfPlayerOwnsNeighborhood();
					}
				}
			}
		}
	}
	
	
	public int getRent() {
		if ((buildingNum <= -1) ||  (buildingNum >= 6)){
			return 0;
		}else if ((playerOwnsNeighborhood == 1) && (buildingNum == 0)){
			return (rent[buildingNum] * 2);
		}else {
			return rent[buildingNum];
		}
	}
	
}