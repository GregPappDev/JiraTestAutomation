package TestCases.LoginTestCases;

import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;



public class LoginUserTestCases {
    final private LoginPage loginPage = new LoginPage();
    protected WebDriver driver;


    public LoginUserTestCases(WebDriver driver) {
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

    public boolean revealCaptcha(String userName, String password, int numberOfTries){

        multipleLoginTries(userName, password, numberOfTries);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> result = driver.findElements(By.id("captchalabel"));
        return result.size() > 0;
    }

    public boolean failCaptcha(String userName, String password){

        WebElement userNameField = driver.findElement(By.id("login-form-username"));
        userNameField.sendKeys(userName);
        WebElement passwordField = driver.findElement(By.id("login-form-password"));
        passwordField.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement captchaField = driver.findElement(By.id("login-form-captcha"));
        captchaField.sendKeys("#######");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        navigateToUrl();
        /* driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); */
        List<WebElement> result = driver.findElements(By.id("login-form-username"));
        return result.size() > 0;
    }

    protected void navigateToUrl(){
        driver.get(loginPage.getUrl());
    }
    protected void fillInUserName(String userName){
        driver.findElement(loginPage.getUserNameInputField())
                .sendKeys(userName);
    }
    protected void fillInPassword(String password){
        driver.findElement(loginPage.getPasswordInputField())
                .sendKeys(password);
    }
    protected void clickSubmitButton(){
        driver.findElement(loginPage.getLoginButton()).click();
    }

    public void multipleLoginTries(String userName, String password, int numberOfTries){

        int counter = 0;
        while(counter < numberOfTries){
            navigateToUrl();
            fillInUserName(userName);
            fillInPassword(password);
            clickSubmitButton();
            counter++;
        }

    }

}
