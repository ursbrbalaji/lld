package results;

import entities.Document;

import java.util.Comparator;
import java.util.List;

public class PublishOrderStrategy  implements  ResultsOrderStrategy {
    @Override
    public List<Document> sort(List<Document> documents, SortOrder sortOrder) {
        List<Document> sortedDocuments;
        if(sortOrder == SortOrder.ASC){
            documents.sort(Comparator.comparing(Document::getCreatedAt));
        } else {
            documents.sort((doc1 , doc2)-> doc2.getCreatedAt().compareTo(doc1.getCreatedAt()));
        }
        return documents;
    }
}
