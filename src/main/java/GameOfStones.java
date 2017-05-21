import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class GameOfStones {

	protected void runMultipleCases(int numberOfTests, int[] stones) {
		for (int i = 0; i < stones.length; i++) {
			play(stones[i], Player.FIRST);
		}
	}

	protected void play(int setOfStones, Player currentPlayer) {
		Move bestMove = evaluateBestMoveForThis(setOfStones);
		setOfStones = reduce(setOfStones, bestMove);
		if (stillPossibleToPlay(setOfStones)) {
			play(setOfStones, otherPlayer(currentPlayer));
		} else {
			declareWinner(currentPlayer);
		}
	}

	private int reduce(int setOfStones, Move bestMove) {
		if (bestMove == null) {
			setOfStones = 0;
		} else {
			setOfStones -= bestMove.value;
		}
		return setOfStones;
	}

	protected Move evaluateBestMoveForThis(int setOfStones) {
		List<BestMoves> bestMovesPossible = new ArrayList<>();
		for (Move move : Move.values()) {
			if (possible(setOfStones, move)) {
				int reducedSetOfStones = setOfStones - move.value;
				if (reducedSetOfStones == 0) {
					return move;
				} else {
					bestMovesPossible.add(new BestMoves(reducedSetOfStones, move));
				}
			}
		}
		if (bestMovesPossible.isEmpty()) {
			return null;
		}
		return optimumMove(bestMovesPossible);
	}

	private Move optimumMove(List<BestMoves> bestMovesPossible) {
		return Collections.min(bestMovesPossible).getMove();
	}

	public class BestMoves implements Comparable<BestMoves> {
		private int  reducedValue;
		private Move move;

		public BestMoves(int reducedValue, Move move) {
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
		public int compareTo(BestMoves o) {
			return this.reducedValue - o.reducedValue;
		}
	}

	private boolean stillPossibleToPlay(int setOfStones) {
		return possible(setOfStones, Move.FIVE) || possible(setOfStones, Move.THREE) || possible(setOfStones, Move.TWO);
	}

	protected boolean possible(int setOfStones, Move move) {
		//return Stream.of(Move.values()).anyMatch(x -> (x >= 2));
		//	return IntStream.of(setOfStones).anyMatch(x -> Arrays.stream().anyMatch(boolean::isGreaterThanEqualTo));
		return setOfStones >= move.value;
	}

	protected Player otherPlayer(Player currentPlayer) {
		return currentPlayer.equals(Player.FIRST) ? Player.SECOND : Player.FIRST;
	}

	private void declareWinner(Player winner) {
		System.out.println(winner.toString());
	}

	public enum Player implements Supplier<Player> {
		FIRST(1), SECOND(2);
		private final int value;

		private Player(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			switch (value) {
			case 1:
				return "First";
			case 2:
				return "Second";
			}
			return "INVALIDPLAYER";
		}

		@Override
		public Player get() {
			return this;
		}
	}

	public enum Move {
		TWO(2), THREE(3), FIVE(5);
		private final int value;

		private Move(int value) {
			this.value = value;
		}
	}
}