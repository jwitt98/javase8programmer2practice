//Collections Streams and Filters

package javase8programmer2practice;

import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 *
 * @author jwitt98
 */
public class CollectionsStreamsFilters {
    
    public static void main(String[] args){
        
        int i = 450;

        int round = Math.round(i/100f);
        
        System.out.println(round);//prints 5
        
        List<String> al = new ArrayList<>();
        al.add("zero"); al.add("one"); al.add("two"); al.add("three"); al.add("four");
        
        StreamMaker sm = new StreamMaker(al);
        Optional opt = sm.getStream().findAny();
        opt.ifPresent(System.out::println);//prints zero consistently, although could print any element in theory
        
        opt = sm.getStream().findFirst();
        opt.ifPresent(System.out::println);//prints zero
        
        Stream<String> filtered = sm.getStream().filter(str -> str != "two" && str != "four");
        filtered.forEach(str -> System.out.print(str + " "));//prints zero one three
        System.out.println();
        
        IntStream is = ((Stream<String>)sm.getStream()).mapToInt(str -> str.length());//some type erasure happening here?
            is.forEach(x -> System.out.print(x + " "));//prints 4 3 3 5 4 
        System.out.println();
        
        
        
    }
}

class StreamMaker<T>{
    
    private List<T> list;
    
    StreamMaker(List<T> list){
        this.list = list;
    }
    
    Stream<T> getStream(){
        return list.stream();
    }
    
}
