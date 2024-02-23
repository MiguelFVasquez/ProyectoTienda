package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
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
import javafx.util.Duration;

public class ClienteViewController {

    private InicioViewController inicioViewController;
    Stage stage;
    private App aplicacion;

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
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columnProducto;

    @FXML
    private Label nombreCliente;

    @FXML
    private TableView<?> tableViewProductos;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtNumeroIdentificacion;

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

    @FXML
    void initialize() {
        configurarEventos();
    }

    private void configurarEventos(){
        // Manejamos el evento cuando el mouse entra en el botón
        btnGuardarInfo.setOnMouseEntered(event -> {
            btnGuardarInfo.setStyle("-fx-background-color: white; -fx-border-color:   green; -fx-text-fill:   green ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnGuardarInfo.setOnMouseExited(event -> {
            btnGuardarInfo.setStyle("-fx-background-color:  green; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnCambiarInfo.setOnMouseEntered(event -> {
            btnCambiarInfo.setStyle("-fx-background-color: white; -fx-border-color:    #b26500; -fx-text-fill:    #b26500 ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCambiarInfo.setOnMouseExited(event -> {
            btnCambiarInfo.setStyle("-fx-background-color:  #b26500; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnCarritoCompras.setOnMouseEntered(event -> {
            btnCarritoCompras.setStyle("-fx-background-color: white; -fx-border-color:    #b26500; -fx-text-fill:    #b26500 ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCarritoCompras.setOnMouseExited(event -> {
            btnCarritoCompras.setStyle("-fx-background-color:  #b26500; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnCancelarCambios.setOnMouseEntered( event ->  {
            btnCancelarCambios.setStyle("-fx-background-color: white; -fx-border-color:   red; -fx-text-fill:   red ; -fx-cursor: hand");
        });
        btnCancelarCambios.setOnMouseExited( event ->  {
            btnCancelarCambios.setStyle("-fx-background-color: red; -fx-cursor: default; -fx-text-fill:WHITE");

        });

        btnAgregarCarrito.setOnMouseEntered( event ->  {
            btnAgregarCarrito.setStyle("-fx-background-color: white; -fx-border-color:   green; -fx-text-fill:   green ; -fx-cursor: hand");
        });
        btnAgregarCarrito.setOnMouseExited( event ->  {
            btnAgregarCarrito.setStyle("-fx-background-color: green; -fx-cursor: default; -fx-text-fill:WHITE");

        });

        btnCerrarSesion.setOnMouseEntered(event -> {
            btnCerrarSesion.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-text-fill:   red ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCerrarSesion.setOnMouseExited(event -> {
            btnCerrarSesion.setStyle("-fx-background-color:   red; -fx-cursor: default; -fx-text-fill:WHITE");
        });
    }



}
