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
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.getRootPane().setDefaultButton(buttonZaloguj);
    }
}
