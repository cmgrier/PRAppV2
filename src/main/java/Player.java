import org.json.simple.JSONObject;

import java.util.ArrayList;


public class Player {
    String tag;
    double score;
    ArrayList<String> characters;
    double initialScore;

    private static final int K = 24;

    Player(String tag, double score){
        this.tag = tag;
        this.score = score;
        this.characters = new ArrayList<String>();
        this.initialScore = score;
    }

    Player(String tag, double score, ArrayList<String> characters){
        this.tag = tag;
        this.score = score;
        this.characters = characters;
        this.initialScore = score;
    }

    Player(String tag, double score, ArrayList<String> characters, double initialScore){
        this.tag = tag;
        this.score = score;
        this.characters = characters;
        this.initialScore = initialScore;
    }

    //updates the scores of the player object and the opposing player. uses ELO with a K value of 24
    public void updateScores(Player opponent, boolean won){
        double R1 = Math.pow(10, this.score / 400.0);
        double R2 = Math.pow(10, opponent.getScore() / 400.0);
        double E1 = R1 / (R1 + R2);
        double E2 = R2 / (R1 + R2);
        int S1,S2;
        if(won){
            S1 = 1;
            S2 = 0;
        } else{
            S1 = 0;
            S2 = 1;
        }
        double N1 = this.score + K * (S1 - E1);
        double N2 = opponent.score + K * (S2 - E2);
        setScore(N1);
        opponent.setScore(N2);
    }

    public ArrayList<String> separateCharacters(String characters) {
        System.out.println(characters);
        ArrayList<String> returnList = new ArrayList<>();
        ArrayList<Character> charArray = new ArrayList<>();
        for(int i = 0; i < characters.length();i++){
            charArray.add(characters.charAt(i));
        }
        String tempString = "";
        for(int i = 0; i < charArray.size(); i++){
            char currentChar = charArray.get(i);
            if(currentChar == '.'){
                returnList.add(tempString);
                tempString = "";
            } else {
                tempString += currentChar;
            }
        }
        if(!tempString.isEmpty()){
            returnList.add(tempString);
        }
        //String[] characterList = characters.split(".");
        //System.out.println(characterList[0]);
        System.out.println(returnList);
        return returnList;
    }

    public String characterString(){
        String characters = "";
        for (String s:this.characters) {
            characters = characters + s + ".";
        }
        return characters.substring(0,characters.length()-2);
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public double getScore() {
        return score;
    }

    public String getTag() {
        return tag;
    }

    public void setCharacters(ArrayList<String> characters) {
        this.characters = characters;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
