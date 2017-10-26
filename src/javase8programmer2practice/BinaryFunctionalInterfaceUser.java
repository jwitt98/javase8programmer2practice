//Develop code that uses binary versions of functional interfaces
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 *
 * @author jwitt98
 */
public class BinaryFunctionalInterfaceUser {
    
    public static void main(String[] args){
        
        BiPredicateTester<Integer,Integer> bpt = new BiPredicateTester<>();
        System.out.println(bpt.testBiPred((i,j)-> i.equals(j), new Integer(3), new Integer(4)));//prints false
        System.out.println(bpt.testBiPred((i,j)-> i.equals(j), new Integer(3), new Integer(3)));//prints true
        
        BiConsumerTester<Double, List<String>> bct = new BiConsumerTester<>();
        List<String> sList = new ArrayList<>(Arrays.asList(new String[]{"Double", "Double", "Toil", "and", "Trouble"}));
        Double d = 12.0;
        bct.testBiCons((t,u)-> System.out.println(t + " " + u), d, sList);//prints 12.0 [Double, Double, Toil, and, Trouble]
        
        BiFunctionTester<Character,Double,String> bft = new BiFunctionTester<>();
        String str = bft.testBiFunc((ch,dbl)-> ch + Double.toString(ch * dbl), 'C', 11.2);//'C' = 67
        System.out.println(str);//prints C750.4
    }
    
}

class BiPredicateTester<T,U>{
    
    public boolean testBiPred(BiPredicate bp, T t, U u){
        return bp.test((T)t,(U)u);
    }
}

class BiConsumerTester<T,U>{
    
    public void testBiCons(BiConsumer bc, T t, U u){
        bc.accept(t, u);
    }
    
}

class BiFunctionTester<T,U,R>{
    
    public R testBiFunc(BiFunction<T,U,R> bf, T t, U u){
        return bf.apply(t, u);
    }
}