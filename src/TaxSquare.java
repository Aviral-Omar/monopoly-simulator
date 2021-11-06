public class TaxSquare extends Square {

	private int amount;

	TaxSquare(String name, int amount) {
		super(name);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

}
