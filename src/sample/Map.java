package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {


    private Image border,grass,borderToDestroy;
    private String borderLocation = "file:Border.png";
    private String bordertoDestroyLocation = "file:BorderToDestroy.png";
    private String grassLocation = "file:Grass.png";

    public Map(){
        border = new Image(borderLocation);
        ImageView iv = new ImageView(border);
        iv.setImage(border);
        Main.gp.getChildren().add(iv);

    }

    public Image getBorder() {
        return border;
    }


}
