package pl.dk.selenium.mainContent;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.environment.PageTitles;
import pl.dk.selenium.environment.PageSubURLs;
import pl.dk.selenium.environment.EnvironmentParam;

public class MainPage extends BasePage {
    protected final CommonPage commonPage          = PageFactory.getPageInstance(CommonPage.class);

    @Override
    public void load() {
        getDriver().get(EnvironmentParam.WWW_FRONT_URL_SHOP_HOME_PAGE .getValue() + PageSubURLs.MSALAMON_SHOP_MAIN_PAGE.getValue());
    }

    @Override
    public boolean isLoaded() {
        return isUrlAndPageTitleAsCurrentPage(EnvironmentParam.WWW_FRONT_URL_SHOP_HOME_PAGE .getValue() + PageSubURLs.MSALAMON_SHOP_MAIN_PAGE.getValue());
    }

    @Override
    public String pageTitle() {
        return PageTitles.Home_Page.toString();
    }

    public void openHomePagePageNoAssert() {
        commonPage.startPageNoAssert(true, EnvironmentParam.WWW_FRONT_URL_SHOP_HOME_PAGE);
        PerformAction.waitForPageLoaded();
    }
}
