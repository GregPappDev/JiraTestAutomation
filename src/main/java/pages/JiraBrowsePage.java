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

public class JiraBrowsePage {
    private final WebDriverWait wait;
    private final Logger logger = LogManager.getLogger(JiraBrowsePage.class);

    private final By searchInput = By.id("searcher-query");
    private final By searchButton = By.className("search-button");
    private final By editIssueButton = By.id("edit-issue");

    public JiraBrowsePage(WebDriver driver, Duration waitTimeout) {
        this.wait = new WebDriverWait(driver, waitTimeout);
    }

    public JiraBrowsePage searchIssuesBy(String parameter){
        try {
            logger.info("Click on search input field");
            WebElement searchInputFieldElement = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
            logger.info("Search by issue name: " + parameter);
            searchInputFieldElement.sendKeys(parameter);
            searchInputFieldElement.sendKeys(Keys.RETURN);
        } catch (WebDriverException e){
            logger.error("Exception while click on search input field" + e.getMessage());
        }
        return this;
    }

    public void clickSearchButton(){
        try {
            logger.info("Click on 'Search' button");
            WebElement searchButtonElement = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchButtonElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while click on 'Search' button" + e.getMessage());
        }
    }

    public void checkResult(){
        try {
            logger.info("check result");
        } catch (WebDriverException e){
            logger.error("error" + e.getMessage());
        }
    }

    public void clickEditIssueButton(){
        try {
            logger.info("Click on 'Edit this issue' button");
            WebElement editIssueButtonElement = wait.until(ExpectedConditions.elementToBeClickable(editIssueButton));
            editIssueButtonElement.click();
        } catch (WebDriverException e){
            logger.error("Exception while click on 'Edit this issue' button" + e.getMessage());
        }
    }
}
