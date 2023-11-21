package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SharedChromeDriver {
    public static WebDriver driver = new ChromeDriver();
    public static String chromeDriverPath = "D:\\Codecool\\chromedriver.exe";

    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver", SharedChromeDriver.chromeDriverPath);
        driver.manage().window().maximize();
    }
}
