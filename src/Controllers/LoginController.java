package Controllers;

import Models.LoginModel;
import Views.LoginView;

public class LoginController {

    private final LoginView view_of_logging;
    private final LoginModel model_of_logging;

    public LoginController() {
        view_of_logging = new LoginView();
        model_of_logging = new LoginModel();
    }



}
