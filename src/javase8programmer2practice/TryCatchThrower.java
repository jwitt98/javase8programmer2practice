//Use try-catch and throw statements
package javase8programmer2practice;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jwitt98
 */
public class TryCatchThrower {
    
    public static void main(String[] args){
        
        //Thrower thrower = new Thrower();
        //unreported exception Exception; must be caught or declared to be thrown
        
        try{
            Thrower thrower = new Thrower();
        }catch(Exception e){
            System.out.println("Message is: " + e.getMessage());//prints Message is: Exception thrown
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer now...");
        try{
            int typedInt = scanner.nextInt();
            System.out.println("You typed the integer " + typedInt);
        }catch(InputMismatchException ime){
            System.out.println("You typed an incorrect value!");
        }
        
    }
    
}

class Thrower{
    
    public Thrower() throws Exception{
        throw new Exception("Exception thrown");
    }
    
}