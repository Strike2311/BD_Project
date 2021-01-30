package Models;

import Controllers.LoginController;
import Views.BudzetView;
import Views.LoginView;
import Views.ZamowieniaView;

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
/*
        //Wypisywanie ResultSet jak stringa
        while(myRs.next()){
            System.out.println(myRs.getString("nazwisko")+", "+ myRs.getString("adres"));
        }
        System.out.println("^Wypisanie 1");


        //Określenie komendy dodającej elemnt do tabeli jako string
        String sql = "INSERT INTO klienci " +
                "(imie, nazwisko, nr_telefonul, miasto, adres, email, haslo) " +
                "values('Kacper', 'Kucharczyk', '664278054', 'Wrocław', 'Elizy Orzeszkowej 52', 'kacper0070@wp.pl', 'cotamcotam123')";
        //Wywołanie komendy na tabeli
        myStat.executeUpdate(sql);

        //Reset wartości myRS, bo .next() przesunęło 'głowice' na koniec
        myRs = myStat.executeQuery("SELECT * FROM klienci WHERE imie = 'Kacper'");

        //Wypisywanie ResultSet jak stringa, aby zobaczyc efekt dodawania
        while(myRs.next()){
            System.out.println(myRs.getString("nazwisko")+", "+ myRs.getString("adres"));
        }
        System.out.println("^Wypisanie 2");



        //Usuwanie dodanego elementu wczesniej elemetu
        sql = "DELETE FROM klienci WHERE imie = 'Kacper'";
        myStat.executeUpdate(sql);

        //Reset wartości myRS, bo .next() przesunęło 'głowice' na koniec
        myRs = myStat.executeQuery("SELECT * FROM klienci WHERE imie = 'Kacper'");

        //Wypisywanie ResultSet jak stringa, aby zobaczyc efekt usuwania
        while(myRs.next()){
            System.out.println(myRs.getString("nazwisko")+", "+ myRs.getString("adres"));
        }
        System.out.println("^Wypisanie 3");

       } catch (SQLException c) {
        c.printStackTrace();
      }
    }
*/
    public void rejestracja(String [] dane){
    //dane []

    }

    public static void main (String[] args){
/*
        String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
        String login = "root";
        String password = "root1234";

        try {
            //ustala połączenie z bazą danych
            Connection myCon = DriverManager.getConnection(jdbcsrc,login,password);
            //tworzenie klasy do egzekucji komend
            Statement myStat = myCon.createStatement();
            //egzekucja konkretnej komendy SQL i przypisanie do ResultSet
            ResultSet myRs = null;

       // MenadzerModel model = new MenadzerModel();
        String []dane = {"6", "2","Inne Przyprawy"};
      // model.wyswietlDostawcow(myRs,myStat, "Ziemniaki");
           LoginModel currUser = logowanie("kozey.sven@example.net","pyon1111", 2);
            if(currUser == null)
                System.out.println("błąd logowania");
            else
            System.out.println(currUser.getEmail() + "  " + currUser.getHaslo());
        } catch (SQLException c) {
            c.printStackTrace();
        }
*/

        //LoginModel l = new LoginModel();
        //testSQL();
        LoginController l = new LoginController();


        /*
        BudzetView bv = new BudzetView();
        ZamowieniaView z = new ZamowieniaView();
        LoginView l = new LoginView();
        */

    }


}
