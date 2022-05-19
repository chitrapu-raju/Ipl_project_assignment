import java.io.*;
import java.util.*;
public class MyProject {
    public static void main(String [] args){
        try{
            File fileObj = new File("./matches.csv");
            Scanner readObj = new Scanner(fileObj);
            System.out.println("File opened successfully");
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
            e.getMessage();
        }
    }
}
