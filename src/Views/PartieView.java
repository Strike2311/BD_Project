package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartieView extends JFrame{
    private JPanel mainPane;
    private JTable partieTable;
    private JButton dodajPartieButton;
    private JButton wsteczButton;
    String[] columnNames = {"ID partii", "Nazwa produktu", "Pakowanie", "Cena"};
    private DefaultTableModel tableModel_part = new DefaultTableModel(columnNames, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public PartieView(Statement st, ResultSet rs)
    {
        setSize(800, 300);
        setTitle("Partie");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        try {
            String query = "SELECT id_partii, p2.nazwa_produktu as prod, p.nazwa_pakietu as pak, cena FROM partia part JOIN pakowanie p on p.id_pakowanie = part.Pakowanie_id_pakowanie JOIN produkt p2 on p2.idProdukt = part.Produkt_idProdukt ORDER BY part.id_partii";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idPart = rs.getString("id_partii");
                String id_prod = rs.getString("prod");;
                String id_pak = rs.getString("pak");
                String cena = rs.getString("cena");

                String[] data = { idPart, id_prod, id_pak, cena };
                tableModel_part.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }


        partieTable.setModel(tableModel_part);
        partieTable.setFillsViewportHeight(true);
        partieTable.getTableHeader().setReorderingAllowed(false);
        partieTable.getTableHeader().setResizingAllowed(false);


        setVisible(true);
    }


    public void refresh(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_partii, p2.nazwa_produktu as prod, p.nazwa_pakietu as pak, cena FROM partia part JOIN pakowanie p on p.id_pakowanie = part.Pakowanie_id_pakowanie JOIN produkt p2 on p2.idProdukt = part.Produkt_idProdukt ORDER BY part.id_partii";
            rs = st.executeQuery(query);

            int rowCount = tableModel_part.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel_part.removeRow(i);
            }

            while (rs.next()) {

                String idPart = rs.getString("id_partii");
                String id_prod = rs.getString("prod");;
                String id_pak = rs.getString("pak");
                String cena = rs.getString("cena");

                String[] data = { idPart, id_prod, id_pak, cena };
                tableModel_part.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }



    public JTable getPartieTable() {
        return partieTable;
    }

    public void setPartieTable(JTable partieTable) {
        this.partieTable = partieTable;
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
}
