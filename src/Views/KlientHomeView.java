package Views;

import javax.swing.*;

public class KlientHomeView extends JFrame{
    static final String USERNAME = "root";
    static final String PASSWORD = "root1234";
    static final String CONN_STRING = "jdbc:mysql://localhost:3306/mydb";
    private JButton zamówTowarButton;
    private JButton zarządzajZamówieniamiButton;
    private JPanel MainPanel;
    private JButton wylogujButton;

    public KlientHomeView()
    {

        setTitle("Menu Klienta");
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
    public JButton getZamówTowarButton() {
    return  zamówTowarButton;
    }

    public void setZamówTowarButton(JButton zamówTowarButton) {
        this.zamówTowarButton = zamówTowarButton;
    }

    public JButton getzarządzajZamówieniamiButton() {
        return zarządzajZamówieniamiButton;
    }

    public void setzarządzajZamówieniamiButton(JButton zarządzajZamówieniamiButton) {
        this.zarządzajZamówieniamiButton = zarządzajZamówieniamiButton;
    }



}
