//Use flatMap() methods in the Stream API
package javase8programmer2practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class FlatMapper {
    
    public static void main(String[] args){
        
        List<String> oneToFive = Arrays.asList("One", "Two", "Three", "Four", "Five");
        List<String> aToJ = Arrays.asList("a","b","c","d","e","f","g","h","i","j");
        List<String> monToSun = Arrays.asList("Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun");
        List<List<String>> lists = Arrays.asList(oneToFive,aToJ,monToSun);
        
        Stream streamOfStrings = lists.stream().flatMap(list -> list.stream().filter(str -> !str.startsWith("T")));
        String sos = streamOfStrings.collect(Collectors.joining(", ")).toString();
        System.out.println(sos);
        //prints One, Four, Five, a, b, c, d, e, f, g, h, i, j, Mon, Wed, Fri, Sat, Sun
        
        try(
                FileReader fr = new FileReader("FlatMapper.txt");
                BufferedReader br = new BufferedReader(fr);
            ){
            Stream<String> words = br.lines().flatMap(line -> Arrays.asList(line.split(" ")).stream());
            TreeSet<String> ts = words.peek(wrd-> System.out.print(wrd + " "))//prints The quick bown fox jumps over the lazy dog
                    .flatMap(str -> Arrays.asList(str.toLowerCase().split("")).stream()).collect(Collectors.toCollection(TreeSet::new));
            ts.forEach(str -> System.out.print(str + " "));//prints a b c d e f g h i j k l m n o p q r s t u v w x y z 
            System.out.println();
            
        }catch(IOException e){
            System.out.println("IOException occured: " + e.getMessage());
        }
        
        
        
    }
    
}
