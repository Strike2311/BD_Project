package Views;

import javax.swing.*;

public class RejestracjaView extends JFrame{
    private JComboBox comboBox1;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JLabel imieLabel;
    private JTextField nr_telefonuTextField;
    private JTextField emailTextField;
    private JPasswordField hasloPasswordField;
    private JPanel MainPanel;

    public JComboBox getUprawnieniaCB() {
        return uprawnieniaCB;
    }

    public void setUprawnieniaCB(JComboBox uprawnieniaCB) {
        this.uprawnieniaCB = uprawnieniaCB;
    }

    private JComboBox uprawnieniaCB;
    private JButton zarejestrujButton;
    private JButton cofnijButton;

    public RejestracjaView() {
       setTitle("Rejestracja pracownika");
            uprawnieniaCB.addItem("Magazyn");
            uprawnieniaCB.addItem("Pakowanie");
            uprawnieniaCB.addItem("Obsługa");
            uprawnieniaCB.addItem("Kierownik");
            uprawnieniaCB.addItem("Operator");

        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setIsVisible(boolean isVisible){
        setVisible(isVisible);
    }
    public JComboBox getComboBox1() {

        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {

        this.comboBox1 = comboBox1;
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

    public JLabel getImieLabel() {
        return imieLabel;
    }

    public void setImieLabel(JLabel imieLabel) {
        this.imieLabel = imieLabel;
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
}
