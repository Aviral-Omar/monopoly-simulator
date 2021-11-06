public class RealEstateDeed extends TitleDeed {

	private final String colour;
	private final int costOfHouse;
	private final int costOfHotel;

	RealEstateDeed(Actor owner, int price, int mortgageValue, String colour, int costOfHouse, int costOfHotel) {
		super(owner, price, mortgageValue);
		this.colour = colour;
		this.costOfHouse = costOfHouse;
		this.costOfHotel = costOfHotel;
	}
}
