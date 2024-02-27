package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.ClienteController;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
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

public class ClienteViewController {

    private Cliente cliente;

    private InicioViewController inicioViewController;
    Stage stage;
    private App aplicacion;
    private ClienteController clienteController = new ClienteController();

    public InicioViewController getInicioViewController() {
        return inicioViewController;
    }

    public void setInicioViewController(InicioViewController inicioViewController) {
        this.inicioViewController = inicioViewController;
    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregarCarrito;

    @FXML
    private Button btnCambiarInfo;

    @FXML
    private Button btnCancelarCambios;

    @FXML
    private Button btnCarritoCompras;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnGuardarInfo;

    @FXML
    private Button btnBuscarProducto;

    @FXML
    private TableColumn<Double, Producto> columnPrecio;

    @FXML
    private TableColumn<String, Producto> columnProducto;

    @FXML
    private TableColumn<Integer, Producto> columnCantidad;

    @FXML
    public Label nombreCliente;

    @FXML
    private TableView<Producto> tableViewProductos;

    @FXML
    public TextField txtDireccion;

    @FXML
    public TextField txtNombreCliente;

    @FXML
    public TextField txtNumeroIdentificacion;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtBuscarProducto;

    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    private static final String VALOR_DEFECTO = "1";

    @FXML
    void cambiarInfo(ActionEvent event) {
        gestionActivos( true );
        mostrarMensaje( "Notifiación" , "Cambie su información",
                "Presione en los campos de texto para cambiar su información", Alert.AlertType.INFORMATION  );
    }

    @FXML
    void cancelarCambioInfo(ActionEvent event) {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
        alert.setTitle( "Confirmación" );
        alert.setHeaderText( "¿Estás seguro deseas cancelar los cambios?" );
        alert.setContentText( "Presiona OK para confirmar o Cancelar para cancelar." );

        // Mostrar la ventana de confirmación y esperar a que el usuario elija una opción
        alert.showAndWait().ifPresent( response -> {
            if ( response == javafx.scene.control.ButtonType.OK ) {
                System.out.println( "El usuario confirmó." );
                gestionActivos( false );
                clienteController.mfm.mostrarInfoCliente( clienteController.mfm.obtenerCliente( txtNumeroIdentificacion.getText() ) );
            } else {
                System.out.println( "El usuario canceló." );
            }
        } );
    }

    void gestionActivos(boolean flag){
        txtNombreCliente.setEditable( flag );
        txtDireccion.setEditable( flag );
        btnCancelarCambios.setVisible( flag );
        btnGuardarInfo.setVisible( flag );
        btnCambiarInfo.setDisable( flag );
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        inicioViewController.show();
        this.stage.close();
    }

    @FXML
    void guardarInfoCambiada(ActionEvent event) throws ClienteException, IOException {
        String nombre = txtNombreCliente.getText();
        String direccion = txtDireccion.getText();
        Cliente cliente1 = new Cliente(nombre,txtNumeroIdentificacion.getText(), direccion);
        if(validarDatos( nombre, direccion )){
            if(actualizarCliente( cliente1 )){
                gestionActivos( false );
                clienteController.mfm.serializarClientes();
            }
        }

    }

    boolean actualizarCliente(Cliente cliente){
        try{
           if(clienteController.mfm.actualizarCliente(cliente)){
               mostrarMensaje("Notificación", "Cambio de información realizado con éxito", "", Alert.AlertType.INFORMATION);
               return true;
           }
        } catch (ClienteException e) {
            mostrarMensaje("Notificación", "Cambio de información fallido", e.getMessage(), Alert.AlertType.INFORMATION);
        }
        return false;

    }

    void activarBtnCarrito(Boolean flag){
        btnCarritoCompras.setDisable( flag);
    }

    void deshabilitarCampos(){
        txtNombreCliente.setEditable( false );
        txtNumeroIdentificacion.setEditable( false );
        txtDireccion.setEditable( false );
    }

    //-------------------------------------FUNCIONES TAB PRODUCTOS------------------------------------------------------
    @FXML
    void abrirVentanaCarrito(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation( App.class.getResource("CarritoView.fxml"));
        AnchorPane anchorPane= loader.load();
        CarritoComprasViewController controller = loader.getController();
        controller.setAplicacion(aplicacion);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(stage);

        stage.setTitle( "Carrito de compras" );
        stage.show();
        controller.setClienteViewController( this );
        btnCarritoCompras.setDisable( true );
        stage.setX(stage.getX() + stage.getWidth());
        stage.setY(stage.getY());


        // Crear una transición de deslizamiento para la nueva ventana
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), stage.getScene().getRoot());
        slideIn.setFromX(this.stage.getWidth());
        slideIn.setToX(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), anchorPane);

        // Establecemos la opacidad inicial y final para la transición
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);

        // Iniciamos la transición


        // Mostrar la nueva ventana y ejecutar las transiciones
        controller.show();
        slideIn.play();
        fadeTransition.play();
    }

    @FXML
    void agregarAlCarrito(ActionEvent event) throws IOException {
        Producto selectedItem = tableViewProductos.getSelectionModel().getSelectedItem();
        int cantidad = Integer.parseInt( txtCantidad.getText() );
        Producto productoAux = new Producto(cantidad, selectedItem.getCodigo(), selectedItem.getNombre(), selectedItem.getPrecio());
        if(agregarProducto(productoAux)){
            mostrarMensaje( "Notificación", "Producto agregado al carrito" , cantidad + " de " + selectedItem.getNombre() +
                    ". Puedes ver tu pedido en el botón carrito de compras" , Alert.AlertType.INFORMATION);
            clienteController.mfm.serializarProductos();
            refrescarTableViewProductos();
            activarBtnCarrito( false );
        }



    }

    private boolean agregarProducto(Producto selectedItem) {
        String id = txtNumeroIdentificacion.getText();
        try{
            if(clienteController.mfm.agregarProductoCarritoCliente(selectedItem, id))
                return true;
        } catch (Exception e) {
            throw new RuntimeException( e );
        }
        return false;
    }

    @FXML
    void buscarProducto(ActionEvent event) {

    }



    //-----------------------------------------FUNCIONES UTILITARIAS----------------------------------------------------

    void habilitarCamposProducto(boolean flag){
        btnAgregarCarrito.setDisable(flag);
        txtCantidad.setDisable( flag );
    }
    @FXML
    void initialize() {
        txtCantidad.setText(VALOR_DEFECTO);
        habilitarCamposProducto( true );
        clienteController.mfm.initClienteController( this );
        activarBtnCarrito( true );
        deshabilitarCampos();
        configurarEventos();
        this.columnProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        refrescarTableViewProductos();
    }
    private ObservableList<Producto> getListaProductos() {
        HashMap<String, Producto> productosMap = clienteController.mfm.getProductos();
        listaProductos.addAll( productosMap.values() );
        return listaProductos;
    }

    void refrescarTableViewProductos() {
        tableViewProductos.getItems().clear();
        tableViewProductos.setItems( getListaProductos() );
    }

    private void configurarEventos(){
        // Manejamos el evento cuando el mouse entra en el botón

        btnCambiarInfo.setOnMouseEntered(event -> {
            btnCambiarInfo.setStyle("-fx-background-color: white; -fx-border-color:    #b26500; -fx-text-fill:    #b26500 ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCambiarInfo.setOnMouseExited(event -> {
            btnCambiarInfo.setStyle("-fx-background-color:  #b26500; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnCarritoCompras.setOnMouseEntered(event -> {
            btnCarritoCompras.setStyle("-fx-background-color: #b26500;-fx-border-color:    transparent;-fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCarritoCompras.setOnMouseExited(event -> {
            btnCarritoCompras.setStyle("-fx-background-color:  transparent; -fx-border-color:    #b26500; -fx-cursor: default");
        });

        tableViewProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                habilitarCamposProducto( false ); // Habilitar el botón si hay un elemento seleccionado
            } else {
                habilitarCamposProducto( true ); // Deshabilitar el botón si no hay ningún elemento seleccionado
            }
        });
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                return change;
            }
            return null;
        });
        // Aplicar el TextFormatter al TextField
        txtCantidad.setTextFormatter(formatter);

        txtCantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                txtCantidad.setText(VALOR_DEFECTO);
            }
        });

    }



    public void setInfoCliente(Cliente cliente) {
        clienteController.mfm.mostrarInfoCliente( cliente );
        deshabilitarCampos();
    }

    //--------------------------------------FUNCIONES UTILITARIAS-------------------------------------------------------
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
}
