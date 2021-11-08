public abstract class TitleDeed {
	private final String name;
	private Actor owner;
	private final int price;
	private final int mortgageValue;

	TitleDeed(String name, Actor owner, int price, int mortgageValue) {
		this.name = name;
		this.owner = owner;
		this.price = price;
		this.mortgageValue = mortgageValue;
	}

	public String getName() {
		return name;
	}

	public Actor getOwner() {
		return owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}
}
