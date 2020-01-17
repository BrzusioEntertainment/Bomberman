package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OnePlayerController {

    public static Group gp;
    public static int points = 0;
    private boolean goUP, goDOWN, goLEFT, goRIGHT;
    public static Set<ImageView> borders = new HashSet<>();

    public void loadForOnePlayer(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/group.fxml"));
        gp = fxmlLoader.load();
        Scene scene = new Scene(gp, 1240, 680, Color.BLACK);

        Player player1 = new Player(60, 620, "/sample/resources/heroGame.png", gp);
        getAllBorders(gp);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
                }
            }
        });

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goUP) dy -= 1;
                if (goDOWN) dy += 1;
                if (goLEFT) dx -= 1;
                if (goRIGHT) dx += 1;
                player1.movePlayerBy(dx, dy);


            }
        };
        timer.start();
    }

    public static boolean checkCollision(Node hero) {
        for(ImageView images : borders){
            if(hero.getBoundsInParent().intersects(images.getBoundsInParent())){
                return true;
            }
        }
        return false;
    }

    public void getAllBorders(Group gp){
        for (Node child : gp.getChildren()) {
        if(child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("Border.png")){
            borders.add((ImageView) child);
        }
    }
    }

}
