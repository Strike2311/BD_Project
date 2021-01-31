package Views;

import javax.swing.*;

public class MenadzerView extends JFrame{
    private JLabel headlightLabel;
    private JButton dostawcyButton;
    private JButton budzetButton;
    private JButton pracownicyButton;
    private JButton surowceButton;
    private JPanel MainPanel;
    private JButton wylogujButton;

    public MenadzerView()
    {

        setTitle("Menu menadżera");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 200);
        setResizable(false);
        setContentPane(MainPanel);
        setVisible(true);
    }

    public JButton getDostawcyButton() {
        return dostawcyButton;
    }

    public void setDostawcyButton(JButton dostawcyButton) {
        this.dostawcyButton = dostawcyButton;
    }

    public JButton getBudzetButton() {
        return budzetButton;
    }

    public void setBudzetButton(JButton budzetButton) {
        this.budzetButton = budzetButton;
    }

    public JButton getPracownicyButton() {
        return pracownicyButton;
    }

    public void setPracownicyButton(JButton pracownicyButton) {
        this.pracownicyButton = pracownicyButton;
    }

    public JButton getSurowceButton() {
        return surowceButton;
    }

    public void setSurowceButton(JButton surowceButton) {
        this.surowceButton = surowceButton;
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }

    public void setWylogujButton(JButton wylogujButton) {
        this.wylogujButton = wylogujButton;
    }
}
