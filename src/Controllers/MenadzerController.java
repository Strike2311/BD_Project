package Controllers;

import Models.MenadzerModel;
import Views.*;

import javax.swing.*;
import java.sql.SQLException;

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

    public void init(){
        view.getDostawcyButton().addActionListener(e -> showDostawcy());
        view.getBudzetButton().addActionListener(e -> showBudzet());
    }

    private void showBudzet(){
        try{
            float roznica = model.podgladBudzetu(model.getMyStat(), model.getMyRs());
            new BudzetView(roznica);
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }

    private void showDostawcy() {

        DostawcyView view_dostawcy = new DostawcyView(model.getMyStat(), model.getMyRs());
        view_dostawcy.getAddButton().addActionListener(e ->
        {
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
                } catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
                }
            });
            view_dadaj_dostawce.getCloseButton().addActionListener(e1 ->
                    {
                        //TODO zamykanie okna
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
            //TODO Cofanie
        });
    }
}
