package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageObject;

import java.util.List;

/**
 * Страница добавления пользователя.
 */
public class AddUserPage extends PageObject {

    @FindBy(name = "username")
    private WebElement adminUsernameInput;

    @FindBy(name = "password1")
    private WebElement passwordInput;

    @FindBy(name = "password2")
    private WebElement confirmPasswordInput;

    @FindBy(name = "fullname")
    private WebElement fullnameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(id = "yui-gen4")
    private WebElement getSubmitInput;

    @FindBy(id = "people")
    private WebElement table;

    public AddUserPage(WebDriver driver) {
        super(driver);
    }

    // Заполнение логина.
    public AddUserPage setUsername(String name) {
        adminUsernameInput.clear();
        adminUsernameInput.sendKeys(name);
        return this;
    }

    // Заполнение пароля.
    public AddUserPage setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    // Подтверждение пароля.
    public AddUserPage setConfirmPassword(String confirmPassword) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }

    // Заполнение полного имени.
    public AddUserPage setFullName(String fullName) {
        fullnameInput.clear();
        fullnameInput.sendKeys(fullName);
        return this;
    }

    // Заполнение email.
    public AddUserPage setEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    // Заполнение формы.
    private AddUserPage setFormFields(String username, String password, String fullname, String email) {
        setUsername(username);
        setPassword(password);
        setConfirmPassword(password);
        setFullName(fullname);
        setEmail(email);
        return this;
    }

    // Отправка данных формы.
    private AddUserPage submitForm() {
        getSubmitInput.click();
        return this;
    }

    // Обёртка для упрощения отправки данных формы.
    public AddUserPage submitFilledForm(String username, String password, String fullname, String email) {
        setFormFields(username, password, fullname, email);
        return submitForm();
    }


    public boolean isUserPresentInList(String username) {
        boolean isFound = false;

        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                boolean isTextDisplayed = cell.getText().equals(username);
                if (isTextDisplayed && cell.isDisplayed()){
                    isFound = true;
                    break;
                }
            }
        }
        return isFound;
    }
}
