/*
  LABORATORIO INTERDISCIPLINARE A - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.centrivaccinali.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.example.common.CentroVaccinale;
import org.example.common.Indirizzo;

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
    public GridPane grid = new GridPane();
    @FXML
    public Label ViaLabel;
    @FXML
    public HBox ricercaNome;
    @FXML
    public Button bottoneRicercaNome;
    @FXML
    public HBox ricercaComuneTipolgia;
    @FXML
    public TextField comuneDaRicercare;
    @FXML
    public ComboBox<String> tipologiaDaRicercare = new ComboBox();
    @FXML
    public Button bottoneRicercaComuneTipologia;

    ToggleGroup selectionToggleGroup = new ToggleGroup();

    @FXML
    public RadioButton radioButtonNome;
    @FXML
    public RadioButton radioButtonComuneTipologia;


    private List<CentroVaccinale> centriVaccinali = new ArrayList<>();
    private Image image;
    private CercaCVListener cercaCVListener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ricercaNome.setVisible(true);
        ricercaComuneTipolgia.setVisible(false);

        ////////////// COMBO BOX TIPOLOGIA //////////////
        tipologiaDaRicercare.setValue("");

        tipologiaDaRicercare.getItems().add("Ospedaliero");
        tipologiaDaRicercare.getItems().add("Aziendale");
        tipologiaDaRicercare.getItems().add("Hub");
        /////////////////////////////////////////////////

        radioButtonNome.setToggleGroup(selectionToggleGroup);
        radioButtonComuneTipologia.setToggleGroup(selectionToggleGroup);

        radioButtonNome.setSelected(true);

        selectionToggleGroup.selectedToggleProperty()
                .addListener((observable, oldVal, newVal) -> this.cambiaRicerca(newVal));
    }

    private void cambiaRicerca(Toggle newVal) {
        if(newVal.equals(radioButtonNome)){
            ricercaNome.setVisible(true);
            ricercaComuneTipolgia.setVisible(false);
        }else {
            ricercaNome.setVisible(false);
            ricercaComuneTipolgia.setVisible(true);
        }
    }

    public List<CentroVaccinale> ricercaNome() throws SQLException, RemoteException {
        String nome = nomeDaRicercare.getText().strip();
        //todo effettuare il controllo che non sia presente la stringa vuota
        System.err.println(nome);
        return ClientCVController.stub.cercaCentroVaccinaleNome(nome);
    }

    public List<CentroVaccinale> ricercaComuneTipologia() throws SQLException, RemoteException {
        //todo come ricercaNome ma con comune e tipologia
        //todo effettuare il controllo che non sia presente la stringa vuota
        return ClientCVController.stub.cercaCentroVaccinaleNome("poi modificare con ricerca comune e tipologia");
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

    //metodo collegato al bottone di ricerca per nome
    public void invioRicercaNome(ActionEvent actionEvent) {

        try {
            centriVaccinali.addAll(ricercaNome());
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

    public void invioRicercaComuneTipologia(ActionEvent actionEvent) {
        //todo come invioricercanome ma con comune e tipologia
    }

    public void onClickQuit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void to_02CT_MainWindow(ActionEvent actionEvent) throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }

}


