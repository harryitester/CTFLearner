package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import helpers.FlagGenerator;

@Feature("Challenge Creation")
public class CreateChallengeTest extends TestBase {

    @Test(description = "Create a new challenge and verify it appears in My Challenges")
    @Description("Test creates a new challenge and verifies it appears in My Challenges section")
    public void testCreateChallenge() {
        // Test data
        String challengeTitle = "Harry Potter" + System.currentTimeMillis();
        String category = "Web";
        String randomFlag = FlagGenerator.generateRandomFlag();
        String points = "20";
        String howToSolve = "Use the flag to solve the challenge";
        // Login to the application
        loginPage.loginWithConfigCredentials();

        // Navigate to My Challenges page
        challengePage.navigateToChallenges();
        

        // Create a new challenge
        challengePage.createChallenge(
            challengeTitle,
            category,
            randomFlag,
            points,
            howToSolve
        );

        // Navigate to My Challenges page
        challengePage.navigateMyChallenges();

        // Verify the challenge is displayed
        Assert.assertTrue(
            challengePage.isChallengeDisplayedInMyChallenges(challengeTitle),
            "Created challenge is not displayed in My Challenges"
        );
    }
} 