//Use method references with Streams
package javase8programmer2practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class MethodReferences {
    
    public static void main(String[] args){
        
        Stream<Character> cs = "These Are Some Characters".chars().mapToObj(i -> (char)i);
        cs.filter(ch -> !ch.equals(' ')).forEach(System.out::print);//prints TheseAreSomeCharacters
        System.out.println();
        
        String[] strs = {"these", "are", "some", "lowercase", "strings"};
        List<String> strLst = Arrays.asList(strs);
        strLst.stream().map(StringCapitalizer::strToUpper).forEach( str -> System.out.print(str + " "));//method reference
        //prints THESE ARE SOME LOWERCASE STRINGS
        System.out.println();
        
    }    
    
}

class StringCapitalizer{
    
    public static String strToUpper(String str){
        return str.toUpperCase();
    }
    
}
