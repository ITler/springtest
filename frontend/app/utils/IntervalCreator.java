package utils;

import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.joda.time.Period;

/**
 * @author "ITler"
 *
 * Just a wrapper around <i>org.joda.time.Interval</i>
 */
public class IntervalCreator {

    private static IntervalCreator instance;
    
    public static IntervalCreator getInstance() {
        if (instance == null)
            instance = new IntervalCreator();
        return instance;
    }
    
    public Interval todayWithDuration(LocalTime start, int minutes) {
        return new Interval(start.toDateTimeToday(), Period.minutes(minutes));
    }
    
    public Interval todayWithDuration(String startTime, int minutes) {
        return todayWithDuration(LocalTime.parse(startTime), minutes);
    }
}
