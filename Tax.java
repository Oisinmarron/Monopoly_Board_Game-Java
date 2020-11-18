package monopolyTheGame;

public class Tax extends NonPurchasableProperty{

	private int tax;
	
	public Tax(int boardNum) {
		super(boardNum);
		propType = 4;
	}
	
	public void taxPlayer(Player player){
		if(location == 4) {
			
			System.out.println("Income tax! Pay 10% of your total worth or $200 to the bank, whichever is lower");
			if (player.getCapital() > 200) {
				tax = (int) (player.getCapital()*0.10);
				System.out.println("Your net work is greater than $200, pay 10%.");
				System.out.println("Income tax = $" + tax);
				player.reduceCapital(tax);
			}
			else {
				tax = 200;
				System.out.println("Your net work is less than $200, pay $200.");
				System.out.println("Income tax = $" + tax);
				player.reduceCapital(tax);
			}
			System.out.println("Tax paid to the order of " + tax);
			System.out.println("Your account is now at: $" + player.getCapital());
		}
		else if(location == 38) {
			System.out.println("Luxury tax! Pay the bank $75.");
			System.out.println("Luxury tax = $" + tax);
			player.reduceCapital(tax);
			System.out.println("Your account is now at: $" + player.getCapital());
		}
	}
}
