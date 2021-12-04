package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class RectangularBoardTests {
    
    @Test
    public void testGetPossibleMoves() throws IOException {
        // setup
        boardReader br = new boardReader();
        PegGame board = br.readBoard("data/1_4.txt");
        int expected = 1;
        
        // invoke
        Collection<Move> possMoves = board.getPossibleMoves();
        int actual = possMoves.size();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testDeepCopy() throws IOException {
        // setup
        boardReader br = new boardReader();
        PegGame board = br.readBoard("data/1_4.txt");
        PegGame expected = board;
        
        // invoke
        PegGame actual = board.deepCopy();

        // analyze
        assertEquals(expected, actual);
    }
}
