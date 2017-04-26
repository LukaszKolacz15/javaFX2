package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Controller {

    public void openDialog(){
//        System.out.println("Działa");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("AkademiaKodu");
        alert.setHeaderText("Witaj świecie");
        alert.setContentText("Halo jestem tekstem");

//        alert.showAndWait();

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){
            System.out.println("ktos klikal OK");
        }
    }

}
