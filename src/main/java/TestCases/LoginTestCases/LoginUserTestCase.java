package TestCases.LoginTestCases;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
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

    public boolean multipleLoginTriesTestCase(String userName, String password, int numberOfTries){


        int counter = 0;
        while(counter < numberOfTries){
            navigateToUrl();
            System.out.println(counter);
            WebElement userNameField = driver.findElement(By.id("login-form-username"));
            userNameField.sendKeys(userName);
            WebElement passwordField = driver.findElement(By.id("login-form-password"));
            passwordField.sendKeys((password));
            WebElement loginButton = driver.findElement(By.id("login"));
            loginButton.click();



            counter++;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> result = driver.findElements(By.id("captchalabel"));
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
