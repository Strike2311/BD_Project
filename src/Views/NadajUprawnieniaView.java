package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NadajUprawnieniaView extends JFrame{
    private JPanel mainPane;
    private JTable zatwierdzeniPracownicyTable;
    private JScrollPane scrlPane1;
    private JTable niezatwoerdzeniPracownicyTable;
    private JScrollPane scrlPane2;
    private JTextField stawkaTextField;
    private JButton stawkaButton;
    private JButton acceptButton;
    private JButton wsteczButton;
    String[] columnNames1 = {"ID pracownika", "Imię", "Nazwisko", "Uprawnienia"};
    String[] columnNames2 = {"ID pracownika", "Imię", "Nazwisko", "Uprawnienia", "Stawka"};
    private DefaultTableModel tableModel_niezat = new DefaultTableModel(columnNames1, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel tableModel_zat = new DefaultTableModel(columnNames2, 0)
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public NadajUprawnieniaView(Statement st, ResultSet rs)
    {
        setSize(800, 300);
        setTitle("Pracownicy");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String query = "SELECT id_pracownicy, imie, nazwisko, uprawnienia FROM pracownicy WHERE pracownik = false";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idPrac = rs.getString("id_pracownicy");
                String imie = rs.getString("imie");;
                String nazwisko = rs.getString("nazwisko");
                String uprawnienia = rs.getString("uprawnienia");

                String[] data = { idPrac, imie, nazwisko, uprawnienia };
                tableModel_niezat.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        try {
            String query = "SELECT id_pracownicy, imie, nazwisko, uprawnienia, stawka FROM pracownicy WHERE pracownik = true";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String idPrac = rs.getString("id_pracownicy");
                String imie = rs.getString("imie");;
                String nazwisko = rs.getString("nazwisko");
                String uprawnienia = rs.getString("uprawnienia");
                String stawka = rs.getString("stawka");

                String[] data = { idPrac, imie, nazwisko, uprawnienia, stawka };
                tableModel_zat.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        zatwierdzeniPracownicyTable.setModel(tableModel_zat);
        zatwierdzeniPracownicyTable.setFillsViewportHeight(true);
        zatwierdzeniPracownicyTable.getTableHeader().setReorderingAllowed(false);
        zatwierdzeniPracownicyTable.getTableHeader().setResizingAllowed(false);

        niezatwoerdzeniPracownicyTable.setModel(tableModel_niezat);
        niezatwoerdzeniPracownicyTable.setFillsViewportHeight(true);
        niezatwoerdzeniPracownicyTable.getTableHeader().setReorderingAllowed(false);
        niezatwoerdzeniPracownicyTable.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }

    public void refresh_zatwierdzonych(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_pracownicy, imie, nazwisko, uprawnienia, stawka FROM pracownicy WHERE pracownik = true";
            rs = st.executeQuery(query);

            int rowCount = tableModel_zat.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel_zat.removeRow(i);
            }

            while (rs.next()) {

                String idPrac = rs.getString("id_pracownicy");
                String imie = rs.getString("imie");;
                String nazwisko = rs.getString("nazwisko");
                String uprawnienia = rs.getString("uprawnienia");
                String stawka = rs.getString("stawka");

                String[] data = { idPrac, imie, nazwisko, uprawnienia, stawka };
                tableModel_zat.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }

    public void refresh_niezatwierdzonych(Statement st, ResultSet rs)
    {
        try {
            String query = "SELECT id_pracownicy, imie, nazwisko, uprawnienia FROM pracownicy WHERE pracownik = false";
            rs = st.executeQuery(query);

            int rowCount = tableModel_niezat.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                tableModel_niezat.removeRow(i);
            }

            while (rs.next()) {

                String idPrac = rs.getString("id_pracownicy");
                String imie = rs.getString("imie");;
                String nazwisko = rs.getString("nazwisko");
                String uprawnienia = rs.getString("uprawnienia");

                String[] data = { idPrac, imie, nazwisko, uprawnienia };
                tableModel_niezat.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }
    }

    public JTable getZatwierdzeniPracownicyTable() {
        return zatwierdzeniPracownicyTable;
    }

    public void setZatwierdzeniPracownicyTable(JTable zatwierdzeniPracownicyTable) {
        this.zatwierdzeniPracownicyTable = zatwierdzeniPracownicyTable;
    }

    public JTable getNiezatwoerdzeniPracownicyTable() {
        return niezatwoerdzeniPracownicyTable;
    }

    public void setNiezatwoerdzeniPracownicyTable(JTable niezatwoerdzeniPracownicyTable) {
        this.niezatwoerdzeniPracownicyTable = niezatwoerdzeniPracownicyTable;
    }

    public JTextField getStawkaTextField() {
        return stawkaTextField;
    }

    public void setStawkaTextField(JTextField stawkaTextField) {
        this.stawkaTextField = stawkaTextField;
    }

    public JButton getStawkaButton() {
        return stawkaButton;
    }

    public void setStawkaButton(JButton stawkaButton) {
        this.stawkaButton = stawkaButton;
    }

    public JButton getAcceptButton() {
        return acceptButton;
    }

    public void setAcceptButton(JButton acceptButton) {
        this.acceptButton = acceptButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    public void setWsteczButton(JButton wsteczButton) {
        this.wsteczButton = wsteczButton;
    }

}
