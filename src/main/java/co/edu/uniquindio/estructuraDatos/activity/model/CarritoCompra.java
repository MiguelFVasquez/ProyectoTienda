package co.edu.uniquindio.estructuraDatos.activity.model;

import java.util.ArrayList;
import java.util.HashSet;

public class CarritoCompra {
    private String codigoCarro;
    private HashSet<String> listaCodigosProductos;
    private ArrayList<Producto> listaProductos;

    public CarritoCompra() {
        this.codigoCarro="0";
        this.listaCodigosProductos = new HashSet<>();
        this.listaProductos = new ArrayList<>();
    }

    public CarritoCompra(String codigoCarro) {
        this.codigoCarro=codigoCarro;
        this.listaCodigosProductos = new HashSet<>();
        this.listaProductos = new ArrayList<>();
    }

    public boolean verificarProducto(String codigo){
        return listaCodigosProductos.contains(codigo);
    }

    public void agregarACarro(Producto newProducto){
        listaCodigosProductos.add(newProducto.getCodigo());
        listaProductos.add(newProducto);
    }
    public void eliminarDeCarrito(Producto productoEliminar){
        listaCodigosProductos.remove(productoEliminar.getCodigo());
        listaProductos.remove(productoEliminar);
    }

    public HashSet<String> getListaCodigosProductos() {
        return listaCodigosProductos;
    }

    public void setListaCodigosProductos(HashSet<String> listaCodigosProductos) {
        this.listaCodigosProductos = listaCodigosProductos;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
