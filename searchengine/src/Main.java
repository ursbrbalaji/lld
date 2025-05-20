import entities.Document;
import results.ResultsOrderType;
import results.SortOrder;
import search.SearchType;
import service.SearchEngine;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SearchEngine searchEngine =  SearchEngine.getInstance();
        try {
            searchEngine.addDocument("Document Number 1 : User Balaji");
            Thread.sleep(1);
            searchEngine.addDocument("Document Number 2 : User Taarush");
            Thread.sleep(1);
            searchEngine.addDocument("Document Number 3 : User Naga Utpala");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Word Search - Document : Sort - CreatedAt + Ascending");
        // Created Date Word Search Ascending Order
        List<Document> documentList = searchEngine.search("Document", SearchType.WORD_SEARCH, ResultsOrderType.CREATED, SortOrder.ASC);
        for(Document document: documentList){
            System.out.println(document.toString());
        }

        // Created Date Word Search Descending Order
        System.out.println("\nWord Search - Document : Sort - CreatedAt + Descending");
        documentList = searchEngine.search("Document", SearchType.WORD_SEARCH, ResultsOrderType.CREATED, SortOrder.DESC);
        for(Document document: documentList){
            System.out.println(document.toString());
        }

        // Created Date SubString Search Ascending Order
        System.out.println("\nSub String Search - User Taarush : Sort - CreatedAt + Ascending");
        documentList = searchEngine.search("User Taarush", SearchType.SUBSTRING_SEARCH, ResultsOrderType.CREATED, SortOrder.ASC);
        for(Document document: documentList){
            System.out.println(document.toString());
        }

        // Created Date Word Search Ascending Order
        System.out.println("\nWord Search - Document : Sort - CreatedAt + Ascending");
        documentList = searchEngine.search("User Taarush", SearchType.WORD_SEARCH, ResultsOrderType.CREATED, SortOrder.ASC);
        for(Document document: documentList){
            System.out.println(document.toString());
        }

    }
}