import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menadzer extends Pracownik {

    /*
    Zwraca aktualny balans budżetu
     */
    public static int podgladBudzetu(ResultSet myRs, Statement myStat) throws SQLException {
        String sql = "CALL PodgladZyskow()";
        myRs = myStat.executeQuery(sql);
        int zyski = myRs.getInt("SumaZyskow");
        sql = "CALL PodgladStrat()";
        myRs = myStat.executeQuery(sql);
        int straty = myRs.getInt("SumaStrat");
        return zyski - straty;
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