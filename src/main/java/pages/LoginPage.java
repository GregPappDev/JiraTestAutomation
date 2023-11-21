package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    protected WebDriver driver;
    final private String url = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    final private By userNameInputField  = By.id("login-form-username");
    final private By passwordInputField = By.id("login-form-password");
    final private By loginButton = By.id("login");

    public String getUrl() {
        return url;
    }
    public By getUserNameInputField() {
        return userNameInputField;
    }

    public By getPasswordInputField() {
        return passwordInputField;
    }

    public By getLoginButton() {
        return loginButton;
    }
}
