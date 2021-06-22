package org.example.centrivaccinali.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.example.centrivaccinali.TipologiaCV;
import org.example.common.CentroVaccinale;
import org.example.common.Indirizzo;
import org.example.common.Qualificatore;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Client03CT_CercaCV_Controller implements Initializable {

    @FXML
    public Button bottoneRicerca;
    @FXML
    public TextField nomeDaRicercare;
    @FXML
    public Label CVNameLable;
    @FXML
    public Label TipologiaLabel;
    @FXML
    public ImageView CVImg;
    @FXML
    public VBox chosenCV;
    @FXML
    public Label IndirizzoLabel;
    @FXML
    public Label idCentroVacinaleLabel;
    @FXML
    public GridPane grid;
    @FXML
    public Label ViaLabel;

    private List<CentroVaccinale> centriVaccinali = new ArrayList<>();
    private Image image;
    private CercaCVListener cercaCVListener;


    public List<CentroVaccinale> ricerca() throws SQLException, RemoteException {
        //String nome = nomeDaRicercare.getText().strip();

        //System.err.println(nome);

        //ArrayList prova = ClientCVController.stub.cercaCentroVaccinaleNome(nome);
        //System.err.println(prova);
        ArrayList<CentroVaccinale> ct = new ArrayList<>();
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 1", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Ospedaliero));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 2", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));

        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));

        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        ct.add(new CentroVaccinale("006066a7-8726-47bc-8764-7fc58858d633", "prova 3", new Indirizzo(Qualificatore.Corso, "Basadonne", "129A", "Assago", 22072, "MI"), TipologiaCV.Hub));
        return ct;
    }

    private void impostaCVscelto(CentroVaccinale centroVaccinale) {
        CVNameLable.setText(centroVaccinale.getNome());
        TipologiaLabel.setText(centroVaccinale.getTipologia().name());

        Indirizzo indirizzo = centroVaccinale.getIndirizzo();
        ViaLabel.setText(indirizzo.getQualificatore() + " " + indirizzo.getNome() + " " + indirizzo.getNumeroCivico());
        IndirizzoLabel.setText(indirizzo.getComune() + " " + indirizzo.getCap() + " " + indirizzo.getProv());
        idCentroVacinaleLabel.setText(centroVaccinale.getIdCentroVacciale());
        //image = new Image("src/main/resources/org/example/images/primula.png");
        //CVImg.setImage(image);

        //chosenCV.setStyle("-fx-background-color: #" + "95897f" + ";\n" +  //setta il colore di sfondo del CV scelto
               // "    -fx-background-radius: 30;");
        chosenCV.setStyle("-fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            centriVaccinali.addAll(ricerca());
        } catch (Exception e) {
            System.err.println("ERRORE NELLA CLASSE: Client03CT_CercaCV_Controller");
            e.printStackTrace();
        }

        if (centriVaccinali.size() > 0) {
            impostaCVscelto(centriVaccinali.get(0));
            cercaCVListener = new CercaCVListener() {
                @Override
                public void onClickListener(CentroVaccinale centroVaccinale) {
                    impostaCVscelto(centroVaccinale);
                }
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < centriVaccinali.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Client03CT_CercaCV_Controller.class.getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(centriVaccinali.get(i),cercaCVListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(ActionEvent actionEvent) {
        try {
            centriVaccinali.addAll(ricerca());
        } catch (Exception e) {
            System.err.println("ERRORE NELLA CLASSE: Client03CT_CercaCV_Controller");
            e.printStackTrace();
        }

        if (centriVaccinali.size() > 0) {
            impostaCVscelto(centriVaccinali.get(0));
            cercaCVListener = new CercaCVListener() {
                @Override
                public void onClickListener(CentroVaccinale centroVaccinale) {
                    impostaCVscelto(centroVaccinale);
                }
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < centriVaccinali.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("src/main/resources/org/example/centrivaccinali/gui/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(centriVaccinali.get(i),cercaCVListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

