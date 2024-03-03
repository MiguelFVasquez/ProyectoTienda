package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.AdminController;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import co.edu.uniquindio.estructuraDatos.activity.model.Venta;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button btnLimpiarInventario;
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnVerDetalleVenta;
    @FXML
    private Button btnBuscarVenta;
    @FXML
    private Button btnLimpiarFiltrosVenta;
    @FXML
    private Label nombreCliente;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private DatePicker datePickerDetalleVenta;

    private DetalleVentaViewController detalleVentaViewController;


    //----------------table view productos------------------------------
    @FXML
    private TableView<Producto> tableViewProductos;
    @FXML
    private TableColumn<Producto, Double> columnPrecio;

    @FXML
    private TableColumn<Producto, String> columnProducto;
    @FXML
    private TableColumn<Producto, Integer> columnCantidadProductos;

    //---------------Table view clientes------------------------
    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<String, Cliente> columnDireccion;

    @FXML
    private TableColumn<String, Cliente> columnIdentificacion;

    @FXML
    private TableColumn<String, Cliente> columnNombre;

    //------------------------------------------TABLEVIEW VENTAS--------------------------------------------------------
    @FXML
    private TableView<Venta> tableViewVentas;

    @FXML
    private TableColumn<String, Venta> columnCodigoVenta;

    @FXML
    private TableColumn<Double, Venta> columnTotalVenta;

    @FXML
    private TableColumn<Cliente, Venta> columClienteDetalleVenta;
    //-Variables utilitarias
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    private ObservableList<Producto> listaProductos= FXCollections.observableArrayList();
    private ObservableList<Venta> listaVentas= FXCollections.observableArrayList();
    private Producto productoSeleccionado;
    //------------------FUNCIONES UTILITARIAS-----------------------------------------
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    private boolean confirmacionAlert(){
        // Crear una alerta de tipo CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro de que quiere hacer esta acción?");

        // Configurar los botones
        ButtonType buttonTypeContinuar = new ButtonType("Continuar");
        ButtonType buttonTypeCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeContinuar, buttonTypeCancelar);

        // Mostrar la alerta y esperar a que el usuario haga clic en un botón
        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.filter(buttonType -> buttonType == buttonTypeContinuar).isPresent();
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
    private ObservableList<Venta> getListaVentas() {
        List<Venta> listaAux = adminController.mfm.getListaVentas();
        listaVentas.addAll( listaAux );
        return listaVentas;
    }
    void refrescarTableViews(){
        tableViewClientes.getItems().clear();
        tableViewClientes.setItems( getListaClientes());

        tableViewProductos.getItems().clear();
        tableViewProductos.setItems(getListaProductos());

        tableViewVentas.getItems().clear();
        tableViewVentas.setItems( getListaVentas());
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
        this.tableViewProductos.getSelectionModel().selectedItemProperty().addListener( (obs , oldSelection , newSelection) -> {
            if ( newSelection != null ) {
                btnEliminarProducto.setVisible( true );
                productoSeleccionado = newSelection;
            }
        });
        this.columClienteDetalleVenta.setCellValueFactory(new PropertyValueFactory<>("identificacionCliente"));
        this.columnTotalVenta.setCellValueFactory(new PropertyValueFactory<>("total"));
        this.columnCodigoVenta.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        refrescarTableViews();
        gestionEventos();

        tableViewVentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                gestionBotonesTabDetalleVenta( false );// Habilitar el botón si hay un elemento seleccionado
            } else {
                gestionBotonesTabDetalleVenta( true );// Deshabilitar el botón si no hay ningún elemento seleccionado
            }
        });

    }

    public void gestionBotonesTabDetalleVenta(boolean b) {
        btnVerDetalleVenta.setDisable( b );
    }


    //-------------------------------EVENTOS DE BOTONES----------------------------
    @FXML
    void cerrarSesion(ActionEvent event) {
        inicioViewController.show();
        this.stage.close();
    }
    @FXML
    void buscarProducto(ActionEvent event) {
        String codigoPrpoductoBuscar= txtBuscarProducto.getText();
        Producto productoEncontrado= adminController.mfm.obtenerProducto(codigoPrpoductoBuscar);
        if (productoEncontrado!=null){
            listaProductos.clear();
            listaProductos.add(productoEncontrado);
            tableViewProductos.setItems(listaProductos);
            tableViewProductos.refresh();
        }else {
            mostrarMensaje("Notificación busqueda", "Resultado de la busqueda", "El producto con el código: " + codigoPrpoductoBuscar +" no ha sido encontrado", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    void limpiarFiltrosDeInventario(ActionEvent event) {
        txtBuscarProducto.clear();
        tableViewProductos.getItems().clear();
        tableViewProductos.setItems(getListaProductos());
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        if (productoSeleccionado!=null){
            try {
                if (confirmacionAlert()){
                    if (adminController.mfm.eliminarProducto(productoSeleccionado)){
                        listaProductos.remove(productoSeleccionado);
                        mostrarMensaje("Eliminación de producto", "Producto eliminado", "El producto ha sido eliminado con exito", Alert.AlertType.INFORMATION);
                    }
                }
            } catch (ProductoException productoException) {
                mostrarMensaje("Eliminación de producto", "Producto no eliminado", productoException.getMessage(), Alert.AlertType.INFORMATION);
            }
        }else {
            mostrarMensaje("Selección de producto", "Producto no seleccionado", "Por favor seleccione un producto para ser eliminado", Alert.AlertType.WARNING);
        }
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
    void limpiarFiltros(ActionEvent event) {
        datePickerDetalleVenta.setValue( null );
        tableViewVentas.getItems().clear();
        tableViewVentas.setItems(getListaVentas());
    }

    //-----------------------------------------TAB VENTAS---------------------------------------------------------------
    @FXML
    void verDetalleVenta(ActionEvent event) throws IOException {
        Venta venta = tableViewVentas.getSelectionModel().getSelectedItem();
        refrescarTableViews();
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation( App.class.getResource("DetalleVentaView.fxml"));
        AnchorPane anchorPane= loader.load();
        detalleVentaViewController = loader.getController();
        detalleVentaViewController.setAplicacion(aplicacion);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        detalleVentaViewController.init(stage);
        detalleVentaViewController.setAdminViewController( this );
        refrescarTableDetalleVenta(venta);
        stage.setTitle( "Detalle Venta " );
        stage.show();


        // Crear una transición de deslizamiento para la nueva ventana
        TranslateTransition slideIn = new TranslateTransition( Duration.seconds(0.5), stage.getScene().getRoot());
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), anchorPane);
        slideIn.setFromY(this.stage.getHeight());
        slideIn.setToY(0);

        // Establecemos la opacidad inicial y final para la transición
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);

        // Iniciamos la transición


        // Mostrar la nueva ventana y ejecutar las transiciones
        detalleVentaViewController.show();
        slideIn.play();
        fadeTransition.play();
    }
    private void refrescarTableDetalleVenta(Venta venta) {
        detalleVentaViewController.refrescarTableView(venta);
    }

    @FXML
    void buscarVenta(ActionEvent event) {
        String fehchaVenta= datePickerDetalleVenta.getValue().toString();
        listaVentas.clear();
        listaVentas.addAll(adminController.mfm.getListaVentasFecha(fehchaVenta));
        tableViewVentas.refresh();
    }

}

