import java.io.*;
import java.util.*;
public class MyProject {
    static HashMap<Integer , Integer> noOfMatches = new HashMap<>();
    static ArrayList <String> noOfMatchesWonByAllTeamsList = new ArrayList<>();
    static HashMap<String,Integer> dictionaryDataOfWinnigTeams = new HashMap<>();
    static HashMap <String,Integer> noOfMatchesWonByAllTeamsDictionery = new HashMap<>();
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
                //System.out.println(data[10]);
                if(noOfMatches.containsKey(Integer.valueOf(data[1]))){
                    noOfMatches.put(Integer.valueOf((data[1])), noOfMatches.get(Integer.valueOf((data[1])))+1);
                }
                else {
                    noOfMatches.put(Integer.valueOf(data[1]), 1);
                }
                if(data[10]!= "") {
                    if(Objects.equals(data[10], "Rising Pune Supergiant") || Objects.equals(data[10], "Rising Pune Supergiants")){
                        if(noOfMatchesWonByAllTeamsDictionery.containsKey("Rising Pune Supergiants")){
                            noOfMatchesWonByAllTeamsDictionery.put("Rising Pune Supergiants",noOfMatchesWonByAllTeamsDictionery.get("Rising Pune Supergiants")+1);
                        }
                        else {
                            noOfMatchesWonByAllTeamsDictionery.put("Rising Pune Supergiants", 1);
                        }
                    }
                    else{
                        if(noOfMatchesWonByAllTeamsDictionery.containsKey(data[10])){
                            noOfMatchesWonByAllTeamsDictionery.put(data[10],noOfMatchesWonByAllTeamsDictionery.get(data[10])+1);
                        }
                        else {
                            noOfMatchesWonByAllTeamsDictionery.put(data[10], 1);
                        }
                    }
                }
            }
            readObj.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
            e.getMessage();
        }
        catch (Exception e){
            System.out.println("Something Wrong Please Check Your Code");
            e.getMessage();
        }
        System.out.println(noOfMatches);
        System.out.println(noOfMatchesWonByAllTeamsDictionery);
    }
}
