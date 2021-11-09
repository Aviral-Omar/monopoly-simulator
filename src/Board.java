import java.util.ArrayList;

public class Board {

	private final ArrayList<Square> squares;

	Board(Bank bank) {
		squares = new ArrayList<Square>(40);

		squares.add(new GoSquare());
		squares.add(new RealEstateSquare("Mediterranean Avenue", bank.searchDeed("Mediterranean Avenue")));
		squares.add(new CommunityChestSquare());
		squares.add(new RealEstateSquare("Baltic Avenue", bank.searchDeed("Baltic Avenue")));
		squares.add(new TaxSquare("Income Tax", 200));
		squares.add(new StationSquare("Reading Railroad", bank.searchDeed("Reading Railroad")));
		squares.add(new RealEstateSquare("Oriental Avenue", bank.searchDeed("Oriental Avenue")));
		squares.add(new ChanceSquare());
		squares.add(new RealEstateSquare("Vermont Avenue", bank.searchDeed("Vermont Avenue")));
		squares.add(new RealEstateSquare("Connecticut Avenue", bank.searchDeed("Connecticut Avenue")));
		squares.add(new JailSquare());
		squares.add(new RealEstateSquare("St. Charles Place", bank.searchDeed("St. Charles Place")));
		squares.add(new UtilitySquare("Electric Company", bank.searchDeed("Electric Company")));
		squares.add(new RealEstateSquare("States Avenue", bank.searchDeed("States Avenue")));
		squares.add(new RealEstateSquare("Virginia Avenue", bank.searchDeed("Virginia Avenue")));
		squares.add(new StationSquare("Pennsylvania Railroad", bank.searchDeed("Pennsylvania Railroad")));
		squares.add(new RealEstateSquare("St. James Place", bank.searchDeed("St. James Place")));
		squares.add(new CommunityChestSquare());
		squares.add(new RealEstateSquare("Tennessee Avenue", bank.searchDeed("Tennessee Avenue")));
		squares.add(new RealEstateSquare("New York Avenue", bank.searchDeed("New York Avenue")));
		squares.add(new FreeParkingSquare());
		squares.add(new RealEstateSquare("Kentucky Avenue", bank.searchDeed("Kentucky Avenue")));
		squares.add(new ChanceSquare());
		squares.add(new RealEstateSquare("Indiana Avenue", bank.searchDeed("Indiana Avenue")));
		squares.add(new RealEstateSquare("Illinois Avenue", bank.searchDeed("Illinois Avenue")));
		squares.add(new StationSquare("B. & O. Railroad", bank.searchDeed("B. & O. Railroad")));
		squares.add(new RealEstateSquare("Atlantic Avenue", bank.searchDeed("Atlantic Avenue")));
		squares.add(new RealEstateSquare("Ventnor Avenue", bank.searchDeed("Ventnor Avenue")));
		squares.add(new UtilitySquare("Water Works", bank.searchDeed("Water Works")));
		squares.add(new RealEstateSquare("Marvin Gardens", bank.searchDeed("Marvin Gardens")));
		squares.add(new GoToJailSquare());
		squares.add(new RealEstateSquare("Pacific Avenue", bank.searchDeed("Pacific Avenue")));
		squares.add(new RealEstateSquare("North Carolina Avenue", bank.searchDeed("North Carolina Avenue")));
		squares.add(new CommunityChestSquare());
		squares.add(new RealEstateSquare("Pennsylvania Avenue", bank.searchDeed("Pennsylvania Avenue")));
		squares.add(new StationSquare("Short Line", bank.searchDeed("Short Line")));
		squares.add(new ChanceSquare());
		squares.add(new RealEstateSquare("Park Place", bank.searchDeed("Park Place")));
		squares.add(new TaxSquare("Luxury Tax", 100));
		squares.add(new RealEstateSquare("Boardwalk", bank.searchDeed("Boardwalk")));
	}

	public Square getSquare(int position) {
		return squares.get(position);
	}

}
