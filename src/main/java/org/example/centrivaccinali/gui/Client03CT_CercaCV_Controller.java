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

import static org.example.centrivaccinali.gui.IpServerCheck_Controller.stub;

/**
 * Classe controller dedicata alla ricerca delle informazioni sui Centri Vaccinali
 */
public class Client03CT_CercaCV_Controller implements Initializable {
    /**
     * TextField nome da ricercare
     */
    @FXML
    public TextField nomeDaRicercare;
    /**
     * Label che contiene il nome del centro vaccinale ricercato
     */
    @FXML
    public Label CVNameLable;
    /**
     * Label tipologia
     */
    @FXML
    public Label TipologiaLabel;
    /**
     * Immagine logo cv
     */
    @FXML
    public ImageView CVImg;
    /**
     * VBox per la visualizzazione e la selezione dei cv cercati
     */
    @FXML
    public VBox chosenCV;
    /**
     * Label indirizzo
     */
    @FXML
    public Label IndirizzoLabel;
    /**
     * Label id centro Vaccinale
     */
    @FXML
    public Label idCentroVacinaleLabel;
    /**
     * GridPane
     */
    @FXML
    public GridPane grid = new GridPane();
    /**
     * Label via
     */
    @FXML
    public Label ViaLabel;
    /**
     * HBox ricerca nome
     */
    @FXML
    public HBox ricercaNome;
    /**
     * Bottone per la ricerca tramite nome
     */
    @FXML
    public Button bottoneRicercaNome;
    /**
     * HBox ricerca per comune e tipologia
     */
    @FXML
    public HBox ricercaComuneTipolgia;
    /**
     * TextField per la ricerca tramite comune
     */
    @FXML
    public TextField comuneDaRicercare;
    /**
     * ComboBox per ricercare la tipologia cv
     */
    @FXML
    public ComboBox<String> tipologiaDaRicercare = new ComboBox<>();
    /**
     * Bottone per la ricerca tramite il comune e tipologia
     */
    @FXML
    public Button bottoneRicercaComuneTipologia;
    /**
     * Label numero segnalazioni eventi avversi
     */
    @FXML
    public Label nSegnalazioni;
    /**
     * Label severitá media
     */
    @FXML
    public Label labelSeveritaMedia;

    ToggleGroup selectionToggleGroup = new ToggleGroup();
    /**
     * RadioButton nome cv
     */
    @FXML
    public RadioButton radioButtonNome;
    /**
     * RadioButton per il comune e tipologia
     */
    @FXML
    public RadioButton radioButtonComuneTipologia;


    private final List<CentroVaccinale> centriVaccinali = new ArrayList<>();
    private CercaCVListener cercaCVListener;

    /**
     * Questo metodo inizializza tutti i combobox presenti nella finestra e i bottoni per modificare il tipo di ricerca
     *
     * @param url ereditato dalla superclasse
     * @param resourceBundle ereditato dalla superclasse
     */
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

    /**
     * Questo metodo permette di modificare il tipo di ricerca da Nome a Comune&Tipologia e viceversa
     * @param newVal indica la selezione dell'utente
     */
    private void cambiaRicerca(Toggle newVal) {
        if(newVal.equals(radioButtonNome)){
            ricercaNome.setVisible(true);
            ricercaComuneTipolgia.setVisible(false);
        }else {
            ricercaNome.setVisible(false);
            ricercaComuneTipolgia.setVisible(true);
        }
    }

    /**
     * Questo metodo permette di ricercare i centri vaccinali per nome
     *
     * @return una lista che contiene i centri vaccinali cercati per nome
     *
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public List<CentroVaccinale> ricercaNome() throws SQLException, RemoteException {
        String nomeCV = nomeDaRicercare.getText().strip();
        //effettuano il controllo che non sia presente la stringa vuota
        if(nomeCV.equals("") || nomeCV.length() < 3){
            return null;
        }else{

        return stub.cercaCentroVaccinaleNome(nomeCV);
        }
    }

    /**
     * Questo metodo permette di ricercare i centri vaccinali per comune e tipologia
     *
     * @return una lista che contiene i centri vaccinali cercati per comune e tipologia
     *
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    public List<CentroVaccinale> ricercaComuneTipologia() throws SQLException, RemoteException {
        String nomeComune = comuneDaRicercare.getText().strip();
        String tipologiaCV = tipologiaDaRicercare.getValue();
        //effettuano il controllo che non sia presente la stringa vuota
        if(nomeComune.equals("") || tipologiaCV.equals("")){
            return null;
        }else{

            return stub.cercaCentroVaccinaleComuneTipologia(nomeComune, tipologiaCV);
        }
    }

    /**
     * Questo metodo inserisce le informazioni relative al centro vaccinale selezionate nel riquadro situato sulla parte
     * sinistra della finestra di ricerca dei cv
     *
     * @param centroVaccinale il centro vaccinale selezionato
     *
     * @throws SQLException SQLException
     * @throws RemoteException RemoteException
     */
    private void impostaCVscelto(CentroVaccinale centroVaccinale) throws SQLException, RemoteException {
        CVNameLable.setText(centroVaccinale.getNome());
        TipologiaLabel.setText(centroVaccinale.getTipologia().name());

        Indirizzo indirizzo = centroVaccinale.getIndirizzo();
        ViaLabel.setText(indirizzo.getQualificatore() + " " + indirizzo.getNome() + " " + indirizzo.getNumeroCivico());
        IndirizzoLabel.setText(indirizzo.getComune() + " " + indirizzo.getCap() + " " + indirizzo.getProv());

        String idCV = centroVaccinale.getIdCentroVacciale();
        idCentroVacinaleLabel.setText(idCV);

        //calcolo severità media
        double[] mediaSeveritaEventiAvversi = stub.getAvg_Nsegnalazioni(idCV);
        //caso in cui non ci sono aventi avversi nel centro mostrato
        if(mediaSeveritaEventiAvversi[0] == -1 ){
            labelSeveritaMedia.setText("N/D");
        }
        else{
            //se ci sono eventi avversi mostra la media
        labelSeveritaMedia.setText(String.valueOf(mediaSeveritaEventiAvversi[0]));
        }

        //calcolo numero segnalazioni
        nSegnalazioni.setText(String.valueOf((int)mediaSeveritaEventiAvversi[1]));

        //impostazione grafica
        chosenCV.setStyle("-fx-background-radius: 30;");
    }

    /**
     * Questo metodo permette di cancellare tutti i campi inseriti dall'utente dopo aver effettuato la ricerca
     *
     */
    private void resetItem() {
        centriVaccinali.clear(); //Cancella l' ArrayList che contiene tutti i risultati ricevuti dal server
        nomeDaRicercare.setText("");
        comuneDaRicercare.setText("");
        tipologiaDaRicercare.setValue("");
    }

    /**
     * Questo metodo definisce le azioni da eseguire al click del bottone ricerca
     * @param actionEvent actionEvent
     */
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
                        public void onClickListener(CentroVaccinale centroVaccinale) throws SQLException, RemoteException {
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
                alert.setContentText("Campo ricerca vuoto o minore di 3 caratteri.\nInserisci un criterio di ricerca");

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

    /**
     * Questo metodo, situatosi nella menu bar, permette l'uscita dalla finestra
     * @param actionEvent actionEvent
     */
    public void onClickQuit(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * Questo metodo permette di spostarsi nella finestra principale della sezione cittadini
     * @param actionEvent actionEvent
     * @throws IOException IOException
     */
    public void to_02CT_MainWindow(ActionEvent actionEvent) throws IOException {
        ClientCVMain.setRoot("02CT_MainWindow");
    }

    /**
     * Questo metodo crea un messaggio di Alert che restituisce informazioni riguardanti il progetto
     */
    public void guida(){
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Client CV

                        """, ButtonType.OK);

        info.showAndWait();
    }

    /**
     * Questo metodo crea un messaggio di Alert che restituisce informazioni riguardanti i componenti
     * del gruppo che hanno partecipato alla realizzazione del Progetto
     */

    public void info() {
        Alert info = new Alert(Alert.AlertType.NONE,
                """
                        'Centro Vaccinale' - Client CV

                        BANCORA Davide
                        CASALNOVO Giacomo
                        DONATO Benedetta
                        DUBINI Emanuele

                        UnInsubria.it
                        Copyleft - all rights reversed""", ButtonType.OK);

        info.showAndWait();
    }

}


