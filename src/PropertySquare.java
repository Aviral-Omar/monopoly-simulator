public abstract class PropertySquare extends Square {
	private final TitleDeed titleDeed;

	PropertySquare(String name, TitleDeed titleDeed) {
		super(name);
		this.titleDeed = titleDeed;
	}

	public Actor getOwner() {
		return titleDeed.getOwner();
	}

	public void buyProperty(Actor buyer, Actor seller) {
		titleDeed.setOwner(buyer);
		buyer.addDeed(titleDeed);
		seller.removeDeed(titleDeed);
	}
}
