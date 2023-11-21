import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.JiraCreateIssueDialog;
import pages.JiraLoginPage;
import pages.JiraMainPage;
import utilities.PropertyLoader;
import utilities.WebDriverSetup;

import java.time.Duration;
import java.util.Properties;

public class JiraCreateIssueTest {
    private static WebDriver driver;
    private static Logger logger = LogManager.getLogger(JiraCreateIssueTest.class);
    private static Properties testProperties;
    private static JiraLoginPage loginPage;
    private static JiraMainPage mainPage;
    private static JiraCreateIssueDialog createIssueDialog;



    @BeforeAll
    public static void setUp() {
        driver = WebDriverSetup.getDriver();
        driver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
        Duration waitTimeout = Duration.ofSeconds(5);
        testProperties = PropertyLoader.loadProperties();

        logger.info("Setting up the test with wait timeout: " + waitTimeout.getSeconds() + " seconds");

        loginPage = new JiraLoginPage(driver);
        mainPage = new JiraMainPage(driver, waitTimeout);
        createIssueDialog = new JiraCreateIssueDialog(driver, waitTimeout);

        loginPage.enterUserName(testProperties.getProperty("jira.username"));
        loginPage.enterPassword(testProperties.getProperty("jira.password"));
        loginPage.clickLoginButton();
    }

    @BeforeEach
    public void beforeEach() {
        mainPage.clickCreateButton();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraCreateIssueTestData.csv", numLinesToSkip = 1)
    public void testCreateIssue(
            String project, String issueType, String summary, String description,
            String dueDate, String priority, String label, String originalEstimate, String remainingEstimate) {

        createIssueDialog
                .selectProject(project)
                .selectIssueType(issueType)
                .addSummary(summary)
                .setTextDescriptionMode()
                .addDescription(description)
                .addDueDate(dueDate)
                .assignToMe()
                .selectPriority(priority)
                .addLabel(label)
                .addOriginalEstimate(originalEstimate)
                .addRemainingEstimate(remainingEstimate);

        createIssueDialog.clickCreateIssueButton();
        mainPage.waitForSuccessMessage();
    }

    @AfterAll
    public static void tearDown() {
        WebDriverSetup.closeDriver();
    }
}