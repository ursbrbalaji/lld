package classes;

import interfaces.RateLimiter;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final int windowMilliSec;
    private final Map<String, Queue<Long>> requestTimeStamps;

    public SlidingWindowRateLimiter(int maxRequests, int windowMilliSec) {
        this.maxRequests = maxRequests;
        this.windowMilliSec = windowMilliSec;
        this.requestTimeStamps = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean isRequestAllowed(String userId) {
        long currentTime = System.currentTimeMillis();
        requestTimeStamps.putIfAbsent(userId, new LinkedList<>());
        Queue<Long> timestamps = requestTimeStamps.get(userId);
        if (!timestamps.isEmpty() && currentTime - timestamps.peek() >= windowMilliSec) {
            // Remove the first entry from the queue
            timestamps.remove();
        }
        if (timestamps.size() < maxRequests) {
            // add timestamp at the end of queue
            timestamps.add(currentTime);
            return true;
        }
        return false;
    }
}
