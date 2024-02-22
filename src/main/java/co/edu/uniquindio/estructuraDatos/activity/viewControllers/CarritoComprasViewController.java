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

    //Funciones utilitarias
    private void configurarEventos() {

        // Manejamos el evento cuando el mouse entra en el botón
        btnComprar.setOnMouseEntered(event -> {
            btnComprar.setStyle("-fx-background-color: white; -fx-border-color:   green; -fx-text-fill:   green ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnComprar.setOnMouseExited(event -> {
            btnComprar.setStyle("-fx-background-color: green; -fx-cursor: default; -fx-text-fill:WHITE");
        });

        btnEliminarProducto.setOnMouseEntered( event ->  {
            btnEliminarProducto.setStyle("-fx-background-color: white; -fx-border-color:   red; -fx-text-fill:   red ; -fx-cursor: hand");
        });
        btnEliminarProducto.setOnMouseExited( event ->  {
            btnEliminarProducto.setStyle("-fx-background-color: red; -fx-cursor: default; -fx-text-fill:WHITE");

        });

        btnCerrarVentana.setOnMouseEntered(event -> {
            btnCerrarVentana.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-text-fill:   red ; -fx-cursor: hand");
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnCerrarVentana.setOnMouseExited(event -> {
            btnCerrarVentana.setStyle("-fx-background-color:   red; -fx-cursor: default; -fx-text-fill:WHITE");
        });

    }


    @FXML
    void comprarProductos(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }
    @FXML
    void cerrarVentana(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void initialize() {
        configurarEventos();
    }


}
