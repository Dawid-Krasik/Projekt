package pl.dk.selenium.environment;

public enum PageSubURLs {
    MSALAMON_SHOP_MAIN_PAGE(""),
    MSALAMON_SHOP_KAWA_PAGE("kategoria-produktu/kawa");

    private final String subURL;

    PageSubURLs(String subURL) {
        this.subURL = subURL;
    }

    @Override
    public String toString() {
        return getValue();
    }

    public String getValue() {
        return subURL;
    }
}
