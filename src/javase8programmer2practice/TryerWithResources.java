//Use Autoclose resources with a try-with-resources statement
package javase8programmer2practice;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jwitt98
 */
public class TryerWithResources {
    
    private static Scanner sc1;
    private static Scanner sc2;
    
    public static void main(String[] args){
        
        System.out.println("This program will muliply 2 integers... press enter to continue.");
        try{
            System.in.read();
        }catch(IOException ioe){
            System.out.println("An error occured.  Please try again");
        }
        /*
        try{               
            sc1 = new Scanner(System.in);
            while(true){
                try{
                    sc1.nextLine();
                    System.out.println("Enter the first integer: ");
                    int int1 = sc1.nextInt();
                    System.out.println("Enter the second integer: ");
                    int int2 = sc1.nextInt();
                    int int3 = int1 * int2;
                    System.out.println("The result of " + int1 + " x " + int2 + " is " + int3);
                    break;
                }catch(InputMismatchException ime){
                    System.out.println("Error:  The input did not match that of an integer");
                    System.out.println("Please try again");
                }
            }
        }finally{
            sc1.close();
        }
        */
        try(Scanner sc1 = new Scanner(System.in);){                        
            while(true){
                try{
                    sc1.nextLine();
                    System.out.println("Enter the first integer: ");
                    int int1 = sc1.nextInt();
                    System.out.println("Enter the second integer: ");
                    int int2 = sc1.nextInt();
                    int int3 = int1 * int2;
                    System.out.println("The result of " + int1 + " x " + int2 + " is " + int3);
                    break;
                }catch(InputMismatchException ime){
                    System.out.println("Error:  The input did not match that of an integer");
                    System.out.println("Please try again");
                }
            }
        }
        
    }
    
}
