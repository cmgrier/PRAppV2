import java.util.ArrayList;

public class Season {
    ArrayList<Player> players;
    ArrayList<String> tournaments;
    String name;

    Season(String name, ArrayList<String> tournaments,ArrayList<Player> players){
        this.name = name;
        this.tournaments = tournaments;
        this.players = players;
    }
}
