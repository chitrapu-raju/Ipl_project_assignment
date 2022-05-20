import java.io.*;
import java.util.*;
public class MyProject {
    static HashMap<Integer , Integer> noOfMatches = new HashMap<>();
    static HashMap<Integer,String> matchIdWithBattingTeam = new HashMap<>();
    static ArrayList<Integer> matchesInTheYearOf2016 = new ArrayList<>();
    static ArrayList<Integer> matchesInTheYearOf2015 = new ArrayList<>();
    static HashMap <String,Integer> noOfMatchesWonByAllTeamsDictionery = new HashMap<>();

    static HashMap<String,Integer> bowlerWithHisOvers = new HashMap<>();

    static HashMap<String,Integer> bowlerWithHisRuns = new HashMap<>();

    static HashMap<String,Integer> battingTeamRunGotInExtras = new HashMap<>();

    static HashMap<String,Double> economyOfTheBowler = new HashMap<>();


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
                    if(Integer.parseInt(data[1])==2015){
                        matchesInTheYearOf2015.add(Integer.parseInt(data[0]));
                    }
                }
            }
            System.out.println("Number of matches played per year of all the years in IPL :");
            System.out.println(noOfMatches);
            System.out.println();
            System.out.println("Number of matches won of all teams over all the years of IPL :");
            System.out.println(noOfMatchesWonByAllTeamsDictionery);
            System.out.println();
            //System.out.println(matchesInTheYearOf2016);
            //System.out.println(matchesInTheYearOf2015);

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
                for(Integer matchId : matchesInTheYearOf2015){
                    if(Objects.equals(matchId, Integer.valueOf(dataOfDelivery[0]))){
                        if(bowlerWithHisOvers.containsKey(dataOfDelivery[8])){
                            bowlerWithHisOvers.put(dataOfDelivery[8],bowlerWithHisOvers.get(dataOfDelivery[8])+1);
                            bowlerWithHisRuns.put(dataOfDelivery[8],bowlerWithHisRuns.get(dataOfDelivery[8])+Integer.valueOf(dataOfDelivery[17]));
                        }
                        else {
                            bowlerWithHisOvers.put(dataOfDelivery[8], 1);
                            bowlerWithHisRuns.put(dataOfDelivery[8], Integer.valueOf(dataOfDelivery[17]));
                        }
                    }
                }
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
            System.out.println("For the year 2016 , the extra runs conceded per team :");
            System.out.println(battingTeamRunGotInExtras);
            System.out.println();
            //System.out.println(bowlerWithHisOvers);
            //System.out.println(bowlerWithHisRuns);
            for(String key : bowlerWithHisOvers.keySet()){
                bowlerWithHisOvers.put(key,bowlerWithHisOvers.get(key)/6);
            }
            for(String key : bowlerWithHisOvers.keySet()){
                economyOfTheBowler.put(key, bowlerWithHisRuns.get(key)/(double)bowlerWithHisOvers.get(key));
            }
            //System.out.println(bowlerWithHisOvers);
            System.out.println("For the year 2015 get the top economical bowlers :");
            System.out.println(economyOfTheBowler);
            System.out.println();
            readObj.close();
            deliveryFileReadObj.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
            e.getMessage();
        }
    }
}
