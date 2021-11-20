import javafx.application.Application;
import javafx.stage.Stage;

public class Monopoly extends Application {

	private UI ui;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ui = new UI(primaryStage);

		System.out.println("Game Starting");

		int numberOfPlayers = ui.getNumberOfPlayers();

		Game game = new Game(ui, numberOfPlayers);

		ui.setGame(game);
		ui.initState();
		ui.addSimulationPanel();

		primaryStage.show();

		// TODO control number of rounds
		// for (int round = 0; round < 20; round++) {
		// for (int turn = 0; turn < numberOfPlayers; turn++) {
		// game.simulateTurn(turn);
		// }
		// game.displayState();
		// ui.updateState();
		// }
	}

}
