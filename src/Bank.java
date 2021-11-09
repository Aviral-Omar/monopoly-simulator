import java.util.ArrayList;

public class Bank extends Actor {

	Bank(int cash) {
		super(cash);
		titleDeeds = new ArrayList<TitleDeed>(28);
		// TODO Fill Title Deeds
		titleDeeds.set(0, new RealEstateDeed("Mediterranean Avenue", this, 60, 30, Colours.Brown, 50,
				new Integer[] { 2, 10, 30, 90, 160, 250 }));
	}

	public TitleDeed searchDeed(String name) {
		for (TitleDeed t : titleDeeds) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Bank";
	}

}
