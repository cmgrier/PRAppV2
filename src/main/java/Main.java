import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        try {
            FileReader fr = new FileReader("Data/Smash4/Seasons/TestSeason.csv");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            int lineCnt = 0;
            String SeasonTitle;
            ArrayList<Player> players = new ArrayList<>();
            while ((line = br.readLine()) != null){
                if(lineCnt == 0){
                    SeasonTitle = line;
                    System.out.println(SeasonTitle);
                }else if(lineCnt == 1){
                    String[] Tournaments = line.split(",");
                    ArrayList<String> tournamentList = new ArrayList<>();
                    for (String s:Tournaments) {
                        tournamentList.add(s);
                    }
                    System.out.println(tournamentList);
                }else {
                    String[] player = line.split(",");
                    //add Character parsing here
                    Player p = new Player(player[0],Integer.parseInt(player[1]));
                    players.add(p);
                    }
            }
            Season s = new Season
            br.close();
            fr.close();
            FileWriter fw = new FileWriter("Data/Smash4/Seasons/TestSeason.csv",true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.close();
            fw.close();

        }catch (IOException e){}
        System.out.println("Complete");
//        try {
//            FileWriter fw = new FileWriter("Data/Smash4/Seasons/TestSeason.csv");
//        }
//        catch (IOException e){}
    }
}
