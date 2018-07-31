import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.*;

public class StartScreenController implements Initializable{

    @FXML
    TextField TournamentText, newSeasonTitle, NewPlayerTag, PlayerScore, ChangeTitle, InitialScore;

    @FXML
    Button AddTournamentButton, AddSeasonButton;

    @FXML
    ComboBox<String> ChangeSeason, ChangeGame, SelectPlayer, SelectPlayerStatistics, FirstCharacter, SecondCharacter, ThirdCharacter, DefaultSeasonBox, BasePlayer, MergePlayer;

    @FXML
    Label CurrentGame, CurrentSeason, TitleText, WinLoss, WinPercentage, TourneysEntered;

    @FXML
    ListView SetList, TournamentList;

    @FXML
    Label FirstP1,SecondP1,ThirdP1,FirstP2,SecondP2,ThirdP2,FirstP3,SecondP3,ThirdP3,FirstP4,SecondP4,ThirdP4,FirstP5,SecondP5,ThirdP5,
            FirstP6,SecondP6,ThirdP6,FirstP7,SecondP7,ThirdP7,FirstP8,SecondP8,ThirdP8,FirstP9,SecondP9,ThirdP9,FirstP10,SecondP10,ThirdP10 = new Label();

    @FXML
    Label PlayerName1,PlayerName2,PlayerName3,PlayerName4,PlayerName5,PlayerName6,PlayerName7,PlayerName8,PlayerName9,PlayerName10 = new Label();

    @FXML
    Label P1WR, P2WR, P3WR, P4WR, P5WR, P6WR, P7WR, P8WR, P9WR, P10WR;

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
        readSettings();
        CurrentGame.setText(defaultGame);
        CurrentSeason.setText(defaultSeason);
        updateSeasonList();
        fillCharacterList();
        fillPlayerBox();
        updateTopTen();
        updateCharactersAndPlacings();
        updateTournamentList();
        WinLoss.setVisible(false);
        WinPercentage.setVisible(false);
        TourneysEntered.setVisible(false);
    }

    private void readSettings(){
        try {
            FileReader fr = new FileReader("Data/Settings");
            BufferedReader br = new BufferedReader(fr);
            int lineCnt = 0;
            String line;
            while((line = br.readLine()) != null){
                lineCnt++;
                if(lineCnt == 1){
                    TitleText.setText(line);
                }
                if(lineCnt == 2){
                    defaultSeason = line;
                }
                if(lineCnt == 3){
                    defaultGame = line;
                }
            }
            br.close();
            fr.close();

        }catch (IOException ioe){}
    }

    public void updateSeasonList(){
        File[] seasonFiles = new File("Data/" + CurrentGame.getText() + "/Seasons").listFiles();
        ArrayList<String> seasonTitles = new ArrayList<>();
        for (File seasonFile:seasonFiles) {
            seasonTitles.add(seasonFile.getName());
        }
        ChangeSeason.getItems().clear();
        ChangeSeason.getItems().addAll(seasonTitles);
        DefaultSeasonBox.getItems().clear();
        DefaultSeasonBox.getItems().addAll(seasonTitles);
    }

    public void fillCharacterList(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Bayonetta");
        options.add("Falcon");
        options.add("Cloud");
        options.add("DeDeDe");
        options.add("Diddy");
        options.add("DK");
        options.add("DrMario");
        options.add("DuckHunt");
        options.add("Falco");
        options.add("Fox");
        options.add("GameAndWatch");
        options.add("Ganon");
        options.add("Greninja");
        options.add("Ike");
        options.add("Corrin");
        options.add("Kirby");
        options.add("Bowser");
        options.add("BowserJr");
        options.add("Link");
        options.add("LittleMac");
        options.add("Charizard");
        options.add("Lucario");
        options.add("Lucas");
        options.add("Lucina");
        options.add("Luigi");
        options.add("Mario");
        options.add("Marth");
        options.add("MetaKnight");
        options.add("MewTwo");
        options.add("MiiBrawler");
        options.add("MiiGunner");
        options.add("MiiSwordfighter");
        options.add("Villager");
        options.add("Ness");
        options.add("Pacman");
        options.add("Palutena");
        options.add("Peach");
        options.add("Pikachu");
        options.add("Olimar");
        options.add("Pit");
        options.add("DarkPit");
        options.add("JigglyPuff");
        options.add("Robin");
        options.add("MegaMan");
        options.add("Rob");
        options.add("Rosalina");
        options.add("Roy");
        options.add("Ryu");
        options.add("Samus");
        options.add("Sheik");
        options.add("Shulk");
        options.add("Sonic");
        options.add("ZeroSuitSamus");
        options.add("ToonLink");
        options.add("Wario");
        options.add("WiiFit");
        options.add("Yoshi");
        options.add("Zelda");

        options.add("<Clear>");

        FirstCharacter.getItems().clear();
        SecondCharacter.getItems().clear();
        ThirdCharacter.getItems().clear();
        Collections.sort(options);
        FirstCharacter.getItems().addAll(options);
        SecondCharacter.getItems().addAll(options);
        ThirdCharacter.getItems().addAll(options);
    }

    public void createSeason(){
        String game = CurrentGame.getText();
        if(newSeasonTitle.getText() != null) {
            String seasonTitle = newSeasonTitle.getText();
            try {
                FileWriter fw = new FileWriter("Data/" + game + "/Seasons/" + seasonTitle + ".csv");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(seasonTitle);
                bw.close();
                fw.close();
            } catch (IOException ioe) {
                System.out.println("couldn't create season");
            }
        }
        updateSeasonList();
        updateTournamentList();
    }

    public void changeSeason(){
        Season newSeason = getSeason(CurrentGame.getText(),ChangeSeason.getValue());
        CurrentSeason.setText(newSeason.name);
        updateCharactersAndPlacings();
        updateTopTen();
        fillPlayerBox();
        updateTournamentList();
        WinLoss.setVisible(false);
        WinPercentage.setVisible(false);
        TourneysEntered.setVisible(false);
        SetList.getItems().clear();
        update();
    }

    public void updateTournamentList(){
        TournamentList.getItems().clear();
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        TournamentList.getItems().addAll(s.tournaments);
    }

    public void changeGame(){
        //Todo
    }

    public void updateTopTen(){
        ArrayList<Player> players = getSeason(CurrentGame.getText(),CurrentSeason.getText()).orderedList();
        clearTable();
        PlayerName10.setText(players.get(9).tag);
        PlayerName9.setText(players.get(8).tag);
        PlayerName8.setText(players.get(7).tag);
        PlayerName7.setText(players.get(6).tag);
        PlayerName6.setText(players.get(5).tag);
        PlayerName5.setText(players.get(4).tag);
        PlayerName4.setText(players.get(3).tag);
        PlayerName3.setText(players.get(2).tag);
        PlayerName2.setText(players.get(1).tag);
        PlayerName1.setText(players.get(0).tag);
    }

    private void updateCharactersAndPlacings(){
        Season season = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        Player p1 = season.orderedList().get(0);
        if(p1.tag.equals("")){
            Characters11.setImage(null);
            Characters12.setImage(null);
            Characters13.setImage(null);
            FirstP1.setText(null);
            SecondP1.setText(null);
            ThirdP1.setText(null);
        } else{
            if(p1.getCharacters().size() > 1){
                Characters12.setImage(getCharacterImage(p1.getCharacters().get(1)));
            } else {
                Characters12.setImage(null);
            }
            if(p1.getCharacters().size() > 2){
                Characters13.setImage(getCharacterImage(p1.getCharacters().get(2)));
            } else {
                Characters13.setImage(null);
            }

            Characters11.setImage(getCharacterImage(p1.getCharacters().get(0)));
            // null for now
            FirstP1.setText(null);
            SecondP1.setText(null);
            ThirdP1.setText(null);
        }
        Player p2 = season.orderedList().get(1);
        if(p2.tag.equals("")){
            Characters21.setImage(null);
            Characters22.setImage(null);
            Characters23.setImage(null);
            FirstP2.setText(null);
            SecondP2.setText(null);
            ThirdP2.setText(null);
        } else{
            Characters21.setImage(getCharacterImage(p2.getCharacters().get(0)));
            if(p2.getCharacters().size() > 1){
                Characters22.setImage(getCharacterImage(p2.getCharacters().get(1)));
            } else {
                Characters22.setImage(null);
            }
            if(p2.getCharacters().size() > 2){
                Characters23.setImage(getCharacterImage(p2.getCharacters().get(2)));
            } else {
                Characters23.setImage(null);
            }
            //null for now
            FirstP2.setText(null);
            SecondP2.setText(null);
            ThirdP2.setText(null);
        }
        Player p3 = season.orderedList().get(2);
        if(p3.tag.equals("")){
            Characters31.setImage(null);
            Characters32.setImage(null);
            Characters33.setImage(null);
            FirstP3.setText(null);
            SecondP3.setText(null);
            ThirdP3.setText(null);
        } else{
            Characters31.setImage(getCharacterImage(p3.getCharacters().get(0)));
            if(p3.getCharacters().size() > 1){
                Characters32.setImage(getCharacterImage(p3.getCharacters().get(1)));
            } else {
                Characters32.setImage(null);
            }
            if(p3.getCharacters().size() > 2){
                Characters33.setImage(getCharacterImage(p3.getCharacters().get(2)));
            } else {
                Characters33.setImage(null);
            }
            // null for now
            FirstP3.setText(null);
            SecondP3.setText(null);
            ThirdP3.setText(null);
        }
        Player p4 = season.orderedList().get(3);
        if(p4.tag.equals("")){
            Characters41.setImage(null);
            Characters42.setImage(null);
            Characters43.setImage(null);
            FirstP4.setText(null);
            SecondP4.setText(null);
            ThirdP4.setText(null);
        } else{
            Characters41.setImage(getCharacterImage(p4.getCharacters().get(0)));
            if(p4.getCharacters().size() > 1){
                Characters42.setImage(getCharacterImage(p4.getCharacters().get(1)));
            } else {
                Characters42.setImage(null);
            }
            if(p4.getCharacters().size() > 2){
                Characters43.setImage(getCharacterImage(p4.getCharacters().get(2)));
            } else {
                Characters43.setImage(null);
            }
            //null for now
            FirstP4.setText(null);
            SecondP4.setText(null);
            ThirdP4.setText(null);
        }
        Player p5 = season.orderedList().get(4);
        if(p5.tag.equals("")){
            Characters51.setImage(null);
            Characters52.setImage(null);
            Characters53.setImage(null);
            FirstP5.setText(null);
            SecondP5.setText(null);
            ThirdP5.setText(null);
        } else{
            Characters51.setImage(getCharacterImage(p5.getCharacters().get(0)));
            if(p5.getCharacters().size() > 1){
                Characters52.setImage(getCharacterImage(p5.getCharacters().get(1)));
            } else {
                Characters52.setImage(null);
            }
            if(p5.getCharacters().size() > 2){
                Characters53.setImage(getCharacterImage(p5.getCharacters().get(2)));
            } else {
                Characters53.setImage(null);
            }
            //null for now
            FirstP5.setText(null);
            SecondP5.setText(null);
            ThirdP5.setText(null);
        }
        Player p6 = season.orderedList().get(5);
        if(p6.tag.equals("")){
            Characters61.setImage(null);
            Characters62.setImage(null);
            Characters63.setImage(null);
            FirstP6.setText(null);
            SecondP6.setText(null);
            ThirdP6.setText(null);
        } else{
            Characters61.setImage(getCharacterImage(p6.getCharacters().get(0)));
            if(p6.getCharacters().size() > 1){
                Characters62.setImage(getCharacterImage(p6.getCharacters().get(1)));
            } else {
                Characters62.setImage(null);
            }
            if(p6.getCharacters().size() > 2){
                Characters63.setImage(getCharacterImage(p6.getCharacters().get(2)));
            } else {
                Characters63.setImage(null);
            }
            //null for now
            FirstP6.setText(null);
            SecondP6.setText(null);
            ThirdP6.setText(null);
        }
        Player p7 = season.orderedList().get(6);
        if(p7.tag.equals("")){
            Characters71.setImage(null);
            Characters72.setImage(null);
            Characters73.setImage(null);
            FirstP7.setText(null);
            SecondP7.setText(null);
            ThirdP7.setText(null);
        } else{
            Characters71.setImage(getCharacterImage(p7.getCharacters().get(0)));
            if(p7.getCharacters().size() > 1){
                Characters72.setImage(getCharacterImage(p7.getCharacters().get(1)));
            } else {
                Characters72.setImage(null);
            }
            if(p7.getCharacters().size() > 2){
                Characters73.setImage(getCharacterImage(p7.getCharacters().get(2)));
            } else {
                Characters73.setImage(null);
            }
            //null for now
            FirstP7.setText(null);
            SecondP7.setText(null);
            ThirdP7.setText(null);
        }
        Player p8 = season.orderedList().get(7);
        if(p8.tag.equals("")){
            Characters81.setImage(null);
            Characters82.setImage(null);
            Characters83.setImage(null);
            FirstP8.setText(null);
            SecondP8.setText(null);
            ThirdP8.setText(null);
        } else{
            Characters81.setImage(getCharacterImage(p8.getCharacters().get(0)));
            if(p8.getCharacters().size() > 1){
                Characters82.setImage(getCharacterImage(p8.getCharacters().get(1)));
            } else {
                Characters82.setImage(null);
            }
            if(p8.getCharacters().size() > 2){
                Characters83.setImage(getCharacterImage(p8.getCharacters().get(2)));
            } else {
                Characters83.setImage(null);
            }
            // null for now
            FirstP8.setText(null);
            SecondP8.setText(null);
            ThirdP8.setText(null);
        }
        Player p9 = season.orderedList().get(8);
        if(p9.tag.equals("")){
            Characters91.setImage(null);
            Characters92.setImage(null);
            Characters93.setImage(null);
            FirstP9.setText(null);
            SecondP9.setText(null);
            ThirdP9.setText(null);
        } else{
            Characters91.setImage(getCharacterImage(p9.getCharacters().get(0)));
            if(p9.getCharacters().size() > 1){
                Characters92.setImage(getCharacterImage(p9.getCharacters().get(1)));
            } else {
                Characters92.setImage(null);
            }
            if(p9.getCharacters().size() > 2){
                Characters93.setImage(getCharacterImage(p9.getCharacters().get(2)));
            } else {
                Characters93.setImage(null);
            }
            //null for now
            FirstP9.setText(null);
            SecondP9.setText(null);
            ThirdP9.setText(null);
        }
        Player p10 = season.orderedList().get(9);
        if(p10.tag.equals("")){
            Characters101.setImage(null);
            Characters102.setImage(null);
            Characters103.setImage(null);
            FirstP10.setText(null);
            SecondP10.setText(null);
            ThirdP10.setText(null);
        } else{
            Characters101.setImage(getCharacterImage(p10.getCharacters().get(0)));
            if(p10.getCharacters().size() > 1){
                Characters102.setImage(getCharacterImage(p10.getCharacters().get(1)));
            } else {
                Characters102.setImage(null);
            }
            if(p10.getCharacters().size() > 2){
                Characters103.setImage(getCharacterImage(p10.getCharacters().get(2)));
            } else {
                Characters103.setImage(null);
            }
            //null for now
            FirstP10.setText(null);
            SecondP10.setText(null);
            ThirdP10.setText(null);
        }
        updatePlacings();
        updateWinRates();
    }

    private Image getCharacterImage(String character){
        Image returnImage = Random;
        if(character.equals("Bayonetta")){
            returnImage = Bayo;
        }
        if(character.equals("Falcon")){
            returnImage = Falcon;
        }
        if(character.equals("Cloud")){
            returnImage = Cloud;
        }
        if(character.equals("DeDeDe")){
            returnImage = DeDeDe;
        }
        if(character.equals("Diddy")){
            returnImage = Diddy;
        }
        if(character.equals("DK")){
            returnImage = DK;
        }
        if(character.equals("DrMario")){
            returnImage = DrMario;
        }
        if(character.equals("DuckHunt")){
            returnImage = DuckHunt;
        }
        if(character.equals("Falco")){
            returnImage = Falco;
        }
        if(character.equals("Fox")){
            returnImage = Fox;
        }
        if(character.equals("GameAndWatch")){
            returnImage = GameAndWatch;
        }
        if(character.equals("Ganon")){
            returnImage = Ganon;
        }
        if(character.equals("Greninja")){
            returnImage = Greninja;
        }
        if(character.equals("Ike")){
            returnImage = Ike;
        }
        if(character.equals("Corrin")){
            returnImage = Corrin;
        }
        if(character.equals("Kirby")){
            returnImage = Kirby;
        }
        if(character.equals("Bowser")){
            returnImage = Bowser;
        }
        if(character.equals("BowserJr")){
            returnImage = BowserJr;
        }
        if(character.equals("Link")){
            returnImage = Link;
        }
        if(character.equals("LittleMac")){
            returnImage = LittleMac;
        }
        if(character.equals("Charizard")){
            returnImage = Charizard;
        }
        if(character.equals("Lucario")){
            returnImage = Lucario;
        }
        if(character.equals("Lucas")){
            returnImage = Lucas;
        }
        if(character.equals("Lucina")){
            returnImage = Lucina;
        }
        if(character.equals("Luigi")){
            returnImage = Luigi;
        }
        if(character.equals("Mario")){
            returnImage = Mario;
        }
        if(character.equals("Marth")){
            returnImage = Marth;
        }
        if(character.equals("MetaKnight")){
            returnImage = MetaKnight;
        }
        if(character.equals("MewTwo")){
            returnImage = MewTwo;
        }
        if(character.equals("MiiGunner")){
            returnImage = Miigunner;
        }
        if(character.equals("MiiBrawler")){
            returnImage = MiiBrawler;
        }
        if(character.equals("MiiSwordfighter")){
            returnImage = MiiSwordfighter;
        }
        if(character.equals("Villager")){
            returnImage = Villager;
        }
        if(character.equals("Ness")){
            returnImage = Ness;
        }
        if(character.equals("Pacman")){
            returnImage = Pacman;
        }
        if(character.equals("Palutena")){
            returnImage = Palutena;
        }
        if(character.equals("Peach")){
            returnImage = Peach;
        }
        if(character.equals("Pikachu")){
            returnImage = Pikachu;
        }
        if(character.equals("Olimar")){
            returnImage = Olimar;
        }
        if(character.equals("Pit")){
            returnImage = Pit;
        }
        if(character.equals("DarkPit")){
            returnImage = DarkPit;
        }
        if(character.equals("JigglyPuff")){
            returnImage = JigglyPuff;
        }
        if(character.equals("Robin")){
            returnImage = Robin;
        }
        if(character.equals("Rob")){
            returnImage = Rob;
        }
        if(character.equals("MegaMan")){
            returnImage = MegaMan;
        }
        if(character.equals("Rosalina")){
            returnImage = Rosalina;
        }
        if(character.equals("Roy")){
            returnImage = Roy;
        }
        if(character.equals("Ryu")){
            returnImage = Ryu;
        }
        if(character.equals("Samus")){
            returnImage = Samus;
        }
        if(character.equals("Sheik")){
            returnImage = Sheik;
        }
        if(character.equals("Shulk")){
            returnImage = Shulk;
        }
        if(character.equals("Sonic")){
            returnImage = Sonic;
        }
        if(character.equals("ZeroSuitSamus")){
            returnImage = ZeroSuitSamus;
        }
        if(character.equals("ToonLink")){
            returnImage = ToonLink;
        }
        if(character.equals("Wario")){
            returnImage = Wario;
        }
        if(character.equals("WiiFit")){
            returnImage = WiiFit;
        }
        if(character.equals("Yoshi")){
            returnImage = Yoshi;
        }
        if(character.equals("Zelda")){
            returnImage = Zelda;
        }
        return returnImage;
    }

    private void clearTable(){
        PlayerName1.setText("");
        PlayerName2.setText("");
        PlayerName3.setText("");
        PlayerName4.setText("");
        PlayerName5.setText("");
        PlayerName6.setText("");
        PlayerName7.setText("");
        PlayerName8.setText("");
        PlayerName9.setText("");
        PlayerName10.setText("");
    }

    public Season getSeason(String game, String seasonTitle){
        Season s = new Season("Could Not Find Season");
        try {
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
                    for (String tournament:tourneyList) {
                        tournamentList.add(tournament);
                    }
                } else if(lineCnt > 2){
                    String[] thePlayer = line.split(",");
                    String[] theCharacters = thePlayer[2].split(":");
                    ArrayList<String> characters = new ArrayList<>();
                    for (String character:theCharacters) {
                        characters.add(character);
                    }
                    Player p = new Player(thePlayer[0],Double.parseDouble(thePlayer[1]),characters,Double.parseDouble(thePlayer[3]));
                    players.add(p);
                }
            }
            s = new Season(seasonTitle,tournamentList,players);
        }catch (IOException ioe){
            System.out.println("Could not read Season");
        }
        return s;
    }

    public void addTournament(){
        String game = CurrentGame.getText();
        String seasonTitle = CurrentSeason.getText();
        try {
            JSONObject tourney = (JSONObject) new JSONParser().parse(TournamentText.getText());
            JSONObject tournament = (JSONObject) tourney.get("tournament");
            String tournamentName = (String) tournament.get("name");
            Season s = getSeason(game,seasonTitle);

            if(!TournamentText.getText().isEmpty() && !s.tournaments.contains(tournamentName)){
                s.addTournament(TournamentText.getText(), game);
                s.writeSeason(game);
            }

        }catch (ParseException pe){}
        updateTopTen();
        updateCharactersAndPlacings();
        updateTournamentList();
        fillPlayerBox();
        TournamentText.clear();
    }

    public void alterPlayer(){
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        for (Player p:s.players) {
            if(p.tag.equals(SelectPlayer.getValue())){
                ArrayList<String> newCharacterList = new ArrayList<>();
                if((FirstCharacter.getValue() != null) || (!FirstCharacter.getValue().equals("<Clear>"))){
                    newCharacterList.add(FirstCharacter.getValue());
                    System.out.println(FirstCharacter.getValue());
                }
                if(((SecondCharacter.getValue() != null) || (!FirstCharacter.getValue().equals("<Clear>"))) && (!FirstCharacter.getValue().equals(SecondCharacter.getValue()))){
                    newCharacterList.add(SecondCharacter.getValue());
                    System.out.println(SecondCharacter.getValue());
                }
                if(((ThirdCharacter.getValue() != null) || (!FirstCharacter.getValue().equals("<Clear>"))) && (!SecondCharacter.getValue().equals(ThirdCharacter.getValue())) && (!FirstCharacter.getValue().equals(ThirdCharacter.getValue()))){
                    newCharacterList.add(ThirdCharacter.getValue());
                    System.out.println(ThirdCharacter.getValue());
                }
                newCharacterList.remove("<Clear>");
                newCharacterList.remove(null);
                newCharacterList.remove("First Character");
                newCharacterList.remove("Second Character");
                newCharacterList.remove("Third Character");
                p.setCharacters(newCharacterList);
                if(PlayerScore.getText() != null){
                    p.setScore(Double.parseDouble(PlayerScore.getText()));
                }
                if(InitialScore.getText() != null){
                    p.setInitialScore(Double.parseDouble(InitialScore.getText()));
                }
            }
        }
        s.writeSeason(CurrentGame.getText());
        updateCharactersAndPlacings();
        updateTopTen();
        fillCharacterList();
    }

    public void addPlayer(){
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        ArrayList<String> newCharacterList = new ArrayList<>();
        if(FirstCharacter.getValue() != null){
            newCharacterList.add(FirstCharacter.getValue());
        }
        if((SecondCharacter.getValue() != null) && (!FirstCharacter.getValue().equals(SecondCharacter.getValue()))){
            newCharacterList.add(SecondCharacter.getValue());
        }
        if((ThirdCharacter.getValue() != null) && (!SecondCharacter.getValue().equals(ThirdCharacter.getValue())) && (!FirstCharacter.getValue().equals(ThirdCharacter.getValue()))){
            newCharacterList.add(ThirdCharacter.getValue());
        }
        Player newPlayer = new Player(NewPlayerTag.getText(), Double.parseDouble(PlayerScore.getText()), newCharacterList);
        s.players.add(newPlayer);
        s.writeSeason(CurrentGame.getText());
        updateCharactersAndPlacings();
        updateTopTen();
        fillCharacterList();
    }

    public void fillPlayerBox(){
        SelectPlayer.getItems().clear();
        BasePlayer.getItems().clear();
        MergePlayer.getItems().clear();
        SelectPlayerStatistics.getItems().clear();
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        ArrayList<String> players = new ArrayList<>();
        for (Player p:s.players) {
            players.add(p.tag);
        }
        SelectPlayer.getItems().addAll(players);
        BasePlayer.getItems().addAll(players);
        MergePlayer.getItems().addAll(players);
        SelectPlayerStatistics.getItems().addAll(players);
    }

    public void fillCharacterBoxes(){
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        for (Player p:s.players) {
            if(p.getTag().equals(SelectPlayer.getValue())){
                FirstCharacter.setValue("First Character");
                SecondCharacter.setValue("Second Character");
                ThirdCharacter.setValue("Third Character");
                if((p.getCharacters().size() > 0) && (!p.getCharacters().get(0).equals(""))){
                    FirstCharacter.setValue(p.characters.get(0));
                }
                if(p.getCharacters().size() > 1){
                    SecondCharacter.setValue(p.characters.get(1));
                }
                if(p.getCharacters().size() > 2){
                    ThirdCharacter.setValue(p.characters.get(2));
                }
                PlayerScore.setText(String.valueOf(p.score));
                InitialScore.setText(String.valueOf(p.initialScore));
            }
        }
    }

    public void alterSettings(){
        try {
            FileReader fr = new FileReader("Data/Settings");
            BufferedReader br = new BufferedReader(fr);

            String Title = "";
            String DefaultSeason = "";
            String DefaultGame = "";

            int lineCnt = 0;
            String line;
            while((line = br.readLine()) != null){
                lineCnt++;
                if(lineCnt == 1){
                    Title = line;
                }
                if(lineCnt == 2){
                    DefaultSeason = line;
                }
                if(lineCnt == 3){
                    DefaultGame = line;
                }
            }
            br.close();
            fr.close();

            FileWriter fw = new FileWriter("Data/Settings");
            BufferedWriter bw = new BufferedWriter(fw);

            if(!ChangeTitle.getText().equals("")){
                bw.write(ChangeTitle.getText());
            }else {
                bw.write(Title);
            }
            bw.newLine();
            if(DefaultSeasonBox.getValue() != null){
                bw.write(DefaultSeasonBox.getValue());
            }else {
                bw.write(DefaultSeason);
            }
            bw.newLine();
            bw.write(DefaultGame);
            bw.close();
            fw.close();

        }catch (IOException ioe){}
    }

    public void recalculateSeason(){
        Season s = getSeason(CurrentGame.getText(), CurrentSeason.getText());
        s.recalculateSeason(CurrentGame.getText());
        updateCharactersAndPlacings();
        updateTopTen();
    }

    public void updatePlacings(){
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());

        String P1 = PlayerName1.getText();
        Integer[] p1Placings = getPlayerPlacings(s,P1);
        if(p1Placings[0] != 0){
            FirstP1.setText(String.valueOf(p1Placings[0]));
        }
        if(p1Placings[1] != 0){
            SecondP1.setText(String.valueOf(p1Placings[1]));
        }
        if(p1Placings[2] != 0){
            ThirdP1.setText(String.valueOf(p1Placings[2]));
        }

        String P2 = PlayerName2.getText();
        Integer[] p2Placings = getPlayerPlacings(s,P2);
        if(p2Placings[0] != 0){
            FirstP2.setText(String.valueOf(p2Placings[0]));
        }
        if(p2Placings[1] != 0){
            SecondP2.setText(String.valueOf(p2Placings[1]));
        }
        if(p2Placings[2] != 0){
            ThirdP2.setText(String.valueOf(p2Placings[2]));
        }

        String P3 = PlayerName3.getText();
        Integer[] p3Placings = getPlayerPlacings(s,P3);
        if(p3Placings[0] != 0){
            FirstP3.setText(String.valueOf(p3Placings[0]));
        }
        if(p3Placings[1] != 0){
            SecondP3.setText(String.valueOf(p3Placings[1]));
        }
        if(p3Placings[2] != 0){
            ThirdP3.setText(String.valueOf(p3Placings[2]));
        }

        String P4 = PlayerName4.getText();
        Integer[] p4Placings = getPlayerPlacings(s,P4);
        if(p4Placings[0] != 0){
            FirstP4.setText(String.valueOf(p4Placings[0]));
        }
        if(p4Placings[1] != 0){
            SecondP4.setText(String.valueOf(p4Placings[1]));
        }
        if(p4Placings[2] != 0){
            ThirdP4.setText(String.valueOf(p4Placings[2]));
        }

        String P5 = PlayerName5.getText();
        Integer[] p5Placings = getPlayerPlacings(s,P5);
        if(p5Placings[0] != 0){
            FirstP5.setText(String.valueOf(p5Placings[0]));
        }
        if(p5Placings[1] != 0){
            SecondP5.setText(String.valueOf(p5Placings[1]));
        }
        if(p5Placings[2] != 0){
            ThirdP5.setText(String.valueOf(p5Placings[2]));
        }

        String P6 = PlayerName6.getText();
        Integer[] p6Placings = getPlayerPlacings(s,P6);
        if(p6Placings[0] != 0){
            FirstP6.setText(String.valueOf(p6Placings[0]));
        }
        if(p6Placings[1] != 0){
            SecondP6.setText(String.valueOf(p6Placings[1]));
        }
        if(p6Placings[2] != 0){
            ThirdP6.setText(String.valueOf(p6Placings[2]));
        }

        String P7 = PlayerName7.getText();
        Integer[] p7Placings = getPlayerPlacings(s,P7);
        if(p7Placings[0] != 0){
            FirstP7.setText(String.valueOf(p7Placings[0]));
        }
        if(p7Placings[1] != 0){
            SecondP7.setText(String.valueOf(p7Placings[1]));
        }
        if(p7Placings[2] != 0){
            ThirdP7.setText(String.valueOf(p7Placings[2]));
        }

        String P8 = PlayerName8.getText();
        Integer[] p8Placings = getPlayerPlacings(s,P8);
        if(p8Placings[0] != 0){
            FirstP8.setText(String.valueOf(p8Placings[0]));
        }
        if(p8Placings[1] != 0){
            SecondP8.setText(String.valueOf(p8Placings[1]));
        }
        if(p8Placings[2] != 0){
            ThirdP8.setText(String.valueOf(p8Placings[2]));
        }

        String P9 = PlayerName9.getText();
        Integer[] p9Placings = getPlayerPlacings(s,P9);
        if(p9Placings[0] != 0){
            FirstP9.setText(String.valueOf(p9Placings[0]));
        }
        if(p9Placings[1] != 0){
            SecondP9.setText(String.valueOf(p9Placings[1]));
        }
        if(p9Placings[2] != 0){
            ThirdP9.setText(String.valueOf(p9Placings[2]));
        }

        String P10 = PlayerName10.getText();
        Integer[] p10Placings = getPlayerPlacings(s,P10);
        if(p10Placings[0] != 0){
            FirstP10.setText(String.valueOf(p10Placings[0]));
        }
        if(p10Placings[1] != 0){
            SecondP10.setText(String.valueOf(p10Placings[1]));
        }
        if(p10Placings[2] != 0){
            ThirdP10.setText(String.valueOf(p10Placings[2]));
        }
    }

    private Integer[] getPlayerPlacings(Season s, String playerTag){
        Integer[] placings = new Integer[3];
        placings[0] = 0;
        placings[1] = 0;
        placings[2] = 0;
        for (String tournament:s.tournaments) {
            try {
                String fileLocation = "Data/" + CurrentGame.getText() + "/Tournaments/CSVFiles/" + tournament + ".csv";
                FileReader fr = new FileReader(fileLocation);
                BufferedReader br = new BufferedReader(fr);
                int lineCnt = 0;
                String line = "";
                while((line = br.readLine()) != null){
                    lineCnt++;
                    if(lineCnt == 2){
                        String[] top3 = line.split(",");
                        if(playerTag.equals(top3[0])){
                            placings[0] = placings[0] + 1;
                        }
                        if(playerTag.equals(top3[1])){
                            placings[1] = placings[1] + 1;
                        }
                        if(playerTag.equals(top3[2])){
                            placings[2] = placings[2] + 1;
                        }
                    }
                }
            }catch (IOException ioe){
                System.out.println("Could not read Tournament");
            }
        }
        return placings;
    }

    public void updatePlayerStatistics(){
        String Player = SelectPlayerStatistics.getValue();
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        HashMap<String,Integer[]> Sets = s.getSetCounts(SelectPlayerStatistics.getValue(),CurrentGame.getText());
        Set<String> opponents = Sets.keySet();
        ArrayList<String> SetStrings = new ArrayList<>();
        int wins = 0;
        int losses = 0;
        for (String opponent:opponents) {
            Integer[] setCount = Sets.get(opponent);
            String set = setCount[0] + " W " + setCount[1] + " L vs. " + opponent;
            SetStrings.add(set);
            wins = wins + setCount[0];
            losses = losses + setCount[1];
        }
        TourneysEntered.setVisible(true);
        TourneysEntered.setText(String.valueOf(tournamentsEntered(Player)));
        SetList.getItems().clear();
        SetList.getItems().addAll(SetStrings);
        WinLoss.setVisible(true);
        WinPercentage.setVisible(true);
        WinLoss.setText(wins + "W - " + losses + "L");
        double percentage = Math.round(((double) wins / (wins + losses)) * 100);
        WinPercentage.setText(String.valueOf(percentage) + "%");
    }

    private int tournamentsEntered(String player){
        int numberOfTourneys = 0;
        Season s = getSeason(CurrentGame.getText(), CurrentSeason.getText());
        for (String tournament:s.tournaments) {
            try {
                String fileLocation = "Data/" + CurrentGame.getText() + "/Tournaments/CSVFiles/" + tournament + ".csv";
                FileReader fr = new FileReader(fileLocation);
                BufferedReader br = new BufferedReader(fr);
                int lineCnt = 0;
                String line;
                boolean inTournament = false;
                while((line = br.readLine()) != null){
                    lineCnt++;
                    if(lineCnt > 1){
                        if(line.contains(player)){
                            inTournament = true;
                        }
                    }
                }
                if(inTournament){
                    numberOfTourneys++;
                }
            }catch (IOException ioe){
                System.out.println("Couldn't read tournement: " + tournament);
            }
        }
        return numberOfTourneys;
    }

    public void combinePlayers(){
        String basePlayer = BasePlayer.getValue();
        String mergePlayer = MergePlayer.getValue();
        Season s = getSeason(CurrentGame.getText(), CurrentSeason.getText());
        s.combinePlayers(CurrentGame.getText(),basePlayer,mergePlayer);

        updateTopTen();
        updateCharactersAndPlacings();
        fillPlayerBox();
    }

    public void updateWinRates(){
        if(PlayerName1.getText().equals("")){
            P1WR.setText("");
        } else {
            updateWinRate(PlayerName1,P1WR);
        }

        if(PlayerName2.getText().equals("")){
            P2WR.setText("");
        } else {
            updateWinRate(PlayerName2,P2WR);
        }

        if(PlayerName3.getText().equals("")){
            P3WR.setText("");
        } else {
            updateWinRate(PlayerName3,P3WR);
        }

        if(PlayerName4.getText().equals("")){
            P4WR.setText("");
        } else {
            updateWinRate(PlayerName4,P4WR);
        }

        if(PlayerName5.getText().equals("")){
            P5WR.setText("");
        } else {
            updateWinRate(PlayerName5,P5WR);
        }

        if(PlayerName6.getText().equals("")){
            P6WR.setText("");
        } else {
            updateWinRate(PlayerName6,P6WR);
        }

        if(PlayerName7.getText().equals("")){
            P7WR.setText("");
        } else {
            updateWinRate(PlayerName7,P7WR);
        }

        if(PlayerName8.getText().equals("")){
            P8WR.setText("");
        } else {
            updateWinRate(PlayerName8,P8WR);
        }

        if(PlayerName9.getText().equals("")){
            P9WR.setText("");
        } else {
            updateWinRate(PlayerName9,P9WR);
        }

        if(PlayerName10.getText().equals("")){
            P10WR.setText("");
        } else {
            updateWinRate(PlayerName10,P10WR);
        }
    }

    private void updateWinRate(Label player, Label WR){
        Season s = getSeason(CurrentGame.getText(),CurrentSeason.getText());
        HashMap<String,Integer[]> Sets = s.getSetCounts(player.getText(),CurrentGame.getText());
        Set<String> opponents = Sets.keySet();
        ArrayList<String> SetStrings = new ArrayList<>();
        int wins = 0;
        int losses = 0;
        for (String opponent:opponents) {
            Integer[] setCount = Sets.get(opponent);
            String set = setCount[0] + " W " + setCount[1] + " L vs. " + opponent;
            SetStrings.add(set);
            wins = wins + setCount[0];
            losses = losses + setCount[1];
        }
        double percentage = Math.round(((double) wins / (wins + losses)) * 100);
        WR.setText(String.valueOf(percentage) + "%");
    }

    public void update(){
        try {
            updateTopTen();
            Thread.sleep(20);
            updateCharactersAndPlacings();
            Thread.sleep(20);
            updatePlacings();
            Thread.sleep(20);
            updateWinRates();
        }catch (InterruptedException IE){
            System.out.println("couldn't update Slowly");
        }
    }
}