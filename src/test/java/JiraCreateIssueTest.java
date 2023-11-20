import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.JiraLoginPage;

public class JiraCreateIssueTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        System.setProperty("web-driver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("");
        JiraLoginPage loginPage = new JiraLoginPage(driver);
        loginPage.enterUserName("automation61");
        loginPage.enterPassword("CCAutoTest19.");
        loginPage.clickLoginButton();
    }

    @Test
    public void testCreateIssue(){

    }

    @AfterAll
    public static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
