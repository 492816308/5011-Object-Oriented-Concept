package xwang10_hw1;
import java.time.LocalTime;

enum Dayparts {MORNING, AFTERNOON, EVENING, NIGHT}

public class DayPart {
    private Dayparts dp;

    public DayPart(LocalTime lt) {
        int hour = lt.getHour();
        if (hour >= 5 && hour < 12)
            dp = Dayparts.MORNING;
        else if (hour >= 12 && hour < 17)
            dp = Dayparts.AFTERNOON;
        else if (hour >= 17 && hour < 20)
            dp = Dayparts.EVENING;
        else dp = Dayparts.NIGHT;
    }

    public DayPart() {
        this(LocalTime.now());
    }

    public Dayparts getDayPart() {
        return dp;
    }

    @Override
    public String toString() {
        switch (dp) {
            case MORNING:
                return "morning";
            case AFTERNOON:
                return "afternoon";
            case EVENING:
                return "evening";
            case NIGHT:
                return "night";
            default:
                return "unknown";
        }
    }
}