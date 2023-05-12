module com.example.conversor {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.json;

    opens com.example.conversor to javafx.fxml;
    exports com.example.conversor;
    exports com.example.conversor.controllers;
    opens com.example.conversor.controllers to javafx.fxml;
}