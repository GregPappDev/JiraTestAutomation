import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pages.JiraCreateIssueDialog;
import pages.JiraMainPage;
import utilities.LoginHandler;
import utilities.PropertyLoader;
import utilities.WebDriverSetup;

import java.time.Duration;
import java.util.Properties;

public class JiraCreateIssueTest {
    private static WebDriver driver;
    private static Logger logger = LogManager.getLogger(JiraCreateIssueTest.class);
    private static Properties testProperties;
    private static JiraMainPage mainPage;
    private static JiraCreateIssueDialog createIssueDialog;
    private static LoginHandler loginHandler;



    @BeforeAll
    public static void setUp() {
        testProperties = PropertyLoader.loadProperties();
        driver = WebDriverSetup.getDriver();
        driver.get(testProperties.getProperty("URL"));
        Duration waitTimeout = Duration.ofSeconds(5);

        logger.info("Setting up the test with wait timeout: " + waitTimeout.getSeconds() + " seconds");

        mainPage = new JiraMainPage(driver, waitTimeout);
        createIssueDialog = new JiraCreateIssueDialog(driver, waitTimeout);

        loginHandler = new LoginHandler(driver, testProperties);
        loginHandler.performLogin();
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

        logger.info("Test data: Project={}, IssueType={}, Summary={}", project, issueType, summary);

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
        String successMessage = mainPage.getSuccessMessage();

        logger.info("Test step: Verifying that the success message is not null");
        Assertions.assertNotNull(successMessage, "Success message is null");

        logger.info("Test step: Verifying that the success message contains 'successfully created'");
        Assertions.assertTrue(successMessage.contains("successfully created"), "The issue was not created successfully");
    }
    @AfterEach
    public void afterEach(){
        logger.info("Test scenario was ended");
    }

    @AfterAll
    public static void tearDown() {
        logger.info("Web driver now closing");
        WebDriverSetup.closeDriver();
        logger.info("Web driver closed");
    }
}