package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SaucedemoPage {

    public SaucedemoPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameKutusu;

    @FindBy(id = "password")
    public WebElement passwordKutusu;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    public WebElement ilkUrunIsimElementi;

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    public WebElement addtocardButton;

    @FindBy(xpath = "//*[@class='shopping_cart_link']")
    public WebElement alisverisSepeti;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButonu;//alisverise devam butonu

    @FindBy(className = "inventory_item_name")
    public WebElement sepettekiUrunIsimElementi;

}
