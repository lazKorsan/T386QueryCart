package utils;

import enev.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Android emulator'u (Windows 11 + macOS) hızlı şekilde açar.
 * <p>
 * Eşdeğer komut: emulator -avd Pixel_7_Pro
 * Hız için ek parametreler eklenmiştir (bkz. buildCommand).
 * <p>
 * Kullanım:
 * EmulatorStarter.startAndWait();                       // Pixel_7_Pro, 3 dk timeout
 * EmulatorStarter.startAndWait("Pixel_7_Pro", Duration.ofMinutes(5));
 * EmulatorStarter.stop();
 */
public final class EmulatorStarter {

    private static final Logger log = LogManager.getLogger(EmulatorStarter.class);


    public static final String DEFAULT_AVD = ConfigReader.getProperty("defaultAVDname", "Pixel_7_Pro");
    private static final Duration DEFAULT_TIMEOUT = Duration.ofMinutes(3);
    private static final boolean IS_WINDOWS =
            System.getProperty("os.name", "").toLowerCase().contains("win");

    private EmulatorStarter() {
    }

    // ------------------------------------------------------------------ public API

    public static void startAndWait() {
        startAndWait(DEFAULT_AVD, DEFAULT_TIMEOUT);
    }

    /**
     * Emulator zaten açıksa tekrar açmaz; değilse başlatır ve boot bitene kadar bekler.
     */
    public static void startAndWait(String avdName, Duration timeout) {
        if (isEmulatorRunning()) {
            log.info("Emulator zaten çalışıyor, yeniden başlatılmadı.");
            waitForBoot(timeout);
            return;
        }
        start(avdName);
        waitForBoot(timeout);
    }

    /**
     * Emulator'u arka planda başlatır (beklemez).
     */
    public static void start(String avdName) {
        List<String> command = buildCommand(avdName);
        log.info("Emulator başlatılıyor: {}", String.join(" ", command));

        try {
            Path logFile = Path.of(System.getProperty("java.io.tmpdir"), "emulator-" + avdName + ".log");
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            // Çıktıyı dosyaya yönlendiriyoruz; yoksa pipe dolar ve emulator takılabilir.
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile.toFile()));
            pb.start();
            log.info("Emulator logları: {}", logFile);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Emulator başlatılamadı. ANDROID_HOME / ANDROID_SDK_ROOT tanımlı mı? " + e.getMessage(), e);
        }
    }

    /**
     * sys.boot_completed = 1 olana kadar bekler.
     */
    public static void waitForBoot(Duration timeout) {
        long deadline = System.nanoTime() + timeout.toNanos();
        long startedAt = System.nanoTime();

        run(List.of(adb(), "wait-for-device"), Duration.ofSeconds(Math.max(5, timeout.toSeconds())));

        while (System.nanoTime() < deadline) {
            String booted = run(List.of(adb(), "shell", "getprop", "sys.boot_completed"), Duration.ofSeconds(10));
            if ("1".equals(booted.trim())) {
                long sec = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startedAt);
                log.info("Emulator hazır ({} sn).", sec);
                return;
            }
            sleep(2000);
        }
        throw new IllegalStateException("Emulator " + timeout.toSeconds() + " sn içinde açılamadı.");
    }

    public static boolean isEmulatorRunning() {
        String out = run(List.of(adb(), "devices"), Duration.ofSeconds(10));
        return out.lines().anyMatch(l -> l.startsWith("emulator-") && l.contains("device"));
    }

    /**
     * Açık emulator'u kapatır.
     */
    public static void stop() {
        run(List.of(adb(), "emu", "kill"), Duration.ofSeconds(15));
        log.info("Emulator kapatıldı.");
    }

    // ------------------------------------------------------------------ internals

    private static List<String> buildCommand(String avdName) {
        List<String> cmd = new ArrayList<>();
        cmd.add(sdkTool("emulator", "emulator"));
        cmd.add("-avd");
        cmd.add(avdName);

        // --- Hız ayarları ---
        cmd.add("-no-snapshot-save");   // Kapanırken snapshot yazma (kapanışı ve sonraki açılışı hızlandırır)
        cmd.add("-no-boot-anim");       // Boot animasyonunu atla
        cmd.add("-no-audio");           // Ses sürücüsü gecikmesini kaldır
        cmd.add("-netfast");            // Ağ gecikme/hız emülasyonunu kapat
        cmd.add("-gpu");
        cmd.add("host");                // GPU'yu host makineden kullan (software render'dan çok daha hızlı)
        cmd.add("-accel");
        cmd.add("auto");                // Windows: WHPX/AEHD, macOS: HVF otomatik seçilir

        // CI / headless çalıştırmak isterseniz: -Demulator.headless=true
        if (Boolean.getBoolean("emulator.headless")) {
            cmd.add("-no-window");
        }
        return cmd;
    }

    private static String adb() {
        return sdkTool("platform-tools", "adb");
    }

    /**
     * SDK yolunu ENV'den bulur; bulamazsa PATH'e güvenir.
     */
    private static String sdkTool(String folder, String binary) {
        String exe = IS_WINDOWS ? binary + ".exe" : binary;

        String sdk = firstNonBlank(
                System.getenv("ANDROID_HOME"),
                System.getenv("ANDROID_SDK_ROOT"),
                defaultSdkPath());

        if (sdk != null) {
            File f = new File(new File(sdk, folder), exe);
            if (f.exists()) {
                return f.getAbsolutePath();
            }
        }
        return exe; // PATH üzerinden çalışmayı dene
    }

    private static String defaultSdkPath() {
        String home = System.getProperty("user.home");
        if (IS_WINDOWS) {
            String local = System.getenv("LOCALAPPDATA");
            return local != null ? local + "\\Android\\Sdk" : home + "\\AppData\\Local\\Android\\Sdk";
        }
        return home + "/Library/Android/sdk"; // macOS varsayılanı
    }

    private static String firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.isBlank()) {
                return v;
            }
        }
        return null;
    }

    /**
     * Kısa süreli komut çalıştırır, çıktıyı döner.
     */
    private static String run(List<String> command, Duration timeout) {
        try {
            Process p = new ProcessBuilder(command).redirectErrorStream(true).start();
            String out = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            if (!p.waitFor(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
                p.destroyForcibly();
            }
            return out;
        } catch (IOException e) {
            log.warn("Komut çalıştırılamadı {}: {}", command, e.getMessage());
            return "";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}