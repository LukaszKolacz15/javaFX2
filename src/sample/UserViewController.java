package sample;

import com.jfoenix.controls.JFXListView;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Lukasz Kolacz on 29.04.2017.
 */
public class UserViewController implements Initializable {

    @FXML
    ImageView logo;

    @FXML
    ListView list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        ANIMACJE:

//        RotateTransition transition= new RotateTransition();
//        transition.setDuration(Duration.seconds(2));
//        transition.setByAngle(360);
//        transition.setCycleCount(20);
//        transition.setAutoReverse(false);
//        transition.setToAngle(360);
////        transition.setFromAngle(-60);
//        transition.setNode(logo);
//        transition.play();
//
//
//        TranslateTransition translateTransition = new TranslateTransition();
//        translateTransition.setNode(logo);
//        translateTransition.setDuration(Duration.seconds(5));
//        translateTransition.setByX(800);
//        translateTransition.setFromX(-300);
//        translateTransition.setCycleCount(10);
//        translateTransition.setAutoReverse(false);
//        translateTransition.play();

//
//        FadeTransition fadeTransition = new FadeTransition();
//        fadeTransition.setNode(logo);
//        fadeTransition.setDuration(Duration.seconds(5));
//        fadeTransition.setFromValue(1.0);
//        fadeTransition.setToValue(0.0);
//        fadeTransition.setAutoReverse(true);
//        fadeTransition.setCycleCount(2);
//        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//            }
//        });
//        fadeTransition.play();


//        LIST VIEW:

//        ObservableList<String> items = FXCollections.observableArrayList("Oskar", "Wojtek", "Kacper", "Mikolaj");
//        list.setItems(items);
//      Wywołanie metody do wstawiania na listView
        list.setItems(loadBook());

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(list.getSelectionModel().getSelectedItem());
            }
        });

    }

//    metoda dodawania książek do listView
    private ObservableList<String> loadBook() {
        ObservableList<String> items = FXCollections.observableArrayList();
        Statement statement = MySqlConnector.getInstance().getNewStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            while (resultSet.next()) {
                items.add(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

}
