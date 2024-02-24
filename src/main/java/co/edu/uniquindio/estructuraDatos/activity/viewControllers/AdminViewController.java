package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminViewController {

    private App aplicacion;

    private Stage stage;

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage(){
        return this.stage ;
    }

    public void show() {
        stage.show();

    }
    private InicioViewController inicioViewController;

    public InicioViewController getInicioViewController() {
        return inicioViewController;
    }

    public void setInicioViewController(InicioViewController inicioViewController) {
        this.inicioViewController = inicioViewController;
    }

    public void init(Stage stage2) {
        this.stage = stage2;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuscarProducto;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private TableColumn<?, ?> columnCantidadProductos;

    @FXML
    private TableColumn<?, ?> columnDireccion;

    @FXML
    private TableColumn<?, ?> columnIdentificacion;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columnProducto;

    @FXML
    private Label nombreCliente;

    @FXML
    private Label nombreCliente1;

    @FXML
    private TableView<?> tableViewClientes;

    @FXML
    private TableView<?> tableViewProductos;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    void buscarProducto(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        inicioViewController.show();
        this.stage.close();
    }

    @FXML
    void eliminarCliente(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }


}
