package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JiraLoginPage {
    private WebDriver driver;

    private By usernameField = By.id("login-form-username");
    private By passwordField = By.id("login-form-password");
    private By loginButton = By.xpath("//input[@value='Log In']");

    public JiraLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String username){
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
}
