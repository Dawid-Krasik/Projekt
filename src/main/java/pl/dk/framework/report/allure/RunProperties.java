package pl.dk.framework.report.allure;

import pl.dk.selenium.basetest.BasePageGUI;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.google.inject.Singleton;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

@Singleton
public final class RunProperties {
    private static volatile RunProperties instance;

    private RunProperties() {
    }

    public static synchronized RunProperties getInstance() {
        if (instance == null) {
            synchronized (RunProperties.class) {
                if (instance == null) {
                    instance = new RunProperties();
                    setRunProperties();

                }
            }
        }
        return instance;
    }

    private static void setRunProperties() {
        AllureEnvironmentGenerator.deleteEnvironmentFile();
        try {
            AllureEnvironmentGenerator.addEnvironmentVariable("Operating.System= " + System.getProperty("os.name") + " | " + System.getProperty("os.version"));
            AllureEnvironmentGenerator.addEnvironmentVariableIfNotExist("Browser= " + System.getProperty("browser"));
            AllureEnvironmentGenerator.addEnvironmentVariable("Environment= " + System.getProperty("env"));

            AllureEnvironmentGenerator.addEnvironmentVariable("Thread.Count= " + System.getProperty("thread.count"));
            AllureEnvironmentGenerator.addEnvironmentVariable("Test.Suite= " + System.getProperty("test"));
            Capabilities caps = ((RemoteWebDriver) BasePageGUI.getDriver()).getCapabilities();
            AllureEnvironmentGenerator.addEnvironmentVariable("Browser.Version= " + caps.getVersion());

            if (System.getProperty("seleniumGrid") != null) {
                AllureEnvironmentGenerator.addEnvironmentVariable("Selenium.Grid= " + System.getProperty("seleniumGrid"));
            }

        } catch (IOException e) {
            BFLogger.logError("Failed to add environment variable");
            e.printStackTrace();
        }
    }
}