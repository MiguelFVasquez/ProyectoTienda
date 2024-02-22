package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClienteViewController {

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
    void abrirVentanaCarrito(ActionEvent event) {

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

    }

    @FXML
    void guardarInfoCambiada(ActionEvent event) {

    }

    @FXML
    void initialize() {
        // Manejamos el evento cuando el mouse entra en el botón
        btnGuardarInfo.setOnMouseEntered(event -> {
            btnGuardarInfo.setStyle("-fx-background-color: white; -fx-border-color:   green; -fx-text-fill:   green ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnGuardarInfo.setOnMouseExited(event -> {
            btnGuardarInfo.setStyle("-fx-background-color:  green; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnGuardarInfo.setOnMouseEntered(event -> {
            btnGuardarInfo.setStyle("-fx-background-color: white; -fx-border-color:    #b26500; -fx-text-fill:    #b26500 ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnGuardarInfo.setOnMouseExited(event -> {
            btnGuardarInfo.setStyle("-fx-background-color:  #b26500; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnCarritoCompras.setOnMouseEntered(event -> {
            btnGuardarInfo.setStyle("-fx-background-color: white; -fx-border-color:    #b26500; -fx-text-fill:    #b26500 ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCarritoCompras.setOnMouseExited(event -> {
            btnGuardarInfo.setStyle("-fx-background-color:  #b26500; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnCancelarCambios.setOnMouseEntered( event ->  {
            btnCancelarCambios.setStyle("-fx-background-color: white; -fx-border-color:   red; -fx-text-fill:   red ; -fx-cursor: hand");
        });
        btnCancelarCambios.setOnMouseExited( event ->  {
            btnCancelarCambios.setStyle("-fx-background-color: red; -fx-cursor: default; -fx-text-fill:WHITE");

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
