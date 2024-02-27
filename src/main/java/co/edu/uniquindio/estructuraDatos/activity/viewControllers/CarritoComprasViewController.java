package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CarritoComprasViewController {
    private Stage stage;

    private ClienteViewController clienteViewController;
    private App aplicacion;



    //FUNCIONES UTILIRARIAS
    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage(){
        return this.stage ;
    }

    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void show() {
        stage.show();
    }

    public void setClienteViewController(ClienteViewController clienteViewController) {
        this.clienteViewController = clienteViewController;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnComprar;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button btnCerrarVentana;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columnProducto;

    @FXML
    private TableView<?> tableViewCarrito;

    @FXML
    private TextField txtPrecioTotal;

    @FXML
    void comprarProductos(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        clienteViewController.activarBtnCarrito(false);
        this.stage.close();
    }

    @FXML
    void initialize() {
    }


}
