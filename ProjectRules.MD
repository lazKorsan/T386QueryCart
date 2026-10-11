# 🚩 Proje Çalışma Kuralları (Project Rules)

Bu proje bir ekip çalışmasıdır. Proje yapısının bozulmaması ve zaman kaybı yaşanmaması için aşağıdaki kurallara uyulması **ZORUNLUDUR**.

## 🛑 1. Ortak Dosyalara Dokunma (CRITICAL!)
Aşağıdaki "Core" dosyalarda tek başınıza değişiklik yapıp pushlamak kesinlikle **YASAKTIR**:
- `src/test/java/utilities/Driver.java`
- `src/test/java/utilities/ConfigReader.java`
- `src/test/java/utilities/ClickUtils.java`
- `pom.xml`

**Eğer bu dosyalarda bir değişiklik gerekiyorsa;** önce Slack/WhatsApp üzerinden durumu belirtin, ekip onayı alın ve değişikliği yaptıktan sonra mutlaka test edip öyle gönderin. Yanlışlıkla bozulan bir Driver, tüm ekibin çalışmasını durdurur.

## 🛠 2. Çalışma Alanı ve Sorumluluklar
Herkes sadece kendine atanan **User Story (US)** sınırları içerisinde kalmalıdır:
- **Feature Files:** Sadece kendi US numaranızla dosya açın (Örn: `US007_Registration.feature`).
- **Step Definitions:** Sadece kendi feature dosyanızın adımlarını içeren class'ta çalışın.
- **Pages:** Kendi sayfanız için ayrı bir Page class'ı açın. Başkasının sayfasındaki bir elementi kullanmanız gerekirse o kişiye haber verin.

## 🌿 3. Git İş Akışı ve Pull Request (PR)
- **Branch:** Asla `main` branch'ine direkt kod göndermeyin.
- **İsimlendirme:** Branch isminiz `feature/US001_isim` şeklinde olmalıdır.
- **PR Kontrolü:** Kodunuzu göndermeden önce mutlaka en güncel `main` branch'ini kendi branch'inize çekin (merge edin), conflict varsa çözün ve testlerinizin geçtiğinden emin olun.
- **Gözden Geçirme:** Gönderilen her PR, başka bir ekip üyesi tarafından incelenmeden onaylanmayacaktır.

## ⚠️ 4. Önemli Hatırlatmalar
- `configuration.properties` dosyasını asla GitHub'a pushlamayın.
- Localinizde çalışan kod, driver yapısını bozuyorsa o kodu "GÜNCELLE" demeden önce ekibe danışın.
- Hata yaptığınızda veya yanlışlıkla bir dosyayı değiştirdiğinizde hemen iletişim kanallarından bilgi verin.

---
*Kurallara uymak, projenin hızını ve kalitesini belirler. İyi çalışmalar!*