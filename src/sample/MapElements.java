package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;

public class MapElements {

    public static Set<ImageView> borders = new HashSet<>();
    public static Set<ImageView> bordersToDestroy = new HashSet<>();
    public static Group gp;
    public static boolean canPutP1 = true;
    public static boolean canPutP2 = true;
    public static boolean isBombP1, isBombP2;
    public static boolean endGame = false;

    public MapElements(Group gp){
        MapElements.gp = gp;
        getAllBorders();
        getAllBordersToDestroy();
    }

    public static void getAllBorders(){
        for (Node child : gp.getChildren()) {
            if(child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("Border.png")){
                borders.add((ImageView) child);
            }
        }
    }

    public static void getAllBordersToDestroy(){
        for (Node child : gp.getChildren()) {
            if(child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("BorderToDestroy.png")){
                bordersToDestroy.add((ImageView) child);
            }
        }
    }

    public boolean checkCollision(Node hero) {
        for(ImageView images : borders){
            if(hero.getBoundsInParent().intersects(images.getBoundsInParent())){
                return true;
            }
        }
        try {
            for(ImageView images : bordersToDestroy){
                if(hero.getBoundsInParent().intersects(images.getBoundsInParent())){
                    return true;
                }
            }
        } catch (ConcurrentModificationException e){
            System.out.println("Exception");
        }
        return false;
    }

    public void endGame() {
        try {
            Stage stage = (Stage) MapElements.gp.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/endGame.fxml"));
            Group gp = fxmlLoader.load();
            Scene scene = new Scene(gp, 1240, 680, Color.BLACK);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("KONIEC :)");
            Bomb bomb = new Bomb();
            bomb.endThreads();
        }
    }
}
