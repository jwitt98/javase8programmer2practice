//Identify potential threading problems among deadlock, starvation, livelock, and race conditions
//Race condition was already demonstrated in RunnableCallableExcecutorService class
package javase8programmer2practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jwitt98
 */
public class ThreadingProblemMaker {
    
    private final String name;
    
    public ThreadingProblemMaker(){
        this("NA");
    }
    public ThreadingProblemMaker(String name){
        this.name = name;
    }
    
    public synchronized void waiter(ThreadingProblemMaker tpm){
        System.out.println(tpm.name + " will make " + this.name + " wait now");
        tpm.wait(this);
    }
    
    public synchronized void wait(ThreadingProblemMaker tpm){
        System.out.println(tpm.name + " is waiting...");
    }
    
    public synchronized void doWork(String str){
        try{
            System.out.print(str + " ");
            Thread.sleep(1000);
        }catch(InterruptedException ie){
            System.err.println(ie.getMessage());
        };
    }
    
    public static void main(String[] args){
        ThreadingProblemMaker A = new ThreadingProblemMaker("A");
        ThreadingProblemMaker B = new ThreadingProblemMaker("B");
        
       //new Thread(()-> A.waiter(B)).start();      
       //new Thread(()-> B.waiter(A)).start();
       //Un-commenting the 2 lines above will cause a deadlock condition most of the time with output:
       //B will make A wait now
       //A will make B wait now
       
        int numThreads = 10;
        ExecutorService exec = Executors.newCachedThreadPool(new MyThreadFactory());
        //Note: Linux ignores Thread priorities
        //Executing the for loop below on a Windows machine will cause
        //Takers to dominate the upper part of the output while
        //Starvers will be primarily towards the end of the output
        //Example output on a Windows machine: 
        //Taker Taker Taker Starver Taker Taker Starver Starver Starver Starver 
        //Example output on a Linux machine:
        //Starver Taker Starver Taker Taker Starver Starver Taker Starver Taker 
        for(int i = 0; i < numThreads; i++){
           exec.submit(() -> {});//submit a runnable that does nothing
        }  
        exec.shutdown();
        try{
            exec.awaitTermination(5, TimeUnit.SECONDS);
        }catch(InterruptedException ie){
            System.err.println(ie.getMessage());
        }
        System.out.println();
       
    }
    
}

class MyThreadFactory implements ThreadFactory{
    
    private static boolean flag = true; 
    
    @Override
    public Thread newThread(Runnable r){
        if(flag){
            flag = false;
            r = () -> new ThreadingProblemMaker().doWork("Starver");
            Thread t = new Thread(r);
            t.setPriority(Thread.MIN_PRIORITY);
            return t;
        }else{
            flag = true;
            r = () -> new ThreadingProblemMaker().doWork("Taker");
            Thread t = new Thread(r);
            t.setPriority(Thread.MAX_PRIORITY);
            return t;
        }
        
    }
    
}