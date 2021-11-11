import java.util.LinkedList;

public class ChanceDeck extends CardDeck<ChanceCard> {
	ChanceDeck() {
		deck = new LinkedList<ChanceCard>();

		for (ChanceActions action : ChanceActions.values()) {
			deck.add(new ChanceCard(action));
		}

		shuffleDeck();
	}

	public static String getMessage(ChanceActions action, Player player) {
		return switch (action) {
		case AdvanceToBoardwalk:
			yield player + " advanced to Boardwalk";
		case AdvanceToGo:
			yield player + " advanced to GO and received $200.";
		case AdvanceToIllinoisAvenue:
			yield player + " advanced to Illinois Avenue."; //TODO Add go condition.
		case AdvanceToStCharlesPlace:
			yield player + " advanced to St. Charles Place.";
		case AdvanceToNearestRailroad:
			yield player + " needs to advance to nearest Railroad."; //TODO Display nearest railroad.
		case AdvanceToNearestUtility:
			yield player + " needs to advance to nearest Utility."; //TODO Display nearest Utility.
		case ReceiveDividend:
			yield player + " received $50 dividend from Bank.";
		case GetOutOfJail:
			yield player + " received a get out of jail free card.";
		case MoveBack:
			yield player + " needs to move back by 3 steps."; //TODO Display current location.
		case GoToJail:
			yield player + " went to jail.";
		case PayForRepairs:
			yield player + " needs to pay $25 per house and $50 per hotel for repairs."; //TODO Display amount to be paid.
		case PaySpeedingFine:
			yield player + " paid $15 to the bank for speeding.";
		case AdvanceToReadingRailroad:
			yield player + " advanced to Reading Railroad."; //TODO Add go condition.
		case PayForBecomingChairman:
			yield player + " paid $50 to each player for being elected as Chairman of the Board.";
		case ReceiveBuildingLoanMaturity:
			yield player + " received $50 as maturity on building loan from the bank."; //Comfirm there are two nearest railroad card.
		};
	}
}
