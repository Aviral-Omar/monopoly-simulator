public abstract class Square {
	private final String name;

	Square(String name) {
		this.name = name;
		return;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
