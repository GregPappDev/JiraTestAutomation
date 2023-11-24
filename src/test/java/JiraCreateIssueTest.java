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
import pages.JiraCreateIssueDialog;
import pages.JiraMainPage;
import utilities.ExtentManager;
import utilities.LoginHandler;
import utilities.PropertyLoader;
import utilities.WebDriverSetup;

import java.time.Duration;
import java.util.Properties;

public class JiraCreateIssueTest {
    private static WebDriver driver;
    private static final Duration waitTimeout = Duration.ofSeconds(5);
    private static final Logger logger = LogManager.getLogger(JiraCreateIssueTest.class);
    private static JiraMainPage mainPage;
    private static JiraCreateIssueDialog createIssueDialog;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeAll
    public static void setUp() {
        Properties testProperties = PropertyLoader.loadProperties();

        String reportPath = testProperties.getProperty("reportPath");
        extent = ExtentManager.getInstance(reportPath);
        test = ExtentManager.createTest("JiraCreateIssueTest");

        driver = WebDriverSetup.getDriver();
        driver.get(testProperties.getProperty("URL"));

        logger.info("Setting up the test with wait timeout: " + waitTimeout.getSeconds() + " seconds");

        LoginHandler loginHandler = new LoginHandler(driver, testProperties);
        loginHandler.performLogin();

        test.log(Status.INFO, "Initialized the test setup");
    }

    @BeforeEach
    public void beforeEach() {
        mainPage = new JiraMainPage(driver, waitTimeout);
        createIssueDialog = new JiraCreateIssueDialog(driver, waitTimeout);
        mainPage.clickCreateButton();
        test.log(Status.INFO, "Clicked on 'Create' button");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/jiraCreateIssueTestData.csv", numLinesToSkip = 1)
    public void testCreateIssue(
            String project, String issueType, String summary, String description,
            String dueDate, String priority, String label, String originalEstimate, String remainingEstimate) {

        logger.info("Test data: Project={}, IssueType={}, Summary={}", project, issueType, summary);

        test.log(Status.INFO, "Test data: Project=" + project + ", IssueType=" + issueType + ", Summary=" + summary);

        createIssueDialog
                .selectProject(project)
                .selectIssueType(issueType)
                .addSummary(summary)
                .addDueDate(dueDate)
                .setTextDescriptionMode()
                .addDescription(description)
                .assignToMe()
                .selectPriority(priority)
                .addLabel(label)
                .addOriginalEstimate(originalEstimate)
                .addRemainingEstimate(remainingEstimate)
                .clickCreateIssueButton();

        String successMessage = mainPage.getSuccessMessage();

        logger.info("Test step: Verifying that the success message is not null");
        Assertions.assertNotNull(successMessage, "Success message is null");

        logger.info("Test step: Verifying that the success message contains 'successfully created'");
        try {
            test.log(Status.INFO, "Verifying that the success message contains 'has been updated'");
            Assertions.assertTrue(successMessage.contains("successfully created"), "The issue was not created successfully");
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