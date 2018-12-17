package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageObject;

/**
 * Класс для страницы подтверждения удаления пользователя.
 */
public class ConfirmDeleteUserPage extends PageObject {

    @FindBy(id = "yui-gen4")
    private WebElement deleteUserButton;

    @FindBy(xpath = "//body")
    private WebElement pageBody;

    public ConfirmDeleteUserPage(WebDriver driver) {
        super(driver);
    }

    public boolean pageTextContents(String query){
        String body = pageBody.getText();
        return body.contains(query);
    }

    public ConfirmDeleteUserPage confirmDeleteUser(){
        deleteUserButton.click();
        return this;
    }

}
