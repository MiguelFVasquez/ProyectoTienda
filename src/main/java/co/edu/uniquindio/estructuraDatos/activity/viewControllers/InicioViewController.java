package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.controllers.InicioController;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;


public class InicioViewController {


    private InicioController inicioController;
    Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnInicioSesion;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Label presionaAqui;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIdentificacionInicioSesion;

    @FXML
    private TextField txtIdentificacionRegistro;

    @FXML
    private TextField txtNombreRegistro;

    @FXML
    private Tab tabInicio;

    @FXML
    private Tab tabRegistro;

    @FXML
    private TabPane tabPane;


    @FXML
    void initialize() {
        inicioController= new InicioController();
        inicioController.mfm.initInicioViewController(this);
        configurarEventos();

    }

    @FXML
    void iniciarSesion(ActionEvent event) {
    }
    @FXML
    void registrarse(ActionEvent event) {
    }

    private void configurarEventos() {

        // Creamos una transición de escala que dura 0.2 segundos
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.2), btnInicioSesion);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.2), btnInicioSesion);
        scaleOut.setFromX(1.1);
        scaleOut.setFromY(1.1);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        ScaleTransition scaleIn2 = new ScaleTransition(Duration.seconds(0.2), btnRegistrarse);
        scaleIn2.setFromX(1);
        scaleIn2.setFromY(1);
        scaleIn2.setToX(1.1);
        scaleIn2.setToY(1.1);

        ScaleTransition scaleOut2 = new ScaleTransition(Duration.seconds(0.2), btnRegistrarse);
        scaleOut2.setFromX(1.1);
        scaleOut2.setFromY(1.1);
        scaleOut2.setToX(1);
        scaleOut2.setToY(1);

        // Manejamos el evento cuando el mouse entra en el botón
        btnInicioSesion.setOnMouseEntered(event -> {
            btnInicioSesion.setStyle("-fx-background-color: white; -fx-border-color:   #0a002c; -fx-text-fill:   #0a002c ; -fx-cursor: hand");
            scaleIn.play();
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnInicioSesion.setOnMouseExited(event -> {
            btnInicioSesion.setStyle("-fx-background-color:   #0a002c; -fx-cursor: default; -fx-text-fill:WHITE");
            scaleOut.play();});

        presionaAqui.setOnMouseEntered( event ->  {
            presionaAqui.setStyle("-fx-cursor: hand");
        });
        presionaAqui.setOnMouseExited( event ->  {
            presionaAqui.setStyle("-fx-cursor: default");
        });

        btnRegistrarse.setOnMouseEntered(event -> {
            btnRegistrarse.setStyle("-fx-background-color: white; -fx-border-color: #00574a; -fx-text-fill:   #00574a ; -fx-cursor: hand");
            scaleIn2.play();
        });

        // Manejamos el evento cuando el mouse sale del botón
        btnRegistrarse.setOnMouseExited(event -> {
            btnRegistrarse.setStyle("-fx-background-color:   #00574a; -fx-cursor: default; -fx-text-fill:WHITE");
            scaleOut2.play();});

        txtNombreRegistro.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Seleccionar el siguiente campo de texto
                txtIdentificacionRegistro.requestFocus();
            }
        });
        txtIdentificacionRegistro.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Seleccionar el siguiente campo de texto
                txtDireccion.requestFocus();
            }
        });
        txtDireccion.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Seleccionar el siguiente campo de texto
                registrarse( new ActionEvent() );
            }
        });




    }

}
