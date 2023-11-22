package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriverException;

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
//        wait.until(ExpectedConditions.visibilityOf(browseProjectLinks));
//        browseProjectLinks.click();
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

    private void enterSearchedProject(String searchedProject) {
        try {
            logger.info("Waiting for searchProjectsField to be clickable");
            wait.until(ExpectedConditions.elementToBeClickable(searchProjectsField));

            logger.info("Clicking on searchProjectsField");
            searchProjectsField.click();

            logger.info("Typing into searchProjectsField");
            searchProjectsField.sendKeys(searchedProject);
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for searchProjectsField to be clickable: \n"+e.getMessage());
        }
//        searchProjectsField.click();
//        searchProjectsField.sendKeys(searchedProject);
    }

    public void findProject(String searchedProject) {
        clickBrowseProjectsLink();
        clickAllProjectsLink();
        enterSearchedProject(searchedProject);
    }

}
