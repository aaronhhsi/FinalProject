module org.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;


    opens org.example.finalproject to javafx.fxml;
    exports org.example.finalproject;
}