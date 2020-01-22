package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Player {

    private Image player;
    public Node hero;
    private static final double W =1240, H = 680;


    public Player(int x, int y, String location, Group gp){
        player = new Image(location);
        hero = new ImageView(player);
        gp.getChildren().add(hero);
        movePlayerTo(x,y);
    }

    public void movePlayerBy(int dx, int dy) {
        if(dx == 0 && dy ==0) return;

        final double cx = hero.getBoundsInLocal().getWidth() /2;
        final double cy = hero.getBoundsInLocal().getHeight() /2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;

        movePlayerTo(x,y);

    }

    public void movePlayerTo(double x, double y){
        final double cx = hero.getBoundsInLocal().getWidth() /2; //środek x naszego bombiego
        final double cy = hero.getBoundsInLocal().getHeight() /2;   //środek y naszego bombiego

        double xRevert = hero.getLayoutX() - cx;
        double yRevert = hero.getLayoutY() - cy;
        if (x - cx >= 40 &&
                x + cx <= W - 40 &&
                y - cy >= 120 &&
                y + cy <= H - 40 &&
                !OnePlayerController.checkCollision(hero)) {

            hero.relocate(x-cx,y-cy);
        }
        if(OnePlayerController.checkCollision(hero) && (hero.getLayoutX() -cx != xRevert || hero.getLayoutY() - cy != yRevert)) {
            hero.relocate(xRevert + cx, yRevert +cy );
        }
    }
}