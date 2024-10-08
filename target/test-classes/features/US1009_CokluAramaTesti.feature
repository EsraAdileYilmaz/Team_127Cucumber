Feature: US1009 Kullanici coklu arama yapar


  @wip
  Scenario Outline:TC15 Kullanici bir listedeki elementleri aratabilmeli

    Given kullanici "toUrl" anasayfaya gider
    Then arama kutusuna "<aranacakKelimeler>" yazip ENTER tusuna basar
    And arama sonucunda urun bulunabildigini test eder
    Then 1 saniye bekler
    And sayfayi kapatir

    Examples:
      | aranacakKelimeler |
      | phone             |
      | java              |
      | samsung           |
      | apple             |
      | nutella           |


    # Eger bir eleman degil, birden fazla eleman aratmak istiyorsak
    # Scenario yerine Scenario Outline kullaniriz.
    # Birden fazla kelime yazmak icin List'in elmasi icinde "<aranacakKelimeler>" yazilmalidir