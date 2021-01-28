package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;

public class ZamowieniaView extends JDialog{

    static final String USERNAME = "root";
    static final String PASSWORD = "root1234";
    static final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb";
    private JPanel mainPanel;
    private JPanel headPanel;
    private JLabel headLabel;
    private JTable zamowieniaTable;
    private JButton nadajButton;
    private JButton closeButton;
    private JScrollPane scrollPaneTab;
    String[] columnNames = {"ID zamowienia", "ID klienta", "Data zamowienia"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    public ZamowieniaView() {

        setSize(400, 500);
        setTitle("Zamowienia");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(closeButton);
        setLocationRelativeTo(null);



        try {
            Connection myCon = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            Statement myStat = myCon.createStatement();
            String query = "SELECT idZamowienia, Klienci_id_klienci, data_zamowienia FROM zamowienia";
            ResultSet myRs = myStat.executeQuery(query);
            tableModel.setColumnIdentifiers(new String[]{"ID zamowienia", "ID klienta", "Data zamowienia"});

            while (myRs.next()) {

                String idZam = myRs.getString("idZamowienia");
                String idKlient = myRs.getString("Klienci_id_klienci");
                String dateZam = myRs.getString("data_zamowienia");

                String[] data = { idZam, idKlient, dateZam };
                tableModel.addRow(data);
            }

        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        zamowieniaTable.setModel(tableModel);
        zamowieniaTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        setResizable(false);
        zamowieniaTable.setFillsViewportHeight(true);
        zamowieniaTable.getTableHeader().setReorderingAllowed(false);
        zamowieniaTable.getTableHeader().setResizingAllowed(false);
        zamowieniaTable.setRowSelectionAllowed(true);
        setVisible(true);

    }

    public JTable getZamowieniaTable() {
        return zamowieniaTable;
    }

    public void setZamowieniaTable(JTable zamowieniaTable) {
        this.zamowieniaTable = zamowieniaTable;
    }

    public JButton getNadajButton() {
        return nadajButton;
    }

    public void setNadajButton(JButton nadajButton) {
        this.nadajButton = nadajButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(JButton closeButton) {
        this.closeButton = closeButton;
    }
}
