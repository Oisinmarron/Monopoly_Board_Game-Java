package monopolyTheGame;

public class Property {
	
	protected int location;
	protected int propType; // 1 = BuildingLand, 2 = Utilities and Railroads, 3 = CornerTiles, 4 = Tax, 5 = Cards
	
	public Property(int boardNum){
		location = boardNum;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getPropType() {
		return propType;
	}
}
