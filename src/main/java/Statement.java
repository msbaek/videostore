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

    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    private String header() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private String rentalLines() {
        String rentalLines = "";
        for (Rental rental : rentals) {
            rentalLines += rentalLine(rental);
        }
        return rentalLines;
    }

    private String rentalLine(Rental rental) {
        double rentalAmount = determineAmount(rental);
        frequentRenterPoints += determineFrequentRentalPoint(rental);
        totalAmount += rentalAmount;

        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(Rental rental, double rentalAmount) {
        return "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rentalAmount) + "\n";
    }

    private int determineFrequentRentalPoint(Rental rental) {
        boolean bonusIsEarned = (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1;
        if (bonusIsEarned)
            return 2;
        return 1;
    }

    private double determineAmount(Rental rental) {
        double rentalAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount += 2;
                if (rental.getDaysRented() > 2)
                    rentalAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                rentalAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                rentalAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    rentalAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return rentalAmount;
    }

    private String footer() {
        return String.format(
                "Amount owed is %.1f\n" +
                        "You earned %d frequent renter points",
                totalAmount, frequentRenterPoints);
    }

    public double getTotal() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
