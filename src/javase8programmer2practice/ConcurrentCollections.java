//Use java.util.concurrent collections and classes including CyclicBarrier and 
//CopyOnWriteArrayList
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author jwitt98
 */
public class ConcurrentCollections {
    
    private static final CyclicBarrier barrier = new CyclicBarrier(4, () -> 
            System.out.println("All 4 players are ready to start! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"));
    
    public static void main(String[] args){
        
        ExecutorService exec = Executors.newCachedThreadPool();
        Random random = new Random();
        
        for(int i = 1; i <= 4; i++ ){
            final int player = i;
            Runnable r = () -> {
                int rand = random.nextInt(10) + 1;
                System.out.println("Player " + player + " will be ready in " + rand + " seconds");
                try{
                    Thread.sleep(rand * 1000);
                    System.out.println("The number of ready players is : " + barrier.getNumberWaiting());
                    System.out.println("Player " + player + " is ready now");
                    barrier.await();
                }catch(BrokenBarrierException | InterruptedException ex){
                    System.err.println("A broken barrior or interrupted exception occured");
                }
                System.out.println("Player " + player + " has started!");
            };
            
            exec.submit(r);         
        }
        //example of output:  Notice that each thread waits until all 4 have reached the barrier
        //Player 1 will be ready in 4 seconds
        //Player 4 will be ready in 6 seconds
        //Player 2 will be ready in 10 seconds
        //Player 3 will be ready in 2 seconds
        //The number of ready players is : 0
        //Player 3 is ready now
        //The number of ready players is : 1
        //Player 1 is ready now
        //The number of ready players is : 2
        //Player 4 is ready now
        //The number of ready players is : 3
        //Player 2 is ready now
        //All 4 players are ready to start! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //Player 2 has started!
        //Player 3 has started!
        //Player 1 has started!
        //Player 4 has started!
        
        exec.shutdown();
        try{
            exec.awaitTermination(30, TimeUnit.SECONDS);
        }catch(InterruptedException ie){}
        
        List<String> al = new ArrayList<>();
        al.addAll(Arrays.asList("one","two","three"));
        CopyOnWriteArrayList<String> cowal = new CopyOnWriteArrayList<>(new String[]{"one","two","three"}); 
        
        for(String str: al){
            System.out.println("al for str = " + str);
            //al.remove(0);//throws ConcurrentModificationException
        }
        
        Iterator<String> iter = al.iterator();
        while(iter.hasNext()){     
            System.out.println("al iter str = " + iter.next());
            iter.remove();//this is OK when removed using an iterator
        }
        
        for(String str: cowal){
            System.out.println("cowal for str = " + str);
            cowal.add("four");//for loop prints lines with one, two and three, but not four
            cowal.remove("two");
        }
        
        for(String str: cowal){
            System.out.println("cowal for2 str = " + str);
        }
        //prints
        //cowal for2 str = one
        //cowal for2 str = three
        //cowal for2 str = four
        //cowal for2 str = four
        //cowal for2 str = four
        
        Iterator<String> iter2 = cowal.iterator();
        while(iter2.hasNext()){
            System.out.println("cowal iter2 str = " + iter2.next());
            //iter2.remove();//throws UnsupportedOperationException
        }
    }
    
}

