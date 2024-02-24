module com.example.clase {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.logging;
    requires java.desktop;


    opens co.edu.uniquindio.estructuraDatos.activity.app to javafx.graphics, javafx.fxml;
    opens co.edu.uniquindio.estructuraDatos.activity.viewControllers to javafx.fxml;
    opens co.edu.uniquindio.estructuraDatos.activity.model to javafx.base;
    exports co.edu.uniquindio.estructuraDatos.activity.model;
}