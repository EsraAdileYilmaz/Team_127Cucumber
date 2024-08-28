Feature:US1013 Excel olarak verilen urunler ve min. stok miktarlari testi



  Scenario:TC19 verilen stok excelinde verilen min.stok degerlerine gore liste olusturulabilir

    Given kullanici "toUrl" anasayfaya gider
    Then stok excelindeki tum urunleri aratip, min stok miktarinda urun olanlari listeler
    And sayfayi kapatir