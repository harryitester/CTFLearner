package base;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import pages.ChallengePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import helpers.WebUI;
import helpers.DriverManager;

public class TestBase {
    protected WebDriver driver;
    protected ConfigReader config;
    protected LoginPage loginPage;
    protected ChallengePage challengePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        config = new ConfigReader();
        
        // Initialize page objects
        loginPage = new LoginPage(driver);
        challengePage = new ChallengePage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot();
        }
        DriverFactory.quitDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeClass
    public void setup() {
        WebUI.initDriver();
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        challengePage = new ChallengePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
} 