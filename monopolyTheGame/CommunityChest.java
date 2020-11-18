package monopolyTheGame;

import java.util.ArrayList;
import java.util.Collections;

public class CommunityChest{
	
	public int repairCosts;
	ArrayList<Integer> chance = new ArrayList<>();
	
	//Create a deck of cards and shuffle
	public CommunityChest() {
		int counter;
		
		for(counter = 0; counter < 17; counter++) {
			chance.add(counter+1);
			//System.out.println("The element is: " + chance.get(i));
		}
		
		Collections.shuffle(chance);
	}
	
	//Draw card and replace
	protected void drawCard(Player player, ArrayList<Player> allPlayers) {
		int cardNum = chance.get(0);
		if(cardNum != 0) {	// Isn't get out of jail free card
			Collections.rotate(chance, 1); //takes first element and puts it in the back
		}
		pickCard(player, allPlayers, cardNum);
	}
	

	public void pickCard(Player player, ArrayList<Player> allPlayers, int cardNum) {
	
		switch (cardNum) {
		case 0:			
			getOutOfJailFree(player);
			break;
		case 1:		// Bank Error, collect $200		
			collect(player, 200);
			break;
		case 2:		// Doctor's fees, pay $50
			pay(player, 50);
			break;
		case 3:			
			goToGo(player);
			break;
		case 4:		// Sale of stock
			collect(player, 50);
			break;
		case 5:			
			goToJail(player);
			break;
		case 6:		// Grand Opera Night	
			collectOffAll(player, allPlayers, 50);
			break;
		case 7:		// XMas Fund matures
			collect(player, 50);
			break;
		case 8:		// Income tax refund
			collect(player, 20);
			break;
		case 9:		// Your birthday
			collectOffAll(player, allPlayers, 10);
			break;
		case 10:	// Life insurance	
			collect(player, 100);
			break;
		case 11:	// Hospital fees		
			pay(player, 50);
			break;
		case 12:	// School fees		
			pay(player, 50);
			break;
		case 13:	// Receive consultancy fees 		
			collect(player, 25);
			break;
		case 14:	// Street Repairs		
			streetRepairs(player);
			break;
		case 15:	// Beauty contest	
			collect(player, 10);
			break;
		case 16:	// Inheritance money	
			collect(player, 100);
			break;
		default:		
			System.out.println("Error drawing a card");
		}
	}
	
	
	public void getOutOfJailFree(Player player) {
		chance.remove(0);
		player.setJailFreeCard();
	}

	public void goToGo(Player player) {
		player.setBoardLocation(0);
	}

	public void goToJail(Player player) {
		player.setBoardLocation(10);
		player.putInJail();
	}

	
	public void collectOffAll(Player player, ArrayList <Player> allPlayers, int value) {
		int totalValue = 0;
		
		for (int counter = 0; counter < allPlayers.size(); counter++) {
			if(counter != player.playerNum) {	// All other players pay
				allPlayers.get(counter).reduceCapital(value);
				totalValue += value;
			}	
		}
		player.addCapital(totalValue);
	}

	public void pay(Player player, int value) {
		player.reduceCapital(value);
	}

	public void collect(Player player, int value) {
		player.addCapital(value);
	}
	
	public void streetRepairs(Player player) {
		repairCosts = (40 * player.getNumOfHouses());
		repairCosts += (115 * player.getNumOfHotels());
		player.reduceCapital(repairCosts);
	}
	
	public void jailFreeCardUsed() {
		chance.add(0);
	}
}
