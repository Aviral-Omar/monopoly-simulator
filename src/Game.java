import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private final Board board;
	private final Bank bank;
	private final ArrayList<Player> players;

	Game(int numberOfPlayers) {
		// TODO input player names
		players = new ArrayList<Player>(numberOfPlayers);
		System.out.println("Enter name of each player");
		Scanner in = new Scanner(System.in);

		// Setting player names automatically
		// Giving each player $1500 and remaining to bank
		// *********(Edit-Changed to players name)*********
		for (int i = 0; i < numberOfPlayers; i++) {
			players.set(i, new Player(in.nextLine(), 1500));
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
		// TODO Add all actions
		// TODO Add game end condition
		if (square instanceof GoSquare) {

			player.addCash(200);

			System.out.println(player + " gets $200 for landing on Go.");

		} else if (square instanceof TaxSquare) {

			int amount = ((TaxSquare) square).getAmount();

			player.deductCash(amount);
			bank.addCash(amount);

			System.out.println(player + " paid $" + amount + " to bank.");

		} else if (square instanceof PropertySquare) {
			// TODO Add rent collection, bankruptcy check, possibly auction

			// Rent is 4 * dieTotal if 1 is owned and 10 * dieTotal if both are owned
			if (square instanceof UtilitySquare) {

				UtilitySquare sq = (UtilitySquare) square;
				Actor owner = sq.getOwner();

				if (owner == bank) {

					sq.buyProperty(player, bank);

				} else if (owner != player) {
					if (((Player) owner).getUtilitiesOwned() == 1) {
						player.deductCash(4 * dieTotal);
						owner.addCash(4 * dieTotal);
					} else {
						player.deductCash(10 * dieTotal);
						owner.addCash(10 * dieTotal);
					}
				}

			}
		}
	}

	public void simulateTurn(int playerNumber) {
		Player player = players.get(playerNumber);
		// TODO Add jail functionality
		// TODO Add doubles roll functionality
		// TODO Add passing GO functionality
		int dieOne = rollDie();
		int dieTwo = rollDie();

		System.out.println(player + " rolled " + (dieOne + dieTwo) + ".");

		player.setPosition(player.getPosition() + dieOne + dieTwo);
		Square square = board.getSquare(player.getPosition());

		System.out.println(player + " landed on " + square + ",");

		action(square, player, dieOne + dieTwo);
	}
}
