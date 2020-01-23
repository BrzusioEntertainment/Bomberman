package sample;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {

    private Timer timerExplodeP1 = new Timer();
    private Timer timerCleanerP1 = new Timer();
    private Timer timerExplodeP2 = new Timer();
    private Timer timerCleanerP2 = new Timer();
    public Set<ImageView> grass = new HashSet<>();

    public void putBomb(Group gp, Node hero) {
        if (MapElements.endGame){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Koniec Gry");
            alert.setContentText("Gracz się spalił :(");
//            Platform.exit();
            MapElements mapElements = new MapElements(gp);
            mapElements.endGame();
        }
        String bombUrl;
        if(isHeroOne(hero)) {
            bombUrl = "bombP1.png";
            Image bombImg = new Image(bombUrl);
            getAllGrass(gp);
            MapElements.canPutP1 = false;
            if(!isBombOnMap(gp, hero)) {
                checkCollisionAndPutBomb(hero, bombImg);
                timerExplodeP1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            explode(hero);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 2000);
                timerCleanerP1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        cleanAfterExplosion(gp, hero);
                    }
                }, 2500);
            }
        }
        else {
            bombUrl = "bombP2.png";
            Image bombImg = new Image(bombUrl);
            getAllGrass(gp);
            MapElements.canPutP2 = false;
            if(!isBombOnMap(gp, hero)) {
                checkCollisionAndPutBomb(hero, bombImg);
                timerExplodeP2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            explode(hero);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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


    private void destroyByBomb(ImageView bomb, Image fire, Node hero) throws IOException {
        for(Node images : MapElements.gp.getChildren()){
            if(((images.getLayoutY() == bomb.getLayoutY() && images.getLayoutX() == bomb.getLayoutX() + 40) ||
                    (images.getLayoutY() == bomb.getLayoutY() && images.getLayoutX() == bomb.getLayoutX() - 40) ||
                    (images.getLayoutX() == bomb.getLayoutX() && images.getLayoutY() == bomb.getLayoutY() + 40) ||
                    (images.getLayoutX() == bomb.getLayoutX() && images.getLayoutY() == bomb.getLayoutY() - 40)) &&
                    !((ImageView)images).getImage().getUrl().contains("Border.png")){

                ((ImageView)images).setImage(fire);
                if(images.getBoundsInParent().intersects(hero.getBoundsInParent())){
                    MapElements.endGame = true;
                }
            }
        }
    }

    public void explode(Node hero) throws IOException {
        Image fire = new Image("/sample/resources/fire.png");

            if(isHeroOne(hero)) {
                for (ImageView images : grass) {
                    if (images.getImage().getUrl().contains("bombP1.png")) {
                        destroyByBomb(images, fire, hero);
                        images.setImage(fire);
                        timerExplodeP1.purge();
                    }
                }
            }
            else {
                for (ImageView images : grass) {
                    if (images.getImage().getUrl().contains("bombP2.png")) {
                        destroyByBomb(images, fire, hero);
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
        for (ImageView images : this.grass) {
            if (images.getImage().getUrl().contains("fire.png")) {
                images.setImage(grass);
            }
        }
        MapElements.bordersToDestroy.clear();
        MapElements.getAllBordersToDestroy();
        this.grass.clear();
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

    public void endThreads(){
        this.timerCleanerP1.cancel();
        this.timerCleanerP2.cancel();
        this.timerExplodeP1.cancel();
        this.timerExplodeP2.cancel();
    }
}
