package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.InicioController;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;


public class InicioViewController {


    private InicioController inicioController;
    Stage stage;
    private App aplicacion;

    // FUNCIONES DE APERTURA DE VENTANA
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage(){
        return this.stage ;
    }

    public void show() {
        stage.show();

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
    private Button presionaAqui;

    @FXML
    private TextField txtDireccion;

    @FXML
    private PasswordField txtIdentificacionInicioSesion;

    @FXML
    private TextField  txtIdentificacionRegistro;

    @FXML
    private TextField txtNombreRegistro;

    @FXML
    private Tab tabInicio;

    @FXML
    private Tab tabRegistro;

    @FXML
    private TabPane tabPane;



    //--------------------------------------------TAB INICIO DE SESION--------------------------------------------------
    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        String id = txtIdentificacionInicioSesion.getText();
        if ( id.equals( "0000" ) ) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( App.class.getResource( "AdminView.fxml" ) );
            AnchorPane anchorPane = loader.load();
            AdminViewController controller = loader.getController();
            controller.setAplicacion( aplicacion );
            Scene scene = new Scene( anchorPane );
            Stage stage = new Stage();
            stage.setScene( scene );
            controller.init( stage );
            controller.setInicioViewController( this );

            stage.setTitle( "Tienda" );
            stage.show();
            FadeTransition fadeTransition = new FadeTransition( Duration.seconds( 1 ) , anchorPane );

            // Establecemos la opacidad inicial y final para la transición
            fadeTransition.setFromValue( 0.0 );
            fadeTransition.setToValue( 1.0 );

            // Iniciamos la transición
            fadeTransition.play();
            txtIdentificacionInicioSesion.clear();
            this.stage.close();
        } else {
            if ( !id.isEmpty() ) {
                if ( esNumero( id ) ) {
                    if ( verificarCliente( id ) ) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation( App.class.getResource( "ClienteView.fxml" ) );
                        AnchorPane anchorPane = loader.load();
                        ClienteViewController controller = loader.getController();
                        controller.setAplicacion( aplicacion );
                        Scene scene = new Scene( anchorPane );
                        Stage stage = new Stage();
                        stage.setScene( scene );
                        controller.init( stage );
                        controller.setInicioViewController( this );

                        controller.setInfoCliente(inicioController.mfm.obtenerCliente( id ));
                        stage.setTitle( "Tienda" );

                        stage.show();
                        FadeTransition fadeTransition = new FadeTransition( Duration.seconds( 1 ) , anchorPane );

                        // Establecemos la opacidad inicial y final para la transición
                        fadeTransition.setFromValue( 0.0 );
                        fadeTransition.setToValue( 1.0 );

                        // Iniciamos la transición
                        fadeTransition.play();
                        txtIdentificacionInicioSesion.clear();
                        this.stage.close();
                    } else {
                        txtIdentificacionInicioSesion.clear();
                        mostrarMensaje( "Notificación" , "Credencial no válida" ,
                                "El número de identificación no se encuentra registrado" , Alert.AlertType.INFORMATION
                        );
                    }
                } else {
                    txtIdentificacionInicioSesion.clear();
                    mostrarMensaje( "Notificación" , "Credencial incorrecta" ,
                            "La identificación debe ser númerica" , Alert.AlertType.INFORMATION
                    );
                }
            } else {
                mostrarMensaje( "Notificación" , "Espacio en blanco" ,
                        "Ingrese una identificación" , Alert.AlertType.INFORMATION
                );
            }
        }
    }

    private boolean verificarCliente(String id) {
        return inicioController.mfm.verificarCliente( id );
    }

    @FXML
    void activarTabRegistro(ActionEvent event) {
        tabRegistro.setDisable( false ); // Habilita el tab de registro al presionar el botón de registro
        tabPane.getSelectionModel().select( tabRegistro ); // Cambia a la pestaña de registro automáticamente

    }



    //---------------------------------------TAB DE REGISTRO------------------------------------------------------------
    @FXML
    void registrarse(ActionEvent event) throws IOException, ClienteException {
        String nombre = txtNombreRegistro.getText();
        String id = txtIdentificacionRegistro.getText();
        String direccion = txtDireccion.getText();

        if(validarDatos( nombre,id,direccion )){
            if(registrarCliente(nombre, id, direccion)){
                mostrarMensaje( "Notificación", "Cliente creado con éxito","Ahora inicie sesión", Alert.AlertType.INFORMATION );
                inicioController.mfm.serializarClienteRegistrado();
                tabPane.getSelectionModel().select( tabInicio );
                limpiarCampos();
                tabRegistro.setDisable( true );
            }else{
                mostrarMensaje( "Notificación", "Cliente no creado",
                        "La identifiación ingresada ya se encuentra registrada" , Alert.AlertType.INFORMATION );
                txtIdentificacionRegistro.clear();
            }
        }
    }

    void limpiarCampos(){
        txtDireccion.clear();
        txtNombreRegistro.clear();
        txtIdentificacionRegistro.clear();
    }

    private boolean registrarCliente(String nombre , String id , String direccion) throws ClienteException {
        return inicioController.mfm.registrarCliente( nombre,id,direccion );
    }


    //-------------------------------FUNCIONES UTILITARIAS-----------------------------------------------------------
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
                try {
                    registrarse( new ActionEvent() );
                } catch (IOException e) {
                    throw new RuntimeException( e );
                } catch (ClienteException e) {
                    throw new RuntimeException( e );
                }
            }
        });
        txtIdentificacionInicioSesion.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Seleccionar el siguiente campo de texto
                try {
                    iniciarSesion( new ActionEvent() );
                } catch (IOException e) {
                    throw new RuntimeException( e );
                }
            }
        });



    }

    @FXML
    void initialize() {
        inicioController= new InicioController();
        inicioController.mfm.initInicioViewController(this);
        configurarEventos();
        tabRegistro.setDisable( true );

    }

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

    private boolean validarDatos(String nombre, String id, String direccion) {
        String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


        if (nombre == null || nombre.isEmpty()) {
            notificacion += "Ingrese su nombre\n";
        }

        if (id == null || id.isEmpty()) {
            notificacion += "Ingrese su número de identificación\n";
        } else {
            if (!esNumero(id)) {
                notificacion += "El número ingresado debe ser numérico\n";
            }
        }
        if (direccion == null || direccion.isEmpty()) {
            notificacion += "Ingrese su dirección\n";
        }
        if (!notificacion.isEmpty()) {
            mostrarMensaje("Notificación", "Registro fallido",
                    notificacion
                    , Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
}

