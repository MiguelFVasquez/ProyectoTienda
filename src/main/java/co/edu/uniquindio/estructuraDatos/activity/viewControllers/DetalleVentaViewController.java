package co.edu.uniquindio.estructuraDatos.activity.viewControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuraDatos.activity.app.App;
import co.edu.uniquindio.estructuraDatos.activity.model.DetalleVenta;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import co.edu.uniquindio.estructuraDatos.activity.model.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DetalleVentaViewController {


    private App aplicacion;
    private Stage stage;
    private AdminViewController adminViewController;

    public void setAplicacion(App aplicacion) {
        this.aplicacion = aplicacion;

    }
    public void init(Stage stage2) {
        this.stage = stage2;
    }
    public void show() {
        stage.show();
    }

    public void setAdminViewController(AdminViewController adminViewController) {
        this.adminViewController = adminViewController;

    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerrarVentana;

    @FXML
    private TableColumn<Integer, DetalleVenta> columnCantidad;

    @FXML
    private TableColumn<String, DetalleVenta> columnNombreProducto;

    @FXML
    private TableColumn<Double, DetalleVenta> columnSubtotal;

    @FXML
    private TableView<DetalleVenta> tableViewDetalles;

    @FXML
    private TextField txtPrecioTotal;

    private ObservableList<DetalleVenta> listaDetallesVenta = FXCollections.observableArrayList();


    @FXML
    void cerrarVentana(ActionEvent event) {
       // adminViewController.clearTableSelection();
        stage.close();
    }



    //-------------------------------------FUNCIONES UTILITARIAS--------------------------------------------------------

    @FXML
    void initialize() {
        this.columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        tableViewDetalles.setSelectionModel(null);

    }
    private ObservableList<DetalleVenta> getListaDetalles(Venta venta) {
        List<DetalleVenta> ventaList = venta.getListaDetalles();
        listaDetallesVenta.addAll( ventaList );
        double aux = 0;
        for (DetalleVenta detalleVenta: ventaList) {
            aux+= detalleVenta.getSubTotal();
        }
        txtPrecioTotal.setText( String.valueOf( aux ) );
        return listaDetallesVenta;
    }

    void refrescarTableView(Venta venta) {
        tableViewDetalles.getItems().clear();
        tableViewDetalles.setItems( getListaDetalles(venta) );

    }


}
