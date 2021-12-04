package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TriangularBoardTests {
    @Test
    public void GameStateTest(){
        // Setup
        TriangularBoard board = new TriangularBoard(5);
        GameState expected = GameState.NOT_STARTED;

        // invoke 
        GameState actual = board.getGameState();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void GetMovesMadeTest(){
        // Setup
        TriangularBoard board = new TriangularBoard(5);
        List<Move> expected = new LinkedList<>();

        // invoke 
        List<Move> actual = board.getMovesMade();

        // analyze
        assertEquals(expected, actual);
    }
}
