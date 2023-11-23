package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BrowseProjects {
    WebDriver driver;

    private WebDriverWait wait;

    private Logger logger = LogManager.getLogger(BrowseProjects.class);
    @FindBy(xpath = "//*[@id=\"browse_link\"]")
    private WebElement browseProjectLinks;

    @FindBy(xpath = "//*[@id=\"project_view_all_link_lnk\"]")
    private WebElement allProjectsLink;

    @FindBy(xpath = "//*[@id=\"project-filter-text\"]")
    private WebElement searchProjectsField;

    @FindBy(id = "projects")
    private WebElement projectElements;

    public BrowseProjects(WebDriver driver, Duration waitTimeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTimeout);
        PageFactory.initElements(driver, this);
    }

    private void clickBrowseProjectsLink () {
        try {
            wait.until(ExpectedConditions.visibilityOf(browseProjectLinks));
            browseProjectLinks.click();
        } catch (WebDriverException e) {
            logger.error("Exception while clicking browseProjectLinks: \n" + e.getMessage());
        }
    }

    private void clickAllProjectsLink() {
        try {
            logger.info("Waiting for allProjectsLink to be clickable");
            wait.until(ExpectedConditions.elementToBeClickable(allProjectsLink));

            logger.info("Clicking on allProjectsLink");
            allProjectsLink.click();
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for allProjectsLink to be clickable: \n"+ e.getMessage());
        }
    }

    private boolean enterSearchedProject(String searchedProject) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchProjectsField));
            wait.until(ExpectedConditions.elementToBeClickable(searchProjectsField));

            logger.info("Typing into searchProjectsField: " + searchedProject);
            searchProjectsField.sendKeys(searchedProject);
            Thread.sleep(1000);
            String projectsText = projectElements.getText();

            if (projectsText.contains("No projects were found")) {
                logger.warn("No projects were found for the search: " + searchedProject);
            }
            return !projectsText.contains("No projects were found");

        } catch (TimeoutException | InterruptedException e) {
            logger.error("Timeout while waiting for searchProjectsField to be clickable: \n" + e.getMessage());
            throw e;
        }
    }

    public boolean findProject(String searchedProject) throws InterruptedException {
        clickBrowseProjectsLink();
        clickAllProjectsLink();
        return enterSearchedProject(searchedProject);
    }

}
