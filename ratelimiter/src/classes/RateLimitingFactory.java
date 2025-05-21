package classes;

import enums.RateLimitType;
import interfaces.RateLimiter;

public class RateLimitingFactory {

    public static RateLimiter getRateLimiter(RateLimitType rateLimitType, int maxRequests, int windowSizeMS) {
        return switch (rateLimitType) {
            case FIXED_WINDOW -> new FixedWindowRateLimiter(maxRequests, windowSizeMS);
            case SLIDING_WINDOW -> new SlidingWindowRateLimiter(maxRequests, windowSizeMS);
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(maxRequests, windowSizeMS);
            default -> {
                throw new IllegalArgumentException("Unsupported RateLimitType");
            }
        };
    }
}
