package Views;

import javax.swing.*;

public class GodzinyPracyView extends JFrame{
    private JPanel mainPane;
    private JTextField godzinyPracyTextField;
    private JTextField wprowadzGodzinyTextField;
    private JButton zatwierdzButton;
    private JButton wsteczButton;

    public GodzinyPracyView(String liczba_godzin)
    {
        setSize(400, 300);
        setTitle("Liczba godzin pracy");
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        godzinyPracyTextField.setText(liczba_godzin);
        godzinyPracyTextField.setEditable(false);

        setVisible(true);
    }


    public JTextField getGodzinyPracyTextField() {
        return godzinyPracyTextField;
    }

    public void setGodzinyPracyTextField(JTextField godzinyPracyTextField) {
        this.godzinyPracyTextField = godzinyPracyTextField;
    }

    public JTextField getWprowadzGodzinyTextField() {
        return wprowadzGodzinyTextField;
    }

    public void setWprowadzGodzinyTextField(JTextField wprowadzGodzinyTextField) {
        this.wprowadzGodzinyTextField = wprowadzGodzinyTextField;
    }

    public JButton getZatwierdzButton() {
        return zatwierdzButton;
    }

    public void setZatwierdzButton(JButton zatwierdzButton) {
        this.zatwierdzButton = zatwierdzButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    public void setWsteczButton(JButton wsteczButton) {
        this.wsteczButton = wsteczButton;
    }

}
