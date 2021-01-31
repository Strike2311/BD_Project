package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZamowSurowiecView extends JFrame{
    private JScrollPane scrlPane;
    private JTextField iloscTextField;
    private JButton zmowButton;
    private JButton wsteczButton;
    private JPanel mainPane;
    private JTable dostawcyTable;
    private String nazwa_sur;

    String[] columnNames = {"ID dostawcy", "Nazwa", "Lokalizacja", "Odległość", "Surowiec", "Cena"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public ZamowSurowiecView(Statement st, ResultSet rs, String nazwa)
    {
        nazwa_sur = nazwa;
        setSize(800, 300);
        setTitle("Zamawianie surowca: " + nazwa);
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String query = "SELECT id_dostawcy, nazwa_dostawcy, lokalizacja, surowiec, cena, odleglosc FROM dostawcy WHERE surowiec = '" + nazwa + "';";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idDost = rs.getString("id_dostawcy");
                String name = rs.getString("nazwa_dostawcy");;
                String lokalizacja = rs.getString("lokalizacja");
                String surowiec = rs.getString("surowiec");
                String cena = rs.getString("cena");
                String odleglosc = rs.getString("odleglosc");

                String[] data = { idDost, name, lokalizacja, surowiec, cena, odleglosc };
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
            String query = "SELECT id_dostawcy, nazwa_dostawcy, lokalizacja, surowiec, cena, odleglosc FROM dostawcy WHERE surowiec = '" + nazwa_sur + "';";
            rs = st.executeQuery(query);

            int rowCount = tableModel.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }
            while (rs.next()) {

                String idDost = rs.getString("id_dostawcy");
                String name = rs.getString("nazwa_dostawcy");;
                String lokalizacja = rs.getString("lokalizacja");
                String surowiec = rs.getString("surowiec");
                String cena = rs.getString("cena");
                String odleglosc = rs.getString("odleglosc");

                String[] data = { idDost, name, lokalizacja, surowiec, cena, odleglosc };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }


    public JTextField getIloscTextField() {
        return iloscTextField;
    }

    public void setIloscTextField(JTextField iloscTextField) {
        this.iloscTextField = iloscTextField;
    }

    public JButton getZmowButton() {
        return zmowButton;
    }

    public void setZmowButton(JButton zmowButton) {
        this.zmowButton = zmowButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    public void setWsteczButton(JButton wsteczButton) {
        this.wsteczButton = wsteczButton;
    }

    public JTable getDostawcyTable() {
        return dostawcyTable;
    }

    public void setDostawcyTable(JTable dostawcyTable) {
        this.dostawcyTable = dostawcyTable;
    }

}
