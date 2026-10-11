Feature: Uygulama icinde alis-veris yapabilmek icin dogru bilgilerle giris yapabilmeliyim

  @pozitiveLoginPhone
  Scenario: Dogru bilgilerle uyugulamaya giris yapabilmeliyim
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign In" butona tiklar
    * Register kullanicisi "Phone" box kutusuna index 0 ile "5435143814" yazar
    * Register kullanicisi "Password" box kutusuna index 1 ile "Query.2026!" yazar
    * Register kullanicisi "Sign In" butona tiklar
    * Register kullanicisi "Profile" butonunun gornurlugu ile giris yapildigini dogrular

  @pozitiveLoginPhoneMethod
  Scenario: Dogru telefon ve sifre ile uygumaya giris yapabilmeliyim
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign In" butona tiklar
    * Register kullanicisi "5435143814" telefon numarasi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Register kullanicisi "Profile" butonunun gornurlugu ile giris yapildigini dogrular

  @PpozitiveLoginMail
  Scenario: Dogru mail ve sifre ile uygulamaya giris yapabilmeliyim
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign In" butona tiklar
    * Register kullanicisi "*Use Email Instead" butona tiklar
    * Register kullanicisi "Email" box kutusuna index 0 ile "ahmet.customer.141248@QueryCart.com" yazar
    * Register kullanicisi "Password" box kutusuna index 1 ile "Query.2026!" yazar
    * Register kullanicisi "Sign In" butona tiklar
    * Register kullanicisi "Profile" butonunun gornurlugu ile giris yapildigini dogrular

  @pozitiveLoginWithMail
  Scenario: Dogru mail ve sifre bilgileri ile uygulamaya giris yapabilmeliyim
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    #* Register kullanicisi "Sign In" butona tiklar
    * Register kullanicisi "ahmet.customer.141248@QueryCart.com" mail adresi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Register kullanicisi "Profile" butonunun gornurlugu ile giris yapildigini dogrular


