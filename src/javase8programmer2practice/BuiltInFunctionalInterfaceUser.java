//Use  the built-in interfaces included in the java.util.function package such as Predicate, Consumer, Function, and Supplier
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class BuiltInFunctionalInterfaceUser {
    
    public static void main(String[] args){
        Integer[] iArray = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> iList = new ArrayList<>(Arrays.asList(iArray));
        PredicateTester<Integer> pti  = new PredicateTester(iList);
        iList = pti.removeIf(i -> i % 2 != 0);
        iList.forEach(i -> System.out.print(i + " "));//prints 2 4 6 8 10 
        System.out.println();
        
        String[] sArray = {"1","2","3","4","5","6","7","8","9","10"};
        List<String> sList = new ArrayList(Arrays.asList(sArray));
        PredicateTester<String> pts = new PredicateTester(sList);
        sList = pts.removeIf(str -> str.charAt(0) == '5' || str.charAt(0) == '9');
        sList.forEach(str -> System.out.print(str + " "));//prints 1 2 3 4 6 7 8 10 
        System.out.println();
        
        Character[] cArray = {'a','b','c','d','e','f'};
        List<Character> cList = Arrays.asList(cArray);
        ConsumerTester<Character> ctc = new ConsumerTester<>(cList);
        ctc.consume(ch -> System.out.print(ch + " - "));//prints a - b - c - d - e - f - 
        System.out.println();
        
        Double[] dArray = {1.0,2.1,3.2,4.3,5.4,6.5,7.6,8.7,9.8};
        List<Double> dList = new ArrayList(Arrays.asList(dArray));
        FunctionTester<Double, String> ftd = new FunctionTester(dList);
        List<String> strList = ftd.function(d -> Byte.toString(d.byteValue()));
        strList.forEach(str -> System.out.print(str + " "));//prints 1 2 3 4 5 6 7 8 9 
        System.out.println();
        
        Function<String, Integer> intToStr = Integer::parseInt;
        Function<Integer, Integer> intToAbs = Math::abs;
        Function<String, Integer> andThen = intToStr.andThen(intToAbs);
        Function<String, Integer> compose = intToAbs.compose(intToStr);
        Stream<String> strStream = Arrays.stream("2,-8,-100,3,5,8,-99".split(","));
        strStream.map(andThen).forEach(str -> System.out.print(str + " "));
        System.out.println();//prints 2 8 100 3 5 8 99
        Stream<String> strStream2 = Arrays.stream("2,-8,-100,3,5,8,-99".split(","));
        strStream2.map(compose).forEach(str -> System.out.print(str + " "));
        System.out.println();//Also prints 2 8 100 3 5 8 99
        
        Long[] lArray = {100L,200L,300L,400L,500L,600L,700L,800L,900L};
        List<Long> lList = new ArrayList<>(Arrays.asList(lArray));
        SupplierTester<Long> splIndex = new SupplierTester<>(lList);
        int index = splIndex.supply(()-> 400L);
        System.out.println(index);//prints 3
        
    }
    
}

class PredicateTester<T>{
    
    private final List<T> list;
    
    PredicateTester(List<T> list){
        this.list = list;
    }
    
    public List<T> removeIf(Predicate<T> pred){
        Iterator<T> itr = list.iterator();
        while(itr.hasNext()){
            if(pred.test(itr.next())){
                itr.remove();
            }
        }
        return list;
    }
}

class ConsumerTester<T>{
    
    private final List<T> list;
    
    ConsumerTester(List<T> list){
        this.list = list;
    }
    
    public List<T> consume(Consumer<T> cons){
        
        Iterator<T> itr = list.iterator();
        while(itr.hasNext()){
            cons.accept(itr.next());
        }
        return list;
    } 
}

class FunctionTester<T,R>{
    
    private final List<T> list;
    
    FunctionTester(List<T> list){
        this.list = list;
    }
    
    public List<R> function(Function<T,R> func){
        List<R> rList = new ArrayList<>();
        Iterator<T> itr = list.iterator();
        while(itr.hasNext()){
            R type = func.apply(itr.next());
            rList.add(type);
        }
        return rList;
    }
    
}

class SupplierTester<T>{
    
    private final List<T> list;
    
    SupplierTester(List<T> list){
        this.list = list;
    }
    
    public int supply(Supplier<T> sup){
        
       T type = sup.get();
        
        return list.indexOf(type);
    }
}