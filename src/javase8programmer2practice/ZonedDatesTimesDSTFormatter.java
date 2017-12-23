//Work with dates and times across timezones and manage changes resulting from 
//daylight savings including Format date and times values
package javase8programmer2practice;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author jwitt98
 */
public class ZonedDatesTimesDSTFormatter {
    
    public static void main(String[] args){
        
        LocalDate  ld = LocalDate.of(2015, Month.MARCH, 16);
        LocalTime lt = LocalTime.of(14, 20);
        ZoneId zid = ZoneId.of("US/Central");
        ZonedDateTime zdt = ZonedDateTime.of(ld, lt, zid);//DST
        //prints 2015-03-16T14:20-05:00[America/Chicago]
        System.out.println(zdt);
        
        zdt = zdt.plusMonths(9);//DST ended
        //prints 2015-12-16T14:20-06:00[America/Chicago]
        System.out.println(zdt);
        
        zdt = zdt.withZoneSameInstant(ZoneId.of("US/Eastern"));//1 hour ahead
        //prints 2015-12-16T15:20-05:00[US/Eastern]
        System.out.println(zdt);
        
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        //prints ISO_DATE_TIME format: 2015-12-16T15:20:00-05:00[US/Eastern]
        System.out.println("ISO_DATE_TIME format: " + dtf.format(zdt));
        
        dtf = DateTimeFormatter.ISO_DATE;
        //prints ISO_DATE format: 2015-12-16-05:00
        System.out.println("ISO_DATE format: " + zdt.format(dtf));
        
        dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        //prints ISO_LOCAL_DATE format: 2015-12-16
        System.out.println("ISO_LOCAL_DATE format: " + dtf.format(zdt));
        
        dtf = DateTimeFormatter.ISO_LOCAL_TIME;
        //prints ISO_LOCAL_TIME format: 15:20:00
        System.out.println("ISO_LOCAL_TIME format: " + dtf.format(zdt));
        
        dtf = DateTimeFormatter.ofPattern("MMMM dd uuuu h:mm a");
        //prints ofPattern MMMM dd uuuu h:mm a : December 16 2015 3:20 PM
        System.out.println("ofPattern MMMM dd uuuu h:mm a : " + dtf.format(zdt));
        
        
        //java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: ClockHourOfAmPm
        //System.out.println("using LocalDate: " + ld.format(dtf));
        dtf = DateTimeFormatter.ofPattern("MMMM dd uuuu");
        //prints using LocalDate with correct format: March 16 2015
        System.out.println("using LocalDate with correct format: " + ld.format(dtf));
        
        String mydate = "November, 1 2017";
        ld = LocalDate.parse(mydate, DateTimeFormatter.ofPattern("MMMM, d uuuu"));
        //prints mydate parsed is: 2017-11-01
        System.out.println("mydate parsed is: " + ld);
        
        //prints The current DST offset is PT0S
        System.out.println("The current DST offset is " 
                + ZoneId.of("US/Central").getRules().getDaylightSavings(Instant.now()));
        //prints The DST offset in 182 days is PT1H
        System.out.println("The DST offset in 182 days is " 
                + ZoneId.of("US/Central").getRules().getDaylightSavings(Instant.now().plus(182, ChronoUnit.DAYS)));
        
        System.out.println();
        //printZoneIds();
        
    }
    
    static Set<String> getZoneIds(){
        return ZoneId.getAvailableZoneIds();
    }
    
    static void printZoneIds(){
        TreeSet<String> ts = new TreeSet(getZoneIds());
        
        for(String zi : ts){
            System.out.println(zi);
        }
    }
    
}


