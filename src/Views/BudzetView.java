package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class BudzetView extends JDialog{

    static final String USERNAME = "root";
    static final String PASSWORD = "root1234";
    static final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb";

    String[] columnNames1 = {"ID kosztow", "Wartosc"};
    String[] columnNames2 = {"ID zyskow", "Wartosc"};
    private JLabel saldoLabel;
    private JTextField bilansTF;
    private JPanel mainPane;
    private JScrollPane scrlPane1;
    private JScrollPane scrlPane2;
    private JTable stratyTable;
    private JTable zyskiTable;
    private DefaultTableModel tableModel1 = new DefaultTableModel(columnNames1, 0);
    private DefaultTableModel tableModel2 = new DefaultTableModel(columnNames2, 0);
    ResultSet myRs;
    Connection myCon;
    Statement myStat;

    public BudzetView()
    {
        //stratyTable = new JTable();
        //zyskiTable = new JTable();
        setSize(400, 500);
        setTitle("Budzet");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            myCon = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            myStat = myCon.createStatement();
            String query = "SELECT id_budzet, straty, zyski FROM budzet";
            myRs = myStat.executeQuery(query);
            //tableModel1.setColumnIdentifiers(new String[]{"ID zamowienia", "ID klienta", "Data zamowienia"});

            while (myRs.next()) {

                String idBud = myRs.getString("id_budzet");
                String strata = myRs.getString("straty");;
                String zysk = myRs.getString("zyski");

                if(strata != null)
                {
                    String[] data = { idBud, strata };
                    tableModel1.addRow(data);
                }
                if(zysk != null)
                {
                    String[] data = { idBud, zysk };
                    tableModel2.addRow(data);
                }

            }

        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        stratyTable.setModel(tableModel1);
        zyskiTable.setModel(tableModel2);


        //setResizable(false);
        stratyTable.setFillsViewportHeight(true);
        zyskiTable.setFillsViewportHeight(true);
        stratyTable.getTableHeader().setReorderingAllowed(false);
        zyskiTable.getTableHeader().setReorderingAllowed(false);
        stratyTable.getTableHeader().setResizingAllowed(false);
        zyskiTable.getTableHeader().setResizingAllowed(false);


       // bilansTF.setText(Menadzer);

        setVisible(true);
    }


    public JTextField getBilansTF() {
        return bilansTF;
    }
    public void setBilansTF(JTextField bilansTF) {
        this.bilansTF = bilansTF;
    }

    /*
    public void dispose() {
        dispose();
    }
*/
}
