package org.example.centrivaccinali.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.example.common.CentroVaccinale;
import org.example.common.Indirizzo;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client03CT_CercaCV_Controller {

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
    public GridPane grid = new GridPane();
    @FXML
    public Label ViaLabel;

    private List<CentroVaccinale> centriVaccinali = new ArrayList<>();
    private Image image;
    private CercaCVListener cercaCVListener;


    public List<CentroVaccinale> ricerca() throws SQLException, RemoteException {
        String nome = nomeDaRicercare.getText().strip();
        System.err.println(nome);
        return ClientCVController.stub.cercaCentroVaccinaleNome(nome);
    }

    private void impostaCVscelto(CentroVaccinale centroVaccinale) {
        CVNameLable.setText(centroVaccinale.getNome());
        TipologiaLabel.setText(centroVaccinale.getTipologia().name());

        Indirizzo indirizzo = centroVaccinale.getIndirizzo();
        ViaLabel.setText(indirizzo.getQualificatore() + " " + indirizzo.getNome() + " " + indirizzo.getNumeroCivico());
        IndirizzoLabel.setText(indirizzo.getComune() + " " + indirizzo.getCap() + " " + indirizzo.getProv());
        idCentroVacinaleLabel.setText(centroVaccinale.getIdCentroVacciale());
        chosenCV.setStyle("-fx-background-radius: 30;");
    }

    private void resetItem() {
        centriVaccinali.clear();    //Cancella l' ArrayList che contiene tutti i risultati ricevuti dal server
        System.err.println("CANCELLAZIONE GRIGLIA!!!");
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
                grid.getChildren().clear();     //Puliamo la GridPane prima di stampare i nuovi risultati di ricerca
                for (int i = 0; i < centriVaccinali.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader(ClientCVMain.class.getResource("item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(centriVaccinali.get(i), cercaCVListener);

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
        resetItem();
    }

    public void onClickQuit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void to_02CT_MainWindow(ActionEvent actionEvent) throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }
}


