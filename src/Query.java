import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Query {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final String service;
    private final String questionType;
    private final Date startDate;
    private final Date endDate;

    private Query(String service, String questionType, Date startDate, Date endDate) {
        this.service = service;
        this.questionType = questionType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Query createQuery(String query) throws ParseException {
        String[] splittedQuery = query.replace("-", " ").split("\\s");

        String queryService = splittedQuery[1];
        String queryQuestionType = splittedQuery[2];
        Date queryStartDate = FORMAT.parse(splittedQuery[4]);

        // check if end date exist
        Date queryEndDate;
        if (splittedQuery.length == 6) {
            queryEndDate = FORMAT.parse(splittedQuery[5]);
        } else {
            queryEndDate = new Date();
        }

        return new Query(queryService, queryQuestionType, queryStartDate, queryEndDate);
    }

    public String getService() {
        return service;
    }

    public String getQuestionType() {
        return questionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
