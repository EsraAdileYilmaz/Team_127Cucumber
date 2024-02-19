package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class FacebookPage {

    //1.adim driver'imizi bu sayfaya tanitmamiz lazim.bunun icin constructor'imizi gorunur hale getirmemiz lazim.
    public FacebookPage(){
        PageFactory.initElements(Driver.getDriver(),this);
        //bu kodla driver'imizi bu class'ta tanitmis olduk
    }

    @FindBy(xpath = "//button[@title='Autoriser tous les cookies']")
    public WebElement cookiesKabulButonu;

    @FindBy(xpath = "//*[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']")
    public WebElement yeniHesapOlusturButonu;//créer nouveau compte

    @FindBy(name = "websubmit")
    public WebElement yeniKayitKaydolButonu;//s'inscrire






}
