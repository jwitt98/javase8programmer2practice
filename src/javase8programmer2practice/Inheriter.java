/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase8programmer2practice;

/**
 *
 * @author jwitt98
 */
public class Inheriter {
    
    static String staticString(){
        return "This is a static string method";
    }
    
    protected int myInt;
    protected String myString;
    protected boolean myBool;
    
    public Inheriter(int myInt, String myString, boolean myBool){
        this.myInt = myInt;
        this.myString = myString;
        this.myBool = myBool;
    }
    
    protected boolean getMyBool(){
        return myBool;
    }
    
    public static void main(String[] args){
        Inheriter inherited = new Inherited(3, "Wonky", true);
        System.out.println(inherited);
        System.out.println(inherited.myString);
        System.out.println(inherited.staticString());
        System.out.println(((Inherited)inherited).staticString());
    }
    
}

class Inherited extends Inheriter{
    
    static String staticString(){
        return "this hides the original static String method";
    }
    
    String myString = "Silly";
    String mySecondString = "Sillier2";
    
    public Inherited(int myInt, String myString, boolean myBool){
        super(myInt, myString, myBool);
        System.out.println("myBool = " + getMyBool());
    }
    
    public void setMyInt(int myInt){
        this.myInt = myInt;
    }
    
    
    @Override
    public String toString(){
        return "myInt = " + myInt + " | myString = " + myString + " | myBool = " 
                + myBool + " | mySecondString = " + mySecondString;
    }
}
