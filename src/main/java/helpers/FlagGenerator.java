package helpers;

import java.util.Random;

public class FlagGenerator {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();

    public static String generateRandomFlag() {
        StringBuilder flag = new StringBuilder("CTFlearn{");
        int length = random.nextInt(5) + 5; // Random length between 5-10 characters
        
        for (int i = 0; i < length; i++) {
            flag.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        
        flag.append("}");
        return flag.toString();
    }
}