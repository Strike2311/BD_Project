package Views;

import javax.swing.*;

public class DodanieDostawcyView extends JFrame{
    private JPanel mainPane;
    private JTextField nazwaTextField;
    private JTextField lokalizacjaTextField;
    private JTextField surowiecTextField;
    private JTextField cenaTextField;
    private JTextField odlegloscTextField;
    private JButton addButton;
    private JButton closeButton;
    private JComboBox surowceComboBox;

    public DodanieDostawcyView()
    {
        setSize(500, 300);
        setTitle("Dodawanie dostawcy");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        surowceComboBox.addItem(new String("Ziemniaki"));
        surowceComboBox.addItem(new String("Sól"));
        surowceComboBox.addItem(new String("Olej"));
        surowceComboBox.addItem(new String("Pieprz"));
        surowceComboBox.addItem(new String("Aromaty"));
        surowceComboBox.addItem(new String("Inne przyprawy"));


        setVisible(true);
    }


    public JTextField getNazwaTextField() {
        return nazwaTextField;
    }

    public void setNazwaTextField(JTextField nazwaTextField) {
        this.nazwaTextField = nazwaTextField;
    }

    public JTextField getLokalizacjaTextField() {
        return lokalizacjaTextField;
    }

    public void setLokalizacjaTextField(JTextField lokalizacjaTextField) {
        this.lokalizacjaTextField = lokalizacjaTextField;
    }

    public JTextField getSurowiecTextField() {
        return surowiecTextField;
    }

    public void setSurowiecTextField(JTextField surowiecTextField) {
        this.surowiecTextField = surowiecTextField;
    }

    public JTextField getCenaTextField() {
        return cenaTextField;
    }

    public void setCenaTextField(JTextField cenaTextField) {
        this.cenaTextField = cenaTextField;
    }

    public JTextField getOdlegloscTextField() {
        return odlegloscTextField;
    }

    public void setOdlegloscTextField(JTextField odlegloscTextField) {
        this.odlegloscTextField = odlegloscTextField;
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

    public JComboBox getSurowceComboBox() {
        return surowceComboBox;
    }

    public void setSurowceComboBox(JComboBox surowceComboBox) {
        this.surowceComboBox = surowceComboBox;
    }

}
