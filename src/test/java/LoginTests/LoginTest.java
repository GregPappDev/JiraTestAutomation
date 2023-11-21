package LoginTests;

import TestCases.LoginTestCases.LoginUserTestCase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    static String userName;
    static String password;
    final private String chromeDriverPath = "D:\\Codecool\\chromedriver.exe";
    WebDriver driver;

    @BeforeAll
    public static void setUp(){
        userName = System.getenv("correctUserName");
        password = System.getenv("correctPassword");

    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Test
    public void LoginValidUser_SuccessfulLogin(){
        driver.manage().window().maximize();
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        boolean result = loginUser.loginUserTestCase(userName, password);
        assertTrue(result);
    }

    @Test
    public void LoginWithIncorrectPassword_LoginDenied(){
        driver.manage().window().maximize();
        LoginUserTestCase loginUser = new LoginUserTestCase(driver);
        String incorrectPassword = "z";
        boolean result = loginUser.loginUserTestCase("userName", incorrectPassword);
        assertFalse(result);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }
}
