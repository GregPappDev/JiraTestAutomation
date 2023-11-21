package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.PageFactory;


public class BrowseProjects {
    WebDriver driver;

    private Logger logger = LogManager.getLogger(BrowseProjects.class);
    @FindBy(xpath = "//*[@id=\"browse_link\"]")
    WebElement browseProjectLinks;

    @FindBy(xpath = "//*[@id=\"project_view_all_link_lnk\"]")
    WebElement allProjectsLink;

    @FindBy(xpath = "//*[@id=\"project-filter-text\"]")
    WebElement searchProjectsField;

    public BrowseProjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickBrowseProjectsLink () {
        browseProjectLinks.click();
    }

    private void clickAllProjectsLink() {
        allProjectsLink.click();
    }

    private void enterSearchedProject(String searchedProject) {
        searchProjectsField.click();
        searchProjectsField.sendKeys(searchedProject);
    }

    public void findProject(String searchedProject) {
        clickBrowseProjectsLink();
        clickAllProjectsLink();
        enterSearchedProject(searchedProject);
    }

}
