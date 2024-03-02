package co.edu.uniquindio.estructuraDatos.activity.controllers;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import co.edu.uniquindio.estructuraDatos.activity.model.Tienda;
import co.edu.uniquindio.estructuraDatos.activity.model.Venta;
import co.edu.uniquindio.estructuraDatos.activity.persistence.Persistence;
import co.edu.uniquindio.estructuraDatos.activity.viewControllers.AdminViewController;
import co.edu.uniquindio.estructuraDatos.activity.viewControllers.CarritoComprasViewController;
import co.edu.uniquindio.estructuraDatos.activity.viewControllers.ClienteViewController;
import co.edu.uniquindio.estructuraDatos.activity.viewControllers.InicioViewController;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ModelFactoryController {
    private InicioViewController inicioViewController;
    private ClienteViewController clienteViewController;
    private AdminViewController adminViewController;
    private CarritoComprasViewController carritoComprasViewController;
    static Tienda tienda;




    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }

    // Método para obtener la instancia de nuestra clase

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos

        System.out.println("Invocacion clase singleton");
        inicializarDatos();

    }
    private void inicializarDatos(){
        tienda = new Tienda("Tienda");
        cargarDatosDesdeArchivos(tienda);

    }
    public void initInicioViewController(InicioViewController inicioViewController){
        this.inicioViewController= inicioViewController;
    }
    public void initClienteController(ClienteViewController initClienteViewController){
        this.clienteViewController= initClienteViewController;
    }
    public void initAdminController(AdminViewController initAdminViewController){
        this.adminViewController= initAdminViewController;
    }
    public void initCarritoController(CarritoComprasViewController initcarritoComprasViewController) {
        this.carritoComprasViewController = initcarritoComprasViewController;
    }

    public HashMap<String, Cliente> getClientes(){
        return tienda.getMapClientes();
    }



    //--------------------------FUNCIONES DE CLIENTE----------------------------------------------------------

    public boolean verificarCliente(String id){
        return tienda.verificarCliente( id );
    }

    public boolean registrarCliente(String nombre, String id, String direccion) throws ClienteException {

        Cliente cliente = new Cliente(nombre,id,direccion);
        return tienda.crearCliente(cliente);
    }
    public static void cargarDatosDesdeArchivos(Tienda tienda) {
        try {
            Persistence.cargarDatosArchivos(tienda);
            System.out.println("Serializado de usuarios");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public Cliente obtenerCliente(String id) {
        return tienda.obtenerCliente( id );
    }

    public void mostrarInfoCliente(Cliente cliente){
        clienteViewController.nombreCliente.setText( cliente.getNombre() );
        clienteViewController.txtNombreCliente.setText( cliente.getNombre() );
        clienteViewController.txtNumeroIdentificacion.setText( cliente.getNumeroIdentificacion() );
        clienteViewController.txtDireccion.setText( cliente.getDireccion() );
    }

    public boolean actualizarCliente(Cliente cliente) throws ClienteException {
        return tienda.acualizarCliente( cliente );
    }
    //----------------------------------------FUNCIONES DE PRODUCTO-----------------------------------------------------
    public HashMap<String, Producto> getListaProductos() {
        return tienda.getMapProductos();
    }

    public boolean agregarProductoCarritoCliente(Producto selectedItem, String id) throws ProductoException {
        return tienda.agregarProductoCliente( selectedItem , id );
    }
    public boolean eliminarProducto(Producto selectedItem, String id) throws ProductoException {
        return tienda.eliminarProductoCliente( selectedItem , id);
    }
    public Producto obtenerProducto(String codigo){
        return tienda.obtenerProducto(codigo);
    }
    public Producto obtenerProductoNombre(String nombre){
        return tienda.obtenerProductoNombre(nombre);
    }
    public boolean eliminarProducto(Producto productoSeleccionado) throws ProductoException {
        return tienda.eliminarProducto(productoSeleccionado);
    }

    //--------------------------------------FUNCIONES ADMIN-------------------------------------------------------------
    public boolean eliminarCliente(String numeroIdentificacion) throws ClienteException {
       return tienda.eliminarCliente( obtenerCliente( numeroIdentificacion ) );
    }
    public List<Venta> getListaVentas() {
        return tienda.getListaVentas();
    }

    //--------------------------------------FUNCIONES CARRITO-----------------------------------------------------------
    public boolean eliminarProductosCarrito(String identificacionCliente) throws ProductoException, ClienteException {
        return tienda.eliminarProductosCliente(identificacionCliente);
    }
    public boolean comprarProductosCarrito(String identificacionCliente) throws ClienteException {
        return tienda.comprarProductosCarrito(obtenerCliente( identificacionCliente ));
    }


    //-------------------------------------------FUNCIONES DE SERIALIZADO-----------------------------------------------
    public void serializarClientes() throws IOException {
        Persistence.guardarClientes( tienda.getMapClientes() );
    }
    public void serializarProductos() throws IOException {
        Persistence.guardarProductos( tienda.getMapProductos() );

    }

    public void serializarVentas() throws IOException {
        Persistence.guardarVentas( tienda.getListaVentas() );
    }




}
