//Use Path interface to operate on file and directory paths
package javase8programmer2practice;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jwitt98
 */
public class Pathifier {
    
    public static void main(String[] args){
        
        Path path = Paths.get("home","jwitt98", "Documents", "JavaTests", "pathTest.txt");
        
        //prints home/jwitt98/Documents/JavaTests/pathTest.txt
        System.out.println(path);
        
        //prints pathTest.txt
        System.out.println(path.getFileName());
        
        //prints sun.nio.fs.LinuxFileSystem@6bc7c054
        System.out.println(path.getFileSystem());
        
        //prints Documents
        System.out.println(path.getName(2));
        
        //prints 5
        System.out.println(path.getNameCount());
        
        //prints home/jwitt98/Documents/JavaTests
        System.out.println(path.getParent());
        
        //prints null
        System.out.println(path.getRoot());
        
        //prints false
        System.out.println(path.isAbsolute());
        
        path = path.toAbsolutePath();
        //prints true
        System.out.println(path.isAbsolute());
        
        Path relativePath = Paths.get("../../test");
        //prints ../../test
        System.out.println(relativePath);
        
        Path absolutePath = relativePath.toAbsolutePath();
        //prints /home/jwitt98/NetBeansProjects/JavaSE8Programmer2Practice/../../test
        System.out.println(absolutePath);
        
        Path normalizedPath = absolutePath.normalize();
        //prints /home/jwitt98/test
        System.out.println(normalizedPath);
        
        //If test dir does not exisit, prints IOException occured creating real path: /home/jwitt98/test
        //If test dir does exisit, prints The real path is: /home/jwitt98/test
        try {
            Path realPath = normalizedPath.toRealPath(LinkOption.NOFOLLOW_LINKS);
            System.out.println("The real path is: " + realPath);
        } catch (IOException ioe) {
            System.err.println("IOException occured creating real path: " + ioe.getMessage());
        }
        
        Path resolvedPath = normalizedPath.resolve(Paths.get("subTest"));
        //prints /home/jwitt98/test/subTest
        System.out.println(resolvedPath);
        
        Path relativePath2 = Paths.get("test2");
        //prints test2
        System.out.println(relativePath2);
        Path absolutePath2 = relativePath2.toAbsolutePath();
        //prints /home/jwitt98/NetBeansProjects/JavaSE8Programmer2Practice/test2
        System.out.println(absolutePath2);
        //prints absolutePath2.compareTo(relativePath2) = -69
        System.out.println("absolutePath2.compareTo(relativePath2) = " + absolutePath2.compareTo(relativePath2));
        //prints absolutePath2.compareTo(relativePath2.toAbsolutePath()) = 0
        System.out.println("absolutePath2.compareTo(relativePath2.toAbsolutePath()) = " + absolutePath2.compareTo(relativePath2.toAbsolutePath()));
        //prints absolutePath2.equals(relativePath2) = false
        System.out.println("absolutePath2.equals(relativePath2) = " + absolutePath2.equals(relativePath2));
        //prints absolutePath2.equals(relativePath2.toAbsolutePath()) = true
        System.out.println("absolutePath2.equals(relativePath2.toAbsolutePath()) = " + absolutePath2.equals(relativePath2.toAbsolutePath()));
        
    }
    
}
