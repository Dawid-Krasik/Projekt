package pl.dk.selenium.mainContent.homePage;

import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.environment.PageSubURLs;
import pl.dk.selenium.mainContent.CommonPageMain;

public class HomePage extends CommonPageMain {
    private static final By selectorPageContent           = By.xpath("");
    private static final By selectorSearchButton          = By.xpath("//button[@type='submit']");

    @Override
    protected boolean isPageCorrect() {
        PerformAction.waitForPageLoaded();
        return super.isPageCorrect(selectorPageContent, PageSubURLs.MSALAMON_SHOP_MAIN_PAGE) && isVisible(selectorSearchButton);
    }
}
