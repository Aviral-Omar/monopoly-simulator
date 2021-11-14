import java.util.ArrayList;

public class Player extends Actor {

	private final String name;
	private int position;
	private boolean inJail;
	private int turnsInJail;
	private ChanceCard chanceGetOutOfJail;
	private CommunityChestCard communityGetOutOfJail;

	Player(String name, int cash) {
		super(cash);
		this.name = name;
		position = 0;
		titleDeeds = new ArrayList<TitleDeed>();
		inJail = false;
		turnsInJail = 0;
		chanceGetOutOfJail = null;
		communityGetOutOfJail = null;
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

	public int getStationsOwned() {
		int stationsOwned = 0;
		for (TitleDeed t : titleDeeds) {
			if (t instanceof StationDeed) {
				stationsOwned++;
			}
		}
		return stationsOwned;
	}

	public boolean ownsAllOfColour(Colours colour) {
		int n = 0;
		for (TitleDeed t : titleDeeds) {
			if (t instanceof RealEstateDeed) {
				Colours c = ((RealEstateDeed) t).getColour();
				if (c == colour) {
					n++;
				}
			}
		}
		if (colour == Colours.Brown || colour == Colours.DarkBlue) {
			return n == 2;
		} else {
			return n == 3;
		}
	}

	public String getDeeds() {
		String deeds = "";
		for (TitleDeed t : titleDeeds) {
			deeds += t + "\t";
		}
		return deeds;
	}

	public void setChanceGetOutOfJail(ChanceCard chanceGetOutOfJail) {
		this.chanceGetOutOfJail = chanceGetOutOfJail;
	}

	public void setCommunityGetOutOfJail(CommunityChestCard communityGetOutOfJail) {
		this.communityGetOutOfJail = communityGetOutOfJail;
	}

	public void sendToJail() {
		setPosition(10);
		inJail = true;
		turnsInJail = 0;
	}

	public boolean isInJail() {
		return inJail;
	}

	public int getTurnsInJail() {
		return turnsInJail;
	}

	public void setTurnsInJail(int turnsInJail) {
		this.turnsInJail = turnsInJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public Card checkGetOutOfJail() {
		Card getOutOfJail;
		if (chanceGetOutOfJail != null) {
			getOutOfJail = chanceGetOutOfJail;
			chanceGetOutOfJail = null;
			setInJail(false);
			setTurnsInJail(0);
		} else {
			getOutOfJail = communityGetOutOfJail;
			communityGetOutOfJail = null;
			setInJail(false);
			setTurnsInJail(0);
		}
		return getOutOfJail;
	}

	@Override
	public String toString() {
		return name;
	}

}
