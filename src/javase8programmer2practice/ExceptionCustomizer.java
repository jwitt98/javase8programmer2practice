//Create custom exceptions and Auto-closeable resources
package javase8programmer2practice;

import java.io.IOException;
import java.lang.Throwable;
import java.sql.SQLException;
/**
 *
 * @author jwitt98
 */
public class ExceptionCustomizer {
    
    public static void main(String[] args){
        
        try{
            customExceptionThrower();
        }catch(CustomException ce){
              System.out.println(ce.getMessage());//prints CustomException has been thrown
        }
        
        //prints
        //Resource is closing
        //CustomException thrown from AutoCloser
        try(AutoCloser ac = new AutoCloser()){
            ac.ceThrower();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        try{
            exceptionWrapper();
        }catch(CustomException ce){
            //prints CustomException cause is: java.sql.SQLException
            System.out.println("CustomException cause is: " + ce.getCause());
            ce.addSuppressed(new IOException("IOException thrown"));
            ce.addSuppressed(new Exception("Exception thrown"));
            Throwable[] throwables = ce.getSuppressed();
            for(Throwable t : throwables){
                /*prints
                Suppressed Throwable message is: IOException thrown
                Suppressed Throwable message is: Exception thrown
                */            
                System.out.println("Suppressed Throwable message is: " + t.getMessage());
            }
        }
        
    }
    
    static void customExceptionThrower() throws CustomException{
        
        throw new CustomException();
    
    }
    
    static void exceptionWrapper() throws CustomException{
        
        try{
            throw new SQLException("SQLExcpetion Thrown");
        }catch(SQLException sqle){
            throw new CustomException("Wrapped Exception thrown", sqle);
        }
        
    }
    
}

class CustomException extends Exception{
    
    public CustomException(){
        this("CustomException has been thrown", null);
    }
    public CustomException(String message){
        this(message, null);
    }
    public CustomException(Throwable throwable){
        this(null, throwable);
    }
    public CustomException(String message, Throwable throwable){
        super(message, throwable);
    }
    
}

class AutoCloser implements AutoCloseable{
    
    public void close(){
        System.out.println("Resource is closing");
    }
    
    public void ceThrower() throws CustomException{
        throw new CustomException("CustomException thrown from AutoCloser");
    }
    
}