package Models;

import java.sql.*;

public class PracownikModel extends LoginModel {

    String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
    String login = "root";
    String password = "root1234";

    Connection myCon;
    Statement myStat;
    ResultSet myRs;

    private String imie;
    private String nazwisko;
    private String nr_telefonu;
    private String uprawnienia;
    private String czyZatwierdzony;
    private String stawka;
    private String email;
    private String haslo;
    private boolean czyZalogowany;

    public PracownikModel(String imie, String nazwisko, String nr_telefonu, String uprawnienia, String czyZatwierdzony, String stawka, String email, String haslo) {
        super(email, haslo);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_telefonu = nr_telefonu;
        this.uprawnienia = uprawnienia;
        this.czyZatwierdzony = czyZatwierdzony;
        this.stawka = stawka;

    }


    public PracownikModel(String email, String haslo) {
        super(email, haslo);

        try {
            myCon = DriverManager.getConnection(jdbcsrc, login, password);
            myStat = myCon.createStatement();

            String sql = "SELECT imie, nazwisko, nr_telefonu, uprawnienia, pracownik, stawka FROM pracownicy WHERE email = '"+email+"' AND haslo = '"+haslo+"'";
            myRs = myStat.executeQuery(sql);
            if(myRs.next()) {
                if(!(myRs.getString("uprawnienia").equals("Menadżer")) && (myRs.getInt("pracownik") == 1)) {

                    this.imie = myRs.getString("imie");
                    this.nazwisko = myRs.getString("nazwisko");
                    this.nr_telefonu = myRs.getString("nr_telefonu");
                    this.uprawnienia = myRs.getString("uprawnienia");
                    this.czyZatwierdzony = myRs.getString("pracownik");
                    this.stawka = myRs.getString("stawka");
                    super.setCzyZalogowany(true);
                }
                else super.setCzyZalogowany(false);
            }
            else super.setCzyZalogowany(false);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void nadawanieZamowien(ResultSet myRs, Statement myStat, String id) throws SQLException{
        try {
            String sql = "CALL ZmienStatusZamowienia('Nadane','" + id + "')";
            myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void rejestracjaGodzinPracy(ResultSet myRs, Statement myStat, String hrs) throws SQLException{
        try {
            String sql = " CALL RejestracjaGodzin('"+ hrs +"'," +
                    "(SELECT liczba_godzin FROM etat WHERE Pracownicy_id_pracownicy = (SELECT id_pracownicy FROM pracownicy WHERE imie = '"+this.imie+"' AND nazwisko = '"+this.nazwisko+"' AND email = '"+this.email+"' ORDER BY id_pracownicy DESC LIMIT 1)), " +
                    "(SELECT id_pracownicy FROM pracownicy WHERE imie = '"+this.imie+"' AND nazwisko = '"+this.nazwisko+"' AND email = '"+this.email+"' ORDER BY id_pracownicy DESC LIMIT 1)) ";
            myStat.executeUpdate(sql);
            sql = "CALL GetStawkaPracownika('"+this.imie+"', '"+this.nazwisko+"', '"+this.email+"')";

            myRs = myStat.executeQuery(sql);
            myRs.next();
            int wyplata = myRs.getInt("stawka");
            wyplata *= Integer.parseInt(hrs);

            sql = "INSERT INTO budzet (straty) VALUE('"+wyplata+"')";
            myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void wprowadzenieZmianyStanuZasobow(ResultSet myRs, Statement myStat, String []dane) throws SQLException{
        try {//struktura dane [surowiec, ilosc]
            String sql = "UPDATE surowce SET ilosc = ilosc - "+Integer.parseInt(dane[1])+" WHERE nazwa = '"+dane[0]+"'";
            myStat.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
