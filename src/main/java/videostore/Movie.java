package videostore;

public abstract class Movie {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    abstract double determineAmount(int daysRented);

    abstract int determineFrequentRentalPoint(int daysRented);
}
