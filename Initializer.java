package monopolyTheGame;

import java.util.ArrayList;
import java.awt.Color;
import java.util.Scanner;

public class Initializer {
	
	static String[] tokenNames = {"race car","iron","wheelbarrow","shoe","top hat","thimble"};
	Scanner input = new Scanner(System.in);

	public Initializer() {
		
	}
	
	public ArrayList<Property> Initialize(CommunityChest chest, ChanceCards chance) {
		
		ArrayList <Property> properties = new ArrayList<Property>();
		
		properties.add(new CornerTiles(0));
		properties.add(new BuildingLand(1,"Mediterrean Avenue",30,60,10,30,90,160,250,50,2,Color.WHITE)); 
		properties.add(new Cards(2));
		properties.add(new BuildingLand(3,"Baltic Avenue",30,60,20,60,180,320,450,50,4,Color.WHITE));		
		properties.add(new Tax(4));
		properties.add(new Railroads(5,"Reading Railroad",100,200));
		properties.add(new BuildingLand(6,"Oriental Avenue",50,100,30,90,270,400,550,50,6,Color.CYAN));
		properties.add(new Cards(7));
		properties.add(new BuildingLand(8,"Vermont Avenue",50,100,30,90,270,400,550,50,6,Color.CYAN));
		properties.add(new BuildingLand(9,"Connecticut Avenue",60,120,40,100,300,450,600,50,8,Color.CYAN));
		properties.add(new CornerTiles(10));
		properties.add(new BuildingLand(11,"St. Charles Place",70,140,50,150,450,625,750,50,10,Color.MAGENTA));
		properties.add(new Utilities(12,"Electric Company",75,150));
		properties.add(new BuildingLand(13,"States Avenue",70,140,50,150,450,625,750,100,10,Color.MAGENTA));
		properties.add(new BuildingLand(14,"Virginia Avenue",80,160,60,180,500,700,900,100,12,Color.MAGENTA));
		properties.add(new Railroads(15,"Pennsylvania Railroad",100,200));
		properties.add(new BuildingLand(16,"St. James Place",90,180,70,200,550,750,950,100,14,Color.ORANGE));
		properties.add(new Cards(17));
		properties.add(new BuildingLand(18,"Tennessee Avenue",90,180,70,200,550,750,950,100,14,Color.ORANGE));
		properties.add(new BuildingLand(19,"New York Avenue",100,200,80,220,600,800,1000,100,16,Color.ORANGE));
		properties.add(new CornerTiles(20));
		properties.add(new BuildingLand(21,"Kentucky Avenue",110,220,90,250,700,875,1050,150,18,Color.RED));
		properties.add(new Cards(22));
		properties.add(new BuildingLand(23,"Indiana Avenue",110,220,90,250,700,875,1050,150,18,Color.RED));
		properties.add(new BuildingLand(24,"Illinois Avenue",120,240,100,300,750,925,1100,150,20,Color.RED));
		properties.add(new Railroads(25,"B. & O. Railroad",100,200));
		properties.add(new BuildingLand(26,"Atlantic Avenue",130,260,110,330,800,975,1150,150,22,Color.YELLOW));
		properties.add(new BuildingLand(27,"Ventnor Avenue",130,260,110,330,800,975,1150,150,22,Color.YELLOW));
		properties.add(new Utilities(28,"Water Works",75,150));
		properties.add(new BuildingLand(29,"Marvin Gardens",140,280,120,360,850,1025,1200,150,24, Color.YELLOW));
		properties.add(new CornerTiles(30));
		properties.add(new BuildingLand(31,"Pacific Avenue",150,300,130,390,900,1100,1275,200,26,Color.GREEN));
		properties.add(new BuildingLand(32,"North Carolina Avenue",150,300,130,390,900,1100,1275,200,26,Color.GREEN)); 
		properties.add(new Cards(33));
		properties.add(new BuildingLand(34,"Pennsylvania Avenue",160,320,150,450,1000,1200,1400,200,28,Color.GREEN));
		properties.add(new Railroads(35,"Short Line",100,200));
		properties.add(new Cards(36));
		properties.add(new BuildingLand(37,"Park Place",175,350,175,500,1100,1300,1500,200,35,Color.BLUE));
		properties.add(new Tax(38));
		properties.add(new BuildingLand(39,"Boardwalk",200,400,200,600,1400,1700,2000,200,50,Color.BLUE));

		return properties;
	}

	// Initializes players
	public ArrayList <Player> getPlayers(){
		
		ArrayList <Player> players = new ArrayList<Player>();
		
		int playerNum = 0; //holder for display of player number
		int playerCount = 0;
		
		int j = 0;
		
		System.out.print("Enter number of players (2-6 only):\n");
		playerCount = input.nextInt();
		
		while(playerCount <= 1 || playerCount > 6) {
			System.out.print("Error, invalid range of players\n");
			System.out.print("Enter number of players (2-6 only):\n");
			playerCount = input.nextInt();
		}
		
		System.out.printf("You have %d number of players...\n", playerCount);
		System.out.print("Players, please enter your names:\n");
		
		while(j < playerCount) {
			players.add(new Player(j));
			players.get(j).setName();
			j++;
		}
		
		players = playerTurnStructure(players, playerCount);

		// Assigns player tokens
		ArrayList <String> tokens = new ArrayList<String>();
		tokens = createTokens();
		
		for (int counter = 0; counter < players.size(); counter++) {
			playerNum = counter+1;
			System.out.println("Player " + playerNum + " "+ players.get(counter).getName());
			tokens = players.get(counter).assignToken(tokens);
		}
		//Print out the new order of players and selected tokens
		System.out.println("New Order of players\n_____________________");
		
		
		for(j = 0; j < playerCount; j++) {
			playerNum = j+1;
			System.out.print("Player: " + playerNum);
			System.out.println(" Name: " + players.get(j).getName() + " ------ Token: " + players.get(j).selectedToken + "\n\n");
		}
	
		return players;

	}
	
	// Rolls die for players, restructures turn order according to highest numbers
	public ArrayList <Player> playerTurnStructure(ArrayList<Player> players, int numOfPlayers) {

		ArrayList <Player> playerArr = players;
		Player playerTemp;
		
		int counter_A, counter_B, highNum, temp;
		int[] diceRoll = new int[numOfPlayers];
		Dice dice = new Dice();
		
		// Dice Roll to see order of the players
		for (counter_A = 0; counter_A < numOfPlayers; counter_A++) {
			diceRoll[counter_A] = dice.rollDice();
		}
				
				
		// Rearranges player array in the order the players will go
		for (counter_A = 0; counter_A < numOfPlayers; counter_A++) {
			highNum = 0;

			for (counter_B = counter_A; counter_B < numOfPlayers; counter_B++) {

				
				if(diceRoll[counter_B] > highNum) {
					playerTemp = playerArr.get(counter_A);
					playerArr.set(counter_A, playerArr.get(counter_B));
					playerArr.set(counter_B, playerTemp);			
							
					highNum = diceRoll[counter_B];
					temp = diceRoll[counter_A];
					diceRoll[counter_A] = diceRoll[counter_B];
					diceRoll[counter_B] = temp;
				}
			}
		}
		
		return players;
	}
	
	public ArrayList <String> createTokens(){
		ArrayList <String> tokens = new ArrayList<String>();
		int i = 0;
		while(i<6) {
			tokens.add(tokenNames[i]);
			i++;
		}
		return tokens;
	}
}