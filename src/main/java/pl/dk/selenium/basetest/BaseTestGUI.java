package pl.dk.selenium.basetest;

import pl.dk.framework.report.allure.RunProperties;
import com.capgemini.mrchecker.selenium.core.newDrivers.DriverManager;
import com.capgemini.mrchecker.test.core.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import java.text.MessageFormat;
import static org.junit.Assume.assumeTrue;

public abstract class BaseTestGUI extends BaseTest {
    private boolean skippedSetUp;

    @BeforeEach
    public void mySetUpClass() {
        RunProperties.getInstance();
    }

    @Before
    public void setUp() {
        String messageFormat = "[PRE] Error:\n{0}";
        try {
            setUpTest();
        } catch (Throwable e) {
            skippedSetUp = true;
            StepLogger.makeScreenShot();
            assumeTrue(MessageFormat.format(messageFormat, e.getMessage()), false);
        }
    }

    @After
    public void tearDown() {
        String messageFormat = "[POST] Error:\n{0}";
        if (!skippedSetUp) {
            try {
                tearDownTest();
            } catch (Throwable e) {
                StepLogger.error(MessageFormat.format(messageFormat, e.getMessage()));
            }
        }
        DriverManager.closeDriver();
    }

    public abstract void tearDownTest();

    public abstract void setUpTest();

}
