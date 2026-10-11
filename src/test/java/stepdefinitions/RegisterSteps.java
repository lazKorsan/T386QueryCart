package stepdefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import page.RegisterPage;
import utilities.AndroidClickUtils;
import utilities.Driver;
import utilities.TouchMethods;

import static utilities.Driver.getAppiumDriver;

public class RegisterSteps {

    private static final Logger logger = LogManager.getLogger(RegisterSteps.class);
    private final RegisterPage registerPage = new RegisterPage();

    @Given("Register kullanicisi uyugulamayi acar")
    public void register_kullanicisi_uyugulamayi_acar() {
        getAppiumDriver();
        logger.info("Uygulama başarıyla açıldı");

        // Guard Gate Pattern
        AndroidClickUtils.verifyButtonByName("Profile");
    }

    @When("Register kullanicisi {string} butonun fonksiyonlarini test eder")
    public void registerKullanicisiButonunFonksiyonlariniTestEder(String buttonName) {
        AndroidClickUtils.verifyButtonByName(buttonName);
    }

    @When("Register kullanicisi {string} butona tiklar")
    public void registerKullanicisiButonaTiklar(String buttonName) {
        AndroidClickUtils.smartClick((AndroidDriver) Driver.getAppiumDriver(), buttonName, () -> registerPage.getElementByName(buttonName));
    }

    @When("Register kullanicisi {string} box kutusuna {string} yazar")
    public void registerKullanicisiBoxKutusunaYazar(String boxName, String text) {
        AndroidClickUtils.smartSendKeys((AndroidDriver) Driver.getAppiumDriver(), boxName, text, () -> registerPage.getElementByName(boxName));
    }

    @When("Register kullanicisi {string} yazi elementinin gorunurlugu ile kayit olundugunu dogrular")
    public void registerKullanicisiYaziElementininGorunurluguIleKayitOlundugunuDogrular(String buttonName) {
        AndroidClickUtils.verifyButtonByName(buttonName);
    }

    @When("Register kullanicisi {string} box kutusuna index {int} ile {string} yazar")
    public void registerKullanicisiBoxKutusunaIndexIleYazar(String boxName, int index, String text) {
        AndroidClickUtils.sendKeysToTextBoxByIndex(text, index);
        logger.info("Register kullanicisi {} box kutusuna index {} ile {} yazar", boxName, index, text);
    }

    @When("Register kullanicisi {string} ismi ve {string} sifresi ile kayit olur")
    public void registerKullanicisiIsmiVeSifresiIleKayitOlur(String name, String password) {
        registerPage.registerWithMail(name, password);
    }

    @When("Register kullanicisi {string} , {string} telefon numarasi ve {string} degerleri ile kayt olur")
    public void registerKullanicisiTelefonNumarasiVeDegerleriIleKaytOlur(String name, String phoneNumber, String password) {
        logger.info("Student kullanicisi {} , {} telefon numarasi ve {} degerleri ile kayt olur", name, phoneNumber, password);
        registerPage.registerWithPhoneNumber(name, phoneNumber, password);
    }

    @When("Register kullanicisi {string} telefon numarasi ve {string} sifresi ile uyuglamaya giris yapar")
    public void registerKullanicisiTelefonNumarasiVeSifresiIleUyuglamayaGirisYapar(String phoneNumber, String password) {
        registerPage.loginWithPhoneNumber(phoneNumber, password);
    }

    @When("Register kullanicisi {string} butonunun gornurlugu ile giris yapildigini dogrular")
    public void registerKullanicisiButonununGornurluguIleGirisYapildiginiDogrular(String elementName) {
        AndroidClickUtils.verifyButtonByName(elementName);
    }

    @When("Register kullanicisi {string} mail adresi ve {string} sifresi ile uyuglamaya giris yapar")
    public void registerKullanicisiMailAdresiVeSifresiIleUyuglamayaGirisYapar(String mail, String password) {
        registerPage.loginWithMail(mail, password);
    }

    @When("Register kullanicisi ekrani asagi {int}, {int} noktalarinda yukari {int}, {int} noktasina kaydirir")
    public void swipeFromBottomToTop(int startX, int startY, int endX, int endY) {
        TouchMethods.swipe(startX, startY, endX, endY);
    }
}