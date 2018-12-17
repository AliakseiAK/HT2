package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageObject;

import java.util.List;

/**
 * Класс для страницы создания нового пользователя.
 */
public class CreateUserPage extends PageObject {
    private WebElement registerForm;

    @FindBy(xpath = "//a[text()='Create User']")
    private WebElement aTextCreateUser;

    @FindBy(tagName = "form")
    private List<WebElement> allForms;

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password1")
    private WebElement passwordInput;

    @FindBy(name = "password2")
    private WebElement confirmPasswordInput;

    @FindBy(name = "fullname")
    private WebElement fullnameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "Submit")
    private WebElement getSubmitInput;

    public CreateUserPage(WebDriver driver) {
        super(driver);
        this.registerForm = null;
    }

    public boolean testIsFormDisplayed(){
        aTextCreateUser.click();
        for (WebElement currentForm : allForms){
            int textCounter = 0;
            int passwordCounter = 0;
            List<WebElement> allFormChildElements = currentForm.findElements(By.tagName("input"));
            for (WebElement childElement : allFormChildElements){
                switch (childElement.getAttribute("type")){
                    case "text":
                            textCounter++;
                        break;
                    case "password":
                            passwordCounter++;
                        break;
                }

            }
            if (textCounter == 3 && passwordCounter == 2){
                registerForm = currentForm;
                return true;
            }
        }
        return false;
    }

    public boolean areFieldsEmpty(){
        if (this.registerForm != null){
            return this.usernameInput.getText().equals("") &&
                    this.passwordInput.getText().equals("") &&
                    this.confirmPasswordInput.getText().equals("") &&
                    this.emailInput.getText().equals("") &&
                    this.fullnameInput.getText().equals("");
        } else return false;
    }
}