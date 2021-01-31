package Views;

import javax.swing.*;

public class RejestracjaKlientView extends JFrame{
    private JPanel MainPanel;
    private JLabel imieLabel;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JTextField nr_telefonuTextField;
    private JTextField emailTextField;
    private JPasswordField hasloPasswordField;
    private JButton zarejestrujButton;
    private JButton cofnijButton;
    private JTextField miastoTextField;
    private JTextField adresTextField;


    public RejestracjaKlientView() {
        setTitle("Rejestracja klienta");
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JLabel getImieLabel() {
        return imieLabel;
    }

    public void setImieLabel(JLabel imieLabel) {
        this.imieLabel = imieLabel;
    }

    public JTextField getImieTextField() {
        return imieTextField;
    }

    public void setImieTextField(JTextField imieTextField) {
        this.imieTextField = imieTextField;
    }

    public JTextField getNazwiskoTextField() {
        return nazwiskoTextField;
    }

    public void setNazwiskoTextField(JTextField nazwiskoTextField) {
        this.nazwiskoTextField = nazwiskoTextField;
    }

    public JTextField getNr_telefonuTextField() {
        return nr_telefonuTextField;
    }

    public void setNr_telefonuTextField(JTextField nr_telefonuTextField) {
        this.nr_telefonuTextField = nr_telefonuTextField;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public JPasswordField getHasloPasswordField() {
        return hasloPasswordField;
    }

    public void setHasloPasswordField(JPasswordField hasloPasswordField) {
        this.hasloPasswordField = hasloPasswordField;
    }

    public JButton getZarejestrujButton() {
        return zarejestrujButton;
    }

    public void setZarejestrujButton(JButton zarejestrujButton) {
        this.zarejestrujButton = zarejestrujButton;
    }

    public JButton getCofnijButton() {
        return cofnijButton;
    }

    public void setCofnijButton(JButton cofnijButton) {
        this.cofnijButton = cofnijButton;
    }

    public JTextField getMiastoTextField() {
        return miastoTextField;
    }

    public void setMiastoTextField(JTextField miastoTextField) {
        this.miastoTextField = miastoTextField;
    }

    public JTextField getAdresTextField() {
        return adresTextField;
    }

    public void setAdresTextField(JTextField adresTextField) {
        this.adresTextField = adresTextField;
    }
}
