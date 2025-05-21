package classes;

import java.util.Random;

public class Publisher {
    private final String name;
    private final Random random = new Random();

    public Publisher(String name) {
        this.name = name;
    }

    public void publish(Topic topic, String content) {
        Message message = new Message(content);
        int partitionId = random.nextInt(topic.getPartitonList().size());
        Partition partition = topic.getPartitionById(partitionId);
        System.out.println("Published Publisher = " + name + " topic = " + topic.getName() + ", content = " + content);
        partition.addMessage(message);
    }
}
