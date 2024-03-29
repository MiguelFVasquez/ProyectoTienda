package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.controllers.ClienteController;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class CarritoComprasViewController {
    private Stage stage;

    private ClienteViewController clienteViewController;
    private App aplicacion;
    private ClienteController carritoController = new ClienteController();
    private ObservableList<Producto> listaProductosCliente = FXCollections.observableArrayList();
    private String identificacionCliente;


    //FUNCIONES UTILIRARIAS
    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;

    }

    public void init(Stage stage2) {
        this.stage = stage2;
    }

    public void show() {
        stage.show();
    }

    public void setClienteViewController(ClienteViewController clienteViewController) {
        this.clienteViewController = clienteViewController;
        this.identificacionCliente = clienteViewController.txtNumeroIdentificacion.getText();
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
    private Button btnCancelarCompra;

    @FXML
    private TableColumn<String, Producto> columnPrecio;

    @FXML
    private TableColumn<String, Producto> columnProducto;
    @FXML
    private TableColumn<String, Producto> columnSubtotal;

    @FXML
    private TableColumn<String, Producto> columnCantidad;

    @FXML
    private TableView<Producto> tableViewCarrito;

    @FXML
    private TextField txtPrecioTotal;
    @FXML
    private TextField txtCantidadEliminar;
    private static final String VALOR_DEFECTO = "1";


    @FXML
    void comprarProductos(ActionEvent event) throws ClienteException, IOException {
        if(comprarProductosCarrito(identificacionCliente)){
            listaProductosCliente.clear();
            clienteViewController.setCarritoComprasViewController( null );
            carritoController.mfm.serializarProductos();
            carritoController.mfm.serializarVentas();
            this.stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( App.class.getResource( "CheckView.fxml" ) );
            AnchorPane anchorPane = loader.load();
            CheckViewController controller = loader.getController();
            Scene scene = new Scene( anchorPane );
            Stage stage = new Stage();
            stage.setScene( scene );
            stage.initStyle( StageStyle.UNDECORATED );
            controller.init( stage );
            FadeTransition fadeIn = new FadeTransition( Duration.seconds(1), stage.getScene().getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // Crear una transición de fade out
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), stage.getScene().getRoot());
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            // Configurar un timeline para coordinar el fade in y el fade out
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), event2 -> {
                        // Mostrar la ventana y comenzar el fade in
                        controller.show();
                        fadeIn.play();
                    }),
                    new KeyFrame(Duration.seconds(1), event2 -> {
                        // Comenzar el fade out después de que termine el fade in
                        fadeIn.stop();
                        fadeOut.play();
                    }),
                    new KeyFrame(Duration.seconds(3), event2 -> {
                        // Cerrar la ventana después de que termine el fade out
                        stage.close();
                    })
            );

            // Iniciar el timeline
            timeline.play();
        }
    }

    private boolean comprarProductosCarrito(String identificacionCliente) throws ClienteException {
       try{
            if(carritoController.mfm.comprarProductosCarrito( identificacionCliente )){
                return true;
            }
       } catch (Exception e) {
           throw new ClienteException( e.getMessage() );
       }
       return false;
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        Producto selectedItem = tableViewCarrito.getSelectionModel().getSelectedItem();
        int cantidadElim = Integer.parseInt( txtCantidadEliminar.getText() );
        if(cantidadElim==0){
            cantidadElim = 1;
        }
        if(selectedItem.getCantidad()>= cantidadElim){
            Producto productoAux = new Producto(cantidadElim,selectedItem.getCodigo(),
                    selectedItem.getNombre(), selectedItem.getPrecio());

            Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
            alert.setTitle( "Confirmación" );
            alert.setHeaderText( "¿Estás seguro deseas eliminar "+
                    cantidadElim + " de " + selectedItem.getNombre() +  "?" );
            alert.setContentText( "Presiona ACEPTAR para confirmar o CANCELAR para cancelar." );

            // Mostrar la ventana de confirmación y esperar a que el usuario elija una opción
            alert.showAndWait().ifPresent( response -> {
                if ( response == javafx.scene.control.ButtonType.OK ) {
                    System.out.println( "El usuario confirmó." );
                    gestionActivos( false );
                    eliminarProductoCarrito(productoAux);
                    actualizarSubtotal();
                    tableViewCarrito.getSelectionModel().setSelectionMode( null );
                    refrescarTableViewProductos( carritoController.mfm.obtenerCliente(identificacionCliente) );
                    if(listaProductosCliente.isEmpty()){
                        clienteViewController.refrescarTableViewProductos();
                        clienteViewController.setCarritoComprasViewController( null );
                        stage.close();
                    }
                    clienteViewController.refrescarTableViewProductos();
                } else {
                    System.out.println( "El usuario canceló." );
                }
            } );
        }else{
            mostrarMensaje("Notificación" , "Producto no eliminado" ,
                    "La cantidad de " + selectedItem.getNombre()+ " es mayor a la agregada al carrito ", Alert.AlertType.INFORMATION);
        }


    }

    void actualizarSubtotal() {
        // Recorrer todas las filas de la TableView y actualizar el subtotal en cada una
        for (int i = 0; i < tableViewCarrito.getItems().size(); i++) {
            Producto producto = tableViewCarrito.getItems().get(i);
            double subtotalProducto = producto.getPrecio() * producto.getCantidad();
            // Actualizar el valor de la columna de subtotal en la fila correspondiente
            tableViewCarrito.getItems().get(i).setSubTotal(subtotalProducto);
        }
    }

    private void eliminarProductoCarrito(Producto selectedItem) {
        try{
            if(carritoController.mfm.eliminarProducto(selectedItem, clienteViewController.txtNumeroIdentificacion.getText())){
                mostrarMensaje( "Notificación","Producto eliminado","Se ha eliminado el producto del carrito", Alert.AlertType.INFORMATION );
            }
        } catch (Exception e) {
            throw new RuntimeException( e );
        }
    }

    void gestionActivos(boolean flag){
        btnEliminarProducto.setDisable( !flag );
        txtCantidadEliminar.setEditable( flag );
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        clienteViewController.activarBtnCarrito(false);
        this.stage.close();
    }

    @FXML
    void cancelarCompraTotal(ActionEvent event) {
            Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
            alert.setTitle( "Confirmación" );
            alert.setHeaderText( "¿Estás seguro deseas cancelar la compra?");
            alert.setContentText( "Presiona ACEPTAR para confirmar o CANCELAR para cancelar." );

            // Mostrar la ventana de confirmación y esperar a que el usuario elija una opción
            alert.showAndWait().ifPresent( response -> {
                if ( response == javafx.scene.control.ButtonType.OK ) {
                    System.out.println( "El usuario confirmó." );
                    try {
                        eliminarProductosCarrito();
                    } catch (ClienteException e) {
                        throw new RuntimeException( e );
                    }
                    clienteViewController.refrescarTableViewProductos();
                    clienteViewController.setCarritoComprasViewController( null );
                    stage.close();

                } else {
                    System.out.println( "El usuario canceló." );
                }
            } );
    }

    private void eliminarProductosCarrito() throws ClienteException {
        try{
            if(carritoController.mfm.eliminarProductosCarrito(identificacionCliente)){
                mostrarMensaje( "Notificación", "Compra cancelada",
                        "La compra ha sido cancelada correctamente", Alert.AlertType.INFORMATION );
            }
        } catch (Exception e) {
            throw new ClienteException(e.getMessage() );

        }
    }


    //------------------------------FUNCIONES UTILITARIAS---------------------------------------------------------------
    @FXML
    void initialize() {
        txtCantidadEliminar.setText( "1" );
        carritoController.mfm.initCarritoController( this );
        this.columnProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        gestionEventos();
        gestionActivos( false );

        /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                clienteViewController.activarBtnCarrito(false);
            }
        });*/
    }

    private ObservableList<Producto> getListaProductos(Cliente cliente) {
        ArrayList<Producto> productos = cliente.getCarritoCliente().getListaProductos();
        listaProductosCliente.addAll( productos );
        double aux = 0;
        for (Producto prod: productos) {
            aux+= prod.getSubTotal();
        }
        txtPrecioTotal.setText( String.valueOf( aux ) );
        return listaProductosCliente;
    }

    void refrescarTableViewProductos(Cliente cliente) {
        tableViewCarrito.getItems().clear();
        tableViewCarrito.setItems( getListaProductos(cliente) );

    }

    void gestionEventos() {
        TextFormatter<String> formatter = new TextFormatter<>( change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                return change;
            }
            return null;
        });
        // Aplicar el TextFormatter al TextField
        txtCantidadEliminar.setTextFormatter(formatter);

        txtCantidadEliminar.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Si el foco se pierde
                // Verificar si el TextField está vacío
                if (txtCantidadEliminar.getText().isEmpty()) {
                    // Establecer el valor predeterminado como "1"
                    txtCantidadEliminar.setText(VALOR_DEFECTO);
                }
            }
        });

        tableViewCarrito.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                gestionActivos( true );// Habilitar el botón si hay un elemento seleccionado
                txtCantidadEliminar.setText(String.valueOf(newSelection.getCantidad()));
            } else {
                gestionActivos( false );// Deshabilitar el botón si no hay ningún elemento seleccionado
                txtCantidadEliminar.setText("1");
            }

        });

    }
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertype) {
        Alert alert = new Alert(alertype);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


}
