import java.util.*; 

public class CommunityChestSquare extends Square {

	CommunityChestSquare() {
		super("Community Chest");
	}

	public void communityChest(Player Player1, Player Player2, Player Player3, Bank bank) {
		// Assuming player1 lands on Community Chest.
		// Assuming board is indexed from 0 to 39.
		HashMap<Integer,String> communityChestCards = new HashMap<Integer,String>();
		communityChestCards.put(1, "Advance to go");
		communityChestCards.put(2, "Receive from bank error");
		communityChestCards.put(3, "Pay doctor fee");
		communityChestCards.put(4, "Receive from stock sale");
		communityChestCards.put(5, "Get out of jail");
		communityChestCards.put(6, "Go to jail");
		communityChestCards.put(7, "Receive from opera night");
		communityChestCards.put(8, "Receive from holiday funds");
		communityChestCards.put(9, "Receive income tax refund");
		communityChestCards.put(10, "Receive birthday gift");
		communityChestCards.put(11, "Receive from life insurance");
		communityChestCards.put(12, "Pay hospital fee");
		communityChestCards.put(13, "Pay school fee");
		communityChestCards.put(14, "Receive consultancy fee");
		communityChestCards.put(15, "Pay street repairs");
		communityChestCards.put(16, "Receive from beauty contest");
		communityChestCards.put(17, "Receive inheritance");

		ArrayList<Integer> communityChestDeck = new ArrayList<>(); 
		for(int i=1; i<18; i++)
			communityChestDeck.add(i);

		Collections.shuffle(communityChestDeck);
		int card = communityChestDeck.get(0);

		System.out.println("Community Chest card reads: " + communityChestCards.get(card));

		switch(card){
			case 1: Player1.setPosition(0);
			Player1.addCash(200);
			bank.deductCash(200);
			break;

			case 2: Player1.addCash(200);
			bank.deductCash(200);
			break;

			case 3: Player1.deductCash(50);
			bank.addCash(50);
			break;
			
			case 4: Player1.addCash(50);
			bank.deductCash(50);
			break;

			case 5: break; //TODO Add feature to retain this CC card and should check whether player has get out of jail card when player lands in jail.

			case 6: if (Player1.getPosition() > 10) {
				Player1.addCash(200);	
				bank.deductCash(200);
			}
			Player1.setPosition(10);
			break;

			case 7: Player1.addCash(150);
			Player2.deductCash(50);
			Player3.deductCash(50);
			break;

			case 8: Player1.addCash(100);
			bank.deductCash(100);
			break;
			
			case 9: Player1.addCash(20);
			bank.deductCash(20);
			break;

			case 10: Player1.addCash(30);
			Player2.deductCash(10);
			Player3.deductCash(10);
			break;

			case 11: Player1.addCash(100);
			bank.deductCash(100);
			break;

			case 12, 13: Player1.deductCash(50);
			bank.addCash(50);
			break;

			case 14: Player1.addCash(25);
			bank.deductCash(25);
			break;

			case 15: break; // TODO add count house feature for player.

			case 16: Player1.addCash(10);
			bank.deductCash(10);
			break;

			case 17: Player1.addCash(100);
			bank.deductCash(100);
			break;
		}

		communityChestDeck.remove(0);
		communityChestDeck.add(card); //TODO Handle Get out of Jail card.
	}

}
