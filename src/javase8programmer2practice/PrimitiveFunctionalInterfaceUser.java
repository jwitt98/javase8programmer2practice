//Develop code that uses primitive versions of functional interfaces
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.LongFunction;
import java.util.function.ObjIntConsumer;
import java.util.stream.IntStream;
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
        IntStream.range(1, 20).filter(i -> i > 10).forEach(i -> System.out.print(i + " "));//prints 11 12 13 14 15 16 17 18 19 
        System.out.println();    
        
        List<Long> lList = new ArrayList<>(Arrays.asList(new Long[]{1L,4L,9L,16L,25L,36L,49L,64L,81L}));
        LongFunctionTester<Double> lft = new LongFunctionTester<>(lList);
        List<Double> dList = lft.LongListConverter(l -> Math.sqrt(l));
        dList.forEach(d -> System.out.print(d + " "));//prints 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 
        System.out.println();
        
        double[] doubles = {1.2,2.3,3.4,4.5,5.6,6.7,7.8,8.9,9.1};
        DoubleToIntFunctionTester dtift = new DoubleToIntFunctionTester(doubles);
        int[] ints = dtift.DoubleArrayToIntArray(d-> (int)Math.round(d));
        Arrays.stream(ints).forEach(i -> System.out.print(i + " "));//prints 1 2 3 5 6 7 8 9 9 
        System.out.println();
        
        IntConsumerTester ict = new IntConsumerTester(new int[]{2,4,6,8,10,12,14,16,18,20});
        ict.consumeInt(i -> System.out.print(i * i + " "));//prints 4 16 36 64 100 144 196 256 324 400
        System.out.println();
        
        ObjIntConsumerTester oict = new ObjIntConsumerTester(new int[]{2,4,6,8,10});
        oict.consumeObjInt((i,j)->System.out.print(i + " " + j + " | "));
        //prints Consumed 2 | Consumed 4 | Consumed 6 | Consumed 8 | Consumed 10 |
        System.out.println();
        
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

class LongFunctionTester<R>{
    
    private final List<Long> longs;
    private final ArrayList<R> list = new ArrayList<>();
    
    LongFunctionTester(List<Long> longs){
        this.longs = longs;
    }
    
    ArrayList<R> LongListConverter(LongFunction<R> lf){
        
        longs.stream().map((item) -> lf.apply(item)).forEach(obj -> list.add(obj));
            
        return list;
    }
    
}

class DoubleToIntFunctionTester{
    private final double[] doubles;
    
    DoubleToIntFunctionTester(double[] doubles){
        this.doubles = doubles;
    }
    
    int[] DoubleArrayToIntArray(DoubleToIntFunction dtif){
        
        int[] ints = new int[doubles.length];
        for(int i = 0; i < doubles.length; i++){
            ints[i] = dtif.applyAsInt(doubles[i]);
        }
        
        return ints;
    }
    
}

class IntConsumerTester{
    private final int[] ints;
    
    IntConsumerTester(int[] ints){
        this.ints = ints;
    }
    void consumeInt(IntConsumer ic){
        for(int i : ints){
            ic.accept(i);
        }
    }
}

class ObjIntConsumerTester{
    private final int[] ints;
    
    ObjIntConsumerTester(int[] ints){
        this.ints = ints;
    }
    
    void consumeObjInt(ObjIntConsumer oic){
        
        for(int i : ints){
            oic.accept("Consumed", i);
        }
    }
}

