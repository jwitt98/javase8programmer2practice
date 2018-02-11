//Use Files class to check, read, delete, copy, move, manage metadata of a file or directory
package javase8programmer2practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Map;

/**
 *
 * @author jwitt98
 */
public class FilesTester {
    private static Path path;
    private static Path filePath;
    
    public static void main(String[] args){
        try{
            //creates a directory if it doesn't already exixist.  Ignores it if it already exists
            path = Files.createDirectories(Paths.get("/home/jwitt98/Documents/JavaTests/FilesTest"));
            //checks if the file aready exisits, creates it if it doesn't
            filePath = path.resolve("FilesTest.txt");
            if(!Files.exists(filePath)){
                Files.createFile(filePath);
            }
            
            //prints FilesTest.txt exists? true
            System.out.println("FilesTest.txt exists? " + Files.exists(filePath, LinkOption.NOFOLLOW_LINKS));
        
            try(BufferedWriter bw = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE);
                    BufferedReader br = Files.newBufferedReader(filePath)){
                //writes 50 lines of text to FilesTest.txt
                for(int i = 0; i < 50; i++){
                    bw.write("This is line " + i);
                    bw.newLine();
                }
                //prints the 40 lines read from FilesTest.txt
                br.lines().forEach(System.out::println);
            }
            
            Path copyPath = filePath.getParent().resolve("FileTest_copy.txt");
            //creates an exact copy of FilesTest.txt
            Files.copy(filePath, copyPath, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
            Thread.sleep(1000);//so I can see the file before it is renamed
            //renames file FileTest_copy.txt to FileTest_copy_moved.txt 
            Files.move(copyPath, copyPath.resolveSibling("FileTest_copy_moved.txt"), StandardCopyOption.REPLACE_EXISTING);
            Thread.sleep(1000);//so I can see the file before it is deleted
            //deletes the file FileTest_copy_moved.txt
            Files.delete(copyPath.resolveSibling("FileTest_copy_moved.txt"));
            
            Path absTmpPath = Files.createTempFile("prefix", null);
            //prints /tmp/prefix8854918059952312743.tmp
            System.out.println(absTmpPath);
            
            Path relTmpPath = Paths.get("../../../../" + absTmpPath.getParent() + "/" + absTmpPath.getFileName());
            System.out.println(relTmpPath);
            
            //prints jwitt98
            System.out.println(Files.getOwner(relTmpPath));
            
            //prints Files.isSameFile(absTmpPath, relTmpPath) returns: true
            System.out.println("Files.isSameFile(absTmpPath, relTmpPath) returns: " + Files.isSameFile(absTmpPath, relTmpPath));
            //prints Files.isDirectory(absTmpPath) returns: false
            System.out.println("Files.isDirectory(absTmpPath) returns: " + Files.isDirectory(absTmpPath));
            //prints Files.isExecutable(absTmpPath) returms: false
            System.out.println("Files.isExecutable(absTmpPath) returms: " + Files.isExecutable(absTmpPath));
            //prints Files.isHidden(absTmpPath) returns: false
            System.out.println("Files.isHidden(absTmpPath) returns: " + Files.isHidden(absTmpPath));
            //prints Files.isReadable(path) retruns: true
            System.out.println("Files.isReadable(path) retruns: " + Files.isReadable(path));
            //prints Files.isWritable(path) return: true
            System.out.println("Files.isWritable(path) return: " + Files.isWritable(path));
                       
            Map<String, Object> basicAttributes = Files.readAttributes(path, "*", LinkOption.NOFOLLOW_LINKS);
            //prints the following:
//            str = lastAccessTime : obj = 2018-02-04T00:03:35.574047Z
//            str = lastModifiedTime : obj = 2018-02-04T04:13:30.081405Z
//            str = size : obj = 4096
//            str = creationTime : obj = 2018-02-04T04:13:30.081405Z
//            str = isSymbolicLink : obj = false
//            str = isRegularFile : obj = false
//            str = fileKey : obj = (dev=821,ino=1050894)
//            str = isOther : obj = false
//            str = isDirectory : obj = true           
            basicAttributes.forEach((str, obj)->System.out.println("str = " + str + " : obj = " + obj));
            
            Instant inst = ((FileTime)Files.getAttribute(path, "dos:lastAccessTime", LinkOption.NOFOLLOW_LINKS)).toInstant();
            //prints lastAccessTime is: 2018-02-04T00:03:35.574047Z
            System.out.println("lastAccessTime is: " + inst);
            
            inst = ((FileTime)Files.getAttribute(path, "posix:lastModifiedTime")).toInstant();
            //prints lastModifiedTime is: 2018-02-04T04:35:29.249807Z
            System.out.println("lastModifiedTime is: " + inst);
            
            long size = (Long)Files.getAttribute(path, "size");
            //prints size is : 4096
            System.out.println("size is : " + size);
            
            boolean isDirectory = (Boolean)Files.getAttribute(path, "isDirectory");
            //prints isDirectory is : true
            System.out.println("isDirectory is : " + isDirectory);
            
            System.out.println("path is : " + path);
            
            Files.delete(absTmpPath);
            
        }catch(IOException ioe){
            System.err.println("IOException occured while creating path: " + ioe.getMessage());
            ioe.printStackTrace();
        }catch(InterruptedException ie){
            
        }
        
    }
    
}
