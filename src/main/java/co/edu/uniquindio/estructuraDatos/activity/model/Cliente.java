package co.edu.uniquindio.estructuraDatos.activity.model;

import co.edu.uniquindio.estructuraDatos.activity.exceptions.ProductoException;
import co.edu.uniquindio.estructuraDatos.activity.model.interfaces.ICliente;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente implements ICliente {
    private String nombre;
    private String numeroIdentificacion;
    private String direccion;
    private Set<Producto> listaProductos; //Carrito

    public Cliente() {
    }

    public Cliente(String nombre, String numeroIdentificacion, String direccion) {
        this.nombre = nombre;
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
        this.listaProductos = new HashSet<>();
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
    private boolean verificarProducto(Producto newProducto){
        return listaProductos.contains(newProducto);
    }
    private boolean verificarCantidadProducto(Producto newProducto){
        return false;
    }
    private Producto obtenerProducto(String codigo){
        for (Producto producto: listaProductos) {
            if (producto.verificarCodigo(codigo)) return producto;
        }
        return null;
    }

    /**
     *
     * @param newProducto
     * @return
     * @throws ProductoException
     */
    @Override
    public boolean agregarACarrito(Producto newProducto) throws ProductoException {
        boolean agregado= false;

        if (verificarProducto(newProducto)){
            throw new ProductoException("El producto ya se encuentra en el carrito");
        }else {
            agregado=true;
            listaProductos.add(newProducto);
        }
        return agregado;
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
        Producto productoObtenido= obtenerProducto(eliminarProducto.getCodigo());
        if (productoObtenido==null){
            throw  new ProductoException("El producto de código: " + eliminarProducto.getCodigo()+ " no ha sido encontrado");
        }else {
            eliminado=true;
            listaProductos.remove(productoObtenido);
        }
        return eliminado;
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

}
