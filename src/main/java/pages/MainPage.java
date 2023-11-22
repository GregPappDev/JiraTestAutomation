package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    final private String avatarClassName = "aui-avatar-inner";
    final private String logoutOptionId = "log_out";


    public WebElement getAvatarId(WebDriver driver) {
        return driver.findElement(By.className(avatarClassName));
    }

    public WebElement getLogoutOptionId(WebDriver driver) {
        return driver.findElement(By.id(logoutOptionId));
    }

}
