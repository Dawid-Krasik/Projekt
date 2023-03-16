package pl.dk.selenium.mainContent.newsLetterPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.mainContent.CommonPageMain;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import static org.junit.Assert.*;
import static pl.dk.selenium.enums.Timeslots.*;
import static pl.dk.selenium.environment.EnvironmentParam.*;

public class NewsLetterPage extends CommonPageMain {
    protected final CommonPage commonPage   = PageFactory.getPageInstance(CommonPage.class);

    private static final By selectorNameTextField                          = By.xpath("//input[@name='fields[name]']");
    private static final By selectorEmailTextFiel                          = By.xpath("//input[@name='fields[email]']");
    private static final By selectorPoliciesCheckBox                       = By.xpath("//div[@id='mlb2-2531315']/div/div/div[2]/form/div[3]/label/div");
    private static final By selectorSendFormButton                         = By.xpath("//button[contains(.,'Dawaj te promocje!')]");

    public void openNewsLetterPageNoAssert() {
        commonPage.startPageNoAssert(true, WWW_FRONT_URL_NEWS_LETTER_PAGE);
        PerformAction.waitForPageLoaded();
        assertTrue(PerformAction.scrollWindowToElementAssert(selectorSendFormButton));
    }

    public void clikSendFormButton(){
        PerformAction.clickButton(selectorSendFormButton,"Dawaj te promocje!");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }

    public void acceptPolicesCheckBox(){
        PerformAction.clickButton(selectorPoliciesCheckBox,"Polices check box");
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
    }

    /**
     * Method which allows to fill  'Coupone Code' Into an Shopping Cart.
     *
     * @param Name   Name eg.- "Test".
     * @param Email  Email Code eg.- "Test@test.com".
     *
     */

    @Step("Fill 'Name' and 'Email' into ('NewsLetter') input fields")
    public void fillNewsLetterForm (String Name,String Email) {
        PerformAction.waitMilliseconds(WAIT_SHORT_MOMENT_MS);
        PerformAction.clearTextFieldStepless(selectorNameTextField);
        PerformAction.fillTextField(selectorNameTextField, Name, "Name");
        PerformAction.clearTextFieldStepless(selectorEmailTextFiel);
        PerformAction.fillTextField(selectorEmailTextFiel, Email, "Email");
    }
}
