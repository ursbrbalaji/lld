package search;

import entities.Document;
import entities.DocumentDB;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchStrategyImpl implements SearchStrategy {

    public WordSearchStrategyImpl() {
    }

    @Override
    public List<Document> search(DocumentDB documentDB, String text) {
        Set<Document> searchDocuments = new HashSet<>();
        String[] words = text.toLowerCase().split(" ");
        for(String word: words){
            searchDocuments.addAll(documentDB.getDocumentsForWord(word));
        }
        return searchDocuments.stream().toList();
    }
}
