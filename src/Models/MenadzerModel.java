package Models;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import java.sql.*;

public class MenadzerModel extends LoginModel{

    String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
    String login = "root";
    String password = "root1234";
    Connection myCon;
    Statement myStat;
    ResultSet myRs;

    public MenadzerModel(String email, String haslo) {
        super(email, haslo);

        try
        {
            myCon = DriverManager.getConnection(jdbcsrc, login, password);
            myStat = myCon.createStatement();

            //Zmiana statusu zamówianie na reklamacje

            String sql = "SELECT uprawnienia FROM pracownicy WHERE email = '"+email+"' AND haslo = '"+haslo+"'";
            myRs = myStat.executeQuery(sql);
            if(myRs.next()) {
                if(myRs.getString("uprawnienia").equals("Menadżer")) {
                    super.setCzyZalogowany(true);
                    System.out.println("zalogowano");
                }
                else
                {
                    super.setCzyZalogowany(false);
                    System.out.println("błąd1");
                }
            }
            else
            {
                super.setCzyZalogowany(false);
                System.out.println("błąd2");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

     /*/
    / /   Zwraca aktualny balans budżetu
   /*/
    public float podgladBudzetu(Statement st, ResultSet rs) throws SQLException {
        float zyski, straty;
        try {
           String sql = "CALL PodgladZyskow()";
           rs = st.executeQuery(sql);
           rs.next();
           zyski = rs.getFloat("SumaZyskow");
           sql = "CALL PodgladStrat()";
           rs = st.executeQuery(sql);
           rs.next();
           straty = rs.getFloat("SumaStrat");
           return zyski - straty;

        }catch(SQLException e){
           e.printStackTrace();
       }
        return 0;
    }

    public static void dodajDostawce(Statement myStat, String[] dane) throws SQLException {
        try {
            String sql = "CALL DodajDostawce('" + dane[0] + "', '" + dane[1] + "', '" + dane[2] + "', '" + dane[3] + "', '" + dane[4] + "')";
            myStat.executeUpdate(sql);

        }catch(SQLException e){
           e.printStackTrace();
        }

    }
    public static void usunDostawce(Statement myStat, int id) throws SQLException {
        try {
            String sql = "CALL UsunDostawce_ID('" + id + "')";
            //", '" + dane[1] + "', '" + dane[2] + "', '" + dane[3] + "', '" + dane[4] + "')";
            myStat.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void nadanieUprawnien(ResultSet myRs, Statement myStat, String dane[])throws SQLException{
        //tablica dane w formacie [imie, nazwisko, email]
       try {

           String sql = "UPDATE pracownicy SET pracownik = 1 WHERE imie = '"+dane[0]+"' AND nazwisko = '"+ dane[1] +"' AND email='"+dane[2]+"'";
           myStat.executeUpdate(sql);
       }catch(SQLException e){
           e.printStackTrace();
       }

    }
    public void nadzorWynagrodzen(ResultSet myRs, Statement myStat, String dane[])throws SQLException{
        try {//dane format [imie,nazwisko,email,nowa stawka]

            String sql = "UPDATE pracownicy SET stawka = '"+ dane[3]+"' WHERE imie = '"+dane[0]+"' AND nazwisko = '"+ dane[1] +"' AND email='"+dane[2]+"'";
            myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void sprawdzenieStanuZasobow(ResultSet myRs, Statement myStat)throws SQLException{
        try {//dane format [imie,nazwisko,email,nowa stawka]

            String sql = "SELECT id_surowce,nazwa, ilosc, jednostka FROM surowce";
            myRs = myStat.executeQuery(sql);
            while(myRs.next()){
                System.out.println(myRs.getString("id_surowce")+"  "+myRs.getString("nazwa")+"  "+myRs.getString("ilosc")+"  "+myRs.getString("jednostka"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void zamowienieSurowcow(ResultSet myRs, Statement myStat, String dane[])throws SQLException{
        try {//dane format [id_dostawcy, ilosc_zamowienia, nazwa surowca]

            String sql = "UPDATE surowce SET ilosc = ilosc + '"+dane[1]+"' WHERE nazwa = '"+dane[2]+"'";
            myStat.executeUpdate(sql);
            //sql = "INSERT INTO budzet (straty) VALUES("+dane[1]+"* (SELECT cena FROM dostawcy WHERE id_dostawcy = '"+dane[0]+"'))";
            //myStat.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void wyswietlDostawcow(ResultSet myRs, Statement myStat, String surowiec)throws SQLException{
        try {//dane format [imie,nazwisko,email,nowa stawka]

            String sql = "SELECT * FROM dostawcy WHERE surowiec = '"+surowiec+"'";
            myRs = myStat.executeQuery(sql);
            while(myRs.next()){
                System.out.println(myRs.getString("id_dostawcy")+"  "+myRs.getString("nazwa_dostawcy")+"  "+myRs.getString("lokalizacja")+"  "+myRs.getString("surowiec")+"  "+myRs.getString("cena")+"  "+myRs.getString("odleglosc"));
            }
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
}