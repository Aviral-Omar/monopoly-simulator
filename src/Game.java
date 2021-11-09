import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private final Board board;
	private final Bank bank;
	private final ArrayList<Player> players;

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

		in.close();
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
