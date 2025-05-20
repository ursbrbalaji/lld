package search;

import entities.Document;
import entities.DocumentDB;

import java.util.List;

public interface SearchStrategy {
    public List<Document> search(DocumentDB documentDB, String text);
}
