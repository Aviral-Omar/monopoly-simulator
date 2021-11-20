import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI {
	private final Stage primaryStage;
	private final HBox primaryHBox;
	private final VBox primaryVBox;

	private int tempCount;
	private int numberOfPlayers;
	private int roundNumber;
	private int totalRounds;
	private ArrayList<Player> players;
	private ArrayList<Label> stateLabels;
	private Game game;
	private Board board;

	public UI(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryHBox = new HBox(8);
		primaryVBox = new VBox(8);
		roundNumber = 0;
		totalRounds = 0;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void initState() {
		primaryStage.setTitle("Monopoly");

		ImageView iv = new ImageView();
		iv.setImage(new Image("Board.jpeg", 800, 800, true, true));

		primaryHBox.getChildren().add(iv);

		ScrollPane sPane = new ScrollPane(primaryHBox);
		Scene primaryScene = new Scene(sPane, 1200, 800);

		primaryStage.setScene(primaryScene);
	}

	public int getNumberOfPlayers() {
		Stage numberInput = new Stage();
		numberInput.setTitle("Number of Players");

		VBox vbox = new VBox(8);
		Label label = new Label("Enter number of players");
		TextField tf = new TextField();
		tf.setOnAction(e -> numberInput.close());
		vbox.getChildren().addAll(label, tf);

		numberInput.setScene(new Scene(vbox));
		numberInput.initModality(Modality.APPLICATION_MODAL);
		numberInput.showAndWait();

		numberOfPlayers = Integer.valueOf(tf.getText());
		return numberOfPlayers;
	}

	public void addSimulationPanel() {
		stateLabels = new ArrayList<Label>(numberOfPlayers * 3);
		GridPane gp = new GridPane();
		int c = 1;
		for (Player p : players) {
			stateLabels.add(new Label(p.getName()));
			stateLabels.add(new Label(board.getSquare(p.getPosition()).toString()));
			stateLabels.add(new Label("$" + String.valueOf(p.getCash())));
			stateLabels.add(new Label(p.getDeeds()));
			gp.add(stateLabels.get((c - 1) * 4), 1, c);
			gp.add(stateLabels.get((c - 1) * 4 + 1), 2, c);
			gp.add(stateLabels.get((c - 1) * 4 + 2), 3, c);
			gp.add(stateLabels.get((c - 1) * 4 + 3), 4, c);
			c++;
		}

		HBox buttonBar = new HBox();
		// TODO set button action
		Button viewPrevious = new Button("Previous");
		Label logNumberLabel = new Label(roundNumber + "/" + totalRounds);
		Button simulateRound = new Button("Simulate Round");

		simulateRound.setOnAction(e -> {
			game.simulateRound();
			game.displayState();
			updateState();
		});

		buttonBar.getChildren().addAll(viewPrevious, logNumberLabel, simulateRound);

		primaryVBox.getChildren().addAll(gp, buttonBar);
		primaryHBox.getChildren().add(primaryVBox);
	}

	public void updateState() {
		int c = 1;
		for (Player p : players) {
			stateLabels.get((c - 1) * 4 + 1).setText(board.getSquare(p.getPosition()).toString());
			stateLabels.get((c - 1) * 4 + 2).setText("$" + String.valueOf(p.getCash()));
			stateLabels.get((c - 1) * 4 + 3).setText(p.getDeeds());
			c++;
		}
	}

	public void getNamesOfPlayers(int numberOfPlayers, ArrayList<Player> players) {
		this.players = players;

		Stage nameInput = new Stage();
		nameInput.setTitle("Name of Players");

		Label label = new Label();
		TextField tf = new TextField();
		VBox vbox = new VBox(8);
		vbox.getChildren().addAll(label, tf);

		tempCount = 0;

		label.setText("Enter name of Player " + (tempCount + 1));
		tf.setOnAction(e -> {
			players.add(new Player(tf.getText(), 1500));
			tempCount++;
			if (tempCount == numberOfPlayers) {
				nameInput.close();
			} else {
				label.setText("Enter name of Player " + (tempCount + 1));
				tf.clear();
			}
		});

		nameInput.setScene(new Scene(vbox));
		nameInput.initModality(Modality.APPLICATION_MODAL);
		nameInput.showAndWait();
	}
}
