package Models;

import java.sql.*;

public class PracownikModel extends LoginModel {

    String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
    String login = "root";
    String password = "root1234";

    private Connection myCon;
    private Statement myStat;
    private ResultSet myRs;
    private String id_prac;
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

            String sql = "SELECT id_pracownicy, imie, nazwisko, nr_telefonu, uprawnienia, pracownik, stawka FROM pracownicy WHERE email = '"+email+"' AND haslo = '"+haslo+"'";
            myRs = myStat.executeQuery(sql);
            if(myRs.next()) {
                if(!(myRs.getString("uprawnienia").equals("Menadżer")) && (myRs.getInt("pracownik") == 1)) {

                    this.id_prac = myRs.getString("id_pracownicy");
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
            String sql = "CALL ZmienStatusZamowienia('nadane','" + id + "', current_date())";
            myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void rejestracjaGodzinPracy(ResultSet myRs, Statement myStat, String hrs, String id_pracownika) throws SQLException{
        try {
            String sql = " CALL RejestracjaGodzin('"+ hrs +"', '" + id_pracownika + "');";
            myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void wprowadzenieZmianyStanuZasobow(Statement myStat, ResultSet myRs, String []dane) throws SQLException{
        try {//struktura dane [ID surowca, ilosc]
            String sql = "UPDATE surowce SET ilosc = ilosc - "+Integer.parseInt(dane[1])+" WHERE id_surowce = '"+dane[0]+"'";
            myStat.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void dodajPartie(ResultSet myRs, Statement myStat, String []dane) throws SQLException{
        try {//struktura dane [surowiec, ilosc]
            String sql = "INSERT INTO partia (Produkt_idProdukt, Pakowanie_id_pakowanie, stan) VALUE ( '" + dane[0] + "', '" + dane[1] +"', 'dostępna');";
            myStat.execute(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    public Connection getMyCon() {
        return myCon;
    }

    public void setMyCon(Connection myCon) {
        this.myCon = myCon;
    }

    public Statement getMyStat() {
        return myStat;
    }

    public void setMyStat(Statement myStat) {
        this.myStat = myStat;
    }

    public ResultSet getMyRs() {
        return myRs;
    }

    public void setMyRs(ResultSet myRs) {
        this.myRs = myRs;
    }

    public String getId_prac() {
        return id_prac;
    }

    public void setId_prac(String id_prac) {
        this.id_prac = id_prac;
    }
}
