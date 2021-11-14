import java.util.LinkedList;

public class CommunityChestDeck extends CardDeck<CommunityChestCard> {
	CommunityChestDeck() {
		deck = new LinkedList<CommunityChestCard>();

		for (CommunityChestActions action : CommunityChestActions.values()) {
			deck.add(new CommunityChestCard(action));
		}

		shuffleDeck();
	}

	public static String getMessage(CommunityChestActions action, Player player) {
		return switch (action) {
		case AdvanceToGo:
			yield player + " advanced to GO and received $200.";
		case BankError:
			yield player + " received $200 due to Bank Error.";
		case BeautyContest:
			yield player + " won second prize in a beauty contest and received $10.";
		case DoctorFee:
			yield player + " paid $50 as doctor's fee.";
		case GetOutOfJail:
			yield player + " received a Get Out Of Jail free card.";
		case GoToJail:
			// Already displayed message
			yield "";
		case Birthday:
			yield player + " collected $10 from every player for their birthday.";
		case Hospital:
			yield player + " paid $100 to Hospital.";
		case IncomeTaxRefund:
			yield player + " received $20 as Income Tax Refund.";
		case Inherit:
			yield player + " inherited $100.";
		case LifeInsuranceMatures:
			yield player + " received $100 as their life insurance matured.";
		case SaleOfStock:
			yield player + " received $50 from sale of stock.";
		case SchoolFees:
			yield player + " paid $50 as School Fees.";
		case ConsultancyFee:
			yield player + " received $25 as Consultancy Fee.";
		case StreetRepairs:
			yield player + " paid $40 per house and $115 per hotel for street repairs.";
		// TODO display amount
		case HolidayFundMatures:
			yield player + " received $100 as their Holiday fund matured.";
		};
	}
}
