//Use catch, multi-catch, and finally clauses
package javase8programmer2practice;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author jwitt98
 */
public class CatchMultiCatchFinallyClauser {
    
    public static void main(String[]args){
        
        try{
            Thrower2 thrower = new Thrower2();
        }catch(IOException ioe){
            System.out.println("IOException thrown");//randomly prints
        }catch(ClassNotFoundException cnfe){
            System.out.println("ClassNotFoundException thrown");//randomly prints
        }catch(Exception e){
            System.out.println("Exception will never get thrown");//never prints
        }finally{
            System.out.println("The exceptions are fianlly done");//always prints
        }
        
    }
    
}

class Thrower2{
    
    Thrower2() throws Exception{
        boolean r = new Random().nextBoolean();
        
        if(r){
            throw new IOException();
        }else{
            throw new ClassNotFoundException();
        }
        
    }
    
}
