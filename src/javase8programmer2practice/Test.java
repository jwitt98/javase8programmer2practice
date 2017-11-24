
package javase8programmer2practice;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Test {
    
    public static void main(String[] args){
        LocalDate ld = LocalDate.parse("12/01/2017", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));//prints 01/12/2017
        
        Animal1.main(args);
    }
    
}


class Animal1 {
    public static void main( String [] args ) {
        ArrayList<Animal> animalFile = new ArrayList<>();

      
        animalFile.add(new Animal("name1", "lname1", 30, 10, 0, 1, "or1"));
        animalFile.add(new Animal("name2", "lname2", 10, 5, 0, 1, "or2"));
        animalFile.add(new Animal("name3", "lname3", 40, 10, 0, 1, "or3"));
        animalFile.add(new Animal("name4", "lname4", 20, 10, 0, 1, "or4"));
        
        //animalFile.sort(Comparator.comparingInt(a -> a.getMaximumLength()));
        Collections.sort(animalFile, Collections.reverseOrder(Comparator.comparingInt(a-> a.getMaximumLength())));
        
        System.out.print("The 3 largest animals are: ");
        
        animalFile.subList(0, 3).stream().forEach(n -> System.out.print(n.animalName + " "));
        
    }    
}

class Animal{
    public String animalName;
    public String latinName;
    public int maximumLength;
    public int maximumDepth;
    public int femaleAnimal;
    public int presenceIndicator;
    public String oceanicRegions;
    
    public Animal(String animalName, String latinName, int maximumLength, int maximumDepth, int femaleAnimal, int presenceIndicator, String oceanicRegions){
        this.animalName = animalName;
        this.latinName = latinName;
        this.maximumDepth = maximumDepth;
        this.maximumLength = maximumLength;
        this.femaleAnimal = femaleAnimal;
        this.presenceIndicator = presenceIndicator;
        this.oceanicRegions = oceanicRegions;
    }
    
    public int getMaximumLength(){
        return maximumLength;
    }
}


