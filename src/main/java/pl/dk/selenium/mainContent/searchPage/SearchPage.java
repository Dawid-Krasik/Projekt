package pl.dk.selenium.mainContent.searchPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.mainContent.CommonPageMain;

import static pl.dk.selenium.enums.Timeslots.WAIT_SHORT_MOMENT_MS;

public class SearchPage extends CommonPageMain {

    private static final By selectorSearchTextInput                    = By.cssSelector("#dgwt-wcas-search-input-1");
    private static final By selectorSearchButton                       = By.cssSelector(".header-html2 .dgwt-wcas-search-submit");
    private static final By selectorShowAllHintsButton                 = By.cssSelector(".dgwt-wcas-st-more");


    /**
     * Method which allows to fill  'Product Name' Into an Search product textbox.
     *
     * @param searchProductName  Search Probucts by name eg.- "Kawa".
     *
     */

    @Step("Fill 'Name' into ('Search textbox') field")
    public void fillsearchProductName (String searchProductName) {
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
        PerformAction.clearTextFieldStepless(selectorSearchTextInput);
        PerformAction.fillTextField(selectorSearchTextInput, searchProductName, "Szukaj produktów");
        PerformAction.clickButton(selectorSearchButton,"Szukaj");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }

    @Step("Fill 'Hint' into ('Search textbox') field")
    public void searchProductbyHintName (String searchProductbyHintName) {
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
        PerformAction.clearTextFieldStepless(selectorSearchTextInput);
        PerformAction.fillTextField(selectorSearchTextInput, searchProductbyHintName, "Szukaj produktów");
        PerformAction.clickButton(selectorShowAllHintsButton,"Zobacz wszystkie produkty");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }
}
