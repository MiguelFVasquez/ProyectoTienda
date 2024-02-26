package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.AdminController;
import co.edu.uniquindio.estructuraDatos.activity.controllers.ClienteController;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Tienda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminViewController {

    private App aplicacion;

    private Stage stage;
    private AdminController adminController = new AdminController();

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
    private TableColumn<String, ?> columnCantidadProductos;

    @FXML
    private TableColumn<String, Cliente> columnDireccion;

    @FXML
    private TableColumn<String, Cliente> columnIdentificacion;

    @FXML
    private TableColumn<String, Cliente> columnNombre;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columnProducto;

    @FXML
    private Label nombreCliente;

    @FXML
    private Label nombreCliente1;

    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableView<?> tableViewProductos;

    @FXML
    private TextField txtBuscarProducto;
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();




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



    //--------------------------------FUNCIONES UTILITARIAS-------------------------------------------------------------

    private ObservableList<Cliente> getListaClientes(){
        HashMap<String, Cliente> clientesMap = adminController.mfm.getClientes();
        listaClientes.addAll( clientesMap.values());
        return listaClientes;
    }
    @FXML
    void initialize() {

        adminController.mfm.initAdminController( this );
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));
        this.columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableViewClientes.getItems().clear();
        tableViewClientes.setItems( getListaClientes());
    }


}
