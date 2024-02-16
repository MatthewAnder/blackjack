package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    Table table;
    User user;
    Dealer dealer;

    @BeforeEach
    public void runBefore() {
        user = new User();
        dealer = new Dealer();
        table = new Table(user, dealer);
    }

    @Test
    public void testConstructor() {

    }

    @Test
    public void testDistributeCards() {


    }

    @Test
    public void testDrawCard() {

    }
}
