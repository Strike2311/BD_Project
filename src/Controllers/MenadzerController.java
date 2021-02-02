package Controllers;

import Models.MenadzerModel;
import Views.*;
import javax.swing.*;
import java.sql.SQLException;

public class MenadzerController {
    private MenadzerView view;
    private MenadzerModel model;
    private LoginView view_of_logging;


    public MenadzerController(String email, String haslo, LoginView view_of_logging)
    {this.view_of_logging = view_of_logging;
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

    public void init(){
        view_of_logging.setIsVisible(false);
        view.getDostawcyButton().addActionListener(e -> showDostawcy());
        view.getBudzetButton().addActionListener(e -> showBudzet());
        view.getSurowceButton().addActionListener(e -> showSurowce());
        view.getPracownicyButton().addActionListener(e -> showPracownicy());
        view.getWylogujButton().addActionListener(e -> showWyloguj());
    }

    private void showWyloguj() {
        view.setVisible(false);
        view_of_logging.getPasswordField().setText("");
        view_of_logging.getTextField1().setText("");
        view_of_logging.setIsVisible(true);
    }

    private void showPracownicy() {
        view.setVisible(false);
        NadajUprawnieniaView view_prac = new NadajUprawnieniaView(model.getMyStat(), model.getMyRs());
        view_prac.getAcceptButton().addActionListener(e -> {
            try
            {
                int column = 0;
                int row = view_prac.getNiezatwoerdzeniPracownicyTable().getSelectedRow();
                if(row != -1)
                {
                    String dane[] = {view_prac.getNiezatwoerdzeniPracownicyTable().getModel().getValueAt(row, column).toString()};
                    int index = Integer.parseInt(view_prac.getNiezatwoerdzeniPracownicyTable().getModel().getValueAt(row, column).toString());
                    if(index != 0)
                    {
                        model.nadanieUprawnien(model.getMyRs(), model.getMyStat(), dane);
                        view_prac.refresh_zatwierdzonych(model.getMyStat(), model.getMyRs());
                        view_prac.refresh_niezatwierdzonych(model.getMyStat(), model.getMyRs());
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Wybierz wiersz",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
        });
        view_prac.getStawkaButton().addActionListener(e -> {
            view.setVisible(false);
            try
            {
                int column = 0;
                int row = view_prac.getZatwierdzeniPracownicyTable().getSelectedRow();
                if(row != -1)
                {
                    String stawka = view_prac.getStawkaTextField().getText();
                    if(stawka.length() > 3 || stawka.length() < 2)
                    {
                        JOptionPane.showMessageDialog(null, "Podaj poprawną stawkę",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (stawka.length() < 4 && stawka.length() > 1)
                    {
                        char c;
                        int digitCount = 0;
                        for (int j = 0; j < stawka.length(); j++) {
                            c = stawka.charAt(j);
                            if (Character.isDigit(c)) {
                                digitCount++;
                            }
                        }
                        if(digitCount != stawka.length())
                        {
                            JOptionPane.showMessageDialog(null, "Podaj poprawną stawkę",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            String dane[] = {view_prac.getZatwierdzeniPracownicyTable().getModel().getValueAt(row, column).toString(), stawka};
                            int index = Integer.parseInt(view_prac.getZatwierdzeniPracownicyTable().getModel().getValueAt(row, column).toString());
                            System.out.println(index);
                            if(index != 0)
                            {
                                model.nadzorWynagrodzen(model.getMyRs(), model.getMyStat(), dane);
                                view_prac.refresh_zatwierdzonych(model.getMyStat(), model.getMyRs());
                                view_prac.refresh_niezatwierdzonych(model.getMyStat(), model.getMyRs());
                            }
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Wybierz wiersz",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }

        });
        view_prac.getWsteczButton().addActionListener(e -> {
            view.setVisible(true);
            view_prac.dispose();
        });
    }

    private void showSurowce() {

        SurowceView view_surowce = new SurowceView(model.getMyStat(), model.getMyRs());
        view.setVisible(false);
        view_surowce.getZamowButton().addActionListener(e ->
        {
            int column = 1;
            int row = view_surowce.getDostawcyTable().getSelectedRow();
            if(row != -1)
            {
                view_surowce.setVisible(false);
                String nazwa = view_surowce.getDostawcyTable().getModel().getValueAt(row, column).toString();
                if (nazwa != null) {

                    ZamowSurowiecView zamawianie_surowcow_view = new ZamowSurowiecView(model.getMyStat(), model.getMyRs(), nazwa);
                    zamawianie_surowcow_view.getZmowButton().addActionListener(e1 -> {
                        try {
                            int column2 = 0;
                            int row2 = zamawianie_surowcow_view.getDostawcyTable().getSelectedRow();
                            if(row2 != -1)
                            {
                                String id_dost = zamawianie_surowcow_view.getDostawcyTable().getModel().getValueAt(row2, column2).toString();
                                String ilosc = zamawianie_surowcow_view.getIloscTextField().getText();
                                if(ilosc.length() > 5 || ilosc.length() < 1 || ilosc.equals("0"))
                                {
                                    JOptionPane.showMessageDialog(null, "Wprowadź poprawną ilość",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
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
                                        JOptionPane.showMessageDialog(null, "Wprowadź poprawną ilość",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    else
                                    {
                                        String dane[] = {id_dost, ilosc, nazwa};
                                        model.zamowienieSurowcow(model.getMyStat(), model.getMyRs(), dane);
                                        view_surowce.refresh(model.getMyStat(), model.getMyRs());
                                        JOptionPane.showMessageDialog(null,
                                                "Poprawnie dodano surowce");
                                    }
                                }

                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Wybierz wiersz",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }catch (SQLException x) {
                            JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            x.printStackTrace();
                        }
                    });
                    zamawianie_surowcow_view.getWsteczButton().addActionListener(e1 ->
                    {
                        zamawianie_surowcow_view.dispose();
                        view_surowce.setVisible(true);
                    });

                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Wybierz wiersz",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        view_surowce.getWsteczButton().addActionListener(e ->
        {
            view_surowce.dispose();
            view.setVisible(true);
        });
    }

    private void showBudzet(){
        view.setVisible(false);
        try{
            float roznica = model.podgladBudzetu(model.getMyStat(), model.getMyRs());
            BudzetView view_budzet = new BudzetView(roznica);
            view_budzet.getCofnijButton().addActionListener(e -> {
                view_budzet.dispose();
                view.setVisible(true);
            });

        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }

    private void showDostawcy() {
        view.setVisible(false);
        DostawcyView view_dostawcy = new DostawcyView(model.getMyStat(), model.getMyRs());
        view_dostawcy.getAddButton().addActionListener(e ->
        {   view_dostawcy.setVisible(false);
            DodanieDostawcyView view_dadaj_dostawce = new DodanieDostawcyView();
            view_dadaj_dostawce.getAddButton().addActionListener(e1 ->
            {

                String nazwa = view_dadaj_dostawce.getNazwaTextField().getText();
                String lok = view_dadaj_dostawce.getLokalizacjaTextField().getText();
                String sur = view_dadaj_dostawce.getSurowceComboBox().getSelectedItem().toString();
                String cena = view_dadaj_dostawce.getCenaTextField().getText();
                String odl = view_dadaj_dostawce.getOdlegloscTextField().getText();
                String dane[] = {nazwa, lok, sur, cena, odl};
                if(check_data(dane))
                {
                    try{


                        model.dodajDostawce(model.getMyStat(), dane);
                        view_dostawcy.refresh(model.getMyStat(), model.getMyRs());
                        JOptionPane.showMessageDialog(null,
                                "Dodano dostawcę");

                    } catch (SQLException x) {
                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        x.printStackTrace();
                    }
                }
            });
            view_dadaj_dostawce.getCloseButton().addActionListener(e1 ->
                    {
                        view_dadaj_dostawce.setVisible(false);
                        view_dostawcy.setVisible(true);
                    }
            );
        });
        view_dostawcy.getRemoveButton().addActionListener(e ->
        {
            try{

                int column = 0;
                int row = view_dostawcy.getDostawcyTable().getSelectedRow();
                if(row == -1)
                {
                    JOptionPane.showMessageDialog(null,
                            "Wybierz wiersz",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    int value = Integer.parseInt(view_dostawcy.getTableModel().getValueAt(row, column).toString());
                    model.usunDostawce(model.getMyStat(), value);
                    view_dostawcy.refresh(model.getMyStat(), model.getMyRs());
                }
            } catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
        });
        view_dostawcy.getCloseButton().addActionListener(e ->
        {
            view_dostawcy.setVisible(false);
            view.setVisible(true);
        });
    }

    public boolean check_data(String[] dane)
    {
        if(dane[0].length() > 44 || dane[0].length() < 3)
        {
            JOptionPane.showMessageDialog(null, "Zła nazwa",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dane[1].length() < 45 && dane[1].length() > 2) {
            char c;
            int letterCount = 0;
            for (int j = 0; j < dane[1].length(); j++) {
                c = dane[1].charAt(j);
                if (Character.isLetter(c)) {
                    letterCount++;
                }
            }
            if(letterCount != dane[1].length())
            {
                JOptionPane.showMessageDialog(null, "Zła lokalizacja",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Zła lokalizacja",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dane[3].length() < 5 && dane[3].length() > 0)
        {
            char c;
            int digitCount = 0;
            for (int j = 0; j < dane[3].length(); j++) {
                c = dane[3].charAt(j);
                if (Character.isDigit(c)) {
                    digitCount++;
                }
            }
            if(digitCount != dane[3].length())
            {
                JOptionPane.showMessageDialog(null, "Zła cena",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Zła cena",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(dane[4].length() < 5 && dane[4].length() > 0)
        {
            char c;
            int digitCount = 0;
            for (int j = 0; j < dane[4].length(); j++) {
                c = dane[4].charAt(j);
                if (Character.isDigit(c)) {
                    digitCount++;
                }
            }
            if(digitCount != dane[4].length())
            {
                JOptionPane.showMessageDialog(null, "Zła odleglość",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Zła odleglość",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
