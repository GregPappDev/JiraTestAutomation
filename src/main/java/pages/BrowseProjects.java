package pages;

import org.junit.jupiter.api.Assertions;
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
//            searchProjectsField.click();
            wait.until(ExpectedConditions.elementToBeClickable(searchProjectsField));

            logger.info("Typing into searchProjectsField: " + searchedProject);
            searchProjectsField.sendKeys(searchedProject);

            Thread.sleep(1500);
            String projectsText = projectElements.getText();
            if (projectsText.contains("No projects were found")) {
                logger.warn("No projects were found for the search: " + searchedProject);
            }
        } catch (TimeoutException | InterruptedException e) {
            logger.error("Timeout while waiting for searchProjectsField to be clickable: \n"+e.getMessage());
        } catch (NoSuchElementException e) {
            logger.error("Element not found: \n" + searchedProject + " " + e.getMessage());
        }

//    <div class="mod-content" id="projects"><div class="p-list"><table class="aui"><thead><tr><th class="project-list-name sortable active" data-sort-key="name">Project<span class="aui-icon aui-icon-small aui-iconfont-new-arrow-up"></span></th><th class="project-list-key sortable " data-sort-key="key">Key</th><th class="project-list-type sortable " data-sort-key="projectTypeName">Project type</th><th class="project-list-lead sortable " data-sort-key="lead">Project lead</th><th class="project-list-category sortable " data-sort-key="projectCategoryId">Project category</th><th class="project-list-url">URL</th></tr></thead><tbody class="projects-list"><tr data-project-id="10101"><td data-cell-type="name" class="cell-type-name"><a title="Main Testing Project" href="/browse/MTP" atltoken="false" data-track-click="projects.browse.project"><span class="aui-avatar aui-avatar-small aui-avatar-project jira-system-avatar"><span class="aui-avatar-inner"><img src="/secure/projectavatar?size=small&amp;pid=10101"></span></span>Main Testing Project</a></td><td class="cell-type-key">MTP</td><td class="cell-type-project-type">
//    <img src="data:image/svg+xml;base64,PHN2ZyB2ZXJzaW9uPSIxIiBpZD0iV2Fyc3R3YV8xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCA3MiA3MiIgZW5hYmxlLWJhY2tncm91bmQ9Im5ldyAwIDAgNzIgNzIiPjxzdHlsZT4uc3Qye2ZpbGw6I2ZmZn08L3N0eWxlPjxnIGlkPSJHcm91cCI+PGNpcmNsZSBpZD0iT3ZhbCIgY3g9IjM2IiBjeT0iMzYiIHI9IjM2IiBmaWxsPSIjZmY5OTFmIi8+PC9nPjxnIGlkPSJQYWdlLTEiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDE2IDIxKSI+PGcgaWQ9Ikdyb3VwLTMiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDAgLjYxNjcpIj48ZGVmcz48ZmlsdGVyIGlkPSJBZG9iZV9PcGFjaXR5TWFza0ZpbHRlciIgZmlsdGVyVW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4PSIwIiB5PSIuNCIgd2lkdGg9IjE2LjkiIGhlaWdodD0iMjcuNiI+PGZlQ29sb3JNYXRyaXggdmFsdWVzPSIxIDAgMCAwIDAgMCAxIDAgMCAwIDAgMCAxIDAgMCAwIDAgMCAxIDAiLz48L2ZpbHRlcj48L2RlZnM+PG1hc2sgbWFza1VuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeD0iMCIgeT0iLjQiIHdpZHRoPSIxNi45IiBoZWlnaHQ9IjI3LjYiIGlkPSJtYXNrLTJfMV8iPjxnIGZpbHRlcj0idXJsKCNBZG9iZV9PcGFjaXR5TWFza0ZpbHRlcikiPjxwYXRoIGlkPSJwYXRoLTFfMV8iIGNsYXNzPSJzdDIiIGQ9Ik0wIC40aDE2LjlWMjhIMHoiLz48L2c+PC9tYXNrPjxwYXRoIGlkPSJGaWxsLTEiIGQ9Ik0xMy43IDI4Yy0uOCAwLTEuNi0uMy0yLjMtLjlMMS4xIDE2LjhjLTEuNS0xLjUtMS41LTMuOSAwLTUuNGwxMC0xMGMxLjItMS4yIDMuMy0xLjIgNC41IDAgMS4yIDEuMyAxLjIgMy4zIDAgNC41bC04LjIgOC4yIDguNSA4LjVjMS4yIDEuMiAxLjIgMy4zIDAgNC41LS42LjYtMS40LjktMi4yLjkiIG1hc2s9InVybCgjbWFzay0yXzFfKSIgZmlsbD0iI2ZmZiIvPjwvZz48ZyBpZD0iR3JvdXAtNiIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMjMgLjYxNjcpIj48ZGVmcz48ZmlsdGVyIGlkPSJBZG9iZV9PcGFjaXR5TWFza0ZpbHRlcl8xXyIgZmlsdGVyVW5pdHM9InVzZXJTcGFjZU9uVXNlIiB4PSIuNCIgeT0iLjQiIHdpZHRoPSIxNi45IiBoZWlnaHQ9IjI3LjYiPjxmZUNvbG9yTWF0cml4IHZhbHVlcz0iMSAwIDAgMCAwIDAgMSAwIDAgMCAwIDAgMSAwIDAgMCAwIDAgMSAwIi8+PC9maWx0ZXI+PC9kZWZzPjxtYXNrIG1hc2tVbml0cz0idXNlclNwYWNlT25Vc2UiIHg9Ii40IiB5PSIuNCIgd2lkdGg9IjE2LjkiIGhlaWdodD0iMjcuNiIgaWQ9Im1hc2stNF8xXyI+PGcgZmlsdGVyPSJ1cmwoI0Fkb2JlX09wYWNpdHlNYXNrRmlsdGVyXzFfKSI+PHBhdGggaWQ9InBhdGgtM18xXyIgY2xhc3M9InN0MiIgZD0iTS40LjRoMTYuOVYyOEguNHoiLz48L2c+PC9tYXNrPjxwYXRoIGlkPSJGaWxsLTQiIGQ9Ik0xMS43IDE1Ljl6TTMuNiAyOGMtLjggMC0xLjYtLjMtMi4zLS45LTEuMi0xLjItMS4yLTMuMyAwLTQuNWw4LjUtOC41LTguMi04LjNDLjQgNC42LjQgMi41IDEuNiAxLjMgMi44LjEgNC45LjEgNi4xIDEuM2wxMCAxMGMuNy43IDEuMSAxLjcgMS4xIDIuNyAwIDEtLjQgMi0xLjEgMi43TDUuOSAyNy4xYy0uNi42LTEuNS45LTIuMy45eiIgbWFzaz0idXJsKCNtYXNrLTRfMV8pIiBmaWxsPSIjZmZmIi8+PC9nPjwvZz48L3N2Zz4=" class="project-type-icon" title="Software"></td><td class="cell-type-user"><a class="user-hover" rel="jiraadmin" id="_jiraadmin" href="/secure/ViewProfile.jspa?name=jiraadmin">Admin</a></td><td class="cell-type-category">
//    No category</td><td class="cell-type-url">No URL</td></tr></tbody></table></div></div>

//    <div class="jira-adbox jira-adbox-medium no-project-results"><h2>No projects were found to match your search</h2>
//        <p class="no-results-hint">Try modifying your filter criteria.</p></div>
    }

    public void findProject(String searchedProject) {
        clickBrowseProjectsLink();
        clickAllProjectsLink();
        enterSearchedProject(searchedProject);
    }

}
