package Controllers;

import Models.*;
import Views.LoginView;

import java.sql.CallableStatement;

public class LoginController {

    private final LoginView view_of_logging;
    private final LoginModel model_of_logging;

    public LoginController() {
        view_of_logging = new LoginView();
        model_of_logging = new LoginModel();

        init();
    }

    public void init() {
        view_of_logging.getButtonZaloguj().addActionListener(e -> logowanie());
    }


    public LoginModel logowanie(){//String login, String haslo, int userType
        //userType 1-klient 2-pracownik 3-menadżer
        LoginModel user;

        int userType = view_of_logging.getLoggingOptionCB().getSelectedIndex();
        String login = view_of_logging.getTextField1().getText();
        String haslo = new String(view_of_logging.getPasswordField().getPassword());

        System.out.println(userType);

        switch (userType) {
            case 0:
                user = new KlientModel(login,haslo);
                if(user.getCzyZalogowany())
                    return user;
                break;
            case 1:
                user = new PracownikModel(login,haslo);
                if(user.getCzyZalogowany())
                    return user;
                break;
            case 2:
                //user = new MenadzerModel(login,haslo);
                new MenadzerController(login, haslo);
                //if(user.getCzyZalogowany())
                 //   return user;

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userType);
        }
        return null;
    }

}
