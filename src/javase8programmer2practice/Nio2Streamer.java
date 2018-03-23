//Use Stream API with NIO.2
package javase8programmer2practice;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.util.function.BiPredicate;
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
            System.out.println();
            
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
            System.out.println();
            
            //prints File count is: 8
            //counts only the files and not the directories
            System.out.println("File count is: " + Files.walk(dir, 2).filter(p->p.toFile().isFile()).count() + "\n");
            
            BiPredicate<Path,BasicFileAttributes> matcher = (path,bfa)-> path.
                    toFile().getName().endsWith(".png") || bfa.size() == 0;
            Stream<Path> foundPaths = Files.find(dir, 2, matcher, FileVisitOption.FOLLOW_LINKS);
            //prints the following
            ///home/jwitt98/Documents/JavaTests/questions.png
            ///home/jwitt98/Documents/JavaTests/zeroLengthFile1
            ///home/jwitt98/Documents/JavaTests/questionsCopy.png
            ///home/jwitt98/Documents/JavaTests/FilesTest/zeroLenghtFile2
            foundPaths.forEach(System.out::println);
            System.out.println();
            
            Path filePath = dir.resolve("fileTest.txt");
            //prints
            //This is a new line of text
            //This is another line of text
            //This is the 3rd line of text
            Files.lines(filePath).forEach(System.out::println);
            System.out.println();
            
            
            
        }catch(IOException ioe){
            System.err.println("IOException Occured: " + ioe.getLocalizedMessage());
        }
        
    }
    
}
