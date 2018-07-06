//Create worker threads using Runnable, Callable and use an ExecutorService to concurrently execute tasks
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

/**
 *
 * @author jwitt98
 */
public class RunnableCallableExcecutorService {
    
    public static long count;
    public static AtomicLong aCount = new AtomicLong(0);
    
    public static void main(String[] args){
        
        Predicate<? super String> predicate = s -> s.startsWith("g");
        
        String str = "gString";
        
        System.out.println(predicate.test(str));
        
        Runnable runnable = ()-> count++;
        
        for(int i = 0; i < 10000; i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
        
        //prints Count = XXXX
        //XXXX varies due to race condition
        //XXXX = 9998, 9994, 10000, 9989 on 4 runs
        System.out.println("Count = " + count);
        count = 0;//reset the count
        System.out.println("Count after reset is " + count + "\n");
        
        
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Callable<String>> callableList = new ArrayList<>();
        for(int i = 0; i < 100; i++ ){
            callableList.add(new Multiplier());
        }
        try{
            List<Future<String>> futList = exec.invokeAll(callableList);
            for(Future<String> fut:futList){
            
                System.out.println(fut.get());
            }
        }catch(InterruptedException | ExecutionException ie){
            System.out.println("An interruption or excecution exception occured: " + ie.getMessage());
        }     
        
        exec.shutdown();
        
    }
    
}

class Multiplier implements Callable<String>{
    @Override
    public String call(){
        String result;
        Random r = new Random();
        int i = r.nextInt(3) + 1;
        switch (i) {
            case 1:
                result = "Result 1 - " + RunnableCallableExcecutorService.aCount.incrementAndGet();
                break;
            case 2:
                result = "Result 2 - " + RunnableCallableExcecutorService.aCount.incrementAndGet();
                break;
            default:
                result = "Result 3 - " + RunnableCallableExcecutorService.aCount.incrementAndGet();
        }
        
        return result;
    }
 
}
