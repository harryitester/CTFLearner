package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import utils.ConfigReader;

public class LoginPage extends BasePage {
    private ConfigReader config;
    public LoginPage(WebDriver driver) {
        super();
        this.config = new ConfigReader();
    }


    public static final By EMAIL_INPUT = By.id("identifier");
    public static final By PASSWORD_INPUT = By.name("password");
    public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");



    public void navigateToLoginPage() {
        driver.get(config.getLoginUrl());
    }

    public void enterEmail(String email) {
        this.sendKeys(LoginPage.EMAIL_INPUT, email);
    }

    public void enterPassword(String password) {
        this.sendKeys(LoginPage.PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        this.click(LOGIN_BUTTON);
    }

    public void login(String email, String password) {
        navigateToLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void loginWithConfigCredentials() {
        login(config.getUsername(), config.getPassword());
    }
} 