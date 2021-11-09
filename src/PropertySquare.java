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
		int price = titleDeed.getPrice();
		titleDeed.setOwner(buyer);
		buyer.addDeed(titleDeed);
		buyer.deductCash(price);
		seller.removeDeed(titleDeed);
		seller.addCash(price);

		System.out.println(buyer + " bought " + titleDeed + " for $" + price + " from " + seller + ".");

	}
}
