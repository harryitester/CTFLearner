package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helpers.WebUI;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = helpers.DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    protected void click(By by) {
        WebUI.clickElement(by);
    }

    protected void clickAndWait(By clickBy, By waitForBy) {
        WebUI.clickAndWait(clickBy, waitForBy);
    }

    protected void sendKeys(By by, String text) {
        WebUI.setText(by, text);
    }

    protected String getText(By by) {
        return WebUI.getText(by);
    }

    protected void selectDropdownByText(By by, String text) {
        WebUI.selectDropdownByText(by, text);
    }

    protected boolean isDisplayed(By by) {
        return WebUI.isElementDisplayed(by);
    }

    protected void selectDropdownByValue(By by, String value) {
        WebUI.selectDropdownByValue(by, value);
    }

    protected boolean isElementDisplayed(By by) {
        return WebUI.isElementDisplayed(by);
    }
} 