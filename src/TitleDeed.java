public abstract class TitleDeed {
	private Actor owner;
	private final int price;
	private final int mortgageValue;

	TitleDeed(Actor owner, int price, int mortgageValue) {
		this.owner = owner;
		this.price = price;
		this.mortgageValue = mortgageValue;
	}
}
