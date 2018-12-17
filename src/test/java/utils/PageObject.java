package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс содержит настройки, общие для всех классов страниц. Реализует Page Factory.
 * Это позволит упростить код, избежать дублирования и облегчает внесение изменений.
 */
public class PageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public PageObject(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
}
