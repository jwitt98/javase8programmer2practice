//Develop code to extract data from an object using peek() and map() 
//methods including primitive versions of the map() method
package javase8programmer2practice;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class MapPeeker {
    
    public static void main(String[] args){
        
        Long count = Stream.of(1,2,4,5,6,7).peek(i->System.out.print(i + " "))
                .map(i-> i * i).peek(i->System.out.print(i + " ")).count();//prints 1 1 2 4 4 16 5 25 6 36 7 49 
        System.out.println();
        System.out.println("count is " + count);//prints count is 6
        
        DoubleStream ds = DoubleStream.of(1.0,4.0,9.0,16.0,25.0,36.0,49.0,64.0,81.0,100.0);
        int sum = ds.mapToInt(d-> (int)Math.round(Math.sqrt(d))).peek(i->System.out.print(i + " ")).sum();
        //prints 1 2 3 4 5 6 7 8 9 10 
        System.out.println();
        System.out.println(sum);//prints 55
        
        System.out.println("1" + "1");//prints 11
        System.out.println(Integer.valueOf("1") + Integer.valueOf("1"));//prints 2
    }
    
 
}
    
