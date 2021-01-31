package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZmianaSurowcowView extends JFrame{
    private JPanel mainPane;
    private JTextField pobranaIloscTextField;
    private JButton zatwierdźButton;
    private JButton wsteczButton;
    private JTable surowceTable;
    String[] columnNames = {"ID surowca", "Nazwa", "ID dostawcy", "Ilosc", "Jednostka"};
    private DefaultTableModel tableModel_sur = new DefaultTableModel(columnNames, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public ZmianaSurowcowView(Statement st, ResultSet rs)
    {
        setSize(800, 300);
        setTitle("Wprowadzenie pobrania surowców");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        try {
            String query = "SELECT id_surowce, nazwa, Surowce_id_dostawcy, ilosc, jednostka FROM surowce";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idSur = rs.getString("id_surowce");
                String nazwa = rs.getString("nazwa");;
                String id_dost = rs.getString("Surowce_id_dostawcy");
                String ilosc = rs.getString("ilosc");
                String jednostka = rs.getString("jednostka");

                String[] data = { idSur, nazwa, id_dost, ilosc, jednostka };
                tableModel_sur.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        surowceTable.setModel(tableModel_sur);
        surowceTable.setFillsViewportHeight(true);
        surowceTable.getTableHeader().setReorderingAllowed(false);
        surowceTable.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }

    public void refresh(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_surowce, nazwa, Surowce_id_dostawcy, ilosc, jednostka FROM surowce";
            rs = st.executeQuery(query);

            int rowCount = tableModel_sur.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel_sur.removeRow(i);
            }

            while (rs.next()) {

                String idSur = rs.getString("id_surowce");
                String nazwa = rs.getString("nazwa");;
                String id_dost = rs.getString("Surowce_id_dostawcy");
                String ilosc = rs.getString("ilosc");
                String jednostka = rs.getString("jednostka");

                String[] data = { idSur, nazwa, id_dost, ilosc, jednostka };
                tableModel_sur.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }

    public JTextField getPobranaIloscTextField() {
        return pobranaIloscTextField;
    }

    public void setPobranaIloscTextField(JTextField pobranaIloscTextField) {
        this.pobranaIloscTextField = pobranaIloscTextField;
    }

    public JButton getZatwierdźButton() {
        return zatwierdźButton;
    }

    public void setZatwierdźButton(JButton zatwierdźButton) {
        this.zatwierdźButton = zatwierdźButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    public void setWsteczButton(JButton wsteczButton) {
        this.wsteczButton = wsteczButton;
    }

    public JTable getSurowceTable() {
        return surowceTable;
    }

    public void setSurowceTable(JTable surowceTable) {
        this.surowceTable = surowceTable;
    }
}
