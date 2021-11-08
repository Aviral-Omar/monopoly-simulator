import java.util.ArrayList;
import java.util.Arrays;

public class RealEstateDeed extends TitleDeed {

	private final Colours colour;
	private final int costOfHouse;

	private final ArrayList<Integer> rentLevels;

	RealEstateDeed(String name, Actor owner, int price, int mortgageValue, Colours colour, int costOfHouse,
			Integer[] rentLevels) {
		super(name, owner, price, mortgageValue);
		this.colour = colour;
		this.costOfHouse = costOfHouse;
		this.rentLevels = new ArrayList<Integer>(Arrays.asList(rentLevels));
	}
}
