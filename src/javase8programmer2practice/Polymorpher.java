//Implement polymorphism

package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public class Polymorpher {
    
    protected int myInt;
    protected String myString;
    protected boolean myBool;
    
    public Polymorpher(int myInt, String myString, boolean myBool){
        this.myInt = myInt;
        this.myString = myString;
        this.myBool = myBool;
    }
 
    public void printDescription(){
        System.out.println("myInt = " + myInt);
        System.out.println("myString = " + myString);
        System.out.println("myBool = " + myBool);
    }
    
    public static void main(String[] args){
        Polymorpher polymorpher = new Polymorpher(8, "Wahoo", false);
        polymorpher.printDescription();
        System.out.println("----------------------------------");
        Polymorpher polymorpher2 = new Polymorpher2(16, "Boohoo", true, 32);
        polymorpher2.printDescription();
    }
}

class Polymorpher2 extends Polymorpher{
    private int myInt2;
    
    public Polymorpher2(int myInt, String myString, boolean myBool, int myInt2){
        super(myInt, myString, myBool);
        this.myInt2 = myInt2;
    }
    
    @Override
    public void printDescription(){
        super.printDescription();
        System.out.println("myInt2 = " + myInt2);
    }
}
