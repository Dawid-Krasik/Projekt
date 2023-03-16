package pl.dk.framework.actions;

import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pl.dk.selenium.CommonPage;
import pl.dk.selenium.basetest.BasePageGUI;
import pl.dk.selenium.enums.Timeslots;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class PerformAction {
    private static final ThreadLocal<CommonPage>  stubPage = new ThreadLocal<>();

    private PerformAction() {}
    public static INewWebDriver getDriver() {
        if (BasePageGUI.hasDriverQuit() || stubPage.get() == null) {
            stubPage.remove();
            stubPage.set(new CommonPage());
        }
        CommonPage page = stubPage.get();
        return page.getDriver();
    }

    public static Actions getAction() {
        return new Actions(getDriver());
    }

    public static String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }

    public static boolean isVisible(By selector) {
        try {
            WebElement webElement = getDriver().findElementDynamic(selector, 0);
            return webElement.isDisplayed();
        } catch (RuntimeException ignored) {
        }
        return false;
    }

    public static boolean scrollWindowToElementAssert (By selector) {
        try {
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                js.executeScript("window.scrollBy(0, 500)");
                return isVisible(selector);
            } catch (RuntimeException ignored){
                    }
            return false;
    }

    public static boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (RuntimeException ignored) {
        }
        return false;
    }

    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"),
                " ");
    }

    public static void waitMilliseconds(Integer milliseconds) {
        BFLogger.logDebug("[waitMilliseconds] " + milliseconds);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            assumeTrue(MessageFormat.format("WAIT: {0} thread interrupted", Thread.currentThread()
                    .getName()), false);
        }
    }

    public static void waitForPageLoaded() {
        waitMilliseconds(Timeslots.WAIT_INSTANT_MOMENT_MS);
        getDriver().waitForPageLoaded();
    }

    public static boolean waitForElementVisibleNoAssertion(By selector) {
        return waitForElementVisibleNoAssertion(selector, Timeslots.WAIT_LONG_MOMENT_MS);
    }

    public static boolean waitForElementVisibleNoAssertion(By selector, int milliseconds) {
        long timeBegin = System.currentTimeMillis();
        long timeCounter = 0;
        boolean visible = false;
        while (timeCounter < milliseconds && !visible) {
            visible = isVisible(selector);
            timeCounter = System.currentTimeMillis() - timeBegin;
        }
        return visible;
    }

    public static boolean isEnabled(By selector) {
        boolean isEditable;
        try {
            WebElement webElement = getDriver()
                    .findElementDynamic(selector);
            isEditable = isEnabled(webElement);
        } catch (RuntimeException a) {
            isEditable = false;
        }
        return isEditable;
    }

    public static boolean isEnabled(WebElement element) {
        boolean isEditable;
        try {
            boolean enabled = element.isEnabled();
            boolean classUiStateDisabled = element.getAttribute("class") != null && element.getAttribute("class")
                    .contains("ui-state-disabled");
            boolean classAriaDisabled = element.getAttribute("aria-disabled") != null && element.getAttribute("aria-disabled")
                    .contains("true");
            isEditable = enabled && !classUiStateDisabled && !classAriaDisabled;
        } catch (RuntimeException a) {
            isEditable = false;
        }
        return isEditable;
    }

    public static void waitForElementVisible(By selector, String elementName) {
        assertTrue(elementName + " element is not visible", waitForElementVisibleNoAssertion(selector));
    }

    public static void clickStepless(By selector) {
        int attempts = 0;
        while (attempts < 4) {
            try {
                waitUntilClickable(selector);
                getDriver().findElementDynamic(selector)
                        .click();
                return;
            } catch (RuntimeException ignored) {
            }
            attempts++;
            waitMilliseconds(Timeslots.WAIT_INSTANT_MOMENT_MS);
        }
        fail("Can't click element. Selector: " + selector);
    }

    public static void waitUntilClickable(By selector) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                getDriver()
                        .waitUntilElementIsClickable(selector);
                break;
            } catch (RuntimeException ignored) {
            }
            attempts++;
        }
    }

    public static void clickSteplessForSubmitButton(By selector) {
        try {
            waitUntilClickable(selector);
            getDriver().findElementDynamic(selector)
                    .click();
            return;
        } catch (RuntimeException ignored) {
        }
        BFLogger.logInfo("False negative element - Disabled. Infinite loop release");
    }

    private static void tryTriggerEventsOnInput(WebElement element) {
        try {
            getAction().keyDown(Keys.LEFT_SHIFT)
                    .keyUp(Keys.LEFT_SHIFT)
                    .build()
                    .perform();
        } catch (RuntimeException ignored) {
        }
    }

    public static void clearTextFieldStepless(By selector) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                WebElement element = getDriver()
                        .findElementDynamic(selector);
                waitUntilClickable(element);
                element.clear();
                tryTriggerEventsOnInput(element);
                return;
            } catch (RuntimeException ignored) {
            }
            attempts++;
        }
        fail("Can't clear text field. Selector: " + selector);
    }

    private static void waitUntilClickable(WebElement element) {
        boolean clickable = false;
        long timeBegin = System.currentTimeMillis();
        long timeCounter = 0;
        while (!clickable && timeCounter < Timeslots.WAIT_LONG_MOMENT_MS) {
            clickable = isVisible(element) && isEnabled(element);
            timeCounter = System.currentTimeMillis() - timeBegin;
        }
    }

    @Step("Click button \"{buttonName}\"")
    public static void clickButton(By selector, String buttonName) {
        waitForElementVisible(selector, buttonName + " button");
        assertTrue(MessageFormat.format("Button \"{0}\" is not visible", buttonName), PerformAction.isVisible(selector));
        assertTrue(MessageFormat.format("Button \"{0}\" is disabled", buttonName), PerformAction.isEnabled(selector));
        BFLogger.logInfo(MessageFormat.format("Click button \"{0}\"", buttonName));
        clickStepless(selector);
    }

    @Step("Click element Disabled \"{name}\"")
    public static void clickElementDisabledWithOutWaiting(By selector, String name) {
        BFLogger.logInfo(MessageFormat.format("Click element \"{0}\"", name));
        clickSteplessForSubmitButton(selector);
    }

    public static String getTextFieldValue(By selector) {
        String result;
        int attempts = 0;
        while (attempts < 2) {
            try {
                result = getDriver()
                        .findElementDynamic(selector)
                        .getAttribute("value");
                return result;
            } catch (RuntimeException ignored) {
            }
            attempts++;
        }
        fail("Can't get text from field. Selector: " + selector);
        return null;
    }

    public static String getTextFromTextbox (By selector){
        isVisible(selector);
        try {
            return getDriver().findElementDynamic(selector).getText();
        }
        catch (Exception exception) {
            BFLogger.logInfo("Exception get element text: " + exception.getMessage());
        }
        waitMilliseconds(Timeslots.WAIT_SHORT_MOMENT_MS);

        fail("Can't get text from text box: " + selector);
        return null;
    }

    public static List<WebElement> findElementDynamics(By selector) {
        List<WebElement> result = new ArrayList<>();
        try {
            result = getDriver().findElementDynamics(selector);
        } catch (RuntimeException ignored) {
        }
        return result;
    }

    @Step("Select value from DropDown menu, without typing by Sorting Options \"{Sorting Options}\"")
    public static void selectOptionsDropDownBySortingOptions(By selectorDropBox, String SortingOptions) {
        List<WebElement> dropdowns = findElementDynamics(selectorDropBox);
        assertEquals("There are more than one dropdown with this selector: " + selectorDropBox, 1, dropdowns.size());
        WebElement dropdown = dropdowns.get(0);
        dropdown.click();
        Select sortingOptionSelect = new Select(dropdown);
        sortingOptionSelect.selectByVisibleText(SortingOptions);
    }

    public static String fillTextFieldSteplessInstant(By selector, String inputValue) {
        WebElement element = getDriver().findElementDynamic(selector);
        element.sendKeys(inputValue);
        if (inputValue.equals(Keys.ENTER.toString()) || inputValue.equals(Keys.TAB.toString())) {
            return inputValue;
        }
        tryTriggerEventsOnInput(element);
        return getTextFieldValue(selector);
    }

    public static String fillTextFieldSteplessByTyping(WebElement element, String inputValue) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                waitUntilClickable(element);
                for (int i = 0; i < inputValue.length(); i++) {
                    char c = inputValue.charAt(i);
                    element.sendKeys(Character.toString(c));
                    waitMilliseconds(Timeslots.WAIT_TYPING_DELAY_MS);
                }
                tryTriggerEventsOnInput(element);
                return getTextFieldValue((By) element);
            } catch (RuntimeException e) {
                clearTextFieldStepless((By) element);
                waitMilliseconds(Timeslots.WAIT_INSTANT_MOMENT_MS);
            }
            attempts++;
        }

        fail("Can't fill text field. Element: " + element);
        return null;
    }

    public static String fillTextFieldStepless(By selector, String inputValue) {
        try {
            String inputtedValue = fillTextFieldSteplessInstant(selector, inputValue);
            if (inputtedValue.equals(inputValue)) {
                return inputtedValue;
            }
            BFLogger.logInfo("Slow typing needed");
        } catch (RuntimeException e) {
            clearTextFieldStepless(selector);
        }
        clearTextFieldStepless(selector);
        return fillTextFieldSteplessByTyping((WebElement) selector, inputValue);
    }

    @Step("Fill text field \"{fieldName}\" with \"{inputValue}\"")
    public static String fillTextField(By selector, String inputValue, String fieldName) {
        BFLogger.logInfo(MessageFormat.format("Fill text field \"{0}\" with \"{1}\"", fieldName, inputValue));
        return fillTextFieldStepless(selector, inputValue);
    }
}
