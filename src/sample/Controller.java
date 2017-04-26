package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField loginText;

    @FXML
    PasswordField passwordText;



//    25.04.2017
//    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("AkademiaKodu");
//        alert.setHeaderText(null);
//        alert.setContentText("Hello jestem tekstem!");
//
//    ButtonType buttonCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
//    ButtonType buttonOskarIsOk = new ButtonType("Oskar jest fajny!");
//    ButtonType buttonOK = new ButtonType("Ok!");
//
//        alert.getButtonTypes().setAll(buttonCancel,buttonOskarIsOk,buttonOK);
//
//
//    Optional<ButtonType> result = alert.showAndWait();
//        if(result.get() == buttonOskarIsOk) {
//        System.out.println("Ktoś myśli, że Oskar jest fajny!");
//
//
//    }else if(result.get() == buttonCancel) {
//        System.out.println("Ktoś wyłączył okno (anuluował)");
//    }


    private boolean isLoginFormValid(){
        if(loginText.getText().trim().length() < 4 || passwordText.getText().trim().length() < 4){
            Utils.openDialog("Loging", "POJEBALO?!");
            return false;
        }
        return true;
    }


    public void openDialog() {

//        System.out.println("Login: " + loginText.getText());
//        System.out.println("Password: " + passwordText.getText());

        if(!isLoginFormValid()){
            return;
        }


        Statement statement = MySqlConnector.getInstance().getNewStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE name = " + "'"+loginText.getText()+"' LIMIT 1");

            int counter = 0;

                while (resultSet.next()){

                    String passwordFromDatabase = resultSet.getString("password");

                    if(passwordFromDatabase.equals(passwordText.getText())){

                        Utils.openDialog("Loging", "Done!");

                    }else {

                        Utils.openDialog("Loging", "Wrong password!");

                    }

                    counter ++;

                }
            if(counter ==0){

                Utils.openDialog("Loging", "User doesen't exist");

            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
