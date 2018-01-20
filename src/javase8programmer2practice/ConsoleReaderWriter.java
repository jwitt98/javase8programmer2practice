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
        
        Console console = System.console();//retruns null when runnig from IDE
        System.out.println("Type a password");
        char[] password = console.readPassword();//thrown NullPointerException when run from IDE
        System.out.print("The password you typed is ");
        //prints the password typed above when run from command shell
        for(char ch : password){
            System.out.print(ch);
        }
        System.out.println();
        //When run from the command line, prints...
        //Boolean true, Character B, Decimal 12, Float scientific 3.000000e+04, 
        //Float decimal 4.600000, Float either 50000.0, Hashcode 6bc7c054, Integer octal 11, 
        //Intger hex f, String String, Date/Time 12/31/69, line seperator
        //...all on one line followed by a new line
        console.printf("Boolean %b, Character %c, Decimal %d, Float scientific %e, "
                + "Float decimal %f, Float either %g, Hashcode %h, "
                + "Integer octal %o, Intger hex %x, String %s, Date/Time %tD, line seperator %n"
                , true, 'B', 12, 3e4, 4.6, 5e4, console, 0x9, 15, "String", 123456L);
        
    }
    
}
