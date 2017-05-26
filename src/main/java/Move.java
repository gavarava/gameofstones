public enum Move {
		TWO(2), THREE(3), FIVE(5);

	public int getValue() {
		return value;
	}

	private final int value;

		private Move(int value) {
			this.value = value;
		}
	}