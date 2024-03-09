package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CheckViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    private Stage stage;

    @FXML
    void initialize() {

    }

    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void show() {
        stage.show();
    }

}
