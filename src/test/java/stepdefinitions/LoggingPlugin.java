package stepdefinitions;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cucumber'ın resmi event sistemini kullanarak API steplerinin adlarını ve
 * çalışma sürelerini renklendirilmiş formatta loglar.
 * <p>
 * Bağımsız çalışır, BasePage vb. UI sınıflarına ihtiyaç duymaz.
 */
public class LoggingPlugin implements ConcurrentEventListener {

    private static final Logger logger = LogManager.getLogger(LoggingPlugin.class);
    private static final long SLOW_STEP_THRESHOLD_MS = 3000;

    // =========================================================================
    // ANSI RENK VE DESEN SABİTLERİ (Sayfa İçi Tanımlamalar)
    // =========================================================================
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED_BOLD = "\u001B[1;31m";
    private static final String ANSI_GREEN_BOLD = "\u001B[1;32m";
    private static final String ANSI_BLUE_BOLD = "\u001B[1;34m";

    // Renklendirme Yardımcı Metotları
    private static String red(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }

    private static String redBold(String text) {
        return ANSI_RED_BOLD + text + ANSI_RESET;
    }

    private static String green(String text) {
        return ANSI_GREEN + text + ANSI_RESET;
    }

    private static String greenBold(String text) {
        return ANSI_GREEN_BOLD + text + ANSI_RESET;
    }

    private static String blue(String text) {
        return ANSI_BLUE + text + ANSI_RESET;
    }

    private static String blueBold(String text) {
        return ANSI_BLUE_BOLD + text + ANSI_RESET;
    }

    private static String yellow(String text) {
        return ANSI_YELLOW + text + ANSI_RESET;
    }

    // =========================================================================
    // THREAD-SAFE SAYAÇLAR VE DEĞİŞKENLER
    // =========================================================================
    private final Map stepStartTimes = new ConcurrentHashMap<>();
    private final ThreadLocal stepCounter = ThreadLocal.withInitial(() -> 0);
    private final ThreadLocal testStartTime = new ThreadLocal<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        stepCounter.set(0);
        testStartTime.set(System.currentTimeMillis());

        logger.info(blue("═══════════════════════════════════════════════════"));
        logger.info("📋" + blueBold(" APPIUM TESTİ BAŞLIYOR:") + " {}", blue(event.getTestCase().getName()));
        logger.info("📌" + blue(" Tags:") + " {}", yellow(String.valueOf(event.getTestCase().getTags())));
        logger.info("📂" + blue(" Feature:") + " {}", blue(getFeatureName(event.getTestCase())));
        logger.info(blue("═══════════════════════════════════════════════════"));
    }

    private void onTestStepStarted(TestStepStarted event) {
        if (!(event.getTestStep() instanceof PickleStepTestStep)) {
            return; // Hook adımlarını (@Before/@After) loglama, sadece Gherkin stepleri
        }
        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        int currentCount = (stepCounter.get() != null) ? (int) stepCounter.get() : 0;
        int count = currentCount + 1;
        stepCounter.set(count);


        String stepText = step.getStep().getKeyword() + step.getStep().getText();
        stepStartTimes.put(stepKey(String.valueOf(event.getTestCase().getId()), step), System.currentTimeMillis());

        logger.info("▶️ " + blueBold(" STEP {}:") + " {}", count, blue(stepText));
    }

    private void onTestStepFinished(TestStepFinished event) {
        if (!(event.getTestStep() instanceof PickleStepTestStep)) {
            return;
        }
        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String stepText = step.getStep().getKeyword() + step.getStep().getText();
        int count = (int) stepCounter.get();

        Long start = (Long) stepStartTimes.remove(stepKey(String.valueOf(event.getTestCase().getId()), step));
        long duration = (start != null) ? System.currentTimeMillis() - start : -1;

        Result result = event.getResult();
        if (result.getStatus() == Status.PASSED) {
            logger.info("✅" + greenBold(" STEP {} TAMAMLANDI:") + " {}" + blue(" (Süre: {}ms)"),
                    count, blue(stepText), green(String.valueOf(duration)));

            if (duration >= SLOW_STEP_THRESHOLD_MS) {
                logger.warn("⚠️" + redBold("  YAVAŞ STEP {}:") + " {}" + red(" (Süre: {}ms)"),
                        red(String.valueOf(count)), blue(stepText), redBold(String.valueOf(duration)));
            }
        } else {
            logger.error("❌" + redBold(" STEP {}:") + " BAŞARISIZ: {}" + red(" (Süre: {}ms)"),
                    red(String.valueOf(count)), blue(stepText), redBold(String.valueOf(duration)));

            if (result.getError() != null) {
                logger.error("❌" + redBold(" Hata Mesajı:") + " {}", redBold(result.getError().getMessage()));
            }
        }
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        Long start = (Long) testStartTime.get();
        long duration = (start != null) ? System.currentTimeMillis() - start : -1;

        logger.info(blue("═══════════════════════════════════════════════════"));
        if (event.getResult().getStatus() == Status.PASSED) {
            logger.info("✅" + greenBold(" TEST BAŞARIYLA TAMAMLANDI:") + " {}", green(event.getTestCase().getName()));
        } else {
            logger.error("❌" + redBold(" TEST BAŞARISIZ:") + " {}", redBold(event.getTestCase().getName()));
        }
        logger.info("📊" + blue(" Toplam Test Süresi:") + " {}" + red("ms"), redBold(String.valueOf(duration)));
        logger.info("📊" + blue(" Toplam Step Sayısı:") + " {}", blue(String.valueOf(stepCounter.get())));
        logger.info(blue("═══════════════════════════════════════════════════"));

        // ThreadLocal temizliği
        stepCounter.remove();
        testStartTime.remove();
    }

    private String stepKey(String testCaseId, PickleStepTestStep step) {
        return testCaseId + "#" + step.getId();
    }

    private String getFeatureName(TestCase testCase) {
        try {
            String uri = testCase.getUri().toString();
            String fileName = uri.substring(uri.lastIndexOf('/') + 1);
            return fileName.replace(".feature", "");
        } catch (Exception e) {
            return "Unknown Feature";
        }
    }
}