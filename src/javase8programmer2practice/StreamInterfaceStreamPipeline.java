//Describe Stream interface and Stream pipeline
package javase8programmer2practice;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 *
 * @author jwitt98
 */
public class StreamInterfaceStreamPipeline {
    //interfaces: java.util.BaseStream >> (IntStream, LongStream, DoubleStream, Stream<T>)
    //Stream operations can be chained together to form a stream pipeline
    //The 3 parts of a stream pipeline are source, intermediate operations, and terminal operations
    //Source is the stream itself
    //Intermediate are optional operations that can be chained together (map, filter, distinct, sorted)
    //Terminal opertions produce a resuult (sum, foreach, collect, reduce)
    
    public static void main(String[] args){
        
        Stream<Integer> intStream = Stream.of(1,10,2,9,3,8,4,7,5,6);
        intStream.sorted().forEach(i -> System.out.print(i + " "));//prints 1 2 3 4 5 6 7 8 9 10
        System.out.println();
        
        intStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        intStream.map(i -> i *i).forEach(i -> System.out.print(i + " "));//prints 1 4 9 16 25 36 49 64 81 100 
        System.out.println();
        
        intStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        intStream.filter(i -> i % 2 == 0).forEach(i -> System.out.print(i + " "));//prints 2 4 6 8 10 
        System.out.println();
        
        intStream = Stream.of(1,1,2,2,3,3,4,4,5,5);
        intStream.distinct().forEach(i -> System.out.print(i + " "));//prints 1 2 3 4 5 
        System.out.println();
        
        IntStream is = IntStream.of(2,2,2,2,2);
        int sum = is.sum();
        System.out.println("sum is " + sum);//prints sum is 10
        
        is = IntStream.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> intList = is.collect(ArrayList<Integer>::new, (a,b) -> a.add(b * b), (a,b)-> a.addAll(b));
        System.out.println("intList type is: " + intList.getClass().getSimpleName());//prints intList type is: ArrayList
        intList.forEach(i -> System.out.print(i + " "));//prints 1 4 9 16 25 36 49 64 81 100 
        System.out.println();
        
        Stream<Integer> streamOfInts = Stream.of(1,2,3,4,5,6,7,8,9,10);
        //Intream deosn't have an overloaded collect method that takes a Collector like Stream does
        intList = streamOfInts.collect(Collectors.toList());
        intList.subList(2, 8).forEach(j -> System.out.print(j + " "));//prints 3 4 5 6 7 8
        System.out.println();
    }
    
}
