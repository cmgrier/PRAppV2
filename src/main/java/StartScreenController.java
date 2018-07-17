import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable{

    @FXML
    TextField TournamentText, newSeasonTitle;

    @FXML
    Button AddTournamentButton, AddSeasonButton;

    @FXML
    ComboBox<String> ChangeSeason, ChangeGame;

    @FXML
    Label CurrentGame, CurrentSeason;

    @FXML
    Label FirstP1,SecondP1,ThirdP1,FirstP2,SecondP2,ThirdP2,FirstP3,SecondP3,ThirdP3,FirstP4,SecondP4,ThirdP4,FirstP5,SecondP5,ThirdP5,
            FirstP6,SecondP6,ThirdP6,FirstP7,SecondP7,ThirdP7,FirstP8,SecondP8,ThirdP8,FirstP9,SecondP9,ThirdP9,FirstP10,SecondP10,ThirdP10 = new Label();

    @FXML
    Label PlayerName1,PlayerName2,PlayerName3,PlayerName4,PlayerName5,PlayerName6,PlayerName7,PlayerName8,PlayerName9,PlayerName10 = new Label();

    @FXML
    Label Sponser1,Sponser2,Sponser3,Sponser4,Sponser5,Sponser6,Sponser7,Sponser8,Sponser9,Sponser10 = new Label();

    @FXML
    ImageView Characters11, Characters12, Characters13, Characters21, Characters22, Characters23, Characters31, Characters32, Characters33 ,
            Characters41, Characters42, Characters43, Characters51, Characters52, Characters53, Characters61, Characters62, Characters63,
            Characters71, Characters72, Characters73, Characters81, Characters82, Characters83, Characters91, Characters92, Characters93,
            Characters101, Characters102, Characters103 = new ImageView();

    //Character images below
    private Image Miigunner = new Image("CharacterIcons/stock_90_miigunner_01.png");
    private Image Bayo = new Image("CharacterIcons/stock_90_bayonetta_01.png");
    private Image Falcon = new Image("CharacterIcons/stock_90_captain_01.png");
    private Image Cloud = new Image("CharacterIcons/stock_90_cloud_01.png");
    private Image DeDeDe = new Image("CharacterIcons/stock_90_dedede_01.png");
    private Image Diddy = new Image("CharacterIcons/stock_90_diddy_01.png");
    private Image DK = new Image("CharacterIcons/stock_90_donkey_01.png");
    private Image DrMario = new Image("CharacterIcons/stock_90_drmario_01.png");
    private Image DuckHunt = new Image("CharacterIcons/stock_90_duckhunt_01.png");
    private Image Falco = new Image("CharacterIcons/stock_90_falco_01.png");
    private Image Fox = new Image("CharacterIcons/stock_90_fox_01.png");
    private Image GameAndWatch = new Image("CharacterIcons/stock_90_gamewatch_01.png");
    private Image Ganon = new Image("CharacterIcons/stock_90_ganon_01.png");
    private Image Greninja = new Image("CharacterIcons/stock_90_gekkouga_01.png");
    private Image Ike = new Image("CharacterIcons/stock_90_ike_01.png");
    private Image Corrin = new Image("CharacterIcons/stock_90_kamui_01.png");
    private Image Kirby = new Image("CharacterIcons/stock_90_kirby_01.png");
    private Image Bowser = new Image("CharacterIcons/stock_90_koopa_01.png");
    private Image BowserJr = new Image("CharacterIcons/stock_90_koopajr_01.png");
    private Image Link = new Image("CharacterIcons/stock_90_link_01.png");
    private Image LittleMac = new Image("CharacterIcons/stock_90_littlemac_01.png");
    private Image Charizard = new Image("CharacterIcons/stock_90_lizardon_01.png");
    private Image Lucario = new Image("CharacterIcons/stock_90_lucario_01.png");
    private Image Lucas = new Image("CharacterIcons/stock_90_lucas_01.png");
    private Image Lucina = new Image("CharacterIcons/stock_90_lucina_01.png");
    private Image Luigi = new Image("CharacterIcons/stock_90_luigi_01.png");
    private Image Mario = new Image("CharacterIcons/stock_90_mario_01.png");
    private Image Marth = new Image("CharacterIcons/stock_90_marth_01.png");
    private Image MetaKnight = new Image("CharacterIcons/stock_90_metaknight_01.png");
    private Image MewTwo = new Image("CharacterIcons/stock_90_mewtwo_01.png");
    private Image MiiBrawler = new Image("CharacterIcons/stock_90_miifighter_01.png");
    private Image MiiSwordfighter = new Image("CharacterIcons/stock_90_miiswordsman_01.png");
    private Image Villager = new Image("CharacterIcons/stock_90_murabito_01.png");
    private Image Ness = new Image("CharacterIcons/stock_90_ness_01.png");
    private Image Pacman = new Image("CharacterIcons/stock_90_pacman_01.png");
    private Image Palutena = new Image("CharacterIcons/stock_90_palutena_01.png");
    private Image Peach = new Image("CharacterIcons/stock_90_peach_01.png");
    private Image Pikachu = new Image("CharacterIcons/stock_90_pikachu_01.png");
    private Image Olimar = new Image("CharacterIcons/stock_90_pikmin_01.png");
    private Image Pit = new Image("CharacterIcons/stock_90_pit_01.png");
    private Image DarkPit = new Image("CharacterIcons/stock_90_pitb_01.png");
    private Image JigglyPuff = new Image("CharacterIcons/stock_90_purin_01.png");
    private Image Robin = new Image("CharacterIcons/stock_90_reflet_01.png");
    private Image Rob = new Image("CharacterIcons/stock_90_robot_01.png");
    private Image MegaMan = new Image("CharacterIcons/stock_90_rockman_01.png");
    private Image Rosalina = new Image("CharacterIcons/stock_90_rosetta_01.png");
    private Image Roy = new Image("CharacterIcons/stock_90_roy_01.png");
    private Image Ryu = new Image("CharacterIcons/stock_90_ryu_01.png");
    private Image Samus = new Image("CharacterIcons/stock_90_samus_01.png");
    private Image Sheik = new Image("CharacterIcons/stock_90_sheik_01.png");
    private Image Shulk = new Image("CharacterIcons/stock_90_shulk_01.png");
    private Image Sonic = new Image("CharacterIcons/stock_90_sonic_01.png");
    private Image ZeroSuitSamus = new Image("CharacterIcons/stock_90_szerosuit_01.png");
    private Image ToonLink = new Image("CharacterIcons/stock_90_toonlink_01.png");
    private Image Wario = new Image("CharacterIcons/stock_90_wario_01.png");
    private Image WiiFit = new Image("CharacterIcons/stock_90_wiifit_01.png");
    private Image Yoshi = new Image("CharacterIcons/stock_90_yoshi_01.png");
    private Image Zelda = new Image("CharacterIcons/stock_90_zelda_01.png");
    private Image Random = new Image("CharacterIcons/stock_90_omakase_01.png");

    String defaultGame = "Smash4";
    String defaultSeason = "TestSeason";

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // do all things here that happen when this screen is started
        CurrentGame.setText(defaultGame);
        CurrentSeason.setText(defaultSeason);
    }

    public void createSeason(){
        String game = CurrentGame.getText();
        if(newSeasonTitle.getText() != null) {
            String seasonTitle = newSeasonTitle.getText();
            try {
                FileWriter fw = new FileWriter("Data/" + game + "/Seasons/" + seasonTitle);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(seasonTitle);
                bw.close();
                fw.close();
            } catch (IOException ioe) {
                System.out.println("couldn't create season");
            }
        }
    }

    public void changeSeason(){
        //todo update the top ten with the new season
    }

    public void addTournament(){
        String game = CurrentGame.getText();
        String seasonTitle = CurrentSeason.getText();
        try {
            JSONObject tourney = (JSONObject) new JSONParser().parse(TournamentText.getText());
            JSONObject tournament = (JSONObject) tourney.get("tournament");
            String tournamentName = (String) tournament.get("name");
            FileReader fr = new FileReader("Data/" + game + "/Seasons/" + seasonTitle);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineCnt = 0;
            ArrayList<String> tournamentList = new ArrayList<>();
            ArrayList<Player> players = new ArrayList<>();
            while((line = br.readLine()) != null){
                lineCnt++;
                if(lineCnt == 2){
                    String[] tourneyList = line.split(",");
                    for (String s:tourneyList) {
                        tournamentList.add(s);
                    }
                } else {
                    String[] thePlayer = line.split(",");
                    String[] theCharacters = thePlayer[2].split(".");
                    ArrayList<String> characters = new ArrayList<>();
                    for (String s:theCharacters) {
                        characters.add(s);
                    }
                    Player p = new Player(thePlayer[0],Double.parseDouble(thePlayer[1]),characters,Double.parseDouble(thePlayer[3]));
                    players.add(p);
                }
            }
            Season s = new Season(seasonTitle,tournamentList,players);
            if(!TournamentText.getText().isEmpty() && !tournamentList.contains(tournamentName)){
                s.addTournament(TournamentText.getText(), game);
                s.writeSeason(game);
            }

        }catch (IOException ioe){}
        catch (ParseException pe){}
    }

}
