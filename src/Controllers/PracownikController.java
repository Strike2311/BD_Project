package Controllers;

import Models.PracownikModel;
import Views.LoginView;
import Views.MenadzerView;
import Views.PracownikHomeView;

import javax.swing.*;

public class PracownikController {
    private PracownikHomeView view;
    private PracownikModel model;

    public PracownikController(String email, String haslo, LoginView view_of_logging)
    {
        model = new PracownikModel(email, haslo);
        if(model.getCzyZalogowany())
        {
            view = new PracownikHomeView();

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

    public void init(){

    }

}