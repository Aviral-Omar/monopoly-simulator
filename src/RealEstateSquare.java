public class RealEstateSquare extends PropertySquare {

	private int numberOfHouses;
	private boolean hasHotel;

	RealEstateSquare(String name, TitleDeed titleDeed) {
		super(name, titleDeed);
		numberOfHouses = 0;
		hasHotel = false;
	}

	public int getRent() {
		return ((RealEstateDeed) titleDeed).getRent(hasHotel ? 5 : numberOfHouses);
	}
}
