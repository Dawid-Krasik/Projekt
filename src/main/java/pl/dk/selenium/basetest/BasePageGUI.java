package pl.dk.selenium.basetest;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.ResolutionEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.DriverManager;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.selenium.core.utils.ResolutionUtils;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import pl.dk.framework.actions.PerformAction;
import pl.dk.selenium.enums.Timeslots;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public abstract class BasePageGUI extends BasePage {
    private static final ThreadLocal<INewWebDriver>	drv					= new ThreadLocal<>();
    private static final ThreadLocal<Boolean>		changeResolution	= new ThreadLocal<>();
    private List<String>                            urls				= new ArrayList<>();
    private String browserWindowHandle                                  = getDriver().getWindowHandle();

    public static INewWebDriver getDriver() {
        changeResolution.set(hasDriverQuit());
        INewWebDriver driver = BasePage.getDriver();
        drv.remove();
        drv.set(driver);
        if (changeResolution.get() != null && changeResolution.get()) {
            driver.manage()
                    .window()
                    .maximize();
            Dimension size = driver.manage()
                    .window()
                    .getSize();
            ResolutionEnum resolution = ResolutionEnum.w1920;
            if (size.getHeight() > resolution.getHeight() || size.getWidth() > resolution.getWidth()) {
                ResolutionUtils.setResolution(driver, resolution);
            }
        }
        return driver;
    }

    public static boolean hasDriverQuit() {
        INewWebDriver driver = drv.get();
        if (driver != null) {
            boolean quit = driver.toString()
                    .contains("(null)");
            if (quit) {
                drv.remove();
            }
            return quit;
        }
        return true;
    }

    protected abstract boolean isPageCorrect();

    public final void isPageCorrectAssert() {
        isPageCorrectAssert(true);
    }

    private void isPageCorrectAssert(boolean logSuccessStep) {
        long timeBegin = System.currentTimeMillis();
        boolean correct = isPageCorrect();
        String time = DateTime.getTimeFromMilliseconds(System.currentTimeMillis() - timeBegin);
        String className = PerformAction.splitCamelCase(this.getClass()
                .getSimpleName());
        String url = "";
        if (this.urls.size() < 1) {
            url = "Only the presence of an element on the page was checked";
        } else if (this.urls.size() < 2) {
            url = "URL: " + this.urls.get(0);
        } else {
            url += "One of URLs:\n";
            for (String alternatives : this.urls) {
                url += "\t" + alternatives + "\n";
            }
            url = url.trim();
        }
        String msgPattern = "{0} was {1}opened\n{2}\nTime: {3}";
        if (!correct) {
            makeSourcePageOnFailure();
            fail(MessageFormat.format(msgPattern, className, "not ", url, time));
        }
        if (logSuccessStep) {
            StepLogger.step(MessageFormat.format(msgPattern, className, "", url, time));
        }
    }

    private boolean isURLMatchList(String currentURL) {
        for (String url : this.urls) {
            Pattern pattern = Pattern.compile(".*" + url + ".*");
            if (pattern.matcher(currentURL)
                    .matches()) {
                return true;
            }
        }
        return this.urls.isEmpty();
    }

    protected final boolean isPageCorrect(By selector, Object[] urlArray) {
        this.urls = Arrays.stream(urlArray)
                .map(Object::toString)
                .collect(Collectors.toList());
        boolean selectorObjectVisible = false;
        boolean urlMatch = false;
        long timeBegin = System.currentTimeMillis();
        long timeCounter = System.currentTimeMillis() - timeBegin;
        String currentURL = "";
        while (timeCounter < Timeslots.WAIT_FOR_LAGGY_PAGE_MS) {
            if (hasDriverQuit()) {
                StepLogger.error("Driver is closed");
                return false;
            }
            selectorObjectVisible = PerformAction.isVisible(selector);
            currentURL = PerformAction.getCurrentURL();
            urlMatch = isURLMatchList(currentURL);
            timeCounter = System.currentTimeMillis() - timeBegin;
            if (selectorObjectVisible && urlMatch) {
                break;
            }
            // Error check
            // ErrorPage.check();
        }

        if (!selectorObjectVisible) {
            StepLogger.error("Page object not visible. Selector: " + selector.toString());
        }
        if (!urlMatch) {
            StepLogger.error(MessageFormat.format("Current URL ({0}) not match to list ({1})", currentURL, urls));
        }

        return selectorObjectVisible && urlMatch;
    }

    protected final boolean isPageCorrect(By selector, Object url) {
        return isPageCorrect(selector, new Object[] { url });
    }

    protected final boolean isPageCorrect(By selector) {
        return this.isPageCorrect(selector, new Object[0]);
    }

    //overide abstract
    @Override
    public final boolean isLoaded() {
        return false;
    }

    @Override
    public final void load() {
    }

    @Override
    public final String pageTitle() {
        return null;
    }

    @Override
    public void onTestClassFinish() {
        BFLogger.logDebug("[AFTER CLASS - BASE PAGE GUI] onTestClassFinish");
        DriverManager.closeDriver();
    }

    static void makeScreenShot(String attachmentName) {
        BFLogger.logInfo("*makeScreenShot*");
        if (System.getProperty("screenshots", "true")
                .equalsIgnoreCase("false")) {
            BFLogger.logInfo("screenshot disabled");
        } else if (!hasDriverQuit()) {
            makeScreenShotAlways(attachmentName);
        } else {
            StepLogger.info("Unable to take screenshot - No driver");
        }
    }

    @Attachment(value = "{attachmentName}", type = "image/png")
    private static byte[] makeScreenShotAlways(String attachmentName) {
        BFLogger.logDebug("BasePageGUI.makeScreenShotAlways attachmentName=" + attachmentName);
        byte[] screenshot = new byte[0];
        try {
            screenshot = Files.readAllBytes(Paths.get("src/resources/img/noImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!hasDriverQuit()) {
            try {
                INewWebDriver driver = getDriver();
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } catch (Exception e) {
                e.printStackTrace();
                return screenshot;
            }
        } else {
            BFLogger.logDebug("Unable to take screenshot - No driver");
        }
        return screenshot;
    }

    public void activate() {
        getDriver().switchTo()
                .window(browserWindowHandle);
    }
}