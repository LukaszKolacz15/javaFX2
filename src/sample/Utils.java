package sample;

import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;
import javafx.scene.control.Alert;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String hashPassword(String password){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try {
                byte[] hashArray = digest.digest(password.getBytes("UTF-8"));
                return DatatypeConverter.printHexBinary(hashArray);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
