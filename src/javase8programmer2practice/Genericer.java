//Create and use a generic class

package javase8programmer2practice;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author jwitt98
 * @param <T>
 * @param <V>
 */
public class Genericer<T, V> {
    private final T typeT;
    private final V typeV;
    
    public Genericer(T typeT, V typeV ){
        this.typeT = typeT;
        this.typeV = typeV;
    }
 
    public T getTypeT(){
        return this.typeT;
    }
    public V getTypeV(){
        return this.typeV;
    }
    
    public void printString(String str){
        System.out.println("This is the passed String in printString(): " + str);
    }
    
    public <X> List<X> fillList(List<X> list, X val){
        for(int i = 0; i < list.size(); i++){
            list.set(i, val);
        }
        return list;
    }
    
    public static void main(String[] args){
        Genericer<int[], Integer> genericer = new Genericer<>(new int[]{4,8,4,6,9,2,2}, 99);
        
        if(genericer.getTypeT() instanceof int[]){
            for(int i = 0; i < genericer.getTypeT().length; i++){
                System.out.println("The " + i + " array index contains " + genericer.getTypeT()[i]);
            }
        }
        System.out.println("typeV is of type " + genericer.getTypeV().getClass() 
                +" with value of " + genericer.getTypeV());
        
        Genericer genericerRaw = new Genericer(Integer.parseInt("8"), "RAW");
        System.out.println("Raw type T is: " + genericerRaw.getTypeT().getClass());
        System.out.println("Raw type V is: " + genericerRaw.getTypeV().getClass());
        //incompatible types: Object cannot be converted to String
        //genericerRaw.printString(genericerRaw.getTypeV());
        //Raw type problem example below
        List<String> list1 = new LinkedList<>();
        list1.add("one");
        list1.add("two");
        List list2 = list1; //compiles OK
        list2.add(new Integer(3));//compiles OK
        for(Iterator<String> itr = list2.iterator(); itr.hasNext();){
            //java.lang.Integer cannot be cast to java.lang.String
            //System.out.println(" Next is " + itr.next());
            //System.out.println("class is " + itr.next().getClass());
            itr.next();
        }
        List<String> strlist = new ArrayList<>();
        strlist.add("one"); strlist.add("two"); strlist.add("three"); strlist.add("four");
        List newList = genericer.fillList(strlist, "Fifty");
        newList.forEach(System.out::println);
        
        //wildcard example
        List<?> myList = new ArrayList<>();
        List<?> myList2 = newList;
        //no suitable method found for add(ArrayList<String>)
        //method Collection.add(CAP#1) is not applicable
        //(argument mismatch; ArrayList<String> cannot be converted to CAP#1)
        //method List.add(CAP#1) is not applicable
        //(argument mismatch; ArrayList<String> cannot be converted to CAP#1)
        //where CAP#1 is a fresh type-variable:
        //CAP#1 extends Object from capture of ?
        //myList.add(new ArrayList<String>());
        //this is OK
        myList2.forEach(System.out::println);
        //this is not OK
        //myList2.add("This added");
        
    }
}
