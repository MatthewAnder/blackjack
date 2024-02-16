package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RanksTest {
    @Test
    public void testToString() {
        assertEquals("Ace", Ranks.ACE.toString());
        assertEquals("2", Ranks.TWO.toString());
        assertEquals("3", Ranks.THREE.toString());
        assertEquals("Jack", Ranks.JACK.toString());
    }

    @Test
    public void testGetValue() {
        assertEquals(1, Ranks.ACE.getValue());
        assertEquals(10, Ranks.JACK.getValue());
    }
}
