package Controllers;

import javafx.fxml.Initializable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // do all things here that happen when this screen is started
    }

    public void createSeason(){
        String game = "Game";
        String seasonTitle = "Season Title";
        try {
            FileWriter fw = new FileWriter("Data/" + game + "/Seasons/" + seasonTitle);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(seasonTitle);
            bw.close();
            fw.close();
        }catch (IOException ioe){
            System.out.println("couldn't create season");
        }
    }
}
