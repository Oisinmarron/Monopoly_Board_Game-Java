package monopolyTheGame;

import java.util.ArrayList;
import java.util.Scanner;

public class CornerTiles extends NonPurchasableProperty{
	Scanner jailSelect = new Scanner(System.in);
	
	public CornerTiles(int boardNum) {
		super(boardNum);
		propType = 3;
	}

	public void goToJail(Player player, ArrayList <Player> allPlayers) {
		System.out.println("Go to Jail");
		player.setBoardLocation(10);
		player.putInJail();
		
		System.out.println("Jail rules: Select 1-5");
		System.out.println("1. Roll doubles on any of next 3 turns to get out of jail");
		System.out.println("2. Use 'Get Out of Jail Free Card");
		System.out.println("3. Purchase 'Get Out of Jail Free Card'");
		System.out.println("4. Pay $50 to get out of jail.");
		
		int playerIn = jailSelect.nextInt();
		switch(playerIn){
		case 1:
			return;
		case 2:
			if(player.jailCard == 1) {
				player.getOutOfJail();
			}
			else {
				System.out.println("You don't have a 'Get Out of Jail Free Card.'");
			}
			break;
		case 3:
			System.out.println("Searching for players who have this card.");
			buyCard(player, allPlayers);
			break;
		case 4:
			player.reduceCapital(50);
			System.out.println("You have: $" + player.getCapital());
			break;
		default:
			return;			
		}
	}
	
	public void freeParking() {
		System.out.println("Free Parking");
	}
	
	public void justVisiting() {
		System.out.println("Just visiting");
	}
	
	public void atGo(Player player) {
		System.out.println("Collect $200 as you pass GO");
		player.addCapital(200);
	}
	
	protected void buyCard(Player player, ArrayList <Player> allPlayers) {
		String response = null;
		
		for(int i = 0; i < allPlayers.size(); i++) {
			if(allPlayers.get(i).getOutOfJailFreeCard() == 1) {
				System.out.println("Player " + allPlayers.get(i).getName() + " has a get of Jail Free Card.\n Is he willing to sell? Y/N");
				response = jailSelect.next();
				
				if(response.equals("Y")){
					//Haggling
					System.out.println("Player " + allPlayers.get(i).getName() + "name your price.");
					int value = jailSelect.nextInt();
					System.out.println("Player " + player.getName() + " , the card is offered at $" + value + "\nDo you accept?");
					response = jailSelect.next();
					//Check deal acceptability
					if(response.equals("Y")){
						player.reduceCapital(value);
						allPlayers.get(i).addCapital(value);
						System.out.println("Player " + player.getName() + "your new balance is $" + player.getCapital());
						System.out.println("Player " + allPlayers.get(i).getName() + "your new balance is $" + allPlayers.get(i).getCapital());
					}
					else {
						System.out.println("Player " + player.getName() + " refuses the offer.");
					}
				}
			}
			else {
				System.out.println("Player " + player.getName() + " doesn't have cards available for purchase");
			}
		}
	}
}
