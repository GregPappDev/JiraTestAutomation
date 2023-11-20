package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JiraCreateIssueDialog {
    private WebDriver driver;

    private By projectField = By.id("project-field");
    private By issueTypeField = By.id("issuetype-field");
    private By summaryField = By.id("summary");
    private By attachmentBrowseButton = By.id("attachment-browse-button");
    private By duedateField = By.id("duedate");
    private By duedateTrigger = By.id("duedate-trigger");
    private By textModeButton = By.linkText("Text");// By.xpath("//li[@data-mode='source' and not(@class)]//button[text()='Text']");
    private By descritionField = By.id("description");
    private By assigneeField = By.id("assignee-field");
    private By assignToMeTrigger = By.id("assign-to-me-trigger");
    private By priorityField = By.id("priority-field");
    private By labelTextarea = By.id("label-textarea");
    private By originalEstimate = By.id("timetracking_originalestimate");
    private By remainingEstimate = By.id("timetracking_reminingestimate");
    private By checkboxForCreateAnother = By.id("qf-create-another");
    private By createButton = By.id("create-issue-submit");
    private By cancelButton = By.xpath("//button[@type='button' and @accesskey='`' and @title='Press Alt+` to cancel' and contains(@class, 'cancel')]");


    public JiraCreateIssueDialog(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProject(String project){
        driver.findElement(projectField).sendKeys(project);
    }
    public void selectIssueType(String issueType){
        driver.findElement(issueTypeField).sendKeys(issueType);
    }
    public void addSummary(String summary){
        driver.findElement(summaryField).sendKeys(summary);
    }
    public void clickBrowseAttachment(){
        driver.findElement(attachmentBrowseButton).click();
    }
    public void clickDueDateTrigger(){
        driver.findElement(duedateTrigger);
    }
    public void addDueDate(String dueDate){
        driver.findElement(duedateField).sendKeys(dueDate);
    }
    public void setTextDescriptionMode(){
        driver.findElement(textModeButton).click();
    }
    public void addDescription(String description){
        driver.findElement(descritionField).sendKeys(description);
    }
    public void selectAssignee(String user){
        driver.findElement(assigneeField).sendKeys(user);
    }
    public void assignToMe(){
        driver.findElement(assignToMeTrigger).click();
    }
    public void selectPriority(String level){
        driver.findElement(priorityField).sendKeys(level);
    }
    public void addLabel(String label){
        driver.findElement(labelTextarea).sendKeys(label);
    }
    public void addOriginalEstimate(String time){
        driver.findElement(originalEstimate).sendKeys(time);
    }
    public void addRemainingEstimate(String time){
        driver.findElement(remainingEstimate).sendKeys(time);
    }
    public void clickOnCreateAnother_Checkbox(){
        driver.findElement(checkboxForCreateAnother).click();
    }
    public void clickCreateIssueButton(){
        driver.findElement(createButton).click();
    }
    public void clickOnCancel(){
        driver.findElement(cancelButton).click();
    }
}
