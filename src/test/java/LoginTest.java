import TestCases.LoginUserTestCases;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {
    private static String userName;
    private static String password;
    private WebDriver driver;
    private LoginUserTestCases loginUser;

    @BeforeAll
    public static void setUp(){
        userName = System.getenv("correctUserName");
        password = System.getenv("correctPassword");

    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginUser = new LoginUserTestCases(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Login valid user successfully")
    public void LoginValidUser_SuccessfulLogin(){

        boolean result = loginUser.loginUserTestCase(userName, password);
        assertTrue(result);
    }

    @Test
    @Order(2)
    @DisplayName("Deny login with valid username and invalid password")
    public void LoginWithIncorrectPassword_LoginDenied(){

        String incorrectPassword = "z";
        boolean result = loginUser.loginUserTestCase("userName", incorrectPassword);
        assertFalse(result);
    }

    @Test
    @Order(3)
    @DisplayName("Deny login with empty credentials")
    public void LoginWithEmptyCredentials_LoginDenied(){

        String emptyUserName = "";
        String emptyPassword = "";
        boolean result = loginUser.loginUserTestCase(emptyUserName, emptyPassword);
        assertFalse(result);
    }

    @Test
    @Order(4)
    @DisplayName("Reveal CAPTCHA after three failed login attempt")
    public void AfterThreeInvalidTries_CaptchaRevealed(){

        int numberOfTries = 3;
        String correctUserName = userName;
        String invalidPassword = "x";

        boolean result = loginUser.revealCaptcha(correctUserName, invalidPassword, numberOfTries);
        assertTrue(result);
    }
    @Test
    @Order(5)
    @DisplayName("Fail CAPTCHA test and deny login")
    public void AfterThreeInvalidTries_FailCaptcha_LoginDenied(){

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
