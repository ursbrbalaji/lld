package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Partition {
    private final int id;
    private final List<Message> messages;
    private final Map<Subscriber, AtomicInteger> subcriberOffsets;

    public Partition(int id) {
        this.id = id;
        messages = Collections.synchronizedList(new ArrayList<>());
        subcriberOffsets = new ConcurrentHashMap<>();
    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    public void registerSubscriber(Subscriber subscriber) {
        subcriberOffsets.putIfAbsent(subscriber, new AtomicInteger(0));
    }

    public Message getNextMessageForSubscriber(Subscriber subscriber) {
        AtomicInteger offset = subcriberOffsets.get(subscriber);
        int currOffset = offset.get();
        // Check if messages available to process
        if (currOffset < messages.size()) {
            //increment offset
            offset.incrementAndGet();
            // return message
            return messages.get(currOffset);
        }
        return null;
    }
}
