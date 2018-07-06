//Create worker threads using Runnable, Callable and use an ExecutorService to concurrently execute tasks
package javase8programmer2practice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

/**
 *
 * @author jwitt98
 */
public class RunnableCallableExcecutorService {
    
    public static long count;
    
    public static void main(String[] args){
        
        Predicate<? super String> predicate = s -> s.startsWith("g");
        
        Object obj = new Object();
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
        
        Callable<Long> callable =  ()-> count++;
        for(int i = 0; i < 10000; i++){
            try{
                callable.call();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Count = " + count + "\n");
        
        ExecutorService exec = Executors.newFixedThreadPool(2);
        Future<Long> fut = exec.submit(new Multiplier());
        Future<Long> fut2 = exec.submit(new Multiplier());
        while(true){
            try{
                if(fut.isDone() && fut2.isDone()){
            
                    System.out.println("Future is: " + fut.get());
                    System.out.println("Future2 is: " + fut2.get());
                    break;
                }
            }catch(InterruptedException | ExecutionException e){
                System.out.println(e.getMessage());
            }
        }
        exec.shutdown();
        
    }
    
}

class Multiplier implements Callable<Long>{
    @Override
    public Long call(){
        Random r = new Random();
        long l = r.nextInt(10) + 1;
        for(int i = 0; i < 10; i++){
            l += 1;
            System.out.println("l = " + l);
        }
        
        return l;
    }
 
}
