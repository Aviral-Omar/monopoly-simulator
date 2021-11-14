import java.util.ArrayList;
import java.util.Scanner;

//TODO Update player UML

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

	private void useGetOutOfJailCard(Card getOutOfJail, Player player) {
		if (getOutOfJail instanceof CommunityChestCard) {
			communityChestDeck.insertAtBottom((CommunityChestCard) getOutOfJail);
			System.out.println(player + " used Community Get Out Of Jail card.");
		} else if (getOutOfJail instanceof ChanceCard) {
			chanceDeck.insertAtBottom((ChanceCard) getOutOfJail);
			System.out.println(player + " used Chance Get Out Of Jail card.");
		}

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
			player.setCommunityGetOutOfJail(card);
		}
		case GoToJail -> {
			player.sendToJail();

			Card getOutOfJail = player.checkGetOutOfJail();

			if (getOutOfJail != null) {
				useGetOutOfJailCard(getOutOfJail, player);
			}
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
			Square sq = board.getSquare(player.getPosition());
			action(sq, player, 0);
		}
		case AdvanceToGo -> {
			player.setPosition(0);
			Square sq = board.getSquare(player.getPosition());
			action(sq, player, 0);
		}
		case AdvanceToIllinoisAvenue -> {
			if (player.getPosition() == 36) {
				// Assuming position can be only 3 chance squares.
				player.addCash(200);
				bank.deductCash(200);
				System.out.println(player + " collects $200 for passing GO.");
			}
			player.setPosition(24);
			Square sq = board.getSquare(player.getPosition());
			action(sq, player, 0);
		}
		case AdvanceToStCharlesPlace -> {
			if (player.getPosition() != 7) {
				// Assuming position can be only 3 chance squares.
				player.addCash(200);
				bank.deductCash(200);
				System.out.println(player + " collects $200 for passing GO.");
			}
			player.setPosition(11);

			Square sq = board.getSquare(player.getPosition());

			action(sq, player, 0);
		}
		case AdvanceToNearestRailroad -> {
			if (player.getPosition() == 7) {
				System.out.println(player + " advanced to Pennsylvania Railroad.");
				player.setPosition(15);
			} else if (player.getPosition() == 22) {
				System.out.println(player + " advanced to B&O Railroad.");
				player.setPosition(25);
			} else if (player.getPosition() == 36) {
				player.setPosition(5);
				System.out.println(player + " advanced to Reading Railroad.");
				player.addCash(200);
				bank.deductCash(200);
				System.out.println(player + " collects $200 for passing GO.");
			}

			StationSquare sq = (StationSquare) board.getSquare(player.getPosition());
			Actor owner = sq.getOwner();
			if (owner == bank)
				sq.buyProperty(player, bank);
			else if (owner != player) {
				int rent = 50 * (int) Math.pow(2, ((Player) owner).getStationsOwned() - 1);
				player.deductCash(rent);
				owner.addCash(rent);
				System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
			}
		}
		case AdvanceToNearestUtility -> {
			if (player.getPosition() == 7) {
				System.out.println(player + " advanced to Electric Company.");
				player.setPosition(12);
			} else if (player.getPosition() == 22) {
				System.out.println(player + " advanced to Water Works.");
				player.setPosition(28);
			} else if (player.getPosition() == 36) {
				System.out.println(player + " advanced to Electric Company.");
				player.setPosition(12);
				player.addCash(200);
				bank.deductCash(200);
				System.out.println(player + " collects $200 for passing GO.");
			}

			UtilitySquare sq = (UtilitySquare) board.getSquare(player.getPosition());
			Actor owner = sq.getOwner();
			if (owner == bank)
				sq.buyProperty(player, bank);
			else if (owner != player) {
				int dieScore = rollDie() + rollDie();
				int rent = 10 * dieScore;
				player.deductCash(rent);
				owner.addCash(rent);

				System.out.println(
						player + " rolled a " + dieScore + " and paid $" + rent + " as rent to " + owner + ".");
			}
		}
		case ReceiveDividend -> {
			player.addCash(50);
			bank.deductCash(50);
		}
		case GetOutOfJail -> {
			player.setChanceGetOutOfJail(card);
		}
		case MoveBack -> {
			player.setPosition(player.getPosition() - 3);
			System.out.println(player + " landed on " + board.getSquare(player.getPosition()) + ".");
		}
		case GoToJail -> {
			player.sendToJail();

			Card getOutOfJail = player.checkGetOutOfJail();

			if (getOutOfJail != null) {
				useGetOutOfJailCard(getOutOfJail, player);
			}
		}
		case PayForRepairs -> {
			// TODO Add house count feature.
		}
		case PaySpeedingFine -> {
			player.deductCash(15);
			bank.addCash(15);
		}
		case AdvanceToReadingRailroad -> {
			player.setPosition(5);
			System.out.println(player + " advanced to Reading Railroad.");
			player.deductCash(200);
			bank.addCash(200);
			System.out.println(player + " collects $200 for passing GO.");

			StationSquare sq = (StationSquare) board.getSquare(player.getPosition());
			Actor owner = sq.getOwner();
			if (owner == bank)
				sq.buyProperty(player, bank);
			else if (owner != player) {
				int rent = 50 * (int) Math.pow(2, ((Player) owner).getStationsOwned() - 1);
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

	private void action(Square square, Player player, int dieTotal) {
		// TODO Add houses functionality
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
			// TODO Check sufficient balance, bankruptcy check, possibly auction

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

					int rent = sq.getRent(player.ownsAllOfColour(sq.getColour()));

					player.deductCash(rent);
					owner.addCash(rent);

					System.out.println(player + " paid $" + rent + " as rent to " + owner + ".");
				}
			}
		} else if (square instanceof CommunityChestSquare) {

			CommunityChestCard card = (CommunityChestCard) communityChestDeck.pickFromTop();
			System.out.println(player + " drew a Community Chest Card.");
			System.out.println(CommunityChestDeck.getMessage(card.getAction(), player));
			communityChestAction(player, card);

			if (card.getAction() != CommunityChestActions.GetOutOfJail) {
				communityChestDeck.insertAtBottom(card);
			}

		} else if (square instanceof ChanceSquare) {

			ChanceCard card = (ChanceCard) chanceDeck.pickFromTop();
			System.out.println(player + " drew a Chance Card.");
			System.out.println(ChanceDeck.getMessage(card.getAction(), player));
			chanceAction(square, player, dieTotal, card);

			if (card.getAction() != ChanceActions.GetOutOfJail) {
				chanceDeck.insertAtBottom(card);
			}

		} else if (square instanceof GoToJailSquare) {

			player.sendToJail();
			System.out.println(player + " was sent to jail.");
		}
	}

	private int rollDie() {
		return (int) (Math.random() * 6 + 1);
	}

	public void simulateTurn(int playerNumber) {
		Player player = players.get(playerNumber);

		int numberOfRolls = 0;
		int dieOne, dieTwo;

		do {

			dieOne = rollDie();
			dieTwo = rollDie();

			numberOfRolls++;

			System.out.println(player + " rolled " + dieOne + " & " + dieTwo + ".");

			if (dieOne == dieTwo && numberOfRolls == 3) {
				player.sendToJail();
				System.out.println(player + " was sent to jail for rolling doubles thrice.");

				Card getOutOfJail = player.checkGetOutOfJail();

				if (getOutOfJail != null) {
					useGetOutOfJailCard(getOutOfJail, player);
				}

				break;
			}

			if (player.isInJail()) {
				if (dieOne != dieTwo && player.getTurnsInJail() < 2) {
					player.setTurnsInJail(player.getTurnsInJail() + 1);
					System.out.println(player + " stayed in Jail.");
				} else {
					if (dieOne != dieTwo) {
						player.deductCash(50);
						bank.addCash(50);
						System.out.println(player + " paid $50 as fine to get out of jail.");
					} else {
						System.out.println(player + " rolled doubles to get out of jail.");
					}
					player.setInJail(false);
					player.setTurnsInJail(0);

					player.setPosition(player.getPosition() + dieOne + dieTwo);

					Square square = board.getSquare(player.getPosition());

					System.out.println(player + " landed on " + square + ".");

					action(square, player, dieOne + dieTwo);
				}
				break;
			}

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
		} while (dieOne == dieTwo);

	}

	public void displayState() {
		System.out.println("Game State:");
		for (Player player : players) {
			System.out.printf("%-12s%-24s$%-8s\t", player, board.getSquare(player.getPosition()), player.getCash());
			System.out.println(player.getDeeds());
		}
	}

}
