package lgsapp.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnect{
    private DbConnect() {

    }
    public static DbConnect getInstance() {

        return new DbConnect();
    }
    public static Connection getConnection(){
        String connect_string = "jdbc:sqlite:lgsdb.sqlite";
    }
}