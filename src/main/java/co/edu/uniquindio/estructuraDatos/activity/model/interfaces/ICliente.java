package co.edu.uniquindio.estructuraDatos.activity.model.interfaces;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.model.Producto;

public interface ICliente {
    public boolean agregarACarrito(Producto newProducto) throws ProductoException;
    public boolean eliminarDeCarrito(Producto eliminarProducto) throws ProductoException;
}
