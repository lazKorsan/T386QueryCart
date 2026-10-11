# T167QueryCart Proje Kurulum Rehberi

Bu belge, projenin yerel geliştirme ortamınızda sorunsuz bir şekilde çalıştırılması için gerekli adımları içerir.

## 1. Projeyi Klonlama
Öncelikle projeyi GitHub üzerinden bilgisayarınıza klonlayın:
```bash
git clone https://github.com/[kullanici-adi]/T167QueryCart.git
```

## 2. Konfigürasyon Dosyası
Projenin çalışması için gerekli olan `configuration.properties` dosyasını temin edin (Slack veya WhatsApp üzerinden paylaşılan güncel sürüm) ve projenin **kök dizinine** (root directory) yapıştırın.

> [!IMPORTANT]
> `configuration.properties` dosyası şifreler ve özel API anahtarları içerebilir. Bu dosyanın `.gitignore` içerisinde olduğundan ve yanlışlıkla uzak sunucuya (remote) gönderilmediğinden emin olun.

## 3. JDK ve SDK Ayarları
Projenin doğru derlenmesi için IntelliJ IDEA üzerinde aşağıdaki ayarları yapın:
- **File > Project Structure > Project** yolunu izleyin.
- **SDK:** `JDK 21` (JetBrains Runtime 21.0.8) versiyonunu seçin.
- Eğer yüklü değilse, IDE üzerinden download ederek ilgili versiyonu kurabilirsiniz.

## 4. Bağımlılıkların Yüklenmesi (Maven)
Projedeki kütüphaneleri indirmek ve kurulumu tamamlamak için Maven sekmesini kullanın:
- Sağ taraftaki **Maven** panelini açın.
- **Execute Maven Goal** simgesine tıklayın.
- Gelen ekrana aşağıdaki komutu yazın ve çalıştırın:
  ```bash
  mvn clean install
  ```

## 5. Git İş Akışı ve Branch Yönetimi
Kurulum başarıyla tamamlandıktan sonra doğrudan `main` üzerinde çalışmamaya özen gösterin:

1.  **Yeni Branch Oluşturma:**
    ```bash
    git checkout -b branch-isminiz
    ```
2.  **Çalışmaya Başlama:** Kendi branch'inize geçtiğinizden emin olduktan sonra kodlama yapmaya başlayabilirsiniz.

---
*Herhangi bir sorunla karşılaşırsanız lütfen teknik ekip ile iletişime geçin.*