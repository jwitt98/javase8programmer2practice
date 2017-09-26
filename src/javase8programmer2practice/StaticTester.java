//Develop code that uses static keyword on initialize blocks, variables, methods, and classes
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public class StaticTester {
    
    private static int numInstances;
    
    static{
        numInstances = 100;
    }
    
    public StaticTester(){
        numInstances++;
    }
    
    public static int getNumInstances(){
        return numInstances;
    }
    
    public static void main(String[] args){
        System.out.println("numInstances1 = " + new StaticTester().getNumInstances());
        System.out.println("numInstances2 = " + new StaticTester().getNumInstances());
        System.out.println("numInstances3 = " + StaticTester.getNumInstances());
    }
    
}
