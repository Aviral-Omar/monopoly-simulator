public class PropertySquare extends Square {
	private final TitleDeed titleDeed;

	PropertySquare(String name, TitleDeed titleDeed) {
		super(name);
		this.titleDeed = titleDeed;
	}
}
