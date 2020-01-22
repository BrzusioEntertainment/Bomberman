package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TwoPlayerController {

    public static Group gp;
    private boolean goUP, goDOWN, goLEFT, goRIGHT, space, enter; //P1
    private boolean goW, goS, goA, goD;
    private Bomb bombP1 = new Bomb();
    private Bomb bombP2 = new Bomb();
    public static boolean canPutP1 = true;
    public static Label pointsP1;

    public void loadForTwoPlayers(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/group.fxml"));
        gp = fxmlLoader.load();
        Scene scene = new Scene(gp, 1240, 680, Color.BLACK);

        Player player1 = new Player(60, 620, "/sample/resources/heroGame.png", gp);
        Player player2 = new Player(1180, 620, "sample/resources/heroGame2.png", gp);
//        MapElements.getAllBorders();
//        MapElements.getAllBordersToDestroy();
//        getPointsInstance(gp);


        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    goUP = true;
                    break;
                case DOWN:
                    goDOWN = true;
                    break;
                case LEFT:
                    goLEFT = true;
                    break;
                case RIGHT:
                    goRIGHT = true;
                    break;
                case ENTER:
                    enter = true;
                    break;
                case W:
                    goW = true;
                    break;
                case S:
                    goS = true;
                    break;
                case A:
                    goA = true;
                    break;
                case D:
                    goD = true;
                    break;
                case SPACE:
                    space = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    goUP = false;
                    break;
                case DOWN:
                    goDOWN = false;
                    break;
                case LEFT:
                    goLEFT = false;
                    break;
                case RIGHT:
                    goRIGHT = false;
                    break;
                case ENTER:
                    enter = false;
                    break;
                case W:
                    goW = false;
                    break;
                case S:
                    goS = false;
                    break;
                case A:
                    goA = false;
                    break;
                case D:
                    goD = false;
                    break;
                case SPACE:
                    space = false;
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();

                AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                int dxx =0, dyy = 0;

                if (goUP) dy -= 1;
                if (goDOWN) dy += 1;
                if (goLEFT) dx -= 1;
                if (goRIGHT) dx += 1;
                if (goW) dyy -= 1;
                if (goS) dyy += 1;
                if (goA) dxx -= 1;
                if (goD) dxx += 1;

                if (enter && MapElements.canPutP2) bombP2.putBomb(gp, player2.hero);
                if (space && MapElements.canPutP1) bombP1.putBomb(gp, player1.hero);

                player1.movePlayerBy(dxx, dyy);
                player2.movePlayerBy(dx,dy);
            }
        };
        timer.start();
    }


}
