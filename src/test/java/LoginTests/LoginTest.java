package LoginTests;

import Shared.SharedChromeDriver;
import TestCases.LoginTestCases.LoginValidUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Shared.SharedChromeDriver.chromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @BeforeEach
    public void init(){
        SharedChromeDriver.openBrowser();
    }

    @Test
    public void LoginValidUser_SuccessfulLogin(){
        LoginValidUser loginValidUser = new LoginValidUser();
        String userName = System.getenv("correctUserName");
        String password = System.getenv("correctPassword");
        boolean result = loginValidUser.loginValidUserTestCase(userName, password);
        assertTrue(result);
    }

    @AfterEach
    public void tearDown() {
        SharedChromeDriver.closeBrowser();
    }
}
