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
class Encapsulater {
    
    private int myInt;
    private String myString;
    private boolean myBool;
    
    public Encapsulater(int myInt, String myString, boolean myBool){
        this.myInt = myInt;
        this.myString = myString;
        this.myBool = myBool;
    }
    
    public int getMyInt(){
        return myInt;
    }
    
    public String getMyString(){
        return myString;
    }
    
    public boolean getMyBool(){
        return myBool;
    }
    
    public void setMyInt(int myInt){
        this.myInt = myInt;
    }
    
    public void setMyString(String myString){
        this.myString = myString;
    }
    
    public void setMyBool(boolean myBool){
        this.myBool = myBool;
    }
    
    @ Override
    public String toString(){
        return "myInt = " + myInt + " | myString = " + myString + " | myBool = " + myBool;
    }
    
    public static void main(String[] args){
        Encapsulater encapsulater = new Encapsulater(8, "Hehe", true);
        System.out.println(encapsulater);
        encapsulater.setMyInt(88);
        encapsulater.setMyString("Hardy har har...");
        encapsulater.setMyBool(false);
        System.out.println("myInt = " + encapsulater.getMyInt()
            + " | myString = " + encapsulater.getMyString()
            + " | myBool = " + encapsulater.getMyBool());
    }
    
}
