package pl.dk.selenium.mainContent.homePageTest;

import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

@App.Msalamon
@Type.Regression
public class HomePageTest extends CommonTest {

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    public void test() {
        BFLogger.logInfo("test");
    }
}
