import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameOfStonesTest {

	GameOfStones underTest = new GameOfStones();

	@Test
	public void testPossibleMoveForFailure() throws Exception {
		assertFalse(underTest.possible(1, Move.TWO));
	}

	@Test
	public void testPossibleMoveForSuccess() throws Exception {
		assertTrue(underTest.possible(2, Move.TWO));
	}

	@Test
	public void testPlayGameOfStones() throws Exception {
		underTest.play(7, GameOfStones.Player.FIRST);
	}

	@Test
	public void testPlayGameOfStonesFor10Stones() throws Exception {
		underTest.play(10, GameOfStones.Player.FIRST);
	}

	@Test
	public void testEvaluateBestMoveFor10Stones() throws Exception {
		Move move = underTest.evaluateBestMoveUsingGreedyAlgorithm(10);
		assertThat("", move, is(Move.FIVE));
	}

	@Test
	public void testEvaluateBestMoveForSetOfStonesLessThanAllPossibleValues() throws Exception {
		Move move = underTest.evaluateBestMoveUsingGreedyAlgorithm(1);
		assertNull("", move);
	}

	@Test
	public void testOptimumMove() {

		int setOfStones = 10;
		BestMoveOutcome moveOutcome5 = new BestMoveOutcome(setOfStones, Move.FIVE);
		BestMoveOutcome moveOutcome2 = new BestMoveOutcome(setOfStones, Move.TWO);
		BestMoveOutcome moveOutcome3 = new BestMoveOutcome(setOfStones, Move.THREE);
		List<BestMoveOutcome> bestMovesList = new ArrayList<>();
		bestMovesList.add(moveOutcome5);
		bestMovesList.add(moveOutcome2);
		bestMovesList.add(moveOutcome3);
		Move result = underTest.optimumMove(bestMovesList);
		assertThat("", result, is(Move.FIVE));
	}

	@Test
	public void testPlayGameOfStonesForSetOfValues() throws Exception {
		underTest.runMultipleCases(8, new int[] { 1, 2, 3, 4, 5, 6, 7, 10 });
	}

	@Test
	public void testOptionalForOtherPlayer1() throws Exception {
		assertThat("Not Working", underTest.otherPlayer(GameOfStones.Player.FIRST), is(GameOfStones.Player.SECOND));
	}

	@Test
	public void testOptionalForOtherPlayer2() throws Exception {
		assertThat("Not Working", underTest.otherPlayer(GameOfStones.Player.SECOND), is(GameOfStones.Player.FIRST));
	}

}