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
    private static boolean lock = true;
    
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
            Thread.sleep(1000);//simulate some work being done
        }catch(InterruptedException ie){
            System.err.println(ie.getMessage());
        }
    }
    public synchronized boolean getLock(){
        try{
            Thread.sleep(1000);//wait while the other thread changes the lock state
        }catch(InterruptedException ie){
            System.err.println(ie.getMessage());
        }
        String tName = Thread.currentThread().getName();
        System.out.println("Thread " + tName + " -Getting lock = " + lock);
        return lock;
    }
    public synchronized void setLock(boolean value){     
        String tName = Thread.currentThread().getName();
        System.out.println("Thread " + tName + " -Setting lock to " + value);
        lock = value;
    }
    
    public static void main(String[] args){
        
//Deadlock demonstation        
        ThreadingProblemMaker A = new ThreadingProblemMaker("A");
        ThreadingProblemMaker B = new ThreadingProblemMaker("B");
        
       //new Thread(()-> A.waiter(B)).start();      
       //new Thread(()-> B.waiter(A)).start();
       //Un-commenting the 2 lines above will cause a deadlock condition about 
       //80% of the time with output:
       //B will make A wait now
       //A will make B wait now

//Starvation demonstration       
        int numThreads = 10;
        ExecutorService exec = Executors.newCachedThreadPool(new StarvationThreadFactory());
        //Note: Linux ignores Thread priorities by default
        //Executing the for loop below on a Windows machine will cause
        //Takers to dominate the beginning of the output while
        //Starvers will be primarily towards the end of the output
        //In more extreme scenarios and larger numbers of Threads, some starver
        //threads may be starved out indefintley
        //Example output on a Windows machine: 
        //Taker Taker Taker Starver Taker Taker Starver Starver Starver Starver 
        //Example output on a Linux machine with default Thread priorities:
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
              
//LiveLock demonstration
        exec = Executors.newCachedThreadPool();
        ThreadingProblemMaker tpm = new ThreadingProblemMaker();
        Runnable r1 = () -> {
            while(tpm.getLock()){
                new ThreadingProblemMaker().setLock(false);
            }
        };
        Runnable r2 = () -> {
            while(!tpm.getLock()){
                new ThreadingProblemMaker().setLock(true);
            }
        };
        
        exec.submit(r1);
        exec.submit(r2);  
        //Un-commenting the 2 lines above will cause a LiveLock Scenario
        //One thread sets the lock to true and then is forced to wait while the
        //other thread sets the lock to false.  Each thread undoes the other 
        //threads's action.
        //Example excerpt of output follows:
        //Thread pool-1-thread-1 -Getting lock = true
        //Thread pool-1-thread-1 -Setting lock to false
        //Thread pool-1-thread-2 -Getting lock = false
        //Thread pool-1-thread-2 -Setting lock to true
        //Thread pool-1-thread-1 -Getting lock = true
        //Thread pool-1-thread-1 -Setting lock to false
        //Thread pool-1-thread-2 -Getting lock = false
        //Thread pool-1-thread-2 -Setting lock to true
        //...pattern repeats indefinitely
    }
    
}

class StarvationThreadFactory implements ThreadFactory{
    
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

class LiveLockThreadFactory implements ThreadFactory{
    
    public Thread newThread(Runnable r){
        
        r = () -> {
            while(new ThreadingProblemMaker().getLock()){
                new ThreadingProblemMaker().setLock(false);
            }
        
        };
        Thread t = new Thread(r);
        return t;
    }
}