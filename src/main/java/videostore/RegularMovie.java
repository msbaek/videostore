package videostore;

public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title, REGULAR);
    }

    double determineAmount(int daysRented) {
        double rentalAmount = 0;
        switch (pricecode) {
            case REGULAR:
                rentalAmount += 2;
                if (daysRented > 2)
                    rentalAmount += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                rentalAmount += daysRented * 3;
                break;
            case CHILDRENS:
                rentalAmount += 1.5;
                if (daysRented > 3)
                    rentalAmount += (daysRented - 3) * 1.5;
                break;
        }
        return rentalAmount;
    }

    int determineFrequentRentalPoint(int daysRented) {
        boolean bonusIsEarned = (pricecode == NEW_RELEASE && daysRented > 1);
        if (bonusIsEarned)
            return 2;
        return 1;
    }
}
