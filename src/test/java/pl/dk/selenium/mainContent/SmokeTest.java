package pl.dk.selenium.mainContent;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.junit.jupiter.api.Test;
import pl.dk.categories.App;
import pl.dk.categories.Type;

@App.Msalamon
@Type.Smoke
public class SmokeTest extends CommonTest {
    @Override
    public void setUpTest() {
        super.setUpTest();
    }

    @Override
    public void tearDownTest() {
    }

    @Test
    public void smokeTest() {
        BFLogger.logInfo("Smoke Test");
    }
}
