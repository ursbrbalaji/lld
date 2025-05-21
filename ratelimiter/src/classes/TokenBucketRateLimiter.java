package classes;

import interfaces.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final double refillRate; // per second
    private final Map<String, Integer> tokensBucket;
    private final Map<String, Long> refillTimestamps;

    public TokenBucketRateLimiter(int maxRequests, double refillRate) {
        this.maxRequests = maxRequests;
        this.refillRate = refillRate;
        this.tokensBucket = new ConcurrentHashMap<>();
        this.refillTimestamps = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean isRequestAllowed(String userId) {
        long currTime = System.currentTimeMillis();
        refillTimestamps.putIfAbsent(userId, currTime);
        tokensBucket.putIfAbsent(userId, this.maxRequests);
        long lastRefillTime = refillTimestamps.get(userId);
        long elapsedTime = (lastRefillTime - currTime);
        // Refill the bucket based on elapsed time
        if (elapsedTime > 0) {
            int newTokens = Math.min(this.maxRequests, tokensBucket.get(userId) + (int) (elapsedTime * refillRate));
            tokensBucket.put(userId, newTokens);
            refillTimestamps.put(userId, currTime);
        }
        if (tokensBucket.get(userId) > 0) {
            tokensBucket.put(userId, tokensBucket.get(userId) - 1);
            return true;
        }
        return false;
    }
}
