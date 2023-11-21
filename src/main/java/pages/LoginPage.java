package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    protected WebDriver driver;
    private String url = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    private By userNameInputField  = By.id("login-form-username");
    private By passwordInputField = By.id("login-form-password");
    private By loginButton = By.id("login");

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
