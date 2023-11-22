package TestCases.LoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LogoutUserTestCases extends LoginUserTestCases{

    private MainPage mainPage = new MainPage();
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    private String logoutText = "Logout";
    public LogoutUserTestCases(WebDriver driver) {
        super(driver);
    }

    public boolean logoutUserTestCase(String userName, String password){
        navigateToUrl();
        fillInUserName(userName);
        fillInPassword(password);
        clickSubmitButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement avatar = driver.findElement(By.className("aui-avatar-inner"));
        //wait.until(d -> avatar.isDisplayed());
        avatar.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement logoutOption = mainPage.getLogoutOptionId(driver);
        //wait.until(driver -> logoutOption.isDisplayed());
        logoutOption.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver.findElement(By.tagName("h1")).getText().equals(logoutText);


    }
}
