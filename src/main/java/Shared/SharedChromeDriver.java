package Shared;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SharedChromeDriver {
    public static WebDriver chromeDriver = new ChromeDriver();
    public static String chromeDriverPath = "D:\\Codecool\\chromedriver.exe";

    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver", SharedChromeDriver.chromeDriverPath);
        chromeDriver.manage().window().maximize();
    }

    public static void closeBrowser(){
        chromeDriver.close();
    }

}
