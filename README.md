# T167QueryCart - Mobil Otomasyon Test Projesi

Bu proje, [QueryCart](https://qa.querycatt.com/#/home) e-ticaret platformunun mobil web/uygulama arayüzü testlerini otomatize etmek amacıyla geliştirilmektedir.

## 🚀 Proje Hakkında
Bu test otomasyon projesi, kullanıcıların ana sayfa erişiminden ödeme süreçlerine, profil yönetiminden iade taleplerine kadar tüm uçtan uca (E2E) süreçlerini doğrulamayı hedefler.

**Test Edilen Ortam:** [QueryCart QA Environment](https://qa.querycatt.com/#/home)
**Öncelik:** Yüksek (High/Highest) iş kritik fonksiyonların doğrulanması.

## 📂 Proje Klasör Yapısı
Proje, sürdürülebilir bir otomasyon mimarisi için aşağıdaki yapıya sahiptir:
```text
T167QueryCart
├── src/test/java
│   ├── pages/         # Page Object Model sınıfları
│   ├── stepdefs/      # Cucumber Step Definitions (varsa)
│   ├── runners/       # Test çalıştırma sınıfları
│   └── utilities/     # Driver, ConfigReader, ReusableMethods vb.
├── src/test/resources # Feature dosyaları ve test verileri
├── configuration.properties # Hassas veriler ve config (Git'e gönderilmez)
├── pom.xml            # Maven bağımlılık yönetimi
└── README.md          # Proje dökümantasyonu
```

## 🛠 Kullanılan Teknolojiler
* **Dil:** Java
* **Framework:** Appium / Selenium WebDriver (Mobil Uyumluluk)
* **Build Aracı:** Maven
* **Test Runner:** TestNG veya Cucumber (Proje yapısına göre)
* **Raporlama:** Allure veya Extent Reports
* **Proje Takibi:** Jira
* **İletişim:** Slack & WhatsApp

## 📋 Test Senaryoları (User Stories)
Proje kapsamında test edilecek kullanıcı hikayeleri ve sorumlu paydaşlar aşağıda listelenmiştir:

| ID | Tanım | Öncelik | Sorumlu |
|:---|:---|:---:|:---|
| **US_001** | Ana sayfaya erişim sağlanabilmeli. | Highest | Kerim |
| **US_002** | Alt bar linkleri (Home, Category, Wishlist, Cart, Profile) ve arama kutusu  | Medium | Elif  |
| **US_003** | Kayıtsız kullanıcı ürün inceleme, özellik seçimi ve sepete ekleme. | High | Elif |
| **US_004** | Ana sayfada "Categories" başlığı ve alt kategorilerin görüntülenmesi. | High | Elif |
| **US_005** | Ana sayfada "Popular Brands" başlığı ve markaların görüntülenmesi. | High | Bekir |
| **US_006** | Şifremi unuttum süreci (OTP, E-mail/Telefon ile resetleme). | High | Bekir |
| **US_007** | Yeni kullanıcı kaydı (SignUp) formu ve validasyonlar. | Highest | Ahmet |
| **US_008** | Kayıtlı kullanıcı girişi (SignIn), "Beni Hatırla" ve hata mesajları. | Highest | Ahmet |
| **US_009** | Kategori menüsü (Men, Women, Junior) ve filtreleme ikonları. | High | Fatih |
| **US_010** | "Most Popular" menüsü, ürün listeleme ve "See All" fonksiyonu. | Medium | Beril |
| **US_011** | Wishlist (Favori Listesi) oluşturma ve ürün ekleme. | Medium | Beril |
| **US_012** | Ürün detay sayfası (Details, Videos, Review, Shipping&Return) kontrolleri. | Medium | Beril |
| **US_013** | "Men" kategorisi özelindeki işlemler ve filtreleme. | High | Fatih |
| **US_014** | "Juniors" kategorisi özelindeki işlemler ve filtreleme. | Medium | Kerim  |
| **US_015** | "Women" kategorisi özelindeki işlemler ve filtreleme. | Medium | Ahmet |
| **US_016** | Kullanıcı Dashboard (Özet bilgiler, sipariş geçmişi) kontrolü. | Medium | Ahmet |
| **US_017** | Adres yönetimi (Ekleme, Düzenleme, Silme). | High | Fatih |
| **US_018** | Sipariş geçmişi (Fatura görüntüleme, iptal işlemleri). | High | Ahmet |
| **US_019** | Ödeme öncesi adres seçimi ve sipariş özeti (Shipping Info). | High | Ahmet |
| **US_020** | Dashboard üzerinden şifre değiştirme işlemi. | High | Ahmet |
| **US_021** | Profil bilgilerini (İsim, Mail, Tel) düzenleme. | High | Ahmet |
| **US_022** | Güvenli çıkış (Logout) işlemi. | Highest | Mfatih |
| **US_023** | Kayıtsız kullanıcıların favori ekleme yaparken Login'e yönlendirilmesi. | Medium | Mfatih  |
| **US_024** | Ödeme sayfası (Stripe), kart bilgileri ve sipariş onayı. | Highest | Mfatih  |
| **US_025** | İade talebi (Return Request) oluşturma ve yönetme. | Medium | Ahmet |
| **US_026** | Wishlist sayfası yönetimi ve ürün kaldırma. | High | Ahmet |
| **US_027** | Sepet (Shopping Cart) işlemleri, miktar güncelleme ve ödeme adımı. | Highest | Mfatih |

## ⚙️ Kurulum ve Çalıştırma
Projenin yerel ortamda kurulumu için lütfen **[KURULUM.MD](./KURULUM.MD)** dosyasındaki adımları takip edin.

### Temel Komutlar
Bağımlılıkları yüklemek ve testleri çalıştırmak için terminalde şu komutları kullanabilirsiniz:

```bash
# Bağımlılıkları yükle
mvn clean install

# Tüm testleri çalıştır
mvn test
```

## 🚦 Git İş Akışı
1. Ana projeyi (main) güncel tutun: `git pull origin main`
2. Kendi göreviniz için yeni bir branch açın: `git checkout -b feature/US_0xx_tanim`
3. Kodunuzu commit'leyin: `git commit -m "feat: US_0xx testleri tamamlandı"`
4. Branch'inizi push edin: `git push origin feature/US_0xx_tanim`
5. Pull Request (PR) oluşturun ve ekip arkadaşlarınıza haber verin.

## 🤝 İletişim ve Takip
* **Proje Yönetimi:** Tüm süreçler **Jira** üzerinden takip edilmektedir. Görev atamaları ve status güncellemeleri Jira üzerinden yapılmalıdır.
* **Anlık İletişim:** Teknik sorular ve acil durumlar için **Slack** ve **WhatsApp** kanalları aktif olarak kullanılmaktadır.
* **Daily Meeting:** Günlük ilerlemeler ve bloklayan konular için belirlenen saatlerde toplantılar yapılır.

---
*Bu proje eğitim/test amaçlı oluşturulmuştur.