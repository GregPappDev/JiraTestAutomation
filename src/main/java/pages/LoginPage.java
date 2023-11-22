package pages;

import org.openqa.selenium.By;


public class LoginPage {

    final private String url = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    final private By userNameInputField  = By.id("login-form-username");
    final private By passwordInputField = By.id("login-form-password");
    final private By loginButton = By.id("login");
    final private By captchaField= By.id("login-form-captcha");

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

    public By getCaptchaField(){return captchaField;}
}
