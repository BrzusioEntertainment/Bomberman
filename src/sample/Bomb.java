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

    private Timer timerExplodeP1 = new Timer();
    private Timer timerCleanerP1 = new Timer();
    private Timer timerExplodeP2 = new Timer();
    private Timer timerCleanerP2 = new Timer();
    public static Set<ImageView> grass = new HashSet<>();

    public void putBomb(Group gp, Node hero){
        String bombUrl;
        if(isHeroOne(hero)) {
            bombUrl = "bombP1.png";
        }
        else {
            bombUrl = "bombP2.png";
        }

        Image bombImg = new Image(bombUrl);
        getAllGrass(gp);
        if(isHeroOne(hero)) {
            MapElements.canPutP1 = false;
        }
        else {
            MapElements.canPutP2 = false;
        }
        if(!isBombOnMap(gp, hero)) {
            if(isHeroOne(hero)) {
                checkCollisionAndPutBomb(hero, bombImg);
                timerExplodeP1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        explode(hero);
                    }
                }, 2000);
                timerCleanerP1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        cleanAfterExplosion(gp, hero);
                    }
                }, 2500);
            }
            else {
                checkCollisionAndPutBomb(hero, bombImg);
                timerExplodeP2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        explode(hero);
                    }
                }, 2000);
                timerCleanerP2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        cleanAfterExplosion(gp, hero);
                    }
                }, 2500);
            }

        }
     }

    private void getAllGrass(Group gp) {
        for (Node child : gp.getChildren()) {
            if (child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("Grass.png")) {
                grass.add((ImageView) child);
            }
        }
    }

    private boolean isBombOnMap(Group gp, Node hero) {
        if(isHeroOne(hero)) {
            for (Node child : gp.getChildren()) {
                if (child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("bombP1.png")) {
                    return true;
                }
            }
        }
        else {
            for (Node child : gp.getChildren()) {
                if (child instanceof ImageView && ((ImageView) child).getImage().getUrl().contains("bombP2.png")) {
                    return true;
                }
            }
        }

        return false;
    }

    private void checkCollisionAndPutBomb(Node hero, Image bomb) {
        for(ImageView images : grass){
            if(hero.getBoundsInParent().intersects(images.getBoundsInParent())){
                images.setImage(bomb);
                if(isHeroOne(hero)) {
                    MapElements.isBombP1 = true;
                }
                else {
                    MapElements.isBombP2 = true;
                }
                break;
            }
        }
    }


    private void destroyByBomb(ImageView bomb, Image fire) {
        for(Node images : MapElements.gp.getChildren()){
            if(((images.getLayoutY() == bomb.getLayoutY() && images.getLayoutX() == bomb.getLayoutX() + 40) ||
                    (images.getLayoutY() == bomb.getLayoutY() && images.getLayoutX() == bomb.getLayoutX() - 40) ||
                    (images.getLayoutX() == bomb.getLayoutX() && images.getLayoutY() == bomb.getLayoutY() + 40) ||
                    (images.getLayoutX() == bomb.getLayoutX() && images.getLayoutY() == bomb.getLayoutY() - 40)) &&
                    !((ImageView)images).getImage().getUrl().contains("Border.png")){
                ((ImageView)images).setImage(fire);
            }
        }
    }

    public void explode(Node hero) {
        Image fire = new Image("/sample/resources/fire.png");

            if(isHeroOne(hero)) {
                for (ImageView images : grass) {
                    if (images.getImage().getUrl().contains("bombP1.png")) {
                        destroyByBomb(images, fire);
                        images.setImage(fire);
                        timerExplodeP1.purge();
                    }
                }
            }
            else {
                for (ImageView images : grass) {
                    if (images.getImage().getUrl().contains("bombP2.png")) {
                        destroyByBomb(images, fire);
                        images.setImage(fire);
                        timerExplodeP2.purge();
                    }
                }
            }
        }

    public void cleanAfterExplosion(Group gp, Node hero){
        Image grass = new Image("/sample/resources/Grass.png");
        for(ImageView images : MapElements.bordersToDestroy){
            if(images.getImage().getUrl().contains("fire.png")){
                images.setImage(grass);
            }
        }
        for (ImageView images : Bomb.grass) {
            if (images.getImage().getUrl().contains("fire.png")) {
                images.setImage(grass);
            }
        }
        MapElements.bordersToDestroy.clear();
        MapElements.getAllBordersToDestroy();
        Bomb.grass.clear();
        getAllGrass(gp);
        if(isHeroOne(hero)) {
            MapElements.canPutP1 = true;
        }
        else {
            MapElements.canPutP2 = true;
        }
    }

    private boolean isHeroOne(Node hero){
        return ((ImageView) hero).getImage().getUrl().contains("heroGame.png");
    }

    private boolean isHeroTwo(Node hero){
        return ((ImageView) hero).getImage().getUrl().contains("heroGame2.png");
    }
}
