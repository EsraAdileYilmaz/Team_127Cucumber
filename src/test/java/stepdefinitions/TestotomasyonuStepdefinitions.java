package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.TestOtomasyonuPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestotomasyonuStepdefinitions {

    TestOtomasyonuPage testOtomasyonuPage=new TestOtomasyonuPage();//Bu objeyi pages package'deki locatelere ulasmak icin olusturduk
    Sheet sayfa2;
    int actualStokMiktari;

    @Given("kullanici testotomasyonu anasayfaya gider")
    public void kullanici_testotomasyonu_anasayfaya_gider() {
        Driver.getDriver().get(ConfigReader.getProperty("toUrl"));
    }
    @Then("arama kutusuna phone yazip ENTER tusuna basar")
    public void arama_kutusuna_phone_yazip_enter_tusuna_basar() {
      testOtomasyonuPage.aramaKutusu.sendKeys("phone" + Keys.ENTER);
    }
    @Then("arama sonucunda urun bulunabildigini test eder")
    public void arama_sonucunda_urun_bulunabildigini_test_eder() {
      int sonucSayisi=testOtomasyonuPage.bulunanUrunElementleriList.size();
        Assert.assertTrue(sonucSayisi > 0);
    }
    @Then("sayfayi kapatir")
    public void sayfayi_kapatir() {
       Driver.quitDriver();
    }

    @And("biraz bekler")
    public void birazBekler() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("arama kutusuna shoes yazip ENTER tusuna basar")
    public void aramaKutusunaShoesYazipENTERTusunaBasar() {
        testOtomasyonuPage.aramaKutusu.sendKeys("shoes" +Keys.ENTER);
    }

    @Then("arama kutusuna nutella yazip ENTER tusuna basar")
    public void aramaKutusunaNutellaYazipENTERTusunaBasar() {
        testOtomasyonuPage.aramaKutusu.sendKeys("nutella" + Keys.ENTER);
    }

    @And("arama sonucunda urun bulunamadigini test eder")
    public void aramaSonucundaUrunBulunamadiginiTestEder() {
        int sonucSayisi=testOtomasyonuPage.bulunanUrunElementleriList.size();
        Assert.assertEquals(0,sonucSayisi);

    }


    @Then("arama kutusuna {string} yazip ENTER tusuna basar")
    public void aramaKutusunaYazipENTERTusunaBasar(String istenenUrun) {
        testOtomasyonuPage.aramaKutusu.sendKeys(istenenUrun+ Keys.ENTER);
    }

    @And("{int} saniye bekler")
    public void saniyeBekler(int saniye) {

        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("kullanici {string} anasayfaya gider")
    public void kullaniciAnasayfayaGider(String gidilecekUrl) {
        // gidilecekUrl : gitmek istedigimiz sayfanin configuration.properties'deki ismi
      Driver.getDriver().get(ConfigReader.getProperty(gidilecekUrl));
    }

    @Then("account butonuna basar")
    public void account_butonuna_basar() {
        testOtomasyonuPage.accountButonu.click();
    }

    @Then("email olarak {string} girer")
    public void email_olarak_girer(String emailTuru) {
       testOtomasyonuPage.emailKutusu.sendKeys(ConfigReader.getProperty(emailTuru));
    }
    @Then("password olarak {string} girer")
    public void password_olarak_girer(String passwordTuru) {
        testOtomasyonuPage.passwordKutusu.sendKeys(ConfigReader.getProperty(passwordTuru));
    }
    @Then("signIn butonuna basar")
    public void sign_in_butonuna_basar() {
     testOtomasyonuPage.loginButonu.click();
    }

    @Then("basarili giris yapilabildigini test eder")
    public void basarili_giris_yapilabildigini_test_eder() {
       Assert.assertTrue(testOtomasyonuPage.logoutButonu.isDisplayed());
    }

    @And("sisteme giris yapamadigini test eder")
    public void sistemeGirisYapamadiginiTestEder(){
        Assert.assertTrue(testOtomasyonuPage.emailKutusu.isDisplayed());//email kutusu hala gorunuyorsa giris yapilamamistir
    }

    @When("email olarak listede verilen {string} girer")
    public void emailOlarakListedeVerilenGirer(String verilenEmail) {

        testOtomasyonuPage.emailKutusu.sendKeys(verilenEmail);
    }

    @And("password olarak listede verilen {string} girer")
    public void passwordOlarakListedeVerilenGirer(String verilenPassword) {
        testOtomasyonuPage.passwordKutusu.sendKeys(verilenPassword);
    }

    @Then("stok excelindeki {string} daki urunun stok miktarini bulur")
    public void stokExcelindekiDakiUrununStokMiktariniBulur(String satirNo) {
        // excel'de istenen satira gidip
        // satirdaki urun ismini aldik
        // ve aldigimiz urun ismini testotomasyonu.com'da aratip
        // sonucunu actual stok sayisini(actualStokMiktari olarak) kaydettik

        String dosyaYolu="src/test/java/utilities/stok.xlsx";//Copy Path Reference/Path from content root.bu yolda bir dosya oldugunu gosterir
        Workbook workbook;
        // Workbook workbook=WorkbookFactory.create(fileInputStream) Buna alternative olarak=>  Workbook workbook=new XSSFWorkbook(fileInputStream);
        try {

            FileInputStream fileInputStream=new FileInputStream(dosyaYolu);//var olan dosyanin icini okuma objesi
            workbook= WorkbookFactory.create(fileInputStream);//icini okudugumuz dosyayi kopya excele atadik

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sayfa2=workbook.getSheet("Sayfa2");
        String satirdakiUrunIsmi = sayfa2.getRow(Integer.parseInt(satirNo)-1) .getCell(0).toString();
        //burada satirdaki urunlere ulasip onlari kaydettik

        testOtomasyonuPage.aramaKutusu.sendKeys(satirdakiUrunIsmi+ Keys.ENTER);//excel listesindeki urunleri testotomasyonunda aratip urunu bulucaz.
        actualStokMiktari = testOtomasyonuPage.bulunanUrunElementleriList.size();//excell satirindaki urunun testotomasyonu sitesinde bulunan miktarini verir
    }

    @And("stok miktarinin {string} da verilen stok miktarindan fazla oldugunu test eder")
    public void stokMiktarininDaVerilenStokMiktarindanFazlaOldugunuTestEder(String verilenSatir) {

        // yine excel'de istenen satira gidip
        // o satirda belirlenen min stok miktarini aldik
        // ve bir onceki adimda buldugumuz actualStokMiktari ile karsilastirip
        // testimizi yaptik

        String dosyaYolu="src/test/java/utilities/stok.xlsx";
        Workbook workbook;
        try {
            FileInputStream fileInputStream=new FileInputStream(dosyaYolu);
            workbook= WorkbookFactory.create(fileInputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sayfa2=workbook.getSheet("Sayfa2");

        String minStokMiktariStr = sayfa2
                                   .getRow(Integer.parseInt(verilenSatir)-1)
                                   .getCell(1).toString();//verilen satira gittik ama string olarak
        System.out.println(minStokMiktariStr);//5.0
        int minStokMiktari = (int) Double.parseDouble(minStokMiktariStr);//5.0 verdigi icin Double yaptik

        Assert.assertTrue(actualStokMiktari >= minStokMiktari);

    }

    @Then("stok excelindeki tum urunleri aratip, min stok miktarinda urun olanlari listeler")
    public void stokExcelindekiTumUrunleriArtipMinStokMiktarindaUrunOlanlariListeler() {

        String dosyaYolu="src/test/java/utilities/stok.xlsx";
        Workbook workbook;
        try {
            FileInputStream fileInputStream=new FileInputStream(dosyaYolu);
            workbook= WorkbookFactory.create(fileInputStream); // Workbook workbook=WorkbookFactory.create(fileInputStream) Buna alternative olarak=>  Workbook workbook=new XSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sayfa2=workbook.getSheet("Sayfa2");

        int stokExceliSonSatirNo = sayfa2.getLastRowNum();//sayfa2 deki enson urun stok numarasini verir

        String satirdakiUrunIsmi;
        int satirdakiUrunMinStok;//aranan urunun excel icinde bulunan miktari
        int arananUrunUygulamadaBulunanSonucSayisi;//aranan urunun websitesinde bulunan miktari

        List<String> yeterliStokOlanlarListesi = new ArrayList<>();
        List<String> yeterliStokOlmayanlarListesi = new ArrayList<>();

        for (int i = 1; i <=stokExceliSonSatirNo ; i++) {

            satirdakiUrunIsmi = sayfa2
                               .getRow(i)
                               .getCell(0)
                               .toString();//tum satirlarin 0. hucresi

            satirdakiUrunMinStok = (int)Double.parseDouble(sayfa2
                                   .getRow(i)
                                   .getCell(1)
                                   .toString());//tum satirlarin 1. hucresi

            testOtomasyonuPage.aramaKutusu.sendKeys(satirdakiUrunIsmi+ Keys.ENTER);
            arananUrunUygulamadaBulunanSonucSayisi = testOtomasyonuPage.bulunanUrunElementleriList.size();

            if (arananUrunUygulamadaBulunanSonucSayisi >= satirdakiUrunMinStok){
                yeterliStokOlanlarListesi.add(satirdakiUrunIsmi);
            }else{
                yeterliStokOlmayanlarListesi.add(satirdakiUrunIsmi);//listeye ekliyoruz
            }

        }

        System.out.println("Yeterli stok olan urunler : " + yeterliStokOlanlarListesi);//Yeterli stok olan urunler : [samsung, apple, dress, chair, headphone]
        System.out.println("Yeterli stok OLMAYAN urunler : " + yeterliStokOlmayanlarListesi);//Yeterli stok OLMAYAN urunler : [phone, nutella, shoes]


    }
}
