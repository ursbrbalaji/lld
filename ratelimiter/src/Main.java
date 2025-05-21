import enums.RateLimitType;
import services.RateLimiterService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RateLimiterService rateLimiterService = new RateLimiterService();
        List<String> users = new ArrayList<>(Arrays.asList("user_1", "user_2"));
        List<RateLimitType> rateLimitTypes = new ArrayList<>(Arrays.asList(RateLimitType.FIXED_WINDOW, RateLimitType.SLIDING_WINDOW, RateLimitType.TOKEN_BUCKET));
        for (RateLimitType rateLimitType : rateLimitTypes) {
            System.out.printf("%s Rate Limiter!\n", rateLimitType);
            for (String user : users) {
                // Add user rate limits
                rateLimiterService.addUser(user, rateLimitType, 5, 1);
                for (int i = 1; i <= 20; i++) {
                    boolean requestAllowed = rateLimiterService.isRequestAllowed(user);
                    System.out.printf("user: %s; request count: %d; isRequestAllowed: %b\n", user, i, requestAllowed);
                }
            }
        }


    }
}