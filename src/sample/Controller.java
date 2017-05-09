package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    TextField loginText;

    @FXML
    PasswordField passwordText;


    @FXML
    TextField loginTextReg;

    @FXML
    TextField lastNameReg;

    @FXML
    TextField telephoneReg;

    @FXML
    PasswordField passReg;

    @FXML
    PasswordField repeatedPassReg;

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


    private boolean isLoginFormValid() {
        if (loginText.getText().trim().length() < 4 || passwordText.getText().trim().length() < 4) {
            Utils.openDialog("Loging", "Login or password can not be less than four characters!");
            return false;
        }
        return true;
    }


    public void openDialog(MouseEvent event) {

//        System.out.println("Login: " + loginText.getText());
//        System.out.println("Password: " + Utils.hashPassword(passwordText.getText()));

        if (!isLoginFormValid()) {
            return;
        }

        Statement statement = MySqlConnector.getInstance().getNewStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE name = " + "'" + loginText.getText() + "' LIMIT 1");
            int counter = 0;
            while (resultSet.next()) {
                String passwordFromDatabase = resultSet.getString("password");
//                    if(passwordFromDatabase.equals(passwordText.getText())){


// Hashowanie!!!

                if (passwordFromDatabase.equals(Utils.hashPassword(passwordText.getText()))) {


// Przełączenie na inna scene po wpisaniu poprawnego loginu i hasla
                    try {
                        Parent myPage = FXMLLoader.load(getClass().getResource("userView.fxml"));
                        Scene scene = new Scene(myPage);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stage.hide();
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
// /////////////////////////////////////////////////////////////////
                    Utils.openDialog("Loging", "Done!");
                } else {
                    Utils.openDialog("Loging", "Wrong password!");
                }
                counter++;
            }
            if (counter == 0) {
                Utils.openDialog("Loging", "User doesen't exist! Please create an account");
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createAccount(MouseEvent event) {

        if (passReg.getText().equals(repeatedPassReg.getText())){

            try {
                Parent myPage = FXMLLoader.load(getClass().getResource("registrationCorrect.fxml"));
                Scene scene = new Scene(myPage);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.hide();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

//            TODO: dodać walidację poprawnie wprowadzonego numeru telefonu

            String sgl = "INSERT INTO user (name, password, lastname, number) VALUES(?, ?, ?, ?)";
            try {
                PreparedStatement statement = MySqlConnector.getInstance().getConnection().prepareStatement(sgl);
                statement.setString(1, loginTextReg.getText());
                statement.setString(2, Utils.hashPassword(passReg.getText()));
                statement.setString(3, lastNameReg.getText());
                statement.setString(4, telephoneReg.getText());
                statement.execute();

//                Utils.openDialog("Logowanie", "Poprawnie założono konto");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else {
            Utils.openDialog("Logowanie", "Hasła muszą być jednakowe!");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
