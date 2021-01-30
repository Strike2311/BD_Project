package Views;

import javax.swing.*;

public class LoginView {


    private final JFrame frame;
    private JPanel checkLoggingPanel;
    private JLabel checkLoggingLabel;
    private JComboBox loggingOptionCB;
    private JPanel CBPanel;
    private JLabel loginLabel;
    private JTextField textField1;
    private JLabel hasloLabel;
    private JPasswordField passwordField;
    private JButton buttonZaloguj;
    private JPanel mainPanel;
    private JButton signUpButton;

    public LoginView() {
        frame = new JFrame("Logowanie");
        loggingOptionCB.addItem(new String("KLIENT"));
        loggingOptionCB.addItem(new String("PRACOWNIK"));
        loggingOptionCB.addItem(new String("MENADŻER"));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.getRootPane().setDefaultButton(buttonZaloguj);
    }

    public JComboBox getLoggingOptionCB() {
        return loggingOptionCB;
    }

    public void setLoggingOptionCB(JComboBox loggingOptionCB) {
        this.loggingOptionCB = loggingOptionCB;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getButtonZaloguj() {
        return buttonZaloguj;
    }

    public void setButtonZaloguj(JButton buttonZaloguj) {
        this.buttonZaloguj = buttonZaloguj;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public void setSignUpButton(JButton signUpButton) {
        this.signUpButton = signUpButton;
    }
    public JFrame getFrame() {
        return frame;
    }

}
