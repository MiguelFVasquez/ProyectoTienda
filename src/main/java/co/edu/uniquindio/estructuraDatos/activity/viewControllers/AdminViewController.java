package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.AdminController;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Label nombreCliente;

    @FXML
    private Label nombreCliente1;
    @FXML
    private TextField txtBuscarProducto;
    //----------------table view productos------------------------------
    @FXML
    private TableView<Producto> tableViewProductos;
    @FXML
    private TableColumn<Producto, Double> columnPrecio;

    @FXML
    private TableColumn<Producto, String> columnProducto;
    @FXML
    private TableColumn<Producto, Integer> columnCantidadProductos;
    //---------------Table vieew clientes------------------------
    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<String, Cliente> columnDireccion;

    @FXML
    private TableColumn<String, Cliente> columnIdentificacion;

    @FXML
    private TableColumn<String, Cliente> columnNombre;

    //-Variables utilitarias
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private ObservableList<Producto> listaProductos= FXCollections.observableArrayList();

    //------------------FUNCIONES UTILITARIAS-----------------------------------------
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean esNumero(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validarDatos(String nombre, String direccion) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */

        if (nombre == null || nombre.isEmpty()) {
            notificacion += "Ingrese su nombre\n";
        } else {
            if (esNumero(nombre)) {
                notificacion += "El nombre ingresado no puede ser numérico\n";
            }
        }
        if (direccion == null || direccion.isEmpty()) {
            notificacion += "Ingrese su dirección\n";
        } else {
            if (esNumero(direccion)) {
                notificacion += "La dirección ingresada no puede ser numérica\n";
            }
        }
        if (!notificacion.isEmpty()) {
            mostrarMensaje("Notificación", "Cambio de información fallido",
                    notificacion
                    , Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    private ObservableList<Cliente> getListaClientes(){
        HashMap<String, Cliente> clientesMap = adminController.mfm.getClientes();
        listaClientes.addAll( clientesMap.values());
        return listaClientes;
    }
    private ObservableList<Producto> getListaProductos(){
        HashMap<String,Producto> productosMap=adminController.mfm.getListaProductos();
        listaProductos.addAll(productosMap.values());
        return listaProductos;
    }
    void refrescarTableViews(){
        tableViewClientes.getItems().clear();
        tableViewClientes.setItems( getListaClientes());

        tableViewProductos.getItems().clear();
        tableViewProductos.setItems(getListaProductos());
    }

    void gestionEventos(){
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEliminarCliente.setDisable(false); // Habilitar el botón si hay un elemento seleccionado
            } else {
                btnEliminarCliente.setDisable(true); // Deshabilitar el botón si no hay ningún elemento seleccionado
            }
        });
    }

    //-------------------------------EVENTOS DE BOTONES----------------------------

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
        Cliente selectedItem = tableViewClientes.getSelectionModel().getSelectedItem();
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
        alert.setTitle( "Confirmación" );
        alert.setHeaderText( "¿Estás seguro de que deseas eliminar el cliente " + selectedItem.getNombre() + "?" );
        alert.setContentText( "Presiona ACEPTAR para confirmar o Cancelar para cancelar." );

        // Mostrar la ventana de confirmación y esperar a que el usuario elija una opción
        alert.showAndWait().ifPresent( response -> {

            if ( response == ButtonType.OK ) {
                System.out.println( "El usuario confirmó." );
                if(eliminarClienteSeleccionado( selectedItem.getNumeroIdentificacion())){
                    listaClientes.remove(selectedItem);
                    try {
                        adminController.mfm.serializarClientes();
                    } catch (IOException e) {
                        throw new RuntimeException( e );
                    }
                }
            } else {
                System.out.println( "El usuario canceló." );
            }
        } );

    }

    private boolean eliminarClienteSeleccionado(String numeroIdentificacion) {
        try{
            if(adminController.mfm.eliminarCliente(numeroIdentificacion)){
                mostrarMensaje("Notificación", "Cliente eliminado", "Cliente eliminado con éxito", Alert.AlertType.INFORMATION);
                return true;
            }
        } catch (Exception e) {
            mostrarMensaje("Notificación", "Cliente no eliminado", e.getMessage(), Alert.AlertType.INFORMATION);
        }
        return false;
    }



    @FXML
    void initialize() {
        btnEliminarCliente.setDisable( true );
        adminController.mfm.initAdminController( this );
        //Iniciar los valores de la tableview de clientes
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));
        this.columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        //Iniciar los valores de la tableview de productos
        this.columnProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnCantidadProductos.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        refrescarTableViews();
        gestionEventos();
    }

}
