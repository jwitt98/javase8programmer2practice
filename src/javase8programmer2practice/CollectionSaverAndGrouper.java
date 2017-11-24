//Save results to a collection using the collect method and group/partition data using the Collectors class
package javase8programmer2practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class CollectionSaverAndGrouper {
    
    public static void main(String[] args){
        
        //Collect a stream into an ArrayList after perfroming an operation on the contents
        IntStream is = IntStream.rangeClosed(0, 10);
        List<Integer> iList = is.parallel()
                .collect(ArrayList::new, (aln, i) -> aln.add(i*i), ArrayList::addAll);
        //combiner only functions with parallel streams
        iList.forEach(i->System.out.print(i + " "));//prints 0 1 4 9 16 25 36 49 64 81 100
        System.out.println();
        
        //Collect a stream into an ArrayList after perfoming a mapping operation
        Stream<Integer> si = Stream.of(10,20,30,40,50);
        iList = si.map(i-> i + 50).collect(Collectors.toCollection(ArrayList::new));
        iList.forEach(i->System.out.print(i + " "));//prints 60 70 80 90 100
        System.out.println();
        
        Person person1 = new Person("John","Peters", 50, Sex.MALE);
        Person person2 = new Person("Jane","Peters", 48, Sex.FEMALE);
        Person person3 = new Person("Paul","Diller", 28, Sex.MALE);
        Person person4 = new Person("Amy","Diller", 31, Sex.FEMALE);
        
        
        //Collect a component of the list into a new ArrayList
        List<Person> people = Arrays.asList(person1, person2, person3, person4);
        List<String> fNames = people.stream().map(Person::getFirstName).collect(Collectors.toList());
        fNames.forEach(name -> System.out.print(name + " "));//prints John Jane Paul Amy
        System.out.println();
        
        //Collect a component of the list and use joining
        String lNames = people.stream().map(Person::getLastName).collect(Collectors.joining(", "));
        System.out.println(lNames);//prints Peters, Peters, Diller, Diller
        
        List<Sex> sex = people.stream().map(Person::getSex).collect(Collectors.toList());
        sex.forEach(s -> System.out.print(s + " "));//prints MALE FEMALE MALE FEMALE
        System.out.println();
        
        //Collect the components of the list onto a map
        Map<Integer, String> ageMap = people.stream().collect(Collectors.toMap(Person::getAge, Person::getFirstName));
        ageMap.forEach((a,n)-> System.out.print( n + " is age " + a + "\n"));
        //prints
        //Jane is age 48
        //John is age 50
        //Paul is age 28
        //Amy is age 31
        
        //Produce and average age of persons in the list
        double avgAge = people.stream().collect(Collectors.averagingInt(Person::getAge));
        System.out.println("Average age is " + avgAge);//prints Average age is 39.25
        
        
        //Group by sex
        Map<Sex, List<Person>> groupedSex = people.stream().collect(Collectors.groupingBy(Person::getSex));
        for(Map.Entry<Sex,List<Person>> entry : groupedSex.entrySet()){
            if(entry.getKey() == Sex.MALE){
                String males = entry.getValue().stream().map(Person::getFirstName).collect(Collectors.joining(", "));
                System.out.println("Males are : " + males);//prints Males are : John, Paul
            }else if(entry.getKey() == Sex.FEMALE){
                String females = entry.getValue().stream().map(Person::getFirstName).collect(Collectors.joining(", "));
                System.out.println("Females are : " + females);//prints Females are : Jane, Amy
            }
        }
        
        //Partition by age 40 and older = true
        Map<Boolean,List<Person>> overTheHill = people.stream().collect(Collectors.partitioningBy(p -> p.getAge() >= 40));
        for(Map.Entry<Boolean, List<Person>> entry : overTheHill.entrySet()){
            if(entry.getKey()){
                String oth = entry.getValue().stream().map(Person::getFirstName).collect(Collectors.joining(" and "));
                System.out.print("Those who are over the hill include: " + oth + "\n");
                //prints Those who are over the hill include: John and Jane
            }else{
                String uth = entry.getValue().stream().map(Person::getFirstName).collect(Collectors.joining(" and "));
                System.out.print("Those who are still young are : " + uth + "\n");
                //prints Those who are still young are : Paul and Amy
            }
        }
       
    }
    
}

class Person{
    
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
    
    Person(String firstName, String lastName, int age, Sex sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAge(){
        return age;
    }
    public Sex getSex(){
        return sex;
    }
    
}

enum Sex{ MALE, FEMALE}