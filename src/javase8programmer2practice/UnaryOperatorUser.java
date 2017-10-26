//Develop code that uses the UnaryOperator interface
package javase8programmer2practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 *
 * @author jwitt98
 */
public class UnaryOperatorUser {
    
    public static void main(String[] args){
        
        UnaryOperatorTester<String> uots = new UnaryOperatorTester<>("Test");
        String myString = uots.unaryOpTest(str -> str.concat("ing is good for peace of mind"));
        System.out.println(myString);//prints Testing is good for peace of mind
        
        UnaryOperatorTester<List> uotl = new UnaryOperatorTester<>(Arrays.asList(new Integer[]{1,2,3,4,5}));
        UnaryOperator<List> uol = UnaryOperator.identity();
        List myList = uotl.unaryOpTest(uol);
        System.out.println(myList);//prints [1, 2, 3, 4, 5]
        
    }
    
}

class UnaryOperatorTester<T>{
    private final T item;
    
    UnaryOperatorTester(T item){
        this.item = item;
    }
    
    public T unaryOpTest(UnaryOperator<T> uo){
        return uo.apply(item);
    }
    
}