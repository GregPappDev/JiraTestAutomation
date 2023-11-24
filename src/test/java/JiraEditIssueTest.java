import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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
import org.openqa.selenium.WebDriverException;
import pages.JiraBrowsePage;
import pages.JiraEditIssueDialog;
import pages.JiraMainPage;
import utilities.ExtentManager;
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
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeAll
    @CsvFileSource(resources = "/jiraBrowseIssueTestdata.csv", numLinesToSkip = 1)
    public static void setUp(){
        Properties testProperties = PropertyLoader.loadProperties();

        String reportPath = testProperties.getProperty("reportPath");
        extent = ExtentManager.getInstance(reportPath);
        test = ExtentManager.createTest("JiraEditIssueTest");

        driver = WebDriverSetup.getDriver();
        driver.get(testProperties.getProperty("EDIT_ISSUE_URL"));

        logger.info("Setting up the test with wait timeout: " + waitTimeout.getSeconds() + " seconds");

        LoginHandler loginHandler = new LoginHandler(driver, testProperties);
        loginHandler.performLogin();

        test.log(Status.INFO, "Initialized the test setup");
    }

    @BeforeEach
    public void beforeEach(){
        browsePage = new JiraBrowsePage(driver, waitTimeout);
        mainPage = new JiraMainPage(driver, waitTimeout);
        editIssueDialog = new JiraEditIssueDialog(driver, waitTimeout);

        browsePage.clickEditIssueButton();
        test.log(Status.INFO, "Clicked on 'Edit Issue' button");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraEditIssuesTestData.csv", numLinesToSkip = 1)
    public void testEditIssue(
            String summary, String issueType, String dueDate, String description, String priority,
            String label, String originalEstimate, String remainingEstimate, String comment ){

        logger.info("Test data: Summary={}, IssueType={}, Comment = {}", summary, issueType, comment);

        test.log(Status.INFO, "Test data: Summary=" + summary + ", IssueType=" + issueType + ", Comment=" + comment);

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

        try {
            logger.info("Test step: Verifying that the success message contains 'has been updated'");
            test.log(Status.INFO, "Verifying that the success message contains 'has been updated'");
            Assertions.assertTrue(successMessage.contains("has been updated"), "The issue was not edited successfully");
            test.log(Status.PASS, "Test passed");
        } catch (WebDriverException e){
            logger.error("Exception while check 'Success Message': " + e.getMessage());
            test.log(Status.FAIL, "Test Failed");
        }

    }

    @AfterEach
    public void afterEach(){
        test.log(Status.INFO, "Completed the test scenario");
        logger.info("Test scenario was ended");
    }

    @AfterAll
    public static void tearDown() {
        test.log(Status.INFO, "Web driver now closing");
        logger.info("Web driver now closing");

        WebDriverSetup.closeDriver();

        test.log(Status.INFO, "Web driver closed");
        logger.info("Web driver closed");


        ExtentManager.addTestToReport(extent,test);
    }
}
