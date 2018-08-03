import Controllers.StagesClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application{
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        StagesClass.mainStage = stage;
        StagesClass.mainStage.setTitle("PRAppV2");
        Parent mainScreen = FXMLLoader.load(getClass().getResource("StartScreenGUI.fxml"));
        StagesClass.mainScene = new Scene(mainScreen);

        StagesClass.tutorialStage = new Stage();
        Parent TutorialScreen = FXMLLoader.load(getClass().getResource("TutorialGUI.fxml"));
        StagesClass.tutorialScene = new Scene(TutorialScreen);
        StagesClass.tutorialStage.initModality(Modality.APPLICATION_MODAL);
        StagesClass.tutorialStage.setTitle("Tutorial");
        StagesClass.tutorialStage.setScene(StagesClass.tutorialScene);

        stage.setScene(StagesClass.mainScene);
        stage.show();
    }
}
