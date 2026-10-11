package enev;

import java.io.*;
import java.util.Properties;

public class ConfigWriter {

    public static final String red = "\u001B[38;2;255;0;0m";
    public static final String green = "\u001B[38;2;0;255;0m";
    public static final String redBold = "\u001B[31;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final String FILE_PATH = "src/main/java/enev/accountInfo/account.properties";

    public static void setProperty(String key, String value) {
        Properties properties = new Properties();
        File file = new File(FILE_PATH);

        try {
            // 1. Üst klasörler (enev/accountInfo) yoksa oluştur
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean dirsCreated = parentDir.mkdirs();
                if (dirsCreated) {
                    System.out.println("Gerekli klasör dizini oluşturuldu: " + parentDir.getPath());
                }
            }

            // 2. Dosya yoksa yeni dosya oluştur
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("Yeni properties dosyası oluşturuldu: " + file.getPath());
                }
            } else {
                // Dosya zaten varsa mevcut değerleri UTF-8 ile oku
                try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
                    properties.load(isr);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya veya klasör hazırlığı sırasında hata oluştu: " + e.getMessage());
        }

        // 3. Yeni key-value değerini set et
        properties.setProperty(key, value);

        // 4. Dosyayı UTF-8 karakter seti ile yaz/kaydet
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            properties.store(osw, "Updated via MyMemory API with UTF-8");
            System.out.println(green("Başarıyla yazıldı -> ") + redBold(key + " = " + value));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Properties dosyasına yazılamadı!");
        }
    }

    static String green(String text) {
        return green + text + ANSI_RESET;
    }

    static String redBold(String text) {
        return redBold + text + ANSI_RESET;
    }
}