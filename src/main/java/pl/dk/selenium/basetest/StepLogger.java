package pl.dk.selenium.basetest;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.util.NoSuchElementException;
import java.util.UUID;

public final class StepLogger {

    private StepLogger() {
    }

    @Step("[INFO] {info}")
    public static void info(String info) {
        BFLogger.logInfo("[INFO] " + info);
    }

    @SuppressWarnings("deprecation")
    public static void error(String error) {
        String message = "[ERROR] " + error;
        String uuid = UUID.randomUUID()
                .toString();
        try {
            AllureLifecycle allureLifeCycle = Allure.getLifecycle();
            allureLifeCycle.startStep(uuid, new StepResult().withStatus(Status.FAILED)
                    .withName(message));
            BFLogger.logError(message);
//            makeScreenShot();
            allureLifeCycle.stopStep(uuid);
        } catch (NoSuchElementException e) {
            info(message);
        }
    }

    @Step("{step}")
    public static void step(String step) {
        BFLogger.logInfo(step);
    }

    @Step("--Screenshot--")
    public static void makeScreenShot() {
        BasePageGUI.makeScreenShot("Screenshot");
    }
}
