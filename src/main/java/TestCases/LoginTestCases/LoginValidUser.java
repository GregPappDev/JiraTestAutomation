package TestCases.LoginTestCases;

import TestCases.SharedChromeDriver;
import pages.LoginPage;

import java.time.Duration;

import static TestCases.SharedChromeDriver.driver;

public class LoginValidUser {
    private LoginPage loginPage = new LoginPage();
    private String expectedPageTitle = "System Dashboard - Jira Auto";

    public boolean loginValidUserTestCase(){
        SharedChromeDriver.openBrowser();
        navigateToUrl();
        fillInUserName();
        fillInPassword();
        clickSubmitButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(driver.getTitle());
        return driver.getTitle().equals(expectedPageTitle);
    }

    private void navigateToUrl(){
        driver.get(loginPage.getUrl());
    }
    private void fillInUserName(){
        driver.findElement(loginPage.getUserNameInputField())
                .sendKeys("automation60");
    }
    private void fillInPassword(){
        driver.findElement(loginPage.getPasswordInputField())
                .sendKeys("CCAutoTest19.");
    }
    private void clickSubmitButton(){
        driver.findElement(loginPage.getLoginButton()).click();
    }

}
