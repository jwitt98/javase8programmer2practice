//Create and manage date-based and time-based events including a combination of
//date and time into a single object using LocalDate, LocalTime, LocalDateTime, 
//Instant, Period, and Duration
package javase8programmer2practice;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

/**
 *
 * @author jwitt98
 */
public class DateTimeInstantPeriodDuration {
    
    public static void main(String[] args){
        
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        //prints The current date and time is: 2017-12-11T20:15:14.757
        System.out.println("The current date and time is: " + ldt);
        
        Instant instant = Instant.now();//time is UTC
        //prints Instant.now() is: 2017-12-12T02:15:14.757Z
        System.out.println("Instant.now() is: " + instant);
        
        Period period = Period.ofMonths(6);
        ldt = ldt.plus(period);
        //prints Adjusted date and time is: 2018-06-11T20:19:07.738
        System.out.println("Adjusted date and time is: " + ldt);
        
        Duration duration = Duration.ofDays(8);
        ldt = ldt.plus(duration);
        //prints Adjusted date and time is: 2018-06-19T20:21:54.031
        System.out.println("Adjusted date and time is: " + ldt);
        
    }
    
}
