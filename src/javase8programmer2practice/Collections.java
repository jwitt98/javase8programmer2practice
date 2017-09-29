//Create and use ArrayList, TreeSet, TreeMap, and ArrayDeque objects
package javase8programmer2practice;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author jwitt98
 */
public class Collections {
    
    public static void main(String[] args){
        
        ListTests lt = new ListTests();
        lt.populate();
        lt.collect();
        lt.printAl();
        lt.printAll();
        
        Integer[] integerArray = {1,2,3};
        List<Integer> integerList = Arrays.asList(integerArray);
        //java.lang.UnsupportedOperationException
        //integerList.add(10);
        integerList.set(2, 10);//OK
        for(Integer integer : integerArray){
            //integerArray now contains [1,2,10]
            System.out.println("integerArray contains: " + integer);//prints 1,2,10
        }
        
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("c");
        treeSet.add("b");
        treeSet.add("a");
        treeSet.add("b");
        for(String str : treeSet){
            System.out.print(str);//prints abc
        }
        System.out.println();
        
        NavigableMap<Integer,String> nm = new TreeMap<>();
        nm.put(10, "Ten");
        nm.put(20, "Twenty");
        nm.put(30, "Thirty");
        nm.put(40, "Forty");

        System.out.println(nm.ceilingEntry(25));
        
        Deque<Double> dq = new ArrayDeque<>(3);
        dq.addLast(10.1); dq.addLast(2.2); dq.addFirst(1.1); dq.add(34.0); dq.addLast(3.7);
        dq.forEach(d->System.out.print(d + "  " ));//prints 1.1  10.1  2.2  34.0  3.7  
        System.out.println();
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
    
    public void printAl(){
        Iterator itr = arrayList.iterator();
        while(itr.hasNext()){
            System.out.println("Iterated arraylist = " + itr.next());
        }
        System.out.println();
    }
    
    public void printAll(){
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
