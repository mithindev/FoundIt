module com.example.foundit {
    requires javafx.controls;
    requires javafx.fxml;
            
                requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.foundit to javafx.fxml;
    exports com.example.foundit;
}