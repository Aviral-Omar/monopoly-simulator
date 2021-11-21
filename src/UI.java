import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Button viewPrevious;
	Button viewNext;
	Label logNumberLabel;
	VBox logPanel;

	private int tempCount;
	private int numberOfPlayers;
	private int roundNumber;
	private int totalRounds;
	private ArrayList<Player> players;
	private ArrayList<Label> stateLabels;
	private final ArrayList<ArrayList<String>> roundLog;
	private Game game;
	private Board board;

	public UI(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryHBox = new HBox(8);
		primaryVBox = new VBox(8);
		viewPrevious = new Button("Previous");
		viewNext = new Button("Next");
		logPanel = new VBox();
		roundNumber = 0;
		totalRounds = 0;
		roundLog = new ArrayList<ArrayList<String>>();
		roundLog.add(new ArrayList<String>());
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
		Scene primaryScene = new Scene(sPane, 1200, 820);

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
		logNumberLabel = new Label(roundNumber + "/" + totalRounds);
		Button simulateRound = new Button("Simulate Round");

		viewPrevious.setOnAction(e -> updateLogPanel(roundNumber - 1));
		viewNext.setOnAction(e -> updateLogPanel(roundNumber + 1));

		viewPrevious.setDisable(true);
		viewNext.setDisable(true);

		simulateRound.setOnAction(e -> {
			game.simulateRound();
			game.displayState();
			updateState();
			updateLogPanel(roundNumber);
		});

		buttonBar.getChildren().addAll(viewPrevious, logNumberLabel, viewNext, simulateRound);

		primaryVBox.getChildren().addAll(gp, buttonBar, logPanel);
		primaryHBox.getChildren().add(primaryVBox);
	}

	public void updateState() {
		int c = 0;
		for (Player p : players) {
			stateLabels.get(c * 4 + 1).setText(board.getSquare(p.getPosition()).toString());
			stateLabels.get(c * 4 + 2).setText("$" + String.valueOf(p.getCash()));
			stateLabels.get(c * 4 + 3).setText(p.getDeeds());
			c++;
		}
		totalRounds++;
		roundNumber = totalRounds;
		logNumberLabel.setText(roundNumber + "/" + totalRounds);
		roundLog.add(new ArrayList<String>());
	}

	public void log(String line) {
		roundLog.get(totalRounds).add(line);
		System.out.println(line);
	}

	public void updateLogPanel(int roundNumber) {
		this.roundNumber = roundNumber;
		if (roundNumber == 0 || roundNumber == 1) {
			viewPrevious.setDisable(true);
		} else {
			viewPrevious.setDisable(false);
		}
		if (roundNumber == 0 || roundNumber == totalRounds) {
			viewNext.setDisable(true);
		} else {
			viewNext.setDisable(false);
		}
		logNumberLabel.setText(roundNumber + "/" + totalRounds);
		logPanel.getChildren().clear();
		for (String line : roundLog.get(roundNumber - 1)) {
			logPanel.getChildren().add(new Label(line));
		}
	}
}
