import java.util.ArrayList;
import java.util.List;

public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<Rental>();
    private double total;
    private int frequentRenterPoints;
    private double totalAmount;

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String generate() {
        clearTotals();
        String statementText = header();
        statementText += rentalLines();
        statementText += footer();
        return statementText;
    }

    private String footer() {
        return String.format(
                "Amount owed is %.1f\n" +
                        "You earned %d frequent renter points",
                totalAmount, frequentRenterPoints);
    }

    private String rentalLines() {
        String rentalLines = "";
        for(Rental rental : rentals) {
            double thisAmount = 0;

            //determine amounts for rental line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2)
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3)
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
                frequentRenterPoints++;

            // show figures for this rental
            rentalLines += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        return rentalLines;
    }

    private String header() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    public double getTotal() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
