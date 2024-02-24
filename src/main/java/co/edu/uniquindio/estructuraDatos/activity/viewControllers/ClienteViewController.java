package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.ClienteController;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columnProducto;

    @FXML
    public Label nombreCliente;

    @FXML
    private TableView<?> tableViewProductos;

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
    void agregarAlCarrito(ActionEvent event) {

    }
    @FXML
    void buscarProducto(ActionEvent event) {

    }

    @FXML
    void cambiarInfo(ActionEvent event) {

    }

    @FXML
    void cancelarCambioInfo(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        inicioViewController.show();
        this.stage.close();
    }

    @FXML
    void guardarInfoCambiada(ActionEvent event) {

    }

    void activarBtnCarrito(){
        btnCarritoCompras.setDisable( false);
    }


    void deshabilitarCampos(){
        txtNumeroIdentificacion.setEditable( false );
        txtNumeroIdentificacion.setEditable( false );
        txtDireccion.setEditable( false );
    }
    @FXML
    void initialize() {
        clienteController.mfm.initClienteController(this);

        configurarEventos();

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




    }


    public void setInfoCliente(Cliente cliente) {
        clienteController.mfm.mostrarInfoCliente( cliente );
        deshabilitarCampos();
    }
}
