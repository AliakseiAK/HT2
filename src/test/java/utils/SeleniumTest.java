package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageobjects.LoginPage;

/**
 * Класс, содержащий настройки уровня теста. Этот класс можно будет использовать и в других тестах, не дублируя код.
 * Облегчает сопровождение тестов.
 */
public class SeleniumTest {
    protected static WebDriver driver;


    @BeforeClass
    @Parameters({"browser", "address", "adminUsername", "adminPassword", "firefoxPath", "chromePath"})
    public void setup(String browser, String address, String adminUsername, String adminPassword, String firefoxPath, String chromePath){
        if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.firefox.driver", firefoxPath);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.startup.homepage", "about:blank");
            driver = new FirefoxDriver();

            login(address, adminUsername, adminPassword);

        } else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", chromePath);
            //Chrome стартует с пустым профилем. Если нужен конкретный профиль, его можно установить с помощью:
            //String userProfile= "C:\\Users\\user_name\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";
            //ChromeOptions options = new ChromeOptions();
            //options.addArguments("user-data-dir="+userProfile);
            //driver = new ChromeDriver(options);
            driver = new ChromeDriver();

            login(address, adminUsername, adminPassword);
        }
    }

    @AfterClass
    public static void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
    }


    private void login(String address, String adminUsername, String adminPassword){
        driver.get(address);
        LoginPage page = new LoginPage(driver);
        page.loginToJenkins(adminUsername, adminPassword);
    }
}