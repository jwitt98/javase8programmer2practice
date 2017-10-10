//Filter a collection by using lambda expressions
package javase8programmer2practice;
import java.util.stream.IntStream;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Optional;

/**
 *
 * @author jwitt98
 */
public class CollectionFilter {
    
    public static void main(String[] args){
        TreeSet<Tree> ts = new TreeSet<>();//a TreeSet of Trees
        ts.add(new Tree(TreeType.OAK, WoodType.VERY_HARD));
        ts.add(new Tree(TreeType.MAPLE, WoodType.HARD));
        ts.add(new Tree(TreeType.CHERRY, WoodType.HARD));
        ts.add(new Tree(TreeType.ELM, WoodType.SOFT));
        ts.add(new Tree(TreeType.PINE, WoodType.SOFT));
        
        TreeSet<Tree> ts2 = ts;
        TreeSet<Tree> ts3 = ts;
        
        ts.removeIf(tree -> tree.woodType.equals(WoodType.SOFT));
        
        ts.forEach(tree -> System.out.print(tree.type + " "));//prints CHERRY OAK MAPLE 
        System.out.println();
                
        Optional<Tree> opt = ts2.stream().max(new WoodTypeComparator());
        opt.ifPresent(i -> System.out.print(i.type + " "));//prints OAK
        System.out.println();
        
        Optional<Tree> opt2 = ts3.stream().min(new WoodTypeComparator());
        opt2.ifPresent(i -> System.out.print(i.type + " "));//prints CHERRY
        System.out.println();

        
    }
    
}

class Tree implements Comparable{
    TreeType type;
    WoodType woodType;
    
    Tree(TreeType type, WoodType woodType){
        this.type = type;
        this.woodType = woodType;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Tree){
            return ((Tree) obj).type.equals(this.type);
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        String name = this.type.name();
        IntStream is = name.chars();
        String hash = "";
        is.forEach(i -> hash.concat(Integer.toString(i)));
        return new Integer(hash);
    }
    
    @Override
    public int compareTo(Object that){
        try{
            return ((Tree)that).type.compareTo(this.type);
        }catch(NullPointerException | ClassCastException e){
            System.err.println(e.getStackTrace());
            return -1;
        }
    }
    
}

enum TreeType{
    ELM,MAPLE,OAK,SPRUCE,PINE,HICKORY,CHERRY,WALNUT,MULLBERRY;
}
enum WoodType{
    VERY_SOFT,SOFT,MEDIUM,HARD,VERY_HARD;
}

class WoodTypeComparator implements Comparator{
    @Override
    public int compare(Object obj1, Object obj2){
        try{
            return ((Tree)obj1).woodType.compareTo(((Tree)obj2).woodType);
        }catch(NullPointerException | ClassCastException e){
            System.err.println(e.getStackTrace());
            return -1;
        }
    }

}