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

public class JiraEditIssueDialog {
    private final WebDriverWait wait;
    private final Logger logger = LogManager.getLogger(JiraEditIssueDialog.class);
    private final By title = By.xpath("//*[@id=\"edit-issue-dialog\"]/header/h2");
    private final By issueTypeField = By.id("issuetype-field");
    private final By summaryField = By.id("summary");
    private final By attachmentBrowseButton = By.id("attachment-browse-button");
    private final By duedateField = By.id("duedate");
    private final By duedateTrigger = By.id("duedate-trigger");
    private final By descriptionTextModeButton = By.xpath("(//button[text()='Text'])[1]");
    private final By descriptionDiv = By.id("description-wiki-edit");
    private final By descriptionField = By.id("description");
    private final By assigneeField = By.id("assignee-field");
    private final By assignToMeTrigger = By.id("assign-to-me-trigger");
    private final By priorityField = By.id("priority-field");
    private final By labelTextarea = By.id("labels-textarea");
    private final By originalEstimate = By.id("timetracking_originalestimate");
    private final By remainingEstimate = By.id("timetracking_remainingestimate");
    private final By commentTextModeButton = By.xpath("(//button[text()='Text'])[2]");
    private final By commentDiv = By.id("comment-wiki-edit");
    private final By commentField = By.id("comment");
    private final By updateButton = By.id("edit-issue-submit");
    private final By cancelButton = By.xpath("//button[text()='Cancel']");

    public JiraEditIssueDialog(WebDriver driver, Duration waitTimeout){
        this.wait = new WebDriverWait(driver,waitTimeout);
    }

    public String getTitle(){
        try {
            logger.info("Get 'Edit Issue' title");
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
            String titleText = titleElement.getText();

            logger.info("Title: " + titleText);

            return titleText;
        } catch (WebDriverException e){
            logger.error("Exception while get 'Edit Issue' title" + e.getMessage());
            return null;
        }
    }
    public JiraEditIssueDialog addSummary(String summary){
        try {
            logger.info("Add " + summary + " into 'summary' field");
            WebElement summaryFieldElement = wait.until(ExpectedConditions.elementToBeClickable(summaryField));
            summaryFieldElement.click();
            summaryFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            summaryFieldElement.sendKeys(summary);
            summaryFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e) {
            logger.error("Exception while adding 'summary': " + e.getMessage());
        }
        return this;
    }
    public JiraEditIssueDialog selectIssueType(String issueType){
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

    public JiraEditIssueDialog clickBrowseAttachment(){
        try {
            logger.info("Click on 'browse' link");
            WebElement browseAttachmentElement = wait.until(ExpectedConditions.elementToBeClickable(attachmentBrowseButton));
            browseAttachmentElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while clicking 'browse' link: " + e.getMessage());
        }
        return this;
    }
    public JiraEditIssueDialog clickDueDateTrigger(){
        try {
            logger.info("Clicking on 'Due Date Trigger'");
            WebElement dueDateTriggerElement = wait.until(ExpectedConditions.elementToBeClickable(duedateTrigger));
            dueDateTriggerElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while clicking 'Due Date Trigger': " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog addDueDate(String dueDate){
        try {
            logger.info("Adding due date: " + dueDate);
            WebElement dueDateFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(duedateField));
            dueDateFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            dueDateFieldElement.sendKeys(dueDate);
            dueDateFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding due date: " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog setTextDescriptionMode(){
        try {
            logger.info("Setting text description mode");
            WebElement descriptionTextModeButtonElement = wait.until(ExpectedConditions.elementToBeClickable(descriptionTextModeButton));
            descriptionTextModeButtonElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while setting text description mode: " + e.getMessage());
        }
        return this;
    }
    public JiraEditIssueDialog addDescription(String description){
        try {
            logger.info("Adding description: " + description);
            WebElement descriptionDivElement = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionDiv));
            WebElement descriptionFieldElement = descriptionDivElement.findElement(descriptionField);
            descriptionFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            descriptionFieldElement.sendKeys(description);
        } catch (WebDriverException e){
            logger.error("Exception while adding description: " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog selectAssignee(String user){
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

    public JiraEditIssueDialog assignToMe(){
        try {
            logger.info("Assigning to me");
            WebElement assignToMeTriggerElement = wait.until(ExpectedConditions.elementToBeClickable(assignToMeTrigger));
            assignToMeTriggerElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while assigning to me: " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog selectPriority(String level){
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

    public JiraEditIssueDialog addLabel(String label){
        try {
            logger.info("Adding label: " + label);
            WebElement labelTextareaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(labelTextarea));
            labelTextareaElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            labelTextareaElement.sendKeys(label);
            labelTextareaElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding label: " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog addOriginalEstimate(String time){
        try {
            logger.info("Adding original estimate: " + time);
            WebElement originalEstimateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(originalEstimate));
            originalEstimateElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            originalEstimateElement.sendKeys(time);
            originalEstimateElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding original estimate: " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog addRemainingEstimate(String time){
        try {
            logger.info("Adding remaining estimate: " + time);
            WebElement remainingEstimateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(remainingEstimate));
            remainingEstimateElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            remainingEstimateElement.sendKeys(time);
            remainingEstimateElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while adding remaining estimate: " + e.getMessage());
        }
        return this;
    }

    public JiraEditIssueDialog setCommentTextMode(){
        try {
            logger.info("Setting comment field text mode");
            WebElement commentTextModeButtonElement = wait.until(ExpectedConditions.elementToBeClickable(commentTextModeButton));
            commentTextModeButtonElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while setting comment field text mode: " + e.getMessage());
        }
        return this;
    }
    public JiraEditIssueDialog addComment(String comment){
        try {
            logger.info("Adding description: " + comment);
            WebElement commentDivElement = wait.until(ExpectedConditions.presenceOfElementLocated(commentDiv));
            WebElement commentFieldElement = commentDivElement.findElement(commentField);
            commentFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            commentFieldElement.sendKeys(comment);
        } catch (WebDriverException e){
            logger.error("Exception while adding comment: " + e.getMessage());
        }
        return this;
    }

    public void clickUpdateIssueButton(){
        try {
            logger.info("Click on 'Update' button");
            WebElement updateButtonElement = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
            updateButtonElement.click();
        } catch (WebDriverException e) {
            logger.error("Exception while clicking 'Update' button: " + e.getMessage());
        }
    }
    public void clickOnCancel(){
        try {
            logger.info("Click on 'Cancel' button");
            WebElement cancelButtonElement = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
            cancelButtonElement.click();
        } catch (WebDriverException e) {
            logger.error("Exception while clicking 'Cancel' button: " + e.getMessage());
        }
    }
}
