package utilities;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static utilities.Driver.getAppiumDriver;

public class TouchMethods {

    public static void swipe(int x, int y, int endX, int endY) {
        // 1. PointerInput oluştur - "parmak" simülasyonu
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // 2. Başlangıç ve bitiş noktaları
        Point start = new Point(x, y);        // (710, 1800)
        Point end = new Point(endX, endY);    // (710, 220)

        // 3. Hareket sırası oluştur
        Sequence swipe = new Sequence(finger, 0);

        // 4. Parmak başlangıç noktasına git (ani hareket)
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(0),              // 0ms (ani)
                PointerInput.Origin.viewport(),    // Ekran koordinatları
                start.getX(), start.getY()         // (710, 1800)
        ));

        // 5. Parmak BAS (dokun)
        swipe.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        // 6. Parmak SÜRÜKLE (1000ms boyunca)
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(1000),           // 1 saniye
                PointerInput.Origin.viewport(),    // Ekran koordinatları
                end.getX(), end.getY()             // (710, 220)
        ));

        // 7. Parmak KALDIR
        swipe.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        // 8. Hareketi UYGULA
        getAppiumDriver().perform(Arrays.asList(swipe));
    }

    public static List<String> smoothSwipe(int startX, int startY, int endX, int endY, int stepCount) {
        // Her adım için gecikme (ms)
        int stepDelay = 200;

        // Toplam mesafeyi hesapla
        int totalDistanceY = endY - startY;
        int totalDistanceX = endX - startX;

        // Her adımın mesafesi
        int stepDistanceY = totalDistanceY / stepCount;
        int stepDistanceX = totalDistanceX / stepCount;

        int currentX = startX;
        int currentY = startY;

        for (int i = 0; i < stepCount; i++) {
            int nextX = currentX + stepDistanceX;
            int nextY = currentY + stepDistanceY;

            // Son adımda tam hedefe git
            if (i == stepCount - 1) {
                nextX = endX;
                nextY = endY;
            }

            // Kaydırma yap
            swipe(currentX, currentY, nextX, nextY);

            // Biraz bekle (kaydırmanın tamamlanması için)
            try {
                Thread.sleep(stepDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentX = nextX;
            currentY = nextY;
        }
        return null;
    }
}
