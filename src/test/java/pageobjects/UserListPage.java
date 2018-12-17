package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageObject;

import java.util.List;

public class UserListPage extends PageObject {

    @FindBy(tagName = "a")
    private List<WebElement> allLinks;

    @FindBy(id = "people")
    private WebElement table;

    public UserListPage(WebDriver driver) {
        super(driver);
    }

    public boolean testDeleteUserLink(String username){
        boolean result = false;
        for (WebElement link : allLinks) {
            String attribute = link.getAttribute("href");
            if (attribute != null){
                if (attribute.contains("user/" + username + "/delete")) {
                    result = true;
                    link.click();
                    break;
                }
            }
        }
        return result;
    }


    public boolean isDeleteLinkPresent(String username)
    {
        boolean found = false;
        List<WebElement> all_links_webpage = driver.findElements(By.tagName("a"));
        for (WebElement link : all_links_webpage) {
            String attribute = link.getAttribute("href");
            if (attribute != null){
                if (attribute.contains("user/" + username + "/delete")) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
