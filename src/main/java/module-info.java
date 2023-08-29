module com.example.zoohandlung12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zoohandlung12 to javafx.fxml;
    exports com.example.zoohandlung12;
}