package monopolyTheGame;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;

public class Player {

	public int playerNum;
	public int capital;
	public int location;
	public int numOfHouses;
	public int numOfHotels;
	public int turnsLeftInJail; // Check before each player's turn if they are in jail for this turn
	public String name;
	public int jailCard;
	protected int doubleDiceCount;
	
	String selectedToken = null;
	Scanner input = new Scanner(System.in);
	ArrayList<PurchasableProperty> ownedProperties = new ArrayList<PurchasableProperty>();
	//ArrayList<BuildingLand> ownedProperties = new ArrayList<BuildingLand>();
	
	public Player(int playerNum) {
		capital = 1500;
		location = 0;
		numOfHotels = 0;
		numOfHouses = 0;
		turnsLeftInJail = 0;
		jailCard = 0;
		this.playerNum = playerNum;
		doubleDiceCount=0;
	}
	public void doubleCount() {
		doubleDiceCount++;
		if(doubleDiceCount == 3) {
			putInJail();
			doubleDiceCount=0;
		}
	}
	public void setName() {
		System.out.println("\nEnter your name\n");
		name = input.nextLine();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> assignToken(ArrayList<String> tokens) {
		

		String userSelection = null; //holder for user input
		System.out.print("____________________\nSelect token 1-6:\n");
		
		while(selectedToken == null) {
			
	 		for(int i = 0; i < tokens.size(); i++) {//print list of tokens available
				System.out.println("token: " + tokens.get(i));
	 		}
	 		
			userSelection = input.nextLine(); //Scan in user input
			
			if(tokens.contains(userSelection)) { //Check for validity
				selectedToken = userSelection;
				tokens.remove(userSelection);
				System.out.print("You have selected " + selectedToken + "\n");
			}
			
			else{
				System.out.print("Invalid Token, please try again from the list below\n");
			}
		}
		
		return tokens;
	}

	public int getPlayerNum() {
		return playerNum;
	}
	
	public void reduceCapital(int capital) {
		this.capital -= capital;
	}
	
	public void addCapital(int capital) {
		this.capital += capital;
	}
	
	public int getCapital(){
		return capital;
	}
	
	public void setBoardLocation(int location) {
		if(location > 39) {
			location = location - 39;
		}
		this.location = location;
	}
	
	public int getBoardLocation() {
		return location;
	}
	
	public int getNumOfHouses(){
		return numOfHouses;
	}
	public void setNumOfHouses(int houses) {
		numOfHouses = houses;
	}
	
	public int getNumOfHotels(){
		return numOfHotels;
	}
	public void setNumOfHotels(int hotels) {
		numOfHotels = hotels;
	}
	
	public void putInJail(){
		turnsLeftInJail = 3;
		location = 10;
	}
	
	public void getOutOfJail(){ 
		turnsLeftInJail = 0;
	}
	
	public void setJailFreeCard() {
		jailCard = 1;
	}
	
	public void useJailFreeCard(CommunityChest chest) {
		getOutOfJail();
		jailCard = 0;
		chest.jailFreeCardUsed();
	}
	
	public void useJailFreeCard(ChanceCards chance) {
		getOutOfJail();
		jailCard = 0;
		chance.jailFreeCardUsed();
	}
	
	public int getOutOfJailFreeCard() {
		return jailCard;
	}
	
	public void removeLand(PurchasableProperty property) {
		ownedProperties.remove(property);
		addCapital(property.getPurchaseCost()/2);
		if (property.getPropType() == 1) {	// If property can have houses
			addCapital((((BuildingLand) property).getBuildingNum()*((BuildingLand) property).getPricePerHouse())/2);
		}
		property.resetOwner();
	}
	
	public ArrayList<PurchasableProperty> getOwnedProperties() {
		return ownedProperties;
	}
	
	public void propertyTransactions(PurchasableProperty property, ArrayList <Player> allPlayers) {
		if(property.getOwner() == -1) {
			property.setOwner(this, allPlayers);
		}
		else {
			if(this.getCapital() >= property.getRent()) {
				
				System.out.println(allPlayers.get(playerNum).getName() + " pay rent to " 
						+ property.getOwner() + ", amounting to  " + property.getRent());
				this.reduceCapital(property.getRent());
				
				allPlayers.get(property.getOwner()).addCapital(property.getRent());
			}
			else {
				this.bankrupt(this, allPlayers.get(property.getOwner()), allPlayers); // this player bankrupt, owes all they have to owner of this property
			}
		}
	}
	
	public void propertyTransactions(Tax property) {
		property.taxPlayer(this);
	}
	
	public void propertyTransactions(CornerTiles property) {
		if(property.getLocation() == 0) {
			property.atGo(this);
		} else if (property.getLocation() == 10) {
			property.justVisiting();
		} else if (property.getLocation() == 20) {
			property.freeParking();
		}else {
			property.goToJail(this);
		}
	}
	
	public void propertyTransactions(Cards property, ArrayList <Player> allPlayers, CommunityChest chest, ChanceCards chance) {
		int propLoc = property.getLocation();
		if ((propLoc == 2) || (propLoc == 17) || (propLoc == 33)) {
			System.out.println("You pulled a chest card");
			property.chestCards(chest, this, allPlayers);
		}
		else {
			System.out.println("You pulled a chance card");
			property.chanceCards(chance, this);
		}
	}
	
	public void bankrupt(Player bankrupted, Player owner, ArrayList <Player> allPlayers) {
		for (int counter = 0; counter < ownedProperties.size(); counter++) {
			bankrupted.removeLand(ownedProperties.get(counter));
		}
		
		owner.addCapital(bankrupted.getCapital());
		allPlayers.remove(bankrupted.playerNum);
	}

}

