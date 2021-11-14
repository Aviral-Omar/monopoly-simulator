public class RealEstateSquare extends PropertySquare {

	private int numberOfHouses;
	private boolean hasHotel;

	RealEstateSquare(String name, TitleDeed titleDeed) {
		super(name, titleDeed);
		numberOfHouses = 0;
		hasHotel = false;
	}

	public int getRent(boolean ownsAllOfColour) {
		// double if all of colour owned
		return (ownsAllOfColour ? 2 : 1) * ((RealEstateDeed) titleDeed).getRent(hasHotel ? 5 : numberOfHouses);
	}

	public Colours getColour() {
		return ((RealEstateDeed) titleDeed).getColour();
	}
}
