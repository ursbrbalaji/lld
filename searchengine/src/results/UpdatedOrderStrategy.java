package results;

import entities.Document;

import java.util.Comparator;
import java.util.List;

public class UpdatedOrderStrategy implements  ResultsOrderStrategy {
    @Override
    public List<Document> sort(List<Document> documents, SortOrder sortOrder) {
        if(sortOrder == SortOrder.ASC){
            documents.sort(Comparator.comparing(Document::getLastModifiedAt));
        } else {
            documents.sort((doc1 , doc2)-> doc2.getLastModifiedAt().compareTo(doc1.getLastModifiedAt()));
        }
        return documents;
    }
}
