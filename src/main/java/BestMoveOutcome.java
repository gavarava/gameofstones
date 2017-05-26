public class BestMoveOutcome implements Comparable<BestMoveOutcome> {
		private int  reducedValue;
		private Move move;

		private BestMoveOutcome() {}

		public BestMoveOutcome(int reducedValue, Move move) {
			this.reducedValue = reducedValue;
			this.move = move;
		}

		public int getReducedValue() {
			return reducedValue;
		}

		public Move getMove() {
			return move;
		}

		@Override
		public int compareTo(BestMoveOutcome o) {
			return this.reducedValue - o.reducedValue;
		}
	}