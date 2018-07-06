//Identify potential threading problems among deadlock, starvation, livelock, and race conditions
package javase8programmer2practice;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jwitt98
 */
public class ThreadingProblemMaker {
    
    private String name;
    
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
    
    public static void main(String[] args){
        ThreadingProblemMaker A = new ThreadingProblemMaker("A");
        ThreadingProblemMaker B = new ThreadingProblemMaker("B");
        
       new Thread(()-> A.waiter(B)).start();
       
       new Thread(()-> B.waiter(A)).start();
    }
    
}
