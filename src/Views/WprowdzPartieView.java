package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WprowdzPartieView extends JFrame{
    private JPanel mainPane;
    private JButton dodajPartieButton;
    private JButton wsteczButton;
    private JTable produktTable;
    private JTable pakowanieTable;

    String[] columnNames_produkty = {"ID Produktu", "Nazwa produktu"};
    private DefaultTableModel tableModel_prod = new DefaultTableModel(columnNames_produkty, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    String[] columnNames_pakowanie = {"ID pakietu", "Nazwa pakietu", "Liczba sztuk w zestawie"};
    private DefaultTableModel tableModel_pak  = new DefaultTableModel(columnNames_produkty, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public WprowdzPartieView(Statement st, ResultSet rs)
    {
        setSize(800, 400);
        setTitle("Wprowadź partie");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String query = "SELECT idProdukt, nazwa_produktu FROM produkt";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idProd = rs.getString("idProdukt");
                String nazwa = rs.getString("nazwa_produktu");;

                String[] data = { idProd, nazwa};
                tableModel_prod.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }


        try {
            String query = "SELECT id_pakowanie, nazwa_pakietu, liczba_sztuk FROM pakowanie";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String id_pak = rs.getString("id_pakowanie");
                String nazwa = rs.getString("nazwa_pakietu");
                String liczba = rs.getString("liczba_sztuk");;

                String[] data = { id_pak, nazwa, liczba};
                tableModel_pak.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }


        pakowanieTable.setModel(tableModel_pak);
        pakowanieTable.setFillsViewportHeight(true);
        pakowanieTable.getTableHeader().setReorderingAllowed(false);
        pakowanieTable.getTableHeader().setResizingAllowed(false);


        produktTable.setModel(tableModel_prod);
        produktTable.setFillsViewportHeight(true);
        produktTable.getTableHeader().setReorderingAllowed(false);
        produktTable.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }

    public void refresh_prod(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT idProdukt, nazwa_produktu FROM produkt";
            rs = st.executeQuery(query);

            int rowCount = tableModel_prod.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel_prod.removeRow(i);
            }

            while (rs.next()) {

                String idProd = rs.getString("idProdukt");
                String nazwa = rs.getString("nazwa_produktu");;

                String[] data = { idProd, nazwa};
                tableModel_prod.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }

    public void refresh_pak(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_pakowanie, nazwa_pakietu, liczba_sztuk FROM pakowanie";
            rs = st.executeQuery(query);

            int rowCount = tableModel_pak.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel_pak.removeRow(i);
            }
            while (rs.next()) {

                String id_pak = rs.getString("id_pakowanie");
                String nazwa = rs.getString("nazwa_pakietu");
                String liczba = rs.getString("liczba_sztuk");;

                String[] data = { id_pak, nazwa, liczba};
                tableModel_pak.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }



    public JButton getDodajPartieButton() {
        return dodajPartieButton;
    }

    public void setDodajPartieButton(JButton dodajPartieButton) {
        this.dodajPartieButton = dodajPartieButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    public void setWsteczButton(JButton wsteczButton) {
        this.wsteczButton = wsteczButton;
    }

    public JTable getProduktTable() {
        return produktTable;
    }

    public void setProduktTable(JTable produktTable) {
        this.produktTable = produktTable;
    }

    public JTable getPakowanieTable() {
        return pakowanieTable;
    }

    public void setPakowanieTable(JTable pakowanieTable) {
        this.pakowanieTable = pakowanieTable;
    }
}
