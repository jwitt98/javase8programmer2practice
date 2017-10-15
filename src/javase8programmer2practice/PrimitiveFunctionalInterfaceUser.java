//Develop code that uses primitive versions of functional interfaces
package javase8programmer2practice;

import java.util.function.IntPredicate;
/**
 *
 * @author jwitt98
 */
public class PrimitiveFunctionalInterfaceUser {
    
    public static void main(String[] args){
        
        IntPredicateTester ipt = new IntPredicateTester(new int[]{2,4,6,8,10});
        System.out.println("Contains 6 = " + ipt.testInt(i -> i == 6));//prints Contains 6 = true
        System.out.println("Contains 5 = " + ipt.testInt(i -> i == 5));//prints Contains 5 = false
        System.out.println("Contains an even number = " + ipt.testInt(i -> i % 2 == 0));//prints Contains an even number = true
        System.out.println("Contains an odd number = " + ipt.testInt(i -> i % 2 != 0));//prints Contains an odd number = false
        
    }
    
}

class IntPredicateTester{
    
    private final int[] ints;
    
    IntPredicateTester(int[] ints){
        this.ints =ints;
    }
    
    boolean testInt(IntPredicate ip){
        for(int i: ints){
            if (ip.test(i)) return true;
        }
        return false;
    }
    
}