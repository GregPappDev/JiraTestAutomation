package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JiraMainPage {
    private WebDriver driver;

    private By createButton = By.id("create-link");
    public JiraMainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickCreateButton(){
        driver.findElement(createButton).click();
    }
}
