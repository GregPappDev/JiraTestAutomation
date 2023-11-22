package TestCases.LoginTestCases;

import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;



public class LoginUserTestCase {
    final private LoginPage loginPage = new LoginPage();
    protected WebDriver driver;

    public LoginUserTestCase(WebDriver driver) {
        this.driver = driver;
    }

    public boolean loginUserTestCase(String userName, String password){
        navigateToUrl();
        fillInUserName(userName);
        fillInPassword(password);
        clickSubmitButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> result = driver.findElements(By.id("header-details-user-fullname"));
       return result.size() > 0;
    }

    private void navigateToUrl(){
        driver.get(loginPage.getUrl());
    }
    private void fillInUserName(String userName){
        driver.findElement(loginPage.getUserNameInputField())
                .sendKeys(userName);
    }
    private void fillInPassword(String password){
        driver.findElement(loginPage.getPasswordInputField())
                .sendKeys(password);
    }
    private void clickSubmitButton(){
        driver.findElement(loginPage.getLoginButton()).click();
    }

}
