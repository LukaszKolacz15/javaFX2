package sample;

import javafx.scene.control.Alert;

/**
 * Created by Lukasz Kolacz on 26.04.2017.
 */

public class Utils {

    public static void openDialog(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
