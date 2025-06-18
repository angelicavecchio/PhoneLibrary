module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.prefs;


        exports com.example.Controller;   // <-- aggiungi questa riga

    opens com.example to javafx.fxml;
    opens com.example.Controller to javafx.fxml; // <-- e questa riga
   
    exports com.example;
}
