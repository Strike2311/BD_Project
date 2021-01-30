package Models;

import java.sql.*;

public class MenadzerModel extends LoginModel{

    public MenadzerModel(String email, String haslo) {
        super(email, haslo);
        String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
        String login = "root";
        String password = "root1234";
        try {
            Connection myCon = DriverManager.getConnection(jdbcsrc, login, password);
            Statement myStat_tmp = myCon.createStatement();

            ResultSet myRs_tmp;
            //Zmiana statusu zamówianie na reklamacje



            String sql = "SELECT uprawnienia FROM pracownicy WHERE email = '"+email+"' AND haslo = '"+haslo+"'";
            myRs_tmp = myStat_tmp.executeQuery(sql);
            if(myRs_tmp.next()) {
                if(myRs_tmp.getString("uprawnienia").equals("Menadżer")) {
                    super.setCzyZalogowany(true);
                }
                else super.setCzyZalogowany(false);
            }
            else super.setCzyZalogowany(false);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

     /*/
    / /   Zwraca aktualny balans budżetu
   /*/
    public static int podgladBudzetu(ResultSet myRs, Statement myStat) throws SQLException {
        int zyski, straty;
        try {
           String sql = "CALL PodgladZyskow()";
           myRs = myStat.executeQuery(sql);
            zyski = myRs.getInt("SumaZyskow");
           sql = "CALL PodgladStrat()";
           myRs = myStat.executeQuery(sql);
            straty = myRs.getInt("SumaStrat");
            return zyski - straty;

        }catch(SQLException e){
           e.printStackTrace();
       }
        return 0;
    }

    public static void dodajDostawce(Statement myStat, String[] dane) throws SQLException {
        try {
            String sql = "CALL DodajDostawce(" + dane[0] + ", " + dane[1] + ", " + dane[2] + ", " + dane[3] + ", " + dane[4] + ")";
            myStat.executeUpdate(sql);

        }catch(SQLException e){
           e.printStackTrace();
        }

    }
    public static void usunDostawce(Statement myStat, String[] dane) throws SQLException {
        try {
            String sql = "CALL UsunDostawce(" + dane[0] + ", " + dane[1] + ", " + dane[2] + ", " + dane[3] + ", " + dane[4] + ")";
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
            sql = "INSERT INTO budzet (straty) VALUES("+dane[1]+"* (SELECT cena FROM dostawcy WHERE id_dostawcy = '"+dane[0]+"'))";
            myStat.executeUpdate(sql);
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


}