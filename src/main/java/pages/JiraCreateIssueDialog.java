package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraCreateIssueDialog {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(JiraCreateIssueDialog.class);

    private By projectField = By.id("project-field");
    private By issueTypeField = By.id("issuetype-field");
    private By summaryField = By.id("summary");
    private By attachmentBrowseButton = By.id("attachment-browse-button");
    private By duedateField = By.id("duedate");
    private By duedateTrigger = By.id("duedate-trigger");
    private By textModeButton = By.xpath("//li[@data-mode='source' and not(@class)]//button[text()='Text']");
    private By descriptionDiv = By.id("description-wiki-edit");
    private By descritionField = By.id("description");
    private By assigneeField = By.id("assignee-field");
    private By assignToMeTrigger = By.id("assign-to-me-trigger");
    private By priorityField = By.id("priority-field");
    private By labelTextarea = By.id("labels-textarea");
    private By originalEstimate = By.id("timetracking_originalestimate");
    private By remainingEstimate = By.id("timetracking_remainingestimate");
    private By checkboxForCreateAnother = By.id("qf-create-another");
    private By createButton = By.id("create-issue-submit");
    private By cancelButton = By.xpath("//button[@type='button' and @accesskey='`' and @title='Press Alt+` to cancel' and contains(@class, 'cancel')]");


    public JiraCreateIssueDialog(WebDriver driver, Duration waitTimeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTimeout);
    }

    public JiraCreateIssueDialog selectProject(String project){
        try {
            logger.info("Select " + project + " project");
            WebElement projectFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(projectField));
            projectFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            projectFieldElement.sendKeys(project);
            projectFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e) {
            logger.error("Exception while selecting project: " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog selectIssueType(String issueType){
        try {
            logger.info("Select " + issueType + " issue type");
            WebElement issueTypeFieldElement = wait.until(ExpectedConditions.elementToBeClickable(issueTypeField));
            issueTypeFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            issueTypeFieldElement.sendKeys(issueType);
            issueTypeFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e) {
            logger.error("Exception while selecting issue type: " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog addSummary(String summary){
        try {
            logger.info("Add " + summary + " into 'summary' field");
            WebElement summaryFieldElement = wait.until(ExpectedConditions.elementToBeClickable(summaryField));
            summaryFieldElement.click();
            summaryFieldElement.sendKeys(summary);
            summaryFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e) {
            logger.error("Exception while adding 'summary': " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog clickBrowseAttachment(){
        try {
            logger.info("Click on 'browse' link");
            WebElement browseAttachmentElement = wait.until(ExpectedConditions.elementToBeClickable(attachmentBrowseButton));
            browseAttachmentElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while clicking 'browse' link: " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog clickDueDateTrigger(){
        try {
            logger.info("Clicking on 'Due Date Trigger'");
            WebElement dueDateTriggerElement = wait.until(ExpectedConditions.elementToBeClickable(duedateTrigger));
            dueDateTriggerElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while clicking 'Due Date Trigger': " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog addDueDate(String dueDate){
        try {
            logger.info("Adding due date: " + dueDate);
            WebElement dueDateFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(duedateField));
            dueDateFieldElement.sendKeys(dueDate);
            dueDateFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding due date: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog setTextDescriptionMode(){
        try {
            logger.info("Setting text description mode");
            WebElement textModeButtonElement = wait.until(ExpectedConditions.elementToBeClickable(textModeButton));
            textModeButtonElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while setting text description mode: " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog addDescription(String description){
        try {
            logger.info("Adding description: " + description);
            WebElement descriptionDivElement = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionDiv));
            WebElement descriptionFieldElement = descriptionDivElement.findElement(descritionField);
            descriptionFieldElement.sendKeys(description);
        } catch (WebDriverException e){
            logger.error("Exception while adding description: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog selectAssignee(String user){
        try {
            logger.info("Selecting assignee: " + user);
            WebElement assigneeFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(assigneeField));
            assigneeFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            assigneeFieldElement.sendKeys(user);
            assigneeFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while selecting assignee: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog assignToMe(){
        try {
            logger.info("Assigning to me");
            WebElement assignToMeTriggerElement = wait.until(ExpectedConditions.elementToBeClickable(assignToMeTrigger));
            assignToMeTriggerElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while assigning to me: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog selectPriority(String level){
        try {
            logger.info("Selecting priority: " + level);
            WebElement priorityFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priorityField));
            priorityFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            priorityFieldElement.sendKeys(level);
            priorityFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while selecting priority: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog addLabel(String label){
        try {
            logger.info("Adding label: " + label);
            WebElement labelTextareaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(labelTextarea));
            labelTextareaElement.sendKeys(label);
            labelTextareaElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding label: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog addOriginalEstimate(String time){
        try {
            logger.info("Adding original estimate: " + time);
            WebElement originalEstimateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(originalEstimate));
            originalEstimateElement.sendKeys(time);
            originalEstimateElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding original estimate: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog addRemainingEstimate(String time){
        try {
            logger.info("Adding remaining estimate: " + time);
            WebElement remainingEstimateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(remainingEstimate));
            remainingEstimateElement.sendKeys(time);
            remainingEstimateElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding remaining estimate: " + e.getMessage());
        }
        return this;
    }

    public JiraCreateIssueDialog clickOnCreateAnother_Checkbox(){
        try {
            logger.info("Click on 'Create Another' checkbox");
            WebElement createAnotherElement = wait.until(ExpectedConditions.elementToBeClickable(checkboxForCreateAnother));
            createAnotherElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while clicking 'Create Another' checkbox: " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog clickCreateIssueButton(){
        try {
            logger.info("Click on 'Create' issue submit button");
            WebElement createButtonElement = wait.until(ExpectedConditions.elementToBeClickable(createButton));
            createButtonElement.click();
        } catch (WebDriverException e) {
            logger.error("Exception while clicking 'Create Issue' button: " + e.getMessage());
        }
        return this;
    }
    public JiraCreateIssueDialog clickOnCancel(){
        try {
            logger.info("Click on 'Cancel' button");
            WebElement cancelButtonElement = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
            cancelButtonElement.click();
        } catch (WebDriverException e) {
            logger.error("Exception while clicking 'Cancel' button: " + e.getMessage());
        }
        return this;
    }
}
