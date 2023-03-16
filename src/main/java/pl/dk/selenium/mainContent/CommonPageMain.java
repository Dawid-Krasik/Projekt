package pl.dk.selenium.mainContent;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.mainContent.loginPage.LoginPage;
import pl.dk.selenium.mainContent.menuPages.CoffeePage;
import pl.dk.selenium.mainContent.shoppingCart.ShoppingCartPage;
import com.capgemini.mrchecker.selenium.core.newDrivers.DriverManager;
import pl.dk.selenium.mainContent.wishlist.WishlistPage;

public class CommonPageMain extends CommonPage {
    private static final String xpathMainMenuItemLink         = "//a[text()='%s']";
    private static final By     selectorAccountButton         = By.cssSelector(".kadence-account-svg");
    private static final By     selectorWishlistButton        = By.id("menu-item-17582");
    private static final By     selectorShoppingCartButton    = By.cssSelector(".header-cart-inner-wrap:nth-child(2) .kadence-svg-icon");

    @Step("Open 'Login' Page")
    public LoginPage goToLoginPage() {
        PerformAction.clickButton(selectorAccountButton, "'Account' Tab");
        return new LoginPage();
    }

    @Step("Open 'Wishlist' Page")
    public WishlistPage goToWishlistPage() {
        PerformAction.clickButton(selectorWishlistButton, "'Wishlist' Tab");
        return new WishlistPage();
    }

    @Step("Open 'ShoppingCart' Page")
    public ShoppingCartPage openShoppingCartFrame() {
        PerformAction.clickButton(selectorShoppingCartButton, "'ShoppingCart' Tab");
        return new ShoppingCartPage();
    }

    @Step("Open 'Coffe' Page")
    public CoffeePage goToCoffePage() {
        By itemLocator = By.xpath(String.format(xpathMainMenuItemLink, "Kawa"));
        PerformAction.clickButton(itemLocator, "'Kawa' Tab");
        return new CoffeePage();
    }

    @Step("Logout User")
    public void logoutUserWithStep() {
        By itemLocator = By.xpath(String.format(xpathMainMenuItemLink, "Logout"));
        PerformAction.clickButton(itemLocator, "Logout");
        DriverManager.closeDriver();
    }
}
