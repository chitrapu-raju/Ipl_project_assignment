import java.io.*;
import java.util.*;
public class MyProject {
    static HashMap<Integer , Integer> noOfMatches = new HashMap<>();
    public static void main(String [] args){
        try{
            File fileObj = new File("./matches.csv");
            Scanner readObj = new Scanner(fileObj);
            //System.out.println("File opened successfully");
            boolean firstLine = true;
            while(readObj.hasNextLine()) {
                String line = readObj.nextLine();
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String [] data = line.split(",");
                //System.out.println(Integer.parseInt(data[0]));
                if(noOfMatches.containsKey(Integer.valueOf(data[1]))){
                    noOfMatches.put(Integer.valueOf((data[1])), noOfMatches.get(Integer.valueOf((data[1])))+1);
                }
                else {
                    noOfMatches.put(Integer.valueOf(data[1]), 1);
                }
            }
            readObj.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
            e.getMessage();
        }
        System.out.println(noOfMatches);
    }
}
