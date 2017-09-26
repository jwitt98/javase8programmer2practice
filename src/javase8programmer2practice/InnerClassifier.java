//Create inner classes including static inner class, local class, nested class, 
//and anonymous inner class
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public class InnerClassifier {
    
    public static void main(String[] Args){
        Innerds.InnerStatic is = new Innerds.InnerStatic();
        is.printme();
        //need a reference to innerds to instantiate InnerNested
        Innerds innerds = new Innerds();
        Innerds.InnerInterface nestedInnerds = innerds.new InnerNested("We are inner nested now...");
        System.out.println(nestedInnerds.getMyVar());
        innerds.local();
        
        OuterInterface.InnerStatic oiis = new OuterInterface.InnerStatic();
        System.out.println(oiis.string);
        
        System.out.println(innerds.innested.getMyVar());
    }   
    
}

class Innerds{
    
    static class InnerStatic{
        //static OK here
        static int myInt = 8;
        public void printme(){
            System.out.println("In static inner class...");
        }
    }
    
    //static keyword not explicity required here
    static interface InnerInterface{
        String getMyVar();
    }
    
    public class InnerNested implements InnerInterface{
        private String myVar;
        //Illegal static declaration in inner class Innerds.InnerNested
        //modifier 'static' is only allowed in constant variable declarations
        //public static int count;
        public InnerNested(String myVar){
            this.myVar = myVar;
        }
        
        public String getMyVar(){
            return this.myVar;
        }
    }
    
    InnerNested innested = new InnerNested("This is innested");
    
    public void local(){
        class InLocal{
            //Illegal static declaration in inner class inLocal
            //modifier 'static' is only allowed in constant variable declarations
            //static int myint = 8;
            void printme(){
                System.out.println("In printme in inLocal in local");
            }
        }
        new InLocal().printme();
        
        
    }
}

interface OuterInterface{
    static class InnerStatic{
        public String string = "OuterInterface InnerStatic string";
    }
}
