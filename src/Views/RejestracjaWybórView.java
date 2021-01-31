package Views;

import javax.swing.*;

public class RejestracjaWybórView extends JFrame{
    private JButton klientButton;
    private JButton pracownikButton;
    private JPanel MainPanel;
    private JButton cofnijButton;

    public RejestracjaWybórView(){
        setTitle("Wybór konta");
        setSize(500,400);
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public void setIsVisible(boolean isVisible){
        setVisible(isVisible);
    }
    public JButton getKlientButton() {
        return klientButton;
    }

    public void setKlientButton(JButton klientButton) {
        this.klientButton = klientButton;
    }

    public JButton getPracownikButton() {
        return pracownikButton;
    }

    public void setPracownikButton(JButton pracownikButton) {
        this.pracownikButton = pracownikButton;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JButton getCofnijButton() {
        return cofnijButton;
    }

    public void setCofnijButton(JButton cofnijButton) {
        this.cofnijButton = cofnijButton;
    }
}
