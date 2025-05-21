package interfaces;

public interface RateLimiter {
    boolean isRequestAllowed(String userId);
}
