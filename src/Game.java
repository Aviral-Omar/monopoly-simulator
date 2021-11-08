import java.util.ArrayList;

public class Game {

	private final Board board;
	private final Bank bank;
	private final ArrayList<Player> players;

	Game(int numberOfPlayers) {
		// TODO input player names
		players = new ArrayList<Player>(numberOfPlayers);

		// Setting player names automatically
		// Giving each player $1500 and remaining to bank
		for (int i = 0; i < numberOfPlayers; i++) {
			players.set(i, new Player("Player " + (i + 1), 1500));
		}

		bank = new Bank(20580 - numberOfPlayers * 1500);
		board = new Board(bank);
	}

	private int rollDie() {
		return (int) (Math.random() * 6 + 1);
	}

	private void action(Square square, Player player, int dieTotal) {
		String name = player.getName();
		// Enhanced switch statement
		// TODO Add all actions
		// TODO Add game end condition
		if (square instanceof GoSquare) {

			player.addCash(200);

			System.out.println(name + " gets $200 for landing on Go.");

		} else if (square instanceof TaxSquare) {

			int amount = ((TaxSquare) square).getAmount();

			player.deductCash(amount);
			bank.addCash(amount);

			System.out.println(name + " paid $" + amount + " to bank.");

		} else if (square instanceof PropertySquare) {
			// TODO Add rent collection, bankruptcy check, possibly auction
			if (square instanceof UtilitySquare) {

				UtilitySquare sq = (UtilitySquare) square;
				if (sq.getOwner() == bank) {
					sq.buyProperty(player, bank);
				} else if (sq.getOwner() != player) {
					// TODO collect rent
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

		System.out.println(player.getName() + " rolled " + (dieOne + dieTwo) + ".");

		player.setPosition(player.getPosition() + dieOne + dieTwo);
		Square square = board.getSquare(player.getPosition());

		System.out.println(player.getName() + " landed on " + square.getName() + ",");

		action(square, player, dieOne + dieTwo);
	}
}
