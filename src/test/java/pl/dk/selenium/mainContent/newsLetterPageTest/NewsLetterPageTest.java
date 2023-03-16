package pl.dk.selenium.mainContent.newsLetterPageTest;


import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Scenario;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import pl.dk.selenium.mainContent.newsLetterPage.NewsLetterPage;

@App.Msalamon
@Type.Regression
@Story(Scenario.TEST_SCENARIO_ID_4)
public class NewsLetterPageTest extends CommonTest {
    protected final NewsLetterPage  newsLetterPage  = PageFactory.getPageInstance(NewsLetterPage.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    @Description("Positive test scenario. Send 'NewsLetter' form all valid params")
    @AllureId("1.0")
    public void sendingNewsLetterFromCorret() {
        newsLetterPage.openNewsLetterPageNoAssert();
        newsLetterPage.fillNewsLetterForm("Test", "Test@Test.com");
        newsLetterPage.acceptPolicesCheckBox();
        newsLetterPage.clikSendFormButton();
    }
    @Test
    @Description("Negative test scenario. Send 'NewsLetter' form - without (name and Email) ")
    @AllureId("2.0")
    public void sendingNewsLetterWithoutNameAndEmail() {
        newsLetterPage.openNewsLetterPageNoAssert();
        newsLetterPage.acceptPolicesCheckBox();
        newsLetterPage.clikSendFormButton();
    }

    @Test
    @Description("Negative test scenario. Send 'NewsLetter' form - without (Polices check box) ")
    @AllureId("3.0")
    public void sendingNewsLetterWithoutPolices() {
        newsLetterPage.openNewsLetterPageNoAssert();
        newsLetterPage.fillNewsLetterForm("Test", "Test@Test.com");
        newsLetterPage.clikSendFormButton();
    }
}
