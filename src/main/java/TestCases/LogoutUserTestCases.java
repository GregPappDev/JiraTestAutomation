package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.Duration;


public class LogoutUserTestCases extends LoginUserTestCases{

    final private MainPage mainPage = new MainPage();
    final private String logoutText = "Logout";
    public LogoutUserTestCases(WebDriver driver) {
        super(driver);
    }

    public boolean logoutUserTestCase(String userName, String password){
        login(userName, password);
        logout(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver.findElement(By.tagName("h1")).getText().equals(logoutText);
    }

    private void login(String userName, String password){
        navigateToUrl();
        fillInUserName(userName);
        fillInPassword(password);
        clickSubmitButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void logout(WebDriver driver){
        mainPage.getAvatarClassName(driver).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        mainPage.getLogoutOptionId(driver).click();
    }
}
