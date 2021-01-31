package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZamówTowarView extends JFrame{
    private JLabel listaProduktowLabel;
    private JButton zamówButton;
    private JTextField iloscTextField;
    private JLabel iloscLabel;
    private JButton cofnijButton;
    private JPanel MainPanel;
    private JTable tabelaZamowTowary;
    private JScrollPane ScrollPanel;
    String[] columnNames = {"ID Partii", "Cena", "Stan"};

    public JTable getTabelaZamowTowary() {
        return tabelaZamowTowary;
    }

    public void setTabelaZamowTowary(JTable tabelaZamowTowary) {
        this.tabelaZamowTowary = tabelaZamowTowary;
    }

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
    public ZamówTowarView(Statement st, ResultSet rs, String email) throws SQLException {

        setSize(800, 300);
        setTitle("Zamów towar");
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            String sql = "SELECT id_klienci FROM klienci WHERE email ='"+email+"'";
            rs = st.executeQuery(sql);
            rs.next();
            String id = rs.getString("id_klienci");
            String query = "SELECT id_partii, cena, stan FROM partia";
            rs = st.executeQuery(query);

            while (rs.next()) {

                String id_partii = rs.getString("id_partii");
                String cena = rs.getString("cena");;
                String stan = rs.getString("stan");


                String[] data = { id_partii, cena, stan };
                tableModel.addRow(data);
            }
        }catch (SQLException c)
        {
            JOptionPane.showMessageDialog(null,"Error in Zamowienia Grid View..... "+c);
        }

        tabelaZamowTowary.setModel(tableModel);
        tabelaZamowTowary.setFillsViewportHeight(true);
        tabelaZamowTowary.getTableHeader().setReorderingAllowed(false);
        tabelaZamowTowary.getTableHeader().setResizingAllowed(false);

        setVisible(true);
    }

    public void setIsVisible(boolean isVisible){
        setVisible(isVisible);
    }
    public JLabel getListaProduktowLabel() {
        return listaProduktowLabel;
    }

    public void setListaProduktowLabel(JLabel listaProduktowLabel) {
        this.listaProduktowLabel = listaProduktowLabel;
    }

    public JButton getZamówButton() {
        return zamówButton;
    }

    public void setZamówButton(JButton zamówButton) {
        this.zamówButton = zamówButton;
    }

    public JTextField getIloscTextField() {
        return iloscTextField;
    }

    public void setIloscTextField(JTextField iloscTextField) {
        this.iloscTextField = iloscTextField;
    }

    public JLabel getIloscLabel() {
        return iloscLabel;
    }

    public void setIloscLabel(JLabel iloscLabel) {
        this.iloscLabel = iloscLabel;
    }

    public JButton getCofnijButton() {
        return cofnijButton;
    }

    public void setCofnijButton(JButton cofnijButton) {
        this.cofnijButton = cofnijButton;
    }
}
