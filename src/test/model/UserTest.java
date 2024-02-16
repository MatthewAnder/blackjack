package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user;

    @BeforeEach
    public void runBefore() {
        user = new User();
    }

    @Test
    public void testGetValueOfHand() {
        assertEquals(0, user.getValueOfHand());
        user.addHand(new Cards(Suits.SPADE, Ranks.FIVE));
        assertEquals(5, user.getValueOfHand());
        user.addHand(new Cards(Suits.HEART, Ranks.NINE));
        assertEquals(14, user.getValueOfHand());
    }
}
