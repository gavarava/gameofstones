import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfStonesTest {

	GameOfStones underTest = new GameOfStones();

	@Test
	public void testPossibleMoveForFailure() throws Exception {
		assertFalse(underTest.possible(1, GameOfStones.Move.TWO));
	}

	@Test
	public void testPossibleMoveForSuccess() throws Exception {
		assertTrue(underTest.possible(2, GameOfStones.Move.TWO));
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
		GameOfStones.Move move = underTest.evaluateBestMoveForThis(10);
		assertThat("", move, is(GameOfStones.Move.FIVE));
	}

	@Test
	public void testEvaluateBestMoveForSetOfStonesLessThanAllPossibleValues() throws Exception {
		GameOfStones.Move move = underTest.evaluateBestMoveForThis(1);
		assertNull("", move);
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