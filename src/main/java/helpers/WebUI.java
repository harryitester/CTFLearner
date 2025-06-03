package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class WebUI {
    private static WebDriverWait wait;
    private final Select select;
    private final By locator;

    public static void initDriver() {
        WebDriver driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }

    public WebUI(By locator) {
        this.locator = locator;
        waitForElementVisible(locator);
        this.select = new Select(DriverManager.getDriver().findElement(locator));
    }

    public static void waitForElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible after timeout: " + by.toString());
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException("Element became stale while waiting for visibility: " + by.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for element visibility: " + e.getMessage());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable after timeout: " + by.toString());
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException("Element became stale while waiting for clickable: " + by.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for element clickable: " + e.getMessage());
        }
    }

    public static void clickElement(By by) {
        try {
            waitForElementClickable(by);
            DriverManager.getDriver().findElement(by).click();
        } catch (ElementClickInterceptedException e) {
            try {
                WebElement element = DriverManager.getDriver().findElement(by);
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
            } catch (Exception jsEx) {
                throw new RuntimeException("Failed to click element using both normal and JavaScript click: " + by.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error clicking element: " + e.getMessage());
        }
    }

    public static void setText(By by, String text) {
        waitForElementVisible(by);
        WebElement element = DriverManager.getDriver().findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(By by) {
        try {
            waitForElementVisible(by);
            return DriverManager.getDriver().findElement(by).getText();
        } catch (StaleElementReferenceException e) {
            try {
                waitForElementVisible(by);
                return DriverManager.getDriver().findElement(by).getText();
            } catch (Exception retryEx) {
                throw new RuntimeException("Failed to get text after retry: " + by.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting text: " + e.getMessage());
        }
    }

    public static void selectDropdownByValue(By by, String value) {
        try {
            waitForElementVisible(by);
            Select select = new Select(DriverManager.getDriver().findElement(by));
            select.selectByValue(value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select dropdown value: " + value + " for element: " + by.toString());
        }
    }

    public static boolean isElementDisplayed(By by) {
        try {
            waitForElementVisible(by);
            return DriverManager.getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public static void scrollToElement(By by) {
        WebElement element = DriverManager.getDriver().findElement(by);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public static void verifyElementText(By by, String expectedText) {
        try {
            String actualText = getText(by);
            Assert.assertEquals(actualText, expectedText, "Text mismatch for element: " + by.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error verifying element text: " + e.getMessage());
        }
    }

    public static void waitForElementToDisappear(By by) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element still visible after timeout: " + by.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for element to disappear: " + e.getMessage());
        }
    }

    public void selectByText(String text) {
        try {
            select.selectByVisibleText(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select option by text: " + text + " in dropdown: " + locator);
        }
    }

    public static void clickAndWait(By clickBy, By waitForBy) {
        try {
            WebUI.clickElement(clickBy);
            Thread.sleep(500);
            wait.until(ExpectedConditions.elementToBeClickable(waitForBy));
        } catch (Exception e) {
            throw new RuntimeException("Failed to click and wait: " + e.getMessage());
        }
    }

    public static void clickAndWaitForVisibility(By clickBy, By waitForBy) {
        try {
            WebUI.clickElement(clickBy);
            wait.until(ExpectedConditions.visibilityOfElementLocated(waitForBy));
        } catch (Exception e) {
            throw new RuntimeException("Failed to click and wait for visibility: " + e.getMessage());
        }
    }

    public static void selectDropdownByText(By by, String text) {
        try {
            Thread.sleep(1000);
            waitForElementVisible(by);
            Select select = new Select(DriverManager.getDriver().findElement(by));
            select.selectByVisibleText(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select dropdown text: " + text + " for element: " + by.toString());
        }
    }
} 