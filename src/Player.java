public class Player extends Actor {

	private final String name;
	private int position;

	Player(String name, int cash) {
		super(cash);
		this.name = name;
		position = 0;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getUtilitiesOwned() {
		int utilitiesOwned = 0;
		for (TitleDeed t : titleDeeds) {
			if (t instanceof UtilityDeed) {
				utilitiesOwned++;
			}
		}
		return utilitiesOwned;
	}

	@Override
	public String toString() {
		return name;
	}

}
