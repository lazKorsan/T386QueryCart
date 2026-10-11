package page;

import enev.ConfigWriter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.AndroidClickUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegisterPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(RegisterPage.class);


    public RegisterPage() {
        super();
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Edit Profile\")")
    public WebElement editProfileButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Save Changes\")")
    public WebElement saveChangesButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    public WebElement nameBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    public WebElement emailBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
    public WebElement passwordBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign Up\").instance(1)")
    public WebElement submitButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign In\").instance(1)")
    private WebElement signInButton;

    public WebElement getElementByName(String elementName) {
        if (elementName == null || elementName.isBlank()) {
            return null;
        }

        String normalized = elementName.strip().toLowerCase(Locale.ROOT);

        return switch (normalized) {
            case "edit profile" -> editProfileButton;
            case "save changes" -> saveChangesButton;
            case "name" -> nameBox;
            case "email" -> emailBox;
            case "password" -> passwordBox;
            case "submit" -> submitButton;
            case "sign in" -> signInButton;
            default -> null;
        };
    }

    public void registerWithMail(String name, String password) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String email = name + ".customer." + timestamp + "@QueryCart.com";

        AndroidClickUtils.clickByDescription(driver, "Sign Up");
        AndroidClickUtils.clickByDescription(driver, "*Use Email Instead");
        AndroidClickUtils.sendKeysToTextBoxByIndex(name, 0);
        AndroidClickUtils.sendKeysToTextBoxByIndex(email, 1);
        AndroidClickUtils.sendKeysToTextBoxByIndex(password, 2);

        submitButton.click();

        String keyMail = "mail" + timestamp;
        String keyPassword = "password" + timestamp;

        // todo *** src/main/java/enev/accountInfo/account.properties
        ConfigWriter.setProperty(keyMail, email);
        ConfigWriter.setProperty(keyPassword, password);
    }

    public void registerWithPhoneNumber(String name, String phoneNumber, String password) {
        logger.info("Student kullanıcısı kayıt oluyor - İsim: {}, Telefon: {}, Şifre: {}", name, phoneNumber, password);

        String finalPhoneNumber = phoneNumber;
        String timestamp = null;
        if (phoneNumber.contains("{timeStamp}")) {
            timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
            finalPhoneNumber = phoneNumber.replace("{timeStamp}", timestamp);
            logger.info("Telefon numarasına timestamp eklendi: {}", finalPhoneNumber);
        }

        try {
            AndroidClickUtils.clickByDescription(driver, "Profile");
            logger.info("Profile butonuna tıklandı");

            AndroidClickUtils.clickByDescription(driver, "Sign Up");
            logger.info("Sign Up butonuna tıklandı");

            AndroidClickUtils.sendKeysToTextBoxByIndex(name, 0);
            logger.info("İsim girildi: {}", name);

            AndroidClickUtils.sendKeysToTextBoxByIndex(finalPhoneNumber, 1);
            logger.info("Telefon numarası girildi: {}", finalPhoneNumber);

            AndroidClickUtils.sendKeysToTextBoxByIndex(password, 2);
            logger.info("Şifre girildi");

            submitButton.click();
            logger.info("Submit butonuna tıklandı");

            logger.info("✅ Kayıt işlemi başarıyla tamamlandı!");

        } catch (Exception e) {
            logger.error("❌ Kayıt işlemi sırasında hata oluştu: {}", e.getMessage());
            throw new RuntimeException("Kayıt işlemi başarısız!", e);
        }

        // todo test basarili ise degerler src/main/java/enev/accountInfo/account.properties icine yazilir
        String nameKey = "name" + timestamp;
        String passwordKey = "password" + timestamp;
        String phoneNumberKey = "phone" + timestamp;
        ConfigWriter.setProperty(nameKey, name);
        ConfigWriter.setProperty(phoneNumberKey, finalPhoneNumber);
        ConfigWriter.setProperty(passwordKey, password);
    }

    public void loginWithPhoneNumber(String phoneNumber, String password) {
        AndroidClickUtils.clickByDescription(driver, "Profile");
        logger.info("Student kullanicisi uzerinde {} yazan butonuna tiklar", "Profile");

        AndroidClickUtils.clickByDescription(driver, "Sign In");
        logger.info("Student kullanicisi uzerinde {} yazan butonuna tiklar", "Sign In");

        AndroidClickUtils.sendKeysToTextBoxByIndex(phoneNumber, 0);
        logger.info("Student kullanicisi phoneBox kutusuna {} degerini yazar", phoneNumber);

        AndroidClickUtils.sendKeysToTextBoxByIndex(password, 1);
        logger.info("Student kullanicisi passwordBox kutusuna {} degerini yazar", password);

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.ENTER).perform();
        logger.info("remember me butonuna tiklandi");

        signInButton.click();
    }

    public void loginWithMail(String email, String password) {
        AndroidClickUtils.clickByDescription(driver, "Sign In");

        AndroidClickUtils.clickByDescription(driver, "*Use Email Instead");
        logger.info("Student kullanicisi uzerinde {} yazan butonuna tiklar", "*Use Email Instead");

        AndroidClickUtils.sendKeysToTextBoxByIndex(email, 0);
        logger.info("email box kutusna {} yazan butonuna tiklar ", email);

        AndroidClickUtils.sendKeysToTextBoxByIndex(password, 1);
        logger.info("password box kutusna {} yazan butonuna tiklar ", password);

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.ENTER).perform();
        logger.info("remember me butonuna tiklandi");

        signInButton.click();
    }
}