package sample;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Player {

    private Image player,border;
    private Node hero;
    private static final double W =1240, H = 680;
    private Wall wall = new Wall();


    public Player(int x, int y,String location){

        player = new Image(location);
        hero = new ImageView(player);
        Main.gp.getChildren().add(hero);
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

        final double xxx = wall.getDx();
        final double yyy = wall.getDy();
        System.out.println(xxx);
        System.out.println(yyy);


        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            hero.relocate(x-cx,y-cy);
        }

    }

}