import java.util.Scanner;

public class Monopoly {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Game Starting.");

		System.out.print("Enter number of players: ");

		int numberOfPlayers = in.nextInt();

		Game game = new Game(numberOfPlayers);

		for (int round = 0; round < 8; round++) {
			for (int turn = 0; turn < numberOfPlayers; turn++) {
				game.simulateTurn(turn);
			}
			game.displayState();
		}

		in.close();
	}

}
