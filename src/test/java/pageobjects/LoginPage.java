package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageObject;

public class LoginPage extends PageObject {

    @FindBy(id = "j_username")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='j_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name='Submit']")
    private WebElement submitInput;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage loginToJenkins(String adminUsername, String adminPassword){
        this.usernameInput.clear();
        this.usernameInput.sendKeys(adminUsername);

        this.passwordInput.clear();
        this.passwordInput.sendKeys(adminPassword);

        this.submitInput.click();

        return this;
    }
}
