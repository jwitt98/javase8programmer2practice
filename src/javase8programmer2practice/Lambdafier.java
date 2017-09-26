//Create and use Lambda expressions
package javase8programmer2practice;

import java.util.List;
import java.util.ArrayList;
//import java.util.function.Predicate;
/**
 *
 * @author jwitt98
 */
public class Lambdafier {
    
    private String myStr =  "Fifty";
    public static List<String> myList = new ArrayList<>();
    
    public static void main(String[] args){
        
        myList.add("One");
        myList.add("Two");
        myList.add("Three");
        myList.add("Four");
        
        myList.removeIf(str->str.equals("Two"));
        myList.replaceAll(str->str.concat("-Mississippi"));
        Lambdafier lf = new Lambdafier();
        myList = lf.addMyListIf(str->str.equals("One-Mississippi"));
        myList = lf.addMyListIf(str->str.equals("Three-Mississippi"));
        myList = lf.addMyListIf(str->str.equals("Two-Mississippi"));
        
        myList.forEach(System.out::println);
    }
    
    public void setMyStr(String str){
        this.myStr = str;
    }
    public String getMyStr(){
        return this.myStr;
    }
        
    List addMyListIf(BoolTest bt){
        
        for(int i = 0 ; i < myList.size(); i++){
            if(bt.test(myList.get(i))){
                myList.add(myStr);
            }
        }
        return myList;
    }
    
}



@FunctionalInterface
interface BoolTest<T>{
    boolean test(T t);
}
