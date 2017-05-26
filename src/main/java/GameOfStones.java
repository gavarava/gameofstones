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
		Move bestMove = evaluateBestMoveUsingGreedyAlgorithm(setOfStones);
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
			setOfStones -= bestMove.getValue();
		}
		return setOfStones;
	}

	protected Move evaluateBestMoveUsingGreedyAlgorithm(int setOfStones) {
		List<BestMoveOutcome> bestMoveOutcomePossible = new ArrayList<>();
		for (Move move : Move.values()) {
			if (possible(setOfStones, move)) {
				int reducedSetOfStones = setOfStones - move.getValue();
				if (reducedSetOfStones == 0) {
					return move;
				} else {
					bestMoveOutcomePossible.add(new BestMoveOutcome(reducedSetOfStones, move));
				}
			}
		}
		if (bestMoveOutcomePossible.isEmpty()) {
			return null;
		}
		return optimumMove(bestMoveOutcomePossible);
	}

	protected Move optimumMove(List<BestMoveOutcome> bestMoveOutcomePossible) {
		return Collections.min(bestMoveOutcomePossible).getMove();
	}

	private boolean stillPossibleToPlay(int setOfStones) {
		return possible(setOfStones, Move.FIVE) || possible(setOfStones, Move.THREE) || possible(setOfStones, Move.TWO);
	}

	protected boolean possible(int setOfStones, Move move) {
		//return Stream.of(Move.values()).anyMatch(x -> (x >= 2));
		//	return IntStream.of(setOfStones).anyMatch(x -> Arrays.stream().anyMatch(boolean::isGreaterThanEqualTo));
		return setOfStones >= move.getValue();
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

}