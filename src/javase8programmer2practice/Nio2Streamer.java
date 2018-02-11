//Use Stream API with NIO.2
package javase8programmer2practice;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class Nio2Streamer {
    
    public static void main(String[] args){
        
        try{
        
            Path dir = Paths.get("/home/jwitt98/Documents/JavaTests");
            
            Stream<Path> pathStream = Files.list(dir);         
            //prints the following:
            //Directory?: false : /home/jwitt98/Documents/JavaTests/questions.png
            //Directory?: false : /home/jwitt98/Documents/JavaTests/printFile.txt
            //Directory?: false : /home/jwitt98/Documents/JavaTests/serialized.dat
            //Directory?: false : /home/jwitt98/Documents/JavaTests/questionsCopy.png
            //Directory?: true : /home/jwitt98/Documents/JavaTests/FilesTest
            //Directory?: false : /home/jwitt98/Documents/JavaTests/fileTest.txt
            pathStream.forEach(path -> System.out.println("Directory?: " + Files.isDirectory(path) + " : " + path));
            
            //prints the following
            ///home/jwitt98/Documents/JavaTests
            ///home/jwitt98/Documents/JavaTests/questions.png
            ///home/jwitt98/Documents/JavaTests/printFile.txt
            ///home/jwitt98/Documents/JavaTests/serialized.dat
            ///home/jwitt98/Documents/JavaTests/questionsCopy.png
            ///home/jwitt98/Documents/JavaTests/FilesTest
            ///home/jwitt98/Documents/JavaTests/FilesTest/FilesTest.txt
            ///home/jwitt98/Documents/JavaTests/fileTest.txt
            Files.walk(dir, FileVisitOption.FOLLOW_LINKS).forEach(System.out::println);
            
            //Files.find(dir, 1, matcher, options)
            
        }catch(IOException ioe){
            System.err.println("IOException Occured: " + ioe.getLocalizedMessage());
        }
        
    }
    
}
