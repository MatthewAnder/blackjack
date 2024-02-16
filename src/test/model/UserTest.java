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
    public void testGetMoney() {
        assertEquals(0, user.getMoney());
    }

    @Test
    public void testGetHand() {
        Cards card1 = new Cards(Suits.HEART, Ranks.THREE);
        user.addHand(card1);
        assertEquals(card1, user.getHand().get(0));
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
