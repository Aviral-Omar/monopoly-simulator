public class ChanceCard extends Card {

	private final ChanceActions action;

	ChanceCard(ChanceActions action) {
		this.action = action;
	}

	public ChanceActions getAction() {
		return action;
	}

}