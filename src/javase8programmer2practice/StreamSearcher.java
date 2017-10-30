//Search for data by using search methods of the Stream classes 
//including findFirst, findAny, anyMatch, allMatch, noneMatch
package javase8programmer2practice;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *
 * @author jwitt98
 */
public class StreamSearcher {
    
    public static void main(String[] args){
        
        OptionalInt optInt = IntStream.of(2,4,6,8,10,12,14,16,18,20).findFirst();
        System.out.println(optInt.getAsInt());//prints 2
        
        OptionalInt optInt2 = IntStream.of(1,3,5,7,9,11,13,15,17,19).findAny();
        System.out.println(optInt2.getAsInt());//prints 1 consisitently although not guaranteed
        
        boolean hasEven = IntStream.of(2,4,6,8,10,12,14,16,18,20).anyMatch(i-> i % 2 == 0);
        System.out.println("hasEven is: " + hasEven);//prints hasEven is: true
        hasEven = IntStream.of(1,3,5,7,9,11,13,15,17,19).anyMatch(i-> i % 2 == 0);
        System.out.println("hasEven is: " + hasEven);//prints hasEven is: false
        hasEven = IntStream.of().anyMatch(i-> i % 2 == 0);
        System.out.println("hasEven is: " + hasEven);//prints hasEven is: false
        
        boolean allEven = IntStream.of(2,4,6,8,10,12,14,16,18,20).allMatch(i -> i % 2 == 0);
        System.out.println("allEven is: " + allEven);//prints allEven is: true
        allEven = IntStream.of(1,2,4,6,8,10,12,14,16,18,20).allMatch(i -> i % 2 == 0);
        System.out.println("allEven is: " + allEven);//prints allEven is: false
        allEven = IntStream.of().allMatch(i -> i % 2 == 0);
        System.out.println("allEven is: " + allEven);//prints allEven is: true
        
        boolean noneEven = IntStream.of(2,4,6,8,10,12,14,16,18,20).noneMatch(i -> i % 2 == 0);
        System.out.println("noneEven is: " + noneEven);//prints noneEven is: false
        noneEven = IntStream.of(1,3,5,7,9,11,13,15,17,19).noneMatch(i -> i % 2 == 0);
        System.out.println("noneEven is: " + noneEven);//prints noneEven is: true
        noneEven = IntStream.of().noneMatch(i -> i % 2 == 0);
        System.out.println("noneEven is: " + noneEven);//prints noneEven is: true
        
    }
    
}
