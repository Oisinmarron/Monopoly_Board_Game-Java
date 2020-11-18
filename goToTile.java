/*
This is a test class for isolating and testing the movePlayer command independent of the main code. See main
test function: "Tile jump check" for test implementation.
 package monopolyTheGame;
 

public class goToTile {
	
	public goToTile(String name, int boardNum) {
	}
	
	public static void movePlayer(Player player, int boardNum) {
		System.out.println("Moving player " + player.getName() + " located on tile " + boardNum + " to:");
		if(player.getBoardLocation() < boardNum) {
			player.addCapital(200);
		}
		player.setBoardLocation(boardNum);
	}
	
}
*/