package classes;

import java.util.List;

public class Subscriber {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void subscribe(Topic topic) {
        List<Partition> partitionList = topic.getPartitonList();
        for (Partition partition : partitionList) {
            partition.registerSubscriber(this);
            new Thread(() -> consumeMessages(partition)).start();
        }
    }

    private void consumeMessages(Partition partition) {
        try {
            while (true) {
                Thread.sleep(5);
                Message message = partition.getNextMessageForSubscriber(this);
                if (message != null) {
                    processMessage(message);
                }
            }
        } catch (Exception exp) {
            System.out.println(exp.toString());
        }
    }

    private void processMessage(Message message) {
        System.out.println("Consumed Subscriber name = " + name + " content = " + message.getContent());
    }
}
