package sample;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Wall {

    private Image border;
    private Node wall;

    private final double MinX ,MinY,MaxX,MaxY;
    private final Bounds wallx;

    public Wall(){
        border = new Image("file:Border.png");
        ImageView iv = new ImageView(border);
        iv.setImage(border);
        iv.setX(200);
        iv.setY(200);

        wall = iv;

        Main.gp.getChildren().add(iv);

        MinX = iv.getBoundsInParent().getMinX();
        MinY = iv.getBoundsInParent().getMinY();
        MaxX = iv.getBoundsInParent().getMaxX();
        MaxY = iv.getBoundsInParent().getMaxY();

        wallx = iv.getBoundsInParent();

    }

    public Node getWall() {
        return wall;
    }

    public double getMinX() {
        return MinX;
    }

    public double getMinY() {
        return MinY;
    }

    public double getMaxX() {
        return MaxX;
    }

    public double getMaxY() {
        return MaxY;
    }

    public Bounds getWallx() {
        return wallx;
    }
}
