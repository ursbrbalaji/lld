package search;

public class SearchStrategyFactory {

    public static SearchStrategy getSearchEngineStrategy(SearchType searchType) {

        return switch (searchType) {
            case WORD_SEARCH -> new WordSearchStrategyImpl();
            case SUBSTRING_SEARCH -> new SubStringSearchStrategyImpl();
            default -> null;
        };
    }
}
