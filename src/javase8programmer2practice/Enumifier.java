//Use enumerated types including methods, and constructors in an enum type
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public class Enumifier {
    
    public static void main(String[] args){
        Weekdays today = Weekdays.NODAY;
        System.out.println("I did this on a " + today);
        System.out.println(today + " is the " + today.getDayNumber() + " day of the week");
        System.out.println(today + " is the " + today.getStrNumber() + " day of the week");
    }
    
}
//unums are implicity public, static and final
enum Weekdays{
    
    SUNDAY("1st","First"),MONDAY("2nd", "Second"),TUESDAY("3rd", "Third"),WEDNESDAY("4th", "Fourth")
    ,THURSDAY("5th", "Fifth"),FRIDAY("6th", "Sixth"),SATURDAY("7th", "Seventh"),NODAY("0th");
    
    private String dayNumber;
    private String strNumber;
    
    private Weekdays(String dayNumber){
        this(dayNumber, null);
    }
    
    private Weekdays(String dayNumber, String strNumber){
        this.dayNumber = dayNumber;
        this.strNumber = strNumber;
    }
    
    public String getDayNumber(){
        return dayNumber;
    }
    
    public String getStrNumber(){
        return strNumber;
    }
}