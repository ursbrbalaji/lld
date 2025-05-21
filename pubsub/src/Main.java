import classes.Publisher;
import classes.Subscriber;
import classes.Topic;

public class Main {
    public static void main(String[] args) throws Exception {
        Topic topic = new Topic("Topic_1", 2);

        Publisher publisherA = new Publisher("PublisherA");
        Publisher publisherB = new Publisher("PublisherB");

        Subscriber subscriberA = new Subscriber("SubscriberA");
        Subscriber subscriberB = new Subscriber("SubscriberB");

        subscriberA.subscribe(topic);

        System.out.println("Publish Messages Started");
        publisherA.publish(topic, "PublisherA Message 1");
        publisherB.publish(topic, "PublisherB Message 1");
        publisherA.publish(topic, "PublisherA Message 2");
        publisherB.publish(topic, "PublisherB Message 2");
        subscriberB.subscribe(topic);

        Thread.sleep(500);
        Topic topic2 = new Topic("Topic_2", 2);
        publisherA.publish(topic2, "Topic 2 Publisher A Message 1");
        publisherB.publish(topic2, "Topic 2 PublisherB Message 1");
        Thread.sleep(500);
        subscriberA.subscribe(topic2);
        Thread.sleep(5000);
        subscriberB.subscribe(topic2);

    }
}

