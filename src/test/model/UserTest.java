package model;

import model.exceptions.NegativeMoneyException;
import model.exceptions.NoMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User user;

    @BeforeEach
    public void runBefore() {
        user = new User();
    }

    @Test
    public void testTakeMoney() {
        try {
            assertEquals(1, user.takeMoney(1));
            assertEquals(100, user.takeMoney(100));
            assertEquals(899, user.getMoney());
            assertEquals(898, user.takeMoney(898));
            assertEquals(1, user.takeMoney(1));
        } catch (NoMoneyException e) {
            fail("Unexpected NoMoneyException");
        } catch (NegativeMoneyException e) {
            fail("Unexpected NegativeMoneyException");
        }
        assertEquals(0, user.getMoney());
    }

    @Test
    public void testTakeMoneyExpectedException() {

        try {
            user.takeMoney(1001);
            fail("No Exception was thrown");
        } catch (NoMoneyException e) {
            // go right through
        } catch (NegativeMoneyException e) {
            fail("Unexpected NegativeMoneyException");
        }

        assertEquals(1000, user.getMoney());
    }

    @Test
    public void testTakeMoneyExpectedExceptionNegativeMoney() {

        try {
            user.takeMoney(-1);
            fail("No Exception was thrown");
        } catch (NoMoneyException e) {
            fail("Unexpect NoMoneyException");
        } catch (NegativeMoneyException e) {
            // go right through
        }

        assertEquals(1000, user.getMoney());
    }

    @Test
    public void testTakeMoneyExpectedExceptionZero() {

        try {
            user.takeMoney(0);
            fail("No Exception was thrown");
        } catch (NoMoneyException e) {
            fail("Unexpect NoMoneyException");
        } catch (NegativeMoneyException e) {
            // go right through
        }

        assertEquals(1000, user.getMoney());
    }

    @Test
    public void testGiveMoney() {
        assertEquals(1000, user.getMoney());
        user.giveMoney(1000);
        assertEquals(2000, user.getMoney());
    }

    @Test
    public void testGetMoney() {
        assertEquals(1000, user.getMoney());
    }

    @Test
    public void testAddHand() {
        Cards card1 = new Cards(Suits.HEART, Ranks.THREE);
        Cards card2 = new Cards(Suits.CLUB, Ranks.KING);
        user.addHand(card1);
        user.addHand(card2);

        assertEquals(card1, user.getHand().get(0));
        assertEquals(card2, user.getHand().get(1));
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

    @Test
    public  void testResetHand() {
        Cards card1 = new Cards(Suits.HEART, Ranks.THREE);
        Cards card2 = new Cards(Suits.CLUB, Ranks.KING);
        user.addHand(card1);
        user.addHand(card2);

        assertEquals(2, user.getHand().size());
        user.resetHand();
        assertEquals(0, user.getHand().size());
        assertFalse(1 == user.getHand().size());
    }
}
