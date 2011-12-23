public class Movie {

    public static final int CHILDRENS = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    private String title;
    private int pricecode;

    public Movie(String title, int priceCode) {
        this.title = title;
        pricecode = priceCode;
    }

    public int getPriceCode() {
        return pricecode;
    }

    public void setPriceCode(int arg) {
        pricecode = arg;
    }

    public String getTitle() {
        return title;
    }

    double determineAmount(int daysRented) {
        double rentalAmount = 0;
        switch (getPriceCode()) {
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
}
