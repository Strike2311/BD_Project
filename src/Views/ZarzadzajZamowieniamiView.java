package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ZarzadzajZamowieniamiView extends JFrame{
    private JButton zwróćButton;
    private JButton cofnijButton;
    private JScrollPane zamowieniaPanel;
    private JPanel MainPanel;
    private JTable tabelaZarzadzajZamowienia;
    String[] columnNames = {"ID zamówienia", "Data zamówienia", "Data nadania", "Status"};

    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public ZarzadzajZamowieniamiView(Statement st, ResultSet rs, String email) throws SQLException{

        setSize(800, 300);
        setTitle("Zarządzenia zamówieniami");
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String sql = "SELECT id_klienci FROM klienci WHERE email ='"+email+"'";
            rs = st.executeQuery(sql);
            rs.next();
            String id = rs.getString("id_klienci");
            String query = "SELECT idZamowienia, data_zamowienia, data_nadania, status FROM zamowienia WHERE Klienci_id_klienci = '"+id+"'";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idZamowienia = rs.getString("idZamowienia");
                String dataZamowienia = rs.getString("data_zamowienia");;
                String dataNadania = rs.getString("data_nadania");
                String statusZamowienia = rs.getString("status");


                String[] data = { idZamowienia, dataZamowienia, dataNadania, statusZamowienia };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        tabelaZarzadzajZamowienia.setModel(tableModel);
        tabelaZarzadzajZamowienia.setFillsViewportHeight(true);
        tabelaZarzadzajZamowienia.getTableHeader().setReorderingAllowed(false);
        tabelaZarzadzajZamowienia.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }
    public void refresh(Statement st, ResultSet rs, String email)
    {
        try {
            String sql = "SELECT id_klienci FROM klienci WHERE email ='"+email+"'";
            rs = st.executeQuery(sql);
            rs.next();
            String id = rs.getString("id_klienci");
            String query = "SELECT idZamowienia, data_zamowienia, data_nadania, status FROM zamowienia WHERE Klienci_id_klienci = '"+id+"'";
            rs = st.executeQuery(query);
            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                tableModel.removeRow(0);
            }
            while (rs.next()) {

                String idZamowienia = rs.getString("idZamowienia");
                String dataZamowienia = rs.getString("data_zamowienia");;
                String dataNadania = rs.getString("data_nadania");
                String statusZamowienia = rs.getString("status");


                String[] data = { idZamowienia, dataZamowienia, dataNadania, statusZamowienia };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }


    }
    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JTable getTabelaZarzadzajZamowienia() {
        return tabelaZarzadzajZamowienia;
    }

    public void setTabelaZarzadzajZamowienia(JTable tabelaZarzadzajZamowienia) {
        this.tabelaZarzadzajZamowienia = tabelaZarzadzajZamowienia;
    }

    public JButton getZwróćButton() {
        return zwróćButton;
    }

    public void setZwróćButton(JButton zwróćButton) {
        this.zwróćButton = zwróćButton;
    }

    public JButton getCofnijButton() {
        return cofnijButton;
    }

    public void setCofnijButton(JButton cofnijButton) {
        this.cofnijButton = cofnijButton;
    }

    public JScrollPane getZamowieniaPanel() {
        return zamowieniaPanel;
    }

    public void setZamowieniaPanel(JScrollPane zamowieniaPanel) {
        this.zamowieniaPanel = zamowieniaPanel;
    }
}
