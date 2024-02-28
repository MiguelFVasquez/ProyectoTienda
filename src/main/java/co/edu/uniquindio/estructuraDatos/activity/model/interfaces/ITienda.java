package co.edu.uniquindio.estructuraDatos.activity.model.interfaces;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ClienteException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.exceptions.VentaException;
import co.edu.uniquindio.estructuraDatos.activity.model.Cliente;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;
import co.edu.uniquindio.estructuraDatos.activity.model.Venta;

public interface ITienda {

    //----------Metodos para los clientes----------------------------
    public boolean crearCliente(Cliente newCliente) throws ClienteException;
    public boolean acualizarCliente(Cliente clienteActualizar) throws ClienteException;
    public boolean eliminarCliente(Cliente clienteEliminar) throws ClienteException;

    //----------Metodos para la venta-----------------------
    public boolean crearVenta(Venta newVenta) throws VentaException;
    public boolean eliminaVenta(Venta ventaEliminar) throws VentaException;
    //----------Metodos para los clientes-----------------------
    public boolean agregarProducto(Producto newProducto) throws ProductoException;
    public boolean actualizarProducto(Producto productoActualizar) throws ProductoException;
    public boolean ventaProducto(Producto productoVender) throws ProductoException;
    public boolean eliminarProducto(Producto productoEliminar) throws ProductoException;
}
