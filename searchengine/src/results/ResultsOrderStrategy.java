package results;

import entities.Document;

import java.util.List;

public interface ResultsOrderStrategy {
    public List<Document> sort(List<Document> documents, SortOrder sortOrder );
}
