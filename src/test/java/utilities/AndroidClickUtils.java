package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utilities.Driver.getAppiumDriver;


public class AndroidClickUtils {


    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    private static final Logger logger = LogManager.getLogger(AndroidClickUtils.class);
    private static final int DEFAULT_TIMEOUT = 10;

    public static void clickElement(AndroidDriver driver, String elementName) {
        var el1 = getAppiumDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"" + elementName + "\").instance(0)"));
        el1.click();
    }


    public static void clickByDescription(AndroidDriver driver, String buttonName) {
        // Yıldız (*) veya tırnak işaretlerinden arındırma
        logger.info("Student kullanıcısı üzerinde '{}' yazan butona tıklıyor", buttonName);

        try {


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


            WebElement button = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"" + buttonName + "\")"));

            logger.info("'{}' butonu görünür olana kadar bekleniyor...", buttonName);
            wait.until(ExpectedConditions.visibilityOf(button));
            logger.info("'{}' butonu görünür oldu", buttonName);


            if (button.isEnabled() && button.isDisplayed()) {
                logger.info("'{}' butonu tıklanabilir durumda", buttonName);
                button.click();
                logger.info("'{}' butonuna başarıyla tıklandı", buttonName);

            } else {

                logger.warn("'{}' butonu tıklanabilir değil! (enabled: {}, displayed: {})",
                        buttonName, button.isEnabled(), button.isDisplayed());
                logger.warn("Test devam ediyor...");
            }

           // ReusableMethods.wait(2);

        } catch (TimeoutException e) {

            logger.warn("'{}' butonu {} saniye beklenmesine rağmen görünür olmadı!", buttonName, 30);
            logger.warn("Test devam ediyor...");
            //ReusableMethods.wait(2);

        } catch (NoSuchElementException e) {

            logger.warn("'{}' butonu DOM'da bulunamadı!", buttonName);
            logger.warn("Test devam ediyor...");
           // ReusableMethods.wait(2);

        } catch (Exception e) {

            logger.warn("'{}' butonuna tıklanırken beklenmeyen hata: {}", buttonName, e.getMessage());
            logger.warn("Test devam ediyor...");
            //ReusableMethods.wait(2);
        }

    }

    public static void smartClick(AndroidDriver driver, String buttonName,
                                  java.util.function.Supplier<WebElement> elementSupplier) {
        try {
            WebElement element = elementSupplier.get();
            if (element != null) {
                logger.info("🎯 Element page class'ta tanımlı bulundu: {}", buttonName);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                logger.info("✅ '{}' elementine page class üzerinden tıklandı", buttonName);
                //ReusableMethods.wait(2);
                return;
            }
        } catch (Exception e) {
            logger.info("⚠️ Page class element alınamadı: {}. Description ile deneniyor...", e.getMessage());
        }

        // Page class'ta yoksa description ile tıkla
        logger.info("🔍 '{}' page class'ta bulunamadı, description ile tıklanıyor...", buttonName);
        clickByDescription(driver, buttonName);
    }

    public static void smartSendKeys(AndroidDriver driver, String elementName,
                                     String text,
                                     java.util.function.Supplier<WebElement> elementSupplier) {
        try {
            WebElement element = elementSupplier.get();
            if (element != null) {
                logger.info("🎯 Element page class'ta bulundu, sendKeys yapılıyor: {}", elementName);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
                wait.until(ExpectedConditions.visibilityOf(element));
                element.click();
                element.clear();
                element.sendKeys(text);
                logger.info("✅ '{}' elementine '{}' yazıldı", elementName, text);
                //ReusableMethods.wait(1);
                return;
            }
        } catch (Exception e) {
            logger.info("⚠️ Page class element alınamadı: {}. Description ile deneniyor...", e.getMessage());
        }

        // Fallback: description ile bul ve yaz
        logger.info("🔍 '{}' page class'ta yok, description ile yazılıyor...", elementName);
        sendKeysByDescription(driver, elementName, text);
    }

    public static void sendKeysByDescription(AndroidDriver driver, String elementName, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().description(\"" + elementName + "\")"));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.clear();
            element.sendKeys(text);
            logger.info("✅ '{}' (description) alanına '{}' yazıldı", elementName, text);
            //ReusableMethods.wait(1);
        } catch (Exception e) {
            logger.warn("❌ '{}' alanına yazılamadı: {}", elementName, e.getMessage());
        }
    }

    public static void sendKeysToTextBoxByIndex(String text, int index) {
        try {
            AndroidDriver driver = (AndroidDriver) getAppiumDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            WebElement textBox = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\").instance(" + index + ")"));

            wait.until(ExpectedConditions.elementToBeClickable(textBox));
            textBox.click();
            textBox.clear();
            textBox.sendKeys(text);
            logger.info("✅ Index {}'li text box'a '{}' değeri girildi", index, text);

        } catch (Exception e) {
            logger.error("❌ Index {}'li text box'a değer girilemedi: {}", index, e.getMessage());
            throw new RuntimeException("Text box'a değer girilemedi!", e);
        }
    }

    public static void verifyButtonByName(String buttonName) {

        logger.info(GREEN + "=".repeat(60) + RESET);
        logger.info(GREEN + "👁️ STUDENT BUTON GÖRÜNÜRLÜK TESTİ: " + buttonName + RESET);
        logger.info(GREEN + "=".repeat(60) + RESET);

        try {
            logger.info(CYAN + "📱 '{}' butonu aranıyor..." + RESET, buttonName);

            AndroidDriver driver = (AndroidDriver) getAppiumDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


            WebElement button = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"" + buttonName + "\")")
                    )
            );

            logger.info(YELLOW + "⏳ '{}' butonu bulundu, görünürlük kontrolü yapılıyor..." + RESET, buttonName);

            // Görünürlük ve enabled kontrolü
            if (button.isDisplayed() && button.isEnabled()) {
                logger.info(GREEN + "✅ '{}' butonu GÖRÜNÜR ve AKTİF" + RESET, buttonName);
                logger.info(GREEN + "🎯 {} butonu görünürlük testini BAŞARIYLA geçti" + RESET, buttonName);
                logger.info(GREEN + "=".repeat(60) + RESET);
                logger.info(GREEN + "✅ TEST BAŞARILI: " + buttonName + " elementi doğrulandı" + RESET);
                logger.info(GREEN + "=".repeat(60) + RESET);
            } else {
                logger.warn(YELLOW + "⚠️ '{}' butonu görünür: {}, aktif: {}" + RESET,
                        buttonName, button.isDisplayed(), button.isEnabled());
                logger.warn(RED + "=".repeat(60) + RESET);
                logger.warn(RED + "⚠️ UYARI: " + buttonName + " butonu görünür veya aktif değil" + RESET);
                logger.warn(RED + "=".repeat(60) + RESET);

            }

        } catch (NoSuchElementException e) {
            logger.error(RED + "❌ '{}' butonu BULUNAMADI: {}" + RESET, buttonName, e.getMessage());
            logger.error(RED + "=".repeat(60) + RESET);
            logger.error(RED + "❌ HATA: " + buttonName + " butonu UI'da mevcut değil" + RESET);
            logger.error(RED + "=".repeat(60) + RESET);
            // Test devam eder
        } catch (TimeoutException e) {
            logger.error(RED + "⏰ '{}' butonu BEKLENEN SÜREDE BULUNAMADI: {}" + RESET, buttonName, e.getMessage());
            logger.error(RED + "=".repeat(60) + RESET);
            logger.error(RED + "❌ HATA: " + buttonName + " butonu için timeout oluştu" + RESET);
            logger.error(RED + "=".repeat(60) + RESET);
            // Test devam eder
        } catch (Exception e) {
            logger.error(RED + "💥 '{}' butonu DOĞRULAMA SIRASINDA HATA: {}" + RESET, buttonName, e.getMessage());
            logger.error(RED + "=".repeat(60) + RESET);
            logger.error(RED + "❌ KRİTİK HATA: " + buttonName + " butonu doğrulanamadı" + RESET);
            logger.error(RED + "=".repeat(60) + RESET);

        }

    }

    public static void clickOnCenterOfBounds(AppiumDriver driver, String bounds) {
        // "[112,725][188,801]" formatını ayrıştırmak için bir regex deseni
        Pattern pattern = Pattern.compile("\\[(\\d+),(\\d+)\\]\\[(\\d+),(\\d+)\\]");
        Matcher matcher = pattern.matcher(bounds);

        if (matcher.matches()) {
            // Koordinatları yakala
            int x1 = Integer.parseInt(matcher.group(1));
            int y1 = Integer.parseInt(matcher.group(2));
            int x2 = Integer.parseInt(matcher.group(3));
            int y2 = Integer.parseInt(matcher.group(4));

            // Merkez noktayı hesapla
            int centerX = (x1 + x2) / 2;
            int centerY = (y1 + y2) / 2;

            System.out.println("Hesaplanan merkez koordinatlara tıklanıyor: (" + centerX + ", " + centerY + ")");

            // Modern Appium (W3C Actions) ile tıklama işlemi
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(new Pause(finger, Duration.ofMillis(100))); // Tıklama süresi
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(tap));

        } else {
            // Eğer bounds formatı geçersizse hata fırlat
            throw new IllegalArgumentException("Geçersiz bounds formatı. Beklenen format: '[x1,y1][x2,y2]', Gelen: " + bounds);
        }
    }

    public static void scrollWithUiScrollableContentDesc(String elementText) {

        Driver.getAppiumDriver().findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().description(\"" + elementText + "\"));"
                )
        );
    }

    public static void KeyBack() {
        AndroidDriver driver = (AndroidDriver) getAppiumDriver();
        // Geri tuşuna basın
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

    }

    public static void xPathElementClick(String itemName, String reviews, String price) {
        String xpathExpression = String.format("//android.view.View[contains(@content-desc, '%s') and contains(@content-desc, '%s') and contains(@content-desc, '%s')]/android.widget.ImageView", itemName, reviews, price);

        // Öğeyi bulma
        WebElement element = getAppiumDriver().findElement(AppiumBy.xpath(xpathExpression));

        // Öğeyle etkileşim
        element.click();

    }

    public static void hideKeyboard() {
        AndroidDriver driver = (AndroidDriver) getAppiumDriver();
        driver.hideKeyboard(); // Klavyeyi kapatma komutu
    }





}