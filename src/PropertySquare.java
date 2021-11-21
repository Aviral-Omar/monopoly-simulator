public abstract class PropertySquare extends Square {
	protected final TitleDeed titleDeed;

	PropertySquare(String name, TitleDeed titleDeed) {
		super(name);
		this.titleDeed = titleDeed;
	}

	public Actor getOwner() {
		return titleDeed.getOwner();
	}

	public void buyProperty(Actor buyer, Actor seller, UI ui) {
		int price = titleDeed.getPrice();
		if (buyer.getCash() > price) {
			titleDeed.setOwner(buyer);
			buyer.addDeed(titleDeed);
			buyer.deductCash(price);
			seller.removeDeed(titleDeed);
			seller.addCash(price);

			ui.log(buyer + " bought " + titleDeed + " for $" + price + " from " + seller + ".");
		} else {
			ui.log(buyer + " has insufficient cash for " + getName() + ".");
		}

	}
}
