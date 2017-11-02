//Develop code that uses Stream data methods and calculation methods
package javase8programmer2practice;

import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class StreamCalc {
    
    public static void main(String[] args){
        
        String[] strArray = {"one","two","three","four","five"};
        
        Stream<String> strStream = Stream.of(strArray);
        Long count = strStream.count();
        System.out.println("Count is: " + count);//prints Count is: 5
        
        strStream = Stream.of(strArray);
        Optional<String> optStr = strStream.max((stra, strb) -> stra.compareTo(strb));
        optStr.ifPresent(System.out::println);//prints two
        
        strStream = Stream.of(strArray);
        optStr = strStream.min((stra, strb) -> stra.compareTo(strb));
        optStr.ifPresent(System.out::println);//prints five
        
        Stream<Integer> IStream = Stream.of(10,15,7,20,33,99,84,2);
        Optional<Integer> optI = IStream.max((a,b)-> a.compareTo(b));
        optI.ifPresent(System.out::println);//prints 99
        
        DoubleStream DStream = DoubleStream.of(20.0, 10.1, 12.2);
        System.out.println("Sum = " + DStream.sum());//prints Sum = 42.3
        
        OptionalDouble ODouble = IntStream.of(0,1,2,3,4,5,6,7,8,9).average();
        ODouble.ifPresent(System.out::println);//prints 4.5
        
        OptionalInt OInt = IntStream.of(4,8,3,1,6,7,2).max();
        OInt.ifPresent(System.out::println);//prints 8
        
        IntSummaryStatistics iss = IntStream.of(0,1,2,3,4,5,6,7,8,9).summaryStatistics();
        iss.accept(10);
        long sum = iss.getSum();
        System.out.println("Sum = " + sum);//prints Sum = 55
        long issCount = iss.getCount();
        System.out.println("Count = " + issCount);//prints Count = 11
        Double avg = iss.getAverage();
        System.out.println("Average = " + avg);//prints Average = 5.0 (55 / 11)
        int max = iss.getMax();
        System.out.println("Max = " + max);//prints Max = 10
        int min = iss.getMin();
        System.out.println("Min = " + min);//prints Min = 0
        IntConsumer is = iss.andThen(i->System.out.println(i * i));
        is.accept(5);
    }
    
}
