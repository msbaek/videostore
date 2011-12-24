package videostore;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title, NEW_RELEASE);
    }

    double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    int determineFrequentRentalPoint(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}
