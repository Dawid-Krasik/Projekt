package pl.dk.selenium.mainContent.shoppingCart;


import com.capgemini.mrchecker.test.core.utils.PageFactory;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Scenario;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonPageMain;
import pl.dk.selenium.mainContent.CommonTest;

@App.Msalamon
@Type.Regression
@Story(Scenario.TEST_SCENARIO_ID_2)
public class ShoppingCartPageTest extends CommonTest {
    protected final ShoppingCartPage    shoppingCartPage          = PageFactory.getPageInstance(ShoppingCartPage.class);
    protected final CommonPageMain      commonPageMain            = PageFactory.getPageInstance(CommonPageMain.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    @Description("Positive test scenario. Adding product to shopping Cart")
    @AllureId("1.0")
    public void checkingTheContentsOfShoppingCart() {
        commonPageMain.goToCoffePage();
        shoppingCartPage.addProductToShoppingCart();
        shoppingCartPage.checkShoppingCart();
        shoppingCartPage.removeProductFromShoppingCart();
        shoppingCartPage.openShoppingCartPageNoAssert();
    }

    @Test
    @Description("Positive test scenario. Adding more product to shopping Cart")
    @AllureId("2.0")
    public void CheckingThePossibilityOfAddingMoreProductsToShoppingCart() {
        commonPageMain.goToCoffePage();
        shoppingCartPage.addProductToShoppingCart();
        shoppingCartPage.checkShoppingCart();
        shoppingCartPage.addingMoreQuantityProductFromShoppingCart();
        shoppingCartPage.upadteProductFromShoppingCart();
        shoppingCartPage.removeProductFromShoppingCart();
        shoppingCartPage.openShoppingCartPageNoAssert();
    }

    @Test
    @Description("Positive test scenario. Increase quantity of product into the shopping Cart")
    @AllureId("3.0")
    public void CheckingThePossibilityOfsubtractionQuantityProductsToShoppingCart() {
        commonPageMain.goToCoffePage();
        shoppingCartPage.addProductToShoppingCart();
        shoppingCartPage.checkShoppingCart();
        shoppingCartPage.addingMoreQuantityProductFromShoppingCart();
        shoppingCartPage.upadteProductFromShoppingCart();
        shoppingCartPage.subtractionQuantityProductFromShoppingCart();
        shoppingCartPage.upadteProductFromShoppingCart();
        shoppingCartPage.removeProductFromShoppingCart();
        shoppingCartPage.openShoppingCartPageNoAssert();
    }

    @Test
    @Description("Positive test scenario. Adding Coupon Code to shopping Cart")
    @AllureId("4.0")
    public void AddingIncorectShoppingCouponCodeToShoppingCart() {
        commonPageMain.goToCoffePage();
        shoppingCartPage.addProductToShoppingCart();
        shoppingCartPage.checkShoppingCart();
        shoppingCartPage.fillCouponCode("Test");
        shoppingCartPage.removeProductFromShoppingCart();
        shoppingCartPage.openShoppingCartPageNoAssert();
    }
}
