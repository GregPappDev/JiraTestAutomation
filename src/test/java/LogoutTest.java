import TestCases.LoginTestCases.LoginUserTestCases;
import TestCases.LoginTestCases.LogoutUserTestCases;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest {
    private static String userName;
    private static String password;
    private WebDriver driver;
    private LogoutUserTestCases logoutUser;

    @BeforeAll
    public static void setUp(){
        userName = System.getenv("correctUserName");
        password = System.getenv("correctPassword");

    }

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logoutUser = new LogoutUserTestCases(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Logout user successfully")
    public void LogoutUser_SuccessfulLogout(){

        boolean result = logoutUser.logoutUserTestCase(userName, password);
        assertTrue(result);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
