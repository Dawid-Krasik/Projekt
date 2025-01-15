package pl.dk.selenium.mainContent.loginPage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.mainContent.CommonPageMain;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import pl.dk.selenium.basetest.BasePageGUI;
import pl.dk.selenium.environment.EnvironmentParam;

public class LoginPage extends CommonPageMain {
    protected final CommonPage commonPage                           = PageFactory.getPageInstance(CommonPage.class);

    private static final By selectorLoginMessageTextInput           = By.cssSelector(".woocommerce-MyAccount-content > p:nth-child(2)");
    private static final By selectorEmailAddressInputField          = By.id("username");
    private static final By selectorResetEmailAddressInputField     = By.id("user_login");
    private static final By selectorEmailPasswordInputField         = By.id("password");
    private static final By selectorLoginButton                     = By.cssSelector(".woocommerce-form-login__submit");
    private static final By selectorResetPasswordButton             = By.xpath("//button[contains(.,'Zresetuj hasło')]");
    private static final By selectorResetPasswordTextButton         = By.xpath("//a[contains(text(),'Nie pamiętasz hasła?')]");

    @Override
    public boolean isPageCorrect() {
        return CommonPage.isVisible(selectorLoginMessageTextInput);
    }

    @Step("Open Msalamon Shop Page without assert")
    public void openMsalamonShopNoAssert() {
        commonPage.startPageNoAssert(true, EnvironmentParam.WWW_FRONT_URL_SHOP_ACCOUNT_PAGE);
        enterEmailAddress(EnvironmentParam.MSALAMON_LOGIN_EMAIL_ADDRESS.toString());
        enterEmailPassword(EnvironmentParam.MSALAMON_LOGIN_EMAIL_PASSWORD.toString());
        PerformAction.waitForPageLoaded();
    }

    @Step("Click 'Login' button")
    public void clickLoginButton(){
        BasePageGUI.getDriver().findElementDynamic(selectorLoginButton)
                .click();
    }

    @Step("Click 'Reset password' button")
    public void  clickResetPasswordButton(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorResetPasswordButton, "Zresetuj Haslo");
    }

    @Step("Click 'Logout' button")
    public void clickLogoutButton(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorLoginMessageTextInput, "Wyloguj się");
    }

    @Step("Click 'Reset Password' button")
    public void clickResetPasswordTextButton(){
        PerformAction.clickElementDisabledWithOutWaiting(selectorResetPasswordTextButton, "Nie pamiętasz hasła?");
        PerformAction.waitForPageLoaded();
    }

    public void enterEmailAddress(String emailAddress){
        PerformAction.clearTextFieldStepless(selectorEmailAddressInputField);
        PerformAction.fillTextField(selectorEmailAddressInputField, emailAddress, "Nazwa użytkownika lub adress e-mail");
    }

    public void enterEmailPassword(String emailPassword){
        PerformAction.clearTextFieldStepless(selectorEmailPasswordInputField);
        PerformAction.fillTextField(selectorEmailPasswordInputField, emailPassword, "Hasło");
        clickLoginButton();
    }

    public void enterResetEmailAddress(String resetEmailAddress){
        PerformAction.clearTextFieldStepless(selectorResetEmailAddressInputField);
        PerformAction.fillTextField(selectorResetEmailAddressInputField, resetEmailAddress, "Nazwa użytkownika lub adres e-mail");
        clickResetPasswordButton();
    }
}
