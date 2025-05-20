package search;

import entities.Document;
import entities.DocumentDB;

import java.util.List;
import java.util.stream.Collectors;

public class SubStringSearchStrategyImpl implements SearchStrategy {

    public SubStringSearchStrategyImpl() {
    }

    @Override
    public List<Document> search(DocumentDB documentDB, String text) {
        return documentDB.getAllDocuments()
                .stream().filter(doc -> doc.getText().toLowerCase()
                        .contains(text.toLowerCase())).collect(Collectors.toList());
    }
}
