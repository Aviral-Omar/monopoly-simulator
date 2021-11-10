public class CommunityChestCard extends Card {

	private final CommunityChestActions action;

	CommunityChestCard(CommunityChestActions action) {
		this.action = action;
	}

	public CommunityChestActions getAction() {
		return action;
	}

}
