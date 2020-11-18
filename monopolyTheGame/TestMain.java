/*package monopolyTheGame;

import java.awt.Color;
import java.util.ArrayList;

public class TestMain {
	static Dice dice = new Dice();
	
	
	public TestMain() {
		
	}

	//Dice check
/*
	public static void main (String agrs[]) {
		System.out.print("Test");
		System.out.print("Dice roll ");
		int value = dice.rollDice();
		System.out.println(value);
	}
*/
	// Tile jump, player setName, and all player functions test
/*
	public static void main (String agrs[]){
		Player player = new Player();
		player.setName();
		player.setBoardLocation(5);
		System.out.println("Name: " +player.getName()+"\nLocation: " +player.getBoardLocation());
		System.out.println("Changing board location");
		player.setBoardLocation(12);
		System.out.println("New location: " +player.getBoardLocation());
	}
*/
	//Shuffle deck check implementation
/*
	public static void main (String args[]) {
		Player player = new Player();
		Property deck = new ChanceCards(7,"Chance");
		
		player.addCapital(10000000);
		player.setBoardLocation(12);
		player.setName();
		
		System.out.println("Name: " +player.getName()+"\n Location: " + player.getBoardLocation() +
				"\nCapital: " + player.getCapital());
		System.out.print("\nDraw a card: \n");
		((ChanceCards) deck).cardList(player);
		System.out.println("\n\nExecuting test\n\n");
		System.out.println("Name: " +player.getName()+"\n Location: " +player.getBoardLocation() + 
				"\nCapital: " + player.getCapital());
	}
*/
	//Create a tile
/*	
	public static void main(String args[]) {
		
		//Neighborhoods Test = new Neighborhoods(1,"test",30,60,10,30,90,160,250,50,2,Color.WHITE);
		CornerTiles Test = new CornerTiles(23,"GO!");
		System.out.print("Tile location number: ");
		System.out.println(Test.getLoc());
		System.out.print("Tile Name: ");
		System.out.println(Test.getName());
		/*
		//For neighborhoods and railroads
		System.out.println(Test.getbaseRent());
		System.out.print("Mortgage: $");
		System.out.println(Test.getMortgage());
		System.out.print("Building Cost: $");
		System.out.println(Test.getBuildCost());
		System.out.print("Rent from 0 to 5 homes:\n");
		System.out.println(Test.getOneHouseRent());
		System.out.println(Test.getTwoHouseRent());
		System.out.println(Test.getThreeHouseRent());
		System.out.println(Test.getFourHouseRent());
		System.out.println(Test.getHotelRent());
		System.out.print("Tile Color: ");
		System.out.println(Test.getNeighborhoodColor());
	}
*/
	
	//Initializer test
/*	
	public static void main(String args[]) {
	    
		Initializer player = new Initializer();
		ArrayList<Player> returnedList = player.getPlayers();
 		for(int i = 0; i < returnedList.size(); i++) {
			System.out.println("Player: " + returnedList.get(i).getName());
 		}
	}
*/
	//Testing Board initilization
/*
	public static void main(String args[]) {
		Initializer board = new Initializer();
		ArrayList<Property> returnedTiles = board.Intialize();
 		for(int i = 0; i < returnedTiles.size(); i++) {
			System.out.println("Name: " + returnedTiles.get(i).getName());
			System.out.println("Location:  " + returnedTiles.get(i).getLoc() + " Purchase Cost: " 
					+ returnedTiles.get(i).getClass());
 		}
	}
*/
	//Testing Corner executables and test logic for player movement
/*
	public static void main(String args[]) {
		//Create test players
		Initializer player = new Initializer();
		ArrayList<Player> players = player.getPlayers();
 		for(int i = 0; i < players.size(); i++) {
			System.out.println("Player: " + players.get(i).getName());
 		}
 		
 		int j=0;
 		while(j < players.size() && players.size() > 1) {
 			System.out.println("Player: " + players.get(j).getName() + " - Number: " + j
 					+ "\nCurrent Location: " + players.get(j).getBoardLocation());
 			players.get(j).setBoardLocation(Dice.rollDice());
 			System.out.println("Player: " + players.get(j).getName() + " - Number: " + j
 					+ "\nNew Location: " + players.get(j).getBoardLocation());
 			j++;
 		}
 		
	}
*/
/*
	//Token creation
	public static void main(String args[]) {
		Initializer tokens = new Initializer();
		ArrayList<String> token = tokens.createTokens();
 		Player test = new Player(0);
 		test.assignToken(token);
	}
}
*/
