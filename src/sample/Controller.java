package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button player1, player2;

    @FXML
    private void reactToClick(ActionEvent event) throws Exception{
        Stage stage;

        if(event.getSource()==player1){
            stage = (Stage) player1.getScene().getWindow();
            OnePlayerController onePlayerController = new OnePlayerController();
            onePlayerController.loadForOnePlayer(stage);
        }
        else{
            stage = (Stage) player2.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("mapaMarek.fxml"));
        }

    }
}
