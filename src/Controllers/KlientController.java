package Controllers;

import Models.KlientModel;
import Views.KlientHomeView;
import Views.LoginView;
import Views.ZamówTowarView;
import Views.ZarzadzajZamowieniamiView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class KlientController {
    private KlientHomeView view;
    private KlientModel model;
    private LoginView view_of_logging;

    public KlientController(String email, String haslo, LoginView view_of_logging)
    {this.view_of_logging = view_of_logging;
        model = new KlientModel(email, haslo);
        if(model.getCzyZalogowany())
        {
            view = new KlientHomeView();

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
        view_of_logging.setIsVisible(false);
        view.getZamówTowarButton().addActionListener(e -> showZamowTowar());
        view.getzarządzajZamówieniamiButton().addActionListener(e -> {
            try {
                showZarzadzajZamowieniami();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
      view.getWylogujButton().addActionListener(e ->{
          view.setVisible(false);
          view_of_logging.getPasswordField().setText("");
          view_of_logging.getTextField1().setText("");
          view_of_logging.setIsVisible(true);
      });

    }

    private void showZarzadzajZamowieniami() throws SQLException, ArrayIndexOutOfBoundsException {
        view.setIsVisible(false);
        ZarzadzajZamowieniamiView view_zarzadzaj_zamowieniami = new ZarzadzajZamowieniamiView(model.getMyStat(),model.getMyRs(),model.getEmail());
        view_zarzadzaj_zamowieniami.getZwróćButton().addActionListener(e->{
            try{

                int column = 0;
                int row = view_zarzadzaj_zamowieniami.getTabelaZarzadzajZamowienia().getSelectedRow();
                String value = view_zarzadzaj_zamowieniami.getTableModel().getValueAt(row, column).toString();

                model.zgloszenieReklamacji(model.getMyRs(), model.getMyStat(), value);
                view_zarzadzaj_zamowieniami.refresh(model.getMyStat(), model.getMyRs(), model.getEmail());

            } catch (SQLException x) {
                JOptionPane.showMessageDialog(null, x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException e1){

            }
        });
        view_zarzadzaj_zamowieniami.getCofnijButton().addActionListener(e->{
            view_zarzadzaj_zamowieniami.setVisible(false);
            view.setVisible(true);
        });
    }

    private void showZamowTowar() {
        view.setVisible(false);
        try {

            ZamówTowarView view_zamow_towar = new ZamówTowarView(model.getMyStat(), model.getMyRs(), model.getEmail());
            view_zamow_towar.getCofnijButton().addActionListener(e->{
                view.setVisible(true);
                view_zamow_towar.setVisible(false);
            });


            view_zamow_towar.getZamówButton().addActionListener(e ->{
                try {
                    int row = view_zamow_towar.getTabelaZamowTowary().getSelectedRow();
                    if(row != -1) {
                        if (!view_zamow_towar.getTableModel().getValueAt(row, 5).toString().equals("niedostępna")) {
                            ArrayList<String> dane = new ArrayList<String>();
                            dane.add(view_zamow_towar.getTableModel().getValueAt(row, 0).toString());
                            dane.add(view_zamow_towar.getTableModel().getValueAt(row, 4).toString());
                            dane.add(view_zamow_towar.getIloscTextField().getText());

                            if(sprawdzeniePoprawnosciDanychZamowienie(view_zamow_towar.getIloscTextField().getText())) {
                                model.zamowienieDostawy(model.getMyRs(), model.getMyStat(), dane);
                                JOptionPane.showMessageDialog(null, "Poprawnie zakupiłeś produkt " + view_zamow_towar.getTableModel().getValueAt(row, 1).toString() + " w ilości: " +
                                        Integer.parseInt(view_zamow_towar.getIloscTextField().getText()) * Integer.parseInt(view_zamow_towar.getTableModel().getValueAt(row, 3).toString())
                                        + " paczek.", "Zakup", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Błąd wprowadzania danych.", "Zakup", JOptionPane.ERROR_MESSAGE);

                            }
                        } else
                            JOptionPane.showMessageDialog(null, "Wybrany produkt nie jest dostępny.", "Zakup", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Należy wybrać wiersz z tabeli.", "Zakup", JOptionPane.ERROR_MESSAGE);

                    }
                }catch(SQLException e1){

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.setIsVisible(false);

    }
    public boolean sprawdzeniePoprawnosciDanychZamowienie(String s){
        if (s.length()>0 && s.length()<10 && Float.parseFloat(s)>0){
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
        }
        return false;
    }
}
