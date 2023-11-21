package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraMainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger = LogManager.getLogger(JiraMainPage.class);

    private By createButton = By.id("create_link");
    private By successMessage = By.className("aui-message-success");

    public JiraMainPage(WebDriver driver, Duration waitTimeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTimeout);
    }

    public void clickCreateButton(){
        try {
            WebElement createButtonElement = wait.until(ExpectedConditions.elementToBeClickable(createButton));
            createButtonElement.click();
            logger.info("Clicked on the 'Create' button.");
        } catch (WebDriverException e) {
            logger.error("Exception while waiting for 'Create' button to be clickable: " + e.getMessage());
        }
    }

    public String waitForSuccessMessage() {
        try {
            WebElement successMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            String successMessageText = successMessageElement.getText();
            logger.info("Success Message: " + successMessageText);
            return successMessageText;
        } catch (WebDriverException e) {
            logger.error("Exception while waiting for success message: " + e.getMessage());
            return null;
        }
    }
}
