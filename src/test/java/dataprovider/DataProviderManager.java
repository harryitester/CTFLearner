package dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderManager {
    @DataProvider(name = "challengeData")
    public Object[][] getChallengeData() {
        return new Object[][] {
            {
                "Web Challenge " + System.currentTimeMillis(),
                "Web",
                "This is a web challenge created by automation",
                "CTF{web_challenge_flag}",
                "100"
            },
            {
                "Crypto Challenge " + System.currentTimeMillis(),
                "Crypto",
                "This is a crypto challenge created by automation",
                "CTF{crypto_challenge_flag}",
                "200"
            }
        };
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"testuser1@example.com", "password123"},
            {"testuser2@example.com", "password456"}
        };
    }
} 