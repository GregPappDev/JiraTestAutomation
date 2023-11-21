package utilities;

import org.openqa.selenium.WebDriver;
import pages.JiraLoginPage;

import java.util.Properties;

public class LoginHandler {

    private JiraLoginPage loginPage;
    private Properties testProperties;

    public LoginHandler(WebDriver driver, Properties testProperties) {
        this.loginPage = new JiraLoginPage(driver);
        this.testProperties = testProperties;
    }

    public void performLogin() {
        String username = testProperties.getProperty("jira.username");
        String password = testProperties.getProperty("jira.password");

        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}
