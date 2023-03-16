package pl.dk.selenium.mainContent.menuPagesTest;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Type;
import pl.dk.selenium.mainContent.CommonPageMain;
import pl.dk.selenium.mainContent.CommonTest;

@App.Msalamon
@Type.Regression
public class CoffeePageTest extends CommonTest {
    protected final CommonPageMain commonPageMain          = PageFactory.getPageInstance(CommonPageMain.class);

    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    public void test() {
        BFLogger.logInfo("OK");
        commonPageMain.goToCoffePage();
    }
}
