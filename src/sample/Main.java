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


//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:
//                        goUP = true;
//                        break;
//                    case DOWN:
//                        goDOWN = true;
//                        break;
//                    case LEFT:
//                        goLEFT = true;
//                        break;
//                    case RIGHT:
//                        goRIGHT = true;
//                        break;
//                    case W:
//                        goW = true;
//                        break;
//                    case S:
//                        goS = true;
//                        break;
//                    case A:
//                        goA = true;
//                        break;
//                    case D:
//                        goD = true;
//                        break;
//                }
//            }
//        });
//
//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:
//                        goUP = false;
//                        break;
//                    case DOWN:
//                        goDOWN = false;
//                        break;
//                    case LEFT:
//                        goLEFT = false;
//                        break;
//                    case RIGHT:
//                        goRIGHT = false;
//                        break;
//                    case W:
//                        goW = false;
//                        break;
//                    case S:
//                        goS = false;
//                        break;
//                    case A:
//                        goA = false;
//                        break;
//                    case D:
//                        goD = false;
//                        break;
//                }
//            }
//        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bombiman");
        primaryStage.show();


//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                int dx = 0, dy = 0;
//                int dxx =0, dyy = 0;
//
//                if (goUP) dy -= 1;
//                if (goDOWN) dy += 1;
//                if (goLEFT) dx -= 1;
//                if (goRIGHT) dx += 1;
//                if (goW) dyy -= 1;
//                if (goS) dyy += 1;
//                if (goA) dxx -= 1;
//                if (goD) dxx += 1;
//                player1.movePlayerBy(dx, dy);
//                //player2.movePlayerBy(dxx,dyy);
//            }
//        };
//        timer.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

//    public void wall(){
//        border = new Image("file:Border.png");
//        ImageView iv = new ImageView(border);
//        iv.setImage(border);
//        iv.setX(200);
//        iv.setY(200);
//        gp.getChildren().add(iv);
//        final double cx = iv.getBoundsInLocal().getWidth() /2;
//        final double cy = iv.getBoundsInLocal().getHeight() /2;
//    }

}

