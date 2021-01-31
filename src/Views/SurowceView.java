package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SurowceView extends JFrame{
    private JButton zamowButton;
    private JButton wsteczButton;
    private JTable surowceTable;
    private JPanel mainPane;
    String[] columnNames = {"ID surowca", "Nazwa", "Ilość", "Jednostka"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
    {

        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public SurowceView(Statement st, ResultSet rs)
    {
        setSize(800, 300);
        setTitle("Stan surowców");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String query = "SELECT id_surowce,nazwa, SUM(ilosc) as ile, jednostka FROM surowce GROUP BY nazwa";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idSur = rs.getString("id_surowce");
                String nazwa = rs.getString("nazwa");;
                String ilosc = rs.getString("ile");
                String jednostka = rs.getString("jednostka");

                String[] data = { idSur, nazwa, ilosc, jednostka };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        surowceTable.setModel(tableModel);
        surowceTable.setFillsViewportHeight(true);
        surowceTable.getTableHeader().setReorderingAllowed(false);
        surowceTable.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }


    public void refresh(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_surowce,nazwa, SUM(ilosc) as ile, jednostka FROM surowce GROUP BY nazwa";
            rs = st.executeQuery(query);

            int rowCount = tableModel.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel.removeRow(i);
            }

            while (rs.next()) {

                String idSur = rs.getString("id_surowce");
                String nazwa = rs.getString("nazwa");;
                String ilosc = rs.getString("ile");
                String jednostka = rs.getString("jednostka");

                String[] data = { idSur, nazwa, ilosc, jednostka };
                tableModel.addRow(data);
            }
            }catch (SQLException c)
            {
                JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
            }
    }



    public JButton getZamowButton() {
        return zamowButton;
    }

    public void setZamowButton(JButton zamowButton) {
        this.zamowButton = zamowButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    public void setWsteczButton(JButton wsteczButton) {
        this.wsteczButton = wsteczButton;
    }

    public JTable getDostawcyTable() {
        return surowceTable;
    }

    public void setDostawcyTable(JTable dostawcyTable) {
        this.surowceTable = dostawcyTable;
    }


}
