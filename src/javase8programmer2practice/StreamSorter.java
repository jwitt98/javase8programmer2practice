//Sort a collection using Stream API
package javase8programmer2practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class StreamSorter {
    
    public static void main(String[] args){
        
        String txt = "The quick brown fox jumps over the lazy dog";
        Stream<String> strStream = Arrays.stream(txt.split(" ")).sorted((str1,str2)-> str1.compareToIgnoreCase(str2));
        strStream.forEach(str->System.out.print(str + " "));//prints brown dog fox jumps lazy over quick The the
        System.out.println();
        strStream = Arrays.stream(txt.split(" ")).sorted((str1,str2)-> str1.length() - str2.length());
        strStream.forEach(str->System.out.print(str + " "));//prints The fox the dog over lazy quick brown jumps 
        System.out.println();
        strStream = Arrays.stream(txt.split(" ")).sorted(new strLenComp().thenComparing(new strLexComp()));
        strStream.forEach(str->System.out.print(str + " "));//prints dog fox The the lazy over brown jumps quick 
        System.out.println();
        strStream = Arrays.stream(txt.split(" ")).sorted(new strLenComp().thenComparing(new strLexComp()).reversed());
        strStream.forEach(str->System.out.print(str + " "));//prints quick jumps brown over lazy The the fox dog
        System.out.println();
        
        IntegerHolder ih1 = new IntegerHolder(9);
        IntegerHolder ih2 = new IntegerHolder(7);
        IntegerHolder ih3 = new IntegerHolder(3);
        IntegerHolder ih4 = new IntegerHolder(5);
        IntegerHolder ih5 = new IntegerHolder(1);
        
        Stream<IntegerHolder> ihStream = Stream.of(ih1,ih2,ih3,ih4,ih5).sorted();
        ihStream.forEach(ih->System.out.print(ih.getI() + " "));//prints 1 3 5 7 9
        System.out.println();
        
    }
    
}

class strLexComp implements Comparator<String>{
    @Override
    public int compare(String str1, String str2){
        return str1.compareToIgnoreCase(str2);
    }
}

class strLenComp implements Comparator<String>{
    @Override
    public int compare(String str1, String str2){
        return str1.length() - str2.length();
    }
}

class IntegerHolder implements Comparable<IntegerHolder>{
    private final Integer i;
    public IntegerHolder(Integer i){
        this.i = i;
    }
    
    @Override
    public int compareTo(IntegerHolder that){
        return this.i.compareTo(that.getI());
    }
    
    public Integer getI(){
        return this.i;
    }
}