package Models;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class KlientModel extends LoginModel {

    private String imie;
    private String nazwisko;
    private String nr_telefonu;
    private String miasto;
    private String adres;
   // private String email;
   // private String haslo;
   // private boolean czyZalogowany;




    public KlientModel(String imie, String nazwisko, String nr_telefonu, String miasto, String adres, String email, String haslo) {
        super(email, haslo);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_telefonu = nr_telefonu;
        this.miasto = miasto;
        this.adres = adres;

    }
    public KlientModel(String email, String haslo) {
        super(email, haslo);
        String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
        String login = "root";
        String password = "root1234";
        try {
            Connection myCon = DriverManager.getConnection(jdbcsrc, login, password);
            Statement myStat_tmp = myCon.createStatement();

        ResultSet myRs_tmp;
        //Zmiana statusu zamówianie na reklamacje



            String sql = "SELECT imie, nazwisko, nr_telefonul, miasto, adres FROM klienci WHERE email = '"+email+"' AND haslo = '"+haslo+"'";
        myRs_tmp = myStat_tmp.executeQuery(sql);
        if(myRs_tmp.next()) {
            this.imie = myRs_tmp.getString("imie");
            this.nazwisko = myRs_tmp.getString("nazwisko");
            this.adres = myRs_tmp.getString("adres");
            this.nr_telefonu = myRs_tmp.getString("nr_telefonul");
            this.miasto = myRs_tmp.getString("miasto");
            super.setCzyZalogowany(true);
        }
        else super.setCzyZalogowany(false);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNr_telefonu() {
        return nr_telefonu;
    }

    public void setNr_telefonu(String nr_telefonu) {
        this.nr_telefonu = nr_telefonu;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void zamowienieDostawy(ResultSet myRs, Statement myStat, ArrayList<String> dane) throws SQLException {
        //struktura danych [idPartii, cena, ilosc...]
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        try {
            String sql = "INSERT INTO zamowienia (Klienci_id_klienci, data_zamowienia, status) " +
                    "VALUES(CALL GetIdKlienta('" + this.imie + "','" + this.nazwisko + "','" + super.getEmail() + "'), '" + dtf.format(now) + "','przygotowane do nadania') ";
            myStat.executeUpdate(sql);

            sql = "SELECT * FROM zamowienia ORDER BY idZamowienia DESC LIMIT 1";
            myRs = myStat.executeQuery(sql);
            myRs.next();
            String idZamowienia = myRs.getString("idZamowienia");

            for (int i = 0; i < dane.size(); i = i + 3) {
                sql = "INSERT INTO patriejednegozamowienia (Zamowienia_idZamowienia, Partia_id_partii, ilosc) " +
                        "VALUE(" + idZamowienia + ", " + dane.get(i) + "," + dane.get(i + 2) + ") ";
                myStat.executeUpdate(sql);
                sql = "INSERT INTO budzet (zyski) " +
                        "VALUE(" + dane.get(i + 1) + ") ";
                myStat.executeUpdate(sql);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
    public void sprawdzenieStanuDostawy(ResultSet myRs, Statement myStat) throws SQLException {
        try {
            String sql = "SELECT data_zamowienia, data_nadania, status FROM zamowienia WHERE Klienci_id_klienci = " +
                    "GetIdKlienta('" + this.imie + "','" + this.nazwisko + "','" + super.getEmail() + "')";

            myRs = myStat.executeQuery(sql);
            while (myRs.next()) {
                if (myRs.getString("data_nadania") != null)
                    System.out.println(myRs.getString("data_zamowienia") + "  " + myRs.getString("data_nadania") + "  " + myRs.getString("status"));
                else
                    System.out.println(myRs.getString("data_zamowienia") + "  " + "brak danych" + "  " + myRs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void zgloszenieReklamacji(ResultSet myRs, Statement myStat, String id) throws SQLException {
        try {
            String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
            String login = "root";
            String password = "root1234";
            Connection myCon = DriverManager.getConnection(jdbcsrc,login,password);
            Statement myStat_tmp = myCon.createStatement();
            ResultSet myRes_tmp;
            //Zmiana statusu zamówianie na reklamacje
            String sql = "CALL ZmienStatusZamowienia('Reklamacja'," + id + ")";
            myStat.executeUpdate(sql);

            //Wyszukanie id zwracanych partii oraz ich ilości
            sql = "SELECT Partia_id_partii, ilosc FROM patriejednegozamowienia WHERE" +
                    " Zamowienia_idZamowienia = '" + id + "'";
            myRs = myStat.executeQuery(sql);
            ResultSet myRs_tmp;
            int sum = 0;
            while(myRs.next()){
                String sql2 = "SELECT cena FROM partia WHERE id_partii = '" +
                        myRs.getString("Partia_id_partii") + "'";
                myRs_tmp = myStat_tmp.executeQuery(sql2);
                if(myRs_tmp.next())
                sum = sum + (myRs_tmp.getInt("cena")* myRs.getInt("ilosc") );
                //myRs.getInt("ilosc")

            }

            sql = "INSERT INTO budzet (straty) VALUE('" + sum + "')";
            myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

