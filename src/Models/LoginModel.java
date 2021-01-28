package Models;

public class LoginModel {
    private String email;
    private String haslo;
    private boolean czyZalogowany;
    public LoginModel(String email, String haslo){
        this.email = email;
        this.haslo = haslo;
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

    public boolean getCzyZalogowany() {
        return czyZalogowany;
    }

    public void setCzyZalogowany(boolean czyZalogowany) {
        this.czyZalogowany = czyZalogowany;
    }
}
