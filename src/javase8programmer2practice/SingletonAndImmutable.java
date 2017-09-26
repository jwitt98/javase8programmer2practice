//Create and use singleton classes and immutable classes
package javase8programmer2practice;

import java.util.stream.Stream;
import java.util.stream.IntStream;

/**
 *
 * @author jwitt98
 */
public class SingletonAndImmutable {
    
    public static void main(String[] args){
        
        Singleton instance1 = Singleton.getInstance("instance1");
        System.out.println("instance1 contains " + instance1.getStr());
        Singleton instance2 = Singleton.getInstance("Instance2");
        System.out.println("instance2 contains " + instance2.getStr());
        System.out.println("instance1 == instance2 is " + (instance1 == instance2));
        System.out.println("instance1 contains " + instance1.getStr());
        
        Character[] charsOld = {'O','l','d',' ','S','t','r','i','n','g'};
        Character[] charsNew = {'N','e','w',' ','S','t','r','i','n','g'};
        ImmutableString isOld = new ImmutableString(charsOld);
        ImmutableString isNew = new ImmutableString(charsNew);
        System.out.println("isOld contains " + isOld.getString());
        System.out.println("isNew contains " + isNew.getString());
        
    }
    
}

class Singleton{
    
    private String str;
    
    private Singleton (){
        
    }
    
    public static Singleton getInstance(String str){
        Singleton singleton = SingletonHolder.singletonHolder;
        singleton.setStr(str);
        return singleton;
    }
    
    public static class SingletonHolder{
        static Singleton singletonHolder = new Singleton();
    }
    
    public String getStr(){
        return this.str;
    }
    
    private void setStr(String str){
        this.str = str;
    }
    
}

final class ImmutableString{
    
    private final StringBuilder sb;
    
    public ImmutableString(Character[] chars){
        
        sb = new StringBuilder();
        IntStream.range(0, chars.length).map(i -> chars[i])
                .forEach(i -> this.sb.append((char)i));
    }
    
    public String getString(){
        return sb.toString();
    }
    
}