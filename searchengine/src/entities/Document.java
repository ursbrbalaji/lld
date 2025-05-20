package entities;

import java.util.Date;
import java.util.UUID;

public class Document extends BaseClass {
    public String id;
    public String text;

    public Document(String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.lastModifiedAt = new Date();
        this.createdAt = new Date();
    }

    public void updateDocument(String newText){
        this.text = newText;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                '}';
    }
}
