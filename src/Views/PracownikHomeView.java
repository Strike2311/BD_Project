package Views;

import javax.swing.*;

public class PracownikHomeView extends JFrame{
    private JLabel pracownikHomeLabel;
    private JButton nadanieZamowienButton;
    private JButton rejetracjaGodzinPracyButton;
    private JButton wprowadzenieZmianIlosciProduktówButton;
    private JPanel MainPanel;
    private JButton wylogujButton;

    public PracownikHomeView()
    {

        setTitle("Menu Pracownika");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 200);
        setResizable(false);
        setContentPane(MainPanel);
        setVisible(true);
    }
    public void setIsVisible(boolean isVisible){
        setVisible(isVisible);
    }
    public JLabel getPracownikHomeLabel() {
        return pracownikHomeLabel;
    }

    public void setPracownikHomeLabel(JLabel pracownikHomeLabel) {
        this.pracownikHomeLabel = pracownikHomeLabel;
    }

    public JButton getNadanieZamowienButton() {
        return nadanieZamowienButton;
    }

    public void setNadanieZamowienButton(JButton nadanieZamowienButton) {
        this.nadanieZamowienButton = nadanieZamowienButton;
    }

    public JButton getRejetracjaGodzinPracyButton() {
        return rejetracjaGodzinPracyButton;
    }

    public void setRejetracjaGodzinPracyButton(JButton rejetracjaGodzinPracyButton) {
        this.rejetracjaGodzinPracyButton = rejetracjaGodzinPracyButton;
    }

    public JButton getWprowadzenieZmianIlosciProduktówButton() {
        return wprowadzenieZmianIlosciProduktówButton;
    }

    public void setWprowadzenieZmianIlosciProduktówButton(JButton wprowadzenieZmianIlosciProduktówButton) {
        this.wprowadzenieZmianIlosciProduktówButton = wprowadzenieZmianIlosciProduktówButton;
    }
}
