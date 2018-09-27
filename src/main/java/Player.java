import org.json.simple.JSONObject;

import java.util.ArrayList;


public class Player implements Comparable<Player>{
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

    //updates the scores of the player object and the opposing player. uses ELO with a K value of whatever the default value is
    public void updateScores(Player opponent, boolean won, int K){
        if(this.tag.contains("Bye") || opponent.tag.contains("Bye")){

        }else {
            double R1 = Math.pow(10, this.score / 400.0);
            double R2 = Math.pow(10, opponent.getScore() / 400.0);
            double E1 = R1 / (R1 + R2);
            double E2 = R2 / (R1 + R2);
            int S1, S2;
            if (won) {
                S1 = 1;
                S2 = 0;
            } else {
                S1 = 0;
                S2 = 1;
            }
            double N1 = this.score + K * (S1 - E1);
            double N2 = opponent.score + K * (S2 - E2);
            setScore(N1);
            opponent.setScore(N2);
        }
    }

    // additional points for placing well at tournaments (even of big sizes) as well as just attending.
    public void attendanceBoost(int placement, int totalEntrants){
        this.score = this.score + totalEntrants/placement;
    }

    public ArrayList<String> separateCharacters(String characters) {
        ArrayList<String> returnList = new ArrayList<>();
        if(characters.equals(":")){
            return returnList;
        } else {
            ArrayList<Character> charArray = new ArrayList<>();
            for (int i = 0; i < characters.length(); i++) {
                charArray.add(characters.charAt(i));
            }
            String tempString = "";
            for (int i = 0; i < charArray.size(); i++) {
                char currentChar = charArray.get(i);
                if (currentChar == ':') {
                    returnList.add(tempString);
                    tempString = "";
                } else {
                    tempString += currentChar;
                }
            }
            if (!tempString.isEmpty()) {
                returnList.add(tempString);
            }
            return returnList;
        }
    }

    public String characterString(){
        String characters = "";
        for (String s:this.characters) {
            characters = characters + s + ":";
        }
        if(characters.length()>1) {
            return characters.substring(0, characters.length() - 1);
        } else if(characters.equals(":")) {
            return "";
        } else {
            return characters;
        }
    }

    @Override
    public int compareTo(Player player){
        return (int)(this.score - player.getScore());
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

    public void setInitialScore(double initialScore){
        this.initialScore = initialScore;
    }
}
