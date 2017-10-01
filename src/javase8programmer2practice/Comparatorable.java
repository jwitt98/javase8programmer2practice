//Use java.util.Comparator and java.lang.Comparable interfaces
package javase8programmer2practice;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
/**
 *
 * @author jwitt98
 */
public class Comparatorable {
    public static void main(String[] args){
        Employee Frank = new Employee(1000, "Frank", "Jones", LocalDate.of(2015, Month.MARCH, 24));
        Employee Susan = new Employee(1001, "Susan", "Riley", LocalDate.of(1998, Month.JUNE, 3));
        Employee Howdy = new Employee(1002, "Howdy", "Doodey", LocalDate.of(1956, Month.JANUARY, 1));
        
        Employee[] employees = {Howdy, Frank, Susan};
        Arrays.sort(employees);//All elements in the array must implement the Comparable interface
        
        for(Employee employee:employees){
            System.out.print(employee.getEFName() + "  ");//prints Howdy  Susan  Frank 
            //natural sort order is LocalDate oldest to newest determined by compareTo method
        }
        System.out.println("\n");
        
        Arrays.sort(employees, new CompareEID());
        
        for(Employee employee: employees){
            System.out.print(employee.getEFName() + "  ");//prints Frank  Susan  Howdy  
            //sorted by eID
        }
        System.out.println("\n");
        
        Arrays.sort(employees, new CompareELName());
        
        for(Employee employee: employees){
            System.out.print(employee.getELName() + "  ");//prints Doodey  Jones  Riley
            //sorted lexigraphically by last name
        }
        System.out.println("\n");
        
        Arrays.sort(employees, new CompareELNameRev());
        
        for(Employee employee : employees){
            System.out.print(employee.getELName() + " ");// prints Riley Jones Doodey (reverse order)
        }
        System.out.println();
    }
}

class Employee implements Comparable<Employee>{
    private Integer eID;
    private String eFName;
    private String eLName;
    private LocalDate hireDate;
    
    public Employee(Integer eID, String eFName, String eLName, LocalDate hireDate){
        this.eID = eID; this.eFName = eFName; this.eLName = eLName; this.hireDate = hireDate;
    }
    
    @Override
    public int compareTo(Employee that){
        return this.hireDate.compareTo(that.hireDate);
    }
    public Integer getEID(){
        return eID;
    }
    public String getEFName(){
        return eFName;
    }
    public String getELName(){
        return eLName;
    }
    public LocalDate getHireDate(){
        return hireDate;
    }
}

class CompareEID implements Comparator<Employee>{
    @Override
    public int compare(Employee e1, Employee e2){
        return e1.getEID().compareTo(e2.getEID());
    }
}

class CompareELName implements Comparator<Employee>{
    @Override
    public int compare(Employee e1, Employee e2){
        return e1.getELName().compareTo(e2.getELName());
    }
}

class CompareELNameRev implements Comparator<Employee>{
    @Override
    public int compare(Employee e1, Employee e2){
        return e2.getELName().compareTo(e1.getELName());
    }
}