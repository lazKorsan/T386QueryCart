package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utilities.Driver;
import utils.AppiumServer;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "stepdefinitions.LoggingPlugin",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        features = "src/test/resources/Features",
        glue = {"stepdefinitions"},

        dryRun = false,


        publish = true
)
public class TestRunner {

    private static final Logger logger = LogManager.getLogger(TestRunner.class);

    @BeforeClass
    public static void setup() {
        logger.info("Appium  Test Runner başlatılıyor...");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Test çalışması tamamlandı. Sunucu kapatılıyor...");
        Driver.quitAppiumDriver();
    }


}