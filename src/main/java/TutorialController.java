import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;

import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class TutorialController implements Initializable {

    @FXML
    TextArea Text;

    @FXML
    Label Title, PageNumber;

    @FXML
    ImageView TopRightImage, BottomRightImage;



    Image ChallongeLogo = new Image("Images/challonge-logo.png");
    int MaxPageNumber = 5;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        Font SFQuartziteObliqueTitle = Font.loadFont(getClass().getResourceAsStream("/Fonts/SFQuartzite-Oblique.ttf"), 50);
        Title.setFont(SFQuartziteObliqueTitle);
        PageNumber.setText("1");
        Tutorial();
    }

    public void Tutorial(){
        if(PageNumber.getText().equals("1")){
            BottomRightImage.setImage(ChallongeLogo);
            Text.setText("     Welcome to the PRApp Tutorial.  This tutorial will show you how to use all of the features within this application so you can get the most use out of it.  \n" +
                    "     After this tutorial you should be able to add tournaments and players to seasons as well as create seasons.  \n" +
                    "     This app will require that you be able to access the Challonge API.  To do this, you need to at least have a free challonge account and know your API key.  Follow the link below to learn more about the Challonge API.\n" +
                    "\n" +
                    "     https://api.challonge.com/v1\n" +
                    "\n" +
                    "The next page will teach how to start using the application and create a Season and add a Tournament.");

        } else if(PageNumber.getText().equals("2")){
            Text.setText("     The first thing to do is to create a Season.  Go to the Season tab on the left hand side.  At the bottom enter a new season title and then select the create season button.\n" +
                    "     Now in the change season drop box the season you just created will appear in the list.  To change to that season, select it in the drop box and select the change season button.  Now on the top right the current season will be the season you just changed to.\n" +
                    "     The next step is to add a tournament to your season.");

        } else if(PageNumber.getText().equals("3")){
            Text.setText("     To add a tournament, enter the following URL into your web browser with the tournament key replaced.  The tournament key for the tournament is the text after Challonge.com/:\n" +
                    "\n" +
                    "https://api.challonge.com/v1/tournaments/{tournamentKey}.json?include_participants=1&include_matches=1\n" +
                    "\n" +
                    "     Once you log in with your username and API key, text will appear on the screen.  Copy and paste all of the text into the \"Tournament JSON\" textfield in the Tournament Tab.  To add the tournament to the current season, select the \"Add Tournament\" button.  \n" +
                    "     You will see new players appear in the table in the center of the application.  They will not have characters and may be in an odd order.  To fix that we must alter the players to match their skill level and characters.");
        } else if(PageNumber.getText().equals("4")){
            Text.setText("     Once tournaments have been added to a season, players will appear in the PR table on the right.  To alter any player in the season, navigate to the Player tab. \n" +
                    "     Select any player from the \"Select Player\" drop box and the players info will be filled in the textfields below.   The initial score is the power score the player has before any matches are calculated.  The score number is the score after the matches have been calculated for all tournaments in the season.  \n" +
                    "     Once you are done editing a players information, select the Alter Player button to save your changes.  To create a new Player, fill in the \"New Player Tag\" textfield and any other fields above then select the \"Create New Player\" Button.  After editing all the players you would like to edit, navigate to the Season tab and select the \"Recalculate Season\" button.  This will recalculate all the matches with the updated initial scores.  This will also update the table.");
        } else {
            Text.setText("Good Luck :)");
        }
    }

    public void PGUp(){
        if(Integer.parseInt(PageNumber.getText()) < MaxPageNumber){
            int newValue = Integer.parseInt(PageNumber.getText()) + 1;
            PageNumber.setText(String.valueOf(newValue));
        }
        Tutorial();
    }

    public void PGDown(){
        if(Integer.parseInt(PageNumber.getText()) > 0){
            int newValue = Integer.parseInt(PageNumber.getText()) - 1;
            PageNumber.setText(String.valueOf(newValue));
        }
        Tutorial();
    }
}
