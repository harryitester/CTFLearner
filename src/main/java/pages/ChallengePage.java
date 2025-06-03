package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;

public class ChallengePage extends BasePage {
    public ChallengePage(WebDriver driver) {
        super();
    }

    // Challenges Page Locators
    public static class ChallengePageLocators {
        public static final By CREATE_CHALLENGE_BUTTON = By.cssSelector("a[href*='create-challenge']");
        public static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
        public static final By MY_CHALLENGES_LINK = By.xpath("//*[@id='navbarDropdownMenuLink']/following-sibling::a");
        public static By CHALLENGE_SUB_MENU(String text) {
            return By.xpath("//a[normalize-space(text())='" + text + "']");
        }        //class="dropdown-item"
        // Challenges Form Locators
        public static final By CHALLENGE_TITLE = By.id("title");
        public static final By FLAG = By.id("flag"); //CTFlearn{h4ck3d} example data for flag
        public static final By DESCRIPTION = By.id("flask-pagedown-description");
        public static final By CATEGORY = By.id("category");
        public static final By POINTS = By.id("points");
        public static final By HOW_TO_SOLVE = By.id("howtosolve");
        public static final By SUBMIT_FOR_VERIFICATION = By.xpath("//button[@type='submit']"); 
        public static final By CHALLENGE_TITLE_IN_MY_CHALLENGES = By.xpath("//span[text()= 'Harry Potter1748932950167']");
        public static By CHALLENGE_TITLE_IN_MY_CHALLENGES(String text) {
            return By.xpath("//span[text()= '"+ text +"']");
        } 
    }


    /// Challenges Page Actions

    public void navigateToChallenges() {
        this.clickAndWait(ChallengePageLocators.MY_CHALLENGES_LINK, ChallengePageLocators.CHALLENGE_SUB_MENU("Create Challenge"));
        this.click(ChallengePageLocators.CHALLENGE_SUB_MENU("Create Challenge"));
    }

    public void navigateMyChallenges() {
        this.clickAndWait(ChallengePageLocators.MY_CHALLENGES_LINK, ChallengePageLocators.CHALLENGE_SUB_MENU("My Challenges"));
        this.click(ChallengePageLocators.CHALLENGE_SUB_MENU("My Challenges"));
    }

    public boolean isChallengeDisplayedInMyChallenges(String title) {
        return this.isElementDisplayed(ChallengePageLocators.CHALLENGE_TITLE_IN_MY_CHALLENGES(title));
    }

    public void enterChallengeTitle(String title) {
        this.sendKeys(ChallengePageLocators.CHALLENGE_TITLE, title);
    }

    public void enterTheFlag(String flag) {
        this.sendKeys(ChallengePageLocators.FLAG, flag);
    }

    public void enterTheDescription(String description) {
        this.sendKeys(ChallengePageLocators.DESCRIPTION, description);
    }

    public void selectChallengePoints(String points) {
        this.selectDropdownByValue(ChallengePageLocators.POINTS, points);
    }

    public void enterHowToSolve(String howToSolve) {
        this.sendKeys(ChallengePageLocators.HOW_TO_SOLVE, howToSolve);
    }


    public void clickSubmit() {
        click(ChallengePageLocators.SUBMIT_BUTTON);
    }

    public void createChallenge(String title, String category, String flag, String points, String howToSolve) {
        enterChallengeTitle(title);
        enterTheFlag(flag);
        //enterDescription(description); keep sample description for now
        selectChallengePoints(points);
        enterHowToSolve(howToSolve);
        clickSubmit();
    }

    // Challenges Page Validations
    public boolean isChallengeDisplayed(String challengeTitle) {
        List<WebElement> cards = driver.findElements(ChallengePageLocators.CHALLENGE_TITLE);
        return cards.stream()
                .anyMatch(card -> this.getText(ChallengePageLocators.CHALLENGE_TITLE)
                        .equals(challengeTitle));
    }

    public int getNumberOfChallenges() {
        return driver.findElements(ChallengePageLocators.CHALLENGE_TITLE).size();
    }

    public WebElement getChallengeByTitle(String title) {
        List<WebElement> cards = driver.findElements(ChallengePageLocators.CHALLENGE_TITLE);
        return cards.stream()
                .filter(card -> this.getText(ChallengePageLocators.CHALLENGE_TITLE).equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Challenge with title '" + title + "' not found"));
    }
} 