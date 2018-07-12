import org.json.simple.JSONObject;

import java.util.ArrayList;


public class Player {
    String tag;
    double score;
    ArrayList<String> characters;

    private static final int K = 24;

    Player(String tag, double score){
        this.tag = tag;
        this.score = score;
        this.characters = new ArrayList<String>();
    }

    Player(String tag, double score, ArrayList<String> characters){
        this.tag = tag;
        this.score = score;
        this.characters = characters;
        JSONObject obj;
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
