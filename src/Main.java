import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    // list of waiting timelines before the query
    private static final List<String> waitingTimelineList = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int countOfLines = Integer.parseInt(reader.readLine());

        List<String> data = new ArrayList<>();
        for (int i = 0; i < countOfLines; i++) {
            data.add(reader.readLine());
        }

        for (String s : data) {
            char firstCharacter = s.charAt(0);

            if (isQuery(firstCharacter)) {

                int averageWaitingTime = evaluateAverageWaitingTime(s);
                if (averageWaitingTime == 0) {
                    System.out.println("-");
                } else {
                    System.out.println(averageWaitingTime);
                }

            } else {
                waitingTimelineList.add(s);
            }
        }
    }

    private static boolean isQuery(char c) {
        return c == 'D';
    }

    private static int evaluateAverageWaitingTime(String queryString) throws ParseException {
        int averageWaitingTime = 0;
        int numberOfSuitableWaitingTimelines = 0;

        Query query = Query.createQuery(queryString);
        for (String waitingTimelineString : Main.waitingTimelineList) {

            WaitingTimeline waitingTimeline = WaitingTimeline.createWaitingTimeline(waitingTimelineString);

            if (queryAndWaitingTimelineMatcher(query, waitingTimeline)) {

                averageWaitingTime += waitingTimeline.getTime();
                numberOfSuitableWaitingTimelines++;
            }
        }

        if (averageWaitingTime == 0) {
            return 0;
        }

        return averageWaitingTime / numberOfSuitableWaitingTimelines;
    }

    private static boolean queryAndWaitingTimelineMatcher(Query query, WaitingTimeline waitingTimeline) {

        return serviceMatcher(query.getService(), waitingTimeline.getService()) &&
                questionTypeMatcher(query.getQuestionType(), waitingTimeline.getQuestionType()) &&
                dateMatcher(query.getStartDate(), query.getEndDate(), waitingTimeline.getDate());
    }

    private static boolean serviceMatcher(String queryService, String waitingTimelineService) {
        return dataMatcher(queryService, waitingTimelineService);
    }

    private static boolean questionTypeMatcher(String queryQuestionType, String waitingTimelineQuestionType) {
        return dataMatcher(queryQuestionType, waitingTimelineQuestionType);
    }

    private static boolean dataMatcher(String queryData, String waitingTimelineData) {
        if (queryData.equals("*")) {
            return Boolean.TRUE;
        }

        if (queryData.length() > waitingTimelineData.length()) {
            return Boolean.FALSE;
        }

        for (int i = 0; i < queryData.length(); i++) {
            if (queryData.charAt(i) != waitingTimelineData.charAt(i)) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private static boolean dateMatcher(Date queryStartDate, Date queryEndDate, Date waitingTimelineDate) {

        return waitingTimelineDate.before(queryEndDate) && waitingTimelineDate.after(queryStartDate)
                ? Boolean.TRUE : Boolean.FALSE;
    }

}
