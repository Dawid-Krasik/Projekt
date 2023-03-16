package pl.dk.selenium.environment;

public enum PageTitles {

    Home_Page("Sklep elektroniczny - Sklep msalamon");
    private final String value;

    PageTitles(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
