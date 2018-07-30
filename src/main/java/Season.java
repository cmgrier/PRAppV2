import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Season {
    ArrayList<Player> players;
    ArrayList<String> tournaments;
    String name;

    double startingScore = 1600; //the starting score for new players

    Season(String name, ArrayList<String> tournaments, ArrayList<Player> players){
        this.name = name;
        this.tournaments = tournaments;
        this.players = players;
    }

    Season(String name){
        this.name = name;
        this.tournaments = new ArrayList<String>();
        this.players = new ArrayList<Player>();
    }

    //creates tournament from inputed data
    //type the following URL to get tournament
    //https://api.challonge.com/v1/tournaments/{tournamentKey}.json?include_participants=1&include_matches=1
    public void addTournament(String tournamentJSON, String game){
        try {
            // TODO: 7/11/2018 add an if to ensure the tournament name (and location) doesn't exist already

            //creates file for JSON object to live in
            JSONObject object = (JSONObject) new JSONParser().parse(tournamentJSON);
            JSONObject tournament = (JSONObject) object.get("tournament");
            String tournamentName = (String) tournament.get("name");
            String jsonFileLocation = "Data/" + game + "/Tournaments/JSONFiles/" + tournamentName + ".json";
            FileWriter JSONWriter = new FileWriter(jsonFileLocation);
            BufferedWriter BJSONWriter = new BufferedWriter(JSONWriter);
            String TourneyString = object.toString();
            BJSONWriter.write(TourneyString);
            BJSONWriter.close();
            JSONWriter.close();

            tournaments.add(tournamentName);
            //creates file for CSV where we will store name of tournament, top 3, and all matches
            String csvFileLocation = "Data/" + game + "/Tournaments/CSVFiles/" + tournamentName + ".csv";
            FileWriter CSVWriter = new FileWriter(csvFileLocation);
            BufferedWriter BCSVWriter = new BufferedWriter(CSVWriter);
            BCSVWriter.write(tournamentName);

            String top3 = getPlacings(tournamentJSON);
            BCSVWriter.newLine();
            BCSVWriter.write(top3);

            //gets matches from JSON file and writes them into CSV
            ArrayList<Match> matches = new ArrayList<>();
            matches = readMatches(tournament);
            for (Match m:matches) {
                BCSVWriter.newLine();
                BCSVWriter.write(m.toString());
            }
            BCSVWriter.close();
            CSVWriter.close();

            String seasonFileLocation = "Data/" + game + "/Seasons/" + this.name;

            FileWriter seasonWriter = new FileWriter(seasonFileLocation);
            BufferedWriter BseasonWriter = new BufferedWriter(seasonWriter);
            BseasonWriter.write(this.name);
            BseasonWriter.newLine();
            String tournamentString = "";
            for (String s:tournaments) {
                tournamentString += s;
                tournamentString += ",";
            }
            tournamentString.substring(0,(tournamentString.length()-1));
            BseasonWriter.write(tournamentString);
            for (Player p:players) {
                BseasonWriter.newLine();
                String playerString = p.tag + "," + p.score + "," + p.characterString() + "," + p.initialScore;
                BseasonWriter.write(playerString);
            }
            BseasonWriter.close();
            seasonWriter.close();


        }catch (ParseException pe){
            System.out.println("Could not parse");
        }
        catch (IOException e){
            System.out.println("Could not read and/or write file");
        }
    }

    public void writeSeason(String game){
        try {
            FileWriter fw = new FileWriter("Data/" + game + "/Seasons/" + this.name);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.name);
            bw.newLine();
            String tournamentString = "";

            for (String s:this.tournaments) {
                tournamentString = tournamentString + s + ",";
            }
            bw.write(tournamentString.substring(0,tournamentString.length() - 1));
            for (Player p:players) {
                bw.newLine();
                String playerString = p.tag + "," + p.score + "," + p.characterString() + "," + p.initialScore;
                bw.write(playerString);
            }
            bw.close();
            fw.close();
        }catch (IOException ioe){}
    }

    public HashMap<Integer, String> readPlayers(JSONObject tournament){
        HashMap<Integer, String> returnlist = new HashMap<>();
        JSONArray players = (JSONArray) tournament.get("participants");
        //System.out.println(players);
        for(int i = 0; i < players.size(); i++){
            JSONObject obj = (JSONObject) players.get(i);
            JSONObject player = (JSONObject) obj.get("participant");
            String PlayerName = player.get("name").toString();
            Integer PlayerID = Integer.parseInt(player.get("id").toString());
            returnlist.put(PlayerID,PlayerName);
        }
        return returnlist;
    }

    public ArrayList<Player> orderedList(){
        ArrayList<Player> orderedList = new ArrayList<>();
        orderedList.addAll(players);
        Collections.sort(orderedList);
        Collections.reverse(orderedList);
        if(orderedList.size() < 10){
            for(int i = 0; i < 10; i++){
                orderedList.add(new Player("",0));
            }
        }
        return orderedList;
    }

    public ArrayList<Match> readMatches(JSONObject tournament){
        //System.out.println("ReadingMatches...");
        //System.out.println("");
        ArrayList<Match> returnList = new ArrayList<>();
        JSONArray matches = (JSONArray) tournament.get("matches");
        HashMap<Integer, String> players = readPlayers(tournament);
        for(int i = 0; i < matches.size(); i++){
            JSONObject obj = (JSONObject) matches.get(i);
            JSONObject match = (JSONObject) obj.get("match");
            Integer Player1ID = Integer.parseInt(match.get("player1_id").toString());
            Integer Player2ID = Integer.parseInt(match.get("player2_id").toString());
            String matchScore = (String) match.get("scores_csv");
            String[] score = matchScore.split("-");
            int player1wins = 0;
            int player2wins = 0;
            if(score.length == 2){
                player1wins = Integer.parseInt(score[0]);
                player2wins = Integer.parseInt(score[1]);
            } else{
                if(matchScore.equals("-1-0")){
                    player1wins = -1;
                    player2wins = 0;
                } else {
                    player1wins = 0;
                    player2wins = -1;
                }
            }
            String Player1 = players.get(Player1ID);
            String Player2 = players.get(Player2ID);
            Match newMatch = new Match(Player1,player1wins,Player2,player2wins);

            //update player scores
            Player p1 = new Player("DNE",0,null,0);
            Player p2 = new Player("DNE",0,null,0);
            for (Player p:this.players) {
                if(Player1.equals(p.tag)){
                    p1 = p;
                }
                if(Player2.equals(p.tag)){
                    p2 = p;
                }
            }
            if(p1.getTag().equals("DNE")){
                p1 = new Player(Player1,startingScore);
            }
            if(p2.getTag().equals("DNE")){
                p2 = new Player(Player2,startingScore);
            }

            if(p1.getTag().equals(newMatch.getWinner())){
                p1.updateScores(p2,true);
            }
            for (Player p:this.players) {
                if(p.getTag().equals(p1.tag)){
                    p.setScore(p1.score);
                }
                if(p.getTag().equals(p2.tag)){
                    p.setScore(p2.score);
                }
            }
            if(!this.players.contains(p1)){
                this.players.add(p1);
            }
            if(!this.players.contains(p2)){
                this.players.add(p2);
            }

            //System.out.println("Match: " + Player1 + " " + Player2 + " " + player1wins + " " + player2wins);
            returnList.add(newMatch);
        }
        return returnList;
    }

    public void recalculateSeason(String game){
        for (Player p : this.players) {
            p.setScore(p.initialScore);
        }
        for (String tournament:this.tournaments) {
            try {
                FileReader fr = new FileReader("Data/" + game + "/Tournaments/CSVFiles/" + tournament + ".csv");
                BufferedReader br = new BufferedReader(fr);
                String line;
                int lineCnt = 0;
                ArrayList<Match> matches = new ArrayList<>();
                while((line = br.readLine()) != null){
                    lineCnt++;
                    String[] theMatch = line.split(",");
                    if(lineCnt > 2){
                        Match match = new Match(theMatch[0], Integer.parseInt(theMatch[1]), theMatch[2], Integer.parseInt(theMatch[3]));
                        matches.add(match);
                    }
                }
                br.close();
                fr.close();
                for (Match m:matches) {
                    Player p1 = new Player("null", 0);
                    Player p2 = new Player("null", 0);
                    for (Player p:players) {
                        if(p.getTag().equals(m.player1Tag)){
                            p1 = p;
                        }
                        if(p.getTag().equals(m.player2Tag)){
                            p2 = p;
                        }
                    }
                    if(p1.getTag().equals("null") || p2.getTag().equals("null")){
                        System.out.println("Error Finding Player");
                    }
                    if(p1.getTag().equals(m.getWinner())){
                        p1.updateScores(p2, true);
                    } else {
                        p2.updateScores(p1, true);
                    }
                    for (Player p:this.players) {
                        if(p.getTag().equals(p1.tag)){
                            p.setScore(p1.score);
                        }
                        if(p.getTag().equals(p2.tag)){
                            p.setScore(p2.score);
                        }
                    }
                }
            }catch (IOException ioe){
                System.out.println("Could not read CSV file");
            }
            writeSeason(game);
        }
    }

    public String getPlacings(String tournamentString){
        String top3 = "";
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(tournamentString);
            JSONObject theTournament = (JSONObject) object.get("tournament");
            JSONArray participants = (JSONArray) theTournament.get("participants");
            String firstPlace = "";
            String secondPlace = "";
            String thirdPlace = "";
            //System.out.println(participants.size());
            for (Object participant:participants) {
                JSONObject obj = (JSONObject) participant;
                JSONObject player = (JSONObject) obj.get("participant");
                //System.out.println((String) player.get("display_name"));
                Long placement = (Long) player.get("final_rank");
                int playerPlacement = placement.intValue();
                //System.out.println(playerPlacement);
                if(playerPlacement == 1){
                   firstPlace = (String) player.get("display_name");
                }
                if(playerPlacement == 2){
                    secondPlace = (String) player.get("display_name");
                }
                if(playerPlacement == 3){
                    thirdPlace = (String) player.get("display_name");
                }
            }
            top3 = firstPlace + "," + secondPlace + "," + thirdPlace;
        } catch (ParseException pe){
            System.out.println("Could not parse tournament");
        }
        return top3;
    }

    public HashMap<String,Integer[]> getSetCounts(String player, String game){
        HashMap<String,Integer[]> SetCounts = new HashMap<>();
        for (String tournament:tournaments) {
            try {
                String fileLocation = "Data/" + game + "/Tournaments/CSVFiles/" + tournament + ".csv";
                FileReader fr = new FileReader(fileLocation);
                BufferedReader br = new BufferedReader(fr);
                String line;
                int lineCnt = 0;
                while((line = br.readLine()) != null){
                    lineCnt++;
                    if(lineCnt > 2){
                        String[] matchString = line.split(",");
                        Match match = new Match(matchString[0],Integer.parseInt(matchString[1]),matchString[2],Integer.parseInt(matchString[3]));
                        if(match.containsPlayer(player)){
                            String otherPlayer;
                            if(match.player1Tag.equals(player)){
                                otherPlayer = match.player2Tag;
                            } else{
                                otherPlayer = match.player1Tag;
                            }
                            if(SetCounts.containsKey(otherPlayer)){
                                Integer[] SetCount = SetCounts.get(otherPlayer);
                                if(match.getWinner().equals(player)){
                                    SetCount[0] = SetCount[0] + 1;
                                } else {
                                    SetCount[1] = SetCount[1] + 1;
                                }
                                SetCounts.replace(otherPlayer,SetCount);
                            } else{
                                Integer[] NewSetCount = new Integer[2];
                                if(match.getWinner().equals(player)){
                                    NewSetCount[0] = 1;
                                    NewSetCount[1] = 0;
                                } else {
                                    NewSetCount[0] = 0;
                                    NewSetCount[1] = 1;
                                }
                                SetCounts.put(otherPlayer,NewSetCount);
                            }
                        }
                    }
                }
            }catch (IOException ioe){}
        }
        return SetCounts;
    }

    public void combinePlayers(String game, String basePlayer, String mergePlayer){
        for (String tournament:tournaments) {
            try {
                String fileLocation = "Data/" + game + "/Tournaments/CSVFiles/" + tournament + ".csv";
                FileReader fr = new FileReader(fileLocation);
                BufferedReader br = new BufferedReader(fr);
                ArrayList<String> lines = new ArrayList<>();
                String line;
                int lineCnt = 0;
                while((line = br.readLine()) != null){
                    lineCnt++;
                    if(lineCnt > 2){
                        String[] matchString = line.split(",");
                        if(matchString[0].equals(mergePlayer) || matchString[2].equals(mergePlayer)){
                            String newLine = line.replace(mergePlayer, basePlayer);
                            lines.add(newLine);
                        } else {
                            lines.add(line);
                        }
                    } else {
                        lines.add(line);
                    }
                }
                br.close();
                fr.close();

                FileWriter fw = new FileWriter(fileLocation);
                BufferedWriter bw = new BufferedWriter(fw);

                for (String newLine:lines) {
                    bw.write(newLine);
                    bw.newLine();
                }

                bw.close();
                fw.close();

                Player removePlayer = new Player("DNE",0);
                for (Player p:players) {
                    if(p.tag.equals(mergePlayer)){
                        removePlayer = p;
                    }
                }
                if(removePlayer.tag.equals(mergePlayer)){
                    players.remove(removePlayer);
                }
                writeSeason(game);
            } catch (IOException ioe) {
            }
        }
    }
}
