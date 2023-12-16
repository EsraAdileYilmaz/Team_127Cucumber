package stepdefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;
public class Hooks {
    /*
     cucumber'da testBase class yerine Hooks tercih edilir.

     Burada dikkat etmemiz gereken konu
     diger framework'lerde(junit ve testng) TestBase kullanimi class'dan belirlenebiliyordu.
     Biz Testbase kullanmak istersek extends yapiyorduk,
     istemezsek TestBase kullanmadan class'i calistirabiliyorduk.

     AMMA Cucumber'da boyle tercih imkani yoktur.
     Cunku Runner class'da features klasoru ile stepdefinitions package'i ilisiklendirildi,
     yani stepdefinitions package'i altindaki hangi class'ta olursa olsun
     @Before ve @After method'lari varsa tum scenario'lar icin calisacaktir.

     Bundan dolayi biz sadece
     Scenario FAILED oldugunda calisacak,
     fotografi cekip, rapora ekleyecek bir method olusturacagiz.
     */
    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            final byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png","screenshots");
            Driver.closeDriver();
        }
    }
}










