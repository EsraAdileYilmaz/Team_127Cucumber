Feature: US1006 Faker Class'i ile Facebook kayit testi

    @P2
  Scenario: TC12 Feyk bilgilerle facebook'a kayit yapilabilmeli

    Given kullanici "facebookUrl" anasayfaya gider
    Then facebook cookies kabul eder
    And facebook'da yeni hesap olustur butonuna basar
    And 1 saniye bekler
    When Faker class'indan bilgilerle facebook kayit formunu doldurur
    Then 2 saniye bekler
    And facebook kayit ol tusuna basar
    Then 2 saniye bekler
    And sayfayi kapatir