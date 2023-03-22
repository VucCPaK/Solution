import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitingTimeline {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final String service;
    private final String questionType;
    private final Date date;
    private final int time;

    public WaitingTimeline(String service, String questionType, Date date, int time) {
        this.service = service;
        this.questionType = questionType;
        this.date = date;
        this.time = time;
    }

    public static WaitingTimeline createWaitingTimeline(String waitingTimeline) throws ParseException {
        String[] splittedWaitingTimeline = waitingTimeline.split(" ");

        String waitingTimelineService = splittedWaitingTimeline[1];
        String waitingTimelineQuestionType = splittedWaitingTimeline[2];
        Date waitingTimelineDate = FORMAT.parse(splittedWaitingTimeline[4]);
        int waitingTimelineTime = Integer.parseInt(splittedWaitingTimeline[5]);

        return new WaitingTimeline(
                waitingTimelineService,
                waitingTimelineQuestionType,
                waitingTimelineDate,
                waitingTimelineTime);
    }

    public String getService() {
        return service;
    }

    public String getQuestionType() {
        return questionType;
    }

    public Date getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }
}
