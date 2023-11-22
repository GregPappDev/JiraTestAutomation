import TestCases.LoginTestCases.LoginUserTestCase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private static String userName;
    private static String password;

    private WebDriver driver;

    @BeforeAll
    public static void setUp(){
        userName = System.getenv("correctUserName");
        password = System.getenv("correctPassword");

    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();

    }

    @Test
    @Order(1)
    @DisplayName("Login valid user successfully")
    public void LoginValidUser_SuccessfulLogin(){
        driver.manage().window().maximize();
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        boolean result = loginUser.loginUserTestCase(userName, password);
        assertTrue(result);
    }

    @Test
    @DisplayName("Deny login with valid username and invalid password")
    public void LoginWithIncorrectPassword_LoginDenied(){
        driver.manage().window().maximize();
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        String incorrectPassword = "z";
        boolean result = loginUser.loginUserTestCase("userName", incorrectPassword);
        assertFalse(result);
    }

    @Test
    @DisplayName("Deny login with empty credentials")
    public void LoginWithEmptyCredentials_LoginDenied(){
        driver.manage().window().maximize();
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        String emptyUserName = "";
        String emptyPassword = "";
        boolean result = loginUser.loginUserTestCase(emptyUserName, emptyPassword);
        assertFalse(result);
    }

    @Test
    @DisplayName("Reveal CAPTCHA after three failed login attempt")
    public void AfterThreeInvalidTries_CaptchaRevealed(){
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        driver.manage().window().maximize();
        int numberOfTries = 3;
        String correctUserName = userName;
        String invalidPassword = "x";

        boolean result = loginUser.revealCaptcha(correctUserName, invalidPassword, numberOfTries);
        assertTrue(result);
    }
    @Test
    @DisplayName("Fail CAPTCHA test and deny login")
    public void AfterThreeInvalidTries_FailCaptcha_LoginDenied(){
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        driver.manage().window().maximize();
        int numberOfTries = 3;
        String correctUserName = userName;
        String invalidPassword = "x";
        loginUser.multipleLoginTries(correctUserName, invalidPassword, numberOfTries);
        boolean result = loginUser.failCaptcha(userName, password);
        assertTrue(result);
    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
