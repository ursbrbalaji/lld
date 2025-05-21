package services;

import classes.RateLimitingFactory;
import enums.RateLimitType;
import interfaces.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {

    private final Map<String, RateLimiter> userRateLimiters;

    public RateLimiterService() {
        userRateLimiters = new ConcurrentHashMap<>();
    }

    public void addUser(String userId, RateLimitType rateLimitType, int maxRequestCount, int windowMS){
        userRateLimiters.put(userId, RateLimitingFactory.getRateLimiter(rateLimitType, maxRequestCount, windowMS));
    }

    public synchronized boolean isRequestAllowed(String userId){
        RateLimiter userRateLimiter = this.userRateLimiters.get(userId);
        if(userRateLimiter == null) {
            throw new IllegalArgumentException();
        }
        return userRateLimiter.isRequestAllowed(userId);
    }
}
