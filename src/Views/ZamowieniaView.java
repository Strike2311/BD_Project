package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;

public class ZamowieniaView extends JFrame{


    private JPanel mainPanel;
    private JPanel headPanel;
    private JLabel headLabel;
    private JTable zamowieniaTable;
    private JButton nadajButton;
    private JButton cofnijButton;
    private JScrollPane scrollPaneTab;
    String[] columnNames = {"ID zamowienia", "ID klienta", "Imię", "Nazwisko", "Data zamowienia"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public ZamowieniaView(Statement st, ResultSet rs) {

        setSize(500, 400);
        setTitle("Zamowienia");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(cofnijButton);
        setLocationRelativeTo(null);



        try {

            String query = "SELECT idZamowienia, Klienci_id_klienci, k.imie as imie, k.nazwisko as nazwisko, data_zamowienia FROM zamowienia " +
                    "JOIN klienci k on k.id_klienci = zamowienia.Klienci_id_klienci WHERE status = 'przygotowane do nadania'";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idZam = rs.getString("idZamowienia");
                String idKlient = rs.getString("Klienci_id_klienci");
                String imie = rs.getString("imie");
                String nazw = rs.getString("nazwisko");
                String dateZam = rs.getString("data_zamowienia");

                String[] data = { idZam, idKlient, imie, nazw, dateZam };
                tableModel.addRow(data);
            }

        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        zamowieniaTable.setModel(tableModel);
        setResizable(false);
        zamowieniaTable.setFillsViewportHeight(true);
        zamowieniaTable.getTableHeader().setReorderingAllowed(false);
        zamowieniaTable.getTableHeader().setResizingAllowed(false);
        zamowieniaTable.setRowSelectionAllowed(true);
        setVisible(true);

    }

    public void refresh(Statement st, ResultSet rs)
    {
        try {

            String query = "SELECT idZamowienia, Klienci_id_klienci, k.imie as imie, k.nazwisko as nazwisko, data_zamowienia FROM zamowienia " +
                    "JOIN klienci k on k.id_klienci = zamowienia.Klienci_id_klienci WHERE status = 'przygotowane do nadania'";
            rs = st.executeQuery(query);

            int rowCount = tableModel.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }

            while (rs.next()) {

                String idZam = rs.getString("idZamowienia");
                String idKlient = rs.getString("Klienci_id_klienci");
                String imie = rs.getString("imie");
                String nazw = rs.getString("nazwisko");
                String dateZam = rs.getString("data_zamowienia");

                String[] data = { idZam, idKlient, imie, nazw, dateZam };
                tableModel.addRow(data);
            }

        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
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
        return cofnijButton;
    }

    public void setCloseButton(JButton closeButton) {
        this.cofnijButton = closeButton;
    }
}
