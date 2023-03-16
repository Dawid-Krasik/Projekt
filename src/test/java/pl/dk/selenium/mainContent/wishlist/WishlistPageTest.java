package pl.dk.selenium.mainContent.wishlist;


import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Scenario;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonTest;
import pl.dk.selenium.mainContent.CommonPageMain;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import pl.dk.selenium.mainContent.shoppingCart.ShoppingCartPage;

@App.Msalamon
@Type.Regression
@Story(Scenario.TEST_SCENARIO_ID_3)
public class WishlistPageTest extends CommonTest {
    protected final CommonPageMain      commonPageMain             = PageFactory.getPageInstance(CommonPageMain.class);
    protected final WishlistPage        wishlistPage               = PageFactory.getPageInstance(WishlistPage.class);
    protected final ShoppingCartPage    shoppingCartPage           = PageFactory.getPageInstance(ShoppingCartPage.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    @Description("Positive test scenario. Adding product to Wishlist")
    @AllureId("1.0")
    public void addingProductToTheWishlist() {
        commonPageMain.goToCoffePage();
        wishlistPage.addProductToWishlist();
        wishlistPage.addProductToWishlist();
        wishlistPage.removeProductFromWishlist();
        wishlistPage.openWishlistPageNoAssert();
    }
    @Test
    @Description("Positive test scenario. Verify that product is Available")
    @AllureId("2.0")
    public void VerifyThatProductIsAvailable() {
        commonPageMain.goToCoffePage();
        wishlistPage.addProductToWishlist();
        wishlistPage.addProductToWishlist();
        wishlistPage.verifyThatProductIsAvailable("Dostępny");
        wishlistPage.removeProductFromWishlist();
        wishlistPage.openWishlistPageNoAssert();
    }

    @Test
    @Description("Positive test scenario. Adding product from Wishlist to the Shopping Cart")
    @AllureId("3.0")
    public void addingProductFromWishlistToShoppingCart() {
        commonPageMain.goToCoffePage();
        wishlistPage.addProductToWishlist();
        wishlistPage.addProductToWishlist();
        wishlistPage.addProductFromWithlistToShoppingCart();
        wishlistPage.checkShoppingCart();
        shoppingCartPage.removeProductFromShoppingCart();
        wishlistPage.openWishlistPageNoAssert();
    }
}
