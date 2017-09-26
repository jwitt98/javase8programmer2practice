//Create and use ArrayList, TreeSet, TreeMap, and ArrayDeque objects
package javase8programmer2practice;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Collection;

/**
 *
 * @author jwitt98
 */
public class Collections {
    
    public static void main(String[] args){
        
        ListTests lt = new ListTests();
        lt.populate();
        lt.collect();
        lt.print();
        
    }
    
}

class ListTests{
    
    List<Integer> arrayList = new ArrayList<>();
    Set<Integer> treeSet = new TreeSet<>();
    Map<Integer, String> treeMap = new TreeMap<>();
    Deque<Integer> arrayDeque = new ArrayDeque<>();
    
    List<Object> collections = new ArrayList<>();
    
    public void populate(){
        for(int i = 0; i <= 10 ; i++){
            arrayList.add(i);
            treeSet.add(i);
            treeMap.put(i, new Integer(i).toString());
            arrayDeque.add(i);
        }
    }
    
    public void collect(){
        collections.add(arrayList);
        collections.add(treeSet);
        collections.add(treeMap);
        collections.add(arrayDeque);
    }
    
    public void print(){
        for(Object o : collections){
            if(o instanceof Collection){
                System.out.println("Type is: " + o.getClass().getSimpleName());
                ((Collection) o).forEach(System.out::println);
                System.out.println();
            }
            if(o instanceof Map){
                System.out.println("Type is: " + o.getClass().getSimpleName());
                for(Object e : ((Map) o).entrySet()){
                   if(e instanceof Map.Entry){
                       System.out.print("Key = " + ((Map.Entry)e).getKey());
                       System.out.print(" | Value = " + ((Map.Entry)e).getValue() + "\n");
                   }
               }
               System.out.println();
            }
        }
    }
    
    
    
}
