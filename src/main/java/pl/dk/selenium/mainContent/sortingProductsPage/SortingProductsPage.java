package pl.dk.selenium.mainContent.sortingProductsPage;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.mainContent.CommonPageMain;

import static pl.dk.selenium.enums.Timeslots.*;

public class SortingProductsPage extends CommonPageMain {

    private static final By selectorCountryDropDownList                       = By.cssSelector(".orderby");

    /**
     * Method which allows to 'Select Sort Options' from DropDown menu on the Page
     * @param SortingOptions For more Country names check the Enums CountryNames
     */

    @Step("Select 'Country' Name from dropdown menu in 'Metric' Page")
    public void selectDropDownSortingOptions (String SortingOptions){
        PerformAction.waitMilliseconds(WAIT_MEDIUM_MOMENT_MS);
        PerformAction.selectOptionsDropDownBySortingOptions(selectorCountryDropDownList,SortingOptions);
        PerformAction.waitMilliseconds(WAIT_MEDIUM_MOMENT_MS);
    }
}
