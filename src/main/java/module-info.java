module com.example.gamedevlearning {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamedevlearning to javafx.fxml;
    exports com.example.gamedevlearning;
}