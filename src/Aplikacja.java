import Views.BudzetView;
import Views.ZamowieniaView;

import javax.swing.*;
import java.sql.*;

public class Aplikacja {



    public static void main (String[] args){
        String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
        String login = "root";
        String password = "root1234";

        try {
            //ustala połączenie z bazą danych
            Connection myCon = DriverManager.getConnection(jdbcsrc,login,password);
            //tworzenie klasy do egzekucji komend
            Statement myStat = myCon.createStatement();
            //egzekucja konkretnej komendy SQL i przypisanie do ResultSet
            ResultSet myRs = myStat.executeQuery("SELECT * FROM klienci WHERE imie = 'Kacper'");


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

    //ZamowieniaView zam = new ZamowieniaView();
        // udzetView bud = new BudzetView();

    }
}
