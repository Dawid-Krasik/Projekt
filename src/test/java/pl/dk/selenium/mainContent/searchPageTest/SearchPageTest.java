package pl.dk.selenium.mainContent.searchPageTest;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Scenario;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonTest;
import pl.dk.selenium.mainContent.searchPage.SearchPage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@App.Msalamon
@Type.Regression
@Story(Scenario.TEST_SCENARIO_ID_5)
public class SearchPageTest extends CommonTest {
    protected final SearchPage searchPage  = PageFactory.getPageInstance(SearchPage.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    @Description("Positive test scenario. Searching product by Name")
    @AllureId("1.0")
    public void searchingByCorrectName() {
        searchPage.fillsearchProductName("Kawa");
    }

    @Test
    @Description("Negative test scenario. Searching product by incorrect Name")
    @AllureId("2.0")
    public void searchByIncorrectName() {
        searchPage.fillsearchProductName("XWZWEZZZ");
    }

    @Test
    @Description("Positive test scenario. Searching product by Hints")
    @AllureId("3.0")
    public void searchByExtensionThehints() {
        searchPage.searchProductbyHintName("Kaw");
    }
}
