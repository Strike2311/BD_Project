import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pracownik implements Uzytkownik{
    private String imie;
    private String nazwisko;
    private String nr_telefonu;
    private String uprawnienia;
    private boolean pracownik;
    private int stawka;
    private String email;
    private String haslo;

    public static void nadajZamowienie(ResultSet myRs, Statement myStat) throws SQLException {
        try {
            String sql = "SELECT idZamowienia FROM zamowienia " +
                    "WHERE status IS 'przygotowane do nadania'; ";
            myRs = myStat.executeQuery(sql);
//TODO
            //myRS.
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

    public String getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(String uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public boolean isPracownik() {
        return pracownik;
    }

    public void setPracownik(boolean pracownik) {
        this.pracownik = pracownik;
    }

    public int getStawka() {
        return stawka;
    }

    public void setStawka(int stawka) {
        this.stawka = stawka;
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
}
