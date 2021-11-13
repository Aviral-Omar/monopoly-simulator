import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private final Board board;
	private final Bank bank;
	private final ArrayList<Player> players;
	private final CommunityChestDeck communityChestDeck;
	private final ChanceDeck chanceDeck;

	Game(int numberOfPlayers) {
		players = new ArrayList<Player>(numberOfPlayers);
		System.out.println("Enter name of each player");
		Scanner in = new Scanner(System.in);

		// Getting player names from user
		// Giving each player $1500 and remaining to bank
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player(in.nextLine(), 1500));
		}

		bank = new Bank(20580 - numberOfPlayers * 1500);
		board = new Board(bank);

		communityChestDeck = new CommunityChestDeck();
		chanceDeck = new ChanceDeck();

		in.close();
	}

	private void communityChestAction(Player player, CommunityChestCard card) {

		switch (card.getAction()) {

		case AdvanceToGo -> {
			player.setPosition(0);
			player.addCash(200);
			bank.deductCash(200);
		}
		case BankError -> {
			player.addCash(200);
			bank.deductCash(200);
		}
		case BeautyContest -> {
			player.addCash(10);
			bank.deductCash(10);
		}
		case DoctorFee -> {
			player.deductCash(50);
			bank.addCash(50);
		}
		case GetOutOfJail -> {
			// TODO implement get out of jail
		}
		case GoToJail -> {
			// TODO implement go to jail
		}
		case Birthday -> {
			for (Player p : players) {
				p.deductCash(10);
				player.addCash(10);
			}
		}
		case Hospital -> {
			player.deductCash(100);
			bank.addCash(100);
		}
		case IncomeTaxRefund -> {
			player.addCash(20);
			bank.deductCash(20);
		}
		case Inherit -> {
			player.addCash(100);
			bank.deductCash(100);
		}
		case LifeInsuranceMatures -> {
			player.addCash(100);
			bank.deductCash(100);
		}
		case SaleOfStock -> {
			player.addCash(50);
			bank.deductCash(50);
		}
		case SchoolFees -> {
			player.deductCash(50);
			bank.addCash(50);
		}
		case ConsultancyFee -> {
			player.addCash(25);
			bank.deductCash(25);
		}
		case StreetRepairs -> {
			// TODO this card
		}
		case HolidayFundMatures -> {
			player.addCash(100);
			bank.deductCash(100);
		}

		}

	}

	private void chanceAction(Square square, Player player, int dieTotal, ChanceCard card) {

		switch (card.getAction()) {

		case AdvanceToBoardwalk -> {
			player.setPosition(39);
		}
		case AdvanceToGo -> {
			player.setPosition(0);
			player.addCash(200);
			bank.deductCash(200);
		}
		case AdvanceToIllinoisAvenue -> {
			if(player.getPosition() == 36) { //Assuming position can be only 3 chance squares.
				player.addCash(200);
				bank.deductCash(200);
			}
			player.setPosition(24);

			RealEstateSquare sq = (RealEstateSquare) square;
			Actor owner = sq.getOwner();
			if (owner == bank) 
				sq.buyProperty(player, bank);
			else if (owner != player) {
				// Rent levels are based on title deed
				// TODO check double rent condition

				int rent = sq.getRent();
				player.deductCash(rent);
				owner.addCash(rent);
				System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
			}
		}
		case AdvanceToStCharlesPlace -> {
			if(player.getPosition() != 7) { //Assuming position can be only 3 chance squares.
				player.addCash(200);
				bank.deductCash(200);
			}
			player.setPosition(11);

			RealEstateSquare sq = (RealEstateSquare) square;
			Actor owner = sq.getOwner();
			if (owner == bank) 
				sq.buyProperty(player, bank);
			else if (owner != player) {
				// Rent levels are based on title deed
				// TODO check double rent condition

				int rent = sq.getRent();
				player.deductCash(rent);
				owner.addCash(rent);
				System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
			}
		}
		case AdvanceToNearestRailroad -> {
			if (player.getPosition() == 7) {
				System.out.println(player + "advanced to Pennsylvania Railroad.");
				player.setPosition(15);
			}
			else if (player.getPosition() == 22) {
				System.out.println(player + "advanced to B&O Railroad.");
				player.setPosition(25);
			}
			else if (player.getPosition() == 36) {
				System.out.println(player + "advanced to Reading Railroad.");
				player.setPosition(5);
				player.addCash(200);
				bank.deductCash(200);
			}
			
			StationSquare sq = (StationSquare) square;
			Actor owner = sq.getOwner();
			if (owner == bank) 
				sq.buyProperty(player, bank);
			else if (owner != player) {
				int rent = 25 * (int) Math.pow(2, ((Player) owner).getStationsOwned() - 1);
				player.deductCash(rent);
				owner.addCash(rent);
				System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
			}	
		}
		case AdvanceToNearestUtility -> {
			if (player.getPosition() == 7) {
				System.out.println(player + "advanced to Electric Company.");
				player.setPosition(12);
			}
			else if (player.getPosition() == 22) {
				System.out.println(player + "advanced to Water Works.");
				player.setPosition(28);
			}
			else if (player.getPosition() == 36) {
				System.out.println(player + "advanced to Electric Company.");
				player.setPosition(12);
				player.addCash(200);
				bank.deductCash(200);
			}
			
			UtilitySquare sq = (UtilitySquare) square;
			Actor owner = sq.getOwner();
			if (owner == bank) 
				sq.buyProperty(player, bank);
			else if (owner != player) {
				int rent = 0;
				if (((Player) owner).getUtilitiesOwned() == 1) {
					rent = 4 * dieTotal;
					player.deductCash(rent);
					owner.addCash(rent);
				} else {
					rent = 10 * dieTotal;
					player.deductCash(rent);
					owner.addCash(rent);
				}
				System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
			}
		}
		case ReceiveDividend -> {
			player.addCash(50);
			bank.deductCash(50);
		}
		case GetOutOfJail -> {
			// TODO Implement jail functionality.
		}
		case MoveBack -> {
			player.setPosition(player.getPosition()-3);
		}
		case GoToJail -> {
			// TODO Implement jail functionality.
		}
		case PayForRepairs -> {
			// TODO Add house count feature.
		}
		case PaySpeedingFine -> {
			player.deductCash(15);
			bank.addCash(15);
		}
		case AdvanceToReadingRailroad -> {
			player.deductCash(200);
			bank.addCash(200);

			StationSquare sq = (StationSquare) square;
			Actor owner = sq.getOwner();
			if (owner == bank) 
				sq.buyProperty(player, bank);
			else if (owner != player) {
				int rent = 25 * (int) Math.pow(2, ((Player) owner).getStationsOwned() - 1);
				player.deductCash(rent);
				owner.addCash(rent);
				System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
			}	
		}
		case PayForBecomingChairman -> {
			for (Player p : players) {
				p.addCash(50);
				player.deductCash(50);
			}
		}
		case ReceiveBuildingLoanMaturity -> {
			player.addCash(150);
			bank.deductCash(150);
		}
		}

	}

	private int rollDie() {
		return (int) (Math.random() * 6 + 1);
	}

	private void action(Square square, Player player, int dieTotal) {
		// Enhanced switch statement
		// TODO Add houses functionality
		// TODO Add all actions
		// TODO Add game end condition
		if (square instanceof GoSquare) {

			player.addCash(200);
			bank.deductCash(200);

			System.out.println(player + " received $200 for landing on Go.");

		} else if (square instanceof TaxSquare) {

			int amount = ((TaxSquare) square).getAmount();

			player.deductCash(amount);
			bank.addCash(amount);

			System.out.println(player + " paid $" + amount + " to bank as " + square + ".");

		} else if (square instanceof PropertySquare) {
			// TODO Add rent collection, bankruptcy check, possibly auction

			if (square instanceof UtilitySquare) {

				UtilitySquare sq = (UtilitySquare) square;
				Actor owner = sq.getOwner();

				if (owner == bank) {

					sq.buyProperty(player, bank);

				} else if (owner != player) {
					// Rent is 4 * dieTotal if 1 is owned and 10 * dieTotal if both are owned
					int rent = 0;
					if (((Player) owner).getUtilitiesOwned() == 1) {
						rent = 4 * dieTotal;
						player.deductCash(rent);
						owner.addCash(rent);
					} else {
						rent = 10 * dieTotal;
						player.deductCash(rent);
						owner.addCash(rent);
					}
					System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
				}

			} else if (square instanceof StationSquare) {

				StationSquare sq = (StationSquare) square;
				Actor owner = sq.getOwner();

				if (owner == bank) {

					sq.buyProperty(player, bank);

				} else if (owner != player) {
					// Rent levels are 25, 50, 100, 200

					int rent = 25 * (int) Math.pow(2, ((Player) owner).getStationsOwned() - 1);

					player.deductCash(rent);
					owner.addCash(rent);

					System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
				}
			} else {
				RealEstateSquare sq = (RealEstateSquare) square;
				Actor owner = sq.getOwner();

				if (owner == bank) {

					sq.buyProperty(player, bank);

				} else if (owner != player) {
					// Rent levels are based on title deed
					// TODO check double rent condition

					int rent = sq.getRent();

					player.deductCash(rent);
					owner.addCash(rent);

					System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
				}
			}
		} else if (square instanceof CommunityChestSquare) {

			CommunityChestCard card = (CommunityChestCard) communityChestDeck.pickFromTop();
			communityChestAction(player, card);

			if (card.getAction() != CommunityChestActions.GetOutOfJail) {
				communityChestDeck.insertAtBottom(card);
			}

			System.out.println(player + " drew a Community Chest Card.");
			System.out.println(CommunityChestDeck.getMessage(card.getAction(), player));

		} else if (square instanceof ChanceSquare) {

			ChanceCard card = (ChanceCard) chanceDeck.pickFromTop();
			chanceAction(square, player, dieTotal, card);

			if (card.getAction() != ChanceActions.GetOutOfJail) {
				chanceDeck.insertAtBottom(card);
			}
			System.out.println(player + " drew a Chance Card.");
			System.out.println(ChanceDeck.getMessage(card.getAction(), player));
		}
	}

	public void simulateTurn(int playerNumber) {
		Player player = players.get(playerNumber);
		// TODO Add jail functionality
		// TODO Add doubles roll functionality
		int dieOne = rollDie();
		int dieTwo = rollDie();

		System.out.println(player + " rolled " + (dieOne + dieTwo) + ".");

		int finalPosition = player.getPosition() + dieOne + dieTwo;

		// Completing one round
		if (finalPosition >= 40) {
			finalPosition %= 40;
			if (finalPosition > 0) {
				// Passing GO gives $200

				player.addCash(200);
				bank.deductCash(200);

				System.out.println(player + " received $200 for passing Go.");
			}
		}

		player.setPosition(finalPosition);

		Square square = board.getSquare(player.getPosition());

		System.out.println(player + " landed on " + square + ".");

		action(square, player, dieOne + dieTwo);
	}

	public void displayState() {
		System.out.println("Game State:");
		for (Player player : players) {
			System.out.println(player + "\t" + board.getSquare(player.getPosition()) + "\t$" + player.getCash() + "\t"
					+ player.getDeeds());
		}
	}

}
