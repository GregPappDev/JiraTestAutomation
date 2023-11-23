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
import pages.JiraBrowsePage;
import pages.JiraEditIssueDialog;
import pages.JiraMainPage;
import utilities.LoginHandler;
import utilities.PropertyLoader;
import utilities.WebDriverSetup;

import java.time.Duration;
import java.util.Properties;

public class JiraEditIssueTest {
    private static WebDriver driver;
    private static final Duration waitTimeout = Duration.ofSeconds(5);
    private static final Logger logger = LogManager.getLogger(JiraEditIssueTest.class);
    private static JiraMainPage mainPage;
    private static JiraBrowsePage browsePage;
    private static JiraEditIssueDialog editIssueDialog;

    @BeforeAll
    @CsvFileSource(resources = "/jiraBrowseIssueTestdata.csv", numLinesToSkip = 1)
    public static void setUp(){
        Properties testProperties = PropertyLoader.loadProperties();
        driver = WebDriverSetup.getDriver();
        driver.get(testProperties.getProperty("EDIT_ISSUE_URL"));

        logger.info("Setting up the test with wait timeout: " + waitTimeout.getSeconds() + " seconds");

        LoginHandler loginHandler = new LoginHandler(driver, testProperties);
        loginHandler.performLogin();


    }

    @BeforeEach
    public void beforeEach(){
        browsePage = new JiraBrowsePage(driver, waitTimeout);
        mainPage = new JiraMainPage(driver, waitTimeout);
        editIssueDialog = new JiraEditIssueDialog(driver, waitTimeout);

        browsePage.clickEditIssueButton();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraEditIssuesTestData.csv", numLinesToSkip = 1)
    public void testEditIssue(
            String summary, String issueType, String dueDate, String description, String priority,
            String label, String originalEstimate, String remainingEstimate, String comment ){

        logger.info("Test data: Summary={}, IssueType={}, Comment = {}", summary, issueType, comment);

        editIssueDialog
                .addSummary(summary)
                .selectIssueType(issueType)
                .addDueDate(dueDate)
                .setTextDescriptionMode()
                .addDescription(description)
                .selectPriority(priority)
                .addLabel(label)
                .addOriginalEstimate(originalEstimate)
                .addRemainingEstimate(remainingEstimate)
                .setCommentTextMode()
                .addComment(comment)
                .clickUpdateIssueButton();

        String successMessage = mainPage.getSuccessMessage();

        logger.info("Test step: Verifying that the success message is not null");
        Assertions.assertNotNull(successMessage, "Success message is null");

        logger.info("Test step: Verifying that the success message contains 'has been updated'");
        Assertions.assertTrue(successMessage.contains("has been updated"), "The issue was not edited successfully");
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
