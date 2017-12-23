//Define and create and manage date-based and time-based events using Instant, 
//Period, Duration, and TemporalUnit
package javase8programmer2practice;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 *
 * @author jwitt98
 */
public class InstantPeriodDurationTemporalUnit {
    
    public static void main(String[] args){
        
        Instant instant1 = Instant.parse("2017-10-09T12:15:33.123Z");
        Instant instant2 = Instant.parse("2017-10-12T07:45:03.490Z");
        Duration duration = Duration.between(instant1, instant2);
        //prints The duration is PT67H29M30.367S
        System.out.println("The duration is " + duration);
        
        LocalDate date1 = LocalDate.of(2014, Month.AUGUST, 24);
        LocalDate date2 = LocalDate.of(2017, Month.OCTOBER, 12);
        Period period = Period.between(date1, date2);
        //prints The period beween 2014-08-24 and 2017-10-12 is P3Y1M18D
        System.out.println("The period beween " + date1 + " and " + date2 + " is " + period);
        int periodYears = period.getYears();
        int periodMonths = period.getMonths();
        int periodDays = period.getDays();
        //prints The period is 3 years, 1 Months, 18 days.
        System.out.println("The period is " + periodYears + " years, " + periodMonths 
                + " Months, " + periodDays + " days.");
        
        LocalTime time1 = LocalTime.of(3, 15);
        LocalTime time2 = LocalTime.of(5, 15);
        Duration duration2 = Duration.between(time1, time2);
        //prints The durations is PT1H
        System.out.println("The durations is " + duration2);
        
        TemporalUnit seconds = ChronoUnit.SECONDS;
        //prints The number of seconds in 2 hours is 7200
        System.out.println("The number of seconds in " + duration2.toHours()
            + " hours is " + duration2.get(seconds));
    }
    
}