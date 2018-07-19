import Controllers.StagesClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        stage.setScene(StagesClass.mainScene);
        stage.show();
    }
}
