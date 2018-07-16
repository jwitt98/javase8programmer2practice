//Use synchronized keyword and java.util.concurrent.atomic package to control the 
//order of thread execution
//Synchonized methods were demonstrated in ThreadingProblemMaker class
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * @author jwitt98
 */
public class AtomicSynchronizer {
    
    private static int nonAtomicInt;
    private static AtomicInteger atomicInt = new AtomicInteger();
    
    private static int[] nonAtomicIntArray = new int[100];
    private static AtomicIntegerArray atomicIntArray = new AtomicIntegerArray(100);
    
    private static Set<Integer> ts = new TreeSet<>();
    private static List<Integer> intList = new ArrayList<>();
    private static ExecutorService exec;
    
    //method using non-atomic fields
    public static void nonAtomicIncrementAndInsert(int index){
        nonAtomicInt++;
        nonAtomicIntArray[index] = nonAtomicInt;
    }
    //method using atomic fields
    public static void atomicIncrementAndInsert(int index){
        atomicInt.incrementAndGet();
        atomicIntArray.set(index, atomicInt.get());
    }
    //method using sychronized block
    public static void synchronizedIncrementAndInsert(int index){
        synchronized(AtomicSynchronizer.class){
            nonAtomicInt++;
            nonAtomicIntArray[index] = nonAtomicInt;
        }
        
    }
    
    private static void excecutorShudown(){
        exec.shutdown();
        try{
            exec.awaitTermination(5, TimeUnit.SECONDS);
        }catch(InterruptedException ie){
            System.err.println(ie.getMessage());
        }
    }
    
    public static void main(String[] args){
        
        exec = Executors.newCachedThreadPool();
        for(int i = 0; i < nonAtomicIntArray.length; i++){
            final int j = i;
            exec.submit(() -> nonAtomicIncrementAndInsert(j));
        }
        excecutorShudown();
        System.out.println("nonAtominIntArray printed with no sorting:");
        for(int i = 0; i < nonAtomicIntArray.length; i++){
            System.out.print(nonAtomicIntArray[i] + ", ");
            //Numbers are out of sequence, some numbers are printed twice, some
            //numbers do not get printed at all.  The array does not always contain
            //100 ints.  Add to TreeSet for sorting;
            if(!ts.add(nonAtomicIntArray[i])){
                intList.add(nonAtomicIntArray[i]);
            }
        }
        intList.forEach(i -> System.out.print("\n" + i + " included more than once"));        
        System.out.println("\nnonAtomicIntArray sorted with TreeSet:");
        for(int i: ts){
            System.out.print(i + ", ");
        }
        
        for(int i = 1; i <= nonAtomicIntArray.length; i++){
            if(!ts.contains(i)){
                System.out.print("\n" + i + " not included in array");
            }
        }
        System.out.println("\n");
        intList.clear();
        ts.clear();
        
        
        exec = Executors.newCachedThreadPool();
        for(int i = 0; i < atomicIntArray.length(); i++){
            final int j = i;
            exec.submit(() -> atomicIncrementAndInsert(j));
        }
        excecutorShudown();
        System.out.println("\natomicIntArray printed with no sorting:");
        for(int i = 0; i < atomicIntArray.length(); i++){
            System.out.print(atomicIntArray.get(i) + ", ");
            //Numbers are still out of sequence,  some numbers are printed twice
            //and others are not printed at all but the array always contains 100 
            //numbers.  This demostrates that race conditions are still possible
            //when using atomic variables.  Add to a TreeSet for sorting and reprint.
            if(!ts.add(atomicIntArray.get(i))){
                intList.add(atomicIntArray.get(i));
            }
        }
        intList.forEach(i -> System.out.print("\n" + i + " included more than once")); 
        System.out.println("\natomicIntArray sorted with TreeSet: ");
        for(int i: ts){
            System.out.print(i + ", ");
        }
        for(int i = 1; i <= atomicIntArray.length(); i++){
            if(!ts.contains(i)){
                System.out.print("\n" + i + " not included in array");
            }
        }
        System.out.println("\n");
        intList.clear();
        ts.clear();
        
        
        nonAtomicInt = 0;
        exec = Executors.newCachedThreadPool();
        for(int i = 0; i < nonAtomicIntArray.length; i++){
            final int j = i;
            exec.submit(() -> synchronizedIncrementAndInsert(j));
        }
        excecutorShudown();
        System.out.println("\nResult using synchronized block:");
        for(int i = 0; i < nonAtomicIntArray.length; i++){
            System.out.print(nonAtomicIntArray[i] + ", ");
            //Numbers are still out of sequence, but all numbers are printed, and
            //no numbers are skipped or missed.  The array will always contain 100
            //numbers.  Add to a TreeSet to sort.
            if(!ts.add(nonAtomicIntArray[i])){
                intList.add(nonAtomicIntArray[i]);
            }
        }
        intList.forEach(i -> System.out.print("\n" + i + " included more than once"));
        System.out.println("\nResult using synchronized block after sorting:");
        for(int i: ts){
            System.out.print(i + ", ");
        }
        for(int i = 1; i <= nonAtomicIntArray.length; i++){
            if(!ts.contains(i)){
                System.out.print("\n" + i + " not included in array");
            }
        }
        System.out.println("\n");
        System.out.println();
    }
    
}
