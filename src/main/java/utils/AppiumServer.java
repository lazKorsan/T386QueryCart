package utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumServer {

    private static Process appiumProcess;
    private static String osName;
    private static final int DEFAULT_PORT = 4723;
    private static final int STARTUP_TIMEOUT_SECONDS = 30;

    // Sizin sisteminizdeki tam yol
    //private static final String APPIUM_PATH_WINDOWS = "C:\\Users\\user\\AppData\\Roaming\\npm\\appium.cmd";
     private static final String APPIUM_PATH_WINDOWS = System.getenv("APPDATA") + "\\npm\\appium.cmd";

    public static boolean startServer(int port) {
        try {
            detectOperatingSystem();

            // Önce mevcut server'ı kontrol et
            if (isServerRunning(port)) {
                System.out.println("✅ Appium server zaten " + port + " portunda çalışıyor.");
                return true;
            }

            // Eğer server çalışmıyorsa, önce node süreçlerini temizle (Windows için)
            if (isWindows()) {
                System.out.println("🧹 Eski node süreçleri temizleniyor...");
                try {
                    Runtime.getRuntime().exec("taskkill /F /IM node.exe");
                    Thread.sleep(2000); // Süreçlerin tamamen kapanması için bekle
                } catch (Exception e) {
                    System.out.println("⚠️ Node süreçleri temizlenirken hata (önemli değil): " + e.getMessage());
                }
            }

            System.out.println("🚀 Appium server başlatılıyor...");

            ProcessBuilder processBuilder = new ProcessBuilder(buildAppiumCommand(port));
            processBuilder.redirectErrorStream(true);

            // Environment PATH'i güncelle (Appium'ın bulunması için)
            if (isWindows()) {
                String pathEnv = System.getenv("PATH");
                pathEnv += ";C:\\Users\\user\\AppData\\Roaming\\npm";
                processBuilder.environment().put("PATH", pathEnv);
            }

            appiumProcess = processBuilder.start();

            // Arka planda logları okuyarak sürecin asılı kalmasını engelleriz
            startLogStreamer();

            return waitForServerStart(port);

        } catch (IOException e) {
            System.err.println("❌ Appium başlatılırken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    public static boolean startServer() {
        return startServer(DEFAULT_PORT);
    }

    private static String[] buildAppiumCommand(int port) {
        String portStr = String.valueOf(port);
        if (isWindows()) {
            // Windows için appium.cmd üzerinden başlat
            return new String[]{APPIUM_PATH_WINDOWS, "--port", portStr, "--log-level", "info"};
        } else {
            // Mac/Linux için standart komut
            return new String[]{"appium", "--port", portStr, "--log-level", "info"};
        }
    }

    private static void startLogStreamer() {
        Thread logThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(appiumProcess.getInputStream()))) {
                String line;
                while (appiumProcess != null && appiumProcess.isAlive() && (line = reader.readLine()) != null) {
                    System.out.println("[Appium-Log] " + line);
                }
            } catch (IOException e) {
                // Sunucu kapandığında okuma duracaktır
                System.out.println("📝 Appium log streamer kapandı");
            }
        });
        logThread.setDaemon(true);
        logThread.start();
    }

    public static boolean isServerRunning(int port) {
        try {
            URL url = new URL("http://127.0.0.1:" + port + "/status");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }

    public static void stopServer() {
        if (appiumProcess != null && appiumProcess.isAlive()) {
            System.out.println("🛑 Appium server durduruluyor...");
            appiumProcess.destroy();
            try {
                if (!appiumProcess.waitFor(5, TimeUnit.SECONDS)) {
                    System.out.println("⚠️ Appium process normal şekilde kapanmadı, zorla kapatılıyor...");
                    appiumProcess.destroyForcibly();
                }

                // Windows'ta portu işgal eden Node sürecini tamamen temizle
                if (isWindows()) {
                    try {
                        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
                    } catch (Exception e) {
                        System.out.println("⚠️ Node temizleme hatası: " + e.getMessage());
                    }
                }
                System.out.println("✅ Appium server tamamen durduruldu.");
            } catch (InterruptedException e) {
                System.err.println("Durdurma hatası: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        } else {
            System.out.println("ℹ️ Appium server zaten çalışmıyor.");
        }
    }

    private static void detectOperatingSystem() {
        osName = System.getProperty("os.name").toLowerCase();
    }

    private static boolean isWindows() {
        return osName.contains("win");
    }

    private static boolean waitForServerStart(int port) {
        long startTime = System.currentTimeMillis();
        System.out.println("⏳ Appium server'ın başlaması bekleniyor (en fazla " + STARTUP_TIMEOUT_SECONDS + " saniye)...");

        while (System.currentTimeMillis() - startTime < (STARTUP_TIMEOUT_SECONDS * 1000)) {
            if (isServerRunning(port)) {
                System.out.println("✅ Appium server başarıyla başlatıldı! (" + port + " portunda)");
                return true;
            }
            try {
                Thread.sleep(1000);
                System.out.print("."); // Bekleme göstergesi
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(); // Yeni satır
        System.err.println("❌ Appium server " + STARTUP_TIMEOUT_SECONDS + " saniye içinde başlamadı!");
        return false;
    }

    @Test
    public void testAppiumServer(){
        startServer();
       // stopServer();
    }

    public static void AppiumServerRunner(){
        startServer();

    }
    public static void stopServerRunner(){
        stopServer();


    }
}