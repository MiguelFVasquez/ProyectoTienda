package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import co.edu.uniquindio.estructuraDatos.activity.controllers.InicioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InicioViewController implements Initializable {
    @FXML
    private TextField txt1;
    @FXML
    private Button btn1;

    private InicioController inicioController;
    Stage stage;
    @FXML
    void accion1(ActionEvent event) {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void initialize() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicioController= new InicioController();
        inicioController.mfm.initInicioViewController(this);
    }

}
