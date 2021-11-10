import java.util.ArrayList;

public abstract class Actor {
	private int cash;
	protected ArrayList<TitleDeed> titleDeeds;
	private int housesOwned;
	private int hotelsOwned;

	Actor(int cash) {
		this.cash = cash;
	}

	public int getCash() {
		return cash;
	}

	public void addCash(int amount) {
		cash += amount;
	}

	public void deductCash(int amount) {
		cash -= amount;
	}

	public void addDeed(TitleDeed titleDeed) {
		titleDeeds.add(titleDeed);
	}

	public void removeDeed(TitleDeed titleDeed) {
		titleDeeds.remove(titleDeed);
	}
}
