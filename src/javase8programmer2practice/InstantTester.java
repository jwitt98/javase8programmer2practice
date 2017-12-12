
package javase8programmer2practice;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class InstantTester {
    
    public static void main(String[] args){
        
        ZoneId zoneId = ZoneId.systemDefault();
        //prints ZoneId.systemDefault() is : America/Chicago
        System.out.println("ZoneId.systemDefault() is : " + zoneId);
        
        LocalDateTime ldtNow = LocalDateTime.now();
        //prints LocalDateTime.now() is: 2017-12-11T19:30:48.331
        System.out.println("LocalDateTime.now() is: " + ldtNow);
        
        Instant instant = Instant.now();
        //prints Instant.now() is: 2017-12-12T01:30:48.438Z
        System.out.println("Instant.now() is: " + instant);//prints UTC time
        
        Clock clock = Clock.systemDefaultZone();
        //prints Clock.systemDefaultZone() is SystemClock[America/Chicago]
        System.out.println("Clock.systemDefaultZone() is " + clock);
        
        Instant cInstant = clock.instant();
        //prints clock.instant() is: 2017-12-12T01:30:48.443Z
        System.out.println("clock.instant() is: " + cInstant);//still prints UTC time?
        
    }
    
}
