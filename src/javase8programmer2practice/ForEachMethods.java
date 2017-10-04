//Iterate using forEach methods of Streams and List
package javase8programmer2practice;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class ForEachMethods {
    
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4);
        list.forEach(str -> System.out.print(str + " "));//prints 1 2 3 4 
        System.out.println();
        
        Stream<Integer> intStream = list.stream();
        intStream.forEach(str -> System.out.print(str + " "));//prints 1 2 3 4 
        System.out.println();
        
        list.forEach(ForEachMethods::printSquare);//prints 1 4 9 16
        System.out.println();
    }
    
    static void printSquare(Integer i){
        System.out.print(i * i + " ");
    }
    
}
