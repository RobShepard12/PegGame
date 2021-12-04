package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class MoveTest {
    
    @Test
    public void MoveTestEquals () {
        // setup
        Move move1 = new Move(new Location(2, 3), new Location(4, 3));
        Move move2 = new Move(new Location(2, 3), new Location(4, 3));
        Boolean expected = true;

        // invoke
        Boolean actual = move1.equals(move2);

        // analyze
        assertEquals(actual, expected);
    }
}
