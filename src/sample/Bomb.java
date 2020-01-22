package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {

    private static Timer timerExplode = new Timer();
    private static Timer timerCleaner = new Timer();
    public static Set<ImageView> grass = new HashSet<>();

    public static void putBomb(Group gp, Set<ImageView> borders, Node hero){
        Image bombImg = new Image("bombP1.png");
        Node bomb = new ImageView(bombImg);
        getAllGrass(gp);
        OnePlayerController.canPut = false;
        if(!isBombOnMap(gp)) {
            checkCollisionAndPutBomb(hero, bombImg);
            timerExplode.schedule(new TimerTask() {
                @Override
                public void run() {
                    explode();
                }
            }, 2000);
            timerCleaner.schedule(new TimerTask() {
                @Override
                public void run() {
                    cleanAfterExplosion(gp);
                }
            }, 2500);
            OnePlayerController.pointsP1.setText(String.valueOf(Integer.parseInt(OnePlayerController.pointsP1.getText()) + 10));
        }
     }

    private static void getAllGrass(Group gp) {
        for (Node child : gp.getChildren()) {
            if (child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("Grass.png")) {
                grass.add((ImageView) child);
            }
        }
    }

    private static boolean isBombOnMap(Group gp) {
        for (Node child : gp.getChildren()) {
            if (child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("bombP1.png")) {
                return true;
            }
        }
        return false;
    }

    private static void checkCollisionAndPutBomb(Node hero, Image bomb) {
        for(ImageView images : grass){
            if(hero.getBoundsInParent().intersects(images.getBoundsInParent())){
                images.setImage(bomb);
                OnePlayerController.isBomb = true;
                break;
            }
        }
    }

//    private static void destroyByBomb(ImageView bomb, Image fire) {
//        for(ImageView images : OnePlayerController.bordersToDestroy){
//            if(images.getBoundsInParent().intersects(bomb.getBoundsInParent())){
//                images.setImage(fire);
//            }
//        }
//    }

    private static void destroyByBomb(ImageView bomb, Image fire) {
        for(Node images : OnePlayerController.gp.getChildren()){
            if(((images.getLayoutY() == bomb.getLayoutY() && images.getLayoutX() == bomb.getLayoutX() + 40) ||
                    (images.getLayoutY() == bomb.getLayoutY() && images.getLayoutX() == bomb.getLayoutX() - 40) ||
                    (images.getLayoutX() == bomb.getLayoutX() && images.getLayoutY() == bomb.getLayoutY() + 40) ||
                    (images.getLayoutX() == bomb.getLayoutX() && images.getLayoutY() == bomb.getLayoutY() - 40)) &&
                    !((ImageView)images).getImage().getUrl().contains("Border.png")){
                ((ImageView)images).setImage(fire);
            }
        }
    }

    public static void explode() {
        Image fire = new Image("/sample/resources/fire.png");
        for (ImageView images : grass) {
            if (images.getImage().getUrl().contains("bombP1.png")) {
                        destroyByBomb(images, fire);
                        images.setImage(fire);
                        timerExplode.purge();
            }
        }
    }

    public static void cleanAfterExplosion(Group gp){
        Image grass = new Image("/sample/resources/Grass.png");
        for(ImageView images : OnePlayerController.bordersToDestroy){
            if(images.getImage().getUrl().contains("fire.png")){
                images.setImage(grass);
            }
        }
        for (ImageView images : Bomb.grass) {
            if (images.getImage().getUrl().contains("fire.png")) {
                images.setImage(grass);
            }
        }
        OnePlayerController.bordersToDestroy.clear();
        OnePlayerController.getAllBordersToDestroy(gp);
        Bomb.grass.clear();
        getAllGrass(gp);
        OnePlayerController.canPut = true;
    }
}
