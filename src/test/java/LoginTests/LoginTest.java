package LoginTests;

import TestCases.LoginTestCases.LoginValidUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static TestCases.SharedChromeDriver.driver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void LoginValidUser_SuccessfulLogin(){
        LoginValidUser loginValidUser = new LoginValidUser();
        boolean result = loginValidUser.loginValidUserTestCase();
        assertTrue(result);
    }

    @AfterAll
    static public void tearDown() {
        driver.close();
    }
}
