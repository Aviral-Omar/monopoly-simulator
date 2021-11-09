import java.util.ArrayList;

public class Bank extends Actor {

	Bank(int cash) {
		super(cash);

		titleDeeds = new ArrayList<TitleDeed>(28);

		titleDeeds.add(new RealEstateDeed("Mediterranean Avenue", this, 60, 30, Colours.Brown, 50,
				new Integer[] { 2, 10, 30, 90, 160, 250 }));
		titleDeeds.add(new RealEstateDeed("Baltic Avenue", this, 60, 30, Colours.Brown, 50,
				new Integer[] { 4, 20, 60, 180, 320, 450 }));
		titleDeeds.add(new StationDeed("Reading Railroad", this, 200));
		titleDeeds.add(new RealEstateDeed("Oriental Avenue", this, 100, 50, Colours.LightBlue, 50,
				new Integer[] { 6, 30, 90, 270, 400, 550 }));
		titleDeeds.add(new RealEstateDeed("Vermont Avenue", this, 100, 50, Colours.LightBlue, 50,
				new Integer[] { 6, 30, 90, 270, 400, 550 }));
		titleDeeds.add(new RealEstateDeed("Connecticut Avenue", this, 120, 60, Colours.LightBlue, 50,
				new Integer[] { 8, 40, 100, 300, 450, 600 }));
		titleDeeds.add(new RealEstateDeed("St. Charles Place", this, 140, 70, Colours.Pink, 100,
				new Integer[] { 10, 50, 150, 450, 625, 750 }));
		titleDeeds.add(new UtilityDeed("Electric Company", this, 150));
		titleDeeds.add(new RealEstateDeed("States Avenue", this, 140, 70, Colours.Pink, 100,
				new Integer[] { 10, 50, 150, 450, 625, 750 }));
		titleDeeds.add(new RealEstateDeed("Virginia Avenue", this, 160, 80, Colours.Pink, 100,
				new Integer[] { 12, 60, 180, 500, 700, 900 }));
		titleDeeds.add(new StationDeed("Pennsylvania Railroad", this, 200));
		titleDeeds.add(new RealEstateDeed("St. James Place", this, 180, 90, Colours.Orange, 100,
				new Integer[] { 14, 70, 200, 550, 750, 950 }));
		titleDeeds.add(new RealEstateDeed("Tennessee Avenue", this, 180, 90, Colours.Orange, 100,
				new Integer[] { 14, 70, 200, 550, 750, 950 }));
		titleDeeds.add(new RealEstateDeed("New York Avenue", this, 200, 100, Colours.Orange, 100,
				new Integer[] { 16, 80, 220, 600, 900, 1000 }));
		titleDeeds.add(new RealEstateDeed("Kentucky Avenue", this, 220, 110, Colours.Red, 150,
				new Integer[] { 18, 90, 250, 700, 875, 1050 }));
		titleDeeds.add(new RealEstateDeed("Indiana Avenue", this, 220, 110, Colours.Red, 150,
				new Integer[] { 18, 90, 250, 700, 875, 1050 }));
		titleDeeds.add(new RealEstateDeed("Illinois Avenue", this, 240, 120, Colours.Red, 150,
				new Integer[] { 20, 100, 300, 750, 925, 1100 }));
		titleDeeds.add(new StationDeed("B. & O. Railroad", this, 200));
		titleDeeds.add(new RealEstateDeed("Atlantic Avenue", this, 260, 130, Colours.Yellow, 150,
				new Integer[] { 22, 110, 330, 800, 975, 1150 }));
		titleDeeds.add(new RealEstateDeed("Ventnor Avenue", this, 260, 130, Colours.Yellow, 150,
				new Integer[] { 22, 110, 330, 800, 975, 1150 }));
		titleDeeds.add(new UtilityDeed("Water Works", this, 150));
		titleDeeds.add(new RealEstateDeed("Marvin Gardens", this, 280, 140, Colours.Yellow, 150,
				new Integer[] { 24, 120, 360, 850, 1025, 1200 }));
		titleDeeds.add(new RealEstateDeed("Pacific Avenue", this, 300, 150, Colours.Green, 200,
				new Integer[] { 26, 130, 390, 900, 1100, 1275 }));
		titleDeeds.add(new RealEstateDeed("North Carolina Avenue", this, 300, 150, Colours.Green, 200,
				new Integer[] { 26, 130, 390, 900, 1100, 1275 }));
		titleDeeds.add(new RealEstateDeed("Pennsylvania Avenue", this, 320, 160, Colours.Green, 200,
				new Integer[] { 28, 150, 450, 1000, 1200, 1400 }));
		titleDeeds.add(new StationDeed("Short Line", this, 200));
		titleDeeds.add(new RealEstateDeed("Park Place", this, 350, 175, Colours.DarkBlue, 200,
				new Integer[] { 35, 175, 500, 1100, 1300, 1500 }));
		titleDeeds.add(new RealEstateDeed("Boardwalk", this, 400, 200, Colours.DarkBlue, 200,
				new Integer[] { 50, 200, 600, 1400, 1700, 2000 }));

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
