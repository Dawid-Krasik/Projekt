package pl.dk.selenium;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import pl.dk.selenium.basetest.StepLogger;
import pl.dk.selenium.basetest.BasePageGUI;
import pl.dk.framework.actions.PerformAction;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import pl.dk.selenium.environment.EnvironmentParam;

public class CommonPage extends BasePageGUI {
    protected static final By selectorTableRowsWithElements = By.xpath("//nav[@class='navbar navbar--mobile-row']//li[%d]");

    @Override
    protected boolean isPageCorrect() {
        return false;
    }

    public static boolean isVisible(By selector) {
        try {
            WebElement webElement = getDriver().findElementDynamic(selector, 0);
            return webElement.isDisplayed();
        } catch (RuntimeException ignored) {
            StepLogger.error("Runtime exception has been thrown");
        }
        return false;
    }

    public void startPageNoAssert(boolean deleteCookies, EnvironmentParam appUrl) {
        String page = appUrl.getValue();
        if (deleteCookies) {
            getDriver().manage()
                    .deleteAllCookies();
        }

        Set<Cookie> cookies = PerformAction.getDriver()
                .manage()
                .getCookies();
        BFLogger.logInfo("COOKIES: " + cookies.size());
        for (Cookie cookie : cookies) {
            BFLogger.logInfo(cookie.getName() + "   ::::   " + cookie.getValue());
        }

        BFLogger.logInfo("Start page: " + page);
        getDriver().get(page);
    }
}
