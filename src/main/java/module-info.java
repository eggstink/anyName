module com.example.anyname {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.anyname to javafx.fxml;
    exports com.example.anyname;
}