package monopolyTheGame;

import java.util.ArrayList;

public class Cards extends NonPurchasableProperty{

	public Cards(int boardNum) {
		super(boardNum);
		propType = 5;
	}

	public void chanceCards(ChanceCards chance, Player player) {
		chance.drawCard(player);
	}
	
	public void chestCards(CommunityChest chest, Player player, ArrayList <Player> allPlayers) {
		chest.drawCard(player, allPlayers);
	}
}

