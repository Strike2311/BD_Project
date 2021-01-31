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
                String dane[] = {view_prac.getNiezatwoerdzeniPracownicyTable().getModel().getValueAt(row, column).toString()};
                int index = Integer.parseInt(view_prac.getNiezatwoerdzeniPracownicyTable().getModel().getValueAt(row, column).toString());
                if(index != 0)
                {
                    model.nadanieUprawnien(model.getMyRs(), model.getMyStat(), dane);
                    view_prac.refresh_zatwierdzonych(model.getMyStat(), model.getMyRs());
                    view_prac.refresh_niezatwierdzonych(model.getMyStat(), model.getMyRs());
                }
            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
            view.setVisible(true);
            view_prac.setVisible(false);
        });
        view_prac.getStawkaButton().addActionListener(e -> {
            view.setVisible(false);
            try
            {
                int column = 0;
                int row = view_prac.getZatwierdzeniPracownicyTable().getSelectedRow();

                String stawka = view_prac.getStawkaTextField().getText();
                String dane[] = {view_prac.getZatwierdzeniPracownicyTable().getModel().getValueAt(row, column).toString(), stawka};
                int index = Integer.parseInt(view_prac.getZatwierdzeniPracownicyTable().getModel().getValueAt(row, column).toString());
                    System.out.println(index);
                    if(index != 0)
                    {
                        model.nadzorWynagrodzen(model.getMyRs(), model.getMyStat(), dane);
                        view_prac.refresh_zatwierdzonych(model.getMyStat(), model.getMyRs());
                        view_prac.refresh_niezatwierdzonych(model.getMyStat(), model.getMyRs());
                    }
            }catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
            view_prac.setVisible(false);
            view.setVisible(true);

        });
        view_prac.getWsteczButton().addActionListener(e -> {
            view.setVisible(true);
            view_prac.setVisible(false);
        });
    }

    private void showSurowce() {

        SurowceView view_surowce = new SurowceView(model.getMyStat(), model.getMyRs());
        view.setVisible(false);
        view_surowce.getZamowButton().addActionListener(e ->
        {   view_surowce.setVisible(false);
            int column = 1;
            int row = view_surowce.getDostawcyTable().getSelectedRow();
            String nazwa = view_surowce.getDostawcyTable().getModel().getValueAt(row, column).toString();
            if (nazwa != null) {

                ZamowSurowiecView zamawianie_surowcow_view = new ZamowSurowiecView(model.getMyStat(), model.getMyRs(), nazwa);
                zamawianie_surowcow_view.getZmowButton().addActionListener(e1 -> {
                    try {
                        int column2 = 0;
                        int row2 = zamawianie_surowcow_view.getDostawcyTable().getSelectedRow();
                        int value = Integer.parseInt(zamawianie_surowcow_view.getDostawcyTable().getModel().getValueAt(row2, column2).toString());
                        int ilosc = Integer.parseInt(zamawianie_surowcow_view.getIloscTextField().getText());
                        String dane[] = {"" + value, "" + ilosc, nazwa};
                        model.zamowienieSurowcow(model.getMyStat(), model.getMyRs(), dane);
                        view_surowce.refresh(model.getMyStat(), model.getMyRs());
                        JOptionPane.showMessageDialog(null,
                                "Poprawnie dodano surowce");
                    }catch (SQLException x) {
                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        x.printStackTrace();
                    }
                });
                zamawianie_surowcow_view.getWsteczButton().addActionListener(e1 ->
                {
                    zamawianie_surowcow_view.setVisible(false);
                    view_surowce.setVisible(true);
                });

            }
        });
        view_surowce.getWsteczButton().addActionListener(e ->
        {
            view_surowce.setVisible(false);
            view.setVisible(true);
        });
    }

    private void showBudzet(){
        view.setVisible(false);
        try{
            float roznica = model.podgladBudzetu(model.getMyStat(), model.getMyRs());
            BudzetView view_budzet = new BudzetView(roznica);
            view_budzet.getCofnijButton().addActionListener(e -> {
                view_budzet.setVisible(false);
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
                try{
                    String dane[] = {view_dadaj_dostawce.getNazwaTextField().getText(),
                        view_dadaj_dostawce.getLokalizacjaTextField().getText(),
                        view_dadaj_dostawce.getSurowiecTextField().getText(),
                        view_dadaj_dostawce.getCenaTextField().getText(),
                        view_dadaj_dostawce.getOdlegloscTextField().getText()};
                    model.dodajDostawce(model.getMyStat(), dane);
                    view_dostawcy.refresh(model.getMyStat(), model.getMyRs());
                    JOptionPane.showMessageDialog(null,
                            "Dodano dostawcę");
                } catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
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
                int value = Integer.parseInt(view_dostawcy.getTableModel().getValueAt(row, column).toString());
                model.usunDostawce(model.getMyStat(), value);
                view_dostawcy.refresh(model.getMyStat(), model.getMyRs());

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
}
