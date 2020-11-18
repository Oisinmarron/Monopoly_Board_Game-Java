package monopolyTheGame;

import java.util.ArrayList;
import java.util.Collections;

public class ChanceCards {
		
	public int repairCosts;
	ArrayList<Integer> chance = new ArrayList<>();
		
	//Create a deck of cards and shuffle
	public ChanceCards() {
		int counter;
	
		for(counter = 0; counter < 16; counter++) {
			chance.add(counter+1);
			//System.out.println("The element is: " + chance.get(i));
		}
			
		Collections.shuffle(chance);
		
	}
		
	//Draw card and replace
	public void drawCard(Player player) {
		int cardNum = chance.get(0);
		if(cardNum != 0) {	// Isn't get out of jail free card
			Collections.rotate(chance, 1); //takes first element and puts it in the back
		}
		pickCard(player, cardNum);
	}

	public void pickCard(Player player, int cardNum) {
		
		switch (cardNum) {
			case 0:			
				getOutOfJailFree(player);
				break;
			case 1:			
				goBackThreeSpaces(player);
				break;
			case 2:			
				goToBoardwalk(player);
				break;
			case 3:			
				goToGo(player);
				break;
			case 4:			
				goToIllinoisAv(player);
				break;
			case 5:			
				goToJail(player);
				break;
			case 6:			
				goToNearestRailroad(player);
				break;
			case 7:			
				goToNearestUtility(player);
				break;
			case 8:			
				goToReadingRailroad(player);
				break;
			case 9:			
				goToStCharlesPlace(player);
				break;
			case 10:			
				payAllOtherPlayers(player);
				break;
			case 11:			
				payPoorTax(player);
				break;
			case 12:			
				propertyRepairs(player);
				break;
			case 13:			
				receiveBankDividend(player);
				break;
			case 14:			
				receiveBuildingLoan(player);
				break;
			case 15:			
				competitionPrize(player);
				break;
			default:		
				System.out.println("Error drawing a card");
			}
		}
	
		public void getOutOfJailFree(Player player) {
			chance.remove(0);
			player.setJailFreeCard();
		}
		
		public void goBackThreeSpaces(Player player) {
			if (player.getBoardLocation() == 2) {
				player.setBoardLocation(39);
			}else {
				player.setBoardLocation(player.getBoardLocation() - 3);
			}
		}
		
		public void goToBoardwalk(Player player) {
			player.setBoardLocation(39);
		}

		public void goToGo(Player player) {	// Goes through corner tile class, don't need to add 200 to capital
			player.setBoardLocation(0);		
		}

		public void goToIllinoisAv(Player player) {
			if (player.getBoardLocation() == 33) {
				player.setBoardLocation(24);
				player.addCapital(200); 
			}else {
				player.setBoardLocation(24);
			}
		}

		public void goToJail(Player player) {
			player.setBoardLocation(10);
			player.putInJail();
		}

		public void goToNearestRailroad(Player player) {
			if (player.getBoardLocation() == 2) {
				player.setBoardLocation(5);
			}else if (player.getBoardLocation() == 17) {
				player.setBoardLocation(25);
			}else {
				player.setBoardLocation(35);
			}
			// Need to pay double rent if land on someone else's railroad property
		}

		public void goToNearestUtility(Player player) {
			if((player.getBoardLocation() == 2) || (player.getBoardLocation() == 33)) {
				player.setBoardLocation(12);
			}else {
				player.setBoardLocation(28);
			}
		}

		public void goToReadingRailroad(Player player) {
			if ((player.getBoardLocation() == 17) || (player.getBoardLocation() == 33)) {
				player.setBoardLocation(5);
				player.addCapital(200); 
			}else {
				player.setBoardLocation(5);
			}
		}

		public void goToStCharlesPlace(Player player) {
			if ((player.getBoardLocation() == 17) || (player.getBoardLocation() == 33)) {
				player.setBoardLocation(11);
				player.addCapital(200); 
			}else {
				player.setBoardLocation(11);
			}
		}

		public void payAllOtherPlayers(Player player) {
			player.reduceCapital(15);
		}

		public void payPoorTax(Player player) {
			player.reduceCapital(15);
		}

		public void propertyRepairs(Player player) {
			repairCosts = (25 * player.getNumOfHouses());
			repairCosts += (100 * player.getNumOfHotels());
			player.reduceCapital(repairCosts);
		}

		public void receiveBankDividend(Player player) {
			player.addCapital(50); 		// Adjust depending on if bank's capital has a recorded budget (i.e let bank know)
		}

		public void receiveBuildingLoan(Player player) {
			player.addCapital(150); 		// Adjust depending on if bank's capital has a recorded budget (i.e let bank know)
		}

		public void competitionPrize(Player player) {
			player.addCapital(100);
		}
		
		public void jailFreeCardUsed() {
			chance.add(0);
		}
	}
