package pl.dk.selenium.mainContent.menuPages;

import org.openqa.selenium.By;
import pl.dk.selenium.environment.PageSubURLs;
import pl.dk.selenium.mainContent.CommonPageMain;

public class CoffeePage extends CommonPageMain{

    private static final By selectorPageContent           = By.xpath("//Kawa");

    @Override
    protected boolean isPageCorrect() {
        return super.isPageCorrect(selectorPageContent, PageSubURLs.MSALAMON_SHOP_KAWA_PAGE);
    }
}

