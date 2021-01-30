package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DostawcyView extends JDialog{
    private JTable dostawcyTable;
    private JButton addButton;
    private JButton closeButton;
    private JButton removeButton;
    private JScrollPane scrlPane;
    private JPanel mainPane;
    String[] columnNames = {"ID dostawcy", "Nazwa", "Lokalizacja", "Odległość", "Surowiec", "Cena"};

    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public DostawcyView(Statement st, ResultSet rs)
    {
        setSize(800, 300);
        setTitle("Dostawcy");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String query = "SELECT id_dostawcy, nazwa_dostawcy, lokalizacja, surowiec, cena, odleglosc FROM dostawcy";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idDost = rs.getString("id_dostawcy");
                String nazwa = rs.getString("nazwa_dostawcy");;
                String lokalizacja = rs.getString("lokalizacja");
                String surowiec = rs.getString("surowiec");
                String cena = rs.getString("cena");
                String odleglosc = rs.getString("odleglosc");

                String[] data = { idDost, nazwa, lokalizacja, surowiec, cena, odleglosc };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        dostawcyTable.setModel(tableModel);
        dostawcyTable.setFillsViewportHeight(true);
        dostawcyTable.getTableHeader().setReorderingAllowed(false);
        dostawcyTable.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }
    public void refresh(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_dostawcy, nazwa_dostawcy, lokalizacja, surowiec, cena, odleglosc FROM dostawcy";
            rs = st.executeQuery(query);

            int rowCount = tableModel.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }

            while (rs.next()) {

                String idDost = rs.getString("id_dostawcy");
                String nazwa = rs.getString("nazwa_dostawcy");;
                String lokalizacja = rs.getString("lokalizacja");
                String surowiec = rs.getString("surowiec");
                String cena = rs.getString("cena");
                String odleglosc = rs.getString("odleglosc");

                String[] data = { idDost, nazwa, lokalizacja, surowiec, cena, odleglosc };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(JButton closeButton) {
        this.closeButton = closeButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(JButton removeButton) {
        this.removeButton = removeButton;
    }

    public JTable getDostawcyTable() {
        return dostawcyTable;
    }

    public void setDostawcyTable(JTable dostawcyTable) {
        this.dostawcyTable = dostawcyTable;
    }

}
