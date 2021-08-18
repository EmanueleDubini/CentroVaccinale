/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

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
import javafx.scene.Node;
import javafx.scene.control.*;
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
    public ComboBox<String> tipologiaDaRicercare = new ComboBox<>();
    @FXML
    public Button bottoneRicercaComuneTipologia;

    ToggleGroup selectionToggleGroup = new ToggleGroup();

    @FXML
    public RadioButton radioButtonNome;
    @FXML
    public RadioButton radioButtonComuneTipologia;


    private final List<CentroVaccinale> centriVaccinali = new ArrayList<>();
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
        String nomeCV = nomeDaRicercare.getText().strip();
        //effettuano il controllo che non sia presente la stringa vuota
        if(nomeCV.equals("")){
            return null;
        }else{

        return ClientCVController.stub.cercaCentroVaccinaleNome(nomeCV);
        }
    }

    public List<CentroVaccinale> ricercaComuneTipologia() throws SQLException, RemoteException {
        String nomeComune = comuneDaRicercare.getText().strip();
        String tipologiaCV = tipologiaDaRicercare.getValue();
        //effettuano il controllo che non sia presente la stringa vuota
        if(nomeComune.equals("") || tipologiaCV.equals("")){
            return null;
        }else{

            return ClientCVController.stub.cercaCentroVaccinaleComuneTipologia(nomeComune, tipologiaCV);
        }
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
        centriVaccinali.clear(); //Cancella l' ArrayList che contiene tutti i risultati ricevuti dal server
        nomeDaRicercare.setText("");
        comuneDaRicercare.setText("");
        tipologiaDaRicercare.setValue("");
    }

    //metodo collegato al bottone di ricerca per nome
    public void invioRicerca(ActionEvent actionEvent) {

        List<CentroVaccinale> risultati;

        Node source = (Node) actionEvent.getSource();

        try {
            //if per capire se viene effettuata una ricerca per nome o una ricerca per comune e tipologia
            if(source.getId().equals("bottoneRicercaNome")){
                risultati = ricercaNome();
            }
            //altrimenti eseguo una ricerca per comune e tipologia
            else{
                risultati = ricercaComuneTipologia();
            }


            //una volta effettuata la ricerca, se il risultato contiene le ricerche:
            if(risultati != null){
                //aggiunge tutti i risultati alla lista dei CV da visualizzare
                centriVaccinali.addAll(risultati);

                //imposta il centro vaccinale da visualizzare nel riquadro di lato a sinistra, il primo della lista
                if (centriVaccinali.size() > 0) {
                    //prendo il primo
                    impostaCVscelto(centriVaccinali.get(0));
                    cercaCVListener = new CercaCVListener() {
                        @Override
                        public void onClickListener(CentroVaccinale centroVaccinale) {
                            impostaCVscelto(centroVaccinale);
                        }
                    };
                }
                //se non viene restituito nulla dalla ricerca ma era stato scritto qualcosa nella casella di testo:
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nessun Risultato");
                    alert.setHeaderText("Nessun elemento corrisponde alla ricerca");
                    alert.setContentText("La ricerca non ha portato risultati.\nProva un altro criterio di ricerca");

                    alert.showAndWait();
                }

            }else{
                //se quello che viene restituito dalla ricerca e null vuol dire che non e stato scritt nulla nel textfiel della rierca
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Si è verificato un Errore");
                alert.setContentText("Campo Ricerca Vuoto.\nInserisci un criterio di ricerca");

                alert.showAndWait();
            }
        } catch (Exception e) {
            System.err.println("ERRORE NELLA CLASSE: Client03CT_CercaCV_Controller");
            e.printStackTrace();
        }


            int column = 0;
            int row = 1;
            try {
                grid.getChildren().clear();     //Puliamo la GridPane prima di stampare i nuovi risultati di ricerca
                for (CentroVaccinale centroVaccinale : centriVaccinali) {

                    FXMLLoader fxmlLoader = new FXMLLoader(ClientCVMain.class.getResource("item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(centroVaccinale, cercaCVListener);

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


