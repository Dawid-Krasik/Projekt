package pl.dk.selenium.mainContent.shoppingCart;

import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.mainContent.CommonPageMain;

import static pl.dk.selenium.enums.Timeslots.*;
import static pl.dk.selenium.environment.EnvironmentParam.*;

public class ShoppingCartPage extends CommonPageMain {
    protected final CommonPage commonPage                                       = PageFactory.getPageInstance(CommonPage.class);

    private static final By selectorAddProductToShoppingCartButton              = By.xpath("//a[contains(.,'+ do koszyka')]");
    private static final By selectorRemoveProductFromShoppingCartButton         = By.xpath("//a[contains(.,'×')]");
    private static final By selectorCheckShoppingCartButton                     = By.xpath("(//a[contains(text(),'Zobacz koszyk')])[2]");
    private static final By selectorAddMoreProductButton                        = By.cssSelector(".plus");
    private static final By selectorSubtractProductButton                       = By.xpath("//input[@value='-']");
    private static final By selectorCouponInputField                            = By.xpath("//input[@id='coupon_code']");
    private static final By selectorActiveCouponButton                          = By.xpath("//button[@name='apply_coupon']");
    private static final By selectorUpdateProductButton                         = By.xpath("//button[contains(.,'Zaktualizuj koszyk')]");

    public void openShoppingCartPageNoAssert() {
        commonPage.startPageNoAssert(true, WWW_FRONT_URL_SHOPPING_CART_PAGE);
        PerformAction.waitForPageLoaded();
    }
    @Step("Click 'Add product to Shopping cart' Page button")
    public void addProductToShoppingCart(){
            PerformAction.clickElementDisabledWithOutWaiting(selectorAddProductToShoppingCartButton, "Dodaj do koszyka");
            PerformAction.waitMilliseconds(WAIT_MEDIUM_MOMENT_MS);
    }

    @Step("Click 'Remove product from Shopping cart' Page button")
    public void removeProductFromShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorRemoveProductFromShoppingCartButton, "X");
    }

    @Step("Click 'Add more product' button")
    public void addingMoreQuantityProductFromShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorAddMoreProductButton,"+");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }

    @Step("Click 'Subtraction product from Shopping cart' button")
    public void subtractionQuantityProductFromShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorSubtractProductButton, "-");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }

    @Step("Click 'Update shopping cart' button")
    public void upadteProductFromShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorUpdateProductButton, "Zaktualizuj koszyk");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }

    @Step("Click 'Check Shopping cart' Page button")
    public void checkShoppingCart(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorCheckShoppingCartButton, "Zobacz koszyk");
    }

    /**
     * Method which allows to fill  'Coupone Code' Into an Shopping Cart.
     *
     * @param CouponCode  Coupon Code eg.- "Test".
     *
     */

    @Step("Fill 'Code' into ('CouponCode') input field")
    public void fillCouponCode (String CouponCode) {
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
        PerformAction.clearTextFieldStepless(selectorCouponInputField);
        PerformAction.fillTextField(selectorCouponInputField, CouponCode, "CouponCode");
        PerformAction.clickButton(selectorActiveCouponButton,"Apply_Coupon");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }
}
