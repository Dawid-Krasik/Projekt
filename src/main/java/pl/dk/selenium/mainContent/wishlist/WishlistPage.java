package pl.dk.selenium.mainContent.wishlist;

import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.basetest.StepLogger;
import pl.dk.selenium.mainContent.CommonPageMain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.dk.selenium.enums.Timeslots.*;
import static pl.dk.selenium.environment.EnvironmentParam.*;

public class WishlistPage extends CommonPageMain {
    protected final CommonPage commonPage          = PageFactory.getPageInstance(CommonPage.class);


    private static final By selectorAddProductToWishlistButton                   = By.xpath("//main[@id='main']/ul/li/div/div/a/i");
    private static final By selectorRemoveProductFromWishlistButton              = By.xpath("//a[contains(.,'×')]");
    private static final By selectorIsProductAvailableTextInput                  = By.xpath("//span[contains(.,'Dostępny')]");
    private static final By selectorAddToShoppingCartButton                      = By.xpath("//a[contains(text(),'+ do koszyka')]");
    private static final By selectorCheckShoppingCartButton                      = By.xpath("//a[contains(text(),'Zobacz koszyk')]");

    public void openWishlistPageNoAssert() {
        commonPage.startPageNoAssert(true, WWW_FRONT_URL_WISHLIST_PAGE);
        PerformAction.waitForPageLoaded();
    }
    @Step("Click 'Add product to Wishlist' Page button")
    public void addProductToWishlist(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorAddProductToWishlistButton, "Dodaj do listy życzeń");
        PerformAction.waitMilliseconds(WAIT_MOMENT_MS);
    }

    @Step("Click 'Remove product from Wishlist' Page button")
    public void removeProductFromWishlist(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorRemoveProductFromWishlistButton, "X");
        PerformAction.waitMilliseconds(WAIT_MOMENT_MS);
    }

    @Step("Click 'Add product to Wishlist' Page button")
    public void addProductFromWithlistToShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorAddToShoppingCartButton, "Dodaj do koszyka");
        PerformAction.waitMilliseconds(WAIT_MOMENT_MS);
    }

    @Step("Click 'Check Shopping Cart' Page button")
    public void checkShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorCheckShoppingCartButton, "Zobacz koszyk");
    }

    @Step("Confirm that 'Rollout Market' is present ")
    public void verifyThatProductIsAvailable(String country){
        String getVersionValueFromSelector = PerformAction.getTextFromTextbox(selectorIsProductAvailableTextInput);
        assertEquals(getVersionValueFromSelector, country);
        StepLogger.info("\"Wishlist Product\" is Available");
        PerformAction.waitMilliseconds(WAIT_MOMENT_MS);
    }
}
