//Develop code that uses the final keyword
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public final class Finalizer {
    private final int finalInt;
    
    public Finalizer(int finalInt){
        this.finalInt = finalInt;
    }
    
    public static void main(String[] args){
        
        Finalizer finalizer = new Finalizer(8);
        System.out.println("finalInt = " + finalizer.finalInt);
        //cannot assign a value to final variable finalInt
        //finalizer.finalInt = 7;
    }
}

//cannot inherit from final Finalizer
//class FinalizerExtender extends Finalizer{}

class Base{
    public final void print(){
        System.out.println("In Base print...");
    }
}
class Sub extends Base{
    //print() in Sub cannot override print() in Base. 
    //overridden method is final

    //public void print(){
        //System.out.pritnln("In Sub print...")
    //}
}
