package classes;

import interfaces.RateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final int windowMilliSec;
    private final Map<String, Integer> requestsCount;
    private long windowStartTime;

    public FixedWindowRateLimiter(int maxRequests, int windowMilliSec) {
        this.maxRequests = maxRequests;
        this.windowMilliSec = windowMilliSec;
        this.requestsCount = new ConcurrentHashMap<>();
        this.windowStartTime = System.currentTimeMillis();
    }

    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.windowStartTime > windowMilliSec) {
            requestsCount.remove(userId);
            windowStartTime = currentTime;
        }
        // increment count by 1
        requestsCount.put(userId, requestsCount.getOrDefault(userId, 0) + 1);
        return requestsCount.get(userId) <= this.maxRequests;
    }
}
