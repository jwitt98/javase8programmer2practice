//Override hashCode, equals, and toString methods from Object class
package javase8programmer2practice;

import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author jwitt98
 */
public class Overrider {
    
    private String str;
    
    public Overrider(String str){
        this.str = new String(str);
    }
    
    public String getStr(){
        return str;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Overrider){
            return this.str.equals(((Overrider)obj).getStr());
        }else{
            return false;
        }
    }
    
    @Override
    public int hashCode(){
        return this.str.hashCode();
    }
    
    public static void main(String[] args){
        Overrider overrider1 = new Overrider("one");
        Overrider overrider2 = new Overrider("two");
        Overrider overrider3 = new Overrider("one");
        Overrider overrider4 = null;
        
        System.out.println("overrider1.equals(overrider2) = " + overrider1.equals(overrider2));
        System.out.println("overrider1.equals(overrider3) = " + overrider1.equals(overrider3));
        System.out.println("Overrider1.equals(overrider4) = " + overrider1.equals(overrider4));
        System.out.println(overrider4 instanceof Overrider);
        
        Set set = new HashSet(); 
        set.add(overrider1);
        set.add(overrider2);
        set.add(overrider3);
        set.add(overrider4);
        
        System.out.println("set.contains(new Overrider(\"two\") = " + set.contains(new Overrider("two"))) ;
        
    }
    
}
