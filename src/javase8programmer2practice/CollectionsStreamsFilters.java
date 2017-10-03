//Collections Streams and Filters

package javase8programmer2practice;

import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author jwitt98
 */
public class CollectionsStreamsFilters {
    
    public static void main(String[] args){
        
        List<String> al = new ArrayList<>();
        al.add("zero"); al.add("one"); al.add("two"); al.add("three"); al.add("four");
        
        StreamMaker sm = new StreamMaker(al);
        Optional opt = sm.getStream().findAny();
        opt.ifPresent(System.out::println);
        
        opt = sm.getStream().findFirst();
        opt.ifPresent(System.out::println);
        
    }
}

class StreamMaker<T>{
    
    private Stream<T> stream;
    
    StreamMaker(List<T> list){
        stream = list.stream();
    }
    
    Stream<T> getStream(){
        return stream;
    }
    
}
