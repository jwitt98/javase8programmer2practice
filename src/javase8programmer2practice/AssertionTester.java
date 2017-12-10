//Test invariants by using assertions
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public class AssertionTester {
    
    public static void main(String[] args){
        
        boolean myBool = false;
        //using argument -ea:javase8programmer2practice.AssertionTester
        // thows: Exception in thread "main" java.lang.AssertionError: myBool should be true!
        assert myBool : "myBool should be true!"; 
    }
    
}
