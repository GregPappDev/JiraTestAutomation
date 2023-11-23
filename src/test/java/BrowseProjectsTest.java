import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.opentest4j.AssertionFailedError;
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

    @ParameterizedTest
    @CsvFileSource(resources = "projects.csv", numLinesToSkip = 1)
    public void browseProjects(String projectName) {
        Duration waitTimeout = Duration.ofSeconds(5);
        BrowseProjects browseProjects= new BrowseProjects(driver, waitTimeout);
        try {
            boolean projectFound = browseProjects.findProject(projectName);
            Assertions.assertTrue(projectFound, "Project not found for the search: " + projectName);
        } catch (AssertionFailedError e) {
            logger.error("AssertionFailedError occurred while browsing projects: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred while browsing projects: " + e.getMessage());
            Assertions.fail("Exception occurred while browsing projects: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        WebDriverSetup.closeDriver();
        logger.info("Web driver closed");
    }

    @AfterAll
    public static void quit() {
        driver.quit();
    }
}
