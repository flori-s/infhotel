module org.adainf.infhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; 

    opens org.adainf.infhotel to javafx.fxml;
    exports org.adainf.infhotel;
}