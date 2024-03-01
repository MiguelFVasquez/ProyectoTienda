package co.edu.uniquindio.estructuraDatos.activity.model;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.model.interfaces.ICliente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente implements ICliente {
    private String nombre;
    private String numeroIdentificacion;
    private String direccion;
    private CarritoCompra carritoCliente;

    public Cliente() {
    }

    public Cliente(String nombre, String numeroIdentificacion, String direccion) {
        this.nombre = nombre;
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
        this.carritoCliente = new CarritoCompra();
    }

    public CarritoCompra getCarritoCliente() {
        return carritoCliente;
    }

    public void setCarritoCliente(CarritoCompra carritoCliente) {
        this.carritoCliente = carritoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //Funciones propias

    public boolean verificarIdentificacion(String numeroIdentificacion) {
        return this.numeroIdentificacion.equals(numeroIdentificacion);
    }

    //--------------FUNCIONALIDAD CARRITO----------------------------

    /**
     *
     * @param newProducto
     * @return
     * @throws ProductoException
     */
    @Override
    public void agregarACarrito(Producto newProducto) throws ProductoException {
        carritoCliente.agregarACarro(newProducto);
    }

    public Producto obtenerProductoCarrito(String codigo) throws ProductoException {
        return carritoCliente.obtenerProductoCarrito(codigo);
    }
    public ArrayList<Producto> obtenerProductosCarrito(){
        return carritoCliente.getListaProductos();
    }

    /**
     *
     * @param eliminarProducto
     * @return
     * @throws ProductoException
     */
    @Override
    public boolean eliminarDeCarrito(Producto eliminarProducto) throws ProductoException {
        boolean eliminado= false;
        Producto productoObtenido= obtenerProductoCarrito(eliminarProducto.getCodigo());
        if (productoObtenido==null){
            throw  new ProductoException("El producto de código: " + eliminarProducto.getCodigo()+ " no ha sido encontrado");
        }else {
            carritoCliente.eliminarDeCarrito(eliminarProducto);
        }
        return eliminado;
    }
    public void cancelarVenta() {
        carritoCliente.cancelarVenta();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(numeroIdentificacion, cliente.numeroIdentificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroIdentificacion);
    }


    public void comprarProductos() {
        carritoCliente.comprarProductos();
    }
}
