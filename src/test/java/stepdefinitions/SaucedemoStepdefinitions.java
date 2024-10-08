package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SaucedemoPage;

public class SaucedemoStepdefinitions {


    SaucedemoPage saucedemoPage = new SaucedemoPage();//Bu objeyi pages package'deki locatelere ulasmak icin olusturduk
    String ilkUrunIsim;

    @Then("saucedemo username kutusuna {string} yazar")
    public void saucedemo_username_kutusuna_yazar(String kullaniciUsername) {

        saucedemoPage.usernameKutusu.sendKeys(kullaniciUsername);
    }

    @Then("saucedemo password kutusuna {string} yazar")
    public void saucedemo_password_kutusuna_yazar(String kullaniciPassword) {

        saucedemoPage.passwordKutusu.sendKeys(kullaniciPassword);
    }

    @Then("saucedemo login tusuna basar")
    public void saucedemo_login_tusuna_basar() {

        saucedemoPage.loginButton.click();
    }

    @Then("ilk urunun ismini kaydeder ve bu urunun sayfasina gider")
    public void ilk_urunun_ismini_kaydeder_ve_bu_urunun_sayfasina_gider() {
        ilkUrunIsim = saucedemoPage.ilkUrunIsimElementi.getText();
        saucedemoPage.ilkUrunIsimElementi.click();

    }

    @When("saucedemo add to Cart butonuna basar")
    public void saucedemo_add_to_cart_butonuna_basar() {
        saucedemoPage.addtocardButton.click();

    }

    @Then("saucedemo alisveris sepetine tiklar")
    public void saucedemo_alisveris_sepetine_tiklar() {

        saucedemoPage.alisverisSepeti.click();
    }

    @Then("sectigi urunun basarili olarak sepete eklendigini test eder")
    public void sectigi_urunun_basarili_olarak_sepete_eklendigini_test_eder() {
        String actualSepettekiUrunIsmi = saucedemoPage.sepettekiUrunIsimElementi.getText();
        Assert.assertEquals(ilkUrunIsim, actualSepettekiUrunIsmi);
        //sepetteki urunun ismi ile sectigimiz urunun ismi ayni mi diye karsilastiriyoruz .
    }


}
