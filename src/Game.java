import java.util.ArrayList;
// import java.util.Scanner;

public class Game {

	private final Board board;
	private final Bank bank;
	private final ArrayList<Player> players;

	Game(int numberOfPlayers) {
		// Scanner in = new Scanner(System.in);
		players = new ArrayList<Player>(numberOfPlayers);
		for (int i = 0; i < numberOfPlayers; i++) {
			players.set(i, new Player("Player " + (i + 1), 1500));
		}
		bank = new Bank(20580 - numberOfPlayers * 1500);
		board = new Board(bank);
	}
}
