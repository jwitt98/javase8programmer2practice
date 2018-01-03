//Read and write data from the console
package javase8programmer2practice;

import java.io.Console;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author jwitt98
 */
public class ConsoleReaderWriter {
    
    public static void main(String[] args){
        
        try{
            //typed in 3
            System.out.print("Type an integer: ");
            int readInt = System.in.read();
            //prints You typed ASCI character 51
            //why?  because the return type is int, but the value returned is a byte
            System.out.println("You typed ASCI character " + readInt);
            //prints You typed integer 3
            System.out.println("You typed integer " + Character.valueOf((char)readInt));
        }catch(IOException ioe){
            System.err.println("IOExcption occured: " + ioe.getMessage());
        }
        
        try{
            System.setOut(new PrintStream("out.txt"));
        }catch(FileNotFoundException fnfe){
            System.err.println("An error occured while accessing the file " + fnfe.getMessage());
        }
        //prints the two lises below to a file called out.txt
        System.out.println("This is a line in the out file.");
        System.out.println("This is another line");
        
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("Now we are back to the console");
        
        //Console console = System.console();//retruns null when runnig from IDE
        //System.out.println("Type a password");
        //char[] password = console.readPassword();//thrown NullPointerException when run from IDE
        //System.out.print("The password you typed is ");
        //prints the password typed above when run from command shell
        //for(char ch : password){
            //System.out.print(ch);
        //}
        //System.out.println();
        
        
        
    }
    
}
