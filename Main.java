package monopolyTheGame;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
//	protected static int selection;
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
			
		int stopGame = 0, count, initialPlayerNum, displayPlayerNum; //Temporary numOfPlayers
		int rollNum;
		
		CommunityChest chest = new CommunityChest();
		ChanceCards chance = new ChanceCards();
		
		ArrayList <Property> properties = new ArrayList<Property>();
		Initializer initialize = new Initializer();
		
		properties = initialize.Initialize(chest, chance);
		
		Dice dice = new Dice();
		
		ArrayList <Player> playerArray = new ArrayList<Player>();
		playerArray = initialize.getPlayers(); 
		initialPlayerNum = playerArray.size();
		
		while(stopGame == 0) {
			
			for(count = 0; count < playerArray.size(); count++) {
				
				// Player rolls dice, gets put to new location
				displayPlayerNum = count+1;
				System.out.println("\n\n\n________________________________________");
				System.out.println("Player " + displayPlayerNum + " " + playerArray.get(count).getName()
						+ "\nCurrent Location: " + playerArray.get(count).getBoardLocation());
//				switch(menu()){
//					case 1:
//						if((properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 1) || (properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 2))
//						{
//							playerArray.get(count).propertyTransactions((PurchasableProperty) properties.get(playerArray.get(count).getBoardLocation()), playerArray);
//						}
//						
//					case 2:
//						System.out.println("\n\n\n________________________________________");
//						System.out.println("Name: " + playerArray.get(count).getName());
//						System.out.println("Capital: $" + playerArray.get(count).getCapital());
//						System.out.println("Board Location: " + playerArray.get(count).getBoardLocation());
//						System.out.println("Properties owned: " + playerArray.get(count).getOwnedProperties());
//						System.out.println("Houses owned: " + playerArray.get(count).getNumOfHouses() +
//								" Hotels owned: " + playerArray.get(count).getNumOfHotels());
//						System.out.println("________________________________________");
//					case 3:
//						System.out.println("\n\n\n________________________________________");
//						System.out.println("\nProperties available to mortgage");
//						System.out.println(playerArray.get(count).getOwnedProperties());
//					case 4:
//					case 5:
//					default:
//				}
				
				rollNum = dice.rollDice(playerArray.get(count));
				
				playerArray.get(count).setBoardLocation(playerArray.get(count).getBoardLocation() + rollNum);
				
				// In case cards change board location, separate if statement
				if(properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 5){
					playerArray.get(count).propertyTransactions((Cards) properties.get(playerArray.get(count).getBoardLocation()), playerArray, chest, chance);
				}
				
				if((properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 1) || (properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 2)){
					playerArray.get(count).propertyTransactions((PurchasableProperty) properties.get(playerArray.get(count).getBoardLocation()), playerArray);
				}
				
				else if(properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 3) {
					playerArray.get(count).propertyTransactions((CornerTiles) properties.get(playerArray.get(count).getBoardLocation()));
				}
					
				else if(properties.get(playerArray.get(count).getBoardLocation()).getPropType() == 4) {
					playerArray.get(count).propertyTransactions((Tax) properties.get(playerArray.get(count).getBoardLocation()));
				}
				
				
				
				
				if (playerArray.size() == initialPlayerNum - 2) {
					stopGame = 1;
				}
	/*			
				if (playerArray.get(count).getCapital() <= 0 ) {
					if(playerArray.get(count).getOwnedProperties().isEmpty() == false) {
						System.out.println("You have no cash remaining. You must liquidate your holdings");
						System.out.println("This means that you must sell a tile. If the tile has buildings,"
								+ "all buildings will be returned to the bank at half price prior to sale of land.");
						int counter=0;
						while(counter < playerArray.get(count).getOwnedProperties().size()) {
							System.out.println(playerArray.get(count).getOwnedProperties().get(counter).getPropName());	
						}
						
						System.out.println("Select an option");
						String propertySelected = input.next();
						if(playerArray.get(count).getOwnedProperties().contains()) {
							
						}
				
						playerArray.get(count).propertyTransactions((PurchasableProperty) properties.get(playerArray.get(count).getBoardLocation()), playerArray);
						playerArray.get(count).removeLand();
					}
*/					
					
				}
				
			}	
			
		}		
	}
	
//	public static int menu() {
//		selection=0;
//		System.out.println("Enter options 1 - 5 for options");
//		System.out.println("1. Upgrade property");
//		System.out.println("2. Display stats");
//		System.out.println("3. Mortgage properties");
//		System.out.println("4. Purchase Homes");
//		System.out.println("5. Roll Dice");
//		selection = input.next();
//		return selection;
//	}


