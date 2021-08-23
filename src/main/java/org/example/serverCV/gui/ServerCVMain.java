/*
  LABORATORIO INTERDISCIPLINARE B - Como AA20-21

  BANCORA Davide       | 743662 | Como
  CASALNOVO Giacomo    | 740003 | Como
  DONATO Benedetta     | 742957 | Como
  DUBINI Emanuele      | 740954 | Como

 */

package org.example.serverCV.gui;

import javafx.application.Platform;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe <code>ServerCVMain</code> contente i metodi utili alla parte grafica
 */
public class ServerCVMain extends Application {


    ///////////////////////////VERSIONE PRESENTE NEL PROGETTO ORIGINALE PRIMA DI IMPORTARE//////////////////////
    /*private static Stage stage;

    @Override
    /*public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("src/main/java/serverCV/test/ServerCV.fxml"));
        primaryStage.setTitle("Server Centro Vaccinale");
        primaryStage.setScene(new Scene(root));
        //primaryStage.show();
    }*/

    /*public void start(Stage primaryStage) throws Exception{
        Parent root = getParentFromFxml("src/main/resources/org/example/ServerCV.fxml");  //aggiornare con questa riga di codice e aggiungere il metodo sottostante
        primaryStage.setTitle("Centro Vaccinale - ServerCV" + "  /  " + ProgUtili.stampaData() + "  /  " + ProgUtili.getOsName());
        primaryStage.setScene(new Scene(root)); //da root togliere le dimensioni della finestra presenti come defaul messe da intellij per usare quelle settate tramite scene builder
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Parent getParentFromFxml(String resourcePath) throws IOException {
        URL fxmlUrl = new File(resourcePath).toURI().toURL();
        System.out.println(fxmlUrl.toString());
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }*/
    //////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////VERSIONE INSERITA PER USARE MAVEN NEL PROGETTO//////////////////////

    /**
     * Questo metodo lancia la prima finestra per l'esecuzione del programma
     *
     * @param stage stage
     *
     * @throws IOException IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //per chiudere correttamente il jar
        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /*static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }*/

    /**
     * Questo metodo imposta la finestra principale leggendola dal file fxml
     *
     * @return il loader fxml
     *
     * @throws IOException IOException
     */
    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerCVMain.class.getResource("ServerCV.fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo main
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(); //todo quando si chiude la finestra del jar tramite la 'X' rimane in esecuzione il processo e se si riclicca sul jar non lo apre
    }

}








