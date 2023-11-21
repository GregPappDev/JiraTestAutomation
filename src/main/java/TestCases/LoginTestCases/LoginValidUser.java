package TestCases.LoginTestCases;

import Shared.SharedChromeDriver;
import Pages.LoginPage;

import java.time.Duration;

import static Shared.SharedChromeDriver.chromeDriver;

public class LoginValidUser {
    final private LoginPage loginPage = new LoginPage();
    final private String expectedPageTitle = "System Dashboard - Jira Auto";

    public boolean loginValidUserTestCase(){
        navigateToUrl();
        fillInUserName();
        fillInPassword();
        clickSubmitButton();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return chromeDriver.getTitle().equals(expectedPageTitle);
    }

    private void navigateToUrl(){
        chromeDriver.get(loginPage.getUrl());
    }
    private void fillInUserName(){
        chromeDriver.findElement(loginPage.getUserNameInputField())
                .sendKeys("automation60");
    }
    private void fillInPassword(){
        chromeDriver.findElement(loginPage.getPasswordInputField())
                .sendKeys("CCAutoTest19.");
    }
    private void clickSubmitButton(){
        chromeDriver.findElement(loginPage.getLoginButton()).click();
    }

}
