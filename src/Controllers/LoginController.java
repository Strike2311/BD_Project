package Controllers;

import Models.LoginModel;
import Views.LoginView;
import Views.RejestracjaKlientView;
import Views.RejestracjaView;
import Views.RejestracjaWybórView;

import javax.swing.*;
import java.sql.*;

public class LoginController {
    public static String jdbcsrc = "jdbc:mysql://localhost:3306/mydb";
    public static String login = "root";
    public static String password = "root1234";
    public static Connection myCon;
    public static Statement myStat;
    public static ResultSet myRs;

    private  LoginView view_of_logging;
    private LoginModel model_of_logging;



    public LoginController() {
        try {
            myCon = DriverManager.getConnection(jdbcsrc,login,password);
            myStat = myCon.createStatement();

            view_of_logging = new LoginView();
            model_of_logging = new LoginModel();

            init();
        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void init() {
        view_of_logging.getButtonZaloguj().addActionListener(e -> logowanie());
        view_of_logging.getSignUpButton().addActionListener(e -> rejestracjaWyborUzytkownika());
    }

    private void rejestracjaWyborUzytkownika() {
        view_of_logging.setIsVisible(false);
        RejestracjaWybórView view_of_rejestracja = new RejestracjaWybórView();
        view_of_rejestracja.getKlientButton().addActionListener(e ->{
            RejestracjaKlientView view_of_rejestracja_klient = new RejestracjaKlientView();

            view_of_rejestracja_klient.getZarejestrujButton().addActionListener(e1 -> {
                view_of_rejestracja.setIsVisible(false);
                String []dane = {view_of_rejestracja_klient.getImieTextField().getText(),
                    view_of_rejestracja_klient.getNazwiskoTextField().getText(),
                    view_of_rejestracja_klient.getNr_telefonuTextField().getText(),
                    view_of_rejestracja_klient.getMiastoTextField().getText(),
                    view_of_rejestracja_klient.getAdresTextField().getText(),
                    view_of_rejestracja_klient.getEmailTextField().getText(),
                    new String(view_of_rejestracja_klient.getHasloPasswordField().getPassword())
            };

            try{
                String sql = "SELECT * FROM klienci WHERE email = '"+dane[5]+"'";
                myRs = myStat.executeQuery(sql);
                if(!myRs.next()) {

                    sql = "INSERT INTO klienci (imie, nazwisko, nr_telefonul,miasto,adres, email, haslo) VALUES ('" + dane[0] + "', '" + dane[1] + "'," +
                            "'" + dane[2] + "', '" + dane[3] + "', '" + dane[4] + "', '" + dane[5] + "','" + dane[6] + "')";

                    myStat.executeUpdate(sql);

                    myRs = myStat.executeQuery("SELECT * FROM klienci WHERE email = '" + dane[5] + "'");

                    if (myRs.next()) {
                        JOptionPane.showMessageDialog(null, "Rejestracja przebiegła pomyślnie.", "Rejestracja", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Rejestracja nie powiodła się.", "Rejestracja", JOptionPane.ERROR_MESSAGE);

                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Użytkownik o podanym adresie email już istnieje.", "Rejestracja", JOptionPane.ERROR_MESSAGE);
                view_of_rejestracja.setVisible(false);
                view_of_rejestracja_klient.setVisible(false);
                view_of_logging.setIsVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            });
            view_of_rejestracja_klient.getCofnijButton().addActionListener(e1 -> {
                view_of_rejestracja_klient.setVisible(false);
                view_of_rejestracja.setIsVisible(true);

            });
        });


        view_of_rejestracja.getPracownikButton().addActionListener(e ->{
            view_of_rejestracja.setVisible(false);
            RejestracjaView view_of_rejestracja_pracownika = new RejestracjaView();
            view_of_rejestracja_pracownika.getZarejestrujButton().addActionListener(e1 -> {
                String []dane = {view_of_rejestracja_pracownika.getImieTextField().getText(),
                        view_of_rejestracja_pracownika.getNazwiskoTextField().getText(),
                        view_of_rejestracja_pracownika.getNr_telefonuTextField().getText(),
                        view_of_rejestracja_pracownika.getEmailTextField().getText(),
                        new String(view_of_rejestracja_pracownika.getHasloPasswordField().getPassword())
                };

            try{
                String sql = "SELECT * FROM pracownicy WHERE email = '"+dane[3]+"'";
                myRs = myStat.executeQuery(sql);
                if(!myRs.next()) {

                     sql = "INSERT INTO pracownicy (imie, nazwisko, nr_telefonu, email, haslo, stawka, pracownik, uprawnienia) VALUES ('"+dane[0]+"', '"+dane[1]+"'," +
                            "'"+dane[2]+"', '"+dane[3]+"', '"+dane[4]+"','0', '0','"+view_of_rejestracja_pracownika.getUprawnieniaCB().getSelectedItem().toString()+"') ";

                    myStat.executeUpdate(sql);

                    myRs = myStat.executeQuery("SELECT * FROM pracownicy WHERE email = '" + dane[3] + "'");

                    if (myRs.next()) {
                        JOptionPane.showMessageDialog(null, "Rejestracja przebiegła pomyślnie.", "Rejestracja", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "Rejestracja nie powiodła się.", "Rejestracja", JOptionPane.ERROR_MESSAGE);

                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Użytkownik o podanym adresie email już istnieje.", "Rejestracja", JOptionPane.ERROR_MESSAGE);
                view_of_rejestracja.setVisible(false);
                view_of_rejestracja_pracownika.setVisible(false);
                view_of_logging.setIsVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            });

            view_of_rejestracja_pracownika.getCofnijButton().addActionListener(e1 -> {
                view_of_rejestracja_pracownika.setVisible(false);
                view_of_rejestracja.setIsVisible(true);
            });
            });

        view_of_rejestracja.getCofnijButton().addActionListener(e ->{
            view_of_rejestracja.setVisible(false);
            view_of_logging.setIsVisible(true);
        });



    }


    public LoginModel logowanie()
    {//String login, String haslo, int userType
        //userType 1-klient 2-pracownik 3-menadżer
        LoginModel user;

        int userType = view_of_logging.getLoggingOptionCB().getSelectedIndex();
        String login = view_of_logging.getTextField1().getText();
        String haslo = new String(view_of_logging.getPasswordField().getPassword());

        System.out.println(userType);

        switch (userType) {
            case 0:

                new KlientController(login,haslo);
                view_of_logging.setIsVisible(false);
                break;
            case 1:

                new PracownikController(login,haslo);
                view_of_logging.setIsVisible(false);

                break;
            case 2:
                new MenadzerController(login, haslo);
                view_of_logging.setIsVisible(false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userType);
        }
        return null;
    }
    }
