/**
 * module
 */
module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.sql;


    opens org.example.centrivaccinali.gui to javafx.fxml;
    exports org.example.centrivaccinali.gui;

    opens org.example.serverCV.gui to javafx.fxml;
    exports org.example.serverCV;
    exports org.example.serverCV.gui;
}