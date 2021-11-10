import java.util.Collections;
import java.util.LinkedList;

public abstract class CardDeck<T extends Card> {

	protected LinkedList<T> deck;

	protected void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public Card pickFromTop() {
		return deck.removeFirst();
	}

	public void insertAtBottom(T card) {
		deck.add(card);
	}

}
