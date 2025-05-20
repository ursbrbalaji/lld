package entities;

import java.util.*;

public class DocumentDB {
    public List<Document> documents;
    public Map<String, Set<Document>> documentIndex;

    public DocumentDB() {
        this.documents = new ArrayList<>();
        this.documentIndex = new HashMap<>();
    }

    public void addDocument(Document document) {
        this.documents.add(document);
        // Handle case sensitivity for searching
        String[] words = document.getText().toLowerCase().split(" ");
        for (String word : words) {
            if (!documentIndex.containsKey(word)) {
                // Add word to documentIndex
                Set<Document> set = new HashSet<>();
                set.add(document);
                this.documentIndex.put(word, set);
            } else {
                // append document to documentIndex
                this.documentIndex.get(word).add(document);
            }
        }
    }

    public void removeDocument(Document document) {
        this.documents.remove(document);
        // Handle case sensitivity for searching
        String[] words = document.getText().toLowerCase().split(" ");
        for (String word : words) {
            if (documentIndex.containsKey(word)) {
                // remove document to documentIndex
                this.documentIndex.get(word).remove(document);
            }
        }
    }

    public List<Document> getAllDocuments() {
        return this.documents;
    }

    public Set<Document> getDocumentsForWord(String word) {
        return this.documentIndex.get(word);
    }
}
