# Mobile Query Card – UI Business Requirements Document (BRD)

> Kaynak: `Mobile_Query_Card__1668.xlsx` dosyasının **UI BRD TR** sayfasından dönüştürülmüştür.

Bu doküman, mobil uygulamanın UI (kullanıcı arayüzü) gereksinimlerini kullanıcı hikayeleri (User Story) formatında listeler. Her hikaye; açıklama, kabul kriterleri, öncelik ve sorumlu ekip üyesi bilgilerini içerir.

## 📋 Özet Tablo

| US ID | Açıklama | Öncelik | Sorumlu | Kriter Sayısı |
|---|---|---|---|---|
| US-001 | Bir kullanici olarak site ana sayfasina erisim saglayabilmek istiyorum. | Highest |  | 1 |
| US-002 | Bir kullanici olarak site ici islemlerini kolaylikla yapabilmek icin ana sayfa altbarın... | Medium |  | 3 |
| US-003 | Bir kullanici olarak sitede kayıt olmadan urunleri inceleyip urun secebilmek ve sepete ... | High |  | 3 |
| US-004 | Bir kullanici olarak anasayfada categories baslıgı altında kategorileri görüp istediğim... | High |  | 1 |
| US-005 | Bir kullanici olarak anasayfada sitenin popular markalarını görebilmek istiyorum | High | Bekir | 1 |
| US-006 | Kayıtlı bir kullanici olarak şifremi unuttugumda şifre değiştirme işlemi yapabilmek ist... | High |  | 4 |
| US-007 | Bir kullanici olarak sitenin fonksiyonlarindan faydalanabilmek icin siteye kayit olabil... | Highest | Ahmet | 7 |
| US-008 | Kayıtlı bir kullanici olarak siteye kaydoldugum (email/telefon ve password) bilgileriml... | Highest | Ahmet | 7 |
| US-009 | Bir kullanici olarak Kategoriler basligi altinda sitede bulunan tum kategorileri gorebi... | High |  | 3 |
| US-010 | Bir kullanici olarak alisveris yaptigim sitede guncel urunleri takip edebilecegim bir s... | Medium |  | 4 |
| US-011 | Kayıtlı bir kullanıcı olarak sitede favori listesi oluşturabilmek istiyorum | Medium | Beril | 2 |
| US-012 | Bir kullanıcı olarak sayfada sectigim urun ile ilgili detaylı bilgilere ulasabilmek ist... | Medium |  | 6 |
| US-013 | Kayıtlı bir kullanici olarak ana sayfada Men menusune ait kategorileri ve urunleri gore... | High |  | 3 |
| US-014 | Kayıtlı bir kullanici olarak ana sayfada Juniors menusune ait kategorileri ve urunleri ... | Medium |  | 3 |
| US-015 | Kayıtlı bir  kullanici olarak ana sayfada Women menusune ait kategorileri ve urunleri g... | Medium | — | 3 |
| US-016 | Kayitli bir kullanici olarak sitedeki islemlerimi ve ayarlarimi kontrol etmek icin site... | Medium |  | 4 |
| US-017 | Kayıtlı bir kullanıcı olarak sitede adres bilgilerimi yönetebilecegim bir sayfa olmasın... | High |  | 4 |
| US-018 | Kayitli bir kullanici olarak Dashboard panelimde alisveris gecmisimi goruntuleyebileceg... | High |  | 4 |
| US-019 | Kayıtlı bir kullanıcı olarak sitede odemeyi tamamlamadan önce adres bilgilerimi secip y... | High |  | 4 |
| US-020 | Kayıtlı bir kullanıcı olarak dashboard sayfamda sifre degistirme islemlerimi gerceklest... | High |  | 4 |
| US-021 | Kayıtlı bir kullanıcı olarak sitede kendi profil bilgilerimi duzenleyebilmek istiyorum | High |  | 4 |
| US-022 | Kayıtlı bir kullanıcı olarak sistemden guvenli bir sekilde cıkıs yapabilmek istiyorum | Highest |  | 3 |
| US-023 | Bir kullanıcı olarak kayıt olmamis kisilerin begendiği urunlerle favori listesi olustur... | Medium |  | 2 |
| US-24 | Kayitli bir kullanici olarak kontrollerini sagladigim siparislerimin odemesini gercekle... | Highest |  | 6 |
| US-025 | Kayıtlı bir kullanici olarak iade etmek istedigim siparisler ile ilgili bir sayfa olmas... | Medium |  | 5 |
| US-026 | Kayıtlı bir kullanıcı olarak sitede favori urunlerim sayfası olmasını ve bu sayfayı yon... | High |  | 4 |
| US-027 | Kayıtlı bir kullanıcı olarak sitede bir sepetim olmasını ve bu sepet işlemlerimi yönete... | Highest |  | 5 |

## 👥 Ekip Dağılımı

| Takım Üyesi | Atanan User Story'ler | Toplam |
|---|---|---|
| Ahmet | US-007, US-008, US-020, US-021, US-025, US-026, US-18, US-19, US-17  | 9 |
| Atanmamış | US-015, US-018, US-019 | 3 |
| Bekir | US-005, US-006, US-016 | 3 |
| Beril | US-010, US-0011, US-0012 | 3 |
| Elif | US-002, US-003, US-004 | 3 |
| Fatih | US-009, US-013, US-017 | 3 |
| Kerim | US-001, US-014 | 2 |
| Mfatih | US-022, US-023, US-24, US-027 | 4 |

---

## 📖 Kullanıcı Hikayeleri (User Stories)

### US-001

**Açıklama:** Bir kullanici olarak site ana sayfasina erisim saglayabilmek istiyorum.

- **Öncelik:** 🔴 Highest
- **Sorumlu:** Kerim

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] App acildiginda ana sayfa erişilebilir olmalıdır.

---

### US-002

**Açıklama:** Bir kullanici olarak site ici islemlerini kolaylikla yapabilmek icin ana sayfa altbarında kolay linklerin bulunmasını istiyorum

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Elif

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Site alt barinda kolay linkler (Home,Category,Wishlist, Cart, Profile) goruntulenmeli.
- [ ] Kolay linkler (Home,Category, Wishlist, Cart, Profile ) ilgili sayfaya yonlendirme yapmalidir.
- [ ] Search Text Box goruntulenmeli ve arama yapilabilmeli.

---

### US-003

**Açıklama:** Bir kullanici olarak sitede kayıt olmadan urunleri inceleyip urun secebilmek ve sepete ekleyebilmek istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Elif

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfada urunler ve urun özellikleri gorunur olmalı
- [ ] Secilen urune ait beden/renk ve adet ozellikleri secilebilir olmalı
- [ ] Urun detail sayfasında Add to Card butonu görünür ve aktif olmalı

---

### US-004

**Açıklama:** Bir kullanici olarak anasayfada categories baslıgı altında kategorileri görüp istediğim kategoriden ürün secebilmek istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Elif

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfada Categories baslıgı altında tüm kategoriler görünür olmalı.

---

### US-005

**Açıklama:** Bir kullanici olarak anasayfada sitenin popular markalarını görebilmek istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Bekir

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfada Popular Brands baslıgı altında brandler görünür olmalı.

---

### US-006

**Açıklama:** Kayıtlı bir kullanici olarak şifremi unuttugumda şifre değiştirme işlemi yapabilmek istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Bekir

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] SignIn sayfasında Forgot Password linki görünür ve aktif olmalı
- [ ] Forgot password sayfasında gecerli e-mail ya da telefon numarası girilerek şifre resetleme işlemi yapılabilmelidir.
- [ ] Get OTP butonu görünür ve aktif olmalıdır.
- [ ] Back to sign in linki görünür ve aktif olmalıdır.

---

### US-007

**Açıklama:** Bir kullanici olarak sitenin fonksiyonlarindan faydalanabilmek icin siteye kayit olabilmek istiyorum

- **Öncelik:** 🔴 Highest
- **Sorumlu:** Ahmet

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfa alt barinda profile ikonu gorunur ve aktif olmali.
- [ ] SignUp formunun altinda SignUp butonu gorunur ve aktif olmali.
- [ ] SignUp formu icerisinde bos birakilamaz alanlar (Firstname, Email or Phone, Password) olmali.
- [ ] Phone number ile register olabilmek icin en az 7 haneli numara girilmesi zorunlu olmali.
- [ ] Email girisi icin girilen email  gecerli email kriterlerini saglamali
- [ ] Password girisi icin girilen gecerli sifre (min 6 karakter) kriterlerini saglamali.
- [ ] SignUp sayfasindan signIn sayfasina gecis yapilabilmeli.

---

### US-008

**Açıklama:** Kayıtlı bir kullanici olarak siteye kaydoldugum (email/telefon ve password) bilgilerimle login olabilmek istiyorum.

- **Öncelik:** 🔴 Highest
- **Sorumlu:** Ahmet

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfada profile linki görünür olmalı ve SıgnIn sayfasına yönlendirmeli
- [ ] Login sayfasinda SignIn butonu gorunur ve aktif olmali.
- [ ] Giris yapilan bilgilerin hatirlanmasi icin Remember me checkBox'i secilebilir olmali.
- [ ] Login sayfasindan Forgot Password sayfasina gecis yapilabilmeli.
- [ ] Login sayfasindan Register sayfasina gecis yapilabilmeli.
- [ ] Login formuna gecersiz bilgiler girilip signIn butonuna tiklandiginda hata mesajlari goruntulenmeli.
- [ ] Login formuna gecerli bilgiler girilip signIn butonuna tiklandiginda sisteme giris yapilabilmeli.

---

### US-009

**Açıklama:** Bir kullanici olarak Kategoriler basligi altinda sitede bulunan tum kategorileri gorebilmek istiyorum.

- **Öncelik:** 🟠 High
- **Sorumlu:** Fatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfa navbarinda  Category ikonu gorunur ve aktif olmali.
- [ ] Men,Women,Junior başlıkları görünür ve aktif olmalı
- [ ] Filtreleme ikonları aktif olmalıdır.

---

### US-010

**Açıklama:** Bir kullanici olarak alisveris yaptigim sitede guncel urunleri takip edebilecegim bir sayfanin olmasini istiyorum.

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Beril

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfa navBar da Most Popular menu basligi gorunur olmali.
- [ ] See All ikonu gorunur ve aktif olmalı
- [ ] Most Popular baslıgı altında guncel urunler listenmeli
- [ ] Geri butona tıklandıgında anasayfaya geri dönülebilmeli

---

### US-0011

**Açıklama:** Kayıtlı bir kullanıcı olarak sitede favori listesi oluşturabilmek istiyorum

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Beril

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfa navbarda wishlist butonu gorunur ve aktif olmalıdır.
- [ ] Secilen urunler wishlist /favorite butonundan wishlist listesine eklenebilmelidir.

---

### US-0012

**Açıklama:** Bir kullanıcı olarak sayfada sectigim urun ile ilgili detaylı bilgilere ulasabilmek istiyorum

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Beril

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfadan secilen urun sayfasına gecilebilmeli
- [ ] Urun sayfasına gecildiginde Details,Videos,Review,Shipping&Return baslıkları görünür ve aktif olmalı
- [ ] Details butonuna tıklandıgında Product Details baslıgı ve urune ait bilgiler goruntulenmeli
- [ ] Videos butonuna tıklandıgında Product Videos baslıgı ve urune ait videolar goruntulenmeli
- [ ] Review butonuna tıklandıgında Review baslıgı ve urune ait degerlendirmeler goruntulenmeli
- [ ] Shipping&Return butonuna tıklandıgında Shipping&Return baslıgı ve urune ait kargo ve iade kosulları goruntulenmeli

---

### US-013

**Açıklama:** Kayıtlı bir kullanici olarak ana sayfada Men menusune ait kategorileri ve urunleri gorebilmek istiyorum.

- **Öncelik:** 🟠 High
- **Sorumlu:** Fatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfa body bolumunde Men kategori penceresi ve alt kategorileri gorunur ve aktif olmalı
- [ ] Men kategori sayfasındaki urunler uzerinde islemler (sepete ekleme, begenilenler listesine ekleme ve goruntuleme) yapilabilmeli.
- [ ] Filtreleme ikonları gorunur ve aktif olmalı

---

### US-014

**Açıklama:** Kayıtlı bir kullanici olarak ana sayfada Juniors menusune ait kategorileri ve urunleri gorebilmek istiyorum.

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Kerim

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfa body bolumunde Juniors kategori penceresi ve alt kategorileri goruntulenmeli.
- [ ] Juniors kategori sayfasındaki urunler uzerinde islemler (sepete ekleme, begenilenler listesine ekleme ve goruntuleme) yapilabilmeli.
- [ ] Filtreleme ikonları gorunur ve aktif olmalı

---

### US-015

**Açıklama:** Kayıtlı bir  kullanici olarak ana sayfada Women menusune ait kategorileri ve urunleri gorebilmek istiyorum.

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Atanmamış

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfa body bolumunde Women kategori penceresi ve alt kategorileri  goruntulenmeli.
- [ ] Women kategori sayfasındaki urunler uzerinde islemler (sepete ekleme, begenilenler listesine ekleme ve goruntuleme) yapilabilmeli.
- [ ] Filtreleme ikonları gorunur ve aktif olmalı

---

### US-016

**Açıklama:** Kayitli bir kullanici olarak sitedeki islemlerimi ve ayarlarimi kontrol etmek icin sitede bana özel bir dashboard sayfasinin olmasini istiyorum.

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Bekir

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ana sayfada Profile ikonu gorunur ve aktif olmali.
- [ ] Profile ikonuna tıklandıgında dashboard sayfasına gecilmeli
- [ ] Dashboard sayfasinda kullanici icin My Account sayfasında  ozet bilgiler (Total Orders,Total Completed,Total Returnd,Wallet Balance) gorunur olmali.
- [ ] My Account sayfasinda kullanicinin siparis gecmisi goruntulenmeli.

---

### US-017

**Açıklama:** Kayıtlı bir kullanıcı olarak sitede adres bilgilerimi yönetebilecegim bir sayfa olmasını istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Fatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Dashboard sideBar'da Address menu basligi gorunur ve aktif olmali.
- [ ] Adress sayfasında kayıtlı adresler gorunur,duzenlenebilir,silinebilir olmalı
- [ ] Add New Address butonu gorunur ve aktif olmalı
- [ ] Yeni adres kaydı eklenebilmeli

---

### US-018

**Açıklama:** Kayitli bir kullanici olarak Dashboard panelimde alisveris gecmisimi goruntuleyebilecegim bir sayfanin olmasini istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Atanmamış

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Dashboard sideBar'da Order History menu basligi gorunur ve aktif olmali.
- [ ] Order History sayfasinda alisveris gecmisi goruntuleme ikonu aktif olmali.
- [ ] Order History listesinden siparis faturasina ulasilabilmeli.
- [ ] Order History listesinden sipariş iptal edilebilmeli

---

### US-019

**Açıklama:** Kayıtlı bir kullanıcı olarak sitede odemeyi tamamlamadan önce adres bilgilerimi secip yonetebilecegim bir sayfa olmasını istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Atanmamış

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Shipping Information sayfasında Delivery ,Pick Up butonları gorunur ve aktif olmalı
- [ ] Delivery butonu tıklandıgında Shipping adres bilgileri goruntulenmeli,duzenlenebilmeli ,yeni adres eklenebilmeli
- [ ] Order Summary baslıgı altında siparis ozeti goruntulenmeli
- [ ] Save & Pay butonu gorunur ve aktif olmalı

---

### US-020

**Açıklama:** Kayıtlı bir kullanıcı olarak dashboard sayfamda sifre degistirme islemlerimi gerceklestirmek istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Ahmet

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Dashboard sayfasında Change Password linki gorunur ve aktif olmalı
- [ ] Change Password sayfasında Old password,New password,Confirm password text boxları gorunur ve aktif olmalı
- [ ] Save Changes butonu gorunur ve aktif olmalı
- [ ] Yapılan degisiklikler basarıyla kaydedilmeli

---

### US-021

**Açıklama:** Kayıtlı bir kullanıcı olarak sitede kendi profil bilgilerimi duzenleyebilmek istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Ahmet

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Dashboard sayfasında Edit Profile linki gorunur ve aktif olmalı
- [ ] Tıklandıgında Edit Profile sayfasında Full Name,Email,Phone bilgileri duzenlenebilmeli
- [ ] Save Changes butonu gorunur ve aktif olmalı
- [ ] Yapılan degisiklikler basarıyla kaydedilmeli

---

### US-022

**Açıklama:** Kayıtlı bir kullanıcı olarak sistemden guvenli bir sekilde cıkıs yapabilmek istiyorum

- **Öncelik:** 🔴 Highest
- **Sorumlu:** Mfatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfada profil ikonu gorunur olmalı
- [ ] Profil ikonuna tıklandıgında dashboard sidebar da Logout linki gorunur ve aktif olmalı
- [ ] Tıklandıgında basarılı sekilde siteden cıkıs yapılabilmeli

---

### US-023

**Açıklama:** Bir kullanıcı olarak kayıt olmamis kisilerin begendiği urunlerle favori listesi olusturmak icin kayıt sayfasına yonlenmesini istiyorum

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Mfatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Site içerisindeki ürünler uzerinde favori ikonu görünür ve aktif olmalı
- [ ] Urun kaydetmek icin Sıgn in sayfasına gecis yapıp Sign up /Sign in olunmali

---

### US-24

**Açıklama:** Kayitli bir kullanici olarak kontrollerini sagladigim siparislerimin odemesini gerceklestirebilecegim bir odeme sayfasi olmasini istiyorum.

- **Öncelik:** 🔴 Highest
- **Sorumlu:** Mfatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Payment Information sayfasında Select Payment Method menu baslıgı altında odeme yontemi secilmeli
- [ ] Stripe Method'da card bilgi formu doldurulup odeme yapılabilmeli
- [ ] Confirm order butonu gorunur ve aktif olmalı
- [ ] Siparisin tamamlandıgı mesajı goruntulenmeli
- [ ] Siparis detayları ve fatura goruntulenebilmeli
- [ ] Alısverise devam edilebilmeli

---

### US-025

**Açıklama:** Kayıtlı bir kullanici olarak iade etmek istedigim siparisler ile ilgili bir sayfa olmasını ve urun iade islemlerimi yonetebilmeyi istiyorum

- **Öncelik:** 🟡 Medium
- **Sorumlu:** Ahmet

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Dashboard Order History sayfasında secilen siparis (Delivered olan ) sayfasında Return Request butonu gorunur ve aktif olmalı
- [ ] Request Return sayfasında istenen urun secilebilmeli
- [ ] İade nedeni ,not ve attachment eklenebilmeli
- [ ] Request Return butonu gorunur ve aktif olmalı
- [ ] Return Orders sayfasında iade islemi baslatıldıgı goruntulenmeli

---

### US-026

**Açıklama:** Kayıtlı bir kullanıcı olarak sitede favori urunlerim sayfası olmasını ve bu sayfayı yonetebilmeyi istiyorum

- **Öncelik:** 🟠 High
- **Sorumlu:** Ahmet

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Ürünlerin uzerinde favori ikonu gorunur ve aktif olmalı,istenen urun favori listesine eklenmeli
- [ ] Anasayfada Wishlist ikonu gorunur ve aktif olmalı
- [ ] Wishlist sayfasında eklenen urunler goruntulenmeli
- [ ] Istenen urun wishlist sayfasından kaldırılabilmeli

---

### US-027

**Açıklama:** Kayıtlı bir kullanıcı olarak sitede bir sepetim olmasını ve bu sepet işlemlerimi yönetebilmeyi istiyorum

- **Öncelik:** 🔴 Highest
- **Sorumlu:** Mfatih

**Kabul Kriterleri (Acceptance Criteria):**

- [ ] Anasayfada sepet ikonu gorunur ve aktif olmalı
- [ ] Shopping Cart sayfasında sepete eklenmiş urunler goruntulenmeli,silinebilmeli
- [ ] Subtotal bilgisi gorunur olmalı
- [ ] Prooceed to Checkout butonu gorunur ve aktif olmalı odeme sayfasına yonlendirmeli
- [ ] Sepette hiç urun kalmamıs ise alısverise geri donulmeli (Go to Shopping buton gorunur ve aktif olmalı)

---