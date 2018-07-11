import org.json.simple.JSONObject;

import java.util.ArrayList;


public class Player {
    String tag;
    double score;
    ArrayList<String> characters;

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

}
