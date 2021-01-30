package Controllers;

import Models.MenadzerModel;
import Views.MenadzerView;

import javax.swing.*;

public class MenadzerController {
    private MenadzerView view;
    private MenadzerModel model;


    public MenadzerController(String email, String haslo)
    {
        model = new MenadzerModel(email, haslo);
        if(model.getCzyZalogowany())
        {
            view = new MenadzerView();
            init();
        }

        else
        {
            JOptionPane.showMessageDialog(null,
                   "Niepoprawne dane",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public void init() {
        //view.getIdField().setText(LoginTools.getLoginIdText(this.model));
        //view.getPersonalDataButton().addActionListener(e -> showPersonalData());
        view.getDostawcyButton().addActionListener(e -> showDostawcy());
    }

    private void showDostawcy() {


    }
}
