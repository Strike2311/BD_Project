import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menadzer extends Pracownik implements Uzytkownik {

    /*
    Zwraca aktualny balans budżetu
     */
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

}