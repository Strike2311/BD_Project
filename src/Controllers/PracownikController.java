package Controllers;

import Models.PracownikModel;
import Views.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikController {
    private PracownikHomeView view;
    private PracownikModel model;
    private LoginView view_of_logging;
    private String hours;
    private ResultSet rs;

    public PracownikController(String email, String haslo, LoginView view_of_logging)
    {
        this.view_of_logging = view_of_logging;
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
        view.getNadanieZamowienButton().addActionListener(e -> showZamowienia());
        view.getRejetracjaGodzinPracyButton().addActionListener(e -> godzinyPracy());
        view.getDodajPartieButton().addActionListener(e -> wprowadzPartie());
        view.getWprowadzenieZmianIlosciProduktówButton().addActionListener(e -> wprowadzZmianeSurowcow());
        view.getWylogujButton().addActionListener(e -> {
            view.dispose();
            view_of_logging.getPasswordField().setText("");
            view_of_logging.getTextField1().setText("");
            view_of_logging.setIsVisible(true);
        });
    }

    private void wprowadzZmianeSurowcow() {
        ZmianaSurowcowView view_zmiana_sur = new ZmianaSurowcowView(model.getMyStat(), model.getMyRs());
        view.setVisible(false);
        view_zmiana_sur.getZatwierdźButton().addActionListener(e -> {
            try {
                int column = 0;
                int row = view_zmiana_sur.getSurowceTable().getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(null,
                            "Wybierz wiersz",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    String id_sur= view_zmiana_sur.getSurowceTable().getModel().getValueAt(row, column).toString();
                    column = 3;
                    String aktualna_ilosc = view_zmiana_sur.getSurowceTable().getModel().getValueAt(row, column).toString();

                    String ilosc = view_zmiana_sur.getPobranaIloscTextField().getText();
                    String dane[] = {id_sur, ilosc};

                    char c;
                    int digitCount = 0;

                    for (int j = 0; j < ilosc.length(); j++) {
                        c = ilosc.charAt(j);
                        if (Character.isDigit(c)) {
                            digitCount++;
                        }
                    }

                    if(digitCount != ilosc.length())
                    {
                        JOptionPane.showMessageDialog(null,
                                "Niepoprawne dane",
                                "Błąd",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if(ilosc.length() == 0 || (float)Integer.parseInt(ilosc) > Float.parseFloat(aktualna_ilosc))
                    {
                        JOptionPane.showMessageDialog(null,
                                "Niepoprawne dane",
                                "Błąd",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        model.wprowadzenieZmianyStanuZasobow(model.getMyStat(), model.getMyRs(), dane);
                        view_zmiana_sur.refresh(model.getMyStat(), model.getMyRs());
                    }
                }
            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
        });
        view_zmiana_sur.getWsteczButton().addActionListener(e -> {
            view_zmiana_sur.dispose();
            view.setVisible(true);
        });
    }

    private void wprowadzPartie() {

        PartieView partie_view = new PartieView(model.getMyStat(), model.getMyRs());
        partie_view.getDodajPartieButton().addActionListener(e -> {

            partie_view.setVisible(false);
            WprowdzPartieView dodaj_partie_view = new WprowdzPartieView(model.getMyStat(), model.getMyRs());
            dodaj_partie_view.getDodajPartieButton().addActionListener(e1 -> {

                try {
                    int column = 0;
                    int row = dodaj_partie_view.getProduktTable().getSelectedRow();
                    String id_prod = dodaj_partie_view.getProduktTable().getModel().getValueAt(row, column).toString();
                    row = dodaj_partie_view.getPakowanieTable().getSelectedRow();
                    String id_pak = dodaj_partie_view.getPakowanieTable().getModel().getValueAt(row, column).toString();
                    String dane[] = {id_prod, id_pak};
                    model.dodajPartie(model.getMyRs(), model.getMyStat(), dane);
                    partie_view.refresh(model.getMyStat(), model.getMyRs());
                }catch (SQLException x) {
                    JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    x.printStackTrace();
                }

            });
            dodaj_partie_view.getWsteczButton().addActionListener(e1 -> {
                dodaj_partie_view.dispose();
                partie_view.setVisible(true);
            });

        });
        partie_view.getWsteczButton().addActionListener(e -> {
            partie_view.dispose();
            view.setVisible(true);
        });

    }

    private void godzinyPracy() {


        refresh_godz_pracy();

        GodzinyPracyView godziny_view = new GodzinyPracyView(hours);
        godziny_view.getZatwierdzButton().addActionListener(e -> {

            try
            {
                view.setVisible(false);
                String godziny = godziny_view.getWprowadzGodzinyTextField().getText();
                if(godziny.length() > 2 || godziny.length() == 0)
                {
                    JOptionPane.showMessageDialog(null,
                            "Niepoprawne dane",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    char c;
                    int digitCount = 0;

                    for (int j = 0; j < godziny.length(); j++) {
                        c = godziny.charAt(j);
                        if (Character.isDigit(c)) {
                            digitCount++;
                        }
                    }
                    if(digitCount != godziny.length() || Integer.parseInt(godziny) > 12 || Integer.parseInt(godziny) < 1)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Niepoprawne dane",
                                "Błąd",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        model.rejestracjaGodzinPracy(model.getMyRs(), model.getMyStat(), godziny, model.getId_prac());
                        refresh_godz_pracy();
                        godziny_view.getGodzinyPracyTextField().setText(hours);
                    }
                }
            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
        });
        godziny_view.getWsteczButton().addActionListener(e -> {
            godziny_view.dispose();
            view.setVisible(true);
        });
    }
    void refresh_godz_pracy()
    {
        try {
            String sql = "SELECT liczba_godzin FROM etat  WHERE etat.Pracownicy_id_pracownicy = '" + model.getId_prac() + "';";
            model.getMyStat().executeQuery(sql);

            rs = model.getMyRs() ;
            rs = model.getMyStat().executeQuery(sql);
            rs.next();
            String hrs = rs.getString("liczba_godzin");
            hours = hrs;

        }catch (SQLException x) {
            JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            x.printStackTrace();
        }
    }

    private void showZamowienia() {
        ZamowieniaView view_zamowienia = new ZamowieniaView(model.getMyStat(), model.getMyRs());
        view_zamowienia.getNadajButton().addActionListener(e1 -> {
            try
            {
                view.setVisible(false);
                int column = 0;
                int row = view_zamowienia.getZamowieniaTable().getSelectedRow();
                String dane = view_zamowienia.getZamowieniaTable().getModel().getValueAt(row, column).toString();
                int index = Integer.parseInt(view_zamowienia.getZamowieniaTable().getModel().getValueAt(row, column).toString());
                if(index != 0)
                {
                    model.nadawanieZamowien(model.getMyRs(), model.getMyStat(), dane);
                    JOptionPane.showMessageDialog(null,
                            "Poprawnie nadano");
                    view_zamowienia.refresh(model.getMyStat(), model.getMyRs());
                }
            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
        });
        view_zamowienia.getCloseButton().addActionListener(e -> {
            view_zamowienia.dispose();
            view.setVisible(true);
        });
    }

}
