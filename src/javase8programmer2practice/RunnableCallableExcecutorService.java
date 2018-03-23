//Create worker threads using Runnable, Callable and use an ExecutorService to concurrently execute tasks
package javase8programmer2practice;

import java.util.concurrent.Callable;

/**
 *
 * @author jwitt98
 */
public class RunnableCallableExcecutorService {
    
    public static long count;
    
    public static void main(String[] args){
        
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
        System.out.println("Count = " + count);
        
    }
    
}
