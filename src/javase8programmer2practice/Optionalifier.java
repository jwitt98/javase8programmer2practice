//Develop code that uses the Optional class
package javase8programmer2practice;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class Optionalifier {
    
    public static void main(String[] args){
        
        OptionalTest ot1 = new OptionalTest(Optional.of("This is a test, optionally"));
        OptionalTest ot2 = new OptionalTest(Optional.empty());//ot2 has empty Optional<String>
        
        Optional<String> optStr1 = ot1.getTest();
        Optional<String> optStr2 = ot2.getTest();
        
        System.out.println(optStr1.orElse("String is void"));//prints This is a test, optionally
        System.out.println(optStr2.orElse("String is void"));//prints String is void
        
        OptionalDouble dopt = IntStream.rangeClosed(0, 100).average();
        dopt.ifPresent(System.out::println);//prints 50.0
        
        //Optional<String> optStr3 = Optional.of(null);//throws nullPointerException
        Optional<String> optStr3 = Optional.ofNullable(null);//OK
        System.out.println(optStr3);//prints Optional.empty
        //System.out.println(optStr3.get());//throws NoSuchElementException
        System.out.println(optStr3.isPresent());//prints false
        System.out.println(optStr3.orElseGet(()->optStr1.get()));//prints This is a test, optionally
        try{
            System.out.println(optStr3.orElseThrow(IllegalStateException::new));
        }catch(IllegalStateException ise){
            System.out.println("IllegalStateExcpetion Thrown: " + ise.getMessage());
            //prints IllegalStateExcpetion Thrown: null
        }
        
        Optional<Integer> oi = Optional.of(69).map(i-> i + 1);
        oi.ifPresent(System.out::println);//prints 70
        Stream<Integer> si = Stream.of(10,20,30,40,50);
        Optional<Long> os = Optional.of(si).flatMap(t-> Optional.of(t.count()));
        System.out.println(os.orElse(0L));//prints 5
        oi = Optional.of(69).filter(i -> i == 69);
        oi.ifPresent(System.out::println);//prints 69
        
        DoubleStream ds = DoubleStream.of(33.5,78.9,100.5,-12.3,-33.5,99.8);
        OptionalDouble od = ds.max();
        od.ifPresent(System.out::println);//print 100.5
        
    }
    
}
class OptionalTest{
    private final Optional<String> test;
    
    OptionalTest(Optional<String> test){
        this.test = test;
    }

    public Optional<String> getTest(){
        return test;
    }
}