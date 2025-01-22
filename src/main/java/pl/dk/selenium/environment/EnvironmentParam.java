package pl.dk.selenium.environment;


import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;
import pl.dk.selenium.basetest.StepLogger;

import java.util.Objects;

public enum EnvironmentParam {

    WWW_FRONT_URL_SHOP_HOME_PAGE(null),
    WWW_FRONT_URL_SHOP_ACCOUNT_PAGE(null),
    WWW_FRONT_URL_SHOPPING_CART_PAGE(null),
    WWW_FRONT_URL_WISHLIST_PAGE(null),
    WWW_FRONT_URL_NEWS_LETTER_PAGE("newsletter"),
    MSALAMON_LOGIN_EMAIL_ADDRESS(null),
    MSALAMON_LOGIN_EMAIL_PASSWORD(null);

    private final String defaultValue;

    EnvironmentParam(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        String value = getEnvValue();
        if (value == null && Objects.isNull(BaseTest.getEnvironmentService())) {
            throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
        }
        if (value == null) {
            try {
                value = BaseTest.getEnvironmentService().getValue(this.name());
            }
            catch (BFInputDataException ignored) {
                StepLogger.error("BF Input Data Exception has been thrown");
            }
        }
        return value != null ? value : defaultValue;
    }

    private String getEnvValue() {
        String value = System.getenv(this.name());
        if (value == null && defaultValue != null) {
            value = System.getenv(defaultValue);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}