package Models;

import Controllers.LoginController;

import java.sql.*;

public class Aplikacja {

    public static String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
    public static String login = "root";
    public static String password = "root1234";
    public static Connection myCon;


    public static void testSQL() {


        try {
            //ustala połączenie z bazą danych
            myCon = DriverManager.getConnection(jdbcsrc, login, password);
            //tworzenie klasy do egzekucji komend
            Statement myStat = myCon.createStatement();
            //egzekucja konkretnej komendy SQL i przypisanie do ResultSet
            ResultSet myRs = myStat.executeQuery("SELECT * FROM klienci WHERE imie = 'Kacper'");
        } catch (SQLException c) {
            c.printStackTrace();
        }
    }

    public static void main (String[] args){

        LoginController l = new LoginController();
    }

}
