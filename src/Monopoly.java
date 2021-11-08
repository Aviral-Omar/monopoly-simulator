import java.util.Scanner;

public class Monopoly {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Game Starting.");

		System.out.print("Enter number of players: ");

		Game game = new Game(in.nextInt());
		in.close();
	}

}
