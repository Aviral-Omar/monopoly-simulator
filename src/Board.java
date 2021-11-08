import java.util.ArrayList;

public class Board {

	private final ArrayList<Square> squares;

	Board(Bank bank) {
		squares = new ArrayList<Square>(40);

		squares.set(0, new GoSquare("Go"));
		squares.set(1, new RealEstateSquare("Mediterranean Avenue", bank.searchDeed("Mediterranean Avenue")));
		squares.set(2, new CommunityChestSquare("Community Chest"));
		squares.set(3, new RealEstateSquare("Baltic Avenue", bank.searchDeed("Baltic Avenue")));
		squares.set(4, new TaxSquare("Income Tax", 200));
		squares.set(5, new StationSquare("Reading Railroad", bank.searchDeed("Reading Railroad")));
		squares.set(6, new RealEstateSquare("Oriental Avenue", bank.searchDeed("Oriental Avenue")));
		squares.set(7, new ChanceSquare("Chance"));
		squares.set(8, new RealEstateSquare("Vermont Avenue", bank.searchDeed("Vermont Avenue")));
		squares.set(9, new RealEstateSquare("Connecticut Avenue", bank.searchDeed("Connecticut Avenue")));
		squares.set(10, new JailSquare("Jail"));
		squares.set(11, new RealEstateSquare("St. Charles Place", bank.searchDeed("St. Charles Place")));
		squares.set(12, new UtilitySquare("Electric Company", bank.searchDeed("Electric Company")));
		squares.set(13, new RealEstateSquare("States Avenue", bank.searchDeed("States Avenue")));
		squares.set(14, new RealEstateSquare("Virginia Avenue", bank.searchDeed("Virginia Avenue")));
		squares.set(15, new StationSquare("Pennsylvania Railroad", bank.searchDeed("Pennsylvania Railroad")));
		squares.set(16, new RealEstateSquare("St. James Place", bank.searchDeed("St. James Place")));
		squares.set(17, new CommunityChestSquare("Community Chest"));
		squares.set(18, new RealEstateSquare("Tennessee Avenue", bank.searchDeed("Tennessee Avenue")));
		squares.set(19, new RealEstateSquare("New York Avenue", bank.searchDeed("New York Avenue")));
		squares.set(20, new FreeParkingSquare("Free Parking"));
		squares.set(21, new RealEstateSquare("Kentucky Avenue", bank.searchDeed("Kentucky Avenue")));
		squares.set(22, new ChanceSquare("Chance"));
		squares.set(23, new RealEstateSquare("Indiana Avenue", bank.searchDeed("Indiana Avenue")));
		squares.set(24, new RealEstateSquare("Illinois Avenue", bank.searchDeed("Illinois Avenue")));
		squares.set(25, new StationSquare("B. & O. Railroad", bank.searchDeed("B. & O. Railroad")));
		squares.set(26, new RealEstateSquare("Atlantic Avenue", bank.searchDeed("Atlantic Avenue")));
		squares.set(27, new RealEstateSquare("Ventnor Avenue", bank.searchDeed("Ventnor Avenue")));
		squares.set(28, new UtilitySquare("Water Works", bank.searchDeed("Water Works")));
		squares.set(29, new RealEstateSquare("Marvin Gardens", bank.searchDeed("Marvin Gardens")));
		squares.set(30, new GoToJailSquare("Go To Jail"));
		squares.set(31, new RealEstateSquare("Pacific Avenue", bank.searchDeed("Pacific Avenue")));
		squares.set(32, new RealEstateSquare("North Carolina Avenue", bank.searchDeed("North Carolina Avenue")));
		squares.set(33, new CommunityChestSquare("Community Chest"));
		squares.set(34, new RealEstateSquare("Pennsylvania Avenue", bank.searchDeed("Pennsylvania Avenue")));
		squares.set(35, new StationSquare("Short Line", bank.searchDeed("Short Line")));
		squares.set(36, new ChanceSquare("Chance"));
		squares.set(37, new RealEstateSquare("Park Place", bank.searchDeed("Park Place")));
		squares.set(38, new TaxSquare("Luxury Tax", 100));
		squares.set(39, new RealEstateSquare("Boardwalk", bank.searchDeed("Boardwalk")));
	}

	public Square getSquare(int position) {
		return squares.get(position);
	}

}
