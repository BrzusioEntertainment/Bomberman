package sample;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Wall {

    private Image border;

    private final double dx ,dy;

    public Wall(){
        border = new Image("file:Border.png");
        ImageView iv = new ImageView(border);
        iv.setImage(border);
        iv.setX(200);
        iv.setY(200);
        Main.gp.getChildren().add(iv);
        iv.boundsInParentProperty();

        dx = iv.getBoundsInParent().getMinX();
        dy = iv.getBoundsInParent().getMinY();

    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
}
