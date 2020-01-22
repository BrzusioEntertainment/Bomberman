package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Player;

public class Main extends Application {

    public static Group gp = new Group();
    boolean goUP, goDOWN, goLEFT,goRIGHT,goW,goS,goA,goD;
    Player player1,player2;
    private Image player,border;
//    private Map m;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuGroup.fxml"));
        gp = fxmlLoader.load();
        Scene scene = new Scene(gp, 1240, 680, Color.BLACK);



        primaryStage.setScene(scene);
        primaryStage.setTitle("Bombiman");
        primaryStage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }

}

