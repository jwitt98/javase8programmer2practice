//Create and manage date-based and time-based events including a combination of
//date and time into a single object using LocalDate, LocalTime, LocalDateTime, 
//Instant, Period, and Duration
package javase8programmer2practice;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;

/**
 *
 * @author jwitt98
 */
public class DateTimeInstantPeriodDuration {
    
    public static void main(String[] args){
        
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(ld, lt);
        //prints The current date and time is: 2017-12-09T21:18:08.466
        System.out.println("The current date and time is: " + ldt);
        
        ZoneId zi = ZoneId.systemDefault();
        System.out.println("System default ZoneId is: " + zi);
        
        //Clock clock = Clock.system(ZoneId.of("America/Chicago"));
        Clock clock = Clock.systemDefaultZone();
        System.out.println("Clock is : " + clock);
        System.out.println("Clock instant is " + clock.instant());
        Instant inst = Instant.now();//GMT
        //prints The current timestamp is: 2017-12-10T03:23:39.262Z 
        System.out.println("The current GMT timestamp is: " + inst);
        inst = Instant.now(clock);
        System.out.println("The current system timestamp is: " + inst);
        Period period = Period.ofMonths(1);
        
        
    }
    
}
