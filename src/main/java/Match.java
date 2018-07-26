public class Match {
    String player1Tag;
    String player2Tag;
    int player1Score;
    int player2Score;

    public Match(String player1Tag, int player1Score, String player2Tag, int player2Score){
        this.player1Tag = player1Tag;
        this.player1Score = player1Score;
        this.player2Tag = player2Tag;
        this.player2Score = player2Score;
    }

    public String toString(){
        String returnString = player1Tag + "," + player1Score + "," + player2Tag + "," + player2Score;
        return returnString;
    }

    public String getWinner(){
        if(player1Score > player2Score){
            return player1Tag;
        } else {
            return player2Tag;
        }
    }

    public boolean containsPlayer(String player){
        if(player.equals(player1Tag) || player.equals(player2Tag)){
            return true;
        } else {
            return false;
        }
    }
}
