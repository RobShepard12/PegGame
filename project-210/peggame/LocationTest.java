package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class LocationTest {
    @Test
    public void getRowTest(){
        // startup
        Location loc = new Location(5,5);
        int expected = 5;

        // invoke
        int actual = loc.getRow();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void getColTest(){
        // startup
        Location loc = new Location(5,5);
        int expected = 5;

        // invoke
        int actual = loc.getCol();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void toStringTest(){
        // startup
        Location loc = new Location(5,5);
        String expected = "(5, 5)";

        // invoke
        String actual = loc.toString();

        // analyze
        assertEquals(expected, actual);
    }
}
