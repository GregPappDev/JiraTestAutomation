import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BrowseProjects;
import pages.JiraLoginPage;
import pages.JiraMainPage;
import utilities.PropertyLoader;
import utilities.WebDriverSetup;

import java.time.Duration;
import java.util.Properties;

public class BrowseProjectsTest {
    private static WebDriver driver;
    private static Properties testProperties;
    private static JiraLoginPage loginPage;
    private static JiraMainPage mainPage;
    private static Logger logger = LogManager.getLogger(BrowseProjectsTest.class);
    private static BrowseProjects BrowseProjects;

    @BeforeAll
    public static void setUp() {
        System.setProperty("web-driver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        Duration waitTimeout = Duration.ofSeconds(7);
        testProperties = PropertyLoader.loadProperties();

        logger.info("Setting up the test with wait timeout: " + waitTimeout.getSeconds() + " seconds");

        loginPage = new JiraLoginPage(driver);
        mainPage = new JiraMainPage(driver, waitTimeout);

        loginPage.enterUserName(testProperties.getProperty("jira.username"));
        loginPage.enterPassword(testProperties.getProperty("jira.password"));
        loginPage.clickLoginButton();
    }

    @Test
    public void browseProjects() {
        Duration waitTimeout = Duration.ofSeconds(5);
        BrowseProjects browseProjects= new BrowseProjects(driver, waitTimeout);
        browseProjects.findProject("Main");
//        tearDown();
    }

@AfterAll
    public static void tearDown() {
        logger.info("Web driver now closing");
        WebDriverSetup.closeDriver();
        logger.info("Web driver closed");
    }
}
