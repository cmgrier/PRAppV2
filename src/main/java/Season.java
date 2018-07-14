import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Season {
    ArrayList<Player> players;
    ArrayList<String> tournaments;
    String name;

    double startingScore = 1600; //the starting score for new players

    Season(String name, ArrayList<String> tournaments,ArrayList<Player> players){
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
            //creates file for CSV where we will store name of tournament and all matches
            String csvFileLocation = "Data/" + game + "/Tournaments/CSVFiles/" + tournamentName + ".csv";
            FileWriter CSVWriter = new FileWriter(csvFileLocation);
            BufferedWriter BCSVWriter = new BufferedWriter(CSVWriter);
            BCSVWriter.write(tournamentName);

            //gets matches from JSON file and writes them into CSV
            ArrayList<Match> matches = new ArrayList<>();
            matches = readMatches(tournament);
            for (Match m:matches) {
                BCSVWriter.newLine();
                BCSVWriter.write(m.toString());
            }
            BCSVWriter.close();
            CSVWriter.close();

            String seasonFileLocation = "Data/" + game + "/Seasons/" + this.name + ".csv";
//            FileReader seasonReader = new FileReader(seasonFileLocation);
//            BufferedReader BseasonReader = new BufferedReader(seasonReader);
//            BseasonReader.close();
//            seasonReader.close();

            FileWriter seasonWriter = new FileWriter(seasonFileLocation);
            BufferedWriter BseasonWriter = new BufferedWriter(seasonWriter);
            BseasonWriter.write(this.name);
            BseasonWriter.newLine();
            String tournamentString = "";
            for (String s:tournaments) {
                tournamentString += s;
                tournamentString += ",";
            }
            tournamentString.substring(0,(tournamentString.length()-2));
            BseasonWriter.write(tournamentString);
            for (Player p:players) {
                BseasonWriter.newLine();
                String playerString = p.tag + "," + p.score + "," + convertCharacters(p.characters) + "," + p.initialScore;
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

    private String convertCharacters(ArrayList<String> characters){
        String returnString = "";
        for (String s:characters) {
            returnString += s + ".";
        }
        return returnString;
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
            int player1wins = Integer.parseInt(score[0]);
            int player2wins = Integer.parseInt(score[1]);
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

}
