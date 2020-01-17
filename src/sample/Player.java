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
    private boolean isCollision = false;
    private double cx;
    private double cy;

    private Bounds wallx;


    public Player(int x, int y,String location){

        player = new Image(location);
        hero = new ImageView(player);
        Main.gp.getChildren().add(hero);
        movePlayerTo(x,y);
    }

    public void movePlayerBy(int dx, int dy) {
        if(dx == 0 && dy ==0) return;

         cx = hero.getBoundsInLocal().getWidth() /2;
         cy = hero.getBoundsInLocal().getHeight() /2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;
        movePlayerTo(x,y);

    }

    public void movePlayerTo(double x, double y){
        final double cx = hero.getBoundsInLocal().getWidth() /2; //środek x naszego bombiego
        final double cy = hero.getBoundsInLocal().getHeight() /2;   //środek y naszego bombiego
        final double MaxX = wall.getMaxX();
        final double MaxY = wall.getMaxY();
        final double MinX = wall.getMinX();
        final double MinY = wall.getMinY();
        wallx = wall.getWallx();

        if      (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H
                && checkCollision() == false)
        {
            hero.relocate(x-cx,y-cy);
            //System.out.println(wallx);
            System.out.println(x + "," + y + " : " + MinX);
            }
        if (checkCollision() == true && y + cy <=MinY){
            hero.relocate(x-cx,y-cy-1);
        }
        if (checkCollision() == true && y - cy <= MaxY){
            hero.relocate(x-cx,y-cy+1);
        }
        if(checkCollision() == true && x + cx <= MinX){
            hero.relocate(x-cx -1,y-cy);
        }
        if(checkCollision() == true && x - cx >=MaxX){
            hero.relocate(x-cx +1,y-cy);
        }
    }
    public boolean checkCollision(){
        return hero.getBoundsInParent().intersects(wall.getWall().getBoundsInParent());
    }

}