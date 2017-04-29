package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Lukasz Kolacz on 26.04.2017.
 */

public class MySqlConnector {


//BASE PASSWORDS:

    private static MySqlConnector ourInstance = new MySqlConnector();

    public static MySqlConnector getInstance() {
        return ourInstance;
    }

    private Connection connection;

    private MySqlConnector() {
        try {
            Class.forName(DRIVER).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB, USER, USERPW);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connected");
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getNewStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
