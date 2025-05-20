package service;

import entities.Document;
import entities.DocumentDB;
import results.ResultsOrderStrategy;
import results.ResultsOrderStrategyFactory;
import results.ResultsOrderType;
import results.SortOrder;
import search.SearchStrategy;
import search.SearchStrategyFactory;
import search.SearchType;

import java.util.ArrayList;
import java.util.List;


public class SearchEngine {
    private static SearchEngine searchEngine;
    private final DocumentDB documentDB;

    private SearchEngine() {
        documentDB = new DocumentDB();
    }

    // Singleton
    public static synchronized SearchEngine getInstance() {
        if (searchEngine == null) {
            return new SearchEngine();
        }
        return searchEngine;
    }

    public void addDocument(String text) {
        Document document = new Document(text);
        documentDB.addDocument(document);
    }

    public void removeDocument(String text) {
        List<Document> documents = documentDB.getAllDocuments();
        for (Document document : documents) {
            if (document.getText() == text) {
                documentDB.removeDocument(document);
                break;
            }
        }
    }

    public List<Document> search(String text, SearchType searchType, ResultsOrderType resultsOrderType, SortOrder sortOrder) {
        SearchStrategy searchStrategy = SearchStrategyFactory.getSearchEngineStrategy(searchType);
        List<Document> searchDocuments = new ArrayList<>(searchStrategy.search(this.documentDB, text));
        ResultsOrderStrategy resultsOrderStrategy = ResultsOrderStrategyFactory.getResultsOrderStrategy(resultsOrderType);
        return resultsOrderStrategy.sort(searchDocuments, sortOrder);
    }


}
