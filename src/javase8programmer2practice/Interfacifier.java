package javase8programmer2practice;

//Develop code that declares, implements and/or extends interfaces and use the @Override annotation.

/**
 *
 * @author jwitt98
 */
public class Interfacifier {
    
    public static void main(String[] args){
        ResidentialStructure house = new House("ranch house", 8);
        System.out.println("Structure type is " + house.getType());
        System.out.println("The size of the family is " + house.getFamilySize());
        house.printMe();
        ResidentialStructure.printStatic();
        //illegal static interface method call
        //the receiver expression should be replaced with the 
        //type qualifier 'ResidentialStructure'
        //house.printStatic();
    }
    
}

interface Structure{
    public void setType(String type);
    public String getType();
}

interface ResidentialStructure extends Structure{
    void setFamilySize(int size);
    int getFamilySize();
    default void printMe(){
        System.out.println("I am the ResidentialStructure interface...");
    }
    static void printStatic(){
        System.out.println("I am the static printStatic method in ReidentialStructure interface");
    }
}

class House implements ResidentialStructure{
    private String type;
    private int familySize;
    
    House(String type, int familySize){
        this.type = type;
        this.familySize = familySize;
    }
    
    @Override
    public void setFamilySize(int size){
        this.familySize = size;
    }
    
    @Override
    public int getFamilySize(){
        return familySize;
    }
    
    @Override
    public void setType(String type){
        this.type = type;
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    @Override
    public void printMe(){
        System.out.println("I am overriding the default printMe method in ResidentialStructure");
    }
}
