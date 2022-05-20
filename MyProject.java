import java.io.*;
import java.util.*;
public class MyProject {
    static HashMap<Integer , Integer> noOfMatches = new HashMap<>();
    static HashMap<Integer,String> matchIdWithBattingTeam = new HashMap<>();
    static ArrayList<Integer> matchesInTheYearOf2016 = new ArrayList<>();
    static HashMap <String,Integer> noOfMatchesWonByAllTeamsDictionery = new HashMap<>();

    static HashMap<String,Integer> battingTeamRunGotInExtras = new HashMap<>();


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
                    if(Integer.parseInt(data[1])==2016){
                        matchesInTheYearOf2016.add(Integer.parseInt(data[0]));
                    }
                }
            }
            System.out.println(noOfMatches);
            System.out.println(noOfMatchesWonByAllTeamsDictionery);
            //System.out.println(matchesInTheYearOf2016);

            File deliveryFileObj = new File("./deliveries.csv");
            Scanner deliveryFileReadObj = new Scanner(deliveryFileObj);
            boolean removeFirstLine = true;
            while(deliveryFileReadObj.hasNextLine()){
                String deliveryData = deliveryFileReadObj.nextLine();
                if(removeFirstLine){
                    removeFirstLine =false;
                    continue;
                }
                String [] dataOfDelivery = deliveryData.split(",");
                for (Integer matchId : matchesInTheYearOf2016) {
                    if(Objects.equals(matchId, Integer.valueOf(dataOfDelivery[0]))){
                        matchIdWithBattingTeam.put(Integer.valueOf(dataOfDelivery[0]),dataOfDelivery[2]);
                        if(battingTeamRunGotInExtras.containsKey(dataOfDelivery[2])){
                            battingTeamRunGotInExtras.put(dataOfDelivery[2],battingTeamRunGotInExtras.get(dataOfDelivery[2])+Integer.valueOf(dataOfDelivery[16]));
                        }
                        else {
                            battingTeamRunGotInExtras.put(dataOfDelivery[2], Integer.valueOf(dataOfDelivery[16]));
                        }
                    }
                }
            }
            //System.out.println(matchIdWithBattingTeam);
            System.out.println(battingTeamRunGotInExtras);
            readObj.close();
            deliveryFileReadObj.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
            e.getMessage();
        }
    }
}
