Feature:  Bir kullanici olarak alis-veris yapabilmek icin siteye kayit olabilmek istiyorum

  @registerMethodMail
  Scenario: Method ile register olmaklik
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "ahmet" ismi ve "Query.2026!" sifresi ile kayit olur
    * Register kullanicisi "Sign In" yazi elementinin gorunurlugu ile kayit olundugunu dogrular

  @registerMethodPhone
  Scenario: Uygulama ici alis-veris yapabilmek icin telefon ile kayit olmak istiyorum
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Register kullanicisi "ahmet" , "5435{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur

  @PozitiveRegisterMail
  Scenario: Bazi butonlar icin locate alinmadigina dikkat edelim
  Gecerli bilgilerle uyugulamaya kayit olabilmeliyim
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butonun fonksiyonlarini test eder
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Register kullanicisi "*Use Email Instead" butona tiklar
    * Register kullanicisi "Name" box kutusuna "ahmet" yazar
    * Register kullanicisi "Email" box kutusuna "ahmet.customer1119@QueryCart.com" yazar
    * Register kullanicisi "Password" box kutusuna "Query.2026!" yazar
    * Register kullanicisi "Submit" butona tiklar
    * Register kullanicisi "Sign In" yazi elementinin gorunurlugu ile kayit olundugunu dogrular

  @UsingIndex
  Scenario: index adimlarinda boxName sadece log ciktisi icin gerekli. Sadece index numarasi ile yazi girebilirsiniz.
  index numarasi ile kutulara deger girmek
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butonun fonksiyonlarini test eder
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Register kullanicisi "*Use Email Instead" butona tiklar
    * Register kullanicisi "Name" box kutusuna index 0 ile "ahmet" yazar
    * Register kullanicisi "Email" box kutusuna index 1 ile "ahmet.customer1199@QueryCart.com" yazar
    * Register kullanicisi "Password" box kutusuna index 2 ile "Query.2026!" yazar
    * Register kullanicisi "Submit" butona tiklar
    * Register kullanicisi "Sign In" yazi elementinin gorunurlugu ile kayit olundugunu dogrular


  @pozitiveRegisterPhone
  Scenario: Uygulama icinde alis-veris yapabilmek icin telefon numarasi ile kayit olabilmek istiyorum
    * Register kullanicisi uyugulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Register kullanicisi "Name" box kutusuna index 0 ile "ahmet" yazar
    * Register kullanicisi "Phone" box kutusuna index 1 ile "3693696969" yazar
    * Register kullanicisi "Password" box kutusuna index 2 ile "Query.2026!" yazar
    * Register kullanicisi "Submit" butona tiklar
    * Register kullanicisi "Sign In" yazi elementinin gorunurlugu ile kayit olundugunu dogrular










