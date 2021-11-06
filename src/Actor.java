import java.util.ArrayList;

public class Actor {
	private int cash;
	private final ArrayList<TitleDeed> titleDeeds;

	Actor(int cash) {
		this.cash = cash;
		titleDeeds = new ArrayList<TitleDeed>();
	}
}
