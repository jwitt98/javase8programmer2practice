//Create custom exceptions and Auto-closeable resources
package javase8programmer2practice;

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
        
    }
    
    static void customExceptionThrower() throws CustomException{
        
        throw new CustomException();
    
    }
    
}

class CustomException extends Exception{
    
    public CustomException(){
        this("CustomException has been thrown");
    }
    public CustomException(String message){
        super(message);
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