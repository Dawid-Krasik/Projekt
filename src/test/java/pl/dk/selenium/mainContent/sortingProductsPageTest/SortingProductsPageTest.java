package pl.dk.selenium.mainContent.sortingProductsPageTest;

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
import pl.dk.selenium.mainContent.sortingProductsPage.SortingProductsPage;

@App.Msalamon
@Type.Regression
@Story(Scenario.TEST_SCENARIO_ID_6)
public class SortingProductsPageTest extends CommonTest {
    protected final SortingProductsPage  sortingProductsPage         = PageFactory.getPageInstance(SortingProductsPage.class);
    protected final CommonPageMain       commonPageMain              = PageFactory.getPageInstance(CommonPageMain.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    @Description("Positive test scenario. Sorting by 'Sortuj wg popularności' ")
    @AllureId("1.0")
    public void sortingByPopularity() {
        commonPageMain.goToCoffePage();
        sortingProductsPage.selectDropDownSortingOptions("Sortuj wg popularności");
    }

    @Test
    @Description("Positive test scenario. Sorting by 'Sortuj wg średniej oceny' ")
    @AllureId("2.0")
    public void sortingByAveragePrice() {
        commonPageMain.goToCoffePage();
        sortingProductsPage.selectDropDownSortingOptions("Sortuj wg średniej oceny");
    }

    @Test
    @Description("Positive test scenario. Sorting by 'Sortuj od najnowszych' ")
    @AllureId("3.0")
    public void sortingByLatestProduct() {
        commonPageMain.goToCoffePage();
        sortingProductsPage.selectDropDownSortingOptions("Sortuj od najnowszych");
    }

    @Test
    @Description("Positive test scenario. Sorting by 'Sortuj po cenie od najniższej' ")
    @AllureId("4.0")
    public void sortingByLowestPrice() {
        commonPageMain.goToCoffePage();
        sortingProductsPage.selectDropDownSortingOptions("Sortuj po cenie od najniższej");
    }

    @Test
    @Description("Positive test scenario. Sorting by 'Sortuj po cenie od najwyższej' ")
    @AllureId("5.0")
    public void sortingByHighestPrice() {
        commonPageMain.goToCoffePage();
        sortingProductsPage.selectDropDownSortingOptions("Sortuj po cenie od najwyższej");
    }
}
