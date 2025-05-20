package results;

public class ResultsOrderStrategyFactory {
    public static ResultsOrderStrategy getResultsOrderStrategy(ResultsOrderType resultsOrderType) {
        return switch (resultsOrderType) {
            case CREATED -> new PublishOrderStrategy();
            case UPDATED -> new UpdatedOrderStrategy();
            default -> null;
        };
    }
}
