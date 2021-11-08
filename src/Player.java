public class Player extends Actor {

	private final String name;
	private int position;

	Player(String name, int cash) {
		super(cash);
		this.name = name;
		// TODO
		position = 0;
	}
}
