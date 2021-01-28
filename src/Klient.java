import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Klient{

    private String imie;
    private String nazwisko;
    private String nr_telefonu;
    private String miasto;
    private String adres;
    private String email;
    private String haslo;

    public Klient(String imie, String nazwisko, String nr_telefonu, String miasto, String adres, String email, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_telefonu = nr_telefonu;
        this.miasto = miasto;
        this.adres = adres;
        this.email = email;
        this.haslo = haslo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void zamowienieDostawy(ResultSet myRs, Statement myStat, ArrayList<String> dane) throws SQLException{
        //struktura danych [idPartii, cena, ilosc...]

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        try {
            String sql = "INSERT INTO zamowienia (Klienci_id_klienci, data_zamowienia, status) " +
                "VALUES((SELECT id_klienci FROM klienci WHERE imie = '" + this.imie + "' AND nazwisko = '"
                + this.nazwisko + "' AND adres = '" + this.adres + "'ORDER BY id_klienci DESC LIMIT 1), '" + dtf.format(now) + "','przygotowane do nadania') ";
                 myStat.executeUpdate(sql);

            sql = "SELECT * FROM zamowienia ORDER BY idZamowienia DESC LIMIT 1";
            myRs = myStat.executeQuery(sql);
            myRs.next();
            String idZamowienia = myRs.getString("idZamowienia");

            for (int i = 0; i < dane.size(); i=i+3) {
                sql = "INSERT INTO patriejednegozamowienia (Zamowienia_idZamowienia, Partia_id_partii, ilosc) " +
                        "VALUE("+idZamowienia+", "+dane.get(i)+","+dane.get(i+2)+") ";
                myStat.executeUpdate(sql);
                sql = "INSERT INTO budzet (zyski) " +
                        "VALUE("+dane.get(i+1)+") ";
                myStat.executeUpdate(sql);
                }
            } catch(SQLException throwables){
                throwables.printStackTrace();

            }
        }
    }

