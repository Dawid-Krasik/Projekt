package pl.dk.selenium.mainContent.loginPageTest;


import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Scenario;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonPageMain;
import pl.dk.selenium.mainContent.CommonTest;
import pl.dk.selenium.mainContent.loginPage.LoginPage;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import static org.junit.jupiter.api.Assertions.*;
import static pl.dk.selenium.environment.EnvironmentParam.*;

@App.Msalamon
@Type.Regression
@Story(Scenario.TEST_SCENARIO_ID_1)
public class LoginPageTest extends CommonTest {
    protected final LoginPage       loginPage               = PageFactory.getPageInstance(LoginPage.class);
    protected final CommonPageMain  commonPageMain          = PageFactory.getPageInstance(CommonPageMain.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    @Description("Positive test scenario. Login test")
    @AllureId("1.0")
    public void Successful_Login_Test() {
        loginPage.openMsalamonShopNoAssert();
        assertTrue(loginPage.isPageCorrect());
        loginPage.clickLogoutButton();
    }

    @Test
    @Description("Negative test scenario. Invalid login test")
    @AllureId("2.0")
    public void Invalid_Login_Test() {
        commonPageMain.goToLoginPage();
        loginPage.enterEmailAddress("TestTest@Email.com");
        loginPage.enterEmailPassword(MSALAMON_LOGIN_EMAIL_PASSWORD.toString());
    }

}
