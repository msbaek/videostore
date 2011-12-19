import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {
    private Statement statement;

    @Before
    public void setUp() {
        statement = new Statement("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        statement.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3));
        statement.generate();
        assertEquals(9.0d, statement.getTotal(), 0);
        assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatement() {
        statement.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3));
        statement.addRental(new Rental(new Movie("The Tigger Movie", Movie.NEW_RELEASE), 3));
        assertEquals(
                "Rental Record for Fred\n" +
                        "\tThe Cell\t9.0\n" +
                        "\tThe Tigger Movie\t9.0\n" +
                        "Amount owed is 18.0\n" +
                        "You earned 4 frequent renter points",
                statement.generate());
    }

    @Test
    public void testSingleChildrenStatement() {
        statement.addRental(new Rental(new Movie("The Tigger Movie", Movie.CHILDRENS), 3));
        assertEquals(
                "Rental Record for Fred\n" +
                        "\tThe Tigger Movie\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points",
                statement.generate());
    }

    @Test
    public void testMultipleRegularStatement() {
        statement.addRental(new Rental(new Movie("Plan 9 from Outer Space", Movie.REGULAR), 1));
        statement.addRental(new Rental(new Movie("8 1/2", Movie.REGULAR), 2));
        statement.addRental(new Rental(new Movie("Eraserhead", Movie.REGULAR), 3));
        assertEquals(
                "Rental Record for Fred\n" +
                        "\tPlan 9 from Outer Space\t2.0\n" +
                        "\t8 1/2\t2.0\n" +
                        "\tEraserhead\t3.5\n" +
                        "Amount owed is 7.5\n" +
                        "You earned 3 frequent renter points",
                statement.generate());
    }
}
