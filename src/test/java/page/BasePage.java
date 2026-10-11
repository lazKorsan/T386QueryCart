package page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import runner.StudentRunner;
import utilities.Driver;
import utils.AppiumServer;
import utils.EmulatorStarter;

import java.time.Duration;

public abstract class BasePage {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_RED_BOLD = "\u001B[31;1m";
    public static final String ANSI_BG_00C853 = "\u001B[48;2;0;200;83m";
    public static final String ANSI_FG_FFFFFF = "\u001B[38;2;255;255;255m";
    public static final String ANSI_BG_E74C3C = "\u001B[48;2;231;76;60m";
    public static final String ANSI_BG_FFD700 = "\u001B[48;2;255;215;0m";
    public static final String ANSI_FG_000000 = "\u001B[38;2;0;0;0m";
    public static final String ANSI_BG_3498DB = "\u001B[48;2;52;152;219m";
    public static final String ANSI_DIM = "\u001B[2m";
    public static final String ANSI_ITALIC = "\u001B[3m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ALTI_CIZME = "\u001B[4m";

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected AndroidDriver driver;

    public BasePage() {
        this.driver = (AndroidDriver) Driver.getAppiumDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    public static void setEnvirmentReport() {

        long startTime = System.currentTimeMillis();
        logger.info(success("Emulator Baslatma Komut Zamani: " + startTime));

        // Emulator baslatma komutu
        EmulatorStarter.startAndWait();

        long endTime = System.currentTimeMillis();
        logger.info(success("Emulatorun Kullanima Hazir Oldugu Zaman: " + endTime));

        long elapsedTime = endTime - startTime;

        logger.info(info("Emulator Baslamasi icin gecen sure: " + redBold(String.valueOf(elapsedTime)) + info(" ms")));
        // appiumServer baslatma komutu
        AppiumServer.startServer();
    }

    public static String success(String text) {
        return ANSI_BG_00C853 + ANSI_FG_FFFFFF + ANSI_BOLD + text + ANSI_RESET;
    }

    public static String error(String text) {
        return ANSI_BG_E74C3C + ANSI_FG_FFFFFF + ANSI_BOLD + text + ANSI_RESET;
    }

    public static String warning(String text) {
        return ANSI_BG_FFD700 + ANSI_FG_000000 + ANSI_BOLD + text + ANSI_RESET;

    }

    public static String info(String text) {
        return ANSI_BG_3498DB + ANSI_FG_FFFFFF + ANSI_BOLD + text + ANSI_RESET;
    }

    static String redBold(String text) {
        return ANSI_RED_BOLD + text + ANSI_RESET;
    }

}