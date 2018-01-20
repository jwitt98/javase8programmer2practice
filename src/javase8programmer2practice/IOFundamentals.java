//Use BufferedReader, BufferedWriter, File, FileReader, FileWriter, FileInputStream, 
//FileOutputStream, ObjectOutputStream, ObjectInputStream, and PrintWriter in the 
//java.io package.
package javase8programmer2practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author jwitt98
 */
public class IOFundamentals {
    
    private static List<Byte> fileBytes;
    
    public static void main(String[] args){
        
        File file = new File("/home/jwitt98/Documents/JavaTests/fileTest.txt");
        String line1 = "This is a new line of text";
        String line2 = "This is another line of text";
        String line3 = "This is the 3rd line of text";
        
        try(FileTextWriter flw = new FileTextWriter(file)){
            flw.writeLine(line1);
            flw.writeLine(line2);
            flw.writeLine(line3);
        }catch(IOException ioe){
            System.err.println("IO error while writing line: " + ioe.getMessage());
        }
        
        try(FileTextReader flr = new FileTextReader(file)){
            flr.readlines();
        }catch(IOException ioe){
            System.err.println("IO error while reading lines: " + ioe.getMessage());
        }
        
        File imageFile = new File("/home/jwitt98/Documents/JavaTests/questions.png");
        try(FileByteReader fbr = new FileByteReader(imageFile)){
            fileBytes = fbr.getBytes();
        }catch(IOException ioe){
            System.out.println("IOException while reading image file");
        }
        
        File imageFileCopy = new File("/home/jwitt98/Documents/JavaTests/questionsCopy.png");
        try(FileByteWriter fbw = new FileByteWriter(fileBytes, imageFileCopy)){
            fbw.writeBytesToFile();
        }catch(IOException ioe){
            System.out.println("IOException while writing image file");
        }
        
        File serializableFile = new File("/home/jwitt98/Documents/JavaTests/serialized.dat");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializableFile));){
            //writes a new file (seriaized.dat) with object info
            oos.writeObject(new SerializableObject("This is a serailized object!"));
        }catch(IOException ioe){
            System.err.println("IOException while creating ObjectOutputStream");
        }
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializableFile))){
            Object obj = ois.readObject();
            String myString = ((SerializableObject)obj).getStr();
            //prints My serailized object contains string: This is a serailized object!
            System.out.println("My serailized object contains string: " + myString);
        }catch(IOException ioe){
            System.err.println("IOException while creating ObjectInputStream");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFoundException occured while reading serialized object");
        }
        
        File printFile = new File("/home/jwitt98/Documents/JavaTests/printFile.txt");
        SerializableObject so = new SerializableObject("This is a new serializable object for PrintWriter");
        try (PrintWriter pr = new PrintWriter(printFile)) {
            pr.println(so);
            pr.println("This is a line");
            pr.write(98);
        }catch(IOException ioe){
            System.err.println("IOException occured while writing printFile");
        }
        
    }
    
}

class FileTextWriter implements AutoCloseable{
    
    private final FileWriter fw;
    private final BufferedWriter bw;
  
    public FileTextWriter(File file) throws IOException{
        fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
    }
    
    public void writeLine(String line) throws IOException{
        bw.write(line);
        bw.newLine();
    }
    
    @Override
    public void close(){
        try{
            bw.close();
            fw.close();          
        }catch(IOException ioe){
            System.err.println("IO error while closing resource.");
            ioe.printStackTrace();
        }
    }   
}

class FileTextReader implements AutoCloseable{
    
    private final FileReader fr;
    private final BufferedReader br;
    
    public FileTextReader(File file) throws FileNotFoundException{
        fr = new FileReader(file);
        br = new BufferedReader(fr);
    }
    
    public void readlines(){
        Stream<String> lines = br.lines();
        lines.forEach(line -> System.out.println(line));
    }
    
    @Override
    public void close(){
        try{
            br.close();
            fr.close();
        }catch(IOException ioe){
            System.err.println("IO error while closing resource");
            ioe.printStackTrace();
        }
    }
}

class FileByteReader implements AutoCloseable{
    
    private final File file;
    private int bite;
    private final FileInputStream fin;
    private final List<Byte> bytes;
    
    public FileByteReader(File file) throws IOException{
        this.file = file;
        fin = new FileInputStream(file);
        bytes = new ArrayList<>();
        do{
            bite = fin.read();
            if(bite >= 0) bytes.add((byte)bite);
        }while(bite >= 0);
        System.out.println("List<Byte> size is " + bytes.size());
    }
    
    public List<Byte> getBytes(){
        return bytes;
    }
    
    @Override
    public void close(){
        try{
            fin.close();
        }catch(IOException ioe){
            System.err.println("IO erro occured when closing FileInputStream");
            ioe.printStackTrace();
        }
    }
}

class FileByteWriter implements AutoCloseable{
    
    private final List<Byte> bites;
    private FileOutputStream fos;
    private File file;
    
    FileByteWriter(List<Byte> bites, File file) throws IOException{
        this.bites = bites;
        this.file = file;
        this.fos = new FileOutputStream(file);
    }
    
    public void writeBytesToFile() throws IOException{        
        
        for(Byte b : bites){
            fos.write((int)b);
        }
        
    }
    
    @Override
    public void close(){
        try{
            fos.close();
        }catch(IOException ioe){
            System.out.println("IOException occured while closing FileOutputStream");
            ioe.printStackTrace();
        }
    }
    
}

class SerializableObject implements Serializable{
    
    private String str;
    
    SerializableObject(String str){
        this.str = str;
    }
    
    public String getStr(){
        return str;
    }
    
    @Override
    public String toString(){
        return str;
    }
    
}

