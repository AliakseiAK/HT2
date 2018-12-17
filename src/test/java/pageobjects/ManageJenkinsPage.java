package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageObject;

public class ManageJenkinsPage extends PageObject {

    @FindBy(linkText = "Manage Jenkins")
    WebElement manageLink;

    @FindBy(xpath = "//dt[text()='Manage Users']")
    public WebElement dtText;

    @FindBy(xpath = "//dd[text()='Create/delete/modify users that can log in to this Jenkins']")
    public WebElement ddText;

    @FindBy(xpath = "//a//dt[text()='Manage Users']")
    public WebElement dtManageUsersLink;


    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }


    public ManageJenkinsPage getManageUsers(){
        manageLink.click();
        return this;
    }

    public ManageJenkinsPage clickManageUser(){
        dtManageUsersLink.click();
        return this;
    }
}
