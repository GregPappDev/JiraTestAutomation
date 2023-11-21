package TestCases.LoginTestCases;

import Shared.SharedChromeDriver;
import Pages.LoginPage;

import java.time.Duration;

import static Shared.SharedChromeDriver.chromeDriver;

public class LoginValidUser {
    final private LoginPage loginPage = new LoginPage();
    final private String expectedPageTitle = "System Dashboard - Jira Auto";

    public boolean loginValidUserTestCase(String userName, String password){
        navigateToUrl();
        fillInUserName(userName);
        fillInPassword(password);
        clickSubmitButton();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return chromeDriver.getTitle().equals(expectedPageTitle);
    }

    private void navigateToUrl(){
        chromeDriver.get(loginPage.getUrl());
    }
    private void fillInUserName(String userName){
        chromeDriver.findElement(loginPage.getUserNameInputField())
                .sendKeys(userName);
    }
    private void fillInPassword(String password){
        chromeDriver.findElement(loginPage.getPasswordInputField())
                .sendKeys(password);
    }
    private void clickSubmitButton(){
        chromeDriver.findElement(loginPage.getLoginButton()).click();
    }

}
